
package Global;

import Drawer.GraphicsLoader;
import javax.swing.JOptionPane;

/**
*
* @author nkostiai
* 
* GlobalConstants on globaali apuluokka, jossa säilytetään koko ohjelman läpi samana pysyviä muuttujia. 
* Tällaisia ovat esimerkiksi peliruudun koko ja FPS.
*
*
*/
public class GlobalConstants {
    
    /**
     * Peli-ikkunan otsikko.
     */
    public static final String title = "Tasoloikkapeli";
    
    /**
     * Peli-ikkunan leveys.
     */
    public static final int WINDOWWIDTH = 640;
    
    /**
     * Peli-ikkunan korkeus.
     */
    public static final int WINDOWHEIGHT = 480;
    
    /**
     * Puolet peli-ikkunan korkeudesta.
     */
    public static final int MIDDLEY = WINDOWHEIGHT/2;
    
    /**
     * Puolet peli-ikkunan leveydestä.
     */
    public static final int MIDDLEX = WINDOWWIDTH/2;
    
    /**
     * FPS johon tähdätään.
     */
    public static final int FPS = 60;
    
    /**
     * Grafiikoiden lataaja.
     */
    public static GraphicsLoader graphicsLoader;
    
    /**
     * Ladataan grafiikat pelin aluksi.
     */
    public static void setUp(){
        graphicsLoader = new GraphicsLoader();
    }
    
    /**
     * Metodi, jota kutsutaan virhetilanteen sattuessa.
     * Printtaa viestin ja keskeyttä ohjelman.
     * 
     * @param text Viesti joka näytetään käyttäjälle.
     */
    public static void error(String text){
    JOptionPane.showMessageDialog(null, text,
                title, JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
    
    
    
}
