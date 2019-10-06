package readersLoaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Loads images
 */
public class ImageLoader {
	/**
	 * Method that returns object of our loaded image
	 * @param path location of image
	 * @return
	 */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path)); // load image
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); // if he cant load images dont run the game
		}
		return null;
	}

}
