package states;

import main.Display;
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
//
public class HighScoreState extends State {

    Scanner read;
    private Display display;

    ArrayList<String> n = new ArrayList<>();
    ArrayList<Integer> s = new ArrayList<>();

    public HighScoreState(Handler handler, Display display) {
        super(handler);
        this.display = display;
        loadScores();
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

        Font fnt0 = new Font("arial", Font.BOLD, 70);
        g.setFont(fnt0);
        g.setColor(Color.black);
        int x = 60;
        int y = 30;
        int i = 100;
        for (String p : n )
        {
            //g.drawRect(y, x, 35, 20);
            g.drawString(p, y, x);
            x+=i;

        }
        for (int k : s){
            //g.draw(k, 120 , x);
            x+=i;
        }
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

    private void ShowScores(){

    }
}