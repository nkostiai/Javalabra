
package GameState;

import java.awt.Graphics2D;
/**
*
* @author nkostiai
*
* Abstracti yliluokka joka määrittelee kaikkien GameState -luokan perivien luokkien välttämättömät metodit.
* Jokaisella GameStatella tulee myös olla viittaus pelin GameStateManageriin.
*
*
*/
public abstract class GameState {
    
    /**
     * Viite gamestatemanageriin.
     */
    protected GameStateManager gsm;
    
    /**
     * Tämänhetkinen pelitilan tyyppi.
     */
    protected StateType type;
    
    /**
     * Initialisoi GameStaten tilan.
     */
    public abstract void init();
    
    /**
     * Päivittää GameStaten tilan.
     */
    public abstract void update();
    
    /**
     * Käsittelee näppäinpainallukset.
     */
    public abstract void handleInput();
    
    
    public StateType getType(){
        return this.type;
    }
    
    
}
