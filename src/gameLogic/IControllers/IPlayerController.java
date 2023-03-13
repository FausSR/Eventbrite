package gameLogic.IControllers;

import java.util.ArrayList;

import gameLogic.domain.Player;

public interface IPlayerController {
    ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId);
    void drawCards(Player player);
    Player fillBag(Player player);
}
