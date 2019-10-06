package main;

import states.MusicPlayer;

/**
 * Class responsible for launching the game
 */
public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Maze game", 835, 900); // object game which runs Game constructor
		MusicPlayer player = new MusicPlayer("catchme");
		player.run();
		game.start();
	}
}
