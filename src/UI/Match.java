package UI;

import java.util.ArrayList;

import gameLogic.Table.Table;
import gameLogic.controllers.PlayerController;
import gameLogic.controllers.TableController;
import gameLogic.controllers.UserController;
import gameLogic.domain.Player;
import gameLogic.domain.User;

public class Match {
    PlayerController playerController;
    TableController tableController;
    UserController userController;
    ArrayList<Player> players;
    Table table;

    public Match(PlayerController playerController, UserController userController, TableController tableController){
        this.playerController = playerController;
        this.tableController = tableController;
        this.userController = userController;
    }

    public void startGame(){
        SelectPlayer selectPlayer = new SelectPlayer(userController);
        ArrayList<User> selectedPlayers = selectPlayer.selectPlayers();
        table = tableController.generateTable();
        players = playerController.setPlayers(selectedPlayers.get(0).getId(), selectedPlayers.get(1).getId());
    }

    public void generateTable(){
        System.out.println("-------------------WarChest-------------------");
        System.out.println("1-Let's play");
        System.out.println("2-Create user");
        System.out.println("3-View stats");
        System.out.println("----------------------------------------------");
    }
}
