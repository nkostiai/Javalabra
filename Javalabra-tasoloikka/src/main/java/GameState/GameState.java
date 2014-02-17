
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
    
    protected GameStateManager gsm;
    protected StateType type;
    public abstract void init();
    public abstract void update();
    public abstract void handleInput();
    public StateType getType(){
        return this.type;
    }
    
    
}
