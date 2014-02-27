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
    public void testaaEttaKonstruktoriAsettaaPiirtoDimensiotOikein() {
        assertEquals(24, testTileMap.getColumnsToDraw());
        assertEquals(19, testTileMap.getRowsToDraw());
    }

    @Test
    public void testaaEttaLoadTilesToimiiOikein() {
        assertNotNull(testTileMap.getTileset());
    }

    @Test
    public void testaaEttaLoadTilesAsettaaOikeanMaaranSarakkeita() {
        assertEquals(10, testTileMap.getNumberOfColumnsInTileSet());
    }

    @Test
    public void testaaEttaLoadMapAsettaaDimensiotOikein() {
        assertEquals(1280, testTileMap.getWidth());
        assertEquals(864, testTileMap.getHeight());

    }

    @Test
    public void testaagetX() {
        assertEquals(0, testTileMap.getx());
    }

    @Test
    public void testaaGetY() {
        assertEquals(0, testTileMap.gety());
    }

    @Test
    public void testaagetRowOffset() {
        assertEquals(0, testTileMap.getrowOffset());
    }

    @Test
    public void testaagetcolumnOffset() {
        assertEquals(0, testTileMap.getcolumnOffset());
    }

    @Test
    public void testaagetNumberOfRows() {
        assertEquals(27, testTileMap.getNumberOfRows());
    }
    @Test
    public void testaaGetNumberOfColumns(){
        assertEquals(40, testTileMap.getNumberOfColumns());
    }
    
    @Test
    public void testaagetmap(){
        assertNotNull(testTileMap.getMap());
    }
    
    @Test
    public void testaaNumberOfTilesPerRow(){
        assertEquals(10, testTileMap.getNumberOfTilesPerRow());
    }
    
    @Test
    public void testaaTilet(){
        assertNotNull(testTileMap.getTiles());
    }
    
    @Test
    public void testaaFixBounds(){
        testTileMap.setPosition(5000, 5000);
        assertEquals(0, testTileMap.getx());
        assertEquals(0, testTileMap.gety());
        testTileMap.setPosition(-5000, -5000);
        assertEquals(-640, testTileMap.getx());
        assertEquals(-384, testTileMap.gety());
        testTileMap.setPosition(0, 0);
        assertEquals(0, testTileMap.getx());
        assertEquals(0, testTileMap.gety());
        testTileMap.setPosition(-640, -385);
        assertEquals(-640, testTileMap.getx());
        assertEquals(-384, testTileMap.gety());
        
    }
}
