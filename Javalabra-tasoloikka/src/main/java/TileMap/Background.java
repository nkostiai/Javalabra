
package TileMap;

import Global.GlobalConstants;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
    
    //the background image
    private BufferedImage image;
   
    
    //coordinates
    private double x;
    private double y;
    
    //movement speed
    private double xMovementSpeed;
    private double yMovementSpeed;
    
    private double moveScale;
    
    //image dimensions
    private int width;
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
        catch(Exception e){
            
        }
        
        //set movescale
        moveScale = ms;
        
    }
    
    public void setPosition(double x, double y){
        this.x = (x*moveScale) % GlobalConstants.WINDOWWIDTH;
        this.y = (y*moveScale) % GlobalConstants.WINDOWHEIGHT;
    }
    
    public void setVector(double dx, double dy){
        this.xMovementSpeed = dx;
        this.yMovementSpeed = dy;
    }
    
    public void update() {
         moveVertically();
         moveHorizontally(); 
    }
    
    private void moveVertically(){
        x += xMovementSpeed;
        while (x <= -width) {
            x += width;
        }
        while (x >= width) {
            x -= width;
        }
    }
    
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
