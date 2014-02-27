package GameState;

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
    
    /**
     * Taulukko käytössä olevista GameStateista.
     */
    private GameState[] gameStates;
    
    /**
     * Nykyisen gamestaten indeksi taulukossa.
     */
    private int currentState;

    public GameStateManager() {
        gameStates = new GameState[State.MENUSTATE.getTotalNumberofStates()];
        setState(State.MENUSTATE.getStateNumber());
        gameStates[State.MENUSTATE.getStateNumber()] = new MenuState(this);
        gameStates[State.LEVEL1STATE.getStateNumber()] = new Level1State(this);

    }
    
    /**
     * Asettaa nykyisen staten ja vaihtaa taustamusiikin.
     * @param state Mikä state laitetaan
     */
    public void setState(int state) {
        if (state == currentState || state < 0 || state > gameStates.length - 1) {

        } else {
            currentState = state;
            gameStates[currentState].init();
        }
    }
    
    
    public int getCurrentStateNumber() {
        return this.currentState;
    }

    public GameState getCurrentGameState() {
        return gameStates[currentState];
    }
    
    /**
     * Päivittää nykyisen gamestaten.
     */
    public void update() {

        gameStates[currentState].update();

    }


}
