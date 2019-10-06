package world;

/**
 * Door tile class. Returns id and its specifications.
 */
public class DoorTile extends Tile {

	public DoorTile(int id) {
		super(Assets.policeStation, id);

	}

	@Override
	public boolean isDoor() {
		return true;
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
