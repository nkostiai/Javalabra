
package GameState;

import java.awt.Graphics2D;
/**
*
* @author nkostiai
*
* Abstracti superluokka joka määrittelee kaikkien GameState -luokan perivien luokkien välttämättömät metodit.
* Jokaisella GameStatella tulee myös olla viittaus pelin GameStateManageriin.
*
*
*/
public abstract class GameState {
    
    protected GameStateManager gsm;
    
    public abstract void init();
    public abstract void draw(Graphics2D g);
    public abstract void update();
    public abstract void handleInput();
    
    
}
