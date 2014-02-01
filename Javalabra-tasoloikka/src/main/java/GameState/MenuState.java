/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Global.C;
import Global.KeyboardController;
import TileMap.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Admin
 */
public class MenuState extends GameState {

    private Background bg;          
    
    private int currentChoice;
    private String[] options = {"Start", "Quit"};

    private Color titleColor;
    private Font titleFont;

    private Font regularFont;

    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/menubackground.PNG", 1);
        } catch (Exception e) {
            e.printStackTrace();
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
    public void init() {
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
        g.drawString("Tasohyppely", C.MIDDLEX - 170, C.MIDDLEY / 2);

        //draw menu options
        g.setFont(regularFont);

        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawString(options[i], C.MIDDLEX - 30, C.MIDDLEY + i * 30);
        }
    }

    @Override
    public void update() {
        bg.update();
        handleInput();
    }

    @Override
    public void handleInput() {
        if (KeyboardController.isPressed(KeyboardController.ENTER)) {
            select();
        }
        if (KeyboardController.isPressed(KeyboardController.UP)) {
            currentChoice--;
            if (currentChoice < 0) {
                currentChoice = options.length - 1;
            }
        }
        if (KeyboardController.isPressed(KeyboardController.DOWN)) {
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
