package com.tomskra.bomb;

import android.os.Bundle;

public class ExplosionActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explosion);

    }

    public void onResume()
    {
        super.onResume();
        playSound(4);
    }
}
