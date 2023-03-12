package UI;

import java.util.ArrayList;

import gameLogic.IUnit.IUnit;
import gameLogic.Unit.UnitInfo;
import gameLogic.controllers.PlayerController;
import gameLogic.controllers.TableController;
import gameLogic.controllers.UserController;
import gameLogic.domain.Player;
import gameLogic.domain.Table;
import gameLogic.domain.User;
import gameLogic.domain.Zone;

public class Match {
    PlayerController playerController;
    TableController tableController;
    UserController userController;
    ArrayList<Player> players;
    Table table;
    UnitInfo unitInfo;

    public Match(PlayerController playerController, UserController userController, TableController tableController){
        this.playerController = playerController;
        this.tableController = tableController;
        this.userController = userController;
        this.unitInfo = new UnitInfo();
    }

    public void startGame(){
        SelectPlayer selectPlayer = new SelectPlayer(userController);
        ArrayList<User> selectedPlayers = selectPlayer.selectPlayers();
        table = tableController.generateTable();
        players = playerController.setPlayers(selectedPlayers.get(0).getId(), selectedPlayers.get(1).getId());
        loadMap();
        showTable();
    }

    public void showTable(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        for(int i = 0; i <= table.getSize(); i++){
            if(i==0) System.out.print("| ");
            else System.out.print(String.format("| %s ", i - 1));
        }
        System.out.println("\n-----------------------");
        for(int i = 0; i < table.getSize(); i++){
            System.out.print(String.format("|%s", i));
            for(int j = 0; j < table.getSize(); j++){
                Zone zone = table.getZone(i, j);
                if(zone.getUnit() != null) System.out.print("| " + unitInfo.getShortName(zone.getUnit().getUnitType()));
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
        table.getZone(0,2).setIsControllZone(true);
        table.getZone(0,2).setOwner(players.get(0));
        table.getZone(4,2).setIsControllZone(true);
        table.getZone(4,2).setOwner(players.get(1));
        table.getZone(1,1).setIsControllZone(true);
        table.getZone(1,3).setIsControllZone(true);
        table.getZone(3,1).setIsControllZone(true);
        table.getZone(3,3).setIsControllZone(true);
    }
}
