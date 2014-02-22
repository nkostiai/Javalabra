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
import Entity.Properties.*;
import Global.GlobalConstants;

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
        GlobalConstants.setUp();
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
        assertEquals(3000, testPlayer.getMana());
    }
    
    @Test
    public void testaaEttaKonstruktoriAsettaaOikeatMaksimit(){
        assertEquals(5, testPlayer.getMaxHealth());
        assertEquals(6000, testPlayer.getMaximumMana());
    }
    
    @Test
    public void testaaEttaKonstruktoriLaittaaDimensiotOikein(){
        assertEquals(32, testPlayer.getWidth());
        assertEquals(64, testPlayer.getHeight());
        assertEquals(20, testPlayer.getCollisionWidth());
        assertEquals(57, testPlayer.getCollisionHeight());
    }
    
    @Test
    public void testaaEtteiKaukanaOlevatMapObjektitOsuToisiinsa(){
        Player playerTwo = new Player(tilemap);
        playerTwo.setPosition(300, 300);
        assertFalse(playerTwo.intersects(testPlayer));
    }
    
    @Test public void testaaEttaPaallekkainOlevatOsuvatToisiinsa(){
        Player playerTwo = new Player(tilemap);
        playerTwo.setPosition(300, 300);
        testPlayer.setPosition(300, 300);
        assertTrue(playerTwo.intersects(testPlayer));
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
    public void testaaCalculateCollisionWhenNoSolidBlocksAround(){
        testPlayer.calculateCollision(100, 100);
        assertFalse(testPlayer.collisionData.getBottomLeft());
        assertFalse(testPlayer.collisionData.getBottomRight());
        assertFalse(testPlayer.collisionData.getBottomMiddle());
        assertFalse(testPlayer.collisionData.getTopLeft());
        assertFalse(testPlayer.collisionData.getTopRight());
        assertFalse(testPlayer.collisionData.getTopMiddle());
        assertFalse(testPlayer.collisionData.getLeftMiddle());
        assertFalse(testPlayer.collisionData.getRightMiddle());
        
    }
    
    public void testaaCalculateCollisionWhenSurroundedBySolidBlocks(){
        testPlayer.calculateCollision(384, 96);
        assertTrue(testPlayer.collisionData.getBottomLeft());
        assertTrue(testPlayer.collisionData.getBottomRight());
        assertTrue(testPlayer.collisionData.getBottomMiddle());
        assertTrue(testPlayer.collisionData.getTopLeft());
        assertTrue(testPlayer.collisionData.getTopRight());
        assertTrue(testPlayer.collisionData.getTopMiddle());
        assertTrue(testPlayer.collisionData.getLeftMiddle());
        assertTrue(testPlayer.collisionData.getRightMiddle());
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
        assertEquals(32, testPlayer.animation.getImage().getWidth());
    }
    @Test
    public void testaaUpdateLaittaaOikeinPutoamisen(){
        testPlayer.setPosition(100, 100);
        testPlayer.setVector(0, 5.0);
        testPlayer.update();
        assertTrue(testPlayer.falling);
        assertEquals(3, testPlayer.getAnimationAction());
    }
    @Test
    public void testaaUpdateLaittaaOikeinHyppaamisen(){
        testPlayer.setPosition(100, 100);
        testPlayer.setVector(0, -5.0);
        testPlayer.setJumping(true);
        testPlayer.update();
        
        assertTrue(testPlayer.jumping);
        assertEquals(2, testPlayer.getAnimationAction());
    }
    @Test
    public void testaaUpdateLaittaaKavelemisenOikein(){
        
        testPlayer.setRight(true);
        testPlayer.setVector(50, 0);
        testPlayer.update();
        assertEquals(1, testPlayer.getAnimationAction());
    }
    @Test
    public void testaaUpdateLaittaaKavelemisenVasemmalleOikein(){

        testPlayer.setLeft(true);
        testPlayer.setVector(-50, 0);
        testPlayer.update();
        assertEquals(1, testPlayer.getAnimationAction());
    
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
