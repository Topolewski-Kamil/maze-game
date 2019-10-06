package world;

/**
 * Door tile class. Returns id and its specifications.
 */

public class WallTile extends Tile {

	public WallTile(int id) {
		super(Assets.wall, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
