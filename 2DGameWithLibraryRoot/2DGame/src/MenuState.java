/* import java.awt.*;

public class MenuState extends State {

    //Game cls = new Game(, , );
    //cls.setTeacherName(newTeacherName);

    public MenuState( Handler handler ) {
        super(handler);

    }

    public void update() {

    }

    public Rectangle playButton = new Rectangle( 900/2 + 120 , 150, 100 , 50);
    public Rectangle settingsButton = new Rectangle( 900/2 + 120 , 250, 100 , 50); // for each button just add to y + 100
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("MAZE GAME",900/ 2, 100); // change x to getWidth and getHeight method in Game.java class

        Font fnt1 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x + 19, playButton.y + 30);
        g2d.draw(playButton);
        g.drawString("SETTINGS", settingsButton.x + 19, settingsButton.y + 30);
        g2d.draw(settingsButton);
    }
}
*/
import javax.swing.*;
import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        //Button play = new Button("Click me");
        //uiManager.addObject(new UIImageButton(200, 200, 128, 64, , new ClickListener() {

      /*  Button play = new Button("EXIT");
        play.addActionListener(new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().getGameState());
            }
        }  ); */
    }

    @Override
    public void update() {

    }

    //@Override
    public void tick() {
        uiManager.tick();

        // Temporarily just go directly to the GameState, skip the menu state!
        handler.getMouseManager().setUIManager(null);
        State.setState(handler.getGame().getGameState());
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

}