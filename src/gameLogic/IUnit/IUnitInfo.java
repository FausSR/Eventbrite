package gameLogic.IUnit;

import java.util.HashMap;

import gameLogic.unit.SpecificUnitInfo;

public interface IUnitInfo {
    IUnit unitConstructor(int unitType, int userId);
    int getAmount(int unitType);
    String getName(int unitType);
    boolean isRoyalUnit(int unitType);
    HashMap<Integer, SpecificUnitInfo> getUnitGeneralInformation();
}
