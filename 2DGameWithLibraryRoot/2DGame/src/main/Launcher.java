package main;
//responsible of launching the game
public class Launcher {

    public static void main(String[] args){
        Game game = new Game("Maze game", 900, 900); // object game which runs Game constructor
        game.start();
       /* try {
            File sound = new File("res1/sounds//catchme.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch (Exception e){System.out.println(e);
        } */

    }
}
