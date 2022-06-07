package appsforyou.junkcleaner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import appsforyou.junkcleaner.Classes.Apps;


public class Scan_Cpu_Apps extends RecyclerView.Adapter<Scan_Cpu_Apps.MyViewHolder> {


    /// Get List of Apps Causing Junk Files

    public List<Apps> apps;

    public Scan_Cpu_Apps(List<Apps> getapps) {
        apps = getapps;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.scan_cpu_apps, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        Apps app = apps.get(position);
        holder.size.setText("");
        holder.image.setImageDrawable(app.getImage());
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView size;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            size = (TextView) view.findViewById(R.id.apptext);
            image = (ImageView) view.findViewById(R.id.appimage);

        }
    }
}
