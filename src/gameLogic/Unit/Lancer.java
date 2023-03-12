package gameLogic.Unit;

public class Lancer extends Unit{

    boolean alreadyMoved;

    @Override
    public boolean canMove(int firstX, int firstY, int secondX, int secondY, boolean newTurn){
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-secondX), 2) + Math.pow((firstY-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        if(flatNumber <= maxDiagonalDistance){
            this.alreadyMoved = true;
            executeAction = true;
        }
        return executeAction;
    }

    @Override
    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, boolean newTurn) {
        if(newTurn) this.alreadyMoved = false; // I suppose that if you dont move in the same turn, you cant attack adjacent
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-secondX), 2) + Math.pow((firstY-secondY), 2));

        boolean canAttackDirectlly = flatNumber > Math.sqrt(2) && flatNumber <= Math.sqrt(18);
        boolean attackAdjacentIfMove = flatNumber <= Math.sqrt(2) && this.alreadyMoved;
        boolean attackInStraightLine = (firstX == secondX) || (firstY == secondY); // Is very OP if i dont do this and in the game is like this

        if((canAttackDirectlly && attackInStraightLine) || (attackAdjacentIfMove) ){
            this.alreadyMoved = false;
            executeAction = true;
        } 
        return executeAction;
    }
    
    public Lancer(int unitType, int userId){
        super(unitType, "La", userId);
        this.alreadyMoved = false;
    }
}
