package appsforyou.junkcleaner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class PowerConnected extends BroadcastReceiver {

    // bROAD CAST THAT lISTEN fOR charger Connected Events

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i=new Intent(context,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
