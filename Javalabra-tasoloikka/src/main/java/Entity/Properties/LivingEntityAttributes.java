

package Entity.Properties;

/**
*
* @author nkostiai
*
* "Eläville" olioille tarkoitettu attribuuttiluokka, jossa säilytetään muunmuassa HP ja MP
*
*/
public class LivingEntityAttributes {
    
    //health and mana
    private int HP;
    private int maxHP;
    private int MP;
    private int maxMP;
    
    //boolean for checking if entity is dead
    private boolean isDead;
    
    //flinching attributes
    private boolean isFlinching;
    private long flinchTimer;
    
    public LivingEntityAttributes(){
        
    }
    
    //getters
    public int getHP(){
        return HP;
    }
    public int getMaxHP(){
        return maxHP;
    }
    public int getMP(){
        return MP;
    }
    public int getMaxMP(){
        return maxMP;
    }
    public boolean getIsDead(){
        return isDead;
    }
    public long getFlinchTime(){
        return flinchTimer;
    }
    public boolean getIsFliching(){
        return isFlinching;
    }
    
    //setters
    public void setHP(int HP){
        this.HP = HP;
    }
    public void setMaxHP(int maxHP){
        this.maxHP = maxHP;
    }
    public void setMP(int MP){
        this.MP = MP;
    }
    public void setMaxMP(int maxMP){
        this.maxMP = maxMP;
    }
    public void setDead(){
        isDead = true;
    }
    public void setFlinchTimer(long time){
        this.flinchTimer = time;
    }
    public void setFlinching(boolean b){
        isFlinching = b;
    }
    
    //misc
    public void regenerateMP(int amount) {
        MP += amount;
        if (MP > maxMP) {
            MP = maxMP;
        }
    }
    
    public void regenerateHP(int amount){
        HP += amount;
        if(HP > maxHP){
            HP = maxHP;
        }
    }
    
    public void depleteHP(int amount){
        HP -= amount;
    }
    
    public void depleteMP(int amount){
        MP -= amount;
    }
}