package main;

import java.awt.*;
import java.awt.image.BufferStrategy;

import input.KeyManager;
import input.MouseManager;
import states.*;
import world.Assets;
import world.Tile;

/**
 * Main part of the game It holds hold all base code - start, run, close
 * Runnable - allows to run the thread
 */
public class Game implements Runnable { // Runnable - allows to run the thread

	private Display display; // initialize an object
	private int width, height; // size of window
	public String title;

	private Thread thread; // thread is like a mini program that runs separately from main program
	private boolean running = false;

	private BufferStrategy bs; // buffer is a "hidden screen", firstly draws on buffer than on screen - prevent
								// flickering
	private Graphics g; // object g is like brush, it can draw on the canvas

	// States
	public State gameState, menuState, highScoreState, howToPlayState, instructionState;

	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	// Camera
	private GameCamera gameCamera;

	// Handler
	private Handler handler;

	/**
	 * Game constructor is gonna need instances of our display class
	 * 
	 * @param title  title of the display
	 * @param width  width of the display
	 * @param height height of the display Whenever a new game instance is created
	 *               it will automatically set a display for it to have
	 */
	public Game(String title, int width, int height) { // constructor + parameteres of display

		// store variables
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}

	/**
	 * Initializes all the graphics Creates new states
	 */
	private void init() { // initializes all the graphics
		display = new Display(title, width, height);

		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getFrame().addKeyListener(keyManager);
		display.getCanvas().addKeyListener(keyManager);

		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);

		// create new states
		menuState = new MenuState(handler);
		highScoreState = new HighScoreState(handler);
		howToPlayState = new HowToPlayState(handler);
		instructionState = new InstructionState(handler);

		State.setState(menuState);
	}

	private void update() { // updates our game after one game loop
		keyManager.update();

		if (State.getState() != null) { // if state exists
			State.getState().update(); // update it
		}
	}

	/**
	 * draws things to the screen buffer is a hidden screen which prevents
	 * flickering object get display canvas; how many buffers we are going to use if
	 * canvas has non buffers set num of buffers to 3
	 */
	private void render() { // drawing method
		// buffer is a hidden screen which prevent flicking
		bs = display.getCanvas().getBufferStrategy(); // object get display canvas; how many buffers we are going to use
		if (bs == null) { // if canvas has non buffers
			display.getCanvas().createBufferStrategy(3); // set num of buffers to 3
			return;
		}

		g = bs.getDrawGraphics();

		// clear screen
		g.clearRect(0, 0, width, height);

		// draw graphics

		if (State.getState() != null) { // if state exists
			State.getState().render(g); // render it
		}
		// show graphics
		bs.show();
		g.dispose();
	}

	/**
	 * calls init() method ,it is ran only once Runs game loop for a game: 1)
	 * Updates all variables, positions of objects, etc. 2) Render (draw) everything
	 * to the screen tick() and render() shall be run over over again stop() in case
	 * it wasn't stopped before
	 */
	public void run() {
		init(); // call our display/img etc

		int fps = 60; // how many times a second should it refresh
		double timePerUpload = 1_000_000_000 / fps; // in one second how many times we want to refresh
													// 1 bilion nanosecond = 1 second, because more specific'
		double delta = 0; //
		long now; //
		long lastTime = System.nanoTime(); // returns time of our game

		// loop which will update and render
		while (running) { // as long as running equals true
			now = System.nanoTime(); // 2. Set to time after loop
			delta += (now - lastTime) / timePerUpload;

			lastTime = now; // 1. Takes time before loop

			if (delta >= 1) {
				update();
				render(); // keep ticking & rendering (game loop)
				delta--;

			}
		}
		stop();
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Starts thread synchronized - when working with thread directly, so when
	 * starting or stopping it will not mess up Checks if the game is running (if
	 * running equals true) Initializes the thread; this is also applying to the
	 * Game class thread.start(); Calls run() method
	 */
	public synchronized void start() { // starts thread;
		if (running) // check if the game is running (if running equals true)
			return;
		running = true;
		thread = new Thread(this); // initialize the thread; this is applying to Game class
		thread.start(); // call run method
	}

	/**
	 * Method that will close down our thread properly
	 */
	public synchronized void stop() { // stops thread
		if (!running)
			return;
		running = false;
		try {
			thread.join(); // stops the thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Tile getTile(int x, int y) { // return Tile object
		return Tile.grassTile;

	}

	public void reloadMenuState() {
		Assets.init();
		menuState = new MenuState(handler);
	}

	public void reloadGameState() {
		Assets.init();
		gameState = new GameState(handler, display);
		gameCamera = new GameCamera(handler, 0, 0);
	}

	public void reloadSettingsState() {
		Assets.init();
	}
}
