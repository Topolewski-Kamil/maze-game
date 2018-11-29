package world;
import java.awt.*;
import java.awt.image.BufferedImage;

import powerUps.EagleEyeTile;
import powerUps.SpeedUpTile;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile wallTile = new WallTile(1);
    public static Tile powerUp = new SpeedUpTile(2);
    public static Tile eagleTile = new EagleEyeTile(3);
    public static Tile doorTile = new DoorTile(4);


    protected  BufferedImage texture;
    protected final int id;
    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    public Tile (BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void update() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);

    }

    public boolean isSolid() {
        return false;
    }
    public boolean isPowerUp() {
    	return false;
    }
    public boolean isDoor(){return false;}

    public int getId() {
        return id;
    }
}
