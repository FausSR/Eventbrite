package gameLogic.players;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    String name;
    int victories;
    LocalDate lastVictory;
    ArrayList<Integer> hand, discard, bag;
    HashMap<Integer, Integer> recruitment;

    public Player(String givenName){
        this.name = givenName;
        this.victories = 0;
    }

    public String getName(){
        return this.name;
    }

    public int getVictories(){
        return this.victories;
    }

    public LocalDate getLastVictory(){
        return this.lastVictory;
    }

    public ArrayList<Integer> getHand() {
        return this.hand;
    }

    public ArrayList<Integer> getDiscard() {
        return this.discard;
    }

    public ArrayList<Integer> getBag() {
        return this.bag;
    }

    public HashMap<Integer, Integer> getRecruitment() {
        return this.recruitment;
    }

    public void finishMatch(boolean playerWon){
        this.hand = new ArrayList<Integer>();
        this.discard = new ArrayList<Integer>();
        this.bag = new ArrayList<Integer>();
        this.recruitment = new HashMap<Integer, Integer>();
        if(playerWon) {
            this.victories++;
            this.lastVictory = LocalDate.now();
        }
    }
}