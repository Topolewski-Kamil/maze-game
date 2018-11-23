package powerUps;
import readersLoaders.Assets;
import world.Tile;

public class EagleEyeTile extends Tile{

    public EagleEyeTile(int id) {
        super(Assets.speedUp, id);
    }

//    public boolean isPowerUp(){
//
//        return true;
//
//    }
    public boolean isSolid() {
    	return false;
    }
}
