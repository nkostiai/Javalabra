

package Entity.Properties;

/**
*
* @author nkostiai
*
* Ominaisuusluokka, jossa säilytetään MapObject -superluokan yhteentörmäyksen
* tunnistamiseen liittyviä ominaisuuksia.
*
*/
public class CollisionData {
    //collision dimensions
    private int collisionWidth;
    private int collisionHeight;

    //other
    private int currentRow;
    private int currentColumn;
    private double xDestination;
    private double yDestination;
    private double xTemporary;
    private double yTemporary;
    
    //collision booleans
    private boolean topLeft;
    private boolean topMiddle;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomMiddle;
    private boolean bottomRight;
    private boolean rightMiddle;
    private boolean leftMiddle;
    
    public CollisionData(){
        
    }
    
    public int getCollisionWidth(){
        return this.collisionWidth;
    }
    public void setCollisionWidth(int collisionWidth){
        this.collisionWidth = collisionWidth;
    }
    public int getCollisionHeight(){
        return this.collisionHeight;
    }
    public void setCollisionHeight(int collisionHeight){
        this.collisionHeight = collisionHeight;
    }
    public int getcurrentRow(){
        return this.currentRow;
    }
    public void setcurrentRow(int currentRow){
        this.currentRow = currentRow;
    }
    public int getcurrentColumn(){
        return this.currentColumn;
    }
    public void setcurrentColumn(int currentColumn){
        this.currentColumn = currentColumn;
    }
    public void setxDestination(Double xdest){
        this.xDestination = xdest;
    }
    public Double getxDestination(){
        return this.xDestination;
    }
    public void setyDestination(Double ydest){
        this.yDestination = ydest;
    }
    public Double getyDestination(){
        return this.yDestination;
    }
    public void setxTemporary(Double xtemp){
        this.xTemporary = xtemp;
    }
    public Double getxTemporary(){
        return this.xTemporary;
    }
    public void setyTemporary(Double ytemp){
        this.yTemporary = ytemp;
    }
    public Double getyTemporary(){
        return this.yTemporary;
    }
    public void setTopLeft(Boolean b){
        topLeft = b;
    }
    public boolean getTopLeft(){
        return topLeft;
    }
    public void setTopRight(Boolean b){
        topRight = b;
    }
    public boolean getTopRight(){
        return topRight;
    }
    public void setTopMiddle(Boolean b){
        topMiddle = b;
    }
    public boolean getTopMiddle(){
        return topMiddle;
    }
    public void setBottomLeft(Boolean b){
        bottomLeft = b;
    }
    public boolean getBottomLeft(){
        return bottomLeft;
    }
    public void setBottomRight(Boolean b){
        bottomRight = b;
    }
    public boolean getBottomRight(){
        return bottomRight;
    }
    public void setBottomMiddle(Boolean b){
        bottomMiddle = b;
    }
    public boolean getBottomMiddle(){
        return bottomMiddle;
    }
    public void setLeftMiddle(Boolean b){
        leftMiddle = b;
    }
    public boolean getLeftMiddle(){
        return leftMiddle;
    }
    public void setRightMiddle(Boolean b){
        rightMiddle = b;
    }
    public boolean getRightMiddle(){
        return rightMiddle;
    }
    
    
    
}