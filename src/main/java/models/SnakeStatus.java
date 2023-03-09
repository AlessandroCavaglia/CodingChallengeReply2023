package models;

import java.util.ArrayList;
import java.util.List;

public class SnakeStatus {
    public int originalRow;
    public int originalColumn;

    public int actualRow;
    public int actualColumn;
    public String movements= "";

    public void addMove(String direction,int actualRow,int actualColumn){
        movements+=" "+direction;
        this.actualRow=actualRow;
        this.actualColumn=actualColumn;
    }

    public void addWormHoleMove(String direction,int exitRow,int exitColumn){
        movements+=" "+direction+" "+exitRow+" "+exitColumn;
    }

    public void setOriginPosition(int originalRow, int originalColumn){
        this.originalRow=originalRow;
        this.originalColumn=originalColumn;

        movements+=originalRow+" "+originalColumn;
    }
}
