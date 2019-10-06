package states;

import main.Handler;
import main.State;
import world.Assets;
import java.awt.*;

import static states.HighScore.loadScores;

/**
 * Class that is a state in the game, in which top 5 high scores are displayed
 */
public class HighScoreState extends State {

	public HighScoreState(Handler handler) {
		super(handler);
		loadScores();
	}

	@Override
	public void update() {
		loadScores();
		if (handler.getKeyManager().escape) {
			handler.getGame().reloadMenuState();
			State.setState(handler.getGame().menuState);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.background, 0, 0, null);

		String headlineOfScoreBoard = "Hall of fame";
		String bodyOfScoreBoard = "Username:Score";
		Font fnt0 = new Font("arial", Font.BOLD, 70);
		Font fnt1 = new Font("arial", Font.BOLD, 20);

		g.setFont(fnt0);
		int textY = 70;
		int textX = 250;

		g.setColor(Color.BLACK);
		g.drawString(headlineOfScoreBoard, textX, textY + 40);
		g.setColor(Color.white);
		g.drawString(headlineOfScoreBoard, textX - 5, textY + 45);

		g.setFont(fnt1);
		g.setColor(Color.black);
		g.drawString(bodyOfScoreBoard, textX + 100, textY + 130);

		loadScores();
		HighScore.countScore(g);
		g.setFont(fnt1);
		g.drawString("press 'esc' to get back to the menu", 530, 850);
	}
}