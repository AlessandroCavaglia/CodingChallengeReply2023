package models;

import java.util.ArrayList;
import java.util.List;

public class SnakeStatus {
    public int originalRow;
    public int originalColumn;
    public String movements= "";

    public void addMove(String direction){
        movements+=" "+direction;
    }

    public void addWormHole(String direction,int exitRow,int exitColumn){
        movements+=" "+direction+" "+exitRow+" "+exitColumn;
    }

    public void setOriginPosition(int originalRow, int originalColumn){
        this.originalRow=originalRow;
        this.originalColumn=originalColumn;

        movements+=originalRow+" "+originalColumn;
    }
}
