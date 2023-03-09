package utils;

import models.Game;
import models.SnakeStatus;

import java.io.*;

public class OutputWriter {
    public static void write(String fileName, Game g){
        File fout = new File(fileName);
        BufferedWriter bw=null;
        try{
            FileOutputStream fos = new FileOutputStream(fout);

            bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (SnakeStatus status:
                 g.snakeStatuses) {
                bw.write(status.movements);
                bw.write('\n');
            }
            bw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
