package states;

import main.Handler;
import main.State;
import world.Assets;

import java.awt.*;

public class InstructionState extends State {

    private int policemanX, policemanY, cloudX, cloudY, cloudSizeX, cloudSizeY, blankX, blankX2,
            lightsX, lightsY;
    private int cloudNumber, lightNumber;
    private boolean speaking, moving, setted, lightsOn, timeChange;
    private long timer, timer2, timer3, timer4, timer5, timer6;

    public InstructionState(Handler handler) {
        super(handler);

        speaking = false;
        moving = true;
        setted = true;
        timeChange = false;

        policemanX = 0;
        policemanY = 950;

        cloudX = 230;

        cloudNumber = 1;
        cloudSizeX = 600;
        cloudSizeY = 800;

        blankX = 280;
        blankX2 = 280;

        lightsX = 300;
        lightsY = 300;
        lightNumber = 0;

    }

    @Override
    public void update() {

        if (handler.getKeyManager().escape) {
            handler.getGame().reloadMenuState();
            State.setState(handler.getGame().gameState);
        }

        if (setted) {
            timer = System.currentTimeMillis();
            timer3 = System.currentTimeMillis();
            setted = false;
        }

        if (handler.getKeyManager().escape) {
            handler.getGame().reloadMenuState();
            State.setState(handler.getGame().gameState);
        }

        if (!moving) {
            if (System.currentTimeMillis() - timer > 100)
                if (speaking) {

                    setSpeaking(false);
                    timer = System.currentTimeMillis();

                } else {
                    setSpeaking(true);
                    timer = System.currentTimeMillis();
                }
        }

        if (System.currentTimeMillis() - timer3 < 4000) {
            policemanY -= 2;
            cloudY = policemanY - 360;
        } else if (System.currentTimeMillis() - timer3 > 5000 && System.currentTimeMillis() - timer3 < 5500) {
            moving = false;
            timer2 = System.currentTimeMillis();
        }

        if (!moving && blankX < 749) {
            blankX += 2.5;
        } else if ((!moving && blankX2 < 748)) {
            blankX2 += 2.5;

            if (cloudNumber == 2 && blankX2 > 510)
                blankX2 = 748;

            if (cloudNumber == 3 && blankX2 > 650)
                blankX2 = 748;

        } else if (!moving && cloudNumber != 5) {
            cloudNumber += 1;
            if (cloudNumber != 5)
                resetBlanks();
            else {
                speaking = false;
                timer4 = System.currentTimeMillis();
            }
        }
        if (System.currentTimeMillis() - timer4 < 5500 && System.currentTimeMillis() - timer4 > 4000 && cloudNumber == 5) {

            moving = true;
            policemanY += 4;
            timer5 = System.currentTimeMillis();

        }

        if (System.currentTimeMillis() - timer5 > 1300 && System.currentTimeMillis() - timer5 < 2000 && cloudNumber == 5) {
            lightsOn = true;
            lightNumber += 1;
            timer5 = System.currentTimeMillis();
            if (lightNumber == 5)
                State.setState(handler.getGame().gameState);
        }

        if (cloudNumber == 4 && blankX > 550 && blankX < 747 ) {

            if (System.currentTimeMillis() - timer6 > 100) {

                if (!timeChange) {
                    timeChange = true;
                    timer6 = System.currentTimeMillis();
                } else {
                    timeChange = false;
                    timer6 = System.currentTimeMillis();
                }
            }

        }

        if (blankX > 747) {
            timeChange = false;
        }

    }

    @Override
    public void render(Graphics g) {

        //background
        g.drawImage(Assets.black, 0, 0, null);

        if (!timeChange)
            g.drawImage(Assets.instructions, 0, 0, 835, 795, null);
        else
            g.drawImage(Assets.instructions2, 0, 0, 835, 795, null);


        //speaking animation
        if (getSpeaking() || cloudNumber > 6)
            g.drawImage(Assets.policeman2, policemanX, getPolicemanY(), 300, 300, null);
        else
            g.drawImage(Assets.policeman22, policemanX, getPolicemanY(), 300, 300, null);

        //cloud changing
        if (!moving) {
            if (getCloudNumber() == 1)
                g.drawImage(Assets.cloud1, cloudX, cloudY, cloudSizeX, cloudSizeY, null);

            if (getCloudNumber() == 2)
                g.drawImage(Assets.cloud2, cloudX, cloudY, cloudSizeX, cloudSizeY, null);

            if (getCloudNumber() == 3)
                g.drawImage(Assets.cloud3, cloudX, cloudY, cloudSizeX, cloudSizeY, null);

            if (getCloudNumber() == 4)
                g.drawImage(Assets.cloud4, cloudX, cloudY, cloudSizeX, cloudSizeY, null);

            if (getCloudNumber() == 5)
                g.drawImage(Assets.cloud5, cloudX, cloudY, cloudSizeX, cloudSizeY, null);
        }

        //lights changing
        if (lightsOn) {
            if (lightNumber == 1) {
                g.drawImage(Assets.light1, lightsX, lightsY, null);
            }

            if (lightNumber == 2)
                g.drawImage(Assets.light2, lightsX, lightsY, null);

            if (lightNumber == 3)
                g.drawImage(Assets.light3, lightsX, lightsY, null);

            if (lightNumber == 4)
                g.drawImage(Assets.light4, lightsX, lightsY, null);
        }

        if (!moving) {

            g.setColor(new Color(255, 255, 255));

            g.fillRect(blankX, 370, 500, 50);
            g.fillRect(blankX2, 420, 500, 50);

            g.setColor(new Color(0, 0, 0));
            g.fillRect(750, 360, 270, 120);
        }

        g.setColor(new Color(255, 255, 255));
        g.drawString("press 'esc' to skip", 720, 820);

    }

    public void resetBlanks() {
        blankX = 280;
        blankX2 = 280;
    }

    public int getPolicemanY() {
        return policemanY;
    }

    public void setSpeaking(boolean speaking) {
        this.speaking = speaking;
    }

    public boolean getSpeaking() {
        return speaking;
    }

    public int getCloudNumber() {
        return cloudNumber;
    }

}
