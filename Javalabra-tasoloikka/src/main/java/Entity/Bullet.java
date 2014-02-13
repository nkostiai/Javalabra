package Entity;

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
public class Bullet extends MapObject {

    private boolean hasHitSomething;
    private boolean shouldBeRemoved;
    private BufferedImage sprite;

    public Bullet(TileMap tm, Direction direction) {
        //set tilemap
        super(tm);
        
        facesRight = true;
        
        //set vector
        setMovingVector(direction);

        //set dimensions
        setDimensions();

        //load sprites
        loadBulletSprite();
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
    
    public void loadBulletSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Sprites/bullet1.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setAnimation();
    }
    
    private void setAnimation(){
        animation = new Animation();
        BufferedImage[] frames = new BufferedImage[1];
        frames[0] = sprite;
        animation.setFrames(frames);
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
    
    
    @Override
    public void draw(Graphics2D g) {
        
        //set the position
        setMapPosition();
        
        //draw the bullet
        super.draw(g);

    }

}
