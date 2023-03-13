package gameLogic.IRepository;

import java.util.ArrayList;

import gameLogic.domain.User;

public interface IUserRepository {
    ArrayList<User> getPlayers();
    User get(int id);
    User set(User user);
}
