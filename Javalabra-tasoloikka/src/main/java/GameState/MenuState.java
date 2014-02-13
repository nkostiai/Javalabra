
package GameState;

import Global.Buttons;
import Global.GlobalConstants;
import Global.KeyboardController;
import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
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
        titleFont = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[45], Font.PLAIN, 60);
        regularFont = new Font(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()[24], Font.PLAIN, 30);
    }

    @Override
    public void draw(Graphics2D g) {

        //draw background
        bg.draw(g);

        //draw the title text
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Tasohyppely", GlobalConstants.MIDDLEX - 170, GlobalConstants.MIDDLEY / 2);

        //draw menu options
        g.setFont(regularFont);

        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawString(options[i], GlobalConstants.MIDDLEX - 30, GlobalConstants.MIDDLEY + i * 30);
        }
    }

    @Override
    public void update() {
        bg.update();
        handleInput();
    }

    @Override
    public void handleInput() {
        if (KeyboardController.isPressed(Buttons.ENTER.getIDNumber())) {
            select();
        }
        if (KeyboardController.isPressed(Buttons.UP.getIDNumber())) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = options.length - 1;
            }
        }
        if (KeyboardController.isPressed(Buttons.DOWN.getIDNumber())) {
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
    

}
