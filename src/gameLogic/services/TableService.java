package gameLogic.services;

import gameLogic.Table.Table;

public class TableService {
    Table table;

    public TableService(){

    }

    public Table create(int numberOfColumns){
        Table table = new Table(numberOfColumns);
        // table.getCell(0,3).setIsControllZone(true);
        // table.getCell(0,3).setOwner(actualPlayers.get(0));
        // table.getCell(4,3).setIsControllZone(true);
        // table.getCell(4,3).setOwner(actualPlayers.get(1));
        return table;
    }
}
