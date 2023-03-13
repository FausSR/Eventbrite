package ui.terminalApp;

import gameLogic.IControllers.IBoardController;
import gameLogic.IControllers.IPlayerController;
import gameLogic.IControllers.IUserController;

public class MainMenu {

    private void startMenu(IPlayerController playerController, IUserController userController, IBoardController boardController){
        showMenu();
        String option = "";
        while(!option.equals("exit")){
            option = System.console().readLine();
            switch(option) {
                case "1":
                    Match match = new Match(playerController, userController, boardController);
                    match.setGame();
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

    public MainMenu(IPlayerController playerController, IUserController userController, IBoardController boardController){
        startMenu(playerController, userController, boardController);
    }

}
