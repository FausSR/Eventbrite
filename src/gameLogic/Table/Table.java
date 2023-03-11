package gameLogic.Table;

import java.util.ArrayList;

public class Table {
    ArrayList<ArrayList<Zone>> table;

    public Table(int totalColumns){
        table = new ArrayList<ArrayList<Zone>>();
        for(int i = 0; i < totalColumns; i++){
            ArrayList<Zone> row = new ArrayList<Zone>();
            for(int j = 0; j < totalColumns; j++){
                row.add(new Zone());
            }
            table.add(row);
        }
    }   

    public ArrayList<ArrayList<Zone>> getTable(){
        return table;
    }

    public Zone getCell(int row, int column){
        return table.get(row).get(column);
    }
}
