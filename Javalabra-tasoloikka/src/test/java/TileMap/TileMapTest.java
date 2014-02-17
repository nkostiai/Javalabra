/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TileMap;

import Global.GlobalConstants;
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
public class TileMapTest {
    TileMap testTileMap;
    
    
    public TileMapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        GlobalConstants.setUp();
        testTileMap = new TileMap(32);
        testTileMap.loadMap("/Maps/level1map.map");
        testTileMap.loadTiles("/Tilesets/tileset.png");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttaKonstruktoriAsettaaTileSizenOikein() {
        assertEquals(32, testTileMap.getTileSize());
        
    }
    
    @Test
    public void testaaEttaKonstruktoriAsettaaPiirtoDimensiotOikein(){
        assertEquals(24, testTileMap.getColumnsToDraw());
        assertEquals(19, testTileMap.getRowsToDraw());
    }
    
    @Test
    public void testaaEttaLoadTilesToimiiOikein(){
        assertNotNull(testTileMap.getTileset());
    }
    @Test
    public void testaaEttaLoadTilesAsettaaOikeanMaaranSarakkeita(){
        assertEquals(10, testTileMap.getNumberOfColumnsInTileSet());
    }
    
    @Test
    public void testaaEttaLoadMapAsettaaDimensiotOikein(){
        assertEquals(1280, testTileMap.getWidth());
        assertEquals(864, testTileMap.getHeight());
        
    }

    
}
