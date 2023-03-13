package gameLogic.services;

import java.util.ArrayList;

import gameLogic.IServices.IBoardService;
import gameLogic.domain.Board;
import gameLogic.domain.Zone;

public class BoardService implements IBoardService{
    private Board board;

    public Board create(int numberOfColumns){
        board = new Board(numberOfColumns);
        for(int i = 0; i < numberOfColumns; i++){
            ArrayList<Zone> row = new ArrayList<Zone>();
            for(int j = 0; j < numberOfColumns; j++){
                row.add(new Zone(i,j));
            }
            board.setRow(row);
        }
        if(numberOfColumns == 9)
            board = loadBigMap(board);
        else
            board = loadSmallMap(board);
        return board;
    }

    
    private Board loadBigMap(Board board){
        board.getZone(0,2).setIsControllZone(true);
        board.getZone(0,6).setIsControllZone(true);
        board.getZone(8,2).setIsControllZone(true);
        board.getZone(8,6).setIsControllZone(true);
        board.getZone(3,1).setIsControllZone(true);
        board.getZone(5,2).setIsControllZone(true);
        board.getZone(3,3).setIsControllZone(true);
        board.getZone(5,5).setIsControllZone(true);
        board.getZone(3,6).setIsControllZone(true);
        board.getZone(5,7).setIsControllZone(true);
        return board;
    }

    private Board loadSmallMap(Board board){
        board.getZone(0,2).setIsControllZone(true);
        board.getZone(4,2).setIsControllZone(true);
        board.getZone(1,1).setIsControllZone(true);
        board.getZone(1,3).setIsControllZone(true);
        board.getZone(3,1).setIsControllZone(true);
        board.getZone(3,3).setIsControllZone(true);
        return board;
    }
}
