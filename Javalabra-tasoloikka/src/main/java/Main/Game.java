package Main;

import javax.swing.JFrame;

/**
 * Tässä luokassa pidetään oikeastaan vaan main-metodia, jonka hommana on käynnistää koko hommma.
 *  
 */
public class Game 
{
    public static void main( String[] args )
    {
       JFrame window = new JFrame("Tasohyppely");
       window.setContentPane(new GamePanel());
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       window.setResizable(false);
       window.pack();
       window.setVisible(true);
    }
}
