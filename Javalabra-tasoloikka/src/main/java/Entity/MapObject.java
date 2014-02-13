package Entity;

import Entity.Properties.*;
import Global.GlobalConstants;
import TileMap.Tile;
import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author nkostiai
 * 
* Toimii abstraktina superluokkana kaikille pelikartalla sijaitseville
 * objekteille. Objekteille kuuluu erilaisia ominaisuuksia, kuten sijainti,
 * liikkeen nopeus ja yhteentörmäystiedot.
 * 
*/
public abstract class MapObject {

    /**
     * Jokaisen mapobjektin tileMapiin liittyvät attribuutit
     */
    protected TileVariables tileVariables;

    /**
     * objektin x-koordinaatti kartalla
     */
    protected double x;
    
    /**
     * objektin y-koordinaatti kartalla
     */
    protected double y;
    
    /**
     * objektin x-suuntainen liikkumisnopeus
     */
    protected double dx;
    
    /**
     * objektin y-suuntainen liikkumisnopeus
     */
    protected double dy;

    /**
     * objektin piirtoleveys
     */
    protected int width;
    
    /**
     * objektin piirtokorkeus
     */
    protected int height;
   
    /**
     * objektin yhteentörmäyksiin liittyvät attribuutit
     */
    protected CollisionData collisionData;

    /**
     * objektin animaation
     */
    protected Animation animation;
    
    /**
     * objektin tämänhetkisen tilan kuvaus integerinä
     */
    protected int currentAction;
    
    /**
     * objektin edellisen tilan kuvaus integerinä
     */
    protected int previousAction;
    
    /**
     * objektin orientaatio
     */
    protected boolean facesRight;

    /**
     * kertoo onko objekti liikkumassa vasemmalle
     */
    protected boolean left;
    
    /**
     * kertoo onko objekti liikkumassa oikealle
     */
    protected boolean right;
    
    /**
     * kertoo onko objekti liikkumassa ylös
     */
    protected boolean up;
    
    /**
     * kertoo onko objekti liikkumassa alas
     */
    protected boolean down;
    
    /**
     * kertoo onko objekti tällä hetkellä hyppäämässä
     */
    protected boolean jumping;
    
    /**
     * kertoo onko objekti tällä hetkellä putoamassa
     */
    protected boolean falling;

    /**
     * säilöö objektin fysiikkaan liittyvät attribuutit
     */
    protected PhysicsAttributes physicsAttributes;

    /**
     * Konstruktori
     * 
     * @param tm TileMap -tyypin kartta, johon objekti asetetaan.
     */
    public MapObject(TileMap tm) {
        tileVariables = new TileVariables();
        tileVariables.setTileMap(tm);
        tileVariables.setTileSize(tm.getTileSize());
        physicsAttributes = new PhysicsAttributes();
        collisionData = new CollisionData();
    }

    /**
     *
     * @return Palauttaa nykyisen olion collisionboksiin perustuvan
     * Rectangle-tyyppisen olion.
     *     
    */
    public Rectangle getCollisionBox() {
        return new Rectangle((int) x - collisionData.getCollisionWidth(), (int) y - collisionData.getCollisionHeight(), collisionData.getCollisionWidth(), collisionData.getCollisionHeight());
    }
    
    
    /**
     * @param other verrattava objekti
     * 
     * @return palauttaa totuusarvona osuvatko verratut objektit toisiinsa
     */
    public boolean intersects(MapObject other) {

        return getCollisionBox().intersects(other.getCollisionBox());
    }
    
