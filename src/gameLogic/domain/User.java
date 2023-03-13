package gameLogic.domain;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private int victories;
    private LocalDate lastVictory;

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
        return victories;
    }

    public void addVictories(){
        this.victories++;
    }

    public LocalDate getLastVictory(){
        return lastVictory;
    }

    public void setLastVictory(LocalDate date){
        this.lastVictory = date;
    }

    public int getId(){
        return id;
    }

    public void playerWon(){
        this.victories++;
        this.lastVictory = LocalDate.now();
    }
}
