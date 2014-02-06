

package Entity;

import java.awt.image.BufferedImage;
/**
*
* @author nkostiai
*
* Luokan instanssi säilyttää aina yhden animaation framet ja 
* delayn. Luokka myös hoitaa nykyisen framen ylläpitämisen ja pitää
* kirjaa onko animaatio pyörähtänyt jo kertaalleen.
*
*
*/
public class Animation {
    //frames
    private BufferedImage[] frames;
    private int currentFrame;
    
    //delay
    private long startTime;
    private long delay;
    private boolean playedOnce;
    
    public Animation(){
        playedOnce = false;  
    }
    
    /**
*
* @author nkostiai
*
* Metodi asettaa parametrinä saadun kuvataulukon animaation frameiksi.
* 
* @Param frames BufferedImage -taulukko, joka sisältää nykyisen animaation framet.
*
*/
    public void setFrames(BufferedImage[] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }
    
    public void setDelay(long delay){
        if(delay >= -1){
            this.delay = delay;
        }
    }
    public void setFrame(int i){
        if(i < 0){
            currentFrame = 0;
        }
        else if(i >= frames.length){
            currentFrame = frames.length -1;
        }
        else{
            currentFrame = i;
        }
    }
    
    public void update(){
        
        if(delay != -1){
            updateCurrentFrame();
        }
    }
    
    public void updateCurrentFrame(){
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }
    }
    
    public int getFrame(){
        return currentFrame;
    }
    public BufferedImage getImage(){
        return frames[currentFrame];
    }
    
    public boolean hasPlayedOnce(){
        return playedOnce;
    }
    public long getDelay(){
        
        return delay;
    }
    
}