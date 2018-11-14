//responsible of launching the game
public class Launcher {

    public static void main(String[] args){
        Game game = new Game("Maze game", 900, 900); // object game which runs Game constructor
        game.start();
    }
}
