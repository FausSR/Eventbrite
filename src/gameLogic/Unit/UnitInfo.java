package gameLogic.Unit;

import java.util.HashMap;

import gameLogic.IUnit.IUnit;

public class UnitInfo {

    public HashMap<Integer, SpecificUnitInfo> unitGeneralInformation;

    public UnitInfo(){
        unitGeneralInformation = new HashMap<Integer, SpecificUnitInfo>();
        generateTroopsInformation();
    }

    private void generateTroopsInformation(){
        SpecificUnitInfo archer = new SpecificUnitInfo();
        archer.name = "Archer";
        archer.totalAmount = 4;
        unitGeneralInformation.put(1, archer);
        
        SpecificUnitInfo lancer = new SpecificUnitInfo();
        lancer.name = "Lancer";
        lancer.totalAmount = 4;
        unitGeneralInformation.put(2, lancer);

        SpecificUnitInfo knight = new SpecificUnitInfo();
        knight.name = "Knight";
        knight.totalAmount = 5;
        unitGeneralInformation.put(3, knight);

        SpecificUnitInfo berserker = new SpecificUnitInfo();
        berserker.name = "Berserker";
        berserker.totalAmount = 4;
        unitGeneralInformation.put(4, berserker);

        SpecificUnitInfo cavalry = new SpecificUnitInfo();
        cavalry.name = "Cavalry";
        cavalry.totalAmount = 4;
        unitGeneralInformation.put(5, cavalry);
    }

    public IUnit unitConstructor(int unitType, int userId){
        switch(unitType){
            case 1:
                return new Archer(unitType, userId);
            case 2:
                return new Lancer(unitType, userId);
            case 3:
                return new Knight(unitType, userId);
            case 4:
                return new Berserker(unitType, userId);
            case 5:
                return new Cavalry(unitType, userId);
            default:
                System.out.println("That unit doesn't exists"); // throw exception
                return null;
        }
    }

    public int getAmount(int unitType){
        if(unitType == 99) return 1;
        int value = unitGeneralInformation.get(unitType).totalAmount;
        return value;
    }

    public String getName(int unitType){
        if(unitType == 99) return "Royal";
        String value = unitGeneralInformation.get(unitType).name;
        return value;
    }

}

class SpecificUnitInfo{
    int totalAmount;
    String name;
}
