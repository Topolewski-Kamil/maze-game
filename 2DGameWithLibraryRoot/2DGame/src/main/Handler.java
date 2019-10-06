package main;

import input.KeyManager;
import input.MouseManager;
import world.World;

/**
 * Handler Class that lets to along a bunch of variables to anything that it
 * needs (to avoid mess in the code)
 */
public class Handler {

	private Game game;
	private World world;

	/**
	 * Constructor for handler to start
	 * 
	 * @param game Game
	 */
	public Handler(Game game) {
		this.game = game;
	}

	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public int getWidth() {
		return game.getWidth();
	}

	public Game getGame() {
		return game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public int getHeight() {
		return game.getHeight();
	}
}
