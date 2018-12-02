package readersLoaders;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, escape;

    public KeyManager() {
        keys = new boolean[256];
    }

    public void update() {
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        up = keys[KeyEvent.VK_W];
        escape = keys[KeyEvent.VK_ESCAPE];
    }
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public void keyTyped(KeyEvent e) {
    }
}
