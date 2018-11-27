package powerUps;
import world.Assets;
import world.Tile;

public class EagleEyeTile extends Tile{

    public EagleEyeTile(int id) {
        super(Assets.eagleEye, id);
    }

    public boolean isPowerUp(){

        return true;

    }
    public boolean isSolid() {
    	return false;
    }
    

    public int whichPowerUp() {
    	return 2;
    }
}
