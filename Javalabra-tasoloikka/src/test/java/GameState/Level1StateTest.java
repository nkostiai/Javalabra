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

public class Level1StateTest {
    Level1State testileveli;
    GameStateManager gsm;
    
    public Level1StateTest() {
    }
    
    @Before
    public void setUp() {
        gsm = new GameStateManager();
        testileveli = new Level1State(gsm);
    }
    
    @After
    public void tearDown() {
        KeyboardController.setKeyPressStatus(KeyEvent.VK_LEFT, false);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_RIGHT, false);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_UP, false);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, false);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_C, false);
    }

    @Test
    public void testaaEttaKonstruktoriAsettaaTileMapin() {
        assertNotNull(testileveli.getTileMap());
    }
    @Test
    public void testaaEttaKonstruktoriAsettaaTaustan(){
        assertNotNull(testileveli.getBG());
    }
    @Test
    public void testaaEttaKonstruktoriLuoPelaajan(){
        assertNotNull(testileveli.getPlayer());
    }
    
    @Test
    public void testaaHandleInput(){
        assertFalse(testileveli.getPlayer().getLeft());
        assertFalse(testileveli.getPlayer().getRight());
        assertFalse(testileveli.getPlayer().getUp());
        assertFalse(testileveli.getPlayer().getDown());
        assertFalse(testileveli.getPlayer().getJumping());
        KeyboardController.setKeyPressStatus(KeyEvent.VK_LEFT, true);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_RIGHT, true);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_UP, true);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_DOWN, true);
        KeyboardController.setKeyPressStatus(KeyEvent.VK_C, true);
        testileveli.update();
        assertTrue(testileveli.getPlayer().getLeft());
        assertTrue(testileveli.getPlayer().getRight());
        assertTrue(testileveli.getPlayer().getUp());
        assertTrue(testileveli.getPlayer().getDown());
        assertTrue(testileveli.getPlayer().getJumping());
    }
    
}
