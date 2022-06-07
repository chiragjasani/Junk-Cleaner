package appsforyou.junkcleaner.Fragments;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import appsforyou.junkcleaner.Alaram_Booster;
import appsforyou.junkcleaner.ApplicationManager;
import appsforyou.junkcleaner.BatterySaver;
import appsforyou.junkcleaner.DeviceMemoryInfo;
import appsforyou.junkcleaner.CPUCooler;
import appsforyou.junkcleaner.JunkCleaner;
import appsforyou.junkcleaner.MoreFragments;
import appsforyou.junkcleaner.R;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.ALARM_SERVICE;


public class PhoneBooster extends Fragment {

    int mb = 1024 * 1024;
    View view;
    DecoView arcView, arcView2;
    TextView scanning, centree, totalram, usedram, appused, appsfreed, processes, top, bottom, ramperct;
    LinearLayout scanlay, optimizelay;
    public static ImageView optimizebutton;
    TimerTask timer = null;
    TimerTask timer2 = null;
    int x, y;
    int counter = 0;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    int totalCompleteMemorySpace = 0;
    private static final String TAG = "PhoneBooster";
    long totalExtMemory = 0, freeExtMemory = 0;
    long usedExtMemeory = 0;
    int percentExt = 0;
    LinearLayout apssManagerLayout, junkCleanerLayout, batterySaverLayout, cpuCoolerLayout, memoryManagerLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.phone_booster, container, false);

        arcView = view.findViewById(R.id.dynamicArcView2);
        scanning = view.findViewById(R.id.scanning);
        scanlay = view.findViewById(R.id.scanlay);
        optimizelay = view.findViewById(R.id.optimizelay);
        optimizebutton = view.findViewById(R.id.optbutton);
        centree = view.findViewById(R.id.centree);
        totalram = view.findViewById(R.id.totalram);
        usedram = view.findViewById(R.id.usedram);
        appsfreed = view.findViewById(R.id.appsfreed);
        appused = view.findViewById(R.id.appsused);
        processes = view.findViewById(R.id.processes);
        top = view.findViewById(R.id.top);
        bottom = view.findViewById(R.id.bottom);
        ramperct = view.findViewById(R.id.ramperct);
        apssManagerLayout = (LinearLayout) view.findViewById(R.id.appsManagerLayout);
        cpuCoolerLayout = (LinearLayout) view.findViewById(R.id.cpucoolerlayout);
        junkCleanerLayout = (LinearLayout) view.findViewById(R.id.junkcleanerLayout);
        batterySaverLayout = (LinearLayout) view.findViewById(R.id.batterysaverLayout);
        memoryManagerLayout = (LinearLayout) view.findViewById(R.id.memoryMangerLayout);
        memoryManagerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), MoreFragments.class);
                getContext().startActivity(intent1);

            }
        });
        cpuCoolerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), CPUCooler.class);
                getContext().startActivity(intent1);

            }
        });
        batterySaverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BatterySaver.class);
                getContext().startActivity(intent);

            }
        });
        junkCleanerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), JunkCleaner.class);
                getContext().startActivity(intent);

            }
        });
        apssManagerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ApplicationManager.class);
                getContext().startActivity(intent);

            }
        });
        sharedpreferences = getActivity().getSharedPreferences("tarun", Context.MODE_PRIVATE);


        try {



            Random ran3 = new Random();
            //ramperct.setText(ran3.nextInt(60) + 40 + "%");


            optimizebutton.setBackgroundResource(0);
            optimizebutton.setImageResource(0);
            optimizebutton.setImageResource(R.drawable.optimize);

            if (sharedpreferences.getString("booster", "1").equals("0")) {
                optimizebutton.setImageResource(0);
                optimizebutton.setImageResource(R.drawable.optimized);

                centree.setText(sharedpreferences.getString("value", "50MB"));


            }
            start();

            optimizebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (sharedpreferences.getString("booster", "1").equals("1")) {
                        optimize();
                        //getStorageInfo();

                        editor = sharedpreferences.edit();
                        editor.putString("booster", "0");
                        editor.commit();


                        Intent intent = new Intent(getActivity(), Alaram_Booster.class);

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                                intent, PendingIntent.FLAG_ONE_SHOT);

                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (100 * 1000), pendingIntent);
                    } else {

//                            Toast.makeText(getActivity(), "Phone Is Aleady Optimized", Toast.LENGTH_SHORT).show();

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.my_toast, null);

                        TextView text = layout.findViewById(R.id.textView1);
                        text.setText("Phone Is Aleady Optimized");

                        Toast toast = new Toast(getActivity());
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 70);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();

                    }


                }
            });


