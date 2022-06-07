package appsforyou.junkcleaner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import appsforyou.junkcleaner.Fragments.PhoneBooster;


public final class Alaram_Booster extends BroadcastReceiver {
    SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;
    @Override
    public void onReceive(Context context, Intent intent) {

        sharedpreferences = context.getSharedPreferences("tarun", Context.MODE_PRIVATE);
//        Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();



        /// when memory is orveloaded or increased


        editor = sharedpreferences.edit();
        editor.putString("booster", "1");
        editor.commit();

        try {
            PhoneBooster.optimizebutton.setBackgroundResource(0);
            PhoneBooster.optimizebutton.setImageResource(0);
            PhoneBooster.optimizebutton.setImageResource(R.drawable.optimize);
        }
        catch(Exception e)
        {

        }

    }
}
