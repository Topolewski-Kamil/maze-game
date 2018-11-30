package Bot;

import main.Handler;
import world.Tile;

public abstract class CreatureBot extends EntityBot { // using methods from Entity

	// public PowerUpEntity p1;

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 50, DEFAULT_CREATURE_HEIGHT = 60;

	protected int health;
	public float speed;
	protected float xMove, yMove;
	private int tx, ty;

	private static long millis = System.currentTimeMillis() % 1000;
	private static long currentTime = 10000000;

	public int getHealth() {
		return health;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void move() {
		moveX();
		moveY();
	}

	public void moveX() {
		if (xMove > 0) {// Moving right
			tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			
			if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
				x += xMove;
				Bot.setCount(0);
			}

		} else if (xMove < 0) {// Moving left
			tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

			if (moveIfNotCollision(tx, (int) y, bounds.y, bounds.height)) {
				x += xMove;
				Bot.setCount(0);

			}
		}
	}

	public void moveY() {
		if (yMove < 0) {// Up
			ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
				y += yMove;
				Bot.setCount(0);

			}
		} else if (yMove > 0) {// Down
			ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (moveIfNotCollision(ty, (int) x, bounds.x, bounds.width)) {
				y += yMove;
				Bot.setCount(0);

			}
		}
	}

	protected boolean moveIfNotCollision(int tex, int ex, int bounds, int bounds2) {
		if (tex == tx) {
			if (!collision(tex, (int) (ex + bounds) / Tile.TILEHEIGHT)
					&& !collision(tex, (int) (ex + bounds + bounds2) / Tile.TILEHEIGHT)) {

				return true;

			} else {

				return false;
			}
		}
		if (tex == ty) {
			if (!collision((int) (ex + bounds) / Tile.TILEWIDTH, tex)
					&& !collision((int) (ex + bounds + bounds2) / Tile.TILEWIDTH, tex)) {

				return true;

			} else {

				return false;
			}
		}
		return false;
	}

	protected boolean collision(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	public CreatureBot(Handler handler, float x, float y, int width, int height) { // has to has the same variables as
		// Entity constructor
		super(handler, x, y, width, height); // passes those variables to Entity constructor variables
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 100;
		yMove = 100;
	}
}