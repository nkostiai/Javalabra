package Drawer;

import Entity.Bullet;
import Entity.Enemy;
import Entity.MapObject;
import Entity.Player;
import GameState.GameState;
import GameState.GameStateManager;
import GameState.LevelState;
import GameState.MenuState;
import Global.GlobalConstants;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Drawer {

    private GameStateManager gsm;
    private Graphics2D g;
    private BufferedImage arrow;
    private boolean arrowSet = false;
    private BufferedImage gameOverScreen;
    private boolean gameOverScreenSet = false;
    private BufferedImage gameWonScreen;
    private boolean gameWonScreenSet = false;

    public Drawer(Graphics2D g, GameStateManager gsm) {
        this.gsm = gsm;
        this.g = g;
    }

    public void draw() {
        GameState stateToDraw = this.gsm.getCurrentGameState();
        switch (stateToDraw.getType()) {
            case MENUSTATE:
                drawMenuState((MenuState) stateToDraw);
                break;
            case LEVELSTATE:
                drawLevelState((LevelState) stateToDraw);
                break;
        }

    }

    private void drawMenuState(MenuState stateToDraw) {

        //draw background
        drawBackground(stateToDraw.getBG());

        //draw the title text
        g.setColor(stateToDraw.getTitleColor());
        g.setFont(stateToDraw.getTitleFont());
        g.drawString("Liikkuvien puskien", GlobalConstants.MIDDLEX - 170, GlobalConstants.MIDDLEY / 2);
        g.drawString("hävittämissimulaattori", GlobalConstants.MIDDLEX - 170, GlobalConstants.MIDDLEY / 2+40);

        //draw menu options
        g.setFont(stateToDraw.getRegularFont());
        drawMenuOptions(stateToDraw);
        drawDisclaimer();
    }

    private void drawMenuOptions(MenuState stateToDraw) {
        initArrow();
        for (int i = 0; i < stateToDraw.getOptions().length; i++) {
            if (i == stateToDraw.getCurrentChoice()) {
                g.setColor(Color.RED);
                g.drawImage(arrow, GlobalConstants.MIDDLEX - 55, GlobalConstants.MIDDLEY + i * 30 - 20, 20, 20, null);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawString(stateToDraw.getOptions()[i], GlobalConstants.MIDDLEX - 30, GlobalConstants.MIDDLEY + i * 30);
        }
    }
    
    private void initArrow(){
        if(!arrowSet){
            try {
                arrow = ImageIO.read(getClass().getResourceAsStream("/Sprites/arrow.png"));
            } catch (IOException ex) {
                Logger.getLogger(Drawer.class.getName()).log(Level.SEVERE, null, ex);
                GlobalConstants.error("Failed to load arrow graphics");
            }
            arrowSet = true;
        }
    }
    
    private void drawDisclaimer(){
        g.setColor(Color.RED);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        g.drawString("Game by Nikke Kostiainen, Music by Firage (Kermakastikeritari, http://http://firage.bandcamp.com/)", 8, GlobalConstants.WINDOWHEIGHT -8);
    }
    
    private void drawBackground(Background bg) {
        BufferedImage image = bg.getImage();
        double x = bg.getX();
        double y = bg.getY();
        g.drawImage(image, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(image, (int) x + image.getWidth(), (int) y, null);
        }
        if (x > 0) {
            g.drawImage(image, (int) x - image.getWidth(), (int) y, null);
        }
        if (y < 0) {
            g.drawImage(image, (int) x, (int) y + image.getHeight(), null);
        }
        if (y > 0) {
            g.drawImage(image, (int) x, (int) y - image.getHeight(), null);
        }
    }

    private void drawLevelState(LevelState stateToDraw) {
        if(!stateToDraw.getGameOver()&&!stateToDraw.getGameWon()){
        //draw the background
        drawBackground(stateToDraw.getBG());
        //draw the map
        drawTileMap(stateToDraw.getTileMap());
        //draw the enemies
        drawEnemies(stateToDraw.getEnemies());
        //draw the player
        drawPlayer(stateToDraw.getPlayer());
        }
        else{
            if(stateToDraw.getGameOver()){
            initGameOverScreen();
            g.drawImage(gameOverScreen, 0, 0, null);
            }
            else{
                initGameWonScreen();
                g.drawImage(gameWonScreen,0 , 0, null);
            }
        }

    }
    
    private void initGameWonScreen(){
        if(!gameWonScreenSet){
            try {
                gameWonScreen = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/victoryscreen.png"));
            } catch (IOException ex) {
                Logger.getLogger(Drawer.class.getName()).log(Level.SEVERE, null, ex);
                GlobalConstants.error("Failed to load game over graphics");
            }
            gameWonScreenSet = true;
        }
    }
    
    private void initGameOverScreen(){

        if(!gameOverScreenSet){
            try {
                gameOverScreen = ImageIO.read(getClass().getResourceAsStream("/Backgrounds/gameoverscreen.png"));
            } catch (IOException ex) {
                Logger.getLogger(Drawer.class.getName()).log(Level.SEVERE, null, ex);
                GlobalConstants.error("Failed to load game over graphics");
            }
            gameOverScreenSet = true;
        }
  
    }

    private void drawTileMap(TileMap tm) {
        for (int row = tm.getrowOffset(); row < tm.getrowOffset() + tm.getRowsToDraw(); row++) {
            if (row >= tm.getNumberOfRows()) {
                break;
            }

            for (int col = tm.getcolumnOffset(); col < tm.getcolumnOffset() + tm.getColumnsToDraw(); col++) {
                if (col >= tm.getNumberOfColumns()) {
                    break;
                }

                //if the tile to draw is completely transparent don't bother drawing it
                if (tm.getMap()[row][col] == 0) {
                    continue;
                }

                int tileType = tm.getMap()[row][col];
                int rowInTileSet = tileType / tm.getNumberOfTilesPerRow();
                int columnInTileSet = tileType % tm.getNumberOfTilesPerRow();

                g.drawImage(tm.getTiles()[rowInTileSet][columnInTileSet].getImage(), (int) tm.getx() + col * tm.getTileSize(), (int) tm.gety() + row * tm.getTileSize(), null);
            }

        }

    }

    private void drawPlayer(Player player) {
        //set position of tilemap
        player.setMapPosition();
        //draw the player
        drawMapObject(player);
        //draw player's bullets
        drawBullets(player.getBullets());
        //draw player stats
        drawHud(player);
    }
    
    private void drawHud(Player player){
        g.drawImage(player.getHUD().getImage(), 5, 10, null);
        g.setColor(player.getHUD().getColor());
        g.setFont(player.getHUD().getFont());
        g.drawString(player.getHealth()+"/"+player.getMaxHealth(), 30, 24);
        g.drawString(player.getMana()/100+"/"+player.getMaximumMana()/100, 30, 40);
        g.setColor(Color.red);
        g.drawString(""+player.getCollisionDate().getcurrentRow(), 30, 55);
    }

    private void drawEnemies(ArrayList<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            enemy.setMapPosition();
            drawMapObject(enemy);
        }
    }

    private void drawBullets(ArrayList<Bullet> bullets) {
        for (Bullet bullet : bullets) {
            bullet.setMapPosition();
            drawMapObject(bullet);
        }
    }

    private void drawMapObject(MapObject objectToDraw) {
        if(!(objectToDraw.isFlinching() && objectToDraw.getFlinchTimer() % 10 < 5)) {

        if (objectToDraw.getIfFacesRight()) {
            g.drawImage(objectToDraw.getAnimation().getImage(), (int) (objectToDraw.getX() + objectToDraw.getTileVariables().getXMapPosition() - objectToDraw.getWidth() / 2), (int) (objectToDraw.getY() + objectToDraw.getTileVariables().getYMapPosition() - objectToDraw.getHeight() / 2), objectToDraw.getWidth(), objectToDraw.getHeight(), null);
        } else {
            g.drawImage(objectToDraw.getAnimation().getImage(), (int) (objectToDraw.getX() + objectToDraw.getTileVariables().getXMapPosition() - objectToDraw.getWidth() / 2 + objectToDraw.getWidth()), (int) (objectToDraw.getY() + objectToDraw.getTileVariables().getYMapPosition() - objectToDraw.getHeight() / 2), -objectToDraw.getWidth(), objectToDraw.getHeight(), null);
        }
        }
    
    }

}
