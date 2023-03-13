package gameLogic.controllers;

import gameLogic.Unit.UnitInfo;
import gameLogic.domain.Board;
import gameLogic.services.BoardService;

public class BoardController {
    BoardService boardService;
    UnitInfo unitInfo;
    Board board;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    public Board generateBoard(int numberOfColumns){
        board = boardService.create(numberOfColumns);
        return board;
    }

    public Board loadMap(int numberOfColumns){
        board = boardService.create(numberOfColumns);
        return board;
    }

    public Board getBoard(){
        return board;
    }
}
