package gameLogic.domain;

import java.util.ArrayList;

public class Table {
    ArrayList<ArrayList<Zone>> table;
    int size;

    public Table(int totalColumns){
        this.size = totalColumns;
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

    public Zone getZone(int row, int column){
        return table.get(row).get(column);
    }

    public int getSize(){
        return this.size;
    }
}
