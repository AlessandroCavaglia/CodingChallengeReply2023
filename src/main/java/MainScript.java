import models.Game;
import utils.InputReader;
import utils.OutputWriter;

public class MainScript {
    public static void main(String[] args) {
        Game game=InputReader.read("00-example.txt");
        game.snakeStatuses.get(0).setOriginPosition(0,0);
        game.snakeStatuses.get(0).addMove("D");
        OutputWriter.write("00-example-solution.txt",game);
    }

}
