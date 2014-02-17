package GameState;

import Entity.Enemy;
import Entity.Player;
import Global.MusicPlayer;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public abstract class LevelState extends GameState {
    
    protected boolean gameOver;
    protected int gameOverCounter;
    protected boolean gameWon;
    protected int gameWonCounter;
    protected TileMap tileMap;
    protected Background bg;
    protected Player player;
    protected ArrayList<Enemy> enemies;
    protected Random random;
    
    public LevelState(){
        type = StateType.LEVELSTATE;
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
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    
    @Override
    public void update(){
        if(player.isDead()&&!gameOver){
            gameOver = true;
            gameOverCounter = 0;
            MusicPlayer.stop("level1");
        }
        if(!gameWon&&enemies.isEmpty()){
            gameWon = true;
            gameWonCounter = 0;
            MusicPlayer.stop("level1");
        }
        
    }
    
    public boolean getGameOver(){
        return gameOver;
    }
    
    public boolean getGameWon(){
        return gameWon;
    }
}
