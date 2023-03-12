package gameLogic.IUnit;

public interface IUnit {
    boolean canAttack(int firstX, int firstY, int secondX, int secondY, int actualTurn);
    boolean canMove(int firstX, int firstY, int secondX, int secondY, int actualTurn);
    int getUnitType();
    int getUserId();
    String getShortName();
}
