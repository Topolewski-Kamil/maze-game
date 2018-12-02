package states;

import main.Game;
import main.Handler;
import main.State;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import world.Assets;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class MenuState extends State {

    private UIManager uiManager;
    Clip clip = null; //music 1
    static Clip clip2 = null; //music 2
    private static boolean gameStarted = false; //check if previous game was started

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        //main menu music beginning 1
        File Music = new File("res1/sounds/Im a Wanted Man.wav");
        playMusic(Music, clip);
        //main menu music end 1

        //continue button
        if(gameStarted){
            uiManager.addObject(new UIImageButton(250, 200, 228 * 2, 34 * 2, Assets.btn_continue, new ClickListener() {
                @Override
                public void onClick() {
                    clip2.start();
                    clip2.loop(Clip.LOOP_CONTINUOUSLY);
                    handler.getMouseManager().setUIManager(null);
                    State.setState(handler.getGame().gameState);
                    clip.stop();//stop music 1
                }
            })); }
        //end of continue button

        //start button
        uiManager.addObject(new UIImageButton(250, 300, 228 * 2, 34 * 2, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().reloadGameState();
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
                clip.stop();//stop music 1

                //Play screen music beginning 2
                try {
                    File sound = new File("res1/sounds/catchme.wav");
                    AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
                    clip2 = AudioSystem.getClip();
                    clip2.open(ais);
                    clip2.start();
                    clip2.loop(Clip.LOOP_CONTINUOUSLY);
                }catch (Exception e){System.out.println(e);
                }
                //Play screen music end 2
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
    }
    @Override
    public void update() {
        uiManager.tick();
    }
    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
    public static void GameStarted()
    {
        gameStarted = true;
    }

    public void playMusic(File sound,Clip wave){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            wave = AudioSystem.getClip();
            wave.open(ais);
            wave.start();
            wave.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (Exception e){System.out.println(e);
        }
        clip = wave;
    }
}
