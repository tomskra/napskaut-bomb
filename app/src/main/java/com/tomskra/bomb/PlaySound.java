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
        mp = MediaPlayer.create(this, R.raw.bomb);
        mp.setLooping(true);
        mp.start();
        return START_STICKY;
    }

    public void onDestroy()
    {
        mp.stop();
    }

}
