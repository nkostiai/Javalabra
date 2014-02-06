

package GameState;

import Entity.Bullet;
import Entity.Direction;
import Entity.Player;
import Global.Buttons;
import Global.GlobalConstants;
import Global.KeyboardController;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
*
* @author nkostiai
*
* GameState -luokan perivä ensimmäistä tasoa kuvaava pelitasoluokka.
* Pelitasoluokalla on oma TileMap -tyyppinen karttansa, taustakuvansa sekä pelihahmo.
* Luokassa säilytetään myös kaikki luokkaa kuvaavaan kenttään liittyvät objektit.
*
*
*/
public class Level1State extends GameState{
    
    private TileMap tileMap;
    private Background bg;
    
    private Player player;
    
    
    public Level1State(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    @Override
    public void init() {
        
        initializeTileMap();
        
        initializeBackground();
        
        initializePlayer();
        
    }
    
    public void initializeBackground(){
        bg = new Background("/Backgrounds/menubackground.PNG", 0.1);
    }
    
    public void initializeTileMap(){
        tileMap = new TileMap(32);
        tileMap.loadTiles("/Tilesets/tileset.png");
        tileMap.loadMap("/Maps/level1map.map");
        tileMap.setPosition(10, 10);
    }

    public void initializePlayer(){
        player = new Player(tileMap);
        player.setPosition(300, 100);
    }
    
    @Override
    public void draw(Graphics2D g) {
        
        bg.draw(g);
        
        tileMap.draw(g);
        
        player.draw(g);
        
    
    }

    @Override
    public void update() {

    handleInput();
    
    
    player.update();
    bg.update();
    
    tileMap.setPosition(GlobalConstants.MIDDLEX - player.getX(), GlobalConstants.MIDDLEY - player.getY());
    }

    @Override
    public void handleInput() {
        
        player.setUp(KeyboardController.keyState[Buttons.UP.getIDNumber()]);
	player.setLeft(KeyboardController.keyState[Buttons.LEFT.getIDNumber()]);
	player.setDown(KeyboardController.keyState[Buttons.DOWN.getIDNumber()]);
	player.setRight(KeyboardController.keyState[Buttons.RIGHT.getIDNumber()]);
	player.setJumping(KeyboardController.keyState[Buttons.BUTTON1.getIDNumber()]);
	player.setGliding(KeyboardController.keyState[Buttons.BUTTON2.getIDNumber()]);
       if(KeyboardController.isPressed(Buttons.BUTTON4.getIDNumber())){
           player.setFiring();
       }
       
    
    
    }
    
 
    public TileMap getTileMap(){
        return tileMap;
    }
    
    public Background getBG(){
        return bg;
    }
    
    public Player getPlayer(){
        return player;
    }
    
}