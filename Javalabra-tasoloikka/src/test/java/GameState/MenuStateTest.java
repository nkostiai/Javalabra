/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import Global.KeyboardController;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class MenuStateTest {
    MenuState testitaso;
    
    
    public MenuStateTest() {
    }
    
    @Before
    public void setUp() {
        GameStateManager gsm = new GameStateManager();
        testitaso = new MenuState(gsm);
    }
    
    @After
    public void tearDown() {
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, false);
    }

    @Test
    public void konstruktoriAsettaaOikeanValinnan() {
        assertEquals(0, testitaso.getCurrentChoice());
    }
    
    @Test
    public void valintaVaihtuuKunNappaintaPainetaan(){
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, true);
        testitaso.update();
        assertEquals(1, testitaso.getCurrentChoice());
    }
    
    @Test
    public void valintaVaihtuuTakaisinKunNappaintaPainetaanYliAyraiden(){
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, true);
        testitaso.update();
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, false);
        testitaso.update();
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, true);
        testitaso.update();
        assertEquals(0, testitaso.getCurrentChoice());
    }
    
    @Test
    public void konstruktoriAsettaaTaustaKuvan(){
        assertNotNull(testitaso.getBG());
    }
    
    @Test
    public void konstruktoriAsettaaGameStateManagerin(){
        assertNotNull(testitaso.gsm);
    }
    
    @Test
    public void testaaSelectKunValintaEka(){
        testitaso.select();
        assertEquals(1, testitaso.gsm.getCurrentStateNumber());
    }
    
    
}
