package appsforyou.junkcleaner;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.content.ContentValues.TAG;



public class BatterySaver_Black extends Activity {



    ///Apply Extrem Power Saving Mode by allowing very few Apps to run

    ImageView phone, internet, setting, messages, playstore, contacts,calculator,alaram,dots;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    TextView batteryremaning,timeremaning,disable;
    int check=0;



    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
//
//            mWaveLoadingView.setProgressValue(level);
////            mWaveLoadingView.setBottomTitle(level+"%");
//            mWaveLoadingView.setCenterTitle(level+"%");

            batteryremaning.setText("Battery Remaning "+level+"%");

            if(level<=5)
            {

//                houru.setText(3+"");
//                minutesu.setText(55+"");

                timeremaning.setText("3h 55m remaning");


            }
            if(level>5&&level<=10)
            {

//                houru.setText(6+"");
//                minutesu.setText(0+"");

                timeremaning.setText("6h 0m remaning");


            }
            if(level>10&&level<=15)
            {


//                houru.setText(8+"");
//                minutesu.setText(25+"");

                timeremaning.setText("8h 25m remaning");


            }
            if(level>15&&level<=25)
            {


//                houru.setText(12+"");
//                minutesu.setText(55+"");

                timeremaning.setText("12h 55m remaning");

            }
            if(level>25&&level<=35)
            {


//                houru.setText(19+"");
//                minutesu.setText(2+"");

                timeremaning.setText("19h 2m remaning");


            }
            if(level>35&&level<=50)
            {

//
//                houru.setText(22+"");
//                minutesu.setText(0+"");

                timeremaning.setText("22h 0m remaning");


            }
            if(level>50&&level<=65)
            {

//
//                houru.setText(28+"");
//                minutesu.setText(15+"");

                timeremaning.setText("28h 15m remaning");


            }
            if(level>65&&level<=75)
            {


//                houru.setText(30+"");
//                minutesu.setText(55+"");

                timeremaning.setText("30h 55m remaning");


            }
            if(level>75&&level<=85)
            {

//
//                houru.setText(38+"");
//                minutesu.setText(5+"");

                timeremaning.setText("38h 5m remaning");


            }
            if(level>85&&level<=100)
            {


//                houru.setText(60+"");
//                minutesu.setText(55+"");

                timeremaning.setText("60h 0m remaning");


            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((Build.VERSION.SDK_INT >=17)) {
            setContentView(R.layout.powersaving_maintextclock);
        } else {
            setContentView(R.layout.powersaving_main);
        }
        phone = (ImageView) findViewById(R.id.phone);
        internet = (ImageView) findViewById(R.id.internet);
        setting = (ImageView) findViewById(R.id.settings);
        messages = (ImageView) findViewById(R.id.messages);
        playstore = (ImageView) findViewById(R.id.playstore);
        contacts = (ImageView) findViewById(R.id.contacts);
        calculator =(ImageView) findViewById(R.id.calculator);
        alaram =(ImageView) findViewById(R.id.alaram);
        batteryremaning =(TextView) findViewById(R.id.batteryremaning);
        timeremaning =(TextView) findViewById(R.id.timeremaning);
        dots=(ImageView) findViewById(R.id.dots);
        disable=(TextView) findViewById(R.id.disable);
        sharedpreferences = getSharedPreferences("tarunbest", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        changepic();
        registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check%2==0) {
                    disable.setVisibility(View.VISIBLE);
                    disable.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent i=new Intent(BatterySaver_Black.this,Noraml_Mode.class);
                            startActivity(i);

                            finish();
                        }
                    });
                    check++;

                }
                else
                {
                    disable.setVisibility(View.INVISIBLE);
                    check++;
                }
            }
        });


        alaram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("button", "1");
                editor.commit();

                if(sharedpreferences.getString("button1","0").equals("0"))
                {
                    Intent i=new Intent(BatterySaver_Black.this,Pick_Apps.class);
                    startActivity(i);
                }
                else if(sharedpreferences.getString("button1","0").equals("1"))
                {
                    playstore();
                }
                else if(sharedpreferences.getString("button1","0").equals("2"))
                {
                    calculator();
                }
                else if(sharedpreferences.getString("button1","0").equals("3"))
                {
                    alaram();
                }
                else if(sharedpreferences.getString("button1","0").equals("4"))
                {
                    contacts();
                }
                else if(sharedpreferences.getString("button1","0").equals("5"))
                {
                    map();
                }
                else if(sharedpreferences.getString("button1","0").equals("6"))
                {
                    camera();
                }

            }
        });

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("button", "2");
                editor.commit();

                if(sharedpreferences.getString("button2","0").equals("0"))
                {
                    Intent i=new Intent(BatterySaver_Black.this,Pick_Apps.class);
                    startActivity(i);
                }
                else if(sharedpreferences.getString("button2","0").equals("1"))
                {
                    playstore();
                }
                else if(sharedpreferences.getString("button2","0").equals("2"))
                {
                    calculator();
                }
                else if(sharedpreferences.getString("button2","0").equals("3"))
                {
                    alaram();
                }
                else if(sharedpreferences.getString("button2","0").equals("4"))
                {
                    contacts();
                }
                else if(sharedpreferences.getString("button2","0").equals("5"))
                {
                    map();
                }
                else if(sharedpreferences.getString("button2","0").equals("6"))
                {
                    camera();
                }

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urlString = "http://www.google.com";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
            }
        });

        playstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("button", "3");
                editor.commit();

                if(sharedpreferences.getString("button3","0").equals("0"))
                {
                    Intent i=new Intent(BatterySaver_Black.this,Pick_Apps.class);
                    startActivity(i);
                }
                else if(sharedpreferences.getString("button3","0").equals("1"))
                {
                    playstore();
                }
                else if(sharedpreferences.getString("button3","0").equals("2"))
                {
                    calculator();
                }
                else if(sharedpreferences.getString("button3","0").equals("3"))
                {
                    alaram();
                }
                else if(sharedpreferences.getString("button3","0").equals("4"))
                {
                    contacts();
                }
                else if(sharedpreferences.getString("button3","0").equals("5"))
                {
                    map();
                }
                else if(sharedpreferences.getString("button3","0").equals("6"))
                {
                    camera();
                }

            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("button", "4");
                editor.commit();

                if(sharedpreferences.getString("button4","0").equals("0"))
                {
                    Intent i=new Intent(BatterySaver_Black.this,Pick_Apps.class);
                    startActivity(i);
                }
                else if(sharedpreferences.getString("button4","0").equals("1"))
                {
                    playstore();
                }
                else if(sharedpreferences.getString("button4","0").equals("2"))
                {
                    calculator();
                }
                else if(sharedpreferences.getString("button4","0").equals("3"))
                {
                    alaram();
                }
                else if(sharedpreferences.getString("button4","0").equals("4"))
                {
                    contacts();
                }
                else if(sharedpreferences.getString("button4","0").equals("5"))
                {
                    map();
                }
                else if(sharedpreferences.getString("button4","0").equals("6"))
                {
                    camera();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri = intent.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);

                Log.d(TAG, "ZZZ number : " + number + " , name : " + name);

                Intent k = new Intent(Intent.ACTION_DIAL);
                k.setData(Uri.parse("tel:"+number));
                startActivity(k);

            }
        }
    }

    ;
