

package Entity.Enemies;

import Entity.Animation;
import Entity.Enemy;
import Entity.Properties.LivingEntityAttributes;
import Entity.Properties.PhysicsAttributes;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
*
* @author nkostiai
*
* Enemy -luokan yhtä vihollistyyppiä kuvaava luokka.
* Luokka lataa viholliskohtaisen sprite -grafiikan ja määrittää viholliskohtaiset attribuutit joka instanssille.
*
*/
public class Enemy1 extends Enemy{
    
    private BufferedImage[] sprites;
    
    public Enemy1(TileMap tm) {
        super(tm);
        
        initializeAttributes();
        
        try{
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/enemy.gif"));
            setSpritesheet(spritesheet);
        }
        catch(Exception e){
            
        }
        initializeAnimation();
    }
    
    private void initializeAttributes(){
        
        setPhysicsAttributes();
        
        setDimensions();
        
        setLivingAttributes();
        
        damage = 1;
    }
    
    private void setSpritesheet(BufferedImage spritesheet){
        sprites = new BufferedImage[spritesheet.getWidth()/width];
            for(int i = 0; i < sprites.length; i++){
                sprites[i] = spritesheet.getSubimage(i*width, 0, width, height);
            }
    }
    
    private void initializeAnimation(){
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(50);
        
        right = true;
    }
    
    private void setDimensions(){
        width = 40;
        height = 40;
        collisionData.setCollisionWidth(37);
        collisionData.setCollisionHeight(39);
        
    }
    
    private void setPhysicsAttributes(){
        physicsAttributes = new PhysicsAttributes();
        physicsAttributes.setMovingSpeed(0.6);
        physicsAttributes.setMaximumSpeed(0.7);
        physicsAttributes.setFallingSpeed(0.5);
        physicsAttributes.setMaximumFallingSpeed(20.0);
    }
    
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
    
    private void checkFlinching(){
        if(livingAttributes.getIsFliching()){
            long elapsed = (System.nanoTime() - livingAttributes.getFlinchTime()) / 1000000;
            if(elapsed > 400){
                livingAttributes.setFlinching(false);
            }
        }
    }
    
    @Override
    public void draw(Graphics2D g){
        
        setMapPosition();
        
        super.draw(g);
    }

    
    public BufferedImage[] getSprites(){
        return this.sprites;
    }
}