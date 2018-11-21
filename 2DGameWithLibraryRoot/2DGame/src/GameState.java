import java.awt.*;

public class GameState extends State {

    private Player player;
    private World world;
    private Creature c1;

    public GameState( Handler handler ){ // constructor
        super(handler);
        world = new World(handler,"res1/maps/map0.txt");
        handler.setWorld(world);
        player = new Player( handler ,64, 64);
    }

    public void update(){
        world.update();
        player.update();
    }

    //draws things in this state
    public void render(Graphics g){

        world.render(g);
        player.render(g);


    }
}
