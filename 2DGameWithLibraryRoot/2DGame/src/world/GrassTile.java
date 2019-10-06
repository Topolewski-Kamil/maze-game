package world;

/**
 * Grass tile class. Returns id and its specifications.
 */
public class GrassTile extends Tile {

	/**
	 * Grass tile only has an id because it only has image
	 * 
	 * @param id
	 */
	public GrassTile(int id) {
		super(Assets.grass, id);
	}
}
