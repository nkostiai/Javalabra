
package GameState;

import Global.KeyConfig;
import Global.KeyboardController;
import Global.MusicPlayer;
import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
/**
*
* @author nkostiai
*
* Menustate kuvaa pelin aloitusmenua. Menussa pystyy aloittamaan uuden pelin
* tai vaihtoehtoisesti poistumaan pelistä kokonaan.
*
*
*/
public class MenuState extends GameState {
    
    private Background bg;          
    
    private int currentChoice;
    private final String[] options = {"Start", "Quit"};

    private Color titleColor;
    private Font titleFont;

    private Font regularFont;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;
        type = StateType.MENUSTATE;

        try {
            bg = new Background("/Backgrounds/menubackground.PNG", 1);
        } catch (Exception e) {
        }
        
        
        init();
    }

    public void select() {
        if(currentChoice == 0){
            this.gsm.setState(1);
        }
        
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    @Override
    public final void init() {
        bg.setVector(-0.5, 0);

        titleColor = Color.BLACK;
        titleFont = new Font("Impact", Font.PLAIN, 40);
        regularFont = new Font("Comic Sans MS", Font.PLAIN, 30);
        MusicPlayer.load("/Music/menumusic.mp3", "menu");
	MusicPlayer.loop("menu", 600, MusicPlayer.getFrames("menu") - 2200);
    }
    

    @Override
    public void update() {
        bg.update();
        handleInput();
    }

    @Override
    public void handleInput() {
        if (KeyboardController.isPressed(KeyConfig.ENTER.getIDNumber())) {
            select();
        }
        if (KeyboardController.isPressed(KeyConfig.UP.getIDNumber())) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = options.length - 1;
            }
        }
        if (KeyboardController.isPressed(KeyConfig.DOWN.getIDNumber())) {
            currentChoice++;
            if (currentChoice > options.length - 1) {
                currentChoice = 0;
            }
        }
    }
    
    public Background getBG(){
        return bg;
    }
    public int getCurrentChoice(){
        return currentChoice;
    }
    
    public Color getTitleColor(){
        return titleColor;
    }
    public Font getTitleFont(){
        return titleFont;
    }
    public Font getRegularFont(){
        return regularFont;
    }
    public String[] getOptions(){
        return options;
    }
}
