

package Entity.Properties;

import Entity.Player;
import Global.GlobalConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class HUD {
    /**
     * Viite hudin kuvaamaan pelaajaan.
     */
    private final Player player;
    
    /**
     * HUDin käyttämä fontti.
     */
    private Font HUDFont;
    
    /**
     * HUDin käyttämä väri.
     */
    private Color HUDTextColor;
    
    /**
     * HUDin taustakuva.
     */
    private BufferedImage HUDGraphics;
    
    public HUD(Player player){
        this.player = player;
        initialize();
    }
    
    /**
     * Alusta HUD.
     */
    private void initialize(){
        HUDFont = new Font("Impact", Font.PLAIN, 15);
        HUDTextColor = Color.CYAN;
        loadHUDImage();
    }
    
    /**
     *  Lataa HUDin taustakuva.
     */
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