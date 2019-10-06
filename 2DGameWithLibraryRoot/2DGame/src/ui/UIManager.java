package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import main.Handler;
import world.Assets;

/**
 * Handles all of the UI objects
 */
public class UIManager {
	private int playerX = 0;
	private int playerY = 50;
	private int policeX = 0;
	private int policeY = 0;
	private int sizeOfMaze = 710;
	private int policeK;
	private int thiefK;

	public int getThiefK() {
		return thiefK;
	}

	public void setThiefK(int thiefK) {
		this.thiefK = thiefK;
	}

	public int getPoliceK() {
		return policeK;
	}

	public void setPoliceK(int k) {
		this.policeK = k;
	}

	private Handler handler;
	private ArrayList<UIObject> objects;

	public UIManager(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}

	public void tick() {
		for (UIObject o : objects) {
			o.tick();
		}
		// moving down
		if (playerX == 0 && playerY < sizeOfMaze) {
			playerY = playerY + 5;
			this.setThiefK(0);
		}
		if (policeX == 0 && policeY < sizeOfMaze + 120) {
			policeY = policeY + 5;
			this.setPoliceK(0);
		}
		// moving right
		if (playerY == sizeOfMaze && playerX < sizeOfMaze + 70) {
			playerX = playerX + 5;
			this.setThiefK(1);
		}

		if (policeY == sizeOfMaze + 70 && policeX < sizeOfMaze + 70) {
			policeX = policeX + 5;
			this.setPoliceK(1);
		}
		// moving up
		if (playerX == sizeOfMaze + 70 && playerY + 62 > 0) {
			playerY = playerY - 5;
			this.setThiefK(2);
		}

		if (policeX == sizeOfMaze + 70 && policeY > 0) {
			policeY = policeY - 5;
			this.setPoliceK(2);
		}
		// moving left
		if (playerY + 65 == 0 && playerX > 0) {
			playerX = playerX - 5;
			this.setThiefK(3);
		}

		if (policeY == 0 && policeX > 0) {
			policeX = policeX - 5;
			this.setPoliceK(3);
		}
	}

	public void render(Graphics g) {
		for (UIObject o : objects)
			o.render(g);
		if (this.getPoliceK() == 0) {
			g.drawImage(Assets.policeFront, policeX, policeY, 50, 60, null);
		} else if (this.getPoliceK() == 1) {
			g.drawImage(Assets.policeRight, policeX, policeY, 50, 60, null);
		} else if (this.getPoliceK() == 2) {
			g.drawImage(Assets.policeBack, policeX, policeY, 50, 60, null);
		} else if (this.getPoliceK() == 3) {
			g.drawImage(Assets.policeLeft, policeX, policeY, 50, 60, null);
		}
		if (this.getThiefK() == 0) {
			g.drawImage(Assets.thiefFront, playerX, playerY + 70, 50, 60, null);
		} else if (this.getThiefK() == 1) {
			g.drawImage(Assets.thiefRight, playerX, playerY + 70, 50, 60, null);
		} else if (this.getThiefK() == 2) {
			g.drawImage(Assets.thiefBack, playerX, playerY + 70, 50, 60, null);
		} else if (this.getThiefK() == 3) {
			g.drawImage(Assets.thiefLeft, playerX, playerY + 70, 50, 60, null);
		}
	}

	public void onMouseMove(MouseEvent e) {
		for (UIObject o : objects)
			o.onMouseMove(e);
	}

	public void onMouseRelease(MouseEvent e) {
		for (UIObject o : objects)
			o.onMouseRelease(e);
	}

	public void addObject(UIObject o) {
		objects.add(o);
	}

	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}

}
