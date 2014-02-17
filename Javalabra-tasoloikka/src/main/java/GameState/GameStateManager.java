package GameState;

import Global.MusicPlayer;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author nkostiai
 * 
* GameStateManager pitää huolta siitä, että pelin kullakin hetkellä oikea
 * gamestate piirretään ruudulle. GameStateManager hoitaa myös nykyisen
 * GameStaten vaihtamisen ja jokaisella pelin GameStatella on viite samaan
 * GameStateManageriin.
 * 
*
 */
public class GameStateManager {

    private GameState[] gameStates;
    private int currentState;

    public GameStateManager() {
        gameStates = new GameState[State.MENUSTATE.getTotalNumberofStates()];
        Global.MusicPlayer.init();
        setState(State.MENUSTATE.getStateNumber());
        gameStates[State.MENUSTATE.getStateNumber()] = new MenuState(this);
        gameStates[State.LEVEL1STATE.getStateNumber()] = new Level1State(this);

    }

    public void setState(int state) {
        if (state == currentState || state < 0 || state > gameStates.length - 1) {

        } else {
            if(state == State.MENUSTATE.getStateNumber()){
                MusicPlayer.stop("level1");
                MusicPlayer.loop("menu", 600, MusicPlayer.getFrames("menu") - 2200);
            }
            else{
                MusicPlayer.stop("menu");
                MusicPlayer.loop("level1", 600, MusicPlayer.getFrames("level1") - 2200);
            }
            currentState = state;
            gameStates[currentState].init();
        }
    }
    
    public void muteMusic(){

    }

    public int getCurrentStateNumber() {
        return this.currentState;
    }

    public GameState getCurrentGameState() {
        return gameStates[currentState];
    }

    public void update() {

        gameStates[currentState].update();

    }


}
