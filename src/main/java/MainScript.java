import models.Cell;
import models.Game;
import models.SnakeStatus;
import utils.InputReader;
import utils.Kmeans;
import utils.OutputWriter;

public class MainScript {

    public static void main(String[] args) throws CloneNotSupportedException {
        Game game=InputReader.read("00-example.txt");
        game= Kmeans.runKmeans(game);
        System.out.println("HO FATTO IL KMEANS!!!!!!!!!!");
        for(int i =0;i<game.snakeNumber;i++){
            System.out.println("Elaboro il serpente "+i);
            while(game.snakes.get(i)>0){
                char bestMove=bestMove(game.map,(short)game.snakeStatuses.get(i).actualRow,(short)game.snakeStatuses.get(i).actualColumn,game);
                int[] newPos=nextPos(game.snakeStatuses.get(i),bestMove,game);
                game.snakeStatuses.get(i).addMove(""+bestMove,newPos[0],newPos[1]);
                game.map[newPos[0]][newPos[1]].setSnakeId((short)i);
                game.snakes.set(i, (short) (game.snakes.get(i)-1));
            }
        }

        OutputWriter.write("00-example-solution.txt",game);
        System.out.println(score(game.map, game.columns, game.rows));
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

        if (map[floorMod(r+1,game.rows)][c].getScore() >= -10000
                && map[floorMod(r+1,game.rows)][c].getSnakeId() < 0
        ) {
            moves[0] = map[floorMod(r+1,game.rows)][c].getScore();
        } else {
            moves[0] = -10000;
        }

        if (map[r][floorMod(c+1,game.columns)].getScore() >= -10000
                && map[r][floorMod(c+1,game.columns)].getSnakeId() < 0
        ) {
            moves[1] = map[r][floorMod(c+1,game.columns)].getScore();
        } else {
            moves[1] = -10000;
        }

        if (map[floorMod(r-1,game.rows)][c].getScore() >= -10000
                && map[floorMod(r-1,game.rows)][c].getSnakeId() < 0
        ) {
            moves[2] = map[floorMod(r-1,game.rows)][c].getScore();
        } else {
            moves[2] = -10000;
        }

        if (map[r][floorMod(c-1,game.columns)].getScore() >= -10000
                && map[r][floorMod(c-1,game.columns)].getSnakeId() < 0
        ) {
            moves[3] = map[r][floorMod(c-1,game.columns)].getScore();
        } else {
            moves[3] = -10000;
        }

        int best = findMaxIndex(moves);
        switch (best) {
            case 1:
                return 'R';
            case 2:
                return 'U';
            case 3:
                return 'L';
            case 0:
            default:
                return 'D';
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

    public static int[] nextPos(SnakeStatus s,char move,Game g){
        int[] result=new int[2];
        switch (move) {
            case 'R':
                result[0]=s.actualRow;
                result[1]=floorMod(s.actualColumn+1,g.columns);
                break;
            case 'D':
                result[0]=floorMod(s.actualRow+1,g.rows);
                result[1]=s.actualColumn;
                break;
            case 'L':
                result[0]=s.actualRow;
                result[1]=floorMod(s.actualColumn-1,g.columns);
                break;
            case 'U':
                result[0]=floorMod(s.actualRow-1,g.rows);
                result[1]=s.actualColumn;
                break;

        }
        return result;
    }
    private static int floorMod(int val, int mod){
        return (((val % mod) + mod) % mod);
    }

}
