package creaturesSight;

import java.awt.Graphics;
import main.Handler;
import world.Assets;

/**
 * Class that draws players' sight limit
 */
public class Sight {

	private Handler handler;

	// first coordinate of sight
	private float xCircle;

	// second coordinate of sight
	private float yCircle;

	private Player player;

	// size of sight image
	static int size = 2500;

	private static int powerX = 0;
	private static int powerY = 0;

	private static void setPowerX(int powerX) {
		Sight.powerX = powerX;
	}

	private static void setPowerY(int powerY) {
		Sight.powerY = powerY;
	}

	public Sight(Handler handler, float x, float y, Player player) {
		this.handler = handler;
		this.xCircle = player.getPlayerX();
		this.yCircle = player.getPlayerY();
		this.player = player;

	}

	public static void setSize(int size) {
		Sight.size = size;
	}

	public void update() {
		xCircle = player.getPlayerX();
		yCircle = player.getPlayerY();

	}

	public static void eagleEyeEnable() {
		setSize(3300);
		setPowerX(-400);
		setPowerY(-400);
	}

	public static void eagleEyeDisable() {
		Sight.setSize(2500);
		Sight.setPowerX(0);
		Sight.setPowerY(0);
	}

	/**
	 * method rendering the whole in the image on the player and moving with him
	 */
	public void render(Graphics g) {
		g.drawImage(Assets.hole, (int) (xCircle - 1260 - handler.getGameCamera().getxOffset()) + powerX,
				(int) (yCircle - 1258 - handler.getGameCamera().getyOffset()) + powerY, size, size, null);
		
	}
}