package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages all of the keyboard input KeyListener essentially allows to access
 * the keyboard and tells what keys are and aren't being pressed
 */
public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, escape, unable, up2, down2, left2, right2;

	/**
	 * Constructor in order to create array of keys
	 */
	public KeyManager() {
		keys = new boolean[256];
	}

	/**
	 * Stores and updates which keys are pressed
	 */
	public void update() {
		// arrows
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		up = keys[KeyEvent.VK_UP];

		// WSAD
		down2 = keys[KeyEvent.VK_S];
		left2 = keys[KeyEvent.VK_A];
		right2 = keys[KeyEvent.VK_D];
		up2 = keys[KeyEvent.VK_W];

		escape = keys[KeyEvent.VK_ESCAPE];
		unable = keys[KeyEvent.VK_P];
	}

	/**
	 * Method is called whenever a key is pressed
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/**
	 * Method is called whenever a key is released
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}
}
