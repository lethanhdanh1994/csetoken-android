package sg.cse.wallet.mvp.dashboard;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import sg.cse.wallet.R;
import sg.cse.wallet.utils.Support;

public class CustomMarkerView extends MarkerView {

    private TextView tvContent;
    private LinearLayout layContent;
    private TextView tvBottomContent;
    private CardView cvPoint;
    private ArrayList<String> mXValue;
    private LineChart mChart;
    private ShowListener showListener;
    private Boolean isTouchContent = false;

    // private LinearLayout layBottomContent;
    public CustomMarkerView(Context context, int layoutResource, ArrayList<String> xValue, LineChart chart, ShowListener mshowListener) {
        super(context, layoutResource);
        // this markerview only displays a textview
        tvContent = (TextView) findViewById(R.id.tvContent);
        cvPoint = (CardView) findViewById(R.id.cv_point);
        layContent= (LinearLayout) findViewById(R.id.lay_content);
        mChart = chart;
        showListener = mshowListener;
        //  layBottomContent = (LinearLayout) findViewById(R.id.lay_BottomContent);
        tvBottomContent = (TextView) findViewById(R.id.tvBottomContent);
        mXValue = xValue;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
// content (user-interface)
    @Override
    public void refreshContent(final Entry e, Highlight highlight) {
      //  isTouchContent =false;
//        cvPoint.setOnLongClickListener(new OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if(!isTouchContent){
//                    layContent.setVisibility(VISIBLE);
//                    isTouchContent =true;
//                }else {
//                    layContent.setVisibility(GONE);
//                    isTouchContent =false;
//                }
//                return false;
//            }
//
//
//        });
        tvContent.setText(Support.getCuryFormat(Double.valueOf(String.valueOf(e.getY()))));
        // String x = mChart.getXAxis().getValueFormatter().getFormattedValue(e.getX(), mChart.getXAxis());
        // tvContent.setText(x);

        showListener.onShowListener(highlight.getDataSetIndex());
        tvBottomContent.setText(Support.convert12hFormat(mXValue.get((int) e.getX())));
        // this will perform necessary layouting
        super.refreshContent(e, highlight);
    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if(mOffset == null) {
            // center the marker horizontally and vertically
            //  mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
            mOffset = new MPPointF(-(getWidth() / 2), (-(cvPoint.getY()) - (cvPoint.getWidth() / 2)));
        }

        return mOffset;
    }}

interface ShowListener {
    void onShowListener(int index);
}



