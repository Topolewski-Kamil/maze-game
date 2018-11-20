public class PowerUpTile extends Tile{

    public PowerUpTile(int id) {
        super(Assets.powerUp, id);
    }

    public boolean isPowerUp(){

        return true;

    }
    public boolean isSolid() {
    	return false;
    }
}
