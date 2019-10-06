package states;

import java.awt.*;
import bot.Bot;
import creaturesSight.Player;
import creaturesSight.Sight;
import main.Display;
import main.Handler;
import main.State;
import powerUps.EagleEyeEntity;
import powerUps.SpeedUpEntity;
import world.World;
import javax.swing.*;

/**
 * Class which draws all the images when the game mode is selected - tiles, creatures and
 * power ups.
 */
public class GameState extends State {

	private Player player;
	public World world;
	private SpeedUpEntity e;
	private Sight s;
	private EagleEyeEntity eagle;
	private Bot bot;

	private static int currentFPS = 0;
	public static int Fps; // Actual frames per second
	private static long start = 0;
	public static int levelTime; // time in seconds

	public GameState(Handler handler, Display display) { // constructor
		super(handler);
		world = new World(handler, "res1/maps/map0.txt");
		handler.setWorld(world);
		player = new Player(handler, 64, 64);
		e = new SpeedUpEntity(handler, 100, 100);
		s = new Sight(handler, 100, 100, player);
		eagle = new EagleEyeEntity(handler, 100, 100);
		bot = new Bot(handler, 64, 64);
		levelTime = 151; // 120 seconds = 2 minutes
		MenuState.GameStarted();
	}

	public void update() {
		if (SpeedUpEntity.isEaten()) {
			world.updateSpeedUp();
		}
		if (EagleEyeEntity.isEaten()) {
			world.updateEagleEye();
		}
		e.update();
		player.update();
		s.update();
		eagle.update();
		bot.update();

//		System.out.println(MenuState.gameStarted);
		if (handler.getKeyManager().escape && MenuState.gameStarted) {
			escapePressed();
		}
		updateFps();
		if (levelTime == 0) {
			ranOutOfTime();
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
		drawTime(g);
	}

	public static void updateFps() {
		currentFPS++;
		if (System.currentTimeMillis() - start >= 1000) {
			levelTime -= 1; // We deduct 1 second, every second.
			Fps = currentFPS;
			currentFPS = 0;
			start = System.currentTimeMillis();
		}
	}

	public void drawTime(Graphics g) {
		Font fnt0 = new Font("arial", Font.BOLD, 70);
		g.setFont(fnt0);
		int x = 450;
		int y = 80;
		g.setColor(Color.black);
		g.drawString(Integer.toString(levelTime), x, y);
		g.setColor(new Color(198, 226, 255));
		g.drawString(Integer.toString(levelTime), x + 2, y + 2);
	}

	public void ranOutOfTime() {
		Object[] options = { "Go back to main menu", "Quit" };
		JPanel panel = new JPanel();
		panel.add(new JLabel("You ran out of time! "));
		int result = JOptionPane.showOptionDialog(null, panel, "You lost!", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);
		if (result == JOptionPane.YES_OPTION || result == JOptionPane.CLOSED_OPTION) {
			handler.getGame().reloadMenuState();
			State.setState(handler.getGame().menuState);
		}
		if (result == JOptionPane.NO_OPTION) {
			System.exit(0);
		}
	}

	private void escapePressed() {
		// MenuState.GameStarted();
		handler.getGame().reloadMenuState();
		State.setState(handler.getGame().menuState);
	}

}