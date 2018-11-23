package creaturesSight;
import java.awt.*;

import main.Handler;
import world.Assets;

public class Player extends Creature {
	

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = -17;
        bounds.y = -28;
        bounds.width = 30;
        bounds.height = 50;
    }

    public void update(){
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if ( handler.getKeyManager().up ){
            yMove = -speed;
        }
        if ( handler.getKeyManager().down ){
            yMove = speed;
        }
        if ( handler.getKeyManager().right ){
            xMove = speed;
        }
        if ( handler.getKeyManager().left ){
            xMove = -speed;
        }
    }
    public float getX() {
    	return x;
    }
    public float getY() {
    	return y;
    }

    public void render(Graphics g) {
        g.drawImage(Assets.policeFront, (int) (x - 30 - handler.getGameCamera().getxOffset()), (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
       
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }
}
