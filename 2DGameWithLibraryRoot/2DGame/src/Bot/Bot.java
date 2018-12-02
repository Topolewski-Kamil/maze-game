package Bot;

import creaturesSight.Creature;
import main.Handler;
import world.Assets;
import world.Tile;

import java.awt.*;
import java.util.Random;

public class Bot extends CreatureBot {

	public static int getK() {
		return k;
	}

	public static void setCount(int count) {
		Bot.count = count;
	}

	public static void setK(int k) {
		Bot.k = k;
	}

	private static int k = 1;
	private static int count = 0;
	private static Random rand = new Random(); 

	public Bot(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 0;
        bounds.y = 0;
        bounds.width = 63;
        bounds.height = 63;

	}

	public void update() {
		moving();
		move();
	}

	public static int getRandom(int[] array) {

		int r = new Random().nextInt(array.length);
		return array[r];
	}

	private void moving() {
		xMove = 0;
		yMove = 0;

		int[] numbers = new int[] { 0, 1, 2, 3 };

		// up
		if (k == 0) {
			yMove = -speed;
			count++;
			while (count > 20) {
				k = getRandom(numbers);
				count = 0;
			}
		}
		// down
		if (k == 1) {

			yMove = speed;
			count++;
			while (count > 20) {
				k = getRandom(numbers);
				count = 0;
			}
		}

		//right
		if (k == 2) {
			xMove = speed;
			count++;
			while (count > 20) {
				k = getRandom(numbers);
				count = 0;
			}
		}
		//left
		if (k == 3) {
			xMove = -speed;
			count++;
			while (count > 20) {
				k = getRandom(numbers);
				count = 0;
			}
		}
	}
	public static int upOrDown() {
		return rand.nextInt(2);
	}
	public static int rightOrLeft() {
		return rand.nextInt(2) + 2;
	}

	public void render(Graphics g) {
		g.drawImage(Assets.thiefFront, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.red);
		
//		g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

}
