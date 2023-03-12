package gameLogic.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import gameLogic.Unit.UnitInfo;
import gameLogic.domain.Player;
import gameLogic.repository.UserRepository;

public class PlayerService {
    UserRepository userRepository;
    UnitInfo unitInfo;

    public PlayerService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId){
        ArrayList<Player> actualPlayers = new ArrayList<>();
        Player firstPlayer = new Player(userRepository.get(firstPlayerId));
        firstPlayer.setColor("\033[0;36m");
        firstPlayer.setPlayerNumber(0);
        Player secondPlayer = new Player(userRepository.get(secondPlayerId));
        secondPlayer.setColor("\033[0;33m");
        secondPlayer.setPlayerNumber(1);
        actualPlayers.add(firstPlayer);
        actualPlayers.add(secondPlayer);
        takeUnitsForSelectedCards(actualPlayers);
        prepareBagAndRecruitment(firstPlayer);
        prepareBagAndRecruitment(secondPlayer);
        drawCards(firstPlayer);
        drawCards(secondPlayer);
        return actualPlayers;
    }

    public void drawCards(Player player){
        player.addToDiscard(player.getHand());
        player.setHand(new ArrayList<Integer>());
        int maxCardsPerTurn = 3;
        ArrayList<Integer> bag = player.getBag();
        Collections.shuffle(bag);
        int totalIterations = 0;

        if(bag.size() <= maxCardsPerTurn) totalIterations = bag.size();
        else totalIterations = maxCardsPerTurn;

        for (int i = totalIterations - 1; i >= 0; i--) {
            player.getHand().add(bag.remove(i));
        }
    }

    public Player fillBag(Player player){
        player.setBag(player.getDiscard());
        player.setDiscard(new ArrayList<Integer>());
        return player;
    }

    private void takeUnitsForSelectedCards(ArrayList<Player> actualPlayers){
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

    private void prepareBagAndRecruitment(Player actualPlayer){
        ArrayList<Integer> firstBag = new ArrayList<>();
        HashMap<Integer, Integer> firstRecruitment = new HashMap<>();
        for(Integer unitType: actualPlayer.getSelectedCards()){
            firstBag.add(unitType);
            firstBag.add(unitType);
            firstRecruitment.put(unitType, (unitInfo.getAmount(unitType) - 2));
        }
        
        int unitTypeOfRoyalCoin = 99;
        firstBag.add(unitTypeOfRoyalCoin);

        actualPlayer.setBag(firstBag);
        actualPlayer.setRecruitment(firstRecruitment);
    }
}
