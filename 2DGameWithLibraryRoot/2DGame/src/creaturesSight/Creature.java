package creaturesSight;

import main.Handler;
import main.State;
import powerUps.EagleEyeEntity;
import powerUps.SpeedUpEntity;
import states.GameState;
import states.HighScore;
import states.MenuState;
import world.Tile;

import javax.swing.*;

import bot.Bot;
import bot.CreatureBot;
import world.World;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class that creates different variables of the player Also describes
 * its movement
 */
public abstract class Creature extends Entity { // using methods from Entity

	// Whether or not Creature tried to move into the door
	public static boolean triedDoor = false;

	// Wheter or not bot is caught by the player
	public static boolean caught = false;

	// Speed of a creature
	public static final float DEFAULT_SPEED = 4.0f;

	// Width and height of creature
	public static final int DEFAULT_CREATURE_WIDTH = 50, DEFAULT_CREATURE_HEIGHT = 60;

	// variable of speed needed for changing direction of movement
	protected static float speed;
	protected static float xMove, yMove;

	// Coordinates in front of the player according to its movement
	protected static int tx, ty;

	// Coordinates through player is passing according to its movement
	protected static int gx, gy;

	// Variable to control how often different actions should be performed in
	// comparison to System time.
	private static long currentTimeSpeed, currentTimeEagle, currentTime2, currentTime3;

	public static boolean triedDoored() {
		return triedDoor;
	}

	public static void setDoored(boolean doored) {
		Creature.triedDoor = doored;
	}

	public static boolean isCaught() {
		return caught;
	}

	public static void setCaught(boolean caught) {
		Creature.caught = caught;
	}

	public void setSpeed(float speed) {
		Creature.speed = speed;
	}

	public void move() {
		moveX();
		moveY();
		changeDirection();
		catchingThief();
		respawnSpeedPowerUp();
		respawnEaglePowerUp();
	}

	/**
	 * Method to check if player and bot collides. Check if difference between x and
	 * y coordinates of the bot and player is smaller than 35 pixels.
	 */
	public void catchingThief() {
		if (Creature.isCaught() == false) {
			if (Math.abs((int) y - CreatureBot.getEy() - 35) < 20
					&& Math.abs((int) x - CreatureBot.getEx() - 35) < 20) {
				Creature.setCaught(true);
			}
		}
	}

	/**
	 * Method that changes bot direction of movement depending on player location.
	 * If difference between x and y coordinates of the bot and player is smaller
	 * than 35 pixels changes current bot direction movement to opposite.
	 */
	public void changeDirection() {

		if (System.currentTimeMillis() - currentTime2 > 200) {
			if (Math.abs((int) y - CreatureBot.getEy() - 35) < 20) {
				if (x - 35 < CreatureBot.getEx()) {
					if (Bot.getK() == 3) {
						Bot.setK(2);
					}
				} else if (x - 35 > CreatureBot.getEx()) {
					if (Bot.getK() == 2) {
						Bot.setK(3);
					}
				}
				currentTime2 = System.currentTimeMillis();
			}
		}
		if (System.currentTimeMillis() - currentTime3 > 200) {
			if (Math.abs((int) x - CreatureBot.getEx() - 35) < 20) {
				if (y - 35 < CreatureBot.getEy()) {
					if (Bot.getK() == 0) {
						Bot.setK(1);
					}
				} else if (y - 35 > CreatureBot.getEy()) {
					if (Bot.getK() == 1) {
						Bot.setK(0);
					}
				}
				currentTime3 = System.currentTimeMillis();
			}
		}
	}

	/**
	 * Method that set normal speed. After 3 seconds disable speed boost and after 6
	 * respawn the powerUp.
	 */
	public void respawnSpeedPowerUp() {
		if (SpeedUpEntity.isEaten()) {
			if (System.currentTimeMillis() - currentTimeSpeed > 3000) {
				this.setSpeed(DEFAULT_SPEED);
			}
			if (System.currentTimeMillis() - currentTimeSpeed > 6000) {
				SpeedUpEntity.setEaten(false);
			}
		}
	}

	/**
	 * Method that set normal sight. After 3 seconds disable sight increase and
	 * after 6 respawn the powerUp.4
	 */
	private void respawnEaglePowerUp() {
		if (EagleEyeEntity.isEaten()) {
			if (System.currentTimeMillis() - currentTimeEagle > 3000) {
				Sight.eagleEyeDisable();
			}
			if (System.currentTimeMillis() - currentTimeEagle > 6000) {
				EagleEyeEntity.setEaten(false);
			}
		}
	}

