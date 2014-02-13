package Entity;

import Entity.Properties.LivingEntityAttributes;
import Entity.Properties.PhysicsAttributes;
import TileMap.TileMap;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author nkostiai
 * 
* MapObject -luokan perivä pelihahmoa käsittelevä luokka. Luokka perii kaikki
 * MapObject -luokan instanssimuuttujat, sekä yhteentörmäyksiin liittyvät
 * metodit, ja pitää sisällään yksinomaan pelaajalle liittyviä ominaisuuksia,
 * kuten liikkumisen ja hyökkäyksen sekä muita pelitoiminnallisuuksia.
 * 
*
 */
public final class Player extends MapObject {
    /**
     * Pelaajan elämäattribuutit
     */
    private LivingEntityAttributes livingAttributes;
    
    /**
     * Kertoo liitääkö pelaaja parhaillaan
     */
    private boolean gliding;
    
    /**
     * Kertoo laukooko pelaaja ammuksia parhaillaan
     */
    private boolean firing;

    /**
     * Pelaajan luotien vaatima MP
     */
    private int bulletCost;
    
    /**
     * Pelaajan luodit
     */
    private ArrayList<Bullet> bullets;
    
    /**
     * Pelaajan sprite -grafiikka
     */
    private ArrayList<BufferedImage[]> sprites;
    
    /**
     * Pelaajan erilaistent tilojen määrä
     */
    private int amountOfActions;
    
    /**
     * Pelaajan animaatioiden framejen lukumäärä
     */
    private final int[] numFrames = {
        2, 8, 1, 2, 4, 2, 5
    };

    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIRING = 5;

    public Player(TileMap tm) {
        super(tm);

        //set dimensions
        setDimensions();

        //initialize attributes
        initializePhysicsAttributes();
        initializeLivingEntityAttributes();

        //set player orientation
        facesRight = true;

        //set playerbulletdata
        bullets = new ArrayList<>();
        bulletCost = 400;

        //load sprites
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/playertileset.gif"));
            setSpriteImages(spritesheet);

        } catch (Exception e) {

        }

