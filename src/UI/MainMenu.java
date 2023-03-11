package UI;

import gameLogic.controllers.PlayerController;
import gameLogic.controllers.TableController;
import gameLogic.controllers.UserController;

public class MainMenu {

    private void startMenu(PlayerController playerController, UserController userController, TableController tableController){
        showMenu();
        String option = "";
        while(!option.equals("exit")){
            option = System.console().readLine();
            switch(option) {
                case "1":
                    Match match = new Match(playerController, userController, tableController);
                    match.startGame();
                    break;
                case "2":

                    break;
                case "3":

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
        System.out.println("3-View stats");
        System.out.println("----------------------------------------------");
    }

    public MainMenu(PlayerController playerController, UserController userController, TableController tableController){
        startMenu(playerController, userController, tableController);
    }

}