	/**
	 * Method which is responsible for player movement - wall detection, checking
	 * for powerUps. Also changes speed and sight when power up is eaten.
	 */
	private void moveX() {

		if (returnIsItDoor(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
				&& returnIsItDoor(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
			if (!Creature.triedDoor) {
				Creature.setDoored(true);
				return;
			}
			if (Creature.isCaught()) {
				wonGame();
			}

		}

		if (xMove > 0) {// Moving right

			tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			gx = (int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH;

			// check for powerUps when moving right
			if (checkCoordinatesForPowerUp(tx, y, bounds.y, bounds.height, -1)) {
				if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
						|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {

					if (!SpeedUpEntity.isEaten() && !Player.cheatsEnabled) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTimeSpeed = System.currentTimeMillis();
					}
				}
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
					if (!EagleEyeEntity.isEaten() && !Player.cheatsEnabled) {

						Sight.eagleEyeEnable();
						EagleEyeEntity.setEaten(true);
						currentTimeEagle = System.currentTimeMillis();
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

			if (checkCoordinatesForPowerUp(tx, y, bounds.y, bounds.height, 1)) {
				if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
						|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
					if (!SpeedUpEntity.isEaten() && !Player.cheatsEnabled) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTimeSpeed = System.currentTimeMillis();
					}
				}
				if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
						|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
					if (!EagleEyeEntity.isEaten() && !Player.cheatsEnabled) {

						Sight.eagleEyeEnable();
						EagleEyeEntity.setEaten(true);
						currentTimeEagle = System.currentTimeMillis();
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
		if (yMove < 0) {// Up

			ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			// power up collision when moving up
			if (!SpeedUpEntity.isEaten() && !Player.cheatsEnabled) {
				if (checkCoordinatesForPowerUp(ty, x, bounds.x, bounds.width, 1)) {
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTimeSpeed = System.currentTimeMillis();
					}
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
						if (!EagleEyeEntity.isEaten()) {

							Sight.eagleEyeEnable();

							EagleEyeEntity.setEaten(true);
							currentTimeEagle = System.currentTimeMillis();
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

			if (!SpeedUpEntity.isEaten() && !Player.cheatsEnabled) {

				// power up collision when moving down
				if (checkCoordinatesForPowerUp(ty, x, bounds.x, bounds.width, -1)) {
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 1
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 1) {
						this.setSpeed(6.0f);
						SpeedUpEntity.setEaten(true);
						currentTimeSpeed = System.currentTimeMillis();
					}
					if (returnIdOfPowerUp(gx, (int) (y + bounds.y) / Tile.TILEHEIGHT) == 2
							|| returnIdOfPowerUp(gx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) == 2) {
						if (!EagleEyeEntity.isEaten()) {

							Sight.eagleEyeEnable();
							EagleEyeEntity.setEaten(true);
							currentTimeEagle = System.currentTimeMillis();
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

	/**
	 * Check if the Tile x, y is a wall.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean returnIsItWall(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	private boolean returnIsItDoor(int x, int y) {
		return handler.getWorld().getTile(x, y).isDoor();
	}

	private boolean returnIsItPowerUp(int x, int y) {
		return handler.getWorld().getTile(x, y).isPowerUp();
	}

	private int returnIdOfPowerUp(int x, int y) {
		return handler.getWorld().getTile(x, y).whichPowerUp();
	}

	/**
	 * check if player is passing through powerUp
	 * 
	 * @param te
	 * @param ex
	 * @param bounds
	 * @param bounds2
	 * @param shift
	 * @return whether is it powerUp
	 */
	private boolean checkCoordinatesForPowerUp(int te, float ex, int bounds, int bounds2, int shift) {
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

	/**
	 * Creature constructor has to has the same variables as Entity constructor
	 * 
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Creature(Handler handler, float x, float y, int width, int height) { // has to has the same variables as
																				// Entity constructor
		super(handler, x, y, width, height); // passes those variables to Entity constructor variables
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	/**
	 * Saves the acquired score to file and array, and sets state to the
	 * highScoreState
	 */
	public void wonGame() {
		MenuState.gameStarted = false;
		int score = GameState.levelTime;
		Object[] options = { "CONTINUE" };
		int n = JOptionPane.showOptionDialog(null, "You won! ", "Congratulations!", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == JOptionPane.OK_OPTION || n == JOptionPane.CLOSED_OPTION) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter("res1/maps/scores.txt", true));
				writer.write("\n");
				writer.write(MenuState.playerName);
				HighScore.n.add(MenuState.playerName);
				writer.append(' ');
				writer.write(Integer.toString(score));
				HighScore.s.add(score);
				writer.close();
				HighScore.loadScores();
			} catch (IOException e) {
				e.printStackTrace();
			}
			handler.getGame().reloadMenuState();
			State.setState(handler.getGame().highScoreState);
		}
	}
}