package gameLogic.services;

import gameLogic.domain.Table;

public class TableService {
    Table table;

    public TableService(){

    }

    public Table create(int numberOfColumns){
        Table table = new Table(numberOfColumns);
        return table;
    }
}
