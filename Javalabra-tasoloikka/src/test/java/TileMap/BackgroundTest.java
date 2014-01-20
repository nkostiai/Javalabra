
package TileMap;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BackgroundTest {
    
    Background testBackground;
    String testiKuva = "/Test/testpicture.png";
    
    @Before
    public void setUp(){
        testBackground = new Background(testiKuva, 10);
        testBackground.setVector(10, 10);
    }

    @Test
    public void konstruktoriAsettaaKuvanOikein() {
        assertNotNull(testBackground.getImage());
    }
    
    @Test
    public void konstruktoriAsettaaKuvanMitatOikein(){
        assertEquals(32, testBackground.getHeight());
        assertEquals(32, testBackground.getWidth());
    }
    
    @Test
    public void koordinaatitAlkavatOikein(){
        assertEquals(0,(int) testBackground.getX());
        assertEquals(0,(int) testBackground.getY());
        
    }
    
    @Test
    public void updateMuuttaaKoordinaattejaOikein(){
        testBackground.update();
        assertEquals(10,(int) testBackground.getX());
        assertEquals(10,(int) testBackground.getY());
    }


    
}
