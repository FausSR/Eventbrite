package gameLogic.services;

import java.util.ArrayList;

import gameLogic.IRepository.IUserRepository;
import gameLogic.IServices.IUserService;
import gameLogic.domain.User;

public class UserService implements IUserService{
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAll(){
        return userRepository.getPlayers();
    }
}
