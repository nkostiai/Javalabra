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
    public void nappaintenPainallusRekisteroityyOikein(){
        Keys.setKeyPressStatus(KeyEvent.VK_ENTER, true);
        assertTrue(Keys.isPressed(Keys.ENTER));
    }
    
    @Test
    public void nappaintenPainallusPoistuuOikein(){
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
    
}
