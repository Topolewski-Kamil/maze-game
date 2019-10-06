package states;

import main.Handler;
import main.State;
import world.Assets;

import java.awt.*;

public class InstructionState extends State {

    private int policemanX, policemanY, cloudX, cloudY, cloudSizeX, cloudSizeY;
    private int cloudNumber;
    private boolean speaking, moving, setted;
    private long timer, timer2, timer3;

    public InstructionState(Handler handler) {
        super(handler);

        speaking = false;
        moving = true;
        setted = true;

        policemanX = 0;
        policemanY = 950;

        cloudX = 230;


        cloudNumber = 1;
        cloudSizeX = 600;
        cloudSizeY = 800;

    }

    @Override
    public void update() {

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

        if (System.currentTimeMillis() - timer2 > 5000) {
            cloudNumber += 1;
            timer2 = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - timer3 < 4000) {
            policemanY -= 2;
            cloudY = policemanY - 330;
        } else if (System.currentTimeMillis() - timer3 == 5000) {
            moving = false;
            timer2 = System.currentTimeMillis();
        }


    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.black, 0, 0, null);


        g.drawImage(Assets.instructions, 0, 0, 835, 795, null);
        if (getSpeaking())
            g.drawImage(Assets.policeman2, policemanX, getPolicemanY(), 300, 300, null);
        else
            g.drawImage(Assets.policeman22, policemanX, getPolicemanY(), 300, 300, null);

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

    public void setPoliceman(boolean policeman) {
        this.speaking = policeman;
    }

    public boolean isSpeaking() {
        return speaking;
    }

    public int getCloudNumber() {
        return cloudNumber;
    }

    public void setCloudNumber(int cloudNumber) {
        this.cloudNumber = cloudNumber;
    }
}
