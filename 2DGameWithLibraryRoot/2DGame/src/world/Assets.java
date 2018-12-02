package world;
import java.awt.image.BufferedImage; 

import readersLoaders.ImageLoader;

//checking if it works

//creating another class for it just to render it once
public class Assets {

    private static final int size = 50;

    public static BufferedImage thiefFront, policeFront, policeRight, thiefRight, thiefBack, thiefLeft, policeLeft, policeBack, wall, grass, speedUp, hole, eagleEye, door; // variables which will correspond to pictures

    public static void init(){ // load everything in our game
    	SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures1/sheet.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures1/playersheet2.png"));
        SpriteSheet holesheet = new SpriteSheet(ImageLoader.loadImage("/textures1/hole2.png"));
        SpriteSheet doorimg = new SpriteSheet(ImageLoader.loadImage("/textures1/closed.png"));
    	
        thiefFront = playersheet.crop(20, 0, 190, 250);
        thiefRight = playersheet.crop(210, 0, 190, 250);
        thiefBack = playersheet.crop(420, 0, 190, 250);
        thiefLeft = playersheet.crop(610, 0, 190, 250);

        policeFront = playersheet.crop(30, 300, 170, 250);
        policeRight = playersheet.crop(200, 300, 170, 250);
        policeBack = playersheet.crop(440, 300, 170, 250);
        policeLeft = playersheet.crop(590, 300, 170, 250);


        door = doorimg.crop(0,0,25,50);
        
        grass = sheet.crop(0, 0, size, size);
        wall = sheet.crop( size, 0, size, size);

        
        speedUp = sheet.crop(6 * size, 0 , size, size);
        eagleEye = sheet.crop(7 * size, 0, size, size);
        
        hole = holesheet.crop(0, 0, 1500, 1500);

    }
}
