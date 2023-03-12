package UI.TerminalApp;

import java.util.ArrayList;
import java.util.Map;

import UI.Exception.UIException;
import gameLogic.Unit.UnitInfo;
import gameLogic.domain.Board;
import gameLogic.domain.Player;
import gameLogic.domain.Zone;

public class Actions {
    Board board;
    UnitInfo unitInfo;
    int LANCER_UNIT_TYPE = 2;
    int BERSERKER_UNIT_TYPE = 4;
    int CAVALRY_UNIT_TYPE = 5;
    int SWORDSMAN_UNIT_TYPE = 8;

    public Actions(Board board, UnitInfo unitInfo){
        this.unitInfo = unitInfo;
        this.board = board;
    }
    
    public void controlAction(Player player, Player otherPlayer) throws UIException{
        Zone actualPosition = askForPosition(true);

        boolean positionIsControlPoint = actualPosition.getIsControlZone();
        boolean positionIsFreeOrNotMine = (actualPosition.getOwner() == null || actualPosition.getOwner() != player);
        boolean positionHaveAUnitOfMine = checkIfUnitIsMine(actualPosition, player);
        
        if(!positionIsControlPoint || !positionIsFreeOrNotMine || !positionHaveAUnitOfMine) 
            throw new UIException("Invalid position to capture.");

        int indexOfUnit = askToDiscard(player);
        int cardToDiscard = player.getHand().get(indexOfUnit);

        if(actualPosition.getOwner() == otherPlayer) otherPlayer.substractControlPoint();
        player.addControlPoint();
        actualPosition.setOwner(player);
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(cardToDiscard);

        System.out.println("Correct action, press enter to continue.");
        System.console().readLine();
    }

    public void placeAction(Player player) throws UIException{
        Zone actualPosition = askForPosition(true);
        if(actualPosition.getUnit() != null) throw new UIException("Theres another unit in that position.");
        if(!checkControlPointAdjacent(actualPosition, player)) throw new UIException("Theres no adjacent control zone of your domain.");
        
        int indexOfUnit = askToDiscard(player);
        int unitToPlace = player.getHand().get(indexOfUnit);
        checkRoyalInvalidAction(unitToPlace);

        actualPosition.setUnit(unitInfo.unitConstructor(unitToPlace, player.getUser().getId()));
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(unitToPlace);
        player.addDeployedUnits();
    }

    public void recruitAction(Player player) throws UIException{
        System.out.println("Type the recruit number of the unit you want in your bag.");
        System.out.println("(Recruit number - Unit name - Available cards).");
        for (Map.Entry<Integer, Integer> set : player.getRecruitment().entrySet()) {
            // Printing all elements of a Map
            System.out.println(String.format(
                "%s - %s - %s", set.getKey(), unitInfo.getName(set.getKey()), set.getValue()
            ));
        }
        String option = System.console().readLine();
        int selectedUnit = Integer.parseInt(option);
        if(!player.getRecruitment().containsKey(selectedUnit)) throw new UIException("That unit is not available to recruit");

        int indexOfUnit = askToDiscard(player);
        int cardToDiscard = player.getHand().get(indexOfUnit);

        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(cardToDiscard);
        player.getDiscard().add(selectedUnit);
        if(player.getRecruitment().get(selectedUnit) -1 == 0) player.getRecruitment().remove(selectedUnit, 1);
        else player.getRecruitment().put(selectedUnit, player.getRecruitment().get(selectedUnit) -1);
    }

    public void moveAction(Player player, Player otherPlayer, int actualTurn) throws UIException{
        System.out.println("Select unit to move.");
        Zone actualPosition = askForPosition(true);
        int actualUnitType = actualPosition.getUnit().getUnitType();
        if(!checkIfUnitIsMine(actualPosition, player)) throw new UIException("That unit is not yours.");

        System.out.println("Select where to move.");
        Zone newPosition = askForPosition(true);
        if(newPosition.getUnit() != null) throw new UIException("Theres another unit there.");

        int indexOfUnit = askToDiscard(player);
        int unitToPlace = player.getHand().get(indexOfUnit);
        checkRoyalInvalidAction(unitToPlace);
        if(actualPosition.getUnit().getUnitType() != unitToPlace) throw new UIException("The card you are trying to dicard is a different unit.");

        if(!actualPosition.getUnit().canMove(
            actualPosition.getRow(), 
            actualPosition.getColumn(), 
            newPosition.getRow(), 
            newPosition.getColumn(), 
            actualTurn)) throw new UIException("That unit can't perform that action.");

        newPosition.setUnit(actualPosition.getUnit());
        actualPosition.setUnit(null);
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(unitToPlace);

        if(actualUnitType == CAVALRY_UNIT_TYPE){
            System.out.println("Your cavalry can attack after move.");
            extraAttack(player, otherPlayer, actualPosition, actualTurn);
        }
    }