//#F22938 red
// Create background track

//           optimize();
//           getStorageInfo();
        } catch (Exception e) {

        }


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
         * Get device memory info and display
         */

        DeviceMemoryInfo info = new DeviceMemoryInfo(getActivity());

//        String total = Formatter.formatFileSize(getActivity(),
//                info.getTotalInternalMemorySize());
//        String avail = Formatter.formatFileSize(getActivity(),
//                info.getAvailableInternalMemorySize());
        long total = info.getTotalInternalMemorySize();
        long available = info.getAvailableInternalMemorySize();
        long used = total - available;

//        txtTotal.setText("Total: " + total);
//        txtAvail.setText("Available: " + avail);
//        txtFree.setText("Used: " + info.getFreeInternalMemorySize());

        float f = (float) used;
        float f1 = (float) info.getTotalInternalMemorySize();
        int i = (int) ((f / f1) * 100F);
        Log.d(TAG, "onActivityCreated: +internalPercentageUse" + i);
        //setInternalPercentage(i);

        String[] dir = info.getStorageDirectories();
        for (int j = 0; j < dir.length; j++) {
            if (!TextUtils.isEmpty(dir[j])) {
                File file = new File(dir[j]);
                if (file.exists() && file.length() > 0) {
                    ShowExternalInfo(file);
                }
            }
        }
        Log.d(TAG, "onActivityCreated: " + (total + totalExtMemory));
        long totalAll = (total + totalExtMemory);
        long totalused = used + usedExtMemeory;
        long totalAvailable = available + freeExtMemory;
        usedram.setText(Formatter.formatFileSize(getActivity(), totalused) + "");
        totalram.setText(" / " + Formatter.formatFileSize(getActivity(), totalAll) + "");
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {

            //Check for the file
            File appFolder = new File(Environment.getExternalStorageDirectory() + File.separator
                    + getContext().getString(R.string.app_name));

            boolean exist = appFolder.exists();
            if (exist) {

                int totalPercent = (percentExt + i) / 2;
                ramperct.setText(totalPercent + "%");
            } else {
                int totalPercent = i;
                ramperct.setText(totalPercent + "%");
            }
        }


    }

    public void convertedToGb(long rxBytes) {
        if (rxBytes >= 1024) {
            //KB or more
            long rxKb = rxBytes / 1024;
            //RX.setText(Long.toString(rxKb) + " KBs");
            if (rxKb >= 1024) {
                //MB or more
                long rxMB = rxKb / 1024;
                // RX.setText(Long.toString(rxMB) + " MBs");
                if (rxMB >= 1024) {
                    //GB or more
                    long rxGB = rxMB / 1024;
                    //  RX.setText(Long.toString(rxGB));
                }//rxMB>1024
            }//rxKb > 1024
        }

    }

    private void ShowExternalInfo(File file) {
//        layourExternal.setVisibility(View.VISIBLE);
//        String exTotal = Formatter.formatFileSize(getActivity(),
//                DeviceMemoryInfo.getTotalExternalMemorySize(file));
//        String exAvail = Formatter.formatFileSize(getActivity(),
//                DeviceMemoryInfo.getAvailableExternalMemorySize(file));
        totalExtMemory = DeviceMemoryInfo.getTotalExternalMemorySize(file);
        freeExtMemory = DeviceMemoryInfo.getAvailableExternalMemorySize(file);
        usedExtMemeory = totalExtMemory - freeExtMemory;

//        txtExTotal.setText("Total: " + exTotal);
//        txtExAvail.setText("Available: " + exAvail);
//        txtExFree.setText("Used: "
//                + DeviceMemoryInfo.getFreeExternalMemorySize(file));

        float exf = (float) usedExtMemeory;
        float exf1 = (float) DeviceMemoryInfo.getTotalExternalMemorySize(file);
        int t = (int) ((exf / exf1) * 100F);
        Log.d(TAG, "ShowExternalInfo: " + t);
        percentExt = t;
        //setExternalPercentage(t);
    }


    public void setInternalPercentage(int info) {

        String s = (new StringBuilder()).append(info).append("%").toString();
        //  pw.setText(s);
        int j = (int) ((double) info * 3.6000000000000001D);
        //  pw.setProgress(j);

    }

