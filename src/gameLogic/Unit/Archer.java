package gameLogic.Unit;

public class Archer extends Unit{

    @Override
    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, boolean newAction) {
        int flatNumber = (int) Math.sqrt(Math.pow((firstX-firstY), 2) + Math.pow((secondX-secondY), 2));
        if(flatNumber <= 2) return true;
        return false;
    }

    public Archer(int unitType){
        super(unitType, "Ar");
    }
}
