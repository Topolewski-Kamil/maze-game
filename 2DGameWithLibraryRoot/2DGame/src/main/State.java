package main;

import java.awt.*;

/**
 * Holds things states have in common Its abstract because it doesn't initialize
 * anything
 */
public abstract class State {

	private static State currentState = null;

	/**
	 * Set current state
	 * 
	 * @param state
	 */
	public static void setState(State state) {
		currentState = state;
	}

	/**
	 * Returns our current state
	 * 
	 * @return currentState
	 */
	public static State getState() { // returning state
		return currentState;
	} // returns our current state

	protected Handler handler;

	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void update();

	public abstract void render(Graphics graphics);
}
