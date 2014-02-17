

package Entity.Properties;

import Entity.Player;
import Global.GlobalConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class HUD {
    private final Player player;
    private Font HUDFont;
    private Color HUDTextColor;
    private BufferedImage HUDGraphics;
    
    public HUD(Player player){
        this.player = player;
        initialize();
    }
    
    private void initialize(){
        HUDFont = new Font("Impact", Font.PLAIN, 15);
        HUDTextColor = Color.CYAN;
        loadHUDImage();
    }
    
    private void loadHUDImage(){
        try{    
            HUDGraphics = ImageIO.read(getClass().getResourceAsStream("/Sprites/HUD.png"));
        }
        catch(IOException e){
            GlobalConstants.error("An error occured while loading the HUD");
        }
    }
    
    public BufferedImage getImage(){
        return HUDGraphics;
    }
    public Color getColor(){
        return HUDTextColor;
    }
    public Font getFont(){
        return HUDFont;
    }
}