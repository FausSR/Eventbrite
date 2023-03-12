package gameLogic.services;

import java.util.ArrayList;

import gameLogic.domain.User;
import gameLogic.repository.UserRepository;

public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ArrayList<User> getAll(){
        return userRepository.getPlayers();
    }
}
