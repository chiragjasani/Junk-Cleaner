package appsforyou.junkcleaner;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import appsforyou.junkcleaner.Model.AppInfo;

public class AppManagerAdapter extends RecyclerView.Adapter<AppManagerAdapter.ViewHolder> {


    public List<AppInfo> appInfoList;
    private Context mContext;
    private ItemClickListener clickListener;


    public AppManagerAdapter(List<AppInfo> appInfoList, Context mContext) {
        this.appInfoList = appInfoList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listview_software, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.textViewName.setText(appInfoList.get(i).getAppName());
        viewHolder.textViewSize.setText(""+appInfoList.get(i).getPackname());
        viewHolder.appLogo.setImageDrawable(appInfoList.get(i).getAppIcon());
        viewHolder.uninstallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_UNINSTALL_PACKAGE);
                intent.setData(Uri.parse("package:" + appInfoList.get(i).getPackname()));
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return appInfoList.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName, textViewSize;
        ImageView appLogo;
        Button uninstallButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.app_name);
            textViewSize = (TextView) itemView.findViewById(R.id.app_size);
            appLogo = (ImageView) itemView.findViewById(R.id.app_icon);
            uninstallButton = (Button) itemView.findViewById(R.id.riple_uninstall);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onItemClick(v, getAdapterPosition());

        }
    }
}
