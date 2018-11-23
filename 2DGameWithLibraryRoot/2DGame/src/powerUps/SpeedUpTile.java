package powerUps;
import readersLoaders.Assets;
import world.Tile;

public class SpeedUpTile extends Tile{

    public SpeedUpTile(int id) {
        super(Assets.speedUp, id);
    }

    public boolean isPowerUp(){

        return true;

    }
    public boolean isSolid() {
    	return false;
    }
}
