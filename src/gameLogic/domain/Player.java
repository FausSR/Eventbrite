package gameLogic.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    ArrayList<Integer> discard, bag, selectedCards, hand;
    String color;
    int playerNumber;
    HashMap<Integer, Integer> recruitment;
    User user;

    public Player(User user){
        this.user = user;
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.bag = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.recruitment = new HashMap<>();
        this.color = "\033[0;37m";
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
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

    public ArrayList<Integer> getHand(){
        return this.hand;
    }

    public void setHand(ArrayList<Integer> hand){
        this.hand = hand;
    }

    public ArrayList<Integer> getDiscard(){
        return this.discard;
    }

    public void setDiscard(ArrayList<Integer> discard){
        this.discard = discard;
    }

    public void addToDiscard(ArrayList<Integer> discard){
        this.discard.addAll(discard);
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Player)) {
            return false;
        }
        Player c = (Player) o;
        return Integer.compare(user.id, c.getUser().getId()) == 0;
    }
}
