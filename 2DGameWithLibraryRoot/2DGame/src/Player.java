import java.awt.*;

public class Player extends Creature {

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 10;
        bounds.y = 5;
        bounds.width = 50;
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

    public void render(Graphics g) {
    	 if (this.isEaten() == true) {
             g.drawImage(Assets.grass, (int) (260 - handler.getGameCamera().getxOffset()), (int) (68 - handler.getGameCamera().getyOffset()), 60, 60, null);
         }
        g.drawImage(Assets.policeFront, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
       
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }
}
