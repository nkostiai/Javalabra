/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Enemies;

import TileMap.TileMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class Enemy1Test {
    private TileMap tileMap;
    private Enemy1 testEnemy;
    
    
    public Enemy1Test() {
    }
    
    @Before
    public void setUp() {
        tileMap = new TileMap(32);
        testEnemy = new Enemy1(tileMap);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaSpriteSheetinKoko(){
        assertEquals(4, testEnemy.getSprites().length);
    }
    
    @Test
    public void testaaCollisionDatanInitialisaatio(){
        assertEquals(37, testEnemy.getCollisionDate().getCollisionWidth());
        assertEquals(39, testEnemy.getCollisionDate().getCollisionHeight());
    }
    
    @Test
    public void testaaHP(){
        assertEquals(2, testEnemy.getAttributes().getHP());
        assertEquals(2, testEnemy.getAttributes().getMaxHP());
        
    }
    
    @Test
    public void testaaAnimationDelay(){
        assertEquals(50, testEnemy.getAnimation().getDelay());
    }
    
    @Test
    public void testaaSetMapPosition(){
        testEnemy.setMapPosition();
        assertEquals(tileMap.getx(), testEnemy.getTileVariables().getXMapPosition(), 0);
        assertEquals(tileMap.gety(), testEnemy.getTileVariables().getYMapPosition(), 0);
        
    }
    
    @Test
    public void testaaEttaKonstruktoriAsettaaSpritet(){
        assertNotNull(testEnemy.getSprites());
    }

    @Test
    public void testaaFysiikkaAttribuutinGenerointi() {
        assertNotNull(testEnemy.getPhysicsAttributes());
        
    }
    
    @Test
    public void testaaFysiikkaAttribuutteja(){
        assertEquals(0.6, testEnemy.getPhysicsAttributes().getMovingSpeed(), 0);
        assertEquals(0.7, testEnemy.getPhysicsAttributes().getMaximumSpeed(), 0);
        assertEquals(0.5, testEnemy.getPhysicsAttributes().getFallingSpeed(), 0);
        assertEquals(20.0, testEnemy.getPhysicsAttributes().getMaximumFallingSpeed(), 0);
        
    }
    
    @Test
    public void testaaFlinching(){
        
    }
   
    
}
