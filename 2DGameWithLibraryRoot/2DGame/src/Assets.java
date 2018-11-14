import java.awt.image.BufferedImage;

//creating another class for it just to render it once
public class Assets {

    private static final int size = 50;

    public static BufferedImage thiefFront, policeFront, playerRight, playerBack, wall, grass; // variables which will correspond to pictures

    public static void init(){ // load everything in our game
    	SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures1/sheet.png"));
        SpriteSheet playersheet = new SpriteSheet(ImageLoader.loadImage("/textures1/playersheet.png"));
    	
//    	BufferedImage grass = ImageIO.read(new File("C:/Users/kamil/Desktop/obrazeczki/sheet"));
//    	BufferedImage wall = ImageIO.read(new File("C:/Users/kamil//Desktop/obrazeczki/playersheet"));
   

        grass = sheet.crop(0, 0, size, size);
        wall = sheet.crop( size, 0, size, size);
        thiefFront = playersheet.crop(50, 0, 190, 250);
        policeFront = playersheet.crop(30, 300, 175, 250);
    }
}
