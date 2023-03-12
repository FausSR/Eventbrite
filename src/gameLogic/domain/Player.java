package gameLogic.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    ArrayList<Integer> hand, discard, bag, selectedCards;
    HashMap<Integer, Integer> recruitment;
    User user;

    public Player(User user){
        this.user = user;
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.bag = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.recruitment = new HashMap<>();
    }

    public User getUser(){
        return this.user;
    }
    
    public ArrayList<Integer> getBag() {
        return this.bag;
    }

    public void setBag(ArrayList<Integer> bag) {
        this.bag = bag;
    }

    public HashMap<Integer, Integer> getRecruitment() {
        return this.recruitment;
    }
    
    public void setRecruitment(HashMap<Integer, Integer> recruitment) {
        this.recruitment = recruitment;
    }

    public ArrayList<Integer> getSelectedCards() {
        return this.selectedCards;
    }

    public void setSelectedCards(ArrayList<Integer> selectedCards) {
        this.selectedCards = selectedCards;
    }
}
