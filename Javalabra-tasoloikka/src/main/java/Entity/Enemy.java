

package Entity;

import Entity.Properties.LivingEntityAttributes;
import TileMap.TileMap;

/**
*
* @author nkostiai
*
* MapObject -luokan perivä pelin vihollisia kuvaava superluokka. Tästä luokasta on tarkoitus periä
* kaikkien vihollisten yksittäiset luokat.
*
*/
public class Enemy extends MapObject{
    
    protected LivingEntityAttributes livingAttributes;
    
    //damage the enemy does to the player
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