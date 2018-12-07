package states;

import main.Handler;
import main.State;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;
import world.Assets;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HighScoreState extends State {

    Scanner read;

    ArrayList<String> n = new ArrayList<>();
    ArrayList<Integer> s = new ArrayList<>();

    public HighScoreState(Handler handler) {
        super(handler);
        loadScores();
       // System.out.println(read());

    }

    @Override
    public void update() {
        if(handler.getKeyManager().escape)
        {
            handler.getGame().reloadMenuState();
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void render(Graphics g) {

    }

    public void loadScores()
    {
        try
        {
            read = new Scanner (new FileReader ("res1/maps/scores.txt"));

            while (read.hasNext ())
            {
                n.add (read.next ());

                s.add(read.nextInt ());
            }
            System.out.println(n);
            System.out.println(s);
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println(fnfe+": FILE NOT FOUND!");
        }
        catch (InputMismatchException ime)
        {
            System.out.println(ime+": INVALID DATA!");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}