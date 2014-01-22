/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {
    
    private GameState[] gameStates;
    private int currentState;
    
    public GameStateManager(){
        gameStates = new GameState[State.MENUSTATE.getTotalNumberofStates()];
        
        currentState = State.MENUSTATE.getStateNumber();
        gameStates[currentState] = new MenuState(this);
        gameStates[State.LEVEL1STATE.getStateNumber()] = new Level1State();
        
    }
    


public void setState(int state){
    if(state == currentState || state < 0 || state > gameStates.length -1){
        
    }
    else{
        currentState = state;
        gameStates[currentState].init();
    }
}

public int getCurrentStateNumber(){
    return this.currentState;
}

public GameState getCurrentGameState(){
    return gameStates[currentState];
}

public void update(){
    
    gameStates[currentState].update();
    
}

public void draw(Graphics2D g){
    
    gameStates[currentState].draw(g);
    
}



}