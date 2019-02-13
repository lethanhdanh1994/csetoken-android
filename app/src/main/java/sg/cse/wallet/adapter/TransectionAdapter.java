package sg.cse.wallet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import sg.cse.wallet.R;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.mvp.transection.TransectionModel;
import sg.cse.wallet.utils.Support;

public class TransectionAdapter extends RecyclerView.Adapter<TransectionAdapter.ViewHolder> {
    ArrayList<TransactionsHistoryRes.Result.Docs> transectionArrayList;
    String transection_type ;
    int drawable_resource;

    public TransectionAdapter(ArrayList<TransactionsHistoryRes.Result.Docs> transectionArrayList, String transection_type, int drawable_resource) {
        this.transectionArrayList = transectionArrayList;
        this.transection_type = transection_type;
        this.drawable_resource = drawable_resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.amount.setText(String.valueOf(transectionArrayList.get(position).getAmount()+" "+transectionArrayList.get(position).getCoinAsset()));
        holder.time.setText(Support.getStringAgo(transectionArrayList.get(position).getUpdatedAt()));
        holder.transection.setText(transection_type);
        holder.imageView.setImageResource(drawable_resource);
    }

    @Override
    public int getItemCount() {
        return transectionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView amount,time,transection;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            time = itemView.findViewById(R.id.time);
            transection=itemView.findViewById(R.id.transectiontype);
            imageView = itemView.findViewById(R.id.icon);
        }
    }

}