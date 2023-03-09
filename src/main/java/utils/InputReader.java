package utils;

import models.Cell;
import models.Game;
import models.SnakeStatus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public static Game read(String fileName){
        BufferedReader reader;
        Game game=new Game();

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            int rowNum=0;
            while (line != null) {
                String[] elems=line.split(" ");
                if(rowNum==0){
                    game.columns=Integer.parseInt(elems[0]);
                    game.rows=Integer.parseInt(elems[1]);
                    game.snakeNumber=Integer.parseInt(elems[2]);
                    game.map=new Cell[game.rows][game.columns];
                }
                if(rowNum==1){
                    for (String elem:
                            elems) {
                        game.snakes.add(Short.parseShort(elem));
                        game.snakeStatuses.add(new SnakeStatus());
                    }
                }
                if(rowNum>1){
                    int count=0;
                    for (String elem:
                            elems) {
                        if(elem.equals("*")){
                            game.map[rowNum-2][count]=new Cell(Short.parseShort("-20000"));
                        }else{
                            game.map[rowNum-2][count]=new Cell(Short.parseShort(elem));
                        }
                        count++;
                    }
                }
                rowNum++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return game;
    }
}
