package gameLogic.unit;

public class SpecificUnitInfo{
    int totalAmount;
    String name;

    public SpecificUnitInfo(int totalAmount, String name){
        this.totalAmount = totalAmount;
        this.name = name;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getName() {
        return name;
    }
}
