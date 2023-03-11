package gameLogic.Unit;

import java.util.HashMap;

public class UnitInfo {

    public HashMap<Integer, SpecificUnitInfo> unitGeneralInformation;

    public UnitInfo(){
        unitGeneralInformation = new HashMap<Integer, SpecificUnitInfo>();
        generateTroopsInformation();
    }

    private void generateTroopsInformation(){
        SpecificUnitInfo archer = new SpecificUnitInfo();
        archer.name = "Archer";
        archer.shortName = "Ar";
        archer.totalAmount = 4;
        unitGeneralInformation.put(1, archer);
        
        SpecificUnitInfo lancer = new SpecificUnitInfo();
        lancer.name = "Lancer";
        lancer.shortName = "La";
        lancer.totalAmount = 4;
        unitGeneralInformation.put(2, lancer);

        SpecificUnitInfo knight = new SpecificUnitInfo();
        knight.name = "Knight";
        knight.shortName = "Kn";
        knight.totalAmount = 5;
        unitGeneralInformation.put(3, knight);

        SpecificUnitInfo berserker = new SpecificUnitInfo();
        berserker.name = "Berserker";
        berserker.shortName = "Br";
        berserker.totalAmount = 4;
        unitGeneralInformation.put(4, berserker);

        SpecificUnitInfo cavalry = new SpecificUnitInfo();
        cavalry.name = "Cavalry";
        cavalry.shortName = "Ca";
        cavalry.totalAmount = 4;
        unitGeneralInformation.put(5, cavalry);
    }

    public int getAmount(int unitId){
        int value = unitGeneralInformation.get(unitId).totalAmount;
        return value;
    }

    public String getName(int unitId){
        String value = unitGeneralInformation.get(unitId).name;
        return value;
    }

    public String getShortName(int unitId){
        String value = unitGeneralInformation.get(unitId).shortName;
        return value;
    }
}

class SpecificUnitInfo{
    int totalAmount;
    String name, shortName;
}
