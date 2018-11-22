import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;
    private Creature c1;
    private Bot bot;
	private PowerUpEntity e;
	private Sight s;

	public GameState(Handler handler) { // constructor
		super(handler);
		world = new World(handler, "res1/maps/map0.txt");
		handler.setWorld(world);
		player = new Player(handler, 64, 64);
		e = new PowerUpEntity(handler, 100, 100);
		s = new Sight(handler, 100, 100);

        bot = new Bot( handler ,64, 64);
    }

	public void update() {
		world.update();
		e.update();
		player.update();
		s.update();
		bot.update();

	}

    //draws things in this state
    public void render(Graphics g){

		world.render(g);
		e.render(g);
		s.render(g);
		player.render(g);
		bot.render(g);

    }
}
