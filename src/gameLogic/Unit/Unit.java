package gameLogic.Unit;

import gameLogic.IUnit.IUnit;

abstract class Unit implements IUnit{
    int id, userId, unitType;
    String shortName;

    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, int actualTurn){
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-secondX), 2) + Math.pow((firstY-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        if(flatNumber <= maxDiagonalDistance) executeAction = true;
        return executeAction;
    }

    public boolean canMove(int firstX, int firstY, int secondX, int secondY, int actualTurn){
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-secondX), 2) + Math.pow((firstY-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        if(flatNumber <= maxDiagonalDistance) executeAction = true;
        return executeAction;
    }

    public int getUnitType(){
        return this.unitType;
    }

    public int getUserId(){
        return this.userId;
    }

    public String getShortName(){
        return this.shortName;
    }

    public Unit(int unitType, String shortName, int userId){
        this.unitType = unitType;
        this.shortName = shortName;
        this.userId = userId;
        this.id = (int)Math.random()*100000; //Fake id
    }
}
