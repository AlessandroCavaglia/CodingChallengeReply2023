import models.Cell;

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
    }

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
}
