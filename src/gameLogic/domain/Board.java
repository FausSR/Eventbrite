package gameLogic.domain;

import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<Zone>> board;
    int size;

    public Board(int numberOfColumns){
        this.size = numberOfColumns;
        this.board = new ArrayList<ArrayList<Zone>>();
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

    public void setRow(ArrayList<Zone> row) {
        this.board.add(row);
    }
}
