package gameLogic.controllers;

import java.util.ArrayList;

import gameLogic.domain.Player;
import gameLogic.services.PlayerService;

public class PlayerController {
    PlayerService playerService;

    public PlayerController(PlayerService playerService){
        this.playerService = playerService;
    }

    public ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId){
        return playerService.setPlayers(firstPlayerId, secondPlayerId);
    }
}
