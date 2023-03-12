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

    public Actions(Board board, UnitInfo unitInfo){
        this.unitInfo = unitInfo;
        this.board = board;
    }
    
    public void controlAction(Player player) throws UIException{
        Zone actualPosition = askForPosition();

        boolean positionIsControlPoint = actualPosition.getIsControlZone();
        boolean positionIsFreeOrNotMine = (actualPosition.getOwner() == null || actualPosition.getOwner() != player);
        boolean positionHaveAUnitOfMine = checkIfUnitIsMine(actualPosition, player);
        
        if(!positionIsControlPoint || !positionIsFreeOrNotMine || !positionHaveAUnitOfMine) 
            throw new UIException("Invalid position to capture.");

        int indexOfUnit = askToDiscard(player);
        int cardToDiscard = player.getHand().get(indexOfUnit);
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(cardToDiscard);

        System.out.println("Correct action, press enter to continue.");
        System.console().readLine();
    }

    public void placeAction(Player player) throws UIException{
        Zone actualPosition = askForPosition();
        if(actualPosition.getUnit() != null) throw new UIException("Theres another unit in that position.");
        if(!checkControlPointAdjacent(actualPosition, player)) throw new UIException("Theres no adjacent control zone of your domain.");
        
        int indexOfUnit = askToDiscard(player);
        int unitToPlace = player.getHand().get(indexOfUnit);
        checkRoyalInvalidAction(unitToPlace);

        actualPosition.setUnit(unitInfo.unitConstructor(unitToPlace, player.getUser().getId()));
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(unitToPlace);
    }

    public void recruitAction(Player player){
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

        int indexOfUnit = askToDiscard(player);
        int cardToDiscard = player.getHand().get(indexOfUnit);

        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(cardToDiscard);
        player.getDiscard().add(selectedUnit);
        if(player.getRecruitment().get(selectedUnit) -1 == 0) player.getRecruitment().remove(selectedUnit, 1);
        else player.getRecruitment().put(selectedUnit, player.getRecruitment().get(selectedUnit) -1);
    }

    public void moveAction(Player player, boolean firstAction) throws UIException{
        System.out.println("Select unit to move.");
        Zone actualPosition = askForPosition();
        if(!checkIfUnitIsMine(actualPosition, player)) throw new UIException("That unit is not yours.");

        Zone newPosition = askForPosition();
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
            firstAction)) throw new UIException("That unit can't perform that action.");

        newPosition.setUnit(actualPosition.getUnit());
        actualPosition.setUnit(null);
        player.getHand().remove(indexOfUnit);
        player.getDiscard().add(unitToPlace);
    }

    private boolean checkIfUnitIsMine(Zone zone, Player player){
        return zone.getUnit() != null && zone.getUnit().getUserId() == player.getUser().getId();
    }

    private void checkRoyalInvalidAction(int unitToPlace) throws UIException{
        if(unitInfo.isRoyalUnit(unitToPlace)) throw new UIException("Cant use royal card for that action.");
    }

    private Zone askForPosition(){
        System.out.println("Position (row column)");
        System.out.println("Example: 1 2");
        Zone zone = null;
        String option = System.console().readLine().replaceAll("\\s+","");
        int row = Integer.parseInt(String.valueOf(option.charAt(0)));
        int column = Integer.parseInt(String.valueOf(option.charAt(1)));
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
}
