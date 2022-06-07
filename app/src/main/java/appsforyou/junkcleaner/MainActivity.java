package appsforyou.junkcleaner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import appsforyou.junkcleaner.Fragments.ActivityFragment;
import appsforyou.junkcleaner.Fragments.Me_Fragment;
import appsforyou.junkcleaner.Fragments.PhoneBooster;

public class MainActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Thread.UncaughtExceptionHandler oldHandler =
                Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(
                new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(
                            Thread paramThread,
                            Throwable paramThrowable
                    ) {
                        //Do your own error handling here

                        if (oldHandler != null)
                            oldHandler.uncaughtException(
                                    paramThread,
                                    paramThrowable
                            ); //Delegates to Android's error handling
                        else
                            System.exit(2); //Prevents the service/app from freezing
                    }
                });


//        setTheme(R.style.AppTheme1);
        BottomNavigationView navigation = findViewById(R.id.bottomnavigation);
        navigation.setOnNavigationItemSelectedListener(MainActivity.this);
        sharedpreferences = getSharedPreferences("tarunbest", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        loadFragemnt(new PhoneBooster());

//        ImageView img_animation = (ImageView) findViewById(R.id.backbar);
//
//        TranslateAnimation animation = new TranslateAnimation(0.0f, 1000.0f, 0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
//        animation.setDuration(10000);  // animation duration
//        animation.setRepeatCount(0);
//        animation.setInterpolator(new LinearInterpolator());// animation repeat count
////        animation.setRepeatMode(2);   // repeat animation (left to right, right to left )
//        animation.setFillAfter(true);
//
//        img_animation.startAnimation(animation);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        ////// Create Tabs Layout.

//        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.phonebooster));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.cleaner));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.battery_saver));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.cooler));
//
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//
//        viewPager.setOffscreenPageLimit(4);
////        viewPager.setCurrentItem(4);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                viewPager.setCurrentItem(tab.getPosition());
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }
    public boolean loadFragemnt(Fragment fragment){
        if (fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            return true;
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        editor.putString("button1", "0");
        editor.putString("button2", "0");
        editor.putString("button3", "0");
        editor.putString("button4", "0");
        editor.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId()){
            case R.id.menu_home:
                fragment=new PhoneBooster();

                break;

            case R.id.menu_activity:
                fragment=new ActivityFragment();
                break;

            case R.id.menu_me:
                fragment=new Me_Fragment();
                break;
        }
        return loadFragemnt(fragment);
    }

    public class MyException extends Exception {
        // special exception code goes here
    }
}
