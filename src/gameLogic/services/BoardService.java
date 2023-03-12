package gameLogic.services;

import java.util.ArrayList;

import gameLogic.domain.Board;
import gameLogic.domain.Zone;

public class BoardService {
    Board board;

    public BoardService(){

    }

    public Board create(int numberOfColumns){
        Board board = new Board(numberOfColumns);
        for(int i = 0; i < numberOfColumns; i++){
            ArrayList<Zone> row = new ArrayList<Zone>();
            for(int j = 0; j < numberOfColumns; j++){
                row.add(new Zone());
            }
            board.setRow(row);
        }
        return board;
    }
}
