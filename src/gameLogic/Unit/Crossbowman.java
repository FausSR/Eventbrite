package gameLogic.unit;

public class Crossbowman extends Unit{

    @Override
    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, int actualTurn) {
        int flatNumber = (int) Math.sqrt(Math.pow((firstX-secondX), 2) + Math.pow((firstY-secondY), 2));
        if(flatNumber <= 2 && (firstX == secondX || firstY == secondY)) return true;
        return false;
    }

    public Crossbowman(int unitType, int userId){
        super(unitType, "Cr", userId);
    }
}
