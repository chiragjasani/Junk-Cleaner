package appsforyou.junkcleaner;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import appsforyou.junkcleaner.Model.AppInfo;

public class ApplicationManager extends AppCompatActivity implements ItemClickListener {
    List<AppInfo> userAppInfos = null;
    List<AppInfo> systemAppInfos = null;
    AsyncTask<Void, Integer, List<AppInfo>> task;
    RecyclerView recyclerView;
    AppManagerAdapter appManagerAdapter;
    RelativeLayout progressLayout;
    ProgressBar progressBarApps;
    Button systemApps, installedApps;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_manager);
        progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        progressBarApps = (ProgressBar) findViewById(R.id.progressApps);
        systemApps = (Button) findViewById(R.id.buttonsystemapps);
        installedApps = (Button) findViewById(R.id.buttoninstalledapps);
        progressBarApps.setVisibility(View.GONE);
        progressLayout.setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.listViewApps);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        fillData(0);
        systemApps.setBackgroundColor(getResources().getColor(R.color.colorAccent1));
        installedApps.setBackgroundColor(getResources().getColor(R.color.rippelColor));
        installedApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                fillData(0);
                systemApps.setBackgroundColor(getResources().getColor(R.color.colorAccent1));
                installedApps.setBackgroundColor(getResources().getColor(R.color.rippelColor));
            }
        });
        systemApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                systemApps.setBackgroundColor(getResources().getColor(R.color.rippelColor));
                installedApps.setBackgroundColor(getResources().getColor(R.color.colorAccent1));
                i = 0;
                fillData(1);
            }
        });


    }

    private void fillData(final int position) {

//        if (position == 0) {
//            topText.setText("");
//
//        } else {
//            topText.setText(R.string.Attention);
//
//        }


        task = new AsyncTask<Void, Integer, List<AppInfo>>() {
            private int mAppCount = 0;

            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                PackageManager pm = ApplicationManager.this.getPackageManager();
                List<PackageInfo> packInfos = pm.getInstalledPackages(0);
                publishProgress(0, packInfos.size());
                List<AppInfo> appinfos = new ArrayList<AppInfo>();
                for (PackageInfo packInfo : packInfos) {
                    publishProgress(++mAppCount, packInfos.size());
                    final AppInfo appInfo = new AppInfo();
                    Drawable appIcon = packInfo.applicationInfo.loadIcon(pm);
                    appInfo.setAppIcon(appIcon);

                    int flags = packInfo.applicationInfo.flags;

                    int uid = packInfo.applicationInfo.uid;

                    appInfo.setUid(uid);

                    if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfo.setUserApp(false);
                    } else {
                        appInfo.setUserApp(true);
                    }
                    if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfo.setInRom(false);
                    } else {
                        appInfo.setInRom(true);
                    }

                    String appName = packInfo.applicationInfo.loadLabel(pm).toString();
                    appInfo.setAppName(appName);
                    String packname = packInfo.packageName;
                    appInfo.setPackname(packname);
                    String version = packInfo.versionName;
                    appInfo.setVersion(version);
                    appinfos.add(appInfo);
                }
                return appinfos;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {

                try {
                    recyclerView.setVisibility(View.GONE);
                    progressBarApps.setVisibility(View.VISIBLE);
                    progressLayout.setVisibility(View.VISIBLE);
                    //mProgressBarText.setText(getString(R.string.scanning_m_of_n, values[0], values[1]));
                } catch (Exception e) {

                }

            }

            @Override
            protected void onPreExecute() {
                try {
                    recyclerView.setVisibility(View.GONE);
                    progressBarApps.setVisibility(View.VISIBLE);
                    progressLayout.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }
                super.onPreExecute();
            }

            @SuppressLint({"StringFormatInvalid", "StringFormatMatches"})
            @Override
            protected void onPostExecute(List<AppInfo> result) {

                super.onPostExecute(result);


                try {
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBarApps.setVisibility(View.GONE);
                    progressLayout.setVisibility(View.GONE);

                    userAppInfos = new ArrayList<>();
                    systemAppInfos = new ArrayList<>();
                    long allSize = 0;
                    for (AppInfo a : result) {
                        if (a.isUserApp()) {
                            allSize += a.getPkgSize();
                            userAppInfos.add(a);
                        } else {
                            systemAppInfos.add(a);
                        }
                    }
                    appManagerAdapter = new AppManagerAdapter(systemAppInfos, ApplicationManager.this);
                    recyclerView.setAdapter(appManagerAdapter);
                    if (position == 0) {

                        // topText.setText(String.format(getString(R.string.software_top_text), userAppInfos.size(), StorageUtil.convertStorage(allSize)));
                        appManagerAdapter = new AppManagerAdapter(userAppInfos, ApplicationManager.this);
                        recyclerView.setAdapter(appManagerAdapter);

                    } else {

                        appManagerAdapter = new AppManagerAdapter(systemAppInfos, ApplicationManager.this);
                        recyclerView.setAdapter(appManagerAdapter);


                    }
                } catch (Exception e) {

                }
            }

        };
        task.execute();


    }

    @Override
    public void onItemClick(View view, int position) {
        if (i == 0) {


        } else if (i == 1) {
        }

    }
}
