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

    public IUnit unitConstructor(int unitType){
        switch(unitType){
            case 1:
                return new Archer(unitType);
            case 2:
                return new Lancer(unitType);
            case 3:
                return new Knight(unitType);
            case 4:
                return new Berserker(unitType);
            case 5:
                return new Cavalry(unitType);
            default:
                System.out.println("That unit doesn't exists");
                return null;
        }
    }

    public int getAmount(int unitId){
        int value = unitGeneralInformation.get(unitId).totalAmount;
        return value;
    }

    public String getName(int unitId){
        String value = unitGeneralInformation.get(unitId).name;
        return value;
    }

}

class SpecificUnitInfo{
    int totalAmount;
    String name;
}
