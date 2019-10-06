package main;

import javax.swing.JFrame;
import java.awt.*;

/**
 * Handles creation of the display
 */
public class Display {

	/**
	 * frame that's the window canvas a layer on jframe on which we can draw it
	 * would not be reachable because its private, but we created public method
	 * getCanvas
	 */

	private JFrame frame;
	private Canvas canvas;

	// variables which will describe frame
	private String title;
	private int width, height;

	/**
	 * variables which describe frame
	 * 
	 * @param title  title of the display
	 * @param width  width of the display
	 * @param height height of the display
	 *
	 *               Initialize a constructor with parameters Class variables are
	 *               the same as parameters so we have use "this" Calls
	 *               createDisplay method
	 */

	public Display(String title, int width, int height) { // initialize a constructor with parameters
		// class variables are the same as parameters so we have to use "this"
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay(); // call createDisplay method
	}

	/**
	 * Creates the window
	 */
	private void createDisplay() { // creates the window
		frame = new JFrame(title); // create an object - frame
		frame.setSize(width, height); // set size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // window will close properly
		frame.setResizable(false); // stick with its size
		frame.setLocationRelativeTo(null); // appears in the middle of the screen
		frame.setVisible(true); // default it is false

		canvas = new Canvas(); // create object
		canvas.setPreferredSize(new Dimension(width, height)); // size of out game

		// make sure its stays on parameteres we gave it
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));

		frame.add(canvas); // add the canvas to our frame
		frame.pack(); // resize a window so we can see the whole canvas
	}

	/**
	 * Get the canvas
	 * 
	 * @return canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Get the frame
	 * 
	 * @return frame
	 */
	public JFrame getFrame() {
		return frame;
	}
}