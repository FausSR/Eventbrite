package gameLogic.repository;

import java.util.ArrayList;

import gameLogic.IRepository.IUserRepository;
import gameLogic.domain.User;

public class UserRepository implements IUserRepository {
    private ArrayList<User> players;

    public ArrayList<User> getPlayers(){
        ArrayList<User> users = new ArrayList<>(players);
        return users;
    }

    public User get(int id){
        for (User user : players) {
            if(user.getId() == id) return user;
        }
        return null;
    }
    
    public UserRepository(){
        User firstUser = new User("Juan");
        User secondUser = new User("Miguel");
        this.players = new ArrayList<User>();
        players.add(firstUser);
        players.add(secondUser);
    }
}
