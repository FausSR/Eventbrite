package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.IControllers.IUserController;
import gameLogic.IServices.IUserService;
import gameLogic.domain.User;

public class UserController implements IUserController{
    private IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    public ArrayList<User> getAll(){
        return userService.getAll();
    }

    public User create(User user){
        return userService.create(user);
    }
}
