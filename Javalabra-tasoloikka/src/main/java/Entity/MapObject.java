package Entity;

import Entity.Properties.*;
import Global.GlobalConstants;
import TileMap.Tile;
import TileMap.TileMap;
import java.awt.Rectangle;
/**
*
* @author nkostiai
*
* Toimii abstraktina superluokkana kaikille pelikartalla sijaitseville objekteille.
* Objekteille kuuluu erilaisia ominaisuuksia, kuten sijainti, liikkeen nopeus ja yhteentörmäystiedot.
*
*/
public abstract class MapObject {

    //tile variables
    protected TileVariables tileVariables;

    //position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    //dimensions
    protected int width;
    protected int height;
    //collision dimensions
    protected CollisionData collisionData;

    //animations
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facesRight;

    //actions
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    //physics
    protected PhysicsAttributes physicsAttributes;

    //constructor
    public MapObject(TileMap tm) {
        tileVariables = new TileVariables();
        tileVariables.setTileMap(tm);
        tileVariables.setTileSize(tm.getTileSize());
        physicsAttributes = new PhysicsAttributes();
        collisionData = new CollisionData();
    }

/**
*
*
* @return Palauttaa nykyisen olion collisionboksiin perustuvan Rectangle-tyyppisen olion.
*
*/
    public Rectangle getCollisionBox() {
        return new Rectangle((int) x - collisionData.getCollisionWidth(), (int) y - collisionData.getCollisionHeight(), collisionData.getCollisionWidth(), collisionData.getCollisionHeight());
    }

    public boolean intersects(MapObject other) {
        
        return getCollisionBox().intersects(other.getCollisionBox());
    }

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
    
    public void death(){
        setPosition(100, 100);
    }

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
    
    public void setDestination(){
        collisionData.setxDestination(x + dx);
        collisionData.setyDestination(y + dy);

        collisionData.setxTemporary(x);
        collisionData.setyTemporary(y);
    }
    public void checkCollisions(){
        //check horizontal collision
        calculateCollision(x, collisionData.getyDestination());
        checkUpwardsCollision();
        checkDownwardsCollision();
        
        //check vertical collision
        calculateCollision(collisionData.getxDestination(), y);
        checkLeftSideCollision();
        checkRightSideCollision();
        
    }
    
    public void checkIfShouldSetFalling(){
        if (!falling) {
            calculateCollision(x, collisionData.getyDestination() + 1);
            if (!collisionData.getBottomLeft() && !collisionData.getBottomRight() && !collisionData.getBottomMiddle()) {
                falling = true;
            }
        }
    }
    
    public void checkUpwardsCollision(){
        if (dy < 0) {
            if (collisionData.getTopLeft() || collisionData.getTopRight() || collisionData.getTopMiddle()) {
                dy = 0;
                collisionData.setyTemporary(1.0*( collisionData.getcurrentRow() * tileVariables.getTileSize() + collisionData.getCollisionHeight() / 2));
            } else {
                collisionData.setyTemporary(collisionData.getyTemporary() + dy);
            }
        }
    }
    
    public void checkDownwardsCollision(){
        if (dy > 0) {
            if (collisionData.getBottomLeft() || collisionData.getBottomMiddle() || collisionData.getBottomRight()) {
                dy = 0;
                falling = false;
                collisionData.setyTemporary(1.0*((collisionData.getcurrentRow() + 1) * tileVariables.getTileSize() - collisionData.getCollisionHeight() / 2));
            } else {
                collisionData.setyTemporary(collisionData.getyTemporary() + dy);
            }
        }
    }
    
    public void checkLeftSideCollision(){
        if (dx < 0) {
            if (collisionData.getTopLeft() || collisionData.getBottomLeft() || collisionData.getLeftMiddle()) {
                dx = 0;
                collisionData.setxTemporary(1.0 * (collisionData.getcurrentColumn() * tileVariables.getTileSize() + collisionData.getCollisionWidth() / 2));
            } else {
                collisionData.setxTemporary(collisionData.getxTemporary() + dx);
            }
        }
    }
    
    public void checkRightSideCollision(){
        if (dx > 0) {
            if (collisionData.getTopRight() || collisionData.getRightMiddle() || collisionData.getBottomRight()) {
                dx = 0;
                collisionData.setxTemporary((1.0*((collisionData.getcurrentColumn()) + 1) * tileVariables.getTileSize() - collisionData.getCollisionWidth() / 2));
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
    
    public boolean getLeft(){
        return left;
    }
    
    public boolean getRight(){
        return right;
    }
    
    public boolean getUp(){
        return up;
    }
    
    public boolean getDown(){
        return down;
    }
    
    public boolean getJumping(){
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

    public boolean notOnScreen() {
        return x + tileVariables.getXMapPosition() + width < 0 || x + tileVariables.getXMapPosition() - width > GlobalConstants.WINDOWWIDTH || y + tileVariables.getYMapPosition() + height < 0 || y + tileVariables.getYMapPosition() - height > GlobalConstants.WINDOWHEIGHT;
    }

}
