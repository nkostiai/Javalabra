

package Entity.Properties;

/**
*
* @author nkostiai
*
* Ominaisuusluokka, joka kuvaa MapObject -superluokan fysiikan simuloimiseen 
* tarkoitettuja ominaisuuksia, kuten hahmon liikkumisnopeus, maksiminopeus ja 
* hyppäämiskorkeus.
*
*/
public class PhysicsAttributes {
    private double movingSpeed;
    private double maximumSpeed;
    private double deceleration;
    private double fallingSpeed;
    private double maximumFallingSpeed;
    private double initialJumpSpeed;
    private double stopJumpingSpeed;
    
    public PhysicsAttributes(){
        
    }
    
    public void setMovingSpeed(double movingSpeed){
        this.movingSpeed = movingSpeed;
    }
    
    public void setMaximumSpeed(double maximumSpeed){
        this.maximumSpeed = maximumSpeed;
    }
    public void setDeceleration(double deceleration){
        this.deceleration = deceleration;
    }
    public void setFallingSpeed(double fallingSpeed){
        this.fallingSpeed = fallingSpeed;
    }
    public void setMaximumFallingSpeed(double maximumFallingSpeed){
        this.maximumFallingSpeed = maximumFallingSpeed;
    }
    public void setInitialJumpSpeed(double initialJumpSpeed){
        this.initialJumpSpeed = initialJumpSpeed;
    }
    public void setStopJumpingSpeed(double stopJumpingSpeed){
        this.stopJumpingSpeed = stopJumpingSpeed;
    }
    
    public double getMovingSpeed(){
        return this.movingSpeed;
    }
    
    public double getMaximumSpeed(){
       return this.maximumSpeed;
    }
    public double getDeceleration(){
       return this.deceleration;
    }
    public double getFallingSpeed(){
        return this.fallingSpeed;
    }
    public double getMaximumFallingSpeed(){
        return this.maximumFallingSpeed;
    }
    public double getInitialJumpSpeed(){
        return this.initialJumpSpeed;
    }
    public double getStopJumpingSpeed(){
        return this.stopJumpingSpeed;
    }
}