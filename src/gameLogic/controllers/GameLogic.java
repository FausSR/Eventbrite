package gameLogic.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gameLogic.Unit.UnitInfo;
import gameLogic.players.Player;

public class GameLogic {
    int actualTurn, iniciative, playerNumber;
    ArrayList<Player> allPlayers;
    ArrayList<Player> actualPlayers;
    UnitInfo unitInfo;

    public GameLogic(int playerNumber){
        Player firstPlayer = new Player("Juan");
        Player secondPlayer = new Player("Miguel"); // Agarrarlos de BusinessLogic
        this.actualTurn = 0;
        this.iniciative = 0;
        this.playerNumber = playerNumber;
        this.allPlayers = new ArrayList<Player>();
        this.actualPlayers = new ArrayList<Player>();
        allPlayers.add(firstPlayer);
        allPlayers.add(secondPlayer);
    }

    public ArrayList<Player> getPlayers(){
        return allPlayers;
    }

    public void startGame(Player playerOne, Player PlayerTwo){
        actualPlayers.add(playerOne);
        actualPlayers.add(PlayerTwo);
        takeUnitsFromBag();
        prepareHandAndRecruitment();
    }

    private void takeUnitsFromBag(){
        unitInfo = new UnitInfo();
        Random random = new Random();
        ArrayList<Integer> unitList = new ArrayList<Integer>(unitInfo.unitGeneralInformation.keySet());
        int totalSelections = Math.floorDiv(unitList.size(), 2);
        ArrayList<Integer> playerOneCards = new ArrayList<Integer>();
        ArrayList<Integer> playerTwoCards = new ArrayList<Integer>();

        for (int i = 0; i < totalSelections*2 ; i++) {
            int selectedType = random.nextInt(unitList.size());
            if(i < totalSelections) playerOneCards.add(unitList.remove(selectedType));
            else playerTwoCards.add(unitList.remove(selectedType)); 
        }

        actualPlayers.get(0).setSelectedCards(playerOneCards);
        actualPlayers.get(1).setSelectedCards(playerTwoCards);
    }

    public void prepareHandAndRecruitment(){
        Player playerOne = actualPlayers.get(0);
        Player playerTwo = actualPlayers.get(1);
        HashMap<Integer, Integer> bag = new HashMap<>();
        HashMap<Integer, Integer> toRecruit = new HashMap<>();
        for(Integer unitType: playerOne.getSelectedCards()){
            bag.put(unitType,2);
            toRecruit.put(unitType, (unitInfo.getAmount(unitType) - 2));
        }
        HashMap<Integer, Integer> bag2 = new HashMap<>();
        HashMap<Integer, Integer> toRecruit2 = new HashMap<>();
        for(Integer unitType: playerTwo.getSelectedCards()){
            bag2.put(unitType,2);
            toRecruit2.put(unitType, (unitInfo.getAmount(unitType) - 2));
        }
    }
}
