package gameLogic.domain;

import java.time.LocalDate;

public class User {
    int id;
    String name;
    int victories;
    LocalDate lastVictory;

    public User(String givenName){
        this.id = (int) (Math.random()*100000); // Fake id
        this.name = givenName;
        this.victories = 0;
    }

    public User(String givenName, LocalDate date, int victories){
        this.id = (int) (Math.random()*100000); // Fake id
        this.name = givenName;
        this.victories = victories;
        lastVictory = date;
    }

    public String getName(){
        return this.name;
    }

    public int getVictories(){
        return this.victories;
    }

    public void addVictories(){
        this.victories++;
    }

    public LocalDate getLastVictory(){
        return this.lastVictory;
    }

    public void setLastVictory(LocalDate date){
        this.lastVictory = date;
    }

    public int getId(){
        return this.id;
    }

    public void playerWon(){
        this.victories++;
        this.lastVictory = LocalDate.now();
    }
}
