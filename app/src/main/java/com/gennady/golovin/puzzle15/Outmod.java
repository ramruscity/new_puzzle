package com.gennady.golovin.puzzle15;

/**
 * Created by Геннадий on 19.03.2016.
 */
public interface Outmod {

    void nextLevel();
    void saveToDB(String svName, String svTime, String svSteps, Integer currentLevel);
}
