package main;
import javax.swing.JFrame;
import java.awt.*;

public class Display {

    private  JFrame frame;
    private  Canvas canvas; //its like a layer on jframe on which we can draw
    //it would not be reachable because its private, but we created public method getCanvas

    // variables which will describe frame
    private String title;
    private int width, height;

    public Display(String title, int width, int height){ // initialize a constructor with parameters
        // class variables are the same as parameters so we have to use "this"
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay(); //call createDisplay method
    }

    private void createDisplay(){ //creates the window
        frame = new JFrame(title); //create an object - frame
        frame.setSize(width, height); //set size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //window will close properly
        frame.setResizable(false); // stick with its size
        frame.setLocationRelativeTo(null); // appears in the middle of the screen
        frame.setVisible(true); //default it is false

        canvas = new Canvas(); // create object
        canvas.setPreferredSize(new Dimension(width, height)); // size of out game

        //make sure its stays on parameteres we gave it
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));

        frame.add(canvas); // add the canvas to our frame
        frame.pack(); //resize a window so we can see the whole canvas
    }

    public  Canvas getCanvas() {
        return canvas;
    }

    public  JFrame getFrame() {
        return frame;
    }
}