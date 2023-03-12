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

        SpecificUnitInfo crossbowman = new SpecificUnitInfo();
        cavalry.name = "Crossbowman";
        cavalry.totalAmount = 5;
        unitGeneralInformation.put(6, crossbowman);
        
        SpecificUnitInfo mercenary = new SpecificUnitInfo();
        knight.name = "Mercenary";
        knight.totalAmount = 5;
        unitGeneralInformation.put(7, mercenary);
        
        SpecificUnitInfo swordsman = new SpecificUnitInfo();
        knight.name = "Swordsman";
        knight.totalAmount = 4;
        unitGeneralInformation.put(7, swordsman);
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
            case 6:
                return new Crossbowman(unitType, userId);
            case 7:
                return new Mercenary(unitType, userId);
            case 8:
                return new Swordsman(unitType, userId);
            default:
                System.out.println("That unit doesn't exists"); // throw exception
                return null;
        }
    }

    public int getAmount(int unitType){
        if(isRoyalUnit(unitType)) return 1;
        int value = unitGeneralInformation.get(unitType).totalAmount;
        return value;
    }

    public String getName(int unitType){
        if(isRoyalUnit(unitType)) return "Royal";
        String value = unitGeneralInformation.get(unitType).name;
        return value;
    }

    public boolean isRoyalUnit(int unitType){
        if(unitType == 99) return true;
        return false;
    }

}

class SpecificUnitInfo{
    int totalAmount;
    String name;
}
