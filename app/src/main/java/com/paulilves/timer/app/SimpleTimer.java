package com.paulilves.timer.app;

import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Paul on 29-Sep-15.
 */
public class SimpleTimer implements ITimer {

    private int decisecs;
    private boolean isRunning;
    private boolean wasRunning;
    private boolean isIncremental = true;
    final private TextView timeView;

    public boolean isIncremental() {
        return isIncremental;
    }

    public void setIsIncremental(boolean isIncremental) {
        this.isIncremental = isIncremental;
    }

    @Override
    public boolean isWasRunning() {
        return wasRunning;
    }

    @Override
    public void setWasRunning(boolean wasRunning) {
        this.wasRunning = wasRunning;
    }

    public SimpleTimer(TextView timeView) {
        this.timeView = timeView;
    }

    public void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = decisecs / 10 / 3600;
                int minutes = (decisecs / 10 % 3600) / 60;
                int secs = decisecs / 10;
                int deciseconds = decisecs;

                if (secs >= 59) {
                    secs = decisecs / 10 % 60;
                }
                if (minutes >= 59) {
                    minutes = minutes % 60;
                }
                if (deciseconds >= 9) {
                    deciseconds = deciseconds % 10;
                }

                String time = String.format("%d:%02d:%02d.%01d", hours, minutes, secs, deciseconds);
                timeView.setText(time);

                if (isRunning && isIncremental) {
                    decisecs++;
                } else if (isRunning && !isIncremental) {
                    decisecs--;
                    checkIfNegative();
                }

                handler.postDelayed(this, 100);
            }
        });
    }

    @Override
    public void clickStart() {
        isRunning = true;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getDecisecs() {
        return decisecs;
    }

    @Override
    public void addHours(int amount) {
        decisecs += 36000 * amount;
        checkIfNegative();
    }

    @Override
    public void addMinutes(int amount) {
        decisecs += 600 * amount;
        checkIfNegative();
    }

    @Override
    public void addSeconds(int amount) {
        decisecs += 10 * amount;
        checkIfNegative();
    }

    private void checkIfNegative() {
        if (decisecs < 0) {
            decisecs = 0;
        }
    }

    @Override
    public void setDecisecs(int decisecs) {
        this.decisecs = decisecs;
    }

    @Override
    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void clickStop() {

        isRunning = false;
    }

    @Override
    public void clickReset() {
        isRunning = false;
        decisecs = 0;
    }
}
