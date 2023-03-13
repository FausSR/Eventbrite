package gameLogic.domain;

import gameLogic.IUnit.IUnit;

public class Zone {
    private IUnit unit;
    private boolean isControlZone;
    private Player owner;
    private int row, column;
    
    public IUnit getUnit() {
        return unit;
    }

    public void setUnit(IUnit unit) {
        this.unit = unit;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner){
        this.owner = owner;
    }

    public boolean getIsControlZone(){
        return isControlZone;
    }

    public void setIsControllZone(boolean isControlZone){
        this.isControlZone = isControlZone;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Zone(int row, int column){
        this.isControlZone = false;
        this.row = row;
        this.column = column;
    }

    public Zone(boolean isControlZone, Player owner){
        this.isControlZone = isControlZone;
        this.owner = owner;
    }
}
