
package Global;

import java.awt.event.KeyEvent;
/**
*
* @author nkostiai
* 
* KeyboardController -luokka on globaali luokka, joka hoitaa näppäinpainallusten rekisteröinnin ja välittämisen
* pelin muihin osiin.
*
*
*/
public class KeyboardController {
    
    /**
     * Pelin käytössä olevien näppäinten määrä.
     */
    public static final int NumberOfKeys = 10;
    
    /**
     * Näppäinpainallusten tilanteet tällä framella.
     */
    public static boolean[] keyState = new boolean[NumberOfKeys];
    
    /**
     * Näppäinpainallusten tilanteet edellisellä framella.
     */
    public static boolean[] prevKeyState = new boolean[NumberOfKeys];
    

    public static void setKeyPressStatus(int i, boolean b) {
        if (i == KeyEvent.VK_UP) {
            keyState[KeyConfig.UP.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_LEFT) {
            keyState[KeyConfig.LEFT.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_DOWN) {
            keyState[KeyConfig.DOWN.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_RIGHT) {
            keyState[KeyConfig.RIGHT.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_C) {
            keyState[KeyConfig.BUTTON1.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_D) {
            keyState[KeyConfig.BUTTON2.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_X) {
            keyState[KeyConfig.BUTTON3.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_Z) {
            keyState[KeyConfig.BUTTON4.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_ENTER) {
            keyState[KeyConfig.ENTER.getIDNumber()] = b;
        } else if (i == KeyEvent.VK_ESCAPE) {
            keyState[KeyConfig.ESCAPE.getIDNumber()] = b;
        }
    }
    
    /**
     * Päivitä näppäinpainallukset taulukkoon.
     */
    public static void update() {
        System.arraycopy(keyState, 0, prevKeyState, 0, NumberOfKeys);
    }
    
    /**
     * Onko näppäin koodilla i painettu.
     * 
     * @param i näppäimen koodi.
     * @return Onko näppäin painettuna.
     */
    public static boolean isPressed(int i) {
        return keyState[i] && !prevKeyState[i];
    }
    
    public static int numberOfKeys(){
        return NumberOfKeys;
    }
}
