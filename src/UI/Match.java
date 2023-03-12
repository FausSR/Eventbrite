package UI;

import java.util.ArrayList;

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
        ArrayList<Integer> actualHand = new ArrayList<>(player.getHand());
        ArrayList<Integer> actualDiscard = new ArrayList<>();
        ArrayList<Integer> totalDiscard = new ArrayList<>(player.getDiscard());
        while(actualHand.size() > 0){
            showBoard();
            System.out.println(String.format("Hand %s", actualHand.toString()));
            System.console().readLine();
        }
    }
}