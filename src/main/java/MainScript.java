import models.Cell;
import models.Game;
import utils.InputReader;
import utils.OutputWriter;

public class MainScript {

    public static void main(String[] args) {
        /**
         * example field
         */
        Cell[][] map = new Cell[5000][5000];
        for(int i=0; i < 5000; i++) {
            for(int j=0; j < 5000; j++) {
                map[i][j] = new Cell((short) 0,(short) 0);
            }
        }
        map[0][1].setSnakeId((short) 5);
        map[0][1].setScore((short) 5);
        map[0][2].setSnakeId((short) 5);
        map[0][2].setScore((short) 5);
        map[0][3].setSnakeId((short) 5);
        map[0][3].setScore((short) 5);

        System.out.println("Hello world");
        System.out.println(score(map, 3, 3));

        Game game=InputReader.read("00-example.txt");
        game.snakeStatuses.get(0).setOriginPosition(0,0);
        game.snakeStatuses.get(0).addMove("D");
        OutputWriter.write("00-example-solution.txt",game);
    }

    /**
     * Compute total score in the map
     * @param map
     * @param columns
     * @param rows
     * @return total score
     */
    public static int score(Cell[][] map, int columns, int rows) {
        int totalScore = 0;

        for(int i=0; i < rows; i++) {
            for(int j=0; j < columns; j++) {
                if (map[i][j].getScore() <= 10000
                        && map[i][j].getScore() >= -10000
                        && map[i][j].getSnakeId() >= 0
                ) {
                    totalScore += map[i][j].getScore();
                }
            }
        }
        return totalScore;
    }

    /**
     *
     */
    public static char bestMove(Cell[][] map, short r, short c, Game game) {
        short moves[] = new short[4];

        if (map[r+1 % game.rows][c].getScore() > 0
                && map[r+1 % game.rows][c].getSnakeId() >= 0
        ) {
            moves[0] = map[r+1 % game.rows][c].getScore();
        } else {
            moves[0] = -10000;
        }

        if (map[r][c+1 % game.columns].getScore() > 0
                && map[r][c+1 % game.columns].getSnakeId() >= 0
        ) {
            moves[1] = map[r][c+1 % game.columns].getScore();
        } else {
            moves[1] = -10000;
        }

        if (map[r-1 % game.rows][c].getScore() > 0
                && map[r-1 % game.rows][c].getSnakeId() >= 0
        ) {
            moves[2] = map[r-1 % game.rows][c].getScore();
        } else {
            moves[2] = -10000;
        }

        if (map[r][c-1 % game.columns].getScore() > 0
                && map[r][c-1 % game.columns].getSnakeId() >= 0
        ) {
            moves[3] = map[r][c-1 % game.columns].getScore();
        } else {
            moves[3] = -10000;
        }

        int best = findMaxIndex(moves);
        switch (best) {
            case 1:
                return 'R';
            case 2:
                return 'D';
            case 3:
                return 'L';
            case 0:
            default:
                return 'U';
        }
    }

    public static int findMaxIndex(short[] array) {
        int max = array[0];
        int index = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }


}
