package gameLogic.Unit;

public class Berserker extends Unit{

    boolean attackedBefore;
    boolean canAttack;

    @Override
    public boolean canMove(int firstX, int firstY, int secondX, int secondY, boolean newTurn){
        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-firstY), 2) + Math.pow((secondX-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        if(flatNumber <= maxDiagonalDistance){
            this.attackedBefore = false;
            this.canAttack = true;
            executeAction = true;
        }
        return executeAction;
    }

    @Override
    public boolean canAttack(int firstX, int firstY, int secondX, int secondY, boolean newTurn) {
        if(!this.canAttack && !this.attackedBefore) this.canAttack = true;

        boolean executeAction = false;
        double flatNumber = Math.sqrt(Math.pow((firstX-firstY), 2) + Math.pow((secondX-secondY), 2));
        double maxDiagonalDistance = Math.sqrt(2);
        
        if(flatNumber <= maxDiagonalDistance && this.canAttack){
            if(this.attackedBefore){
                this.canAttack = false;
                this.attackedBefore = false;
            } 
            else this.attackedBefore = true;
            executeAction = true;
        } 
        return executeAction;
    }
    
    public Berserker(int unitType, int userId){
        super(unitType, "Br", userId);
        this.attackedBefore = false;
        this.canAttack = true;
    }
}