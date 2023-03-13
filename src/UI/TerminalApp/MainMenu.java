package ui.terminalApp;

import java.util.ArrayList;
import java.util.Collections;

import gameLogic.IControllers.IBoardController;
import gameLogic.IControllers.IPlayerController;
import gameLogic.IControllers.IUserController;
import gameLogic.domain.User;

public class MainMenu {
    
    public MainMenu(IPlayerController playerController, IUserController userController, IBoardController boardController){
        startMenu(playerController, userController, boardController);
    }

    private void startMenu(IPlayerController playerController, IUserController userController, IBoardController boardController){
        String option = "";
        while(!option.equals("exit")){
            showMenu();
            option = System.console().readLine();
            switch(option) {
                case "1":
                    Match match = new Match(playerController, userController, boardController);
                    match.setGame();
                    match.startGame();
                    break;
                case "2":
                    showCreateUserMenu(userController);
                    break;
                case "3":
                    ShowStats(userController);
                    break;
                default:
                    break;
            }
        }
    }

    private void showMenu(){
        System.out.println("-------------------WarChest-------------------");
        System.out.println("1-Let's play");
        System.out.println("2-Create user");
        System.out.println("3-Top 5");
        System.out.println("----------------------------------------------");
    }

    private void showCreateUserMenu(IUserController userController){
        System.out.println("------------------Create User-----------------");
        System.out.print("Write new player name: "); 
        String userName = System.console().readLine();
        User user = new User(userName);
        userController.create(user);
    }

    private void ShowStats(IUserController userController){
        ArrayList<User> users =  userController.getAll();
        Collections.sort(users, User.userCompareByVictories);
        for(int i = 0; i < users.size() && i < 5; i++){
            User user = users.get(i);
            System.out.println("----------------------------------------------");
            System.out.println("Username: " + user.getName());
            System.out.println("Victories: " + user.getVictories());
            if(user.getVictories() != 0){
                System.out.println("Last victory: " + user.getLastVictory());
            }
            else{
                System.out.println("Last victory: Never");
            }
        }
    }

}
