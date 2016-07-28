package com.tomskra.bomb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

abstract public class AbstractActivity extends AppCompatActivity {

    protected int actual = 1;
    private Intent objIntent;

    public void playSound(int speed)
    {
        objIntent = new Intent(this, PlaySound.class);
        objIntent.putExtra("speed", speed);
        startService(objIntent);
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