    public void attackAction(Player player, Player otherPlayer, int actualTurn) throws UIException{
        System.out.println("Select unit that attack.");
        Zone actualPosition = askForPosition(true);
        if(!checkIfUnitIsMine(actualPosition, player)) throw new UIException("That unit is not yours.");
        int actualUnitType = actualPosition.getUnit().getUnitType();

        System.out.println("Select unit to attack.");
        Zone attackPosition = askForPosition(true);
        if(attackPosition.getUnit() == null) throw new UIException("Theres no unit there.");
        if(checkIfUnitIsMine(attackPosition, player)) throw new UIException("Can't attack your own units.");

        int indexOfUnit = askToDiscard(player);
        int unitToPlace = player.getHand().get(indexOfUnit);
        checkRoyalInvalidAction(unitToPlace);
        if(actualUnitType != unitToPlace) throw new UIException("The card you are trying to dicard is a different unit.");

        if(!actualPosition.getUnit().canAttack(
            actualPosition.getRow(), 
            actualPosition.getColumn(), 
            attackPosition.getRow(), 
            attackPosition.getColumn(), 
            actualTurn)) throw new UIException("That unit can't perform that action.");
        
        
        if(actualUnitType == LANCER_UNIT_TYPE)
            lancerSpecialAttack(player, actualPosition, attackPosition);

        attackPosition.setUnit(null);
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(unitToPlace);
        otherPlayer.substractControlPoint();

        if(actualUnitType == BERSERKER_UNIT_TYPE){
            System.out.println("Your berserker can attack again.");
            extraAttack(player, otherPlayer, actualPosition, actualTurn);
        }
        if(actualUnitType == SWORDSMAN_UNIT_TYPE){
            System.out.println("Your swordsman can move after attack.");
            swordsmanSpecialMovement(player, otherPlayer, actualPosition, actualTurn);
        }
    }

    public int initiativeAction(Player player, int initiative) throws UIException{
        if(player.getPlayerNumber() == initiative) throw new UIException("The initiative its already yours.");
        int indexOfUnit = askToDiscard(player);
        int unitToPlace = player.getHand().get(indexOfUnit);
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(unitToPlace);
        return player.getPlayerNumber();
    }

    private boolean checkIfUnitIsMine(Zone zone, Player player){
        return zone.getUnit() != null && zone.getUnit().getUserId() == player.getUser().getId();
    }

    private void checkRoyalInvalidAction(int unitToPlace) throws UIException{
        if(unitInfo.isRoyalUnit(unitToPlace)) throw new UIException("Cant use royal card for that action.");
    }

    private Zone askForPosition(boolean showMessages){
        System.out.println("Position (row column)");
        System.out.println("Example: 1 2");
        Zone zone = null;
        String option = System.console().readLine();
        int row = Integer.parseInt(String.valueOf(option.charAt(0)));
        int column = Integer.parseInt(String.valueOf(option.charAt(2)));
        zone = board.getZone(row, column);
        return zone;
    }

    private int askToDiscard(Player player){
        System.out.println("Discard card:");
        showNamesInTerminal(player.getHand());
        String option = System.console().readLine();
        int index = Integer.parseInt(option);
        return index - 1;
    }
    
    private void showNamesInTerminal(ArrayList<Integer> list){
        for(int i = 0; i < list.size(); i++) System.out.println(String.format("%s- %s", i + 1, unitInfo.getName(list.get(i))));
    }

