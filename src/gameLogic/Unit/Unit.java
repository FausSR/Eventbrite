package gameLogic.Unit;

import gameLogic.IUnit.IUnit;

abstract class Unit implements IUnit{
    int id, unitType;
    String shortName;

    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, boolean newAction){
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-firstY), 2) + Math.pow((secondX-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        if(flatNumber <= maxDiagonalDistance) executeAction = true;
        return executeAction;
    }

    public boolean canMove(int firstX, int firstY, int secondX, int secondY, boolean newAction){
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-firstY), 2) + Math.pow((secondX-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        if(flatNumber <= maxDiagonalDistance) executeAction = true;
        return executeAction;
    }

    public int getUnitType(){
        return this.unitType;
    }

    public String getShortName(){
        return this.shortName;
    }

    public Unit(int unitType, String shortName){
        this.unitType = unitType;
        this.shortName = shortName;
        this.id = (int)Math.random()*100000; //Fake id
    }
}
