import java.awt.*;
import java.awt.image.BufferStrategy;

//will hold all base code - start, run, close
public class Game  implements Runnable  { // Runnable - allows to run the thread

    private Display display; // initialize an object
    private int width, height; // size of window
    public String title;
    
	public Creature c1;

    private Thread thread; // thread is like a mini program that runs separately from main program
    private boolean running = false;

    private BufferStrategy bs; //buffer is a "hidden screen", firstly draws on buffer than on screen - prevent flickering
    private Graphics g; // object g is like brush, it can draw on the canvas

    //States
    private State gameState; //creating state object called "gameState"
    private State menuState;

    //Input
    private KeyManager keyManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    public Game(String title, int width, int height) { // constructor + parameteres of display

        // store variables
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init() { // initializes all the graphics
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        handler = new Handler(this);

        // create new states
        gameState = new GameState(handler); // set the object to GameState()
        menuState = new MenuState(handler);

        State.setState(gameState); // set the current state to gameState
    }


    private void update(){ // updates our game after one game loop
        keyManager.update();

        if(State.getState() != null){ //if state exists
            State.getState().update(); // update it
        }
    }
    private void render(){ // drawing method
        // buffer is a hidden screen which prevent flicking
        bs = display.getCanvas().getBufferStrategy(); // object get display canvas; how many buffers we are going to use
        if (bs == null){ // if canvas has non buffers
            display.getCanvas().createBufferStrategy(3); // set num of buffers to 3
            return;
        }

        g = bs.getDrawGraphics();

        // clear screen
        g.clearRect(0, 0 , width , height);

        //draw graphics

        if(State.getState() != null){ // if state exists
            State.getState().render(g); // render it
        }
        //show graphics
        bs.show();
        g.dispose();
    }

    public void run() {
        init(); // call our display/img etc

        int fps = 50; // how many times a second should it refresh
        double timePerUpload = 1_000_000_000 / fps; // in one second how many times we want to refresh
                                                  // 1 bilion nanosecond = 1 second, because more specific'
        double delta = 0; //
        long now; //
        long lastTime = System.nanoTime(); // returns time of our game

        //loop which will update and render
        while(running){ // as long as running equals true
            now = System.nanoTime(); // 2. Set to time after loop
            delta += (now - lastTime) / timePerUpload;

            lastTime = now; // 1. Takes time before loop

            if ( delta >= 1) {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

//    synchronized - when working with thread directly, so when starting or stopping it will not mess up
    public synchronized void start() { // starts thread;
        if(running) // check if the game is running (if running equals true)
            return;
        running = true;
        thread = new Thread(this); // initialize the thread; this is applying to Game class
        thread.start(); // call run method
    }

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
}
