
package TileMap;

import Global.C;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Background {
    
    private BufferedImage image;
   
    private double x;
    private double y;
    private double dx;
    private double dy;
    
    private double moveScale;
    
    public Background(String s, double ms){
        
        try{
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        }
        catch(Exception e){
            
        }
        
    }
    
    public void setPosition(double x, double y){
        this.x = (x*moveScale) % C.WINDOWWIDTH;
        this.y = (y*moveScale) % C.WINDOWHEIGHT;
    }
    
    public void setVector(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    
	public void update() {
		x += dx;
		while(x <= -image.getWidth()) x += image.getWidth();
		while(x >= image.getWidth()) x -= image.getWidth();
		y += dy;
		while(y <= -image.getHeight()) y += image.getHeight();
		while(y >= image.getHeight()) y -= image.getHeight();
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
    
    
}
