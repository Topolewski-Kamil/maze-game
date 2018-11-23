package creaturesSight;
import java.awt.*;

import main.Handler;

public abstract class Entity {

    protected Handler handler;
    protected static float x;
	protected static float y;
    protected int width, height;
    protected Rectangle bounds;

    public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Entity(Handler handler, float x, float y, int with, int height) { // position x & y, size width & height
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = with;
        this.height = height;

        bounds = new Rectangle(0, 0, width, height);
    }
    // all classes extending it has to have them
    public abstract void update();
    public abstract void render(Graphics g);
}
