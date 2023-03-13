package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.IControllers.IPlayerController;
import gameLogic.IServices.IPlayerService;
import gameLogic.domain.Player;

public class PlayerController implements IPlayerController{
    IPlayerService playerService;

    public PlayerController(IPlayerService playerService){
        this.playerService = playerService;
    }

    public ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId){
        return playerService.setPlayers(firstPlayerId, secondPlayerId);
    }

    public void drawCards(Player player){
        playerService.drawCards(player);
    }

    public Player fillBag(Player player){
        return playerService.fillBag(player);
    }
}
