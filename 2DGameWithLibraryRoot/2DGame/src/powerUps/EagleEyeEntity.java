package powerUps;

import java.awt.Graphics;

import creaturesSight.Entity;
import main.Handler;
import world.Assets;
import world.Tile;
import world.World;

/**
 * Sight increase entity class, draws the power up and manages its states.
 */
public class EagleEyeEntity extends Entity {

	public EagleEyeEntity(Handler handler, float x, float y) {
		super(handler, x, y, 60, 60);
	}

	public static boolean eaten = false;

	public static boolean isEaten() {
		return eaten;
	}

	public static void setEaten(boolean isIt) {
		eaten = isIt;
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {
		if (EagleEyeEntity.isEaten()) {
			for (int i = 0; i < World.lengthOfEagleEye(); i = i + 2) {
				g.drawImage(Assets.grass,
						(int) (((World.eagleEyeArray(i)) * Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
						(int) ((World.eagleEyeArray(i + 1) * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset()),
						Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			}
		}
	}
}
