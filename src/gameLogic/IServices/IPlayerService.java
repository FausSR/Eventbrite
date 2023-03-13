package gameLogic.IServices;

import java.util.ArrayList;

import gameLogic.domain.Player;

public interface IPlayerService {
    ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId);
    Player drawCards(Player player);
    Player fillBag(Player player);
}
