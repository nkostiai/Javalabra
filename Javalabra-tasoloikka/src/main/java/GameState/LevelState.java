package GameState;

import Entity.Enemy;
import Entity.Player;
import TileMap.Background;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public abstract class LevelState extends GameState {
    
    /**
     * Kertoo onko peli ohi.
     */
    protected boolean gameOver;
    
    /**
     * Montako framea peli on ollut ohi. 
     */
    protected int gameOverCounter;
    
    /**
     * Kertoo onko peli voitettu.
     */
    protected boolean gameWon;
    
    /**
     * Montako framea peli on ollut voitettu.
     */
    protected int gameWonCounter;
    
    /**
     * Viite kenttätiedostoon.
     */
    protected TileMap tileMap;
    
    /**
     * Viite taustakuvaan.
     */
    protected Background bg;
    
    /**
     * Viite pelaajaan.
     */
    protected Player player;
    
    /**
     * Kaikki tason viholliset listassa.
     */
    protected ArrayList<Enemy> enemies;
    
    /**
     * Satunnaislukugeneraattori.
     */
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
        }
        if(!gameWon&&enemies.isEmpty()){
            gameWon = true;
            gameWonCounter = 0;
        }
        
    }
    
    public boolean getGameOver(){
        return gameOver;
    }
    
    public boolean getGameWon(){
        return gameWon;
    }
}
