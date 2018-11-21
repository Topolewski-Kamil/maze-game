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
			g.drawImage(Assets.grass, (int) (260 - handler.getGameCamera().getxOffset()),
					(int) (68 - handler.getGameCamera().getyOffset()), width, height, null);
		}
	}

}
