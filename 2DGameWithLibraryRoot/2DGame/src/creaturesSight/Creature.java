package creaturesSight;

import main.Handler;
import powerUps.EagleEyeEntity;
import powerUps.SpeedUpEntity;
import world.Tile;

import javax.swing.*;

public abstract class Creature extends Entity { // using methods from Entity

	public SpeedUpEntity p1;

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 50, DEFAULT_CREATURE_HEIGHT = 60;

	protected int health;
	protected static float speed;
	protected static float xMove, yMove;
	protected static int tx, ty, gx, gy;

	private static long currentTime = 10000000;

	public static int getTx() {
		return tx;
	}

	public static void setTx(int tx) {
		Creature.tx = tx;
	}

	public static int getTy() {
		return ty;
	}

	public static void setTy(int ty) {
		Creature.ty = ty;
	}

	public static float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		Creature.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		Creature.yMove = yMove;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		Creature.speed = speed;
	}

	public void move() {
		moveX();
		moveY();

	}


boolean  a =false;//kwedklsa

	public void moveX() {
		if(returnIsItDoor(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
				&& returnIsItDoor(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
			while(a==false) {
				Message.infoBox("You found the door, now go catch the thief and bring it here!", "Good job!");
				tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
				ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
				a=true;

			}

		}


		if (xMove > 0) {// Moving right



			tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			gx = (int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH;

			// reset the powerUps
			if (SpeedUpEntity.isEaten() == true || EagleEyeEntity.isEaten() == true) {
				if (System.currentTimeMillis() - currentTime > 3000) {
					this.setSpeed(4.0f);
					Sight.setSize(2000);
					Sight.setPowerX(0);
					Sight.setPowerY(0);
				}
				if (System.currentTimeMillis() - currentTime > 6000) {
					SpeedUpEntity.setEaten(false);
					EagleEyeEntity.setEaten(false);
				}
			}
			// check for powerUps when moving right

			if (checkCoordinatesForPowerUp(tx, y, bounds.y, bounds.height, -1)) {

				if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
						|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
					if (SpeedUpEntity.isEaten() == false) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTime = System.currentTimeMillis();
					}
				}

				if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
						|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
					if (EagleEyeEntity.isEaten() == false) {
						Sight.setSize(2500);
						Sight.setPowerX(-250);
						Sight.setPowerY(-260);
						EagleEyeEntity.setEaten(true);
						currentTime = System.currentTimeMillis();
					}
				}
			}

			// check for wall when moving right
			if (!returnIsItWall(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !returnIsItWall(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}

		} else if (xMove < 0) {// Moving left
			tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			gx = (int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH;

			// check for powerUps when moving left
			if (SpeedUpEntity.isEaten() == false) {
				if (checkCoordinatesForPowerUp(tx, y, bounds.y, bounds.height, 1)) {
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTime = System.currentTimeMillis();
					}
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
						if (EagleEyeEntity.isEaten() == false) {
							Sight.setSize(2500);
							Sight.setPowerX(-250);
							Sight.setPowerY(-260);
							EagleEyeEntity.setEaten(true);
							currentTime = System.currentTimeMillis();
						}
					}
				}
			}
			// check for wall when moving left
			if (!returnIsItWall(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !returnIsItWall(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			}
		}
	}

	public void moveY() {



		if (SpeedUpEntity.isEaten() == true) {
			if (System.currentTimeMillis() - currentTime > 3000) {
				this.setSpeed(4.0f);
			}
			if (System.currentTimeMillis() - currentTime > 6000) {
				SpeedUpEntity.setEaten(false);
				EagleEyeEntity.setEaten(false);

			}
		}
		if (yMove < 0) {// Up

			ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			// power up collision when moving up
			if (SpeedUpEntity.isEaten() == false) {
				if (checkCoordinatesForPowerUp(ty, x, bounds.x, bounds.width, 1)) {
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTime = System.currentTimeMillis();
					}
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
						if (EagleEyeEntity.isEaten() == false) {
							Sight.setSize(2500);
							Sight.setPowerX(-250);
							Sight.setPowerY(-260);
							EagleEyeEntity.setEaten(true);
							currentTime = System.currentTimeMillis();
						}
					}
				}
			}
			// wall collision when moving up
			if (!returnIsItWall((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !returnIsItWall((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}
		} else if (yMove > 0) {// Down
			ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

			if (SpeedUpEntity.isEaten() == false) {

				// power up collision when moving down
				if (checkCoordinatesForPowerUp(ty, x, bounds.x, bounds.width, -1)) {
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTime = System.currentTimeMillis();
					}
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
						if (EagleEyeEntity.isEaten() == false) {
							Sight.setSize(2500);
							Sight.setPowerX(-250);
							Sight.setPowerY(-260);
							EagleEyeEntity.setEaten(true);
							currentTime = System.currentTimeMillis();
						}
					}
				}
			}
			// wall collision when moving down
			if (!returnIsItWall((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !returnIsItWall((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			}
		}
	}



	public static class Message
	{

		public static void infoBox(String infoMessage, String titleBar)
		{
			JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected boolean returnIsItWall(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	protected boolean returnIsItDoor (int x, int y){return handler.getWorld().getTile(x,y).isDoor();}

	protected boolean returnIsItPowerUp(int x, int y) {
		return handler.getWorld().getTile(x, y).isPowerUp();
	}

	protected int returnIdOfPowerUp(int x, int y) {
		return handler.getWorld().getTile(x, y).whichPowerUp();
	}

	protected boolean checkCoordinatesForPowerUp(int te, float ex, int bounds, int bounds2, int shift) {
		if (te == ty) {
			if (returnIsItPowerUp((int) (ex + bounds) / Tile.TILEWIDTH, te + shift)
					&& returnIsItPowerUp((int) (x + bounds + bounds2) / Tile.TILEWIDTH, te + shift)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (returnIsItPowerUp(tx + shift, (int) (ex + bounds) / Tile.TILEHEIGHT)
					&& returnIsItPowerUp(tx + shift, (int) (ex + bounds + bounds2) / Tile.TILEHEIGHT)) {
				return true;
			}
			return false;
		}
	}

	public Creature(Handler handler, float x, float y, int width, int height) { // has to has the same variables as
																				// Entity constructor
		super(handler, x, y, width, height); // passes those variables to Entity constructor variables
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
}
