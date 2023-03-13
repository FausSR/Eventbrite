package gameLogic.unit;

public class Archer extends Unit{

    @Override
    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, int actualTurn) {
        int flatNumber = (int) Math.sqrt(Math.pow((firstX-secondX), 2) + Math.pow((firstY-secondY), 2));
        if(flatNumber <= 2) return true;
        return false;
    }

    public Archer(int unitType, int userId){
        super(unitType, "Ar", userId);
    }
}
