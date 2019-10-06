package world;

import java.awt.*;
import java.security.SecureRandom;
import java.util.Arrays;

import main.Handler;
import readersLoaders.Utils;

/**
 * Hold everything connected with the world, and displays everything on the
 * screen
 */
public class World {
    private Handler handler;
    private int width, height;
    private int[][] tilesIndex;
    private String[] tokens;
    private static int[] speedUpArr;
    private static int[] eagleEyeArr;
    private final static int NUMBER_OF_TILES = 25;

    /**
     * Loads a world from the file
     *
     * @param handler
     * @param path
     */
    public World(Handler handler, String path) {
        this.handler = handler;
        loadWorld(path);
    }

    public void updateEagleEye() {
        loadEagleEye();
    }

    public void updateSpeedUp() {
        loadSpeedUp();
    }

    /**
     * Displays world to the screen
     *
     * @param g Graphics xStart tile on the left of the screen that the user can see
     *          xEnd tile the last tile on the right that the user can see And as
     *          player moves , it updates this numbers
     */
    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width,
                (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height,
                (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));

            }
        }
    }

    /**
     * Return Tile object
     *
     * @param x
     * @param y
     * @return
     */
    public Tile getTile(int x, int y) { // return Tile object
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tilesIndex[x][y]];
        if (t == null) {

            return Tile.grassTile;

        }

        return t;
    }

    /**
     * Loads world and stores it inside tilesIndex. Randomizes powerUps and doors
     * coordinates.
     *
     * @param path file name
     */
    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        tilesIndex = new int[width][height];

		loadEagleEye();
		loadSpeedUp();

        SecureRandom r = new SecureRandom();
        int a = r.nextInt(2);
        if (a == 1) {
            a = 23;
        }
        int b = r.nextInt(22) + 1;

        System.out.println("Door " + a + " " + b);
        tilesIndex[a][b] = 4;
//		System.out.println("a: " + a + " b:" + b);

    }

    public void loadEagleEye() {
        SecureRandom randomizer = new SecureRandom();
        int ranNumber;
        eagleEyeArr = new int[0];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

				if (tilesIndex[x][y] != 2 && tilesIndex[x][y] != 4)
					tilesIndex[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);

                if (tilesIndex[x][y] == 0) {
                    ranNumber = randomizer.nextInt(80);
                    if (ranNumber == 5) {

                        tilesIndex[x][y] = 3;
                        eagleEyeArr = Arrays.copyOf(eagleEyeArr, eagleEyeArr.length + 2);
                        eagleEyeArr[eagleEyeArr.length - 2] = x;
                        eagleEyeArr[eagleEyeArr.length - 1] = y;

                    }

                }
            }
        }
    }

    public void loadSpeedUp() {
        SecureRandom randomizer = new SecureRandom();
        int ranNumber;

		speedUpArr = new int[0];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

				if (tilesIndex[x][y] != 3 && tilesIndex[x][y] != 4) {
					tilesIndex[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
				}
                if (tilesIndex[x][y] == 0) {
                    ranNumber = randomizer.nextInt(80);
                    if (ranNumber == 1) {

                        tilesIndex[x][y] = 2;

                        speedUpArr = Arrays.copyOf(speedUpArr, speedUpArr.length + 2);
                        speedUpArr[speedUpArr.length - 2] = x;
                        speedUpArr[speedUpArr.length - 1] = y;

                    }
                }
            }
        }

    }

    public static int speedUpArray(int i) {
        return speedUpArr[i];
    }

    public static int lengthOfSpeedUp() {
        return speedUpArr.length;
    }

    public static int eagleEyeArray(int i) {
        return eagleEyeArr[i];
    }

    public static int lengthOfEagleEye() {
        return eagleEyeArr.length;
    }
}
