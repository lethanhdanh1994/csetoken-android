package sg.cse.wallet.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sg.cse.wallet.R;
import sg.cse.wallet.helper.DateTimeHelper;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.prefs.Const;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class TransactionHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<TransactionsHistoryRes.Result.Docs> transactionsList;

    public TransactionHistoryAdapter(Context context, List<TransactionsHistoryRes.Result.Docs> transactionsList) {
        this.context = context;
        this.transactionsList = transactionsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_history_item, viewGroup, false);
        return new TransactionHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TransactionHistoryViewHolder holder = (TransactionHistoryViewHolder) viewHolder;
        holder.bindView(transactionsList.get(i));
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    private class TransactionHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAddress, tvKind;
        private TextView tvAmount;
        private TextView tvCreatedAt;
        private ImageView ivIconTrans;

        private TransactionHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvKind = itemView.findViewById(R.id.tv_kind);
            ivIconTrans = itemView.findViewById(R.id.iv_iconTransaction);
        }

        @SuppressLint("DefaultLocale")
        private void bindView(TransactionsHistoryRes.Result.Docs tranData) {


            tvCreatedAt.setText(DateTimeHelper.formattedDate(tranData.getCreatedAt(), DateTimeHelper.SDF_WITH_T, "dd.MM.yyyy"));

            if (tranData.getType().equals(Const.TRANS_TYPE_RECEIVE)) {
                tvAddress.setText("From "+tranData.getFromAddress().toString());
                tvAmount.setText(String.format("%f %s", tranData.getAmount(), tranData.getCoinAsset()));
                tvKind.setText(context.getResources().getString(R.string.receive));
                ivIconTrans.setImageResource(R.drawable.receive_ic);
               // setMargins(ivIconTrans,0,0,30,0);

            }
            else{
                tvAddress.setText("To "+tranData.getFromAddress().toString());
                ivIconTrans.setImageResource(R.drawable.send_2_ic);
                //setMargins(ivIconTrans,30,0,0,0);
                tvKind.setText(context.getResources().getString(R.string.sent));
                tvAmount.setTextColor(context.getResources().getColor(R.color.color_orange_main));
                tvAmount.setText(String.format("-%f %s", tranData.getAmount(), tranData.getCoinAsset()));

            }

            }
        private void setMargins (View view, int left, int top, int right, int bottom) {
            if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                p.setMargins(left, top, right, bottom);
                view.requestLayout();
            }
        }
    }
}
