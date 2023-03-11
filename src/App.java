import UI.MainMenu;
import gameLogic.controllers.PlayerController;
import gameLogic.controllers.TableController;
import gameLogic.controllers.UserController;
import gameLogic.repository.UserRepository;
import gameLogic.services.PlayerService;
import gameLogic.services.TableService;
import gameLogic.services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        UserRepository userRepository = new UserRepository();
        TableService tablesService = new TableService();
        PlayerService playerService = new PlayerService(userRepository);
        UserService userService = new UserService(userRepository);
        PlayerController playerController = new PlayerController(playerService);
        UserController userController = new UserController(userService);
        TableController tableController = new TableController(tablesService);
        new MainMenu(playerController, userController, tableController);
    }
}
