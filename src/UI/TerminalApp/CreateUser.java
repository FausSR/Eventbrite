package ui.terminalApp;

import gameLogic.IControllers.IUserController;
import gameLogic.domain.User;

public class CreateUser {
    private IUserController userController;

    public CreateUser(IUserController userController){
        this.userController = userController;
        showMenu();
    }

    public void showMenu(){
        System.out.println("------------------Create User-----------------");
        System.out.print("Write new player name:"); 
        String userName = System.console().readLine();
        User user = new User(userName);
        userController.create(user);
    }
}
