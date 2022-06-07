package appsforyou.junkcleaner;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.todddavies.components.progressbar.ProgressWheel;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class MoreFragments extends AppCompatActivity {

    private TextView txtTotal, txtAvail, txtFree;
    private TextView txtExTotal, txtExAvail, txtExFree;
    private ProgressWheel pw = null;
    private ProgressWheel exPW = null;
    private LinearLayout layourExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_info_layout);


        pw = (ProgressWheel) findViewById(R.id.pw_spinner_internal);
        exPW = (ProgressWheel) findViewById(R.id.pw_spinner_external);

        layourExternal = (LinearLayout) findViewById(R.id.layourExternal);

        txtTotal = (TextView) findViewById(R.id.txtTotalInternalMemory);
        txtAvail = (TextView) findViewById(R.id.txtAvailInternalMemory);
        txtFree = (TextView) findViewById(R.id.txtFreeInternalMemory);

        txtExTotal = (TextView) findViewById(R.id.txtTotalExternalMemory);
        txtExAvail = (TextView) findViewById(R.id.txtAvailExternalMemory);
        txtExFree = (TextView) findViewById(R.id.txtFreeExternalMemory);


        /**
         * Get device memory info and display
         */

        DeviceMemoryInfo info = new DeviceMemoryInfo(MoreFragments.this);

        String total = Formatter.formatFileSize(MoreFragments.this,
                info.getTotalInternalMemorySize());
        String avail = Formatter.formatFileSize(MoreFragments.this,
                info.getAvailableInternalMemorySize());

        txtTotal.setText("Total: " + total);
        txtAvail.setText("Available: " + avail);
        txtFree.setText("Used: " + info.getFreeInternalMemorySize());

        float f = (float) info.getAvailableInternalMemorySize();
        float f1 = (float) info.getTotalInternalMemorySize();
        int i = (int) ((f / f1) * 100F);
        setInternalPercentage(i);

        String[] dir = info.getStorageDirectories();
        for (int j = 0; j < dir.length; j++) {
            if (!TextUtils.isEmpty(dir[j])) {
                File file = new File(dir[j]);
                if (file.exists() && file.length() > 0) {
                    ShowExternalInfo(file);
                }
            }
        }
    }

    private void ShowExternalInfo(File file) {
        layourExternal.setVisibility(View.VISIBLE);
        String exTotal = Formatter.formatFileSize(MoreFragments.this,
                DeviceMemoryInfo.getTotalExternalMemorySize(file));
        String exAvail = Formatter.formatFileSize(MoreFragments.this,
                DeviceMemoryInfo.getAvailableExternalMemorySize(file));

        txtExTotal.setText("Total: " + exTotal);
        txtExAvail.setText("Available: " + exAvail);
        txtExFree.setText("Used: "
                + DeviceMemoryInfo.getFreeExternalMemorySize(file));

        float exf = (float) DeviceMemoryInfo
                .getAvailableExternalMemorySize(file);
        float exf1 = (float) DeviceMemoryInfo.getTotalExternalMemorySize(file);
        int t = (int) ((exf / exf1) * 100F);
        setExternalPercentage(t);
    }

    public void setInternalPercentage(int info) {

        String s = (new StringBuilder()).append(info).append("%").toString();
        pw.setText(s);
        int j = (int) ((double) info * 3.6000000000000001D);
        pw.setProgress(j);

    }

    public void setExternalPercentage(int info) {

        String s = (new StringBuilder()).append(info).append("%").toString();
        exPW.setText(s);
        int j = (int) ((double) info * 3.6000000000000001D);
        exPW.setProgress(j);

    }

}
