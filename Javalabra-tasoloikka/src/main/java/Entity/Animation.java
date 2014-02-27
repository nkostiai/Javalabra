package Entity;

import java.awt.image.BufferedImage;

/**
 *
 * @author nkostiai
 * 
* Luokan instanssi säilyttää aina yhden animaation framet ja delayn. Luokka
 * myös hoitaa nykyisen framen ylläpitämisen ja pitää kirjaa onko animaatio
 * pyörähtänyt jo kertaalleen.
 * 
*
 */
public class Animation {

    /**
     * Animaation kaikki framet.
     */
    private BufferedImage[] frames;

    /**
     * Nykyinen frame.
     */
    private int currentFrame;

    /**
     * Milloin aloitettiin tämä animaatio.
     */
    private long startTime;

    /**
     * Kuinka kauan kutakin framea piirretään.
     */
    private long delay;

    /**
     * Onko tämä animaatio pyörähtänyt kerran.
     */
    private boolean playedOnce;

    public Animation() {
        playedOnce = false;
    }

    /**
     *
     * Metodi asettaa parametrinä saadun kuvataulukon animaation frameiksi.
     *
     * @param frames BufferedImage -taulukko, joka sisältää nykyisen animaation
     * framet.
     *
     */
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }
    
    /**
     * Aseta animaation delay.
     * 
     * @param delay Animaation framejen delay millisekunneissa.
     */
    public void setDelay(long delay) {
        if (delay > -1) {
            this.delay = delay;
        }
    }
    
    /**
     * Asettaa nykyisen framen.
     * 
     * @param i monesko frame laitetaan
     */
    public void setFrame(int i) {
        if (i < 0) {
            currentFrame = 0;
        } else if (i >= frames.length) {
            currentFrame = frames.length - 1;
        } else {
            currentFrame = i;
        }
    }
    
    /**
     * Päivittää animaation.
     */
    public void update() {
        if (delay != -1) {
            updateCurrentFrame();
        }
    }
    
    /**
     * Päivittää nykyisen framen.
     */
    public void updateCurrentFrame() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed >= delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public int getFrame() {
        return currentFrame;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

    public long getDelay() {

        return delay;
    }

}
