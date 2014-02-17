package Main;

import Global.GlobalConstants;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

/**
 * 
 * Main-metodin sisältävä luokka
 *  
 */
public class Game 
{
    public static void main( String[] args )
    {
       JFrame window = new JFrame(GlobalConstants.title);
       window.setContentPane(new GamePanel());
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.setResizable(false);
       window.pack();
       window.setVisible(true);
    }
}