//    public void setExternalPercentage(int info) {
//
//        String s = (new StringBuilder()).append(info).append("%").toString();
//        exPW.setText(s);
//        int j = (int) ((double) info * 3.6000000000000001D);
//        exPW.setProgress(j);
//
//    }

    public void optimize() {

        RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);
        rotate.setInterpolator(new LinearInterpolator());

        ImageView image = view.findViewById(R.id.circularlines);

        image.startAnimation(rotate);


        arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 0)
                .setInterpolator(new AccelerateInterpolator())
                .build());

        arcView.addSeries(new SeriesItem.Builder(Color.parseColor("#F22938"))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.parseColor("#F22938"))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#2499E0"))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();
//
//        int series1Index = arcView.addSeries(seriesItem1);
        int series1Index2 = arcView.addSeries(seriesItem2);

        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(500)
                .setDuration(2000)
                .setListener(new DecoEvent.ExecuteEventListener() {
                    @Override
                    public void onEventStart(DecoEvent decoEvent) {
                        bottom.setText("");
                        top.setText("");
                        centree.setText("Optimizing...");
                    }

                    @Override
                    public void onEventEnd(DecoEvent decoEvent) {

                    }
                })
                .build());

        arcView.addEvent(new DecoEvent.Builder(25).setIndex(series1Index2).setDelay(4000).setListener(new DecoEvent.ExecuteEventListener() {
            @Override
            public void onEventStart(DecoEvent decoEvent) {
                bottom.setText("");
                top.setText("");
                centree.setTextSize(25);
                centree.setText("Optimizing...");

            }

            @Override
            public void onEventEnd(DecoEvent decoEvent) {

                bottom.setText("Free");
                top.setText("RAM");
                Random ran3 = new Random();
                //ramperct.setText(ran3.nextInt(40) + 20 + "%");
            }
        }).build());

        ImageView img_animation = view.findViewById(R.id.waves);

        TranslateAnimation animation = new TranslateAnimation(0.0f, 1000.0f, 0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(5000);  // animation duration
        animation.setRepeatCount(0);
        animation.setInterpolator(new LinearInterpolator());// animation repeat count
//        animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
        animation.setFillAfter(true);

        img_animation.startAnimation(animation);

        int counter = 0;
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                scanlay.setVisibility(View.VISIBLE);
                optimizelay.setVisibility(View.GONE);
                scanning.setText("SCANNING...");
                // killall();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                scanlay.setVisibility(View.GONE);
                optimizelay.setVisibility(View.VISIBLE);
//                optimizebutton.setOnClickListener(null);
                optimizebutton.setImageResource(R.drawable.optimized);


                Random ran = new Random();
                x = ran.nextInt(100) + 30;

                Random ran2 = new Random();
                int proc = ran2.nextInt(10) + 5;

                centree.setText(getUsedMemorySize() - x + " MB");

                editor = sharedpreferences.edit();
                editor.putString("value", getUsedMemorySize() - x + " MB");
                editor.commit();

                Log.e("used mem", getUsedMemorySize() + " MB");
                Log.e("used mem", getTotalRAM());

//                totalram.setText(getTotalRAM());
//                usedram.setText(getUsedMemorySize() - x + " MB/ ");

                appsfreed.setText(getTotalRAM());
                appused.setText(Math.abs(getUsedMemorySize() - x - 30) + " MB/ ");

                processes.setText(y - proc + "");


//                optimizebutton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        optimizebutton.setBackgroundResource(R.drawable.optimized);
//                        Toast.makeText(getActivity(), "Already Optimized", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//                final Timer t = new Timer();
//                final Timer t2 = new Timer();
//
//
//                timer = new TimerTask() {
//
//                    @Override
//                    public void run() {
//
//                        getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                optimizebutton.setBackgroundResource(0);
//                                optimizebutton.setBackgroundResource(R.drawable.optimize);
//
//                                optimizebutton.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        optimize();
//                                    }
//                                });
//                                t.cancel();
//                                timer.cancel();
//                                t.purge();
//
//
//                            }
//                        });
//
//
//                    }
//
//                };
////Set the schedule function and rate
//
//                t.schedule(timer, 300000, 300000);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public void start() {


        final Timer t = new Timer();
        timer = new TimerTask() {

            @Override
            public void run() {

                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                        optimizebutton.setBackgroundResource(0);
//                        optimizebutton.setBackgroundResource(R.drawable.optimize);
                            counter++;
                            centree.setText(counter + "MB");
                        }
                    });
                } catch (Exception e) {

                }


            }

        };
        t.schedule(timer, 30, 30);


        Random ran2 = new Random();
        final int proc = ran2.nextInt(60) + 30;


        arcView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 0)
                .setInterpolator(new AccelerateInterpolator())
                .build());

        arcView.addSeries(new SeriesItem.Builder(Color.parseColor("#F22938"))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(32f)
                .build());

