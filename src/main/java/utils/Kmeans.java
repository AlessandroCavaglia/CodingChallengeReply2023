package utils;

import models.Cell;
import models.Game;
import models.SnakeStatus;

public class Kmeans {
    public static Game runKmeans(Game g) throws CloneNotSupportedException {
        g=Kmeans.initializeSnakes(g);
        /*while(!g.kmeansOver){
            //printPos(g);
            g=assignCentroid(g);
            g=recalculateCentroid(g);
        }
        g=reassignZeros(g);*/
        g=initializeMap(g);
        //printPos(g);
        return g;
    }
    private static Game assignCentroid(Game g){
        int row_num=0;
        for (Cell[] row:
             g.map) {
            int col_num=0;
            for(Cell cell:
            row){
                int distance=Integer.MAX_VALUE;
                Integer snakeId=-1;
                int count=0;
                for (SnakeStatus s:
                     g.snakeStatuses) {
                    int dist=distance(row_num,col_num,s.actualRow, s.actualColumn, g.rows,g.columns);
                    if(dist<distance){
                        snakeId=count;
                        distance=dist;
                    }
                    count++;
                }
                cell.setCentroId(snakeId.shortValue());
                col_num++;
            }
            row_num++;
        }
        return g;
    }


    private static Game recalculateCentroid(Game g){
        int[] snakeRows=new int[g.snakeNumber];
        int[] snakeCols=new int[g.snakeNumber];
        int[] snakePoints=new int[g.snakeNumber];
        for(int i=0;i<g.snakeNumber;i++){
            snakePoints[i]=0;
            snakeRows[i]=0;
            snakeCols[i]=0;
        }
        int row_num=0;
        for (Cell[] row:
                g.map) {
            int col_num=0;
            for(Cell cell:
                    row){
                //Possible to add weighting based on the score
                snakeRows[cell.getCentroId()]+=row_num;
                snakeCols[cell.getCentroId()]+=col_num;
                snakePoints[cell.getCentroId()]+=1;
            }
            row_num++;
        }
        for(int i=0;i<g.snakeNumber;i++){
            if(snakePoints[i]==0){
                snakeRows[i]=0;
                snakeCols[i]=0;
            }else{
                snakeRows[i]=snakeRows[i]/snakePoints[i];
                snakeCols[i]=snakeCols[i]/snakePoints[i];
            }
            g.kmeansOver=true;
            if(g.snakeStatuses.get(i).actualRow!=0+snakeRows[i] || g.snakeStatuses.get(i).actualColumn!=0+snakeCols[i] ){
                g.kmeansOver=false;
            }
            g.snakeStatuses.get(i).setOriginPosition(0+snakeRows[i],0+snakeCols[i]);
            snakeRows[i]=0;
            snakeCols[i]=0;
            snakePoints[i]=0;
        }
        return g;
    }

    private static int distance(int startRow,int startCol,int endRow,int endCol,int rowNum,int colNum){
        int rowDist=Math.abs(startRow-endRow);
        int colDist=Math.abs(startCol-endCol);
        if(rowDist>(rowNum/2)){
            rowDist=rowNum-rowDist;
        }
        if(colDist>(colNum/2)){
            colDist=colNum-colDist;
        }
        return rowDist+colDist;
    }

    private static Game initializeSnakes(Game g){
        Integer snakeId=0;
        for (SnakeStatus snake:g.snakeStatuses){
            boolean free=false;
            int row=-1,col=-1;
            while(!free){
                row=(int)((Math.random() * (g.rows)));
                col=(int)((Math.random() * (g.columns)));
                if(g.map[row][col].getSnakeId()<=0 && g.map[row][col].getScore()>=-10000){
                    free=true;
                }
            }
            snake.setOriginPosition(row,col);
            g.map[row][col].setSnakeId(snakeId.shortValue());
            snakeId++;
        }
        return g;
    }

    private static void printPos(Game g){
        int pos=0;
        for (SnakeStatus s:
             g.snakeStatuses) {
            System.out.println(pos+" "+s.actualRow+" "+s.actualColumn);
            pos++;
        }
        System.out.println("---------");
    }

    private static Game reassignZeros(Game g){
        for (SnakeStatus s:
             g.snakeStatuses) {
            if(s.actualRow==0 && s.actualColumn==0){
                boolean free=false;
                int row=-1,col=-1;
                while(!free){
                    row=(int)((Math.random() * (g.rows)));
                    col=(int)((Math.random() * (g.columns)));
                    if(g.map[row][col].getSnakeId()<=0){
                        free=true;
                    }
                }
                s.setOriginPosition(row,col);
            }
        }
        return g;
    }

    private static Game initializeMap(Game g){
        for (int i=0;i<g.snakeNumber;i++) {
            g.map[g.snakeStatuses.get(i).actualRow][g.snakeStatuses.get(i).actualColumn].setSnakeId((short) i);
        }
        return g;
    }
}
