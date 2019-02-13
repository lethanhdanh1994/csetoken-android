package sg.cse.wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import sg.cse.wallet.R;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.utils.Support;


public class CardStackViewAdapter extends ArrayAdapter<WalletRes.Doc> {
    private String balancetext;

    public CardStackViewAdapter(Context context, String balancetext) {
        super(context, 0);
        this.balancetext = balancetext;
    }


    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.item_cardstack, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        WalletRes.Doc coin = getItem(position);

        holder.name.setText(coin.getCoinAsset());
        holder.amount.setText(Support.getValueFormat(coin.getAvailableAmount()));
        holder.usd.setText(Support.getCuryFormat(coin.getEstimatedUSDT()));

        holder.balancetext.setText(balancetext);

        return contentView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView amount;
        public TextView usd;
        public TextView balancetext;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.amount = (TextView) view.findViewById(R.id.amount);
            this.usd = (TextView) view.findViewById(R.id.usd);
            this.balancetext =(TextView) view.findViewById(R.id.balancetext);
        }
    }

}