    /**
     * laskee objektin kaikki yhteentörmäykset ja asettaa ne collisiondata -booleaneihin
     */
    public void calculateCollision(double x, double y) {

        int leftTile = (int) (x - collisionData.getCollisionWidth() / 2) / tileVariables.getTileSize();
        int rightTile = (int) (x + collisionData.getCollisionWidth() / 2 - 1) / tileVariables.getTileSize();
        int topTile = (int) (y - collisionData.getCollisionHeight() / 2) / tileVariables.getTileSize();
        int bottomTile = (int) (y + collisionData.getCollisionHeight() / 2 - 1) / tileVariables.getTileSize();

        //corners
        int topLeftType = tileVariables.getTileMap().getType(topTile, leftTile);
        int topRightType = tileVariables.getTileMap().getType(topTile, rightTile);
        int bottomLeftType = tileVariables.getTileMap().getType(bottomTile, leftTile);
        int bottomRightType = tileVariables.getTileMap().getType(bottomTile, rightTile);
        //sides
        int topMiddleType = tileVariables.getTileMap().getType(topTile, leftTile + ((rightTile - leftTile) / 2));
        int bottomMiddleType = tileVariables.getTileMap().getType(bottomTile, leftTile + ((rightTile - leftTile) / 2));
        int rightMiddleType = tileVariables.getTileMap().getType(topTile + ((bottomTile - topTile) / 2), rightTile);
        int leftMiddleType = tileVariables.getTileMap().getType(topTile + ((bottomTile - topTile) / 2), leftTile);

        collisionData.setTopLeft(topLeftType == Tile.SOLID);
        collisionData.setTopRight(topRightType == Tile.SOLID);
        collisionData.setTopMiddle(topMiddleType == Tile.SOLID);
        collisionData.setBottomLeft(bottomLeftType == Tile.SOLID);
        collisionData.setBottomRight(bottomRightType == Tile.SOLID);
        collisionData.setBottomMiddle(bottomMiddleType == Tile.SOLID);
        collisionData.setRightMiddle(rightMiddleType == Tile.SOLID);
        collisionData.setLeftMiddle(leftMiddleType == Tile.SOLID);

    }
    
    /**
     * objektin kuolema
     */
    public void death() {
        setPosition(100, 100);
    }

    /**
     * Tarkistaa yhteentörmäykset kartan kanssa ja asettaa sen mukaisesti objektin uuden sijainnin
     */
    public void checkTileMapCollision() {

        collisionData.setcurrentColumn((int) x / tileVariables.getTileSize());
        collisionData.setcurrentRow((int) y / tileVariables.getTileSize());
        //set destination coordinates
        setDestination();
        //check collisions
        checkCollisions();
        //check if we ran out of a cliff
        checkIfShouldSetFalling();

    }
    
    /**
     * Asettaa sijainnin, johon objekti yrittää seuraavaksi mennä
     */
    public void setDestination() {
        collisionData.setxDestination(x + dx);
        collisionData.setyDestination(y + dy);

        collisionData.setxTemporary(x);
        collisionData.setyTemporary(y);
    }
    
    /**
     * tarkistaa objektin yhteentörmäykset
     */
    public void checkCollisions() {
        //check horizontal collision
        calculateCollision(x, collisionData.getyDestination());
        checkUpwardsCollision();
        checkDownwardsCollision();

        //check vertical collision
        calculateCollision(collisionData.getxDestination(), y);
        checkLeftSideCollision();
        checkRightSideCollision();

    }
    
    /**
     * Tarkistaa juostiinko alas kielekkeeltä ja pitäisikö aloittaa putoaminen
     */
    public void checkIfShouldSetFalling() {
        if (!falling) {
            calculateCollision(x, collisionData.getyDestination() + 1);
            if (!collisionData.getBottomLeft() && !collisionData.getBottomRight() && !collisionData.getBottomMiddle()) {
                falling = true;
            }
        }
    }
    
    /**
     * Tarkistaa yhteentörmäykset katon kanssa
     */
    public void checkUpwardsCollision() {
        if (dy < 0) {
            if (collisionData.getTopLeft() || collisionData.getTopRight() || collisionData.getTopMiddle()) {
                dy = 0;
                collisionData.setyTemporary(1.0 * (collisionData.getcurrentRow() * tileVariables.getTileSize() + collisionData.getCollisionHeight() / 2));
            } else {
                collisionData.setyTemporary(collisionData.getyTemporary() + dy);
            }
        }
    }
    
    /**
     * Tarkistaa yhteentörmäykset lattian kanssa
     */
    public void checkDownwardsCollision() {
        if (dy > 0) {
            if (collisionData.getBottomLeft() || collisionData.getBottomMiddle() || collisionData.getBottomRight()) {
                dy = 0;
                falling = false;
                collisionData.setyTemporary(1.0 * ((collisionData.getcurrentRow() + 1) * tileVariables.getTileSize() - collisionData.getCollisionHeight() / 2));
            } else {
                collisionData.setyTemporary(collisionData.getyTemporary() + dy);
            }
        }
    }
    
