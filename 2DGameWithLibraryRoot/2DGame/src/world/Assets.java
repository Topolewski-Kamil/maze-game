package world;
import java.awt.image.BufferedImage; 

import readersLoaders.ImageLoader;

//checking if it works

//creating another class for it just to render it once
public class Assets {

    private static final int size = 50;

    public static BufferedImage thiefFront, policeFront, playerRight, playerBack, wall, grass, speedUp, hole, eagleEye, door; // variables which will correspond to pictures

    //new 10:31
    //private static final int width = 32, height = 32;
    public static BufferedImage[] btn_start, btn_quit, btn_continue;
    //end of new 10:31

    public static void init(){ // load everything in our game
    	SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures1/sheet.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures1/playersheet.png"));
        SpriteSheet holesheet = new SpriteSheet(ImageLoader.loadImage("/textures1/hole2.png"));
        SpriteSheet doorimg = new SpriteSheet(ImageLoader.loadImage("/textures1/closed.png"));

        //new 10:31
        //SpriteSheet sheetMenu = new SpriteSheet(ImageLoader.loadImage("/textures1/sheet1.png"));
        btn_start = new BufferedImage[2];
        //button start 01
        SpriteSheet redStartButton = new SpriteSheet(ImageLoader.loadImage("/textures1/Menu_Red_01.png"));
        SpriteSheet greenStartButton = new SpriteSheet(ImageLoader.loadImage("/textures1/Menu_Green_01.png"));
        btn_start[0] = redStartButton.crop(0, 0, 228, 34);
        btn_start[1] = greenStartButton.crop(0, 0, 228, 34);
        //button start end 01
        //button quit 02
        btn_quit = new BufferedImage[2];
        SpriteSheet redQuitButton = new SpriteSheet(ImageLoader.loadImage("/textures1/Menu_Red_02.png"));
        SpriteSheet greenQuitButton = new SpriteSheet(ImageLoader.loadImage("/textures1/Menu_Green_02.png"));
        btn_quit[0] = redQuitButton.crop(0, 0, 228, 34);
        btn_quit[1] = greenQuitButton.crop(0, 0, 228, 34);
        //button quit end 02
        //btn_start[0] = sheetMenu.crop(width * 6, height * 4, width * 2, height);
        //btn_start[1] = sheetMenu.crop(width * 6, height * 5, width * 2, height);
        //button continue 03
        btn_continue = new BufferedImage[2];
        SpriteSheet redContinueButton = new SpriteSheet(ImageLoader.loadImage("/textures1/Menu_Red_03.png"));
        SpriteSheet greenContinueButton = new SpriteSheet(ImageLoader.loadImage("/textures1/Menu_Green_03.png"));
        btn_continue[0] = redContinueButton.crop(0, 0, 228, 34);
        btn_continue[1] = greenContinueButton.crop(0, 0, 228, 34);
        //button continue end 03
        //end of new 10:31
    	
        thiefFront = playersheet.crop(50, 0, 190, 250);
        policeFront = playersheet.crop(30, 300, 170, 250);
        door=doorimg.crop(0,0,25,50);
        
        grass = sheet.crop(0, 0, size, size);
        wall = sheet.crop( size, 0, size, size);

        
        speedUp = sheet.crop(6 * size, 0 , size, size);
        eagleEye = sheet.crop(7 * size, 0, size, size);
        
        hole = holesheet.crop(0, 0, 1500, 1500);

    }
}
