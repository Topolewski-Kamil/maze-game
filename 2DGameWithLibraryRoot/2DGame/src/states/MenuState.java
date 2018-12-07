package states;

import main.Display;
import main.Game;
import main.Handler;
import main.State;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import world.Assets;

import javax.swing.*;
import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    private static boolean gameStarted = false; //check if previous game was started
    private Display display;
    String playerName;

    public MenuState(Handler handler , Display display) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        //screen changes begin
        this.display = display;
        display.getFrame().getContentPane().setBackground( Color.red );
        //screen changes end

        //main menu music beginning 1
        MusicPlayer player = new MusicPlayer("Im a Wanted Man");
        //player.run();
        //main menu music end 1
        //continue button
        if(gameStarted){
            uiManager.addObject(new UIImageButton(250, 200, 228 * 2, 34 * 2, Assets.btn_continue, new ClickListener() {
                @Override
                public void onClick() {
                    handler.getMouseManager().setUIManager(null);
                    State.setState(handler.getGame().gameState);
                }
            })); }
        //end of continue button

        //start button
        uiManager.addObject(new UIImageButton(250, 300, 228 * 2, 34 * 2, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                playerName = JOptionPane.showInputDialog ("Please enter your username.", "Name");
                handler.getGame().reloadGameState();
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
        //end of start button

        //quit button
        uiManager.addObject(new UIImageButton(250, 500, 228 * 2, 34 * 2, Assets.btn_quit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
        //end of quit button


        //How to play button
        uiManager.addObject(new UIImageButton(250, 600, 228 * 2, 34 * 2, Assets.btn_quit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
        //end of How to play button

        //High Scores button
        uiManager.addObject(new UIImageButton(250, 700, 228 * 2, 34 * 2, Assets.btn_quit, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().highScoreState);
            }
        }));
        //end of High Scoresbutton
    }
    @Override
    public void update() {
        uiManager.tick();
    }
    @Override
    public void render(Graphics g ) {
        uiManager.render(g);
    }
    public static void GameStarted()
    {
        gameStarted = true;
    }
}
