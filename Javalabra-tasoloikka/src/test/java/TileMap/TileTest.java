/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TileMap;

import Global.GlobalConstants;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
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
public class TileTest {
    BufferedImage testimage;
    Tile testTile;
    
    
    
    public TileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        GlobalConstants.setUp();
        testimage = ImageIO.read(getClass().getResourceAsStream("/Test/testpicture.png"));
        testTile = new Tile(testimage, Tile.NONSOLID);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaEttäKonstruktoriLaittaaKuvanOikein() {
        assertNotNull(testTile.getImage());
    }
    
    @Test
    public void testaaEttäKonstruktoriLaittaaTyypinOikein(){
        assertEquals(Tile.NONSOLID, testTile.getType());
    }
    
    @Test
    public void testaaEtteiKonstruktoriEiAnnaLaittaaTyyppejaJoitaEiOle(){
        Tile tiili = new Tile(testimage, 54);
        assertEquals(Tile.NONSOLID, tiili.getType());
        Tile tiili2 = new Tile(testimage, -45);
        assertEquals(Tile.NONSOLID, tiili.getType());
    }
    
}
