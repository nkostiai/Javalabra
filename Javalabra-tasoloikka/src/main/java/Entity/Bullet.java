package Entity;

import Global.GlobalConstants;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
/**
*
* @author nkostiai
*
* MapObject -luokan perivä luokka, joka kuvaa pelissä ruudulla lentäviä ammuksia.
* Ammukset voivat lentää 8-suuntaisesti vaihtelevalla nopeudella. Ammukset eivät myös lennä
* kiinteiden kohteiden läpi, vaan katoavat osuessaan niihin.
*
*/
public final class Bullet extends MapObject {

    private boolean hasHitSomething;
    private boolean shouldBeRemoved;

    public Bullet(TileMap tm, Direction direction) {
        //set tilemap
        super(tm);
        
        //set sprites
        sprites = GlobalConstants.graphicsLoader.getSprites("bullet");
        
        //set orientation
        facesRight = true;
        
        //set vector
        setMovingVector(direction);

        //set dimensions
        setDimensions();

        //set animation
        setAnimation();
    }
    
    public void setMovingVector(Direction direction){
        physicsAttributes.setMovingSpeed(7);
        dx = direction.getdx() * physicsAttributes.getMovingSpeed();
        dy = direction.getdy() * physicsAttributes.getMovingSpeed();
    }
    
    public void setDimensions(){
        width = 15;
        height = 10;
        collisionData.setCollisionWidth(10);
        collisionData.setCollisionHeight(10);
    }
    
    private void setAnimation(){
        animation = new Animation();
        animation.setFrames(sprites.get(0));
        animation.setDelay(400);
    }

    public void setHasHitSomething() {
        if (hasHitSomething) {
            return;
        }
        hasHitSomething = true;
        dx = 0;
        dy = 0;
    }

    public boolean shouldBeRemoved() {
        return shouldBeRemoved;
    }
    
    public boolean hasHitSomething(){
        return hasHitSomething;
    }

    public void update() {

        checkTileMapCollision();
        
        setPosition(collisionData.getxTemporary(), collisionData.getyTemporary());
        
        if (hasHitSomething) {
            shouldBeRemoved = true;
        }

        if (dx == 0 && dy == 0 && !hasHitSomething) {
            setHasHitSomething();
        }
        
        animation.update();
        
    }
    
    

}
