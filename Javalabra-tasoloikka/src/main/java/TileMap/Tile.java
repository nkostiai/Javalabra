

package TileMap;

import java.awt.image.BufferedImage;

/**
*
* @author nkostiai
*
* Tile -luokan instanssi kuvaa yhtä 32x32 kokoista osaa peliruudulta. Jokaisella Tilellä on jokin kuva, joka kyseisen tilen koordinaatteihin
* piiretään. Jokaisella Tilellä on myös tyyppi, joka kertoo tuleeko pelaajan törmätä kyseiseen tileen vai ei.
*
*
*/
public class Tile {
    
    /**
     * Tilen piirrettävä kuva.
     */
    private final BufferedImage image;
    
    /**
     * Tiilin tyyppi.
     */
    private final int type;
    
    /**
     * Ei-kiinteiden tiilien tyyppinumero.
     */
    public static final int NONSOLID = 0;
    
    /**
     * Kiinteiden tiilien tyyppinumero.
     */
    public static final int SOLID = 1;
    
    
    public Tile(BufferedImage image, int type){
        this.image = image;
        if(type > 1 || type < 0){
            this.type = 0;
        }
        else{
        this.type = type;
        }
        
    }
    
    public BufferedImage getImage(){
        return this.image;
    }
    
    public int getType(){
        return this.type;
    }
}