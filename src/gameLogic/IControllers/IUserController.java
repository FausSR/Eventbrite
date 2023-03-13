package gameLogic.IControllers;

import java.util.ArrayList;

import gameLogic.domain.User;

public interface IUserController {
    ArrayList<User> getAll();
    User create(User user);
}
