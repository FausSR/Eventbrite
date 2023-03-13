package ui.terminalApp;

import java.util.ArrayList;

import gameLogic.IControllers.IUserController;
import gameLogic.domain.User;

public class SelectPlayer {
    private IUserController userController;

    public SelectPlayer(IUserController userController){
        this.userController = userController;
    }

    public ArrayList<User> selectPlayers(){
        ArrayList<User> allPlayers = userController.getAll();
        ArrayList<User> selectedPlayers = new ArrayList<User>();
        String option = "";
        while(selectedPlayers.size() != 2){
            System.out.println(String.format("----------------Select Player %s----------------", selectedPlayers.size() + 1));
            for(int i = 0; i < allPlayers.size(); i++) 
                System.out.println(String.format("%s - %s", i + 1, allPlayers.get(i).getName()));
            System.out.println("-----------------------------------------------");
            try{
                option = System.console().readLine();
                int value = Integer.parseInt(option) - 1;
                if(value >= 0 && value < allPlayers.size()){
                    selectedPlayers.add(allPlayers.get(value));
                    allPlayers.remove(value);
                } 
            }catch (NumberFormatException ex){
                System.out.print("Invalid input " + option);
            }
        }
        return selectedPlayers;
    }
}
