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
                row.add(new Zone(i,j));
            }
            board.setRow(row);
        }
        if(numberOfColumns == 9)
            loadBigMap();
        else
            loadSmallMap();
        return board;
    }

    
    private void loadBigMap(){
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
        
    }

    private void loadSmallMap(){
        board.getZone(0,2).setIsControllZone(true);
        board.getZone(4,2).setIsControllZone(true);
        board.getZone(1,1).setIsControllZone(true);
        board.getZone(1,3).setIsControllZone(true);
        board.getZone(3,1).setIsControllZone(true);
        board.getZone(3,3).setIsControllZone(true);
    }
}
