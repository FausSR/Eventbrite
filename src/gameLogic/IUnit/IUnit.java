package gameLogic.IUnit;

public interface IUnit {
    boolean canAttack(int firstX, int firstY, int secondX, int secondY, boolean newAction);
    boolean canMove(int firstX, int firstY, int secondX, int secondY, boolean newAction);
    int getUnitType();
    String getShortName();
}
