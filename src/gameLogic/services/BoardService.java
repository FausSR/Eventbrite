package gameLogic.services;

import gameLogic.domain.Board;

public class BoardService {
    Board board;

    public BoardService(){

    }

    public Board create(int numberOfColumns){
        Board board = new Board(numberOfColumns);
        return board;
    }
}
