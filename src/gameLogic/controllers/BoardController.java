package gameLogic.controllers;

import gameLogic.IControllers.IBoardController;
import gameLogic.IServices.IBoardService;
import gameLogic.domain.Board;

public class BoardController implements IBoardController{
    private IBoardService boardService;

    public BoardController(IBoardService boardService){
        this.boardService = boardService;
    }

    public Board generateBoard(int numberOfColumns){
        Board board = boardService.create(numberOfColumns);
        return board;
    }
}
