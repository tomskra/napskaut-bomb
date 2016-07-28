package com.tomskra.bomb;

import android.os.Bundle;
import android.os.CountDownTimer;

public class ExplosionActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion);

        new CountDownTimer(1910, 1910) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                setContentView(R.layout.activity_black);
                stopSound();
            }

        }.start();
    }

    public void onResume()
    {
        super.onResume();
        playSound(4);
    }
}
