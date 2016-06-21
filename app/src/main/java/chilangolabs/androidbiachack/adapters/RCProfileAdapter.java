package chilangolabs.androidbiachack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import chilangolabs.androidbiachack.R;
import chilangolabs.androidbiachack.entitys.ItemPerfiles;

/**
 * Created by Gorro on 21/06/16.
 */
public class RCProfileAdapter extends RecyclerView.Adapter<RCProfileAdapter.ProfileViewHolder> {

    List<ItemPerfiles> itemPerfiles;
    Context context;

    public RCProfileAdapter(List<ItemPerfiles> itemPerfiles, Context context) {
        this.itemPerfiles = itemPerfiles;
        this.context = context;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profiles, parent, false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        holder.txtName.setText(itemPerfiles.get(position).getName());
        holder.txtType.setText(itemPerfiles.get(position).getType());
        holder.progressBar.setMax(10);
        holder.progressBar.setProgress(itemPerfiles.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return itemPerfiles.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtType;
        ProgressBar progressBar;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtItemName);
            txtType = (TextView) itemView.findViewById(R.id.txtItemProfile);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }


}
