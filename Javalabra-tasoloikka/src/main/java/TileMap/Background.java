
package TileMap;

import Global.C;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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
    public Background(String s, double ms){
        
        //set coordinates
        this.x = 0;
        this.y = 0;
        
        //load image
        try{
            image = ImageIO.read(getClass().getResourceAsStream(s));
            width = image.getWidth();
            height = image.getHeight();
        }
        catch(Exception e){
            
        }
        
        //set movescale
        moveScale = ms;
        
    }
    
    public void setPosition(double x, double y){
        this.x = (x*moveScale) % C.WINDOWWIDTH;
        this.y = (y*moveScale) % C.WINDOWHEIGHT;
    }
    
    public void setVector(double dx, double dy){
        this.xMovementSpeed = dx;
        this.yMovementSpeed = dy;
    }
    
    public void update() {
        x += xMovementSpeed;
        while (x <= -width) {
            x += width;
        }
        while (x >= width) {
            x -= width;
        }
        y += yMovementSpeed;
        while (y <= -height) {
            y += height;
        }
        while (y >= height) {
            y -= height;
        }
    }

    public void draw(Graphics2D g){
        
        g.drawImage(image, (int)x, (int)y, null);
        if(x < 0){
            g.drawImage(image, (int)x + image.getWidth() , (int) y, null);
        }
        if(x > 0){
            g.drawImage(image, (int)x - image.getWidth(), (int)y, null);
        }
        if(y < 0){
            g.drawImage(image, (int)x, (int)y + image.getHeight(), null);
        }
        if(y > 0){
            g.drawImage(image, (int)x, (int)y - image.getHeight(), null);
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
