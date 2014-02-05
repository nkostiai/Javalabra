

package Main;

import GameState.GameStateManager;
import Global.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.event.*;



public class GamePanel extends JPanel implements Runnable, KeyListener{
    
    //thread variables
    private Thread thread;
    private boolean running;
    private long targetTime = 1000 / GlobalConstants.FPS;
    
    //image variables
    private BufferedImage image;
    private Graphics2D g;
    
    //game state manager
    private GameStateManager gsm;
    
    
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
                
        //set running
        running = true;
        
        //create a new game state manager
        gsm = new GameStateManager();
        
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
            catch(Exception e){
                
            }
        }
        
        
    }

    private void update(){
        
        gsm.update();
        KeyboardController.update();
        
        
    }
    
    private void draw(){
        
        gsm.draw(g);
        
    }
    
    //final drawing to screen
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