import gameLogic.controllers.PlayerController;
import gameLogic.IControllers.IBoardController;
import gameLogic.IControllers.IPlayerController;
import gameLogic.IControllers.IUserController;
import gameLogic.IRepository.IUserRepository;
import gameLogic.IServices.IBoardService;
import gameLogic.IServices.IPlayerService;
import gameLogic.IServices.IUserService;
import gameLogic.IUnit.IUnitInfo;
import gameLogic.controllers.BoardController;
import gameLogic.controllers.UserController;
import gameLogic.repository.UserRepository;
import gameLogic.services.PlayerService;
import gameLogic.services.BoardService;
import gameLogic.services.UserService;
import gameLogic.unit.UnitInfo;
import ui.terminalApp.MainMenu;

public class App {
    public static void main(String[] args) throws Exception {
        IUnitInfo unitInfo = new UnitInfo();
        IUserRepository userRepository = new UserRepository();
        IBoardService boardService = new BoardService();
        IPlayerService playerService = new PlayerService(userRepository, unitInfo);
        IUserService userService = new UserService(userRepository);
        IPlayerController playerController = new PlayerController(playerService);
        IUserController userController = new UserController(userService);
        IBoardController boardController = new BoardController(boardService);
        new MainMenu(playerController, userController, boardController);
    }
}
