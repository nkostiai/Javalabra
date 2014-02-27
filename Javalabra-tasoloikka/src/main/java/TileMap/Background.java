
package TileMap;

import Global.GlobalConstants;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
*
* @author nkostiai
*
*Background -luokka kuvaa taustakuvaa, joka piirretään gamestateissa ekana. Taustakuvalle voi antaa erilaisia ominaisuuksia,
* kuten liikkeen tai parallax -tyyppisen skrollauksen.
*
*/
public class Background {
    
    /**
     * Taustakuvan kuva.
     */
    private BufferedImage image;
   
    
    /**
     * Taustakuvan X-koordinaatti.
     */
    private double x;
    
    /**
     * Taustakuvan Y-koordinaatti.
     */
    private double y;
    
    /**
     * Taustakuvan X-suuntainen liikkumisnopeus.
     */
    private double xMovementSpeed;
    
    /**
     * Taustakuvan Y-suuntainen liikkumisnopeus.
     */
    private double yMovementSpeed;
    
    /**
     * Skaala, jolla saadaan aikaan parallax scrolling -efekti.
     */
    private final double moveScale;
    
    /**
     * Taustakuvan leveys.
     */
    private int width;
    
    /**
     * Taustakuvan korkeus.
     */
    private int height;
    
    public Background(String pathToImage, double ms){
        
        //set coordinates
        this.x = 0;
        this.y = 0;
        
        //load image
        try{
            image = ImageIO.read(getClass().getResourceAsStream(pathToImage));
            width = image.getWidth();
            height = image.getHeight();
        }
        catch(IOException e){
            GlobalConstants.error("Error while loading background image.");
        }
        
        //set movescale
        moveScale = ms;
        
    }
    
    /**
     * Aseta taustakuvan koordinaatit.
     * @param x X-koordinaatti.
     * @param y Y-koordinaatti.
     */
    public void setPosition(double x, double y){
        this.x = (x*moveScale) % GlobalConstants.WINDOWWIDTH;
        this.y = (y*moveScale) % GlobalConstants.WINDOWHEIGHT;
    }
    
    /**
     * Aseta taustakuvan liikevektori.
     * 
     * @param dx Liikkeen nopeus X-suunnassa.
     * @param dy Liikkeen nopeus Y-suunnassa.
     */
    public void setVector(double dx, double dy){
        this.xMovementSpeed = dx;
        this.yMovementSpeed = dy;
    }
    
    /**
     * Päivitä taustakuva.
     */
    public void update() {
         moveVertically();
         moveHorizontally(); 
    }
    
    /**
     * Liikuta taustakuvaa pystysuunnassa.
     */
    private void moveVertically(){
        x += xMovementSpeed;
        while (x <= -width) {
            x += width;
        }
        while (x >= width) {
            x -= width;
        }
    }
    
    /**
     * Liikuta taustakuvaa vaakasuunnassa.
     */
    private void moveHorizontally(){
        y += yMovementSpeed;
        while (y <= -height) {
            y += height;
        }
        while (y >= height) {
            y -= height;
        }
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    
    public double getXMovementSpeed(){
        return xMovementSpeed;
    }
    
    public double getYMovementSpeed(){
        return yMovementSpeed;
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
}
