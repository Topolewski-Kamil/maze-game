package bot;

import main.Handler;

import java.awt.*;

/**
 * Abstract class that will be parent class for AI entities. Operates mostly as
 * AI entities manager.
 */
public abstract class EntityBot {

	protected Handler handler;

	// First coordinate of the bot.
	protected static float x;

	// Second coordinate of the bot.
	protected static float y;

	// Width and height of the bot
	protected int width, height;

	// Rectangle witch will represent movement bound of the player
	protected Rectangle bounds;

	public static float getX() {
		return x;
	}

	public static float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * variables which describes the AI
	 * @param handler
	 * @param x First coordinate of an AI.
	 * @param y Second coordinate of an AI.
	 * @param width Width of an AI.
	 * @param height Height of an AI.
	 * 
	 * Constructor to provide initial values for AI entity.
	 */
	public EntityBot(Handler handler, float x, float y, int width, int height) { // position x & y, size width & height
		this.handler = handler;
		EntityBot.x = 1408;
		EntityBot.y = 1408;
		this.width = width;
		this.height = height;

		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void update();

	public abstract void render(Graphics g);
}