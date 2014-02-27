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
public class Level1State extends LevelState {

    public Level1State(GameStateManager gsm) {
        super();
        this.gsm = gsm;

        init();
    }

    @Override
    public final void init() {
        random = new Random();
        this.gameOver = false;
        this.gameWon = false;
        initializeTileMap();

        initializeBackground();

        initializePlayer();

        initializeEnemies();
    }

    /**
     * Asettaa kaikki viholliset kentälle.
     */
    public void initializeEnemies() {
        enemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Enemy1 s = new Enemy1(this.tileMap);
            s.setPosition(random.nextInt(2500) + 100, 150);
            enemies.add(s);
        }
        for (int i = 0; i < 10; i++) {
            Enemy1 s = new Enemy1(this.tileMap);
            s.setPosition(random.nextInt(2500) + 100, 680);
            enemies.add(s);
        }
    }

    /**
     * Initialisoi taustakuvan.
     */
    public void initializeBackground() {
        bg = new Background("/Backgrounds/menubackground.PNG", 0.5);
        bg.setVector(0.4, 0);
    }

    /**
     * Initialisoi kartan.
     */
    public void initializeTileMap() {
        tileMap = new TileMap(32);
        tileMap.loadTiles("/Tilesets/tileset2.png");
        tileMap.loadMap("/Maps/testmap.map");
        tileMap.setPosition(10, 10);
    }

    /**
     * Initialisoi pelaajan.
     */
    public void initializePlayer() {
        player = new Player(tileMap);
        player.setPosition(300, 100);
    }

    @Override
    public void update() {
        super.update();
        if (!gameOver && !gameWon) {
            //handle input    
            handleInput();

            //update player
            player.update();

            //set and update background
            bg.setPosition(tileMap.getx(), tileMap.gety());
            bg.update();

            //set tilemap position
            tileMap.setPosition(GlobalConstants.MIDDLEX - player.getX(), GlobalConstants.MIDDLEY - player.getY());

            //check if bullets hit enemies
            player.checkBullets(enemies);

            //update all the enemies
            updateEnemies();
            checkEnemyAndPlayerCollision();

        } else {
            checkGameOverOrGameWon();
        }
    }
    
    /**
     * Tarkistaa onko peli ohi.
     */
    private void checkGameOverOrGameWon() {
        if (gameOver) {
            gameOverCounter++;
            if (gameOverCounter > 200) {
                gameOverCounter = 0;
                gameOver = false;
                gsm.setState(State.MENUSTATE.getStateNumber());
            }

        } else if (gameWon) {
            gameWonCounter++;
            if (gameWonCounter > 200) {
                gameWonCounter = 0;
                gameWon = false;
                gsm.setState(State.MENUSTATE.getStateNumber());
            }
        }
    }
    
    /**
     * Tarkistaa osuuko pelihahmo johonkin viholliseen.
     */
    private void checkEnemyAndPlayerCollision() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).intersects(player)) {
                player.getHit(enemies.get(i).getDamage());
                break;
            }
        }
    }
    
    /**
     * Päivittää kentän viholliset.
     */
    private void updateEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
            if (enemies.get(i).isDead()) {
                enemies.remove(i);
                i--;
            }
        }
    }

    @Override
    public void handleInput() {

        player.setUp(KeyboardController.keyState[KeyConfig.UP.getIDNumber()]);
        player.setLeft(KeyboardController.keyState[KeyConfig.LEFT.getIDNumber()]);
        player.setDown(KeyboardController.keyState[KeyConfig.DOWN.getIDNumber()]);
        player.setRight(KeyboardController.keyState[KeyConfig.RIGHT.getIDNumber()]);
        player.setJumping(KeyboardController.keyState[KeyConfig.BUTTON1.getIDNumber()]);
        player.setGliding(KeyboardController.keyState[KeyConfig.BUTTON2.getIDNumber()]);
        if (KeyboardController.isPressed(KeyConfig.BUTTON4.getIDNumber())) {
            player.setFiring();
        }

    }

}
