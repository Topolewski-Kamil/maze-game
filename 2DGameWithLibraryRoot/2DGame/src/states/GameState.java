package states;
import java.awt.*;
import java.io.File;

import Bot.Bot;
import creaturesSight.Player;
import creaturesSight.Sight;
import jdk.internal.util.xml.impl.Input;
import main.Handler;
import main.State;
import powerUps.EagleEyeEntity;
import powerUps.SpeedUpEntity;
import world.World;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameState extends State {

	private Player player;
	private World world;
	private SpeedUpEntity e;
	private Sight s;
	private EagleEyeEntity eagle;
	private Bot bot;

	public GameState(Handler handler) { // constructor
		super(handler);
		world = new World(handler, "res1/maps/map0.txt");
		handler.setWorld(world);
		player = new Player(handler, 64, 64);
		e = new SpeedUpEntity(handler, 100, 100);
		s = new Sight(handler, 100, 100);
		eagle = new EagleEyeEntity(handler, 100, 100);
		bot = new Bot(handler, 64, 64);
	}

	public void update() {
		world.update();
		e.update();
		player.update();
		s.update();
		eagle.update();
		bot.update();
		if(handler.getKeyManager().escape)
		{
			MenuState.clip2.stop();//stop music 2
			MenuState.GameStarted();
			handler.getGame().reloadMenuState();
			State.setState(handler.getGame().menuState);
		}
	}

	// draws things in this state
	public void render(Graphics g) {
		world.render(g);
		e.render(g);
		eagle.render(g);
		bot.render(g);
		s.render(g);
		player.render(g);
	}
}
