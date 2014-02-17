package Drawer;

import Global.GlobalConstants;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class GraphicsLoader {
    private final int[] numFramesOfPlayer = {
        2, 8, 1, 2, 4, 2, 5
    };
    private final int[] numFramesOfEnemy1 = {
        4
    };
    private final int[] numFramesOfBullet = {
        1
    };
    private HashMap<String, ArrayList<BufferedImage[]>> sprites;
    

    public GraphicsLoader() {
        sprites = new HashMap<>();
        loadSprites();
    }

    private void loadSprites() {
        //load player sprites
        loadPlayerSprites();
        //load enemy1 sprites
        loadEnemy1Sprites();
        //load bullet sprites
        loadBulletSprites();
    }
    

    
    private void loadPlayerSprites(){
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/playertileset.gif"));
            sprites.put("player", setSpritesheet(spritesheet, 32, 64, numFramesOfPlayer));
        } catch (IOException e) {
            GlobalConstants.error("Failed to load player-sprites. Terminating.");
        }
    }
    
    private void loadEnemy1Sprites() {
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/enemy.gif"));
            sprites.put("enemy1", setSpritesheet(spritesheet, 40, 40, numFramesOfEnemy1));
        } catch (IOException e) {
            GlobalConstants.error("Failed to load enemy-sprites. Terminating.");
        }
    }
    
    private void loadBulletSprites(){
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/bullet1.gif"));
            sprites.put("bullet", setSpritesheet(spritesheet, 6, 6, numFramesOfBullet));
        } catch (IOException e) {
            GlobalConstants.error("Failed to load bullet-sprites. Terminating.");
        }
    }

    private ArrayList<BufferedImage[]> setSpritesheet(BufferedImage spritesheet, int width, int height, int[] numframes) {
        ArrayList<BufferedImage[]> playerSprites = new ArrayList<>();
        int amountOfActions = spritesheet.getHeight() / (height);
        for (int i = 0; i < amountOfActions; i++) {
            BufferedImage[] bi = new BufferedImage[numframes[i]];

            for (int j = 0; j < numframes[i]; j++) {

                bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);

            }
            playerSprites.add(bi);
        }
        return playerSprites;
    }
    
    public ArrayList<BufferedImage[]> getSprites(String name){
        return sprites.get(name);
    }
    

}
