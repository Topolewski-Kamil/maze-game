package powerUps;
import java.awt.Graphics;

import creaturesSight.Entity;
import main.Handler;
import readersLoaders.Assets;
import world.Tile;
import world.World;

public class EagleEyeEntity extends Entity {
	public EagleEyeEntity(Handler handler, float x, float y) {
		super(handler, x, y, 60, 60);
	}

//	public static boolean eaten = false;

//	public static boolean isEaten() {
//		return eaten;
//	}

//	public static void setEaten(boolean isIt) {
//		eaten = isIt;
//	}

	@Override
	public void update() {

	}

	public void render(Graphics g) {

		for (int i = 0; i < World.lengthOfEagleEye(); i = i + 2) {
			g.drawImage(Assets.eagleEye,
					(int) (((World.eagleEyeArray(i)) * Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
					(int) ((World.eagleEyeArray(i + 1) * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset()),
					Tile.TILEWIDTH, Tile.TILEHEIGHT, null);

		}
	}
}
