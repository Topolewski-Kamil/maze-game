package states;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Stores a user's high score.
 */
public class HighScore implements Comparable<HighScore> {

    public static ArrayList<String> n = new ArrayList<>();
    public static ArrayList<Integer> s = new ArrayList<>();

    private static int count = 0;
    public static final int MAX_SCORE = 150;

    private String name;
    private int score;

    public HighScore(String name, int score) {
        this.name = name;
        this.setScore(score);
        //HighScore.count++;  //made another instance
    }

    public HighScore(String name) {
        //reusing other constructor
        this(name, 0);
    }
    public int compareTo(HighScore other) {
        return other.score - this.score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        if (score < 0) {
            this.score = 0;
        }else if (score > MAX_SCORE) {
            this.score = MAX_SCORE;
        }else {
            this.score = score;
        }
    }

    public String toString() {
        return this.name + ":\t" + this.score;
    }

    public static int getScoreCount() {
        return HighScore.count;
    }

    public static Integer getS(int i) {
        return s.get(i);
    }

    public static void SetS(int i , int number){
        s.set(i, number);
    }

    public static void countScore(Graphics g) {
        //create an array of HighScores
        HighScore[] scores = new HighScore[count];

        for (int i = 0; i < count; i++) {
            scores[i] = new HighScore(n.get(i), s.get(i));
        }

        java.util.Arrays.sort(scores);

        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        int x = 250;
        int y = 280;
        int i = 100;
        int shift = 100;
        //print scores (test toString)
        for (int r = 0; r < 5; r++) {
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(r+1) + ".", x - 70 + shift, y);
            g.setColor(Color.black);
            g.drawString(String.valueOf (r+1) + ".", x + 2 - 70 + shift, y + 2);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(scores[r]), x + shift, y);
            g.setColor(Color.black);
            g.drawString(String.valueOf(scores[r]), x + 2 + shift, y + 2);
            y+=i;
        }
    }

    public static void loadScores()
    {
        int countLines = 0;
        try
        {
            Scanner read = new Scanner (new FileReader("res1/maps/scores.txt"));

            while (read.hasNext ())
            {
                n.add (read.next ());

                s.add(read.nextInt ());

                countLines++;
            }
            count+=countLines-count;
            read.close();
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (InputMismatchException ime)
        {
            ime.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
