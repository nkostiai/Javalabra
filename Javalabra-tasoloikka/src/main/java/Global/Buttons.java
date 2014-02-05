/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Global;

import java.awt.event.KeyEvent;

/**
 *
 * @author Admin
 */
public enum Buttons {
    UP(KeyEvent.VK_UP, 0), DOWN(KeyEvent.VK_DOWN, 1), LEFT(KeyEvent.VK_LEFT, 2), RIGHT(KeyEvent.VK_RIGHT, 3), ENTER(KeyEvent.VK_ENTER, 4), ESCAPE(KeyEvent.VK_ESCAPE, 5), BUTTON1(KeyEvent.VK_C, 6), BUTTON2(KeyEvent.VK_D, 7), BUTTON3(KeyEvent.VK_X, 8), BUTTON4(KeyEvent.VK_Z, 9);
    
    private int keyEventNumber;
    private int idNumber;
    
    private Buttons(int keyEventNumber, int idNumber){
        this.keyEventNumber = keyEventNumber;
        this.idNumber = idNumber;
    }
    
    public int getKeyEventNumber(){
        return keyEventNumber;
    }
    
    public int getIDNumber(){
        return idNumber;
    }
}
