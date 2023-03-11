package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.domain.User;
import gameLogic.services.UserService;

public class UserController {
    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public ArrayList<User> getAll(){
        return userService.getAll();
    }

    public User get(int id){
        return userService.get(id);
    }
}
