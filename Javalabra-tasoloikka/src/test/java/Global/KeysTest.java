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
public class KeysTest {

    
    public KeysTest() {
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
        for(int i = 0; i < Keys.numberOfKeys(); i++){
            assertFalse(Keys.isPressed(i));
        }
    }
    
    @Test
    public void enterinPainallusRekisteroityyOikein(){
        Keys.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        assertTrue(Keys.isPressed(Keys.ENTER));
    }
    @Test
    public void nuoltenPainallusRekisteroityyOikein(){
        asetaNappain(KeyEvent.VK_DOWN, true);
        asetaNappain(KeyEvent.VK_UP, true);
        asetaNappain(KeyEvent.VK_LEFT, true);
        asetaNappain(KeyEvent.VK_RIGHT, true);
        assertTrue(Keys.isPressed(Keys.UP));
        assertTrue(Keys.isPressed(Keys.UP));
        assertTrue(Keys.isPressed(Keys.LEFT));
        assertTrue(Keys.isPressed(Keys.RIGHT));
    }
    
    @Test
    public void kirjaintenPainallusRekisteroityyOikein(){
        asetaNappain(KeyEvent.VK_C, true);
        asetaNappain(KeyEvent.VK_D, true);
        asetaNappain(KeyEvent.VK_X, true);
        asetaNappain(KeyEvent.VK_Z, true);
        assertTrue(Keys.isPressed(Keys.BUTTON1));
        assertTrue(Keys.isPressed(Keys.BUTTON2));
        assertTrue(Keys.isPressed(Keys.BUTTON3));
        assertTrue(Keys.isPressed(Keys.BUTTON4));
    }
    
    @Test
    public void escapenPainallusRekisteroityyOikein(){
        asetaNappain(KeyEvent.VK_ESCAPE, true);
        assertTrue(Keys.isPressed(Keys.ESCAPE));
    }
    
    @Test
    public void enterinPainallusPoistuuOikein(){
        Keys.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        Keys.setKeyPressStatus(KeyEvent.VK_ENTER, false);
        assertFalse(Keys.isPressed(Keys.ENTER));
    }
    
    @Test
    public void nappaimetRekisteroityyMyosPreviousStateen(){
        Keys.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        Keys.update();
        assertFalse(Keys.isPressed(Keys.ENTER));
    }

    @Test
    public void escinPainallusPoistuuOikein(){
        Keys.setKeyPressStatus(KeyEvent.VK_ESCAPE, true);
        Keys.setKeyPressStatus(KeyEvent.VK_ESCAPE, false);
        assertFalse(Keys.isPressed(Keys.ESCAPE));
    }
    
    
    public void asetaNappain(int nappain, boolean paalle){
        Keys.setKeyPressStatus(nappain, paalle);
    }
}
