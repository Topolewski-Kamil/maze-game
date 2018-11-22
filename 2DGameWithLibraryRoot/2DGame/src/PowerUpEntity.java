import java.awt.Graphics;

public class PowerUpEntity extends Entity {
	
	public PowerUpEntity(Handler handler, float x, float y) {
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
		if (PowerUpEntity.isEaten() == true) {
			g.drawImage(Assets.grass, (int) ( ((World.speedUpArray(0))* Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
					(int) ((World.speedUpArray(1) * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
			g.drawImage(Assets.grass, (int) ( ((World.speedUpArray(2))* Tile.TILEWIDTH) - handler.getGameCamera().getxOffset()),
					(int) ((World.speedUpArray(3) * Tile.TILEHEIGHT) - handler.getGameCamera().getyOffset()), Tile.TILEWIDTH, Tile.TILEHEIGHT, null);
		}
	}

}
