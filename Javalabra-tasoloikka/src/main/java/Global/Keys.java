//Tämä luokka hoitaa näppäinten painalllusten rekisteröinnin ja käsittelyn
package Global;

import java.awt.event.KeyEvent;

public class Keys {

    public static final int NumberOfKeys = 16;

    public static boolean[] keyState = new boolean[NumberOfKeys];
    public static boolean[] prevKeyState = new boolean[NumberOfKeys];
    
    //Tämän voisi ehkä hoitaa enumeilla..
    public static int UP = 0;
    public static int LEFT = 1;
    public static int DOWN = 2;
    public static int RIGHT = 3;
    public static int BUTTON1 = 4;
    public static int BUTTON2 = 5;
    public static int BUTTON3 = 6;
    public static int BUTTON4 = 7;
    public static int ENTER = 8;
    public static int ESCAPE = 9;

    public static void setKeyPressStatus(int i, boolean b) {
        if (i == KeyEvent.VK_UP) {
            keyState[UP] = b;
        } else if (i == KeyEvent.VK_LEFT) {
            keyState[LEFT] = b;
        } else if (i == KeyEvent.VK_DOWN) {
            keyState[DOWN] = b;
        } else if (i == KeyEvent.VK_RIGHT) {
            keyState[RIGHT] = b;
        } else if (i == KeyEvent.VK_W) {
            keyState[BUTTON1] = b;
        } else if (i == KeyEvent.VK_E) {
            keyState[BUTTON2] = b;
        } else if (i == KeyEvent.VK_R) {
            keyState[BUTTON3] = b;
        } else if (i == KeyEvent.VK_F) {
            keyState[BUTTON4] = b;
        } else if (i == KeyEvent.VK_ENTER) {
            keyState[ENTER] = b;
        } else if (i == KeyEvent.VK_ESCAPE) {
            keyState[ESCAPE] = b;
        }
    }

    public static void update() {
        for (int i = 0; i < NumberOfKeys; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    public static boolean isPressed(int i) {
        return keyState[i] && !prevKeyState[i];
    }

}
