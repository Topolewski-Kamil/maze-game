package states;

import main.Display;
import main.Handler;
import main.State;
import ui.UIManager;
import java.awt.*;

public class HowToPlayState extends State {

    private UIManager uiManager;
    private Display display;

    public HowToPlayState(Handler handler, Display display) {
        super(handler);
        this.display = display;

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {

    }
}
