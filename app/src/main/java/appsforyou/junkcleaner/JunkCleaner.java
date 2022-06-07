package appsforyou.junkcleaner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;


public class JunkCleaner extends AppCompatActivity {

    ImageView mainbrush, cache, temp, residue, system;
    TextView maintext, cachetext, temptext, residuetext, systemtext;
    public static ImageView mainbutton;

    int checkvar = 0;
    int alljunk;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.junk_cleaner);
        mainbrush = (ImageView) findViewById(R.id.mainbrush);
        mainbutton = (ImageView) findViewById(R.id.mainbutton);
        cache = (ImageView) findViewById(R.id.cache);
        temp = (ImageView) findViewById(R.id.temp);
        residue = (ImageView) findViewById(R.id.residue);
        system = (ImageView) findViewById(R.id.system);


        maintext = (TextView) findViewById(R.id.maintext);
        cachetext = (TextView) findViewById(R.id.cachetext);
        temptext = (TextView) findViewById(R.id.temptext);
        residuetext = (TextView) findViewById(R.id.residuetext);
        systemtext = (TextView) findViewById(R.id.systemtext);

        try {

            sharedpreferences = getSharedPreferences("tarun", Context.MODE_PRIVATE);


            if (sharedpreferences.getString("junk", "1").equals("1")) {
                mainbrush.setImageResource(R.drawable.junk_red);
                mainbutton.setImageResource(R.drawable.clean);
                cache.setImageResource(R.drawable.cache);
                temp.setImageResource(R.drawable.temp);
                residue.setImageResource(R.drawable.res);
                system.setImageResource(R.drawable.sys);

                Random ran1 = new Random();
                final int proc1 = ran1.nextInt(20) + 5;

                Random ran2 = new Random();
                final int proc2 = ran2.nextInt(15) + 10;

                Random ran3 = new Random();
                final int proc3 = ran3.nextInt(30) + 15;

                Random ran4 = new Random();
                final int proc4 = ran4.nextInt(25) + 10;

                alljunk = proc1 + proc2 + proc3 + proc4;

                maintext.setText(alljunk + " MB");
                maintext.setTextColor(Color.parseColor("#F22938"));

                cachetext.setText(proc1 + " MB");
                cachetext.setTextColor(Color.parseColor("#F22938"));

                temptext.setText(proc2 + " MB");
                temptext.setTextColor(Color.parseColor("#F22938"));

                residuetext.setText(proc3 + " MB");
                residuetext.setTextColor(Color.parseColor("#F22938"));

                systemtext.setText(proc4 + " MB");
                systemtext.setTextColor(Color.parseColor("#F22938"));

            } else {
                mainbrush.setImageResource(R.drawable.junk_blue);
                mainbutton.setImageResource(R.drawable.cleaned);
                cache.setImageResource(R.drawable.cache2);
                temp.setImageResource(R.drawable.temp2);
                residue.setImageResource(R.drawable.res2);
                system.setImageResource(R.drawable.sys2);


                maintext.setText("CRYSTAL CLEAR");
                maintext.setTextColor(Color.parseColor("#24D149"));

                cachetext.setText(0 + " MB");
                cachetext.setTextColor(Color.parseColor("#24D149"));

                temptext.setText(0 + " MB");
                temptext.setTextColor(Color.parseColor("#24D149"));

                residuetext.setText(0 + " MB");
                residuetext.setTextColor(Color.parseColor("#24D149"));

                systemtext.setText(0 + " MB");
                systemtext.setTextColor(Color.parseColor("#24D149"));
            }


            mainbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (sharedpreferences.getString("junk", "1").equals("1")) {

                        Intent i = new Intent(JunkCleaner.this, Sacnning_Junk.class);
                        i.putExtra("junk", alljunk + "");
                        startActivity(i);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms


                                mainbrush.setImageResource(R.drawable.junk_blue);
                                mainbutton.setImageResource(R.drawable.cleaned);
                                cache.setImageResource(R.drawable.cache2);
                                temp.setImageResource(R.drawable.temp2);
                                residue.setImageResource(R.drawable.res2);
                                system.setImageResource(R.drawable.sys2);


                                maintext.setText("CRYSTAL CLEAR");
                                maintext.setTextColor(Color.parseColor("#24D149"));

                                cachetext.setText(0 + " MB");
                                cachetext.setTextColor(Color.parseColor("#24D149"));

                                temptext.setText(0 + " MB");
                                temptext.setTextColor(Color.parseColor("#24D149"));

                                residuetext.setText(0 + " MB");
                                residuetext.setTextColor(Color.parseColor("#24D149"));

                                systemtext.setText(0 + " MB");
                                systemtext.setTextColor(Color.parseColor("#24D149"));


                                editor = sharedpreferences.edit();
                                editor.putString("junk", "0");
                                editor.commit();


                                Intent intent = new Intent(JunkCleaner.this, Alaram_Junk.class);

                                PendingIntent pendingIntent = PendingIntent.getBroadcast(JunkCleaner.this, 0,
                                        intent, PendingIntent.FLAG_ONE_SHOT);

                                AlarmManager alarmManager = (AlarmManager) JunkCleaner.this.getSystemService(ALARM_SERVICE);
                                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (600 * 1000), pendingIntent);

                            }
                        }, 2000);
                    } else {
//                        Toast.makeText(getActivity(), "No Junk Files ALready Cleaned.", Toast.LENGTH_LONG).show();

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.my_toast, null);

                        TextView text = (TextView) layout.findViewById(R.id.textView1);
                        text.setText("No Junk Files ALready Cleaned.");

                        Toast toast = new Toast(JunkCleaner.this);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                    }
                }
            });


//            Random ran1 = new Random();
//            final int proc1 = ran1.nextInt(20) + 5;
//
//            Random ran2 = new Random();
//            final int proc2 = ran2.nextInt(15) + 10;
//
//            Random ran3 = new Random();
//            final int proc3 = ran3.nextInt(30) + 15;
//
//            Random ran4 = new Random();
//            final int proc4 = ran4.nextInt(25) + 10;
//
//            alljunk=proc1+proc2+proc3+proc4;
//
//            maintext.setText(alljunk+" MB");
//            maintext.setTextColor(Color.parseColor("#F22938"));
//
//            cachetext.setText(proc1+" MB");
//            cachetext.setTextColor(Color.parseColor("#F22938"));
//
//            temptext.setText(proc2+" MB");
//            temptext.setTextColor(Color.parseColor("#F22938"));
//
//            residuetext.setText(proc3+" MB");
//            residuetext.setTextColor(Color.parseColor("#F22938"));
//
//            systemtext.setText(proc4+" MB");
//            systemtext.setTextColor(Color.parseColor("#F22938"));

        } catch (Exception e) {

        }

    }
}
