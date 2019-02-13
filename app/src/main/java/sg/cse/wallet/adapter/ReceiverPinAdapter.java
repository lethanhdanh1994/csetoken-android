package sg.cse.wallet.adapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sg.cse.wallet.CSEApplication;
import sg.cse.wallet.R;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.utils.Support;

public class ReceiverPinAdapter extends RecyclerView.Adapter<ReceiverPinAdapter.MyViewHolder>{
    private List<WalletRes.Doc> listWallet;

    public ReceiverPinAdapter(List<WalletRes.Doc> listWallet) {
        this.listWallet = listWallet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardstack_receive, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final WalletRes.Doc coin = listWallet.get(i);
        myViewHolder.tvcoinAsset.setText(coin.getCoinAsset());
        myViewHolder.tvAddressWallet.setText(coin.getAddress());
        myViewHolder.tvAddressWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Support.setClipboard(CSEApplication.getAppContext(),String.valueOf(coin.getAddress()));
                myViewHolder.tvAddressWallet.setText(R.string.copy_clipboard);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myViewHolder.tvAddressWallet.setText(String.valueOf(coin.getAddress()));
                    }
                }, 2000);
            }
        });
        myViewHolder.ivQRCode.setImageBitmap(Support.GenerateBarcodetoBitmap(coin.getAddress().toString()));
    }

    @Override
    public int getItemCount() {
        return listWallet.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvcoinAsset;
        public ImageView ivQRCode;
        public TextView tvAddressWallet;


        public MyViewHolder(@NonNull View view) {
            super(view);
            this.tvcoinAsset = (TextView) view.findViewById(R.id.tv_code);
            this.ivQRCode = (ImageView) view.findViewById(R.id.iv_qrCode);
            this.tvAddressWallet = (TextView) view.findViewById(R.id.tv_address);
        }
    }
}
