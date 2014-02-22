

package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import Entity.Properties.LivingEntityAttributes;
import Entity.Properties.PhysicsAttributes;
import Global.GlobalConstants;
import TileMap.TileMap;
import java.awt.image.BufferedImage;

/**
*
* @author nkostiai
*
* Enemy -luokan yhtä vihollistyyppiä kuvaava luokka.
* Luokka lataa viholliskohtaisen sprite -grafiikan ja määrittää viholliskohtaiset attribuutit joka instanssille.
*
*/
public class Enemy1 extends Enemy{
    
   
    
    public Enemy1(TileMap tm) {
        super(tm);
        
        initializeAttributes();
        
        sprites  = GlobalConstants.graphicsLoader.getSprites("enemy1");
        
        initializeAnimation();
    }
    
    /**
     * Alustaa relevantint attribuutit.
     */
    private void initializeAttributes(){
        
        setPhysicsAttributes();
        
        setDimensions();
        
        setLivingAttributes();
        
        damage = 1;
    }
    
    /**
     * Alustaa animaation.
     */
    private void initializeAnimation(){
        animation = new Animation();
        animation.setFrames(sprites.get(0));
        animation.setDelay(50);
        facesRight = true;
        right = true;
    }
    
    /**
     * Asettaa dimensiot.
     */
    private void setDimensions(){
        width = 40;
        height = 40;
        collisionData.setCollisionWidth(35);
        collisionData.setCollisionHeight(35);
        
    }
    
    /**
     * Asettaa fysiikkaan liittyvät attribuutit.
     */
    private void setPhysicsAttributes(){
        physicsAttributes = new PhysicsAttributes();
        physicsAttributes.setMovingSpeed(0.6);
        physicsAttributes.setMaximumSpeed(0.7);
        physicsAttributes.setFallingSpeed(0.3);
        physicsAttributes.setMaximumFallingSpeed(2.0);
    }
    
    /**
     * Asettaa elämäattribuutit.
     */
    private void setLivingAttributes(){
        livingAttributes = new LivingEntityAttributes();
        livingAttributes.setHP(2);
        livingAttributes.setMaxHP(2);
        damage = 1;
    }
    

    @Override
    public void getNextPosition(){
        
        super.getNextPosition();
        
        if(falling){
            dy += physicsAttributes.getFallingSpeed();
        }
    }
    
    @Override
    public void update(){
        
        getNextPosition();
        checkTileMapCollision();
        setPosition(collisionData.getxTemporary(), collisionData.getyTemporary());
        checkFlinching();
        
        if(right && dx == 0){
            right = false;
            left = true;
            facesRight = false;
        }
        else if(left && dx == 0){
            left = false;
            right = true;
            facesRight = true;
        }
        
        animation.update();        
    }
    
    /**
     * Tarkistaa välkkymisen.
     */
    private void checkFlinching(){
        if(livingAttributes.getIsFliching()){
            long elapsed = (System.nanoTime() - livingAttributes.getFlinchTime()) / 1000000;
            if(elapsed > 400){
                livingAttributes.setFlinching(false);
            }
        }
    }
    
    public BufferedImage[] getSprites(){
        return this.sprites.get(0);
    }
}