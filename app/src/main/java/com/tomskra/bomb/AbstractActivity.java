package com.tomskra.bomb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

abstract public class AbstractActivity extends AppCompatActivity {

    protected int actual = 1;
    private Intent objIntent;

    public void playSound(final int speed)
    {
        final Context ctx = this;

        new Thread(new Runnable() {
            public void run() {
                objIntent = new Intent(ctx, PlaySound.class);
                objIntent.putExtra("speed", speed);
                startService(objIntent);
            }
        }).start();

    }

    public void stopSound()
    {
//        Intent objIntent = new Intent(this, PlaySound.class);
        stopService(objIntent);
    }


    public void onPause()
    {
        super.onPause();
        stopSound();
    }

    public void onDestroy()
    {
        super.onDestroy();
        stopSound();
    }
}
