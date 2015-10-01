package com.paulilves.timer.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class TimerActivity extends Activity {

    private ITimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        TextView textView = (TextView) findViewById(R.id.time_view);
        timer = new SimpleTimer(textView);

        if (savedInstanceState != null) {
            timer.setDecisecs(savedInstanceState.getInt("decisecs"));
            timer.setIsRunning(savedInstanceState.getBoolean("isRunning"));
            timer.setWasRunning(savedInstanceState.getBoolean("wasRunning"));
        }

        timer.runTimer();
    }

    public void onClickStart(View view) {

        timer.setIsIncremental(true);

        if (timer.isRunning() == false) {
            timer.clickStart();
        }
        else{
            timer.clickStop();
        }
    }

    public void onClickCountdown(View view){

        timer.setIsIncremental(false);

        if (timer.isRunning() == false) {
            timer.clickStart();
        }
        else{
            timer.clickStop();
        }
    }

    public void onClickStop(View view) {
        timer.clickStop();
    }

    public void onClickReset(View view) {
        timer.clickReset();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.setWasRunning(timer.isRunning());
        timer.clickStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (timer.isWasRunning()) {
            timer.clickStart();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("decisecs", timer.getDecisecs());
        outState.putBoolean("isRunning", timer.isRunning());
        outState.putBoolean("wasRunning", timer.isWasRunning());

    }

    //Increment buttons

    public void onClickAddHours(View view){
        timer.addHours(1);
    }

    public void onClickAddMinutesTen(View view){
        timer.addMinutes(10);
    }

    public void onClickAddMinutes(View view){
        timer.addMinutes(1);
    }

    public void onClickAddSecondsTen(View view){
        timer.addSeconds(10);
    }

    public void onClickAddSeconds(View view){
        timer.addSeconds(1);
    }

    //Decrement buttons

    public void onClickSubHours(View view){
        timer.addHours(-1);
    }

    public void onClickSubMinutesTen(View view){
        timer.addMinutes(-10);
    }

    public void onClickSubMinutes(View view){
        timer.addMinutes(-1);
    }

    public void onClickSubSecondsTen(View view){
        timer.addSeconds(-10);
    }

    public void onClickSubSeconds(View view){
        timer.addSeconds(-1);
    }



}
