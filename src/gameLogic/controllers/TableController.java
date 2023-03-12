package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.Unit.UnitInfo;
import gameLogic.domain.Table;
import gameLogic.domain.User;
import gameLogic.services.TableService;

public class TableController {
    ArrayList<User> actualPlayers;
    TableService tableService;
    int actualTurn, iniciative;
    UnitInfo unitInfo;
    Table table;

    public TableController(TableService tableService){
        this.actualTurn = 0;
        this.iniciative = 0;
        this.actualPlayers = new ArrayList<User>();
        this.tableService = tableService;
    }

    public Table generateTable(){
        table = tableService.create(5);
        return table;
    }

    public Table getTable(){
        return table;
    }

    public ArrayList<User> getActualPlayers(){
        return actualPlayers;
    }

    public int getIniciative(){
        return iniciative;
    }
}
