package Entity;

import Global.C;
import TileMap.Tile;
import TileMap.TileMap;
import java.awt.Rectangle;

public abstract class MapObject {

    //tile variables
    protected TileMap tileMap;
    protected int tileSize;
    protected double xMapPosition;
    protected double yMapPosition;

    //position and vector
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    //dimensions
    protected int width;
    protected int height;
    //collision dimensions
    protected int collisionWidth;
    protected int collisionHeight;

    //other
    protected int currentRow;
    protected int currentColumn;
    protected double xDestination;
    protected double yDestination;
    protected double xTemporary;
    protected double yTemporary;
    //collision booleans
    protected boolean topLeft;
    protected boolean topMiddle;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomMiddle;
    protected boolean bottomRight;
    protected boolean rightMiddle;
    protected boolean leftMiddle;

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
    protected double movingSpeed;
    protected double maximumSpeed;
    protected double deceleration;
    protected double fallingSpeed;
    protected double maximumFallingSpeed;
    protected double jumpHeight;
    protected double stopJumpingSpeed;

    //constructor
    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }

    public Rectangle getCollisionBox() {
        return new Rectangle((int) x - collisionWidth, (int) y - collisionHeight, collisionWidth, collisionHeight);
    }

    public boolean intersects(MapObject other) {
        return getCollisionBox().intersects(other.getCollisionBox());
    }

    public void calculateCollision(double x, double y) {

        int leftTile = (int) (x - collisionWidth / 2) / tileSize;
        int rightTile = (int) (x + collisionWidth / 2 - 1) / tileSize;
        int topTile = (int) (y - collisionHeight / 2) / tileSize;
        int bottomTile = (int) (y + collisionHeight / 2 - 1) / tileSize;

        //corners
        int topLeftType = tileMap.getType(topTile, leftTile);
        int topRightType = tileMap.getType(topTile, rightTile);
        int bottomLeftType = tileMap.getType(bottomTile, leftTile);
        int bottomRightType = tileMap.getType(bottomTile, rightTile);
        //sides
        int topMiddleType = tileMap.getType(topTile, leftTile + ((rightTile - leftTile) / 2));
        int bottomMiddleType = tileMap.getType(bottomTile, leftTile + ((rightTile - leftTile) / 2));
        int rightMiddleType = tileMap.getType(topTile + ((bottomTile - topTile) / 2), rightTile);
        int leftMiddleType = tileMap.getType(topTile + ((bottomTile - topTile) / 2), leftTile);

        topLeft = topLeftType == Tile.SOLID;
        topRight = topRightType == Tile.SOLID;
        topMiddle = topMiddleType == Tile.SOLID;
        bottomLeft = bottomLeftType == Tile.SOLID;
        bottomRight = bottomRightType == Tile.SOLID;
        bottomMiddle = bottomMiddleType == Tile.SOLID;
        rightMiddle = rightMiddleType == Tile.SOLID;
        leftMiddle = leftMiddleType == Tile.SOLID;

    }

    public void checkTileMapCollision() {

        currentColumn = (int) x / tileSize;
        currentRow = (int) y / tileSize;

        xDestination = x + dx;
        yDestination = y + dy;

        xTemporary = x;
        yTemporary = y;

        calculateCollision(x, yDestination);
        if (dy < 0) {
            if (topLeft || topRight || topMiddle) {
                dy = 0;
                yTemporary = currentRow * tileSize + collisionHeight / 2;
            } else {
                yTemporary += dy;
            }
        }
        if (dy > 0) {
            if (bottomLeft || bottomMiddle || bottomRight) {
                dy = 0;
                falling = false;
                yTemporary = (currentRow + 1) * tileSize - collisionHeight / 2;
            } else {
                yTemporary += dy;
            }
        }

        calculateCollision(xDestination, y);
        if (dx < 0) {
            if (topLeft || bottomLeft || leftMiddle) {
                dx = 0;
                xTemporary = currentColumn * tileSize + collisionWidth / 2;
            } else {
                xTemporary += dx;
            }
        }
        if (dx > 0) {
            if (topRight || rightMiddle || bottomRight) {
                dx = 0;
                xTemporary = (currentColumn + 1) * tileSize - collisionWidth / 2;
            } else {
                xTemporary += dx;
            }
        }
        if (!falling) {
            calculateCollision(x, yDestination + 1);
            if (!bottomLeft && !bottomRight && !bottomMiddle) {
                falling = true;
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

    public int getCollisionHeight() {
        return collisionHeight;
    }

    public int getCollisionWidth() {
        return collisionWidth;
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
        xMapPosition = tileMap.getx();
        yMapPosition = tileMap.gety();
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
        return x + xMapPosition + width < 0 || x + xMapPosition - width > C.WINDOWWIDTH || y + yMapPosition + height < 0 || y + yMapPosition - height > C.WINDOWHEIGHT;
    }

}
