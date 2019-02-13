package sg.cse.wallet.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import sg.cse.wallet.R;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public class SAlert {

    private Context context;
    private AlertDialog dialog;

    public SAlert(Context context) {
        this.context = context;
    }

    /**
     * Show error dialog
     *
     * @param message
     */
    public void show(String message) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_error, null, true);
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        if (message != null) {
            tvMessage.setText(message);
        }

        dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(true)
                .create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (((Activity) context).isFinishing()) {
            return;
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * Show with action
     *
     * @param message
     * @param positiveText
     * @param negativeText
     * @param onActionClickListener
     */
    public void show(String message, String positiveText, String negativeText,
                     final OnActionClickListener onActionClickListener) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_error, null, true);
        View rootView = view.findViewById(R.id.rootView);
        rootView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorBackground));
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        if (message != null) {
            tvMessage.setText(message);
        }

        dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(true)
                .setPositiveButton(positiveText != null ? positiveText : "", null)
                .setNegativeButton(negativeText != null ? negativeText : "", null)
                .create();
        dialog.setCanceledOnTouchOutside(true);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.colorBackground)));
        }

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onActionClickListener != null) {
                            onActionClickListener.onPositiveClicked();
                        }
                    }
                });
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                        if (onActionClickListener != null) {
                            onActionClickListener.onNegativeClicked();
                        }
                    }
                });
            }
        });
        if (((Activity) context).isFinishing()) {
            return;
        }
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * Dismiss error dialog
     */
    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    // INTERFACE
    public interface OnActionClickListener {
        void onPositiveClicked();

        void onNegativeClicked();
    }
}
