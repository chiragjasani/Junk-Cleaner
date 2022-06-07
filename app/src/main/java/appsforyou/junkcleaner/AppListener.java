package appsforyou.junkcleaner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



 public class AppListener extends BroadcastReceiver {



    /// Listen for newly installed app and protect it

    @Override
    public void onReceive(Context context, Intent arg1) {
        // TODO Auto-generated method stub

        Uri data = arg1.getData();
        String installedPackageName = data.getEncodedSchemeSpecificPart();


//ToDo Change Pacakage Name
        if(!(installedPackageName.equals("appsforyou.junkcleaner"))) {
//            Toast.makeText(context, installedPackageName + "", Toast.LENGTH_SHORT).show();

            final String packageName = installedPackageName;
            PackageManager packageManager= context.getApplicationContext().getPackageManager();
            try {
                String appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA));
                LayoutInflater inflater = LayoutInflater.from(context);
                View layout = inflater.inflate(R.layout.my_toast, null);

                TextView text = (TextView) layout.findViewById(R.id.textView1);
                text.setText(appName+" Is Optimized by Fast Cleaner & Battery Saver.");

                Toast toast = new Toast(context);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 120);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
