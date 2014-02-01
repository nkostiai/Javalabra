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
        tilemap = new TileMap(32);
        tilemap.loadMap("/Maps/level1map.map");
        tilemap.loadTiles("/Tilesets/tileset.png");
        testPlayer = new Player(tilemap);
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
    public void testaaEttaKonstruktoriAsettaaOikeatMaksimit(){
        assertEquals(5, testPlayer.getMaxHealth());
        assertEquals(2500, testPlayer.getMana());
    }
    

    
    @Test
    public void testaaJumping(){
        assertFalse(testPlayer.jumping);
        testPlayer.setJumping(true);
        assertTrue(testPlayer.jumping);
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
    
    @Test
    public void testaaCalculateCollisionWhenNoSolidBlocksAround(){
        testPlayer.calculateCollision(100, 100);
        assertFalse(testPlayer.bottomLeft);
        assertFalse(testPlayer.bottomRight);
        assertFalse(testPlayer.bottomMiddle);
        assertFalse(testPlayer.topLeft);
        assertFalse(testPlayer.topRight);
        assertFalse(testPlayer.topMiddle);
        assertFalse(testPlayer.leftMiddle);
        assertFalse(testPlayer.rightMiddle);
        
    }
    
    public void testaaCalculateCollisionWhenSurroundedBySolidBlocks(){
        testPlayer.calculateCollision(384, 96);
        assertTrue(testPlayer.bottomLeft);
        assertTrue(testPlayer.bottomRight);
        assertTrue(testPlayer.bottomMiddle);
        assertTrue(testPlayer.topLeft);
        assertTrue(testPlayer.topRight);
        assertTrue(testPlayer.topMiddle);
        assertTrue(testPlayer.leftMiddle);
        assertTrue(testPlayer.rightMiddle);
    }
    
    @Test
    public void testaaSetVector(){
        testPlayer.setVector(50, 50);
        testPlayer.setPosition(100, 100);
        testPlayer.update();
        assertEquals(149, testPlayer.getX());
        assertEquals(150, testPlayer.getY());
    }
    @Test
    public void testaaSetAnimation(){
        testPlayer.setAnimation(2, 400, 32);
        assertEquals(400, testPlayer.animation.getDelay());
        assertEquals(33, testPlayer.animation.getImage().getWidth());
    }
    @Test
    public void testaaUpdateLaittaaOikeinPutoamisen(){
        testPlayer.setPosition(100, 100);
        testPlayer.setVector(0, 5.0);
        testPlayer.update();
        assertTrue(testPlayer.falling);
        assertEquals(3, testPlayer.getAnimation());
    }
    @Test
    public void testaaUpdateLaittaaOikeinHyppaamisen(){
        testPlayer.setPosition(100, 100);
        testPlayer.setVector(0, -5.0);
        testPlayer.setJumping(true);
        testPlayer.update();
        
        assertTrue(testPlayer.jumping);
        assertEquals(2, testPlayer.getAnimation());
    }
    @Test
    public void testaaUpdateLaittaaKavelemisenOikein(){
        
        testPlayer.setRight(true);
        testPlayer.setVector(50, 0);
        testPlayer.update();
        assertEquals(1, testPlayer.getAnimation());
    }
    @Test
    public void testaaUpdateLaittaaKavelemisenVasemmalleOikein(){

        testPlayer.setLeft(true);
        testPlayer.setVector(-50, 0);
        testPlayer.update();
        assertEquals(1, testPlayer.getAnimation());
    
    }
    
    @Test
    public void testaaHyppayksenLoppua(){
        testPlayer.setPosition(320, 320);
        testPlayer.setJumping(true);
        testPlayer.setVector(0, -15);
        for(int i = 0; i < 10; i++){
            testPlayer.update();
        }
        assertTrue(testPlayer.dy >-1);
    }
}
