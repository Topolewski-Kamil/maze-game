package world;

import java.awt.image.BufferedImage;

/**
 * Will store Buffered Image
 */
public class SpriteSheet {

	private BufferedImage sheet;

	/**
	 * Constructor that takes Buffered Image as a parameter which will be:
	 * 
	 * @param sheet
	 */
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	/**
	 * Method that crops or takes part of the image, according to the screen
	 * coordinates:
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
