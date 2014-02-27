/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Global.GlobalConstants;
import TileMap.TileMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class BulletTest {
    Bullet testbullet;
    TileMap tilemap;
    public BulletTest() {
    }
    
    @Before
    public void setUp() {
        GlobalConstants.setUp();
        tilemap = new TileMap(32);
        tilemap.loadMap("/Maps/level1map.map");
        tilemap.loadTiles("/Tilesets/tileset.png");
 
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaKonstruktori(){
        testbullet = new Bullet(tilemap, Direction.RIGHT);
        assertEquals(15, testbullet.width, 0);
        assertEquals(10, testbullet.height, 0);
        assertEquals(0, testbullet.dy, 0);
        assertEquals(7, testbullet.dx, 0);
        assertEquals(10, testbullet.collisionData.getCollisionHeight());
        assertEquals(10, testbullet.collisionData.getCollisionWidth());
    }
    
    @Test
    public void testaaVektorinAsetus(){
        testbullet = new Bullet(tilemap, Direction.LEFT);
        testbullet.setMovingVector(Direction.TOPRIGHT);
        assertEquals(7*0.71, testbullet.dx, 0);
        assertEquals(-7*0.71, testbullet.dy, 0);
    }
    
    @Test
    public void testaaShouldBeRemoved(){
        testbullet = new Bullet(tilemap, Direction.BOTTOMLEFT);
        assertFalse(testbullet.shouldBeRemoved());
    }
    @Test
    public void testaahasHitSomethingKunEiOsuMihinkaan(){
        testbullet = new Bullet(tilemap, Direction.LEFT);
        testbullet.setPosition(200, 200);
        testbullet.update();
        assertFalse(testbullet.hasHitSomething());
        
    }
    @Test
    public void testaahasHitSomethingKunPitaisiOsua(){
        testbullet = new Bullet(tilemap, Direction.LEFT);
        testbullet.setPosition(32, 32);
        testbullet.update();
        assertTrue(testbullet.hasHitSomething());
        
    }

    @Test
    public void testaaUpdatenvaikutus() {
        testbullet = new Bullet(tilemap, Direction.LEFT);
        testbullet.update();
        testbullet.setMovingVector(Direction.NULL);
        testbullet.update();
        assertTrue(testbullet.shouldBeRemoved());
    }
    
    @Test
    public void testaaEttaAnimaationDelayOnOikein(){
        testbullet = new Bullet(tilemap, Direction.LEFT);
        assertEquals(400, testbullet.animation.getDelay());
    }
}
