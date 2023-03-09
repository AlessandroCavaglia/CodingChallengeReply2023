package models;

public class Cell {
    private short score;
    private short snakeId;

    private short centroId;

    public Cell(short score, short snakeId) {
        this.score = score;
        this.snakeId = snakeId;
        this.centroId=-1;
    }

    public Cell(short score) {
        this.score = score;
        this.snakeId = -1;
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

    public short getCentroId() {
        return centroId;
    }

    public void setCentroId(short centroId) {
        this.centroId = centroId;
    }
}
