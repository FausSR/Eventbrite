package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.Unit.UnitInfo;
import gameLogic.domain.Board;
import gameLogic.domain.User;
import gameLogic.services.BoardService;

public class BoardController {
    ArrayList<User> actualPlayers;
    BoardService boardService;
    int actualTurn, iniciative;
    UnitInfo unitInfo;
    Board board;

    public BoardController(BoardService boardService){
        this.actualTurn = 0;
        this.iniciative = 0;
        this.actualPlayers = new ArrayList<User>();
        this.boardService = boardService;
    }

    public Board generateBoard(){
        board = boardService.create(5);
        return board;
    }

    public Board getBoard(){
        return board;
    }

    public ArrayList<User> getActualPlayers(){
        return actualPlayers;
    }

    public int getIniciative(){
        return iniciative;
    }
}
