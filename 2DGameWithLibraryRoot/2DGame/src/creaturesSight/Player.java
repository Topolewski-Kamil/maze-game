package creaturesSight;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

import main.Handler;
import powerUps.EagleEyeEntity;
import powerUps.SpeedUpEntity;
import world.Assets;

/**
 * Class that draws Player and clouds communicates to the screen, and gets user
 * keyboard input.
 */
public class Player extends Creature {

    private ArrayList<Integer> arrCh = new ArrayList<>();

    // variable to control how long should the clouds communicates be displayed
    private long cloudTimer, cloudExitTimer;

    // whether player have already tried to to exit the maze
    private boolean triedToExit = false;

    // whether player have left the maze
    private boolean leftTheMaze = false;

    //cheats enabled
    static boolean cheatsEnabled = false;

    float getPlayerX() {
        return x;
    }

    float getPlayerY() {
        return y;
    }

    /**
     * Constructor that takes starting position(x & y) of the Player And passes them
     * on to the Creatures classes constructor
     *
     * @param handler
     * @param x
     * @param y
     */
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = -22;
        bounds.y = -28;
        bounds.width = 30;
        bounds.height = 50;
    }

    @Override
    public void update() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        cheats();
    }

    /**
     * enabling cheats when right combination is inserted
     */
    public void cheats() {
        if (arrCh.size() > 7)
            if (arrCh.get(0) == 0)
                if (arrCh.get(1) == 1)
                    if (arrCh.get(2) == 3)
                        if (arrCh.get(3) == 2)
                            if (arrCh.get(4) == 3)
                                if (arrCh.get(5) == 2)
                                    if (arrCh.get(6) == 0)
                                        if (arrCh.get(7) == 1) {
                                            cheatsEnabled = true;
                                            Creature.speed = 10.0f;
                                            Sight.setSize(0);
                                        }
        if (cheatsEnabled)
            if (handler.getKeyManager().unable) {
                cheatsEnabled = false;
                Creature.speed = DEFAULT_SPEED;
                Sight.setSize(2500);
            }




    }

    /**
     * Method which gets user input and depending on that sets its x or y movement
     * to its speed.
     */
    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up || handler.getKeyManager().up2) {
            yMove = -speed;

            if (arrCh.size() == 0) {
                arrCh.add(0);
            } else {
                if (arrCh.size() == 8) {
                    arrCh.remove(0);
                }
                if (arrCh.get(arrCh.size() - 1) != 0) {
                    arrCh.add(0);
                }
            }

        }
        if (handler.getKeyManager().down || handler.getKeyManager().down2) {
            yMove = speed;
            if (arrCh.size() == 0) {
                arrCh.add(1);
            } else {

                if (arrCh.size() == 8) {
                    arrCh.remove(0);
                }

                if (arrCh.get(arrCh.size() - 1) != 1) {
                    arrCh.add(1);
                }
            }
        }
        if (handler.getKeyManager().right || handler.getKeyManager().right2) {
            xMove = speed;
            if (arrCh.size() == 0) {
                arrCh.add(2);
            } else {

                if (arrCh.size() == 8) {
                    arrCh.remove(0);
                }

                if (arrCh.get(arrCh.size() - 1) != 2) {
                    arrCh.add(2);
                }
            }
        }
        if (handler.getKeyManager().left || handler.getKeyManager().left2) {
            xMove = -speed;
            if (arrCh.size() == 0) {
                arrCh.add(3);
            } else {

                if (arrCh.size() == 8) {
                    arrCh.remove(0);
                }

                if (arrCh.get(arrCh.size() - 1) != 3) {
                    arrCh.add(3);
                }
            }
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    /**
     * Method that displays player image and clouds communicates on the screen.
     * Depending on user input it's drawing it from different sides.
     */
    @Override
    public void render(Graphics g) {
        // down
        if (Creature.isCaught()) {
            g.drawImage(Assets.thiefCaught, (int) (x - 30 - handler.getGameCamera().getxOffset()),
                    (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
            // down
        } else if (handler.getKeyManager().down || handler.getKeyManager().down2)  {
            g.drawImage(Assets.policeFront, (int) (x - 30 - handler.getGameCamera().getxOffset()),
                    (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
            // right
        } else if (handler.getKeyManager().right || handler.getKeyManager().right2) {
            g.drawImage(Assets.policeRight, (int) (x - 30 - handler.getGameCamera().getxOffset()),
                    (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
            // left
        } else if (handler.getKeyManager().left || handler.getKeyManager().left2) {
            g.drawImage(Assets.policeLeft, (int) (x - 30 - handler.getGameCamera().getxOffset()),
                    (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
            // up
        } else if (handler.getKeyManager().up || handler.getKeyManager().up2) {
            g.drawImage(Assets.policeBack, (int) (x - 30 - handler.getGameCamera().getxOffset()),
                    (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
            // not moving
        } else {
            g.drawImage(Assets.policeFront, (int) (x - 30 - handler.getGameCamera().getxOffset()),
                    (int) (y - 32 - handler.getGameCamera().getyOffset()), width, height, null);
        }

        if (!Creature.isCaught()) {
            if (Creature.triedDoored()) {
                if (!triedToExit) {
                    cloudTimer = System.currentTimeMillis();
                    triedToExit = true;
                }
                if (System.currentTimeMillis() - cloudTimer < 5000) {
                    g.drawImage(Assets.cloud, (int) (x - 50 - handler.getGameCamera().getxOffset()),
                            (int) (y - 120 - handler.getGameCamera().getyOffset()), null);
                }
            }
        }
        if (Creature.isCaught()) {
            if (!leftTheMaze) {
                cloudExitTimer = System.currentTimeMillis();
                leftTheMaze = true;
            }
            if (System.currentTimeMillis() - cloudExitTimer < 5000) {
                g.drawImage(Assets.cloudExit, (int) (x - 50 - handler.getGameCamera().getxOffset()),
                        (int) (y - 120 - handler.getGameCamera().getyOffset()), null);
            }
        }
    }
}