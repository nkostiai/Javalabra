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

    private boolean dead;
    private boolean flinching;
    private long flinchTime;

    private boolean firing;
    private int firingCost;
    private int firingDamage;

    private boolean meleeAttacking;
    private int meleeDamage;
    private int meleeRange;
    
    private boolean gliding;

    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
        2, 8, 1, 2, 4, 2, 5
    };

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIRINGATTACK = 5;
    private static final int MELEEATTACK = 6;

    public Player(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        collisionWidth = 15;
        collisionHeight = 20;

        movingSpeed = 0.4;
        maximumSpeed = 1.7;
        deceleration = 0.6;
        fallingSpeed = 0.20;
        maximumFallingSpeed = 4.0;
        jumpHeight = -5.8;
        stopJumpingSpeed = 0.3;

        facesRight = true;
        HP = maximumHP = 5;
        MP = maximumMP = 2500;

        firingCost = 200;
        firingDamage = 5;

        meleeDamage = 8;
        meleeRange = 40;

        //load sprites
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/playersprites.gif"));
            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < 7; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != 6) {
                        bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
                    } else {
                        bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
                    }
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
    
    public void setFiring(){
        firing = true;
    }
    public void setMelee(){
        meleeAttacking = true;
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
		
		// cannot move while attacking, except in air
		if(
		(currentAction == MELEEATTACK || currentAction == FIRINGATTACK) &&
		!(jumping || falling)) {
			dx = 0;
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
        
        //set animation
        if(meleeAttacking){
            setAnimation(MELEEATTACK, 50, 60);
            }
        else if(firing){
            setAnimation(FIRINGATTACK, 100, 30);
        }
        else if(dy > 0){
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
        
        if(currentAction != MELEEATTACK && currentAction != FIRINGATTACK){
            if(right) facesRight = true;
            if(left) facesRight = false;
        }
        
        }
        
    
    public void draw(Graphics2D g){
        
        setMapPosition();
        if(flinching){
            long elapsed = System.nanoTime() - flinchTime / 1000000;
            if(elapsed / 100 % 2 == 0){
                return;
            }
        }
        if(facesRight){
            g.drawImage(animation.getImage(), (int) (x + xMapPosition -width /2), (int)( y + yMapPosition - height /2), null);
        }
        else{
            g.drawImage(animation.getImage(), (int)(x + xMapPosition - width /2 + width), (int)(y + yMapPosition - height /2), -width, height, null);
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
    
}
