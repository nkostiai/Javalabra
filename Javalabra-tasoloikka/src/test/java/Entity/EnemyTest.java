/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Entity.Enemies.Enemy1;
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
public class EnemyTest {
    Enemy testEnemy;
    TileMap tileMap;
    public EnemyTest() {
    }
    
    @Before
    public void setUp() {
        GlobalConstants.setUp();
        tileMap = new TileMap(32);
        testEnemy = new Enemy1(tileMap);
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testaaGetHitKahdellaDamagella(){
        testEnemy.getsHit(2);
        assertEquals(0, testEnemy.getAttributes().getHP());
        assertTrue(testEnemy.isDead());
        assertFalse(testEnemy.getAttributes().getIsFliching());
    }
    
    @Test
    public void testaaGetsHitKunFlinchaa(){
        testEnemy.getAttributes().setFlinching(true);
        testEnemy.getsHit(5);
        assertEquals(2, testEnemy.getAttributes().getHP());
        assertFalse(testEnemy.isDead());
    }
    
    @Test
    public void testaaGetHitYhdellaDamagella(){
        testEnemy.getsHit(1);
        assertEquals(1, testEnemy.getAttributes().getHP());
        assertTrue(testEnemy.getAttributes().getIsFliching());
    }
    
    
    @Test
    public void testaaEttaKonstruktoriLuoAttribuutit(){
        assertNotNull(testEnemy.getAttributes());
    }
    
    @Test
    public void testaaEtteiOleKuollutLuodessa(){
        assertFalse(testEnemy.isDead());
    }
    
    @Test
    public void testaaDamage() {
        assertEquals(1, testEnemy.getDamage());
    }
    
}
