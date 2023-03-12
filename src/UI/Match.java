package UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import gameLogic.Unit.UnitInfo;
import gameLogic.controllers.PlayerController;
import gameLogic.controllers.BoardController;
import gameLogic.controllers.UserController;
import gameLogic.domain.Player;
import gameLogic.domain.Board;
import gameLogic.domain.User;
import gameLogic.domain.Zone;

public class Match {
    PlayerController playerController;
    BoardController boardController;
    UserController userController;
    ArrayList<Player> players;
    Board board;
    UnitInfo unitInfo;
    int actualTurn, iniciative;
    boolean endMatch;

    public Match(PlayerController playerController, UserController userController, BoardController boardController){
        this.playerController = playerController;
        this.boardController = boardController;
        this.userController = userController;
        this.unitInfo = new UnitInfo();
        this.actualTurn = 1;
        this.iniciative = 0;
        this.endMatch = false;
    }

    public void setGame(){
        SelectPlayer selectPlayer = new SelectPlayer(userController);
        ArrayList<User> selectedPlayers = selectPlayer.selectPlayers();
        players = playerController.setPlayers(selectedPlayers.get(0).getId(), selectedPlayers.get(1).getId());
        board = boardController.generateBoard();
        loadMap();
        startGame();
    }

    public void showBoard(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        for(int i = 0; i <= board.getSize(); i++){
            if(i==0) System.out.print("| ");
            else System.out.print(String.format("| %s ", i - 1));
        }
        System.out.println("\n-----------------------");
        for(int i = 0; i < board.getSize(); i++){
            System.out.print(String.format("|%s", i));
            for(int j = 0; j < board.getSize(); j++){
                Zone zone = board.getZone(i, j);
                if(zone.getUnit() != null) System.out.print("| " + zone.getUnit().getShortName());
                else if(zone.getIsControlZone() && zone.getOwner() != null) System.out.print(String.format("| %s ", getPlayerFaction(zone.getOwner())));
                else if(zone.getIsControlZone()) System.out.print("| @ ");
                else System.out.print("| - ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    private String getPlayerFaction(Player player){
        if(player.getUser().getId() == players.get(0).getUser().getId()) return "W";
        return "C";
    }

    private void loadMap(){
        board.getZone(0,2).setIsControllZone(true);
        board.getZone(0,2).setOwner(players.get(0));
        board.getZone(4,2).setIsControllZone(true);
        board.getZone(4,2).setOwner(players.get(1));
        board.getZone(1,1).setIsControllZone(true);
        board.getZone(1,3).setIsControllZone(true);
        board.getZone(3,1).setIsControllZone(true);
        board.getZone(3,3).setIsControllZone(true);
    }

    public void startGame(){
        while(!endMatch){
            Player firstPlayer = players.get(this.iniciative);
            Player secondPlayer = null;
            if(this.iniciative == 0) secondPlayer = players.get(1);
            else secondPlayer = players.get(0);
            actionMenu(firstPlayer);
            actionMenu(secondPlayer);
        }
    }

    public void actionMenu(Player player){
        while(player.getHand().size() > 0){
            try{
                showBoard();
                System.out.println(String.format("Hand: %s", showNamesInArray(player.getHand())));
                System.out.println(String.format("Total discard:  %s", showNamesInArray(player.getDiscard())));
                System.out.println(String.format("Recruitements:  %s", showNamesInHash(player.getRecruitment())));
                System.out.println("-----------Actions-----------");
                System.out.println("1-Move");
                System.out.println("2-Recruit");
                System.out.println("3-Place");
                System.out.println("4-Attack");
                System.out.println("5-Control");
                System.out.println("6-Initiative");
                String option = System.console().readLine();
                int value = Integer.parseInt(option);
                switch(value){
                    case 1:
                        // moveAction();
                    case 2:
                        // recruitAction();
                    case 3:
                        placeAction(player);
                    case 4:
                        // attackAction();
                    case 5:
                        // controlAction();
                    case 6:
                        // initiativeAction();
                }
            }
            catch(RuntimeException exc){
                System.out.println("Invalid input, please try again.");
                System.out.println("Press enter to continue.");
                System.console().readLine();
            }
        }
    }

    private String showNamesInArray(ArrayList<Integer> list){
        return list.stream()
                .map(x -> unitInfo.getName(x))
                .collect(Collectors.joining(", "));
    }

    private String showNamesInHash(HashMap<Integer, Integer> map){
        return map.entrySet().stream()
                .map(x -> String.format("%s = %s", unitInfo.getName(x.getKey()), x.getValue()))
                .collect(Collectors.joining(", ", "", ""));
    }

    private void showNamesInTerminal(ArrayList<Integer> list){
        for(int i = 0; i < list.size(); i++) System.out.println(String.format("%s- %s", i + 1, unitInfo.getName(list.get(i))));
    }

    private void placeAction(Player player){
        Zone actualPosition = askForPosition();
        boolean actionExecuteCorrect = false;
        boolean positionIsControlPoint = actualPosition.getIsControlZone();
        boolean positionIsFreeOrNotMine = (actualPosition.getOwner() == null || actualPosition.getOwner() != player);
        boolean positionHaveAUnitOfMine = (actualPosition.getUnit() != null && actualPosition.getUnit().getUserId() == player.getUser().getId());
        if(positionIsControlPoint && positionIsFreeOrNotMine && positionHaveAUnitOfMine) actionExecuteCorrect = true;
        if(actionExecuteCorrect) askToDiscardAnyCard(player);
        if(actionExecuteCorrect) System.out.println("Correct action, press enter to continue.");
        else System.out.println("Invalid action, press enter to continue.");
        System.console().readLine();
    }

    private Zone askForPosition() throws RuntimeException{
        System.out.println("Position (row column)");
        System.out.println("Example: 1 2");
        Zone zone = null;
        String option = System.console().readLine().replaceAll("\\s+","");
        int row = Integer.parseInt(String.valueOf(option.charAt(0)));
        int column = Integer.parseInt(String.valueOf(option.charAt(1)));
        zone = board.getZone(row, column);
        return zone;
    }

    private void askToDiscardAnyCard(Player player){
        System.out.println("Discard card:");
        showNamesInTerminal(player.getHand());
        String option = System.console().readLine();
        int index = Integer.parseInt(option);
        player.getHand().remove(index);
    }
}