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

    public Board generateBoard(){
        board = boardService.create(5);
        return board;
    }

    public Board getBoard(){
        return board;
    }
}
