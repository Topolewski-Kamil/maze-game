package world;

import java.awt.image.BufferedImage;

import readersLoaders.ImageLoader;

/**
 * Class for loading assets once ,but allows them to use many times
 */
public class Assets {

	private static final int size = 50;
	private static final int BUTTON_HEIGHT = 78;
	private static final int BUTTON_WIDTH = 240;

	public static BufferedImage cloud, cloudExit, thiefFront, policeFront, policeRight, thiefRight, thiefBack,
			thiefLeft, policeLeft, policeBack, thiefCaught, coin, wall, grass, speedUp, hole, eagleEye, policeStation,
			logoBig, logoSmall, background, instructions, policeman2, policeman22, black, cloud1, cloud2, cloud3, cloud4, cloud5;

	public static BufferedImage[] btn_start, btn_quit, btn_continue, btn_help, btn_reset, btn_highscore;

	/**
	 * Method that loads everything(images etc.) on for the game
	 */
	public static void init() { // load everything in our game
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures1/sheet.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures1/playersheet.png"));
		SpriteSheet buttonsSheet = new SpriteSheet(ImageLoader.loadImage("/textures1/buttonssheet3.png"));
		SpriteSheet cloudsSheet = new SpriteSheet(ImageLoader.loadImage("/textures1/cloudssheet2.png"));
		SpriteSheet logoSheet = new SpriteSheet(ImageLoader.loadImage("/textures1/logosheet.png"));

		btn_start = new BufferedImage[2];
		// button start 01
		btn_start[0] = buttonsSheet.crop(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_start[1] = buttonsSheet.crop(0, BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
		// button start end 01
		// button quit 02
		btn_quit = new BufferedImage[2];

		btn_quit[0] = buttonsSheet.crop(0, BUTTON_HEIGHT * 6, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_quit[1] = buttonsSheet.crop(0, BUTTON_HEIGHT * 7, BUTTON_WIDTH, BUTTON_HEIGHT);
		// button quit end 02

		// button continue 03
		btn_continue = new BufferedImage[2];

		btn_continue[0] = buttonsSheet.crop(0, BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_continue[1] = buttonsSheet.crop(0, BUTTON_HEIGHT * 3, BUTTON_WIDTH, BUTTON_HEIGHT);
		// button continue end 03
		// end of new 10:31

		btn_help = new BufferedImage[2];

		btn_help[0] = buttonsSheet.crop(0, BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_help[1] = buttonsSheet.crop(0, BUTTON_HEIGHT * 5, BUTTON_WIDTH, BUTTON_HEIGHT);

		btn_reset = new BufferedImage[2];

		btn_reset[0] = buttonsSheet.crop(0, BUTTON_HEIGHT * 8, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_reset[1] = buttonsSheet.crop(0, BUTTON_HEIGHT * 9, BUTTON_WIDTH, BUTTON_HEIGHT);

		btn_highscore = new BufferedImage[2];

		btn_highscore[0] = buttonsSheet.crop(0, BUTTON_HEIGHT * 10, BUTTON_WIDTH, BUTTON_HEIGHT);
		btn_highscore[1] = buttonsSheet.crop(0, BUTTON_HEIGHT * 11, BUTTON_WIDTH, BUTTON_HEIGHT);

		cloud = cloudsSheet.crop(135, 0, 135, 90);
		cloudExit = cloudsSheet.crop(0, 0, 135, 90);

		thiefFront = playerSheet.crop(20, 0, 190, 250);
		thiefRight = playerSheet.crop(210, 0, 190, 250);
		thiefBack = playerSheet.crop(420, 0, 190, 250);
		thiefLeft = playerSheet.crop(610, 0, 190, 250);

		policeFront = playerSheet.crop(30, 300, 170, 250);
		policeRight = playerSheet.crop(220, 300, 170, 250);
		policeBack = playerSheet.crop(440, 300, 165, 250);
		policeLeft = playerSheet.crop(590, 300, 170, 250);

		thiefCaught = playerSheet.crop(540, 550, 190, 250);

		coin = playerSheet.crop(310, 595, 150, 150);
		policeStation = tileSheet.crop(8 * size, 0, size, size);

		grass = tileSheet.crop(0, 0, size, size);
		wall = tileSheet.crop(size, 0, size, size);

		speedUp = tileSheet.crop(6 * size, 0, size, size);
		eagleEye = tileSheet.crop(7 * size, 0, size, size);

		hole = ImageLoader.loadImage("/textures1/hole2.png");
		logoBig = logoSheet.crop(0, 0, 800, 205);
		logoSmall = logoSheet.crop(77, 210, 652, 185);
		background = ImageLoader.loadImage("/textures1/grassBack.png");
		black = ImageLoader.loadImage("/textures1/black.png");

		instructions = ImageLoader.loadImage("/textures1/instructions.png");
		policeman2 = ImageLoader.loadImage("/textures1/policeman2.png");
		policeman22 = ImageLoader.loadImage("/textures1/policeman22.png");

		cloud1 = ImageLoader.loadImage("/textures1/cloudfirst.png");
		cloud2 = ImageLoader.loadImage("/textures1/cloudsecond.png");
		cloud3 = ImageLoader.loadImage("/textures1/cloudthird.png");
		cloud4 = ImageLoader.loadImage("/textures1/cloudfourth.png");
		cloud5 = ImageLoader.loadImage("/textures1/cloudfifth.png");


	}
}