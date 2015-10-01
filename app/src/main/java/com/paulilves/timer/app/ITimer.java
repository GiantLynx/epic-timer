package com.paulilves.timer.app;

/**
 * Created by Paul on 29-Sep-15.
 */
public interface ITimer {

    void clickStart();

    void setDecisecs(int decisecs);

    void setIsRunning(boolean isRunning);
    void setIsIncremental(boolean isIncremental);
    boolean isIncremental();

    void clickStop();
    void clickReset();

    boolean isWasRunning();

    void setWasRunning(boolean wasRunning);

    void runTimer();

    boolean isRunning();

    int getDecisecs();

    void addHours(int amount);
    void addMinutes(int amount);
    void addSeconds(int amount);



}
