

package Entity.Properties;

/**
*
* @author nkostiai
*
* "Eläville" olioille tarkoitettu attribuuttiluokka, jossa säilytetään muunmuassa HP ja MP
*
*/
public class LivingEntityAttributes {
    
    /**
     * Elämäpisteet.
     */
    private int HP;
    
    /**
     * Elämäpisteiden maksimimäärä.
     */
    private int maxHP;
    
    /**
     * Magiapisteet.
     */
    private int MP;
    
    /**
     * Magiapisteiden maksimimäärä.
     */
    private int maxMP;
    
    /**
     * Onko kohde kuollut.
     */
    private boolean isDead;
    
    /**
     * Välkkyykö kohde.
     */
    private boolean isFlinching;
    
    /**
     * Montako framea kohde on välkkynyt.
     */
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
    
    /**
     * Lisää magiapisteitä halutun määrän verran.
     * 
     * @param amount määrä
     */
    public void regenerateMP(int amount) {
        MP += amount;
        if (MP > maxMP) {
            MP = maxMP;
        }
    }
    
    /**
     * Lisää elämäpisteitä halutun määrän verran.
     * 
     * @param amount määrä
     */
    public void regenerateHP(int amount){
        HP += amount;
        if(HP > maxHP){
            HP = maxHP;
        }
    }
    
    /**
     * Vähentää elämäpisteitä halutun määrän verran
     * 
     * @param amount määrä 
     */
    public void depleteHP(int amount){
        HP -= amount;
    }
    
    /**
     * Vähentää magiapisteitä halutun määrän verran
     * 
     * @param amount määrä
     */
    public void depleteMP(int amount){
        MP -= amount;
    }
}