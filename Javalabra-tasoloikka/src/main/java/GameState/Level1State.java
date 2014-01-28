

package GameState;

import Entity.Player;
import Global.C;
import Global.Keys;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Color;
import java.awt.Graphics2D;


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
        
        tileMap = new TileMap(32);
        tileMap.loadTiles("/Tilesets/tileset.png");
        tileMap.loadMap("/Maps/level1map.map");
        tileMap.setPosition(10, 10);
        
        bg = new Background("/Backgrounds/menubackground.PNG", 0.1);
        
        player = new Player(tileMap);
        player.setPosition(100, 100);
        
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
    tileMap.setPosition(C.MIDDLEX - player.getX(), C.MIDDLEY - player.getY());
    }

    @Override
    public void handleInput() {
        
        player.setUp(Keys.keyState[Keys.UP]);
	player.setLeft(Keys.keyState[Keys.LEFT]);
	player.setDown(Keys.keyState[Keys.DOWN]);
	player.setRight(Keys.keyState[Keys.RIGHT]);
	player.setJumping(Keys.keyState[Keys.BUTTON1]);
	player.setGliding(Keys.keyState[Keys.BUTTON2]);
//       if(Keys.isPressed(Keys.BUTTON3)){
//           player.setMelee();
//       }
//       if(Keys.isPressed(Keys.BUTTON4)){
//           player.setFiring();
//       }
       
    
    
    }
    
}