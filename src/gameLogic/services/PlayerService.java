package gameLogic.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import gameLogic.IRepository.IUserRepository;
import gameLogic.IServices.IPlayerService;
import gameLogic.IUnit.IUnitInfo;
import gameLogic.domain.Player;

public class PlayerService implements IPlayerService{
    private IUserRepository userRepository;
    private IUnitInfo unitInfo;

    public PlayerService(IUserRepository userRepository, IUnitInfo unitInfo){
        this.userRepository = userRepository;
        this.unitInfo = unitInfo;
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
        actualPlayers = takeUnitsForSelectedCards(actualPlayers);
        firstPlayer = prepareBagAndRecruitment(firstPlayer);
        secondPlayer = prepareBagAndRecruitment(secondPlayer);
        return actualPlayers;
    }

    public Player drawCards(Player player){
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
        return player;
    }

    public Player fillBag(Player player){
        player.setBag(player.getDiscard());
        player.setDiscard(new ArrayList<Integer>());
        return player;
    }

    private ArrayList<Player> takeUnitsForSelectedCards(ArrayList<Player> actualPlayers){
        Random random = new Random();
        ArrayList<Integer> unitList = new ArrayList<Integer>(unitInfo.getUnitGeneralInformation().keySet());
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
        return actualPlayers;
    }

    private Player prepareBagAndRecruitment(Player actualPlayer){
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
        return actualPlayer;
    }
}