    private boolean checkControlPointAdjacent(Zone position, Player player) throws UIException{
        boolean adjacentControlPoint = false;
        int row = position.getRow();
        int column = position.getColumn();
        int rowMin = row-1;
        int rowMax = row+1;
        int columnMin = column-1;
        int columnMax = column+1;
        if(row == board.getSize() -1) rowMax = row;
        else if(row == 0) rowMin = row;
        if(column == board.getSize() -1) columnMax = column;
        else if(column == 0) columnMin = column;

        for(int i = rowMin; i <= rowMax; i++){
            for(int j = columnMin; j <= columnMax; j++){
                Zone actualPosition = board.getZone(i, j);
                if(actualPosition.getIsControlZone() && player.equals(actualPosition.getOwner())){
                    adjacentControlPoint = true;
                    break;
                }
            }
        }
        return adjacentControlPoint;
    }

    private void lancerSpecialAttack(Player player, Zone actualPosition, Zone attackPosition) throws UIException{
        int minValue = actualPosition.getRow();
        int maxValue = attackPosition.getRow();
        boolean rowMovement = false;
        Zone newPosition = null;
        if(actualPosition.getRow() == attackPosition.getRow()){
            rowMovement = true;
            minValue = actualPosition.getColumn();
            maxValue = attackPosition.getColumn();
        }
        if(maxValue < minValue){
            int savedValue = maxValue;
            maxValue = minValue;
            minValue = savedValue;
        }
        for(int i = minValue + 1; i < maxValue; i++){
            Zone checkZone = null;
            if(rowMovement)
                checkZone = board.getZone(actualPosition.getRow(), i);
            else 
                checkZone = board.getZone(i, actualPosition.getColumn());
            if(checkZone.getUnit() != null) throw new UIException("Lancer need free space to perform attack");
        }
        if(rowMovement) newPosition = board.getZone(actualPosition.getRow(), maxValue - 1);
        else newPosition = board.getZone(maxValue - 1, actualPosition.getColumn());
        newPosition.setUnit(actualPosition.getUnit());
        actualPosition.setUnit(null);
    }

    private void extraAttack(Player player, Player otherPlayer, Zone actualPosition, int actualTurn) throws UIException{
        String option = "";
        while(!option.equals("N")) {
            try{
                System.out.println("If want to attack type Y.");
                System.out.println("If not, type any N.");
                option = System.console().readLine();
                if(option.equals("Y")) {
                    Zone attackPosition = askForPosition(false);
                    if(attackPosition.getUnit() == null) throw new UIException("Theres no unit there.");
                    if(checkIfUnitIsMine(attackPosition, player)) throw new UIException("Can't attack your own units.");
    
                    if(!actualPosition.getUnit().canAttack(
                        actualPosition.getRow(), 
                        actualPosition.getColumn(), 
                        attackPosition.getRow(), 
                        attackPosition.getColumn(), 
                        actualTurn)) throw new UIException("That unit can't perform that action.");
    
                    attackPosition.setUnit(null);
                    otherPlayer.substractDeployedUnits();
                    option = "N";
                }
            }
            catch(UIException exc){
                System.out.println(exc.getMessage());
                System.out.println("Press enter to continue.");
                System.console().readLine();
            }
        }
    }

    private void swordsmanSpecialMovement(Player player, Player otherPlayer, Zone actualPosition, int actualTurn){
        String option = "";
        while(!option.equals("N")) {
            try{
                System.out.println("If want to move type Y.");
                System.out.println("If not, type any N.");
                option = System.console().readLine();
                if(option.equals("Y")) {
                    System.out.println("Select where to move.");
                    Zone newPosition = askForPosition(true);
                    if(newPosition.getUnit() != null) throw new UIException("Theres another unit there.");
                    
                    if(!actualPosition.getUnit().canMove(
                        actualPosition.getRow(), 
                        actualPosition.getColumn(), 
                        newPosition.getRow(), 
                        newPosition.getColumn(), 
                        actualTurn)) throw new UIException("That unit can't perform that action.");

                    newPosition.setUnit(actualPosition.getUnit());
                    actualPosition.setUnit(null);
                    option = "N";
                }
            }
            catch(UIException exc){
                System.out.println(exc.getMessage());
                System.out.println("Press enter to continue.");
                System.console().readLine();
            }
        }
    }
}