        //initialize animation
        initializeAnimation();

    }
    
    /**
     * Asettaa pelaajan dimensiot
     */
    private void setDimensions() {
        width = 33;
        height = 65;
        collisionData.setCollisionWidth(30);
        collisionData.setCollisionHeight(50);
    }
    
    /**
     * Initialisoi pelaajan fysiikkaan liittyvät attribuutit
     */
    private void initializePhysicsAttributes() {
        physicsAttributes.setMovingSpeed(1.2);
        physicsAttributes.setMaximumSpeed(3.3);
        physicsAttributes.setDeceleration(0.4);
        physicsAttributes.setFallingSpeed(0.60);
        physicsAttributes.setMaximumFallingSpeed(5.0);
        physicsAttributes.setInitialJumpSpeed(-20);
        physicsAttributes.setStopJumpingSpeed(0.5);
    }
    /**
     * Initialisoi pelaajan elämään ja manaan liittyvät attribuutit
     */
    private void initializeLivingEntityAttributes() {
        livingAttributes = new LivingEntityAttributes();
        livingAttributes.setHP(5);
        livingAttributes.setMP(2500);
        livingAttributes.setMaxHP(5);
        livingAttributes.setMaxMP(2500);
    }
    
    /**
     * Asettaa pelajaan spriten
     */
    private void setSpriteImages(BufferedImage spritesheet) {
        sprites = new ArrayList<>();
        amountOfActions = spritesheet.getHeight() / (height - 1);
        for (int i = 0; i < amountOfActions; i++) {
            BufferedImage[] bi = new BufferedImage[numFrames[i]];

            for (int j = 0; j < numFrames[i]; j++) {

                bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);

            }
            sprites.add(bi);
        }
    }
    
    /**
     * Initialisoi pelaajan animaation
     */
    private void initializeAnimation() {
        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);
    }

    public ArrayList<BufferedImage[]> getSprites() {
        return sprites;
    }

    public int getAmountOfActions() {
        return amountOfActions;
    }

    public int getHealth() {
        return livingAttributes.getHP();
    }

    public int getMaxHealth() {
        return livingAttributes.getMaxHP();
    }

    public int getMana() {
        return livingAttributes.getMP();
    }

    public int getMaximumMana() {
        return livingAttributes.getMaxMP();
    }

    public void setGliding(boolean b) {
        gliding = b;
    }

    public void setFiring() {
        firing = true;

    }

    @Override
    public void getNextPosition() {

        // movement
        super.getNextPosition();

        // jumping
        getNextJumpingPosition();

        // falling
        if (falling) {
            getNextFallingPosition();
        }

    }

    private void getNextFallingPosition() {
        
        setFallingSpeed();
        
        if (dy > 0) {
            jumping = false;
        }
        
        if (dy < 0 && !jumping) {
            dy += physicsAttributes.getStopJumpingSpeed();
        }

        if (dy > physicsAttributes.getMaximumFallingSpeed()) {
            dy = physicsAttributes.getMaximumFallingSpeed();
        }
        
    }
    
    private void setFallingSpeed(){
        if (dy > 0 && gliding) {
            dy += physicsAttributes.getFallingSpeed() * 0.1;
        } else {
            dy += physicsAttributes.getFallingSpeed();
        }
    }

    private void getNextJumpingPosition() {
        if (jumping && !falling) {
            dy = physicsAttributes.getInitialJumpSpeed();
            falling = true;
        }
    }

    public void update() {

        //update position
        getNextPosition();

        checkTileMapCollision();

        setPosition(collisionData.getxTemporary(), collisionData.getyTemporary());

        //check if we've played the firing animation once
        checkIfFiringAnimationHasPlayed();

        //regenerate mp
        regenerateMP();

        //check if we need to fire bullets
        updateBulletFiring();

        //update bullets fired by the player
        updatePlayerBullets();

        //update animations
        setCurrentActionAnimation();
        animation.update();
        //set direction
        setDirection();

    }

    public void checkIfFiringAnimationHasPlayed() {
        if (currentAction == FIRING) {
            if (animation.hasPlayedOnce()) {
                firing = false;
            }
        }
    }

    public void setDirection() {
        if (right) {
            facesRight = true;
        }
        if (left) {
            facesRight = false;
        }
    }

    public void regenerateMP() {
        livingAttributes.regenerateMP(1);
    }

    public void setCurrentActionAnimation() {
        if (firing) {
            setAnimation(FIRING, 50, 30);
        } else if (dy > 0) {
            checkDownwardsMovementAction();
        } else if (dy < 0) {
            setAnimation(JUMPING, -1, 30);
        } else if (left || right) {
            setAnimation(WALKING, 40, 30);
        } else {
            setAnimation(IDLE, 400, 30);
        }
    }

    public void checkDownwardsMovementAction() {
        if (gliding) {
            setAnimation(GLIDING, 100, 30);
        } else {
            setAnimation(FALLING, 100, 30);
        }
    }

    public void updateBulletFiring() {
        if (firing && currentAction != FIRING) {
            if (livingAttributes.getMP() > bulletCost) {
                livingAttributes.depleteMP(bulletCost);
                Bullet bullet;
                if (facesRight) {
                    bullet = new Bullet(tileVariables.getTileMap(), Direction.RIGHT);

                } else {
                    bullet = new Bullet(tileVariables.getTileMap(), Direction.LEFT);

                }
                bullet.setPosition(x, y);
                bullets.add(bullet);
            }
        }
    }

    public void updatePlayerBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            if (bullets.get(i).shouldBeRemoved()) {
                bullets.remove(i);
                i--;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {

        //set position of tilemap
        setMapPosition();
        //draw the player
        drawPlayer(g);
        //draw player's bullets
        drawBullets(g);
        //draw player stats

    }
    /**
     * Piirtää pelaajan
     */
    private void drawPlayer(Graphics2D g) {
        super.draw(g);
    }
    
    /**
     * Piirtää pelaajan luodit
     */
    private void drawBullets(Graphics2D g) {
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    public void setAnimation(int action, int delay, int givenwidth) {
        if (currentAction != action) {
            currentAction = action;
            animation.setFrames(sprites.get(action));
            animation.setDelay(delay);
            width = givenwidth;
        }
    }

    public int getAnimationAction() {
        return currentAction;
    }

    public LivingEntityAttributes getAttributes() {
        return this.livingAttributes;
    }

}
