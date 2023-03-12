package gameLogic.domain;

import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<Zone>> board;
    int size;

    public Board(int totalColumns){
        this.size = totalColumns;
        board = new ArrayList<ArrayList<Zone>>();
        for(int i = 0; i < totalColumns; i++){
            ArrayList<Zone> row = new ArrayList<Zone>();
            for(int j = 0; j < totalColumns; j++){
                row.add(new Zone());
            }
            board.add(row);
        }
    }   

    public ArrayList<ArrayList<Zone>> getBoard(){
        return board;
    }

    public Zone getZone(int row, int column){
        return board.get(row).get(column);
    }

    public int getSize(){
        return this.size;
    }
}
