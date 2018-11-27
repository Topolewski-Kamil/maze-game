package main;
import java.awt.*;

// holding things states have in common
public abstract class State { // its abstract because it doesn't initialize anything,

    private static State currentState = null; //object that holds what states are currently

    public static void setState(State state){ // setting current state
        currentState = state;
    }

    public static State getState(){ // returning state
        return currentState;
    } //returns our current state

    // class
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    //classes which extends this class will have to use those methods
    public abstract void update();

    public abstract void render(Graphics graphics);
}
