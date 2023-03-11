package UI;

import java.util.ArrayList;

import gameLogic.controllers.GameLogic;
import gameLogic.players.Player;

public class Match {
    private GameLogic gameLogic;

    public Match(){
        gameLogic = new GameLogic(2);
    }

    public void startGame(){
        ArrayList<Player> selectedPlayers = selectPlayers();
        gameLogic.startGame(selectedPlayers.get(0), selectedPlayers.get(1));
    }

    private ArrayList<Player> selectPlayers(){
        ArrayList<Player> allPlayers = gameLogic.getPlayers();
        ArrayList<Player> selectedPlayers = new ArrayList<Player>();
        String option = "";
        while(selectedPlayers.size() != 2){
            System.out.println(String.format("----------------Select Player %s----------------", selectedPlayers.size() + 1));
            for(int i = 0; i < allPlayers.size(); i++) 
                System.out.println(String.format("%s - %s", i + 1, allPlayers.get(i).getName()));
            System.out.println("-----------------------------------------------");
            try{
                option = System.console().readLine();
                int value = Integer.parseInt(option);
                if(value > 0 && value <= allPlayers.size()){
                    selectedPlayers.add(allPlayers.get(value-1));
                    allPlayers.remove(value-1);
                } 
            }catch (NumberFormatException ex){
                System.out.print("Invalid input " + option);
            }
        }
        return selectedPlayers;
    }
}
