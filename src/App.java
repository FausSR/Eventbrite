import gameLogic.controllers.PlayerController;
import UI.TerminalApp.MainMenu;
import gameLogic.controllers.BoardController;
import gameLogic.controllers.UserController;
import gameLogic.repository.UserRepository;
import gameLogic.services.PlayerService;
import gameLogic.services.BoardService;
import gameLogic.services.UserService;

public class App {
    public static void main(String[] args) throws Exception {
        UserRepository userRepository = new UserRepository();
        BoardService boardService = new BoardService();
        PlayerService playerService = new PlayerService(userRepository);
        UserService userService = new UserService(userRepository);
        PlayerController playerController = new PlayerController(playerService);
        UserController userController = new UserController(userService);
        BoardController boardController = new BoardController(boardService);
        new MainMenu(playerController, userController, boardController);
    }
}
