package appsforyou.junkcleaner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import me.itangqi.waveloadingview.WaveLoadingView;


public class BatterySaver extends AppCompatActivity {

    View view;
    WaveLoadingView mWaveLoadingView;
    ImageView powersaving, ultrasaving, normal;
    TextView hourn, minutes, hourp, minutep, houru, minutesu, hourmain, minutesmain;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            mWaveLoadingView.setProgressValue(level);
//            mWaveLoadingView.setBottomTitle(level+"%");
            mWaveLoadingView.setCenterTitle(level + "%");

            if (level <= 5) {
                hourn.setText(0 + "");
                minutes.setText(15 + "");

                hourp.setText(2 + "");
                minutep.setText(25 + "");

                houru.setText(3 + "");
                minutesu.setText(55 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(0 + "");
                    minutesmain.setText(15 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(2 + "");
                    minutesmain.setText(25 + "");
                }
            }
            if (level > 5 && level <= 10) {
                hourn.setText(0 + "");
                minutes.setText(30 + "");

                hourp.setText(3 + "");
                minutep.setText(5 + "");

                houru.setText(6 + "");
                minutesu.setText(0 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(0 + "");
                    minutesmain.setText(30 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(3 + "");
                    minutesmain.setText(5 + "");
                }
            }
            if (level > 10 && level <= 15) {
                hourn.setText(0 + "");
                minutes.setText(45 + "");

                hourp.setText(3 + "");
                minutep.setText(50 + "");

                houru.setText(8 + "");
                minutesu.setText(25 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(0 + "");
                    minutesmain.setText(45 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(3 + "");
                    minutesmain.setText(50 + "");
                }
            }
            if (level > 15 && level <= 25) {
                hourn.setText(1 + "");
                minutes.setText(30 + "");

                hourp.setText(4 + "");
                minutep.setText(45 + "");

                houru.setText(12 + "");
                minutesu.setText(55 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(1 + "");
                    minutesmain.setText(30 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(4 + "");
                    minutesmain.setText(45 + "");
                }
            }
            if (level > 25 && level <= 35) {
                hourn.setText(2 + "");
                minutes.setText(20 + "");

                hourp.setText(6 + "");
                minutep.setText(2 + "");

                houru.setText(19 + "");
                minutesu.setText(2 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(2 + "");
                    minutesmain.setText(20 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(6 + "");
                    minutesmain.setText(2 + "");
                }
            }
            if (level > 35 && level <= 50) {
                hourn.setText(5 + "");
                minutes.setText(20 + "");

                hourp.setText(9 + "");
                minutep.setText(25 + "");

                houru.setText(22 + "");
                minutesu.setText(0 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(5 + "");
                    minutesmain.setText(20 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(9 + "");
                    minutesmain.setText(20 + "");
                }
            }
            if (level > 50 && level <= 65) {
                hourn.setText(7 + "");
                minutes.setText(30 + "");

                hourp.setText(11 + "");
                minutep.setText(1 + "");

                houru.setText(28 + "");
                minutesu.setText(15 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(7 + "");
                    minutesmain.setText(30 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(11 + "");
                    minutesmain.setText(1 + "");
                }
            }
            if (level > 65 && level <= 75) {
                hourn.setText(9 + "");
                minutes.setText(10 + "");

                hourp.setText(14 + "");
                minutep.setText(25 + "");

                houru.setText(30 + "");
                minutesu.setText(55 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(9 + "");
                    minutesmain.setText(10 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(14 + "");
                    minutesmain.setText(25 + "");
                }
            }
            if (level > 75 && level <= 85) {
                hourn.setText(14 + "");
                minutes.setText(15 + "");

                hourp.setText(17 + "");
                minutep.setText(10 + "");

                houru.setText(38 + "");
                minutesu.setText(5 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(14 + "");
                    minutesmain.setText(15 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(17 + "");
                    minutesmain.setText(10 + "");
                }
            }
            if (level > 85 && level <= 100) {
                hourn.setText(20 + "");
                minutes.setText(45 + "");

                hourp.setText(30 + "");
                minutep.setText(0 + "");

                houru.setText(60 + "");
                minutesu.setText(55 + "");

                if (sharedpreferences.getString("mode", "0").equals("0")) {
                    hourmain.setText(20 + "");
                    minutesmain.setText(45 + "");
                }

                if (sharedpreferences.getString("mode", "0").equals("1")) {
                    hourmain.setText(30 + "");
                    minutesmain.setText(0 + "");
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battery_saver);
        mWaveLoadingView = (WaveLoadingView) findViewById(R.id.waveView);
        powersaving = (ImageView)findViewById(R.id.powersaving);
        ultrasaving = (ImageView)findViewById(R.id.ultra);
        normal = (ImageView)findViewById(R.id.normal);
        hourn = (TextView) findViewById(R.id.hourn);
        minutes = (TextView) findViewById(R.id.minutes);
        hourp = (TextView) findViewById(R.id.hourp);
        minutep = (TextView) findViewById(R.id.minutesp);
        houru = (TextView) findViewById(R.id.houru);
        minutesu = (TextView)findViewById(R.id.minutesu);
        hourmain = (TextView) findViewById(R.id.hourmain);
        minutesmain = (TextView) findViewById(R.id.minutesmain);
        sharedpreferences = getSharedPreferences("tar", Context.MODE_PRIVATE);
        registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        try {

            powersaving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BatterySaver.this, PowerSaving_popup.class);
                    i.putExtra("hour", hourp.getText());
                    i.putExtra("minutes", minutep.getText());
                    i.putExtra("minutesnormal", minutes.getText());
                    i.putExtra("hournormal", hourn.getText());
                    startActivity(i);
                }
            });

            ultrasaving.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BatterySaver.this, Ultra_PopUp.class);
                    i.putExtra("hour", houru.getText());
                    i.putExtra("minutes", minutesu.getText());
                    i.putExtra("minutesnormal", minutes.getText());
                    i.putExtra("hournormal", hourn.getText());
                    startActivity(i);
                }
            });

            normal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BatterySaver.this, Noraml_Mode.class);
                    startActivity(i);
                }
            });


            mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
            mWaveLoadingView.setCenterTitleColor(Color.parseColor("#FFFFFF"));
            mWaveLoadingView.setBottomTitleColor(Color.parseColor("#FFFFFF"));
            mWaveLoadingView.setBorderWidth(10);
            mWaveLoadingView.setAmplitudeRatio(30);
            mWaveLoadingView.setWaveColor(Color.parseColor("#2499E0"));
            mWaveLoadingView.setBorderColor(Color.parseColor("#000000"));
            mWaveLoadingView.setTopTitleStrokeColor(Color.BLUE);
            mWaveLoadingView.setTopTitleStrokeWidth(3);
            mWaveLoadingView.setAnimDuration(3000);
//        mWaveLoadingView.pauseAnimation();
//        mWaveLoadingView.resumeAnimation();
//        mWaveLoadingView.cancelAnimation();
            mWaveLoadingView.startAnimation();


        } catch (Exception e) {

        }



    }


    @Override
    public void onResume() {
        super.onResume();
//        MainActivity.name.setText("Battery Saver");
        registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
          unregisterReceiver(mBatInfoReceiver);
        }
        catch(Exception e)
        {

        }
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//
//
//        if (isVisibleToUser) {
//            MainActivity.name.setText("Battery Saver");
//
//        } else {
//
//        }
//    }


}
