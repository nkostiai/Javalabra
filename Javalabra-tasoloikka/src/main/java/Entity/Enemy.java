

package Entity;

import Entity.Properties.LivingEntityAttributes;
import TileMap.TileMap;

/**
*
* @author nkostiai
*
* MapObject -luokan perivä pelin vihollisia kuvaava yläluokka. Tästä luokasta on tarkoitus periä
* kaikkien vihollisten yksittäiset luokat.
*
*/
public class Enemy extends MapObject{
    
    /**
     * Eläviin olioihin liittyvät attribuutit.
     */
    protected LivingEntityAttributes livingAttributes;
    
    /**
     * Damage the enemy does to player on contact.
     */
    protected int damage;
    
    
    public Enemy(TileMap tm) {
        super(tm);
    }
    
    
    public boolean isDead(){
        return livingAttributes.getIsDead();
    }
    
    public int getDamage(){
        return damage;
    }
    
    /**
     * Vähentää vihollisen HP:ta annetun määrän verran ja tarkastaa kuoleeko vihollinen.
     * 
     * @param damage Vahingon määrä.
     */
    public void getsHit(int damage) {
        if (!(livingAttributes.getIsFliching())) {
           livingAttributes.depleteHP(damage);
           if(livingAttributes.getHP()<=0 && !livingAttributes.getIsDead()){
               livingAttributes.setDead();
           }
           else{
               livingAttributes.setFlinching(true);
               livingAttributes.setFlinchTimer(System.nanoTime());
           }
        }

    }
    
    public void update(){
        
    }
    
    public LivingEntityAttributes getAttributes(){
        return this.livingAttributes;
    }

}