package com.tomskra.bomb;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlaySound extends Service
{
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void onCreate()
    {

    }

    public int onStartCommand(Intent intent, int flags, int startId)
    {
        int speed = intent.getIntExtra("speed", 1);

        int sound;
        switch(speed){
            case 1:
                sound = R.raw.bombslow;
                break;
            case 2:
                sound = R.raw.bombfast1;
                break;
            case 3:
                sound = R.raw.bombfast2;
                break;
            case 4:
                sound = R.raw.explosion;
                break;

            default:
                sound = R.raw.bombslow;
        }

        mp = MediaPlayer.create(this, sound);
        mp.setLooping(true);
        mp.start();
        return START_STICKY;
    }

    public void onDestroy()
    {
        mp.stop();
    }

}
