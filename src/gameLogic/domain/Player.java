package gameLogic.domain;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private ArrayList<Integer> discard, bag, selectedCards, hand;
    private String color;
    private int playerNumber, controlPoints, deployedUnits;
    private HashMap<Integer, Integer> recruitment;
    private User user;

    public Player(User user){
        this.user = user;
        this.hand = new ArrayList<>();
        this.discard = new ArrayList<>();
        this.bag = new ArrayList<>();
        this.selectedCards = new ArrayList<>();
        this.recruitment = new HashMap<>();
        this.color = "\033[0;37m";
        this.controlPoints = 0;
        this.deployedUnits = 0;
    }

    public void setControlPoints(int controlPoints) {
        this.controlPoints = controlPoints;
    }

    public int getControlPoints() {
        return controlPoints;
    }

    public void substractControlPoint(){
        this.controlPoints--;
    }

    public void addControlPoint(){
        this.controlPoints++;
    }

    public void setDeployedUnits(int deployedUnits) {
        this.deployedUnits = deployedUnits;
    }

    public int getDeployedUnits() {
        return deployedUnits;
    }

    public void substractDeployedUnits(){
        this.deployedUnits--;
    }

    public void addDeployedUnits(){
        this.deployedUnits++;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public User getUser(){
        return user;
    }
    
    public ArrayList<Integer> getBag() {
        return bag;
    }

    public void setBag(ArrayList<Integer> bag) {
        this.bag = bag;
    }

    public HashMap<Integer, Integer> getRecruitment() {
        return recruitment;
    }
    
    public void setRecruitment(HashMap<Integer, Integer> recruitment) {
        this.recruitment = recruitment;
    }

    public ArrayList<Integer> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(ArrayList<Integer> selectedCards) {
        this.selectedCards = selectedCards;
    }

    public ArrayList<Integer> getHand(){
        return hand;
    }

    public void setHand(ArrayList<Integer> hand){
        this.hand = hand;
    }

    public ArrayList<Integer> getDiscard(){
        return discard;
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
        return Integer.compare(user.getId(), c.getUser().getId()) == 0;
    }
}
