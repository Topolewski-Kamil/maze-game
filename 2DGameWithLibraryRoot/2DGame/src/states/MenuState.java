package states;
import java.awt.*;
import main.Handler;
import main.State;

public class MenuState extends State {

    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void update() {
        if(handler.getMouseManager().isLeftPressed() )
        { State.setState(handler.getGame().gameState);}
    }

    //public Rectangle playButton = new Rectangle( handler.getWidth()/2 + 120 , 150, 100 , 50);
    public Rectangle playButton = new Rectangle( handler.getWidth()/2 + 120 , 150, 300 , 50);
    public Rectangle settingsButton = new Rectangle( handler.getWidth()/2 + 120 , 250, 300 , 50); // for each button just add to y + 100

    @Override
    public void render(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;

            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);
            g.setColor(Color.black);
            g.drawString("MAZE GAME",handler.getWidth()/ 2, 100); // x is getWidth()

            Font fnt1 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt1);
            g.drawString("PLAY", playButton.x + 19, playButton.y + 45);
            g2d.draw(playButton);
            g.drawString("SETTINGS", settingsButton.x + 19, settingsButton.y + 45);
            g2d.draw(settingsButton);

        g.setColor(Color.RED);
        g.fillRect(handler.getMouseManager().getMouseX() , handler.getMouseManager().getMouseY(), 8, 8);
    }
}
