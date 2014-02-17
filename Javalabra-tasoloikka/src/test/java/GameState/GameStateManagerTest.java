/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

import Global.GlobalConstants;
import Global.MusicPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class GameStateManagerTest {
    
    GameStateManager testattava;
    
    public GameStateManagerTest() {
        GlobalConstants.setUp();
        MusicPlayer.mute();
        testattava = new GameStateManager();
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttaKonstruktoriPelaa() {
        assertEquals(0, testattava.getCurrentStateNumber());
        assertNotNull(testattava.getCurrentGameState());
    }
    
    @Test
    public void toimiikoSetteriOikeallaSyotteella(){
        testattava.setState(1);
        assertEquals(1, testattava.getCurrentStateNumber());
        testattava.setState(0);
        assertEquals(0, testattava.getCurrentStateNumber());
    }
    
    @Test
    public void setteriEiTeeMitaanVaarillaSyotteilla(){
        testattava.setState(320);
        assertEquals(0, testattava.getCurrentStateNumber());
        testattava.setState(1);
        testattava.setState(4000);
        assertEquals(1, testattava.getCurrentStateNumber());
    }
    
    
}
