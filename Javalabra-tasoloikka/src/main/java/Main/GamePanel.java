

package Main;

import Drawer.Drawer;
import GameState.GameStateManager;
import Global.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 * 
 * JPanel -luokan ja Runnablen perivä pääluokka, joka hoitaa varsinaisen peliloopin sekä lopullisen piirtämisen.
 *  
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    /**
     * Pelin threadi.
     */
    private Thread thread;
    
    /**
     * Onko peli käynnissä.
     */
    private boolean running;
    
    /**
     * Apumuuttuja FPS:n säilyttämiseksi.
     */
    private final long targetTime = 1000 / GlobalConstants.FPS;
    
    /**
     * Pelitilan kuva.
     */
    private BufferedImage image;
    
    /**
     * Pelin piirtävä Graphics2D -olio.
     */
    private Graphics2D g;
    
    /**
     * Pelin GameStateManager.
     */
    private GameStateManager gsm;
    
    /**
     * Pelin piirtäjä.
     */
    private Drawer drawer;
    
    public GamePanel(){
        
        super();
        setPreferredSize(new Dimension(GlobalConstants.WINDOWWIDTH, GlobalConstants.WINDOWHEIGHT));
        setFocusable(true);
        requestFocus();

    }
    
    //initialize thread
    @Override
    public void addNotify(){
        super.addNotify();
        if( thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
    
    private void init(){
        //set up drawing
        image = new BufferedImage(GlobalConstants.WINDOWWIDTH, GlobalConstants.WINDOWHEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        //loading screen
        drawLoadingScreen();
        
        //set running
        running = true;
        
         //create graphicsloader
        GlobalConstants.setUp();
        
        //create a new game state manager
        gsm = new GameStateManager();
        
         //create a new drawer
        drawer = new Drawer(g, gsm);

       
    }
    
    /**
     * Pelin aluksi piirretään latausruutu.
     */
    private void drawLoadingScreen(){
        try {
            g.drawImage(ImageIO.read(getClass().getResourceAsStream("/Backgrounds/loadingscreen.png")), 0, 0, null);
        } catch (IOException ex) {
            GlobalConstants.error("FATAL ERROR");
        }
        drawToScreen();
    }
    
    
    @Override
    public void run() {
        
        init();
        
        //timers
        long start;
        long elapsed;
        long wait;
        
        //main game loop
        while(running){
            //set start
            start = System.nanoTime();
            
            //main loop actions: update everything, draw everything and draw everything on screen
            update();
            draw();
            drawToScreen();
            
            //set elapsed and have thread sleep for the wait amount
            elapsed = System.nanoTime() - start;
            
            wait = targetTime - elapsed / 1000000;
            if(wait < 0){
                wait = 5;
            }
            
            try{
                Thread.sleep(wait);
            }
            catch(InterruptedException e){
                GlobalConstants.error("An unexpected error occured with thread. Terminating.");
            }
        }
        
        
    }
    
    /**
     * Päivitetään peli.
     */
    private void update(){
        
        gsm.update();
        KeyboardController.update();
        
        
    }
    
    /**
     * Piirretään peli.
     */
    private void draw(){
        
        drawer.draw();
        
    }
    
    /**
     * Piirretään lopullinen pelikuva ruudulle.
     */
    private void drawToScreen(){
        
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //tällä ei oikeastaan tehdä mitään
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyboardController.setKeyPressStatus(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyboardController.setKeyPressStatus(e.getKeyCode(), false);
    }
    
}