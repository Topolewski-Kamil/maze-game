import javax.swing.plaf.nimbus.State;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    private State gameState;
    private State menuState;



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        /*
        public Rectangle playButton = new Rectangle( 900/2 + 120 , 150, 100 , 50);
        public Rectangle settingsButton = new Rectangle( 900/2 + 120 , 250, 100 , 50); // for each button just add to y + 100
        */
        //Play Button
        if(mx >= 900 /2 + 120 && mx <= 900 /2 + 220){
            if(my >= 250 && my <= 300){
                //pressed Play button
                //System.out.println("-You have done: ");
                //State.setState(menuState);
                 // Game.state = gameState;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
