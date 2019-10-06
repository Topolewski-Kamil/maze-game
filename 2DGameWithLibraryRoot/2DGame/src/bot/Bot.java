package bot;

import creaturesSight.Creature;
import main.Handler;
import world.Assets;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that draws bot on the screen (with changing its profile), coins dropped
 * by the thief and chooses bot direction of movement.
 */
public class Bot extends CreatureBot {

	// variable to control how often coins should be dropped
	private static long currentTime;

	// array list to store the coordinates of the coins
	private static ArrayList<Integer> arr = new ArrayList<Integer>();

	public static int getK() {
		return k;
	}

	public static void setK(int k) {
		Bot.k = k;
	}

	// object to randomize direction of bot movement
	private static Random rand = new Random();

	// variable which is direction of bot movement
	private static int k = rand.nextInt(4);

	/**
	 * variables which describes the bot
	 * 
	 * @param handler
	 * @param x       First coordinate of the bot.
	 * @param y       Second coordinate of the bot.
	 * 
	 *                Constructor to provide initial values for bot.
	 */
	public Bot(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 63;
		bounds.height = 63;

	}

	/**
	 * Method which in GameState updates the bot coordinates.
	 */
	@Override
	public void update() {
		moving();
		move();
		coinCoordinates();
	}

	/**
	 * Method which sets direction of movement of the bot depending on his current
	 * direction and what variable k was randomized.
	 * 
	 * @param //xMove direction of movement right or left.
	 * @param //yMove direction of movement up or down.
	 */
	private void moving() {
		xMove = 0;
		yMove = 0;

		// move up
		if (k == 0) {
			yMove = -speed;

		}
		// move down
		if (k == 1) {

			yMove = speed;

		}

		// move right
		if (k == 2) {
			xMove = speed;

		}
		// move left
		if (k == 3) {
			xMove = -speed;

		}
	}

	/**
	 * Randomizes number 0 or 1, which states for moving up or down
	 * 
	 * @return int 0 or 1
	 */
	public static int upOrDown() {
		return rand.nextInt(2);
	}

	/**
	 * Randomizes number 2 or 3, which states for moving right or left
	 * 
	 * @return int 2 or 3
	 */
	public static int rightOrLeft() {
		return rand.nextInt(2) + 2;
	}

	/**
	 * Method adding to array current coordinates of thief every 4 seconds
	 */
	public void coinCoordinates() {
		if (arr.size() == 12) {
			arr.remove(0);
			arr.remove(0);
		}

		if (Creature.isCaught() == false) {
			if (System.currentTimeMillis() - currentTime > 4000) {
				arr.add(CreatureBot.getEx());
				arr.add(CreatureBot.getEy()); 

				currentTime = System.currentTimeMillis();
			}
		}
	}

	/**
	 * Method that displays coin drops and bot image on the screen.
	 */
	@Override
	public void render(Graphics g) {
		for (int i = 0; i < arr.size(); i = i + 2) {
			g.drawImage(Assets.coin, (int) (arr.get(i) - handler.getGameCamera().getxOffset()),
					(int) (arr.get(i + 1) - handler.getGameCamera().getyOffset()), 64, 64, null);
		}
		if (Creature.isCaught() == false) {
			if (Bot.getK() == 1) {
				g.drawImage(Assets.thiefFront, (int) (x - handler.getGameCamera().getxOffset()),
						(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (Bot.getK() == 2) {
				g.drawImage(Assets.thiefRight, (int) (x - handler.getGameCamera().getxOffset()),
						(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (Bot.getK() == 3) {
				g.drawImage(Assets.thiefLeft, (int) (x - handler.getGameCamera().getxOffset()),
						(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			} else if (Bot.getK() == 0) {
				g.drawImage(Assets.thiefBack, (int) (x - handler.getGameCamera().getxOffset()),
						(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
			}
		}
//		g.drawRect((int) (x - handler.getGameCamera().getxOffset()),
//				(int) (y - handler.getGameCamera().getyOffset()), 63, 63);
	}
}