package chilangolabs.androidbiachack.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chilangolabs.androidbiachack.R;
import chilangolabs.androidbiachack.entitys.ItemFirstAid;

/**
 * Created by Gorro on 21/06/16.
 */
public class RCFirstAidsAdapter extends RecyclerView.Adapter<RCFirstAidsAdapter.FirstAidViewHolder> {

    List<ItemFirstAid> itemFirstAid;
    Context context;

    public RCFirstAidsAdapter(List<ItemFirstAid> itemFirstAid, Context context) {
        this.itemFirstAid = itemFirstAid;
        this.context = context;
    }

    @Override
    public FirstAidViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_firstaid, parent, false);
        return new FirstAidViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FirstAidViewHolder holder, int position) {
        holder.txtName.setText(itemFirstAid.get(position).getName());
        holder.txtType.setText(itemFirstAid.get(position).getDescrp());
    }

    @Override
    public int getItemCount() {
        return itemFirstAid.size();
    }

    public static class FirstAidViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtType;

        public FirstAidViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtItemFirstTitle);
            txtType = (TextView) itemView.findViewById(R.id.txtItemFirstDescr);
        }
    }

}
