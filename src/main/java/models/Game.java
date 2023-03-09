package models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public int columns=0,rows=0,snakeNumber=0;
    public List<Short> snakes=new ArrayList<Short>();
    public Cell[][] map=null;

    public List<SnakeStatus> snakeStatuses=new ArrayList<>();

}
