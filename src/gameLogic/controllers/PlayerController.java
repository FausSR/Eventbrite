package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.IControllers.IPlayerController;
import gameLogic.IServices.IPlayerService;
import gameLogic.domain.Player;

public class PlayerController implements IPlayerController{
    private IPlayerService playerService;

    public PlayerController(IPlayerService playerService){
        this.playerService = playerService;
    }

    public ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId){
        return playerService.setPlayers(firstPlayerId, secondPlayerId);
    }

    public Player drawCards(Player player){
        return playerService.drawCards(player);
    }

    public Player fillBag(Player player){
        return playerService.fillBag(player);
    }
}
