

package Entity;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    
    private long startTime;
    private long delay;
    private boolean playedOnce;
    
    public void Animation(){
        playedOnce = false;
        
    }
    
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
        
        if(delay == -1){
            return;
        }
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