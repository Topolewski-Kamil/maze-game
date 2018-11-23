package creaturesSight;
import java.awt.Graphics;

import main.Handler;
import readersLoaders.Assets;

public class Sight extends Entity {

	public Sight(Handler handler, float x, float y) {
		super(handler, x, y, 1500, 1500);
	}

	public void update() {

	}

	public void render(Graphics g) {
		
		g.drawImage(Assets.hole, (int) (x - 1000 + bounds.x - handler.getGameCamera().getxOffset()),
				(int) (y - 1000 + bounds.y - handler.getGameCamera().getyOffset()), 2000, 2000, null);
	}

}
