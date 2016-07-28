package com.tomskra.bomb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public TextView textView;

    private static String CODE = "267.649.224.635.159";

    private int actual = 1;

    public static Thread t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = preferences.edit();

        textView = (TextView) findViewById(R.id.textView);
        Button btn1 = (Button) findViewById(R.id.button1);
        Button btn2 = (Button) findViewById(R.id.button2);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        Button btn5 = (Button) findViewById(R.id.button5);
        Button btn6 = (Button) findViewById(R.id.button6);
        Button btn7 = (Button) findViewById(R.id.button7);
        Button btn8 = (Button) findViewById(R.id.button8);
        Button btn9 = (Button) findViewById(R.id.button9);
        Button btn10 = (Button) findViewById(R.id.button10);
        Button btn11 = (Button) findViewById(R.id.button11);
        Button btn12 = (Button) findViewById(R.id.button12);

        btn1.setOnClickListener(handleClick);
        btn2.setOnClickListener(handleClick);
        btn3.setOnClickListener(handleClick);
        btn4.setOnClickListener(handleClick);
        btn5.setOnClickListener(handleClick);
        btn6.setOnClickListener(handleClick);
        btn7.setOnClickListener(handleClick);
        btn8.setOnClickListener(handleClick);
        btn9.setOnClickListener(handleClick);
        btn10.setOnClickListener(handleClick);
        btn11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = textView.getText().toString();
                if (text.length() > 0) {
                    textView.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = textView.getText().toString();
                if (text.equals(CODE)) {
                    editor.putString("stav", "deaktivovano");
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    editor.putString("cas", currentDateTimeString);
                    editor.apply();
                    Intent intent = new Intent(v.getContext(), SuccessActivity.class);
                    startActivity(intent);
                } else if(text.equals("636")){
                    //zobrazeni casu odpaleni / deaktivace
                    String stav = preferences.getString("stav", "");
                    String cas = preferences.getString("cas", "");
                    textView.setText(stav + cas);
                }
                else {
                    if (actual == 3){
                        editor.putString("stav", "vybuch");
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        editor.putString("cas", currentDateTimeString);
                        editor.commit();
                        Intent intent = new Intent(v.getContext(), ExplosionActivity.class);
                        startActivity(intent);
                        t1 = new Thread(new Runnable() {
                            public void run()
                            {
                                playSound(4);
                            }});
                        t1.start();

                    }
//                    textView.setTextColor(Color.RED);
//                    textView.setText("Špatný kód");
//                    textView.setTextColor(Color.RED);
//                    SystemClock.sleep(1000);
//                    textView.setTextColor(Color.BLACK);
                    textView.setText("");
                    stopSound();
                    actual++;
                    playSound(actual);
                }

            }
        });
    }

    private View.OnClickListener handleClick = new View.OnClickListener() {
        public void onClick(View v) {
            String pressed = ((Button) v).getText().toString();
            String text = textView.getText().toString();

            if (text.length() > 18) {
                return;
            }
            if (text.length() == 3 || text.length() == 7 || text.length() == 11 || text.length() == 15){
                text = text.concat(".");
            }
            textView.setText(text + pressed);
        }
    };

    public void playSound(int speed)
    {
        Intent objIntent = new Intent(this, PlaySound.class);
        objIntent.putExtra("speed", speed);
        startService(objIntent);
    }

    public void stopSound()
    {
        Intent objIntent = new Intent(this, PlaySound.class);
        stopService(objIntent);
    }

    public void onResume()
    {
        super.onResume();
        playSound(actual);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