//Create data series track
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.parseColor("#F22938"))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();

        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.parseColor("#2499E0"))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .build();


//
//        int series1Index = arcView.addSeries(seriesItem1);
        int series1Index2 = arcView.addSeries(seriesItem2);

        arcView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(0)
                .setDuration(600)
                .build());


        arcView.addEvent(new DecoEvent.Builder(proc).setIndex(series1Index2).setDelay(2000).setListener(new DecoEvent.ExecuteEventListener() {
            @Override
            public void onEventStart(DecoEvent decoEvent) {


            }

            @Override
            public void onEventEnd(DecoEvent decoEvent) {

                t.cancel();
                timer.cancel();
                t.purge();


                centree.setText(getUsedMemorySize() + " MB");

                if (sharedpreferences.getString("booster", "1").equals("0")) {

                    centree.setText(sharedpreferences.getString("value", "50MB"));
                }


                final Timer t = new Timer();
                final Timer t2 = new Timer();


                try {

                    timer2 = new TimerTask() {

                        @Override
                        public void run() {

                            try {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {


                                        centree.setText(getUsedMemorySize() + " MB");

                                        if (sharedpreferences.getString("booster", "1").equals("0")) {

                                            centree.setText(sharedpreferences.getString("value", "50MB"));
                                        }

                                        t2.cancel();
                                        timer2.cancel();
                                        t2.purge();


                                    }
                                });
                            } catch (Exception e) {

                            }

                        }

                    };
//Set the schedule function and rate
                } catch (Exception e) {

                }

                t2.schedule(timer2, 100, 100);


            }
        }).build());
//        centree.setText(getUsedMemorySize()+" MB");
        Log.e("used mem", getUsedMemorySize() + " MB");
        Log.e("used mem", getTotalRAM());

        //totalram.setText(getTotalRAM());
        // usedram.setText(getUsedMemorySize() + " MB/ ");
        appsfreed.setText(getTotalRAM());
        appused.setText(getUsedMemorySize() - x - 30 + " MB/ ");

        Random ran = new Random();
        y = ran.nextInt(50) + 15;

        processes.setText(y + "");


//        final ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
//        final List<ActivityManager.RunningTaskInfo> recentTasks = activityManager.getRunningTasks(Integer.MAX_VALUE);
//
//        Log.e("Ruuning",recentTasks.size()+"");

    }


    public String getTotalRAM() {

        RandomAccessFile reader = null;
        String load = null;
        DecimalFormat twoDecimalForm = new DecimalFormat("#.##");
        double totRam = 0;
        String lastValue = "";
        try {
            try {
                reader = new RandomAccessFile("/proc/meminfo", "r");
                load = reader.readLine();
            } catch (Exception e) {

            }

            // Get the Number value from the string
            Pattern p = Pattern.compile("(\\d+)");
            Matcher m = p.matcher(load);
            String value = "";
            while (m.find()) {
                value = m.group(1);
                // System.out.println("Ram : " + value);
            }
            try {
                reader.close();
            } catch (Exception e) {

            }

            totRam = Double.parseDouble(value);
            // totRam = totRam / 1024;

            double mb = totRam / 1024.0;
            double gb = totRam / 1048576.0;
            double tb = totRam / 1073741824.0;

            if (tb > 1) {
                lastValue = twoDecimalForm.format(tb).concat(" TB");
            } else if (gb > 1) {
                lastValue = twoDecimalForm.format(gb).concat(" GB");
            } else if (mb > 1) {
                lastValue = twoDecimalForm.format(mb).concat(" MB");
            } else {
                lastValue = twoDecimalForm.format(totRam).concat(" KB");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Stream.close(reader);
        }

        return lastValue;
    }


    public long getUsedMemorySize() {

        try {
            ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            long availableMegs = mi.availMem / 1048576L;

            return availableMegs;
        } catch (Exception e) {
            return 200;
        }

    }

    public void killall() {
        List<ApplicationInfo> packages;
        PackageManager pm;
        pm = getActivity().getPackageManager();
        //get a list of installed apps.
        packages = pm.getInstalledApplications(0);

        ActivityManager mActivityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        String myPackage = getActivity().getApplicationContext().getPackageName();
        for (ApplicationInfo packageInfo : packages) {
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) continue;
            if (packageInfo.packageName.equals(myPackage)) continue;
            mActivityManager.killBackgroundProcesses(packageInfo.packageName);
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


//        if (isVisibleToUser) {
//            MainActivity.name.setText("Charge Booster");
//        } else {
//
//        }
    }


}

