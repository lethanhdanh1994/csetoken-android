package sg.cse.wallet.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import sg.cse.wallet.CSEApplication;
import sg.cse.wallet.R;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.utils.Support;

public class CardStackViewReceiveAdapter extends ArrayAdapter<WalletRes.Doc> {


    public CardStackViewReceiveAdapter(Context context) {
        super(context, 0);
    }

        @Override
        public View getView ( int position, View contentView, ViewGroup parent){
            final CardStackViewReceiveAdapter.ViewHolder holder;

            if (contentView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                contentView = inflater.inflate(R.layout.item_cardstack_receive, parent, false);
                holder = new CardStackViewReceiveAdapter.ViewHolder(contentView);
                contentView.setTag(holder);
            } else {
                holder = (CardStackViewReceiveAdapter.ViewHolder) contentView.getTag();
            }

            final WalletRes.Doc coin = getItem(position);

            holder.tvcoinAsset.setText(coin.getCoinAsset());
            holder.tvAddressWallet.setText(String.valueOf(coin.getAddress()));


            holder.tvAddressWallet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Support.setClipboard(CSEApplication.getAppContext(),String.valueOf(coin.getAddress()));
                    holder.tvAddressWallet.setText(R.string.copy_clipboard);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            holder.tvAddressWallet.setText(String.valueOf(coin.getAddress()));
                        }
                    }, 2000);
                }
            });
            holder.ivQRCode.setImageBitmap(Support.GenerateBarcodetoBitmap(coin.getAddress().toString()));
            return contentView;
        }

        private static class ViewHolder {
            public TextView tvcoinAsset;
            public ImageView ivQRCode;
            public TextView tvAddressWallet;


            public ViewHolder(View view) {

                this.tvcoinAsset = (TextView) view.findViewById(R.id.tv_code);
                this.ivQRCode = (ImageView) view.findViewById(R.id.iv_qrCode);
                this.tvAddressWallet = (TextView) view.findViewById(R.id.tv_address);

            }
        }

    }

