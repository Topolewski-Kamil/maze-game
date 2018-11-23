package world;
import java.awt.*;
import java.security.SecureRandom;
import java.util.Arrays;

import main.Handler;
import readersLoaders.Utils;

public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private String[] tokens;
    private static int[] speedUpArr;
    private static int[] eagleEyeArr;

    public World(Handler handler, String path){
        this.handler = handler;
        loadWorld(path);
    }

    public void update() {

    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
               
            }
        }
    }

    public Tile getTile(int x, int y) { // return Tile object
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null) {

            return Tile.grassTile;

        }
       
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        speedUpArr = new int[0];
        eagleEyeArr = new int[0];
        
        tiles = new int[width][height];
        SecureRandom randomizer = new SecureRandom();
        int ranNumber;
       
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++) {
            	
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                if (tiles[x][y] == 0) {
                	ranNumber = randomizer.nextInt(110);  
                	if (ranNumber == 1) {
                		
                		tiles[x][y] = 2;
                		
                		speedUpArr = Arrays.copyOf(speedUpArr, speedUpArr.length + 2);
                		speedUpArr[speedUpArr.length - 2] = x;
                		speedUpArr[speedUpArr.length - 1] = y;
                		
                	} else if (ranNumber == 2) {
                		
                		tiles[x][y] = 3;
                		eagleEyeArr = Arrays.copyOf(eagleEyeArr, eagleEyeArr.length + 2);
                		eagleEyeArr[eagleEyeArr.length - 2] = x;
                		eagleEyeArr[eagleEyeArr.length - 1] = y;
                		
                	}
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public static int speedUpArray(int i) {
    	return speedUpArr[i];
    }
    public static int lengthOfSpeedUp() {
    	return speedUpArr.length;
    }
    public static int lengthOfEagleEye() {
    	return eagleEyeArr.length;
    }
    public static int eagleEyeArray(int j) {
    	return eagleEyeArr[j];
    	
    }
}