//    @Override
//    public void onBackPressed() {
////        super.onBackPressed();
//    }


    public void check()
    {



    }

    public void playstore()
    {
        //////play store///////////////////////////////////////
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/")));
        }

        ////////////////////////////////////////////////////////
    }
    public void contacts()
    {

        //////////////////contacts//////////////////////////////
        Uri uri = Uri.parse("content://contacts");
        Intent intent = new Intent(Intent.ACTION_PICK, uri);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(intent, 1);
        ////////////////////////////////////////////////////////////
    }
    public void calculator()
    {
        ///////////////////////////calculator////////////////////////
        Intent intent;


        try {

            intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName("com.sec.android.app.popupcalculator", "com.sec.android.app.popupcalculator.Calculator"));
            startActivity(intent);
//
        } catch (Exception e) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setComponent(new ComponentName("com.android.calculator2", "com.android.calculator2.Calculator"));
            startActivity(intent);

        }
        /////////////////////////////////////////////////////////////////////////////////////////////
    }
    public void alaram()
    {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        startActivity(i);
    }

    public void map()
    {
        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&daddr=%f,%f (%s)", 12f, 2f, "");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        try
        {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex)
        {
            try
            {
                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(unrestrictedIntent);
            }
            catch(ActivityNotFoundException innerEx)
            {
                Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void camera()
    {

        if(android.os.Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        else
        {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 0);
        }


    }

    public void changepic()
    {
        if(sharedpreferences.getString("button1","0").equals("0"))
        {
            alaram.setImageResource(R.drawable.add);
        }
        else if(sharedpreferences.getString("button1","0").equals("1"))
        {
            alaram.setImageResource(R.drawable.gp);
        }
        else if(sharedpreferences.getString("button1","0").equals("2"))
        {
            alaram.setImageResource(R.drawable.calc);

        }
        else if(sharedpreferences.getString("button1","0").equals("3"))
        {
            alaram.setImageResource(R.drawable.clock);

        }
        else if(sharedpreferences.getString("button1","0").equals("4"))
        {
            alaram.setImageResource(R.drawable.contacts);

        }
        else if(sharedpreferences.getString("button1","0").equals("5"))
        {
            alaram.setImageResource(R.drawable.map);

        }
        else if(sharedpreferences.getString("button1","0").equals("6"))
        {
            alaram.setImageResource(R.drawable.camera);
        }


        if(sharedpreferences.getString("button2","0").equals("0"))
        {
            calculator.setImageResource(R.drawable.add);
        }
        else if(sharedpreferences.getString("button2","0").equals("1"))
        {
            calculator.setImageResource(R.drawable.gp);
        }
        else if(sharedpreferences.getString("button2","0").equals("2"))
        {
            calculator.setImageResource(R.drawable.calc);

        }
        else if(sharedpreferences.getString("button2","0").equals("3"))
        {
            calculator.setImageResource(R.drawable.clock);

        }
        else if(sharedpreferences.getString("button2","0").equals("4"))
        {
            calculator.setImageResource(R.drawable.contacts);

        }
        else if(sharedpreferences.getString("button2","0").equals("5"))
        {
            calculator.setImageResource(R.drawable.map);

        }
        else if(sharedpreferences.getString("button2","0").equals("6"))
        {
            calculator.setImageResource(R.drawable.camera);
        }



        if(sharedpreferences.getString("button3","0").equals("0"))
        {
            playstore.setImageResource(R.drawable.add);
        }
        else if(sharedpreferences.getString("button3","0").equals("1"))
        {
            playstore.setImageResource(R.drawable.gp);
        }
        else if(sharedpreferences.getString("button3","0").equals("2"))
        {
            playstore.setImageResource(R.drawable.calc);

        }
        else if(sharedpreferences.getString("button3","0").equals("3"))
        {
            playstore.setImageResource(R.drawable.clock);

        }
        else if(sharedpreferences.getString("button3","0").equals("4"))
        {
            playstore.setImageResource(R.drawable.contacts);

        }
        else if(sharedpreferences.getString("button3","0").equals("5"))
        {
            playstore.setImageResource(R.drawable.map);

        }
        else if(sharedpreferences.getString("button3","0").equals("6"))
        {
            playstore.setImageResource(R.drawable.camera);
        }



        if(sharedpreferences.getString("button4","0").equals("0"))
        {
            contacts.setImageResource(R.drawable.add);
        }
        else if(sharedpreferences.getString("button4","0").equals("1"))
        {
            contacts.setImageResource(R.drawable.gp);
        }
        else if(sharedpreferences.getString("button4","0").equals("2"))
        {
            contacts.setImageResource(R.drawable.calc);

        }
        else if(sharedpreferences.getString("button4","0").equals("3"))
        {
            contacts.setImageResource(R.drawable.clock);

        }
        else if(sharedpreferences.getString("button4","0").equals("4"))
        {
            contacts.setImageResource(R.drawable.contacts);

        }
        else if(sharedpreferences.getString("button4","0").equals("5"))
        {
            contacts.setImageResource(R.drawable.map);

        }
        else if(sharedpreferences.getString("button4","0").equals("6"))
        {
            contacts.setImageResource(R.drawable.camera);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        changepic();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, 0);
                }else {
                    Toast.makeText(this, "Allow Permission To Use Camera App.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBatInfoReceiver);
    }
}
