package bot;

import main.Handler;
import world.Tile;

/**
 * Abstract class that creates different variables of the bot. Also describes
 * its movement.
 */
public abstract class CreatureBot extends EntityBot { // using methods from Entity

	// speed of AI creature
	public static final float DEFAULT_SPEED = 4.0f;

	// width and height of AI creature
	public static final int DEFAULT_CREATURE_WIDTH = 50, DEFAULT_CREATURE_HEIGHT = 60;

	// variable of speed needed for changing direction of movement
	protected float speed;

	// variables for bot movement
	protected float xMove, yMove;

	// Coordinates in front of the player according to its movement
	private int tx, ty;

	public static int getEx() {
		return (int) x;
	}

	public static int getEy() {
		return (int) y;
	}

	public void move() {
		moveX();
		moveY();
	}

	/**
	 * Method which changes variables xMove and yMove - changes movement direction
	 * of thief.
	 */
	public void moveX() {
		if (xMove > 0) {// Moving right
			tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

			if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
				x += xMove;
			} else {

				Bot.setK(Bot.upOrDown());

				if (Bot.getK() == 0) {

					ty = (int) (y + yMove - 10 + bounds.y) / Tile.TILEHEIGHT;
					if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
						y += yMove;
					} else {
						Bot.setK(1);
					}

				} else {
					ty = (int) (y + yMove + 10 + bounds.y + bounds.height) / Tile.TILEHEIGHT;
					if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
						y += yMove;
					} else {
						Bot.setK(0);
					}

				}
			}

		} else if (xMove < 0) {// Moving left
			tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

			if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
				x += xMove;
			} else {

				Bot.setK(Bot.upOrDown());

				if (Bot.getK() == 0) {

					ty = (int) (y + yMove - 10 + bounds.y) / Tile.TILEHEIGHT;
					if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
						y += yMove;
					} else {
						Bot.setK(1);
					}

				} else {
					ty = (int) (y + yMove + 10 + bounds.y + bounds.height) / Tile.TILEHEIGHT;
					if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
						y += yMove;
					} else {
						Bot.setK(0);
					}

				}
			}
		}
	}

	public void moveY() {
		if (yMove < 0) {// Up
			ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
				y += yMove;
			} else {

				Bot.setK(Bot.rightOrLeft());

				if (Bot.getK() == 2) {

					tx = (int) (x + xMove + 10 + bounds.x + bounds.width) / Tile.TILEWIDTH;
					if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
						x += xMove;
					} else {
						Bot.setK(3);
					}

				} else {
					tx = (int) (x + xMove - 10 + bounds.x) / Tile.TILEWIDTH;
					if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
						x += xMove;
					} else {
						Bot.setK(2);
					}

				}
			}
		} else if (yMove > 0) {// Down
			ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
				y += yMove;
			} else {

				Bot.setK(Bot.rightOrLeft());

				if (Bot.getK() == 2) {

					tx = (int) (x + xMove + 10 + bounds.x + bounds.width) / Tile.TILEWIDTH;
					if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
						x += xMove;
					} else {
						Bot.setK(3);
					}

				} else {
					tx = (int) (x + xMove - 10 + bounds.x) / Tile.TILEWIDTH;
					if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
						x += xMove;
					} else {
						Bot.setK(2);
					}

				}
			}
		}
	}

	protected boolean moveIfNotCollision(int te, float ex, int bounds, int bounds2) {
		if (te == ty) {
			if (!collision((int) (ex + bounds) / Tile.TILEWIDTH, te)
					&& !collision((int) (x + bounds + bounds2) / Tile.TILEWIDTH, te)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (!collision(tx, (int) (ex + bounds) / Tile.TILEHEIGHT)
					&& !collision(tx, (int) (ex + bounds + bounds2) / Tile.TILEHEIGHT)) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Check if the Tile x, y is a wall.
	 * 
	 * @param x
	 * @param y
	 * @return boolean is there a wall or not
	 */
	protected boolean collision(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	/**
	 * Variables which describes the CreatureBot.
	 * 
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * 
	 *                Constructor to provide initial values for CreatureBot.
	 */
	public CreatureBot(Handler handler, float x, float y, int width, int height) { // has to has the same variables as
		// Entity constructor
		super(handler, x, y, width, height); // passes those variables to Entity constructor variables
		speed = DEFAULT_SPEED;
	}
}