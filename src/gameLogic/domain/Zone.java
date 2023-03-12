package gameLogic.domain;

import gameLogic.IUnit.IUnit;

public class Zone {
    IUnit unit;
    boolean isControlZone;
    Player owner;

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

    public Zone(){
        this.isControlZone = false;
    }

    public Zone(boolean isControlZone, Player owner){
        this.isControlZone = isControlZone;
        this.owner = owner;
    }
}