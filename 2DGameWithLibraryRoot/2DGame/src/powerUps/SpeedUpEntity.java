package powerUps;
import java.awt.Graphics;

import creaturesSight.Entity;
import main.Handler;
import world.Assets;
import world.Tile;
import world.World;

public class SpeedUpEntity extends Entity {

	public SpeedUpEntity(Handler handler, float x, float y) {
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

	public void render(Graphics g) {
		if (SpeedUpEntity.isEaten() == true) {
			for (int i = 0; i < World.lengthOfSpeedUp(); i = i + 2) {
				g.drawImage(Assets.grass,
						(int) (((World.speedUpArray(i)) * Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
						(int) ((World.speedUpArray(i + 1) * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset()),
						Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			}
		}
	}

}
