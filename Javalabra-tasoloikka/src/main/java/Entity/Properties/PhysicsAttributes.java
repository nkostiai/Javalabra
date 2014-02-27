

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
    /**
     * Liikkeen nopeus
     */
    private double movingSpeed;
    
    /**
     * Liikkeen maksiminopeus
     */
    private double maximumSpeed;
    
    /**
     * Liikkeen hidastumisnopeus
     */
    private double deceleration;
    
    /**
     * Putoamisen nopeus
     */
    private double fallingSpeed;
    
    /**
     * Putoamisnopeuden maksimi
     */
    private double maximumFallingSpeed;
    
    /**
     * Hypyn aloitusnopeus
     */
    private double initialJumpSpeed;
    
    /**
     * Hypyn lopetuksessa hidastumisen nopeus
     */
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