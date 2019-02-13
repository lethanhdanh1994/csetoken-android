package sg.cse.wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import sg.cse.wallet.R;
import sg.cse.wallet.mvp.profile.Coin;


public class CardStackViewProfileAdapter extends ArrayAdapter<Coin> {

    public CardStackViewProfileAdapter(Context context) {
        super(context, 0);
    }


    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        ViewHolder holder;

        if (contentView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            contentView = inflater.inflate(R.layout.item_profile, parent, false);
            holder = new ViewHolder(contentView);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }

        return contentView;
    }

    private static class ViewHolder {
        public TextView name;
        public TextView location;
        public TextView id;

        public ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.name);
            this.location = (TextView) view.findViewById(R.id.location);
        }
    }

}

