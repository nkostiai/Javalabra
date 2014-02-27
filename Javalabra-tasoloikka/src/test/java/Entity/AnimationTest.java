/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import Global.GlobalConstants;
import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class AnimationTest {
    Animation testAnimation;
    BufferedImage[] kuvataulu = new BufferedImage[5];
    
    public AnimationTest() {
        
    }
    
    @Before
    public void setUp() {
        GlobalConstants.setUp();
        testAnimation = new Animation();
        testAnimation.setFrames(kuvataulu);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testaaLaittaakoKonstruktroiPlayedOncenOikein() {
        assertFalse(testAnimation.hasPlayedOnce());
    }
    
    @Test
    public void setFramesAsettaaOikeanFramen(){
        assertEquals(0, testAnimation.getFrame());
    }
    
    @Test
    public void setFramesAsettaaOikeanFramenKunMennaanVahanPieleen(){
        testAnimation.setFrame(-1);
        assertEquals(0, testAnimation.getFrame());
        testAnimation.setFrame(5);
        assertEquals(4, testAnimation.getFrame());
    }
    
    @Test
    public void setFrameEiAnnaMennaRajojenUlkopuolelle(){
        testAnimation.setFrame(56);
        assertEquals(4, testAnimation.getFrame());
        testAnimation.setFrame(-345);
        assertEquals(0, testAnimation.getFrame());
    }
    
    @Test
    public void setDelayAsettaaDelaynOikein(){
        testAnimation.setDelay(4000);
        assertEquals(4000, testAnimation.getDelay());
    }
    
    @Test
    public void setDelayEiAsetaMitaanJosTuleePienempiKuinMiinusYksi(){
        testAnimation.setDelay(-4000);
        assertEquals(0, testAnimation.getDelay());
    }
    
    @Test
    public void setDelayEiAsetaMitaanJosTuleeTasanMiinusYksi(){
        testAnimation.setDelay(-1);
        assertEquals(0, testAnimation.getDelay());
    }
    
    @Test
    public void setDelayAsettaaNollatOikein(){
        testAnimation.setDelay(30);
        assertEquals(30, testAnimation.getDelay());
        testAnimation.setDelay(0);
        assertEquals(0, testAnimation.getDelay());
    }
    
    @Test
    public void updateCurrentFrameToimiiJotenkin(){
       testAnimation.setDelay(0);
       testAnimation.updateCurrentFrame();
       assertEquals(1, testAnimation.getFrame());
       testAnimation.setFrame(4);
       testAnimation.updateCurrentFrame();
       assertEquals(0, testAnimation.getFrame());
       
    }
    

}
