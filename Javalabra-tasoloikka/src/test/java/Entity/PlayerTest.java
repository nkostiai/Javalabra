/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import TileMap.TileMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class PlayerTest {
    TileMap tilemap;
    Player testPlayer;
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        testPlayer = new Player(new TileMap(20));
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttaKonstruktoriLaittaaOikeitaArvoja() {
        assertEquals(5, testPlayer.getHealth());
        assertEquals(2500, testPlayer.getMana());
    }
    
    @Test
    public void testaaEttaKonstruktoriLaittaaOikeatKoordinaatit(){
        assertEquals(0, testPlayer.getX());
        assertEquals(0, testPlayer.getY());
    }
    
    @Test
    public void testaaEttaSetPositionToimii(){
        testPlayer.setPosition(100, 100);
        assertEquals(100, testPlayer.getX());
        assertEquals(100, testPlayer.getY());
    }
    
    @Test
    public void testaaEttaKonstruktoriLaittaaKuvanOikein(){
        assertNotNull(testPlayer.getSprites());
    }
    
    @Test
    public void testaaEttaKonstruktoriLaskeeKuvanPituudenOikein(){
        assertEquals(7, testPlayer.getAmountOfActions());
    }
    
//    @Test
//    public void testaaCalculateCollisionWhenNoSolidBlocksAround(){
//        testPlayer.calculateCollision(100, 100);
//        assertFalse(testPlayer.bottomLeft);
//        assertFalse(testPlayer.bottomRight);
//        assertFalse(testPlayer.bottomMiddle);
//        assertFalse(testPlayer.topLeft);
//        assertFalse(testPlayer.topRight);
//        assertFalse(testPlayer.topMiddle);
//        
//    }
    
}
