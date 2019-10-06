package creaturesSight;

import java.awt.*;
import main.Handler;

/**
 * Holds base code for every entity, abstract so that it will create specific
 * entity. Has position(x,y) , update , render methods.
 */

public abstract class Entity {

	protected Handler handler;

	// first and second coordinate of the player
	protected static float x, y;

	// width and height of entity
	protected int width, height;

	// Rectangle witch will represent movement bound of the player
	protected Rectangle bounds;

	// Width to center gameCamera on the player
	public int getWidth() {
		return width;
	}

	// Height to center gameCamera on the player
	public int getHeight() {
		return height;
	}

	/**
	 * Constructor to create entity
	 * 
	 * @param handler
	 * @param x       Position
	 * @param y       Position
	 * @param with    size
	 * @param height  size
	 */
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		Entity.x = x;
		Entity.y = y;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void update();

	public abstract void render(Graphics g);
}
