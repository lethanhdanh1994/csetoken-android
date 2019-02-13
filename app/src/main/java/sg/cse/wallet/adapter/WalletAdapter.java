package sg.cse.wallet.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import sg.cse.wallet.R;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.utils.Support;

public class WalletAdapter extends ArrayAdapter<WalletRes.Doc> {

    public WalletAdapter(Context context) {
        super(context, 0);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View contentView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.wallet_item, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        WalletRes.Doc wallet = getItem(position);
        if (wallet != null) {
            holder.tvWalletName.setText(String.valueOf(wallet.getAvailableAmount()) +" "+wallet.getCoinAsset());
            holder.tvPrice.setText(Support.getCuryFormat(wallet.getEstimatedUSDT()));
        }

        return contentView;
    }

    private static class ViewHolder {

        private TextView tvWalletName;
        private TextView tvPrice;

        private ViewHolder(View view) {
            this.tvWalletName = view.findViewById(R.id.tvWalletName);
            this.tvPrice = view.findViewById(R.id.tvPrice);
        }
    }

}

