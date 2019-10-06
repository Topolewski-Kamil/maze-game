package states;

import main.Handler;
import main.State;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import world.Assets;
import javax.swing.*;
import creaturesSight.Creature;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import static states.HighScore.loadScores;
/**
 * State which first appear when the game is run. It directs to other states.
 */
public class MenuState extends State {

    private UIManager uiManager;
    public static boolean gameStarted = false; //check if previous game was started
    public static String playerName;
	private final int IMG_X = 295;
	private final int IMG_WIDTH = 240;
	private final int IMG_HEIGHT = 78;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		// continue button
		if (gameStarted) {
			uiManager.addObject(new UIImageButton(IMG_X, 420, IMG_WIDTH, IMG_HEIGHT, Assets.btn_continue, new ClickListener() {
				@Override
				public void onClick() {
					handler.getMouseManager().setUIManager(null);
					State.setState(handler.getGame().gameState);
				}
			}));
		}
		// end of continue button

		if (!gameStarted) {
			uiManager.addObject(new UIImageButton(IMG_X, 420, IMG_WIDTH, IMG_HEIGHT, Assets.btn_reset, new ClickListener() {
				@Override
				public void onClick() {
					int result = JOptionPane.showConfirmDialog(null, "Are your sure ? " +
									"This will reset scores",
							"Alert", JOptionPane.OK_CANCEL_OPTION);
					if (result == JOptionPane.OK_OPTION){
					try {
                        File file = new File("res1/maps/scores.txt");
                        FileWriter fileWriter = new FileWriter(file, false); // true to append
                        // false to overwrite.
                        fileWriter.write("Name1 10\n" +
                                "Name2 20\n" +
                                "Name3 30\n" +
                                "Name4 40\n" +
                                "Name5 50");
                        fileWriter.close();
                        loadScores();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
				}
				}
			}));
		}

		// start button
		uiManager.addObject(new UIImageButton(IMG_X, 320, IMG_WIDTH, IMG_HEIGHT, Assets.btn_start, new ClickListener() {
			@Override
			  public void onClick() {
				Creature.setCaught(false);
                JTextField username = new JTextField();
                Object[] message = {
                        "Username (No spaces allowed & Max 12 Characters) : ", username,
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Choose name", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    if (!(username.getText().matches("\\s+?")) && username.getText().length() <= 12 &&
                            !(username.getText().matches(""))) {
                        playerName = username.getText();
                        handler.getGame().reloadGameState();
                        handler.getMouseManager().setUIManager(null);
                        State.setState(handler.getGame().instructionState);
//                        MenuState.GameStarted();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Sorry, this "
                                        + "isn't a valid username.\n"
                                        + "Please enter "
                                        + "valid name.",
                                "Try again",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }));
		// end of start button

		// quit button
		uiManager.addObject(new UIImageButton(IMG_X, 720, IMG_WIDTH, IMG_HEIGHT, Assets.btn_quit, new ClickListener() {
			@Override
			public void onClick() {
				System.exit(0);
			}
		}));
		// end of quit button

		// How to play button
		uiManager.addObject(new UIImageButton(IMG_X, 620, IMG_WIDTH, IMG_HEIGHT, Assets.btn_help, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().howToPlayState);
			}
		}));
		// end of How to play button

		// High Scores button
		uiManager.addObject(new UIImageButton(IMG_X, 520, IMG_WIDTH, IMG_HEIGHT, Assets.btn_highscore, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().highScoreState);
			}
		}));
		// end of High Scoresbutton
	}

	@Override
	public void update() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.background, 0, 0, null);
		g.drawImage(Assets.logoBig, 25, 35, null);
		uiManager.render(g);
		

	}

	public static void GameStarted() {
		gameStarted = true;
	}
}