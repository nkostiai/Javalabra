
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
    
    //window title
    public static final String title = "Tasoloikkapeli";
    
    //dimensions
    public static final int WINDOWWIDTH = 640;
    public static final int WINDOWHEIGHT = 480;
    
    //middlepoints
    public static final int MIDDLEY = WINDOWHEIGHT/2;
    public static final int MIDDLEX = WINDOWWIDTH/2;
    
    //frames per second
    public static final int FPS = 60;
    
    public static GraphicsLoader graphicsLoader;
    
    public static void setUp(){
        graphicsLoader = new GraphicsLoader();
    }
    
    public static void error(String text){
    JOptionPane.showMessageDialog(null, text,
                title, JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
    
    
    
}
