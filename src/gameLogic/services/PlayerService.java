package gameLogic.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import gameLogic.Unit.UnitInfo;
import gameLogic.domain.Player;
import gameLogic.repository.UserRepository;

public class PlayerService {
    UserRepository userRepository;
    ArrayList<Player> actualPlayers;
    UnitInfo unitInfo;

    public PlayerService(UserRepository userRepository){
        this.userRepository = userRepository;
        this.actualPlayers = new ArrayList<>();
    }

    public ArrayList<Player> setPlayers(int firstPlayerId, int secondPlayerId){
        actualPlayers.add(new Player(userRepository.get(firstPlayerId)));
        actualPlayers.add(new Player(userRepository.get(secondPlayerId)));
        takeUnitsForBag();
        prepareBagAndRecruitment();
        drawCards();
        return actualPlayers;
    }

    private void takeUnitsForBag(){
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

    private void prepareBagAndRecruitment(){
        ArrayList<Integer> firstBag = new ArrayList<>();
        HashMap<Integer, Integer> toRecruit = new HashMap<>();
        for(Integer unitType: actualPlayers.get(0).getSelectedCards()){
            firstBag.add(unitType);
            firstBag.add(unitType);
            toRecruit.put(unitType, (unitInfo.getAmount(unitType) - 2));
        }
        actualPlayers.get(0).setBag(firstBag);

        ArrayList<Integer> secondBag = new ArrayList<>();
        HashMap<Integer, Integer> secondRecruitment = new HashMap<>();
        for(Integer unitType: actualPlayers.get(1).getSelectedCards()){
            secondBag.add(unitType);
            secondBag.add(unitType);
            secondRecruitment.put(unitType, (unitInfo.getAmount(unitType) - 2));
        }
    }

    private void drawCards(){
        
    }

}
