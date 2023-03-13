package gameLogic.unit;

import java.util.HashMap;

import gameLogic.IUnit.IUnit;
import gameLogic.IUnit.IUnitInfo;

public class UnitInfo implements IUnitInfo{

    HashMap<Integer, SpecificUnitInfo> unitGeneralInformation;

    public UnitInfo(){
        unitGeneralInformation = new HashMap<Integer, SpecificUnitInfo>();
        generateTroopsInformation();
    }

    private void generateTroopsInformation(){
        SpecificUnitInfo archer = new SpecificUnitInfo(4, "Archer");
        unitGeneralInformation.put(1, archer);
        
        SpecificUnitInfo lancer = new SpecificUnitInfo(4, "Lancer");
        unitGeneralInformation.put(2, lancer);

        SpecificUnitInfo knight = new SpecificUnitInfo(5, "Knight");
        unitGeneralInformation.put(3, knight);

        SpecificUnitInfo berserker = new SpecificUnitInfo(4, "Berserker");
        unitGeneralInformation.put(4, berserker);

        SpecificUnitInfo cavalry = new SpecificUnitInfo(4, "Cavalry");
        unitGeneralInformation.put(5, cavalry);

        SpecificUnitInfo crossbowman = new SpecificUnitInfo(5, "Crossbowman");
        unitGeneralInformation.put(6, crossbowman);
        
        SpecificUnitInfo mercenary = new SpecificUnitInfo(5, "Mercenary");
        unitGeneralInformation.put(7, mercenary);
        
        SpecificUnitInfo swordsman = new SpecificUnitInfo(4, "Swordsman");
        unitGeneralInformation.put(8, swordsman);
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

    public HashMap<Integer, SpecificUnitInfo> getUnitGeneralInformation() {
        return unitGeneralInformation;
    }
}