    /**
     * Tarkistaa yhteentörmäykset vasemman seinän kanssa
     */
    public void checkLeftSideCollision() {
        if (dx < 0) {
            if (collisionData.getTopLeft() || collisionData.getBottomLeft() || collisionData.getLeftMiddle()) {
                dx = 0;
                collisionData.setxTemporary(1.0 * (collisionData.getcurrentColumn() * tileVariables.getTileSize() + collisionData.getCollisionWidth() / 2));
            } else {
                collisionData.setxTemporary(collisionData.getxTemporary() + dx);
            }
        }
    }
    
    /**
     * Tarkistaa yhteentörmäykset oikean seinän kanssa
     */
    public void checkRightSideCollision() {
        if (dx > 0) {
            if (collisionData.getTopRight() || collisionData.getRightMiddle() || collisionData.getBottomRight()) {
                dx = 0;
                collisionData.setxTemporary((1.0 * ((collisionData.getcurrentColumn()) + 1) * tileVariables.getTileSize() - collisionData.getCollisionWidth() / 2));
            } else {
                collisionData.setxTemporary(collisionData.getxTemporary() + dx);
            }
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public boolean getUp() {
        return up;
    }

    public boolean getDown() {
        return down;
    }

    public boolean getJumping() {
        return jumping;
    }

    public int getCollisionHeight() {
        return collisionData.getCollisionHeight();
    }

    public int getCollisionWidth() {
        return collisionData.getCollisionWidth();
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        tileVariables.setXMapPosition(tileVariables.getTileMap().getx());
        tileVariables.setYMapPosition(tileVariables.getTileMap().gety());
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        jumping = b;
    }
    
    /**
     * Palauttaa booleanina onko objekti ruudun ulkopuolella
     */
    public boolean notOnScreen() {
        return x + tileVariables.getXMapPosition() + width < 0 || x + tileVariables.getXMapPosition() - width > GlobalConstants.WINDOWWIDTH || y + tileVariables.getYMapPosition() + height < 0 || y + tileVariables.getYMapPosition() - height > GlobalConstants.WINDOWHEIGHT;
    }
    
    /**
     * Asettaa seuraavan framen liikkeen
     */
    public void getNextPosition() {
        if (left) {
            setLeftAcceleration();
        } else if (right) {
            setRightAcceleration();
        } else {
            if (dx > 0) {
                setLeftDeceleration();
            } else if (dx < 0) {
                setRightDeceleration();
            }
        }
    }
    
   
    private void setLeftAcceleration() {
        dx -= physicsAttributes.getMovingSpeed();
        if (dx < -physicsAttributes.getMaximumSpeed()) {
            dx = -physicsAttributes.getMaximumSpeed();
        }
    }

    private void setRightAcceleration() {
        dx += physicsAttributes.getMovingSpeed();
        if (dx > physicsAttributes.getMaximumSpeed()) {
            dx = physicsAttributes.getMaximumSpeed();
        }
    }

    private void setLeftDeceleration() {
        dx -= physicsAttributes.getDeceleration();
        if (dx < 0) {
            dx = 0;
        }
    }

    private void setRightDeceleration() {
        dx += physicsAttributes.getDeceleration();
        if (dx > 0) {
            dx = 0;
        }
    }
    
    /**
     * Piirtää objektin
     */
    public void draw(Graphics2D g) {
        if (facesRight) {
            g.drawImage(animation.getImage(), (int) (x + tileVariables.getXMapPosition() - width / 2), (int) (y + tileVariables.getYMapPosition() - height / 2), width, height, null);
        } else {
            g.drawImage(animation.getImage(), (int) (x + tileVariables.getXMapPosition() - width / 2 + width), (int) (y + tileVariables.getYMapPosition() - height / 2), -width, height, null);
        }
    }

    public CollisionData getCollisionDate() {
        return this.collisionData;
    }

    public PhysicsAttributes getPhysicsAttributes() {
        return this.physicsAttributes;
    }

    public TileVariables getTileVariables() {
        return this.tileVariables;
    }

    public Animation getAnimation() {
        return this.animation;
    }

}
