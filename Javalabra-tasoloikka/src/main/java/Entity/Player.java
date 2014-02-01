package Entity;

import TileMap.TileMap;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

    private int HP;
    private int maximumHP;
    private int MP;
    private int maximumMP;   
    private boolean gliding;
    

    private ArrayList<BufferedImage[]> sprites;
    private int amountOfActions;
    private final int[] numFrames = {
        2, 8, 1, 2, 4, 2, 5
    };

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;

    public Player(TileMap tm) {
        super(tm);

        width = 33;
        height = 65;
        collisionWidth = 30;
        collisionHeight = 50;

        movingSpeed = 1.0;
        maximumSpeed = 2.3;
        deceleration = 0.8;
        fallingSpeed = 0.70;
        maximumFallingSpeed = 5.0;
        jumpHeight = -12;
        stopJumpingSpeed = 0.5;

        facesRight = true;
        HP = maximumHP = 5;
        MP = maximumMP = 2500;


        //load sprites
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/playertileset.gif"));
            sprites = new ArrayList<BufferedImage[]>();
            amountOfActions = spritesheet.getHeight()/(height-1);
            for (int i = 0; i < amountOfActions; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {
                    
                        bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
                    
                }
                sprites.add(bi);
            }

        } catch (Exception e) {

        }
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);

    }

    public ArrayList<BufferedImage[]> getSprites(){
    return sprites;
}
    public int getAmountOfActions(){
        return amountOfActions;
    }
    
    
    public int getHealth(){
        return HP;
    }

    public int getMaxHealth(){
        return maximumHP;
    }
    
    public int getMana(){
        return MP;
    }
    public int getMaximumMana(){
        return maximumMP;
    }
    

    public void setGliding(boolean b){
        gliding = b;
    }
    
	private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= movingSpeed;
			if(dx < -maximumSpeed) {
				dx = -maximumSpeed;
			}
		}
		else if(right) {
			dx += movingSpeed;
			if(dx > maximumSpeed) {
				dx = maximumSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= deceleration;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += deceleration;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
			
		// jumping
		if(jumping && !falling) {
			dy = jumpHeight;
			falling = true;	
		}
		
		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fallingSpeed * 0.1;
			else dy += fallingSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpingSpeed;
			
			if(dy > maximumFallingSpeed) dy = maximumFallingSpeed;
			
		}
		
	}
    
    public void update(){
        
        //update position
        getNextPosition();
        checkTileMapCollision();
        setPosition(xTemporary, yTemporary);
         
        if(dy > 0){
            if(gliding){
                setAnimation(GLIDING, 100, 30);
            }
            else{
                setAnimation(FALLING, 100, 30);
            }
        }
        else if(dy < 0){
            setAnimation(JUMPING, -1, 30);
        }
        else if(left || right){
            setAnimation(WALKING, 40, 30);
        }
        else{
            setAnimation(IDLE, 400, 30);
        }
        
        animation.update();
        //set direction
        
            if(right) facesRight = true;
            if(left) facesRight = false;
        
        
        }
        
    
    public void draw(Graphics2D g){
        
        setMapPosition();
        if(facesRight){
            g.drawImage(animation.getImage(), (int) (x + xMapPosition - width /2), (int)( y + yMapPosition - height /2), null);
        }
        else{
            g.drawImage(animation.getImage(), (int)(x + xMapPosition - width /2 + width+3), (int)(y + yMapPosition - height /2), -width-3, height, null);
        }
        
    }
    
    
    
    public void setAnimation(int action, int delay, int givenwidth){
        if(currentAction != action){
            currentAction = action;
            animation.setFrames(sprites.get(action));
            animation.setDelay(delay);
            width = givenwidth;
        }
    }
    
    public int getAnimation(){
        return currentAction;
    }
    
}
