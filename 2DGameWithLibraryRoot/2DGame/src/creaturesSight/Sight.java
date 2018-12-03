package creaturesSight;
import java.awt.Graphics;

import main.Handler;
import world.Assets;

public class Sight extends Entity {
	public static int size = 2000;
	public static int powerX = 0;
	public static int powerY = 0;	

	public static void setPowerX(int powerX) {
		Sight.powerX = powerX;
	}

	public static void setPowerY(int powerY) {
		Sight.powerY = powerY;
	}

	public Sight(Handler handler, float x, float y) {
		super(handler, x, y, size, size);
	}
	public static void setSize(int size) {
		Sight.size = size;
	}

	public void update() {

	}

	public void render(Graphics g) {
		
		 g.drawImage(Assets.hole, (int) (x - 1000 + bounds.x - handler.getGameCamera().getxOffset()) +  powerX ,
				(int) (y - 1000 + bounds.y - handler.getGameCamera().getyOffset()) + powerY, size, size, null);
	}


	

}
