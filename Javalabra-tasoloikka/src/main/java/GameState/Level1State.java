package GameState;

import Entity.*;
import Entity.Enemies.*;
import Global.*;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nkostiai
 * 
* GameState -luokan perivä ensimmäistä tasoa kuvaava pelitasoluokka.
 * Pelitasoluokalla on oma TileMap -tyyppinen karttansa, taustakuvansa sekä
 * pelihahmo. Luokassa säilytetään myös kaikki luokkaa kuvaavaan kenttään
 * liittyvät objektit.
 * 
*
 */
public class Level1State extends GameState {

    private TileMap tileMap;
    private Background bg;
    private Player player;
    private ArrayList<Enemy> enemies;
    private Random random;

    public Level1State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    @Override
    public final void init() {
        random = new Random();
        initializeTileMap();

        initializeBackground();

        initializePlayer();
        
        initializeEnemies();

    }
    
    private void initializeEnemies(){
        enemies = new ArrayList<>();
        for(int i = 0; i < 10; i++){
        Enemy1 s = new Enemy1(this.tileMap);
        s.setPosition(random.nextInt(2000)+50, 150);
        enemies.add(s);
        }
    }

    public void initializeBackground() {
        bg = new Background("/Backgrounds/menubackground.PNG", 0.5);
    }

    public void initializeTileMap() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/Tilesets/tileset.png");
        tileMap.loadMap("/Maps/level1map.map");
        tileMap.setPosition(10, 10);
    }

    public void initializePlayer() {
        player = new Player(tileMap);
        player.setPosition(300, 100);
    }

    @Override
    public void draw(Graphics2D g) {
        
        //draw the background
        bg.draw(g);
        //draw the map
        tileMap.draw(g);
        //draw the player
        player.draw(g);
        //draw the enemies
        drawEnemies(g);

    }
    
    private void drawEnemies(Graphics2D g){
        for(Enemy enemy: enemies){
            enemy.draw(g);
        }
    }

    @Override
    public void update() {
        //handle input    
        handleInput();

        //update player
        player.update();

        //set and update background
        bg.setPosition(tileMap.getx(), tileMap.gety());
        bg.update();

        //set tilemap position
        tileMap.setPosition(GlobalConstants.MIDDLEX - player.getX(), GlobalConstants.MIDDLEY - player.getY());
        
        //update all the enemies
        updateEnemies();
    }
    
    private void updateEnemies(){
        for(Enemy enemy: enemies){
            enemy.update();
        }
    }
    
    @Override
    public void handleInput() {

        player.setUp(KeyboardController.keyState[Buttons.UP.getIDNumber()]);
        player.setLeft(KeyboardController.keyState[Buttons.LEFT.getIDNumber()]);
        player.setDown(KeyboardController.keyState[Buttons.DOWN.getIDNumber()]);
        player.setRight(KeyboardController.keyState[Buttons.RIGHT.getIDNumber()]);
        player.setJumping(KeyboardController.keyState[Buttons.BUTTON1.getIDNumber()]);
        player.setGliding(KeyboardController.keyState[Buttons.BUTTON2.getIDNumber()]);
        if (KeyboardController.isPressed(Buttons.BUTTON4.getIDNumber())) {
            player.setFiring();
        }

    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public Background getBG() {
        return bg;
    }

    public Player getPlayer() {
        return player;
    }

}
