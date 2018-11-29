package world;


import java.awt.image.BufferedImage;

public class DoorTile extends Tile {


    public DoorTile(int id) {
        super(Assets.door, id);

    }
    public boolean isDoor(){
        return true;
    }
    public boolean isSolid(){return true;}
}

