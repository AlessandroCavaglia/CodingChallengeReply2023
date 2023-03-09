package models;

public class Cell {
    private short score;
    private short snakeId;

    public Cell(short score, short snakeId) {
        this.score = score;
        this.snakeId = snakeId;
    }

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public short getSnakeId() {
        return snakeId;
    }

    public void setSnakeId(short snakeId) {
        this.snakeId = snakeId;
    }
}
