package powerUps;

import world.Assets;
import world.Tile;
/**
 * Speed increase Tile class, returns id and its specifications. 
 */
public class SpeedUpTile extends Tile {

	public SpeedUpTile(int id) {
		super(Assets.speedUp, id);
	}

	public boolean isPowerUp() {

		return true;

	}

	public boolean isSolid() {
		return false;
	}

	public int whichPowerUp() {
		return 1;
	}
}
