package gameLogic.repository;

import java.time.LocalDate;
import java.util.ArrayList;

import gameLogic.IRepository.IUserRepository;
import gameLogic.domain.User;

public class UserRepository implements IUserRepository {
    private ArrayList<User> users;

    public ArrayList<User> getPlayers(){
        ArrayList<User> allUsers = new ArrayList<>(users);
        return allUsers;
    }

    public User get(int id){
        for (User user : users) {
            if(user.getId() == id) return user;
        }
        return null;
    }

    public User set(User user){
        users.add(user);
        return user;
    }
    
    public UserRepository(){
        User firstUser = new User("Juan");
        User secondUser = new User("Miguel");
        firstUser.addVictories();
        firstUser.addVictories();
        secondUser.addVictories();
        secondUser.addVictories();
        secondUser.addVictories();
        firstUser.setLastVictory(LocalDate.now());
        secondUser.setLastVictory(LocalDate.now());
        this.users = new ArrayList<User>();
        users.add(firstUser);
        users.add(secondUser);
    }
}
