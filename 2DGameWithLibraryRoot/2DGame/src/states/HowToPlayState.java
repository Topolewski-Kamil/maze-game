package states;

import main.Handler;
import main.State;
import readersLoaders.ImageLoader;
import world.Assets;
import java.security.SecureRandom;
import java.awt.*;
/**
 * Class which is a state and explains how to play
 */
public class HowToPlayState extends State {

	private Color a = Color.BLACK;
	private SecureRandom random = new SecureRandom();

	public HowToPlayState(Handler handler) {
		super(handler);

	}

	@Override
	public void update() {
		if (handler.getKeyManager().escape) {
			handler.getGame().reloadMenuState();
			State.setState(handler.getGame().menuState);
		}
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		a = new Color(r, g, b);
	}

	@Override
	public void render(Graphics g) {
		Color yellowish = new Color(255, 193, 7);
		Color orangeish = new Color(255, 109, 0);

		g.drawImage(Assets.background, 0, 0, null);

		Font fnt0 = new Font("arial", Font.BOLD, 65);
		Font fnt1 = new Font("arial", Font.BOLD, 15);

		int y = 140;
		int x = 60;
		int shift = 20;
		int xShift = 70;

		String headlineOfScoreBoard = "How to play: ";
		g.setFont(fnt0);
		g.setColor(orangeish);
		g.drawString(headlineOfScoreBoard, x + 150, y - 50);
		g.setColor(yellowish);
		g.drawString(headlineOfScoreBoard, x + 160, y - 50);

		g.setFont(fnt1);
		g.setColor(a);
		g.drawString("    'TO ALL UNITS: a thief robbed the bank of Pixelville city and is currently running wild.'", x,
				y + shift - 20);
		shift += 15;

		String text[] = new String[3];

		text[0] = "You are the only policeman in the area who can prevent this criminal from getting away. The";
		text[1] = "lawbreaker must be caught and brought to justice at police station. But be quick! The thief";
		text[2] = "might find the way out of Pixelville.";

		for (int i = 0; i < text.length; i++) {

			g.setColor(Color.black);
			g.drawString(text[i], x, y + shift);
			shift += 15;
		}
		shift += 10;

		g.drawImage(Assets.speedUp, x, y + shift, null);
		g.drawString("Donuts: they give all policemen power to work. Eating a donut increases your speed", x + xShift,
				y + shift + 20);
		g.drawString("for 3 seconds. Powerup reappears after 6 seconds.", x + xShift, y + shift + 45);
		shift += 70;

		g.drawImage(Assets.eagleEye, x, y + shift, null);
		g.drawString("Pizza: according to American scientists pizza improves your sight. Eating a pizza", x + xShift,
				y + shift + 20);
		g.drawString("increases range of your sight for 3 second. Powerup reappears after 6 seconds.", x + xShift,
				y + shift + 45);
		shift += 70;

		g.drawImage(Assets.policeStation, x, y + shift, null);
		g.drawString("To win you have to catch the thief before the time runs out and then bring him", x + xShift,
				y + shift + 20);
		g.drawString("out of the maze through the police station.", x + xShift, y + shift + 45);
		shift += 70;
		
		g.drawImage(Assets.coin, x, y + shift, 50, 50, null);
		g.drawString("The thief is a weenie and drops his loot behind him. Use them to track him down!", x + xShift,
				y + shift + 30);

		g.drawImage(ImageLoader.loadImage("/textures1/directions.png"), 680, 480, 150, 180, null);
		
		g.drawImage(Assets.logoSmall, 100, 560, null);
		
		g.drawString("press 'esc' to get back to the menu", 550, 800);

	}
}