package world;
import java.awt.image.BufferedImage;

import readersLoaders.ImageLoader;

//checking if it works

//creating another class for it just to render it once
public class Assets {

    private static final int size = 50;

    public static BufferedImage thiefFront, policeFront, playerRight, playerBack, wall, grass, speedUp, hole, eagleEye; // variables which will correspond to pictures

    public static void init(){ // load everything in our game
    	SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures1/sheet.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures1/playersheet.png"));
        SpriteSheet holesheet = new SpriteSheet(ImageLoader.loadImage("/textures1/hole2.png"));
    	
        thiefFront = playersheet.crop(50, 0, 190, 250);
        policeFront = playersheet.crop(30, 300, 170, 250);
        
        grass = sheet.crop(0, 0, size, size);
        wall = sheet.crop( size, 0, size, size);
        
        speedUp = sheet.crop(6 * size, 0 , size, size);
        eagleEye = sheet.crop(7 * size, 0, size, size);
        
        hole = holesheet.crop(0, 0, 1500, 1500);
    }
}
