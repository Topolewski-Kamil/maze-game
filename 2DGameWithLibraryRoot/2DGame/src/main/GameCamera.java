package main;

import creaturesSight.Player;

/**
 * Helps move portion of the map to the another portion of the map
 */
public class GameCamera {

	private Handler handler;
	private float xOffset, yOffset;
	private int mapSize = 700 ;

	/**
	 * Centers the camera on an entity and follows an entity around
	 * 
	 * @param handler
	 * @param xOffset
	 * @param yOffset
	 */
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	private void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		}
		if (xOffset > mapSize) {
			xOffset = mapSize;
		}
		if (yOffset < 0) {
			yOffset = 0;
		}
		if (yOffset > mapSize) {
			yOffset = mapSize;
		}
	}

	/**
	 * Takes on what entity to set camera on
	 * 
	 * @param p Player
	 */
	public void centerOnEntity(Player p) {
		xOffset = p.getX() - handler.getWidth() / 2 + p.getWidth() / 2;
		yOffset = p.getY() - handler.getHeight() / 2 + p.getHeight() / 2;
		checkBlankSpace();
	}

	public void move(float xAmt, float yAmt) {
		xOffset = xOffset + xAmt;
		yOffset = yOffset + yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

}

