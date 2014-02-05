/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Global;

import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class KeyboardControllerTest {

    
    public KeyboardControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void oletuksenaMitaanNappaintaEiPaineta() {
        for(int i = 0; i < KeyboardController.numberOfKeys(); i++){
            assertFalse(KeyboardController.isPressed(i));
        }
    }
    
    @Test
    public void enterinPainallusRekisteroityyOikein(){
        KeyboardController.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        assertTrue(KeyboardController.isPressed(Buttons.ENTER.getIDNumber()));
    }
    @Test
    public void nuoltenPainallusRekisteroityyOikein(){
        asetaNappain(KeyEvent.VK_DOWN, true);
        asetaNappain(KeyEvent.VK_UP, true);
        asetaNappain(KeyEvent.VK_LEFT, true);
        asetaNappain(KeyEvent.VK_RIGHT, true);
        assertTrue(KeyboardController.isPressed(Buttons.DOWN.getIDNumber()));
        assertTrue(KeyboardController.isPressed(Buttons.UP.getIDNumber()));
        assertTrue(KeyboardController.isPressed(Buttons.LEFT.getIDNumber()));
        assertTrue(KeyboardController.isPressed(Buttons.RIGHT.getIDNumber()));
    }
    
    @Test
    public void kirjaintenPainallusRekisteroityyOikein(){
        asetaNappain(KeyEvent.VK_C, true);
        asetaNappain(KeyEvent.VK_D, true);
        asetaNappain(KeyEvent.VK_X, true);
        asetaNappain(KeyEvent.VK_Z, true);
        assertTrue(KeyboardController.isPressed(Buttons.BUTTON1.getIDNumber()));
        assertTrue(KeyboardController.isPressed(Buttons.BUTTON2.getIDNumber()));
        assertTrue(KeyboardController.isPressed(Buttons.BUTTON3.getIDNumber()));
        assertTrue(KeyboardController.isPressed(Buttons.BUTTON4.getIDNumber()));
    }
    
    @Test
    public void escapenPainallusRekisteroityyOikein(){
        asetaNappain(KeyEvent.VK_ESCAPE, true);
        assertTrue(KeyboardController.isPressed(Buttons.ESCAPE.getIDNumber()));
    }
    
    @Test
    public void enterinPainallusPoistuuOikein(){
        KeyboardController.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_ENTER, false);
        assertFalse(KeyboardController.isPressed(Buttons.ENTER.getIDNumber()));
    }
    
    @Test
    public void nappaimetRekisteroityyMyosPreviousStateen(){
        KeyboardController.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        KeyboardController.update();
        assertFalse(KeyboardController.isPressed(Buttons.ENTER.getIDNumber()));
    }

    @Test
    public void escinPainallusPoistuuOikein(){
        KeyboardController.setKeyPressStatus(KeyEvent.VK_ESCAPE, true);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_ESCAPE, false);
        assertFalse(KeyboardController.isPressed(Buttons.ESCAPE.getIDNumber()));
    }
    
    
    public void asetaNappain(int nappain, boolean paalle){
        KeyboardController.setKeyPressStatus(nappain, paalle);
    }
}
