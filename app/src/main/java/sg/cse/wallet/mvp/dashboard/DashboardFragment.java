package sg.cse.wallet.mvp.dashboard;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sg.cse.wallet.R;
import sg.cse.wallet.adapter.TransactionHistoryAdapter;
import sg.cse.wallet.adapter.WalletAdapter;
import sg.cse.wallet.base.BaseDialogFragment;
import sg.cse.wallet.model.ChartRes;
import sg.cse.wallet.model.wallet.TransactionsHistoryRes;
import sg.cse.wallet.model.wallet.WalletRes;
import sg.cse.wallet.prefs.Const;
import sg.cse.wallet.utils.Support;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseDialogFragment implements WalletView {


    @BindView(R.id.tv1H)
    TextView tv1H;
    @BindView(R.id.tv24H)
    TextView tv24H;
    @BindView(R.id.tv1W)
    TextView tv1W;
    @BindView(R.id.tv1M)
    TextView tv1M;
    @BindView(R.id.tv1Y)
    TextView tv1Y;

    @Override
    public int setView() {
        return R.layout.fragment_dashboard;
    }

    private PieChart pieChart;
    private LineChart lineChart;
    private CardStackView cardStackView;
    private RecyclerView rvTransactionHistory;
    private Double totalAmountChart = 0.0;
    private String TYPE_CHART = "hour";
    private String coinAsset;


    @Override
    public void initView() {
        pieChart = rootView.findViewById(R.id.pieChart);
        //pieChart.setBackgroundColor(Color.TRANSPARENT); //set whatever color you prefer
        lineChart = rootView.findViewById(R.id.lineChart);
        cardStackView = rootView.findViewById(R.id.cardStackView);
        rvTransactionHistory = rootView.findViewById(R.id.rvTransactionHistory);

        ////
        setDefaultTvTime();
        tv1H.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

    }

    private WalletPresenter walletPresenter;
    private WalletAdapter walletAdapter;
    private List<WalletRes.Doc> walletList;

    @Override
    public void initValue() {
        showLoading();
        walletPresenter = new WalletPresenter(this);
        walletPresenter.getWallet();
    }

    @Override
    public void initAction() {

    }


    private void setupChart(LineChart chart, LineData data, int color, ArrayList<ChartRes.Result> dataList, String type) {
        final ArrayList<String> xAxisLabel = new ArrayList<>();
//        xAxisLabel.add("2001");
//        xAxisLabel.add("2002");
//        xAxisLabel.add("2003");
//        xAxisLabel.add("2004");
//        xAxisLabel.add("2005");
//        xAxisLabel.add("2006");
//        xAxisLabel.add("2007");
//        xAxisLabel.add("2008");
//        xAxisLabel.add("2009");
//        xAxisLabel.add("2010");
        int k = 0;
        for (int i = 0; i < dataList.size(); i++) {
//            k++;
//            if (k == 10) {
//                k=0;
                xAxisLabel.add(String.valueOf(dataList.get(i).getTime()));
         //   }

        }

        //chart.getAxisLeft().setLabelCount(1, true);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        // xAxis.setLabelCount(1);


        switch (type) {
            case Const.TYPE_1H:
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        String data = Support.convertTimeFormat(xAxisLabel.get((int) value));

                        return data;
                    }
                });
                break;
            case Const.TYPE_24H:
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        String data = Support.convertTimeFormat(xAxisLabel.get((int) value));

                        return data;
                    }
                });
                break;
            case Const.TYPE_1W:
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        String data = Support.convertDayFormat(xAxisLabel.get((int) value));

                        return data;
                    }
                });
                break;
            case Const.TYPE_1M:
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        String data = Support.convertDayFormat(xAxisLabel.get((int) value));

                        return data;
                    }
                });
                break;
            case Const.TYPE_1Y:
                xAxis.setValueFormatter(new IAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        String data = Support.convertDayFormat(xAxisLabel.get((int) value));

                        return data;
                    }
                });
                break;

        }


        //xAxis.setGridColor(ContextCompat.getColor(context, R.color.colorSecondaryText));
        xAxis.setTextColor(ContextCompat.getColor(context, R.color.colorSecondaryText));
        chart.getXAxis().setDrawAxisLine(false);


//      data.setValueTextColor(ContextCompat.getColor(context, R.color.colorLightBlue));
//        data.setValueFormatter(new IValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                String values ="$"+ String.valueOf(value);
//                return values;
//            }
//        });
        data.setDrawValues(false);
//
//        data.setHighlightEnabled(true);

        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        //((LineDataSet) data.getDataSetByIndex(0)).getCircleHoleColor();

        // no description text
        chart.getDescription().setEnabled(false);
        // mChart.setDrawHorizontalGrid(false);
        //
        // enable / disable grid background
        chart.setDrawGridBackground(false);
//        chart.getRenderer().getGridPaint().setGridColor(Color.WHITE & 0x70FFFFFF);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setBackgroundColor(color);

       // LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  final View v = vi.inflate(R.layout.item_bottom_content_chart, null);
       // final TextView tvContent = (TextView) v.findViewById(R.id.tvBottomContent);

        CustomMarkerView mv = new CustomMarkerView(getActivity(), R.layout.marker_line_chart,xAxisLabel, chart, new ShowListener() {
            @Override
            public void onShowListener(int index) {

                // tvContent.setText(xAxisLabel.get(index));


//                tvContent.setText("sdffsdff");
//                // lineChart.addView(v);
//
//                lineChart.removeAllViews();
//                lineChart.addView(v, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


            }
        });
        chart.setMarkerView(mv);

        // set custom chart offsets (automatic offset calculation is hereby disabled)
        //   chart.setViewPortOffsets(10, 0, 10, 0);

        // add data
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();
        l.setEnabled(false);

        chart.getAxisLeft().setEnabled(false);
        chart.getAxisLeft().setSpaceTop(40);
        chart.getAxisLeft().setSpaceBottom(40);
        chart.getAxisRight().setEnabled(false);

        chart.getXAxis().setEnabled(true);

        // animate calls invalidate()...
        chart.animateX(2500);

        // chart.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        chart.getRenderer().getPaintRender().setShadowLayer(6, 5, 6, Color.GRAY);

        ///zoom
        //chart.setScaleX(2f);
        chart.getViewPortHandler().setMinimumScaleX(10f);
        chart.getViewPortHandler().setMinimumScaleY(0f);
    }

    @Override
    public void onGetWalletSuccess(final List<WalletRes.Doc> walletList, Double total) {
        this.walletList = walletList;
        totalAmountChart = total;
        pieChart.notifyDataSetChanged();
        walletAdapter = new WalletAdapter(context);
        walletAdapter.addAll(walletList);
        cardStackView.setAdapter(walletAdapter);

        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {

            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                if (cardStackView.getTopIndex() == walletList.size()) {
                    paginateCardStackView();
                } else {
                    setupPieChart(walletList, cardStackView.getTopIndex());
                }
            }

            @Override
            public void onCardReversed() {

            }

            @Override
            public void onCardMovedToOrigin() {

            }

            @Override
            public void onCardClicked(int index) {

            }
        });
        setupPieChart(walletList, cardStackView.getTopIndex());
        showLoading();
        coinAsset = walletList.get(0).getCoinAsset();
        walletPresenter.getChart(walletList.get(0).getCoinAsset(), Const.TYPE_1H);
    }

    /**
     * Setup pie chart
     */
    @SuppressLint("DefaultLocale")
    private void setupPieChart(List<WalletRes.Doc> walletList, int currentPos) {
        pieChart.setTouchEnabled(false);
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < walletList.size(); i++) {
            entries.add(new PieEntry(Float.valueOf(String.valueOf(walletList.get(i).getEstimatedUSDT()))));
        }
        PieDataSet set = new PieDataSet(entries, "");
        set.setColor(Color.WHITE);
        set.setHighlightEnabled(true);
        set.setSliceSpace(10);
//        pieChart.setCenterText(String.format("$%.2f\n%s",
//                walletList.get(currentPos).getAvailableAmount(),
//                walletList.get(currentPos).getCoinAsset()));

        pieChart.setCenterText(Support.getCuryFormat(totalAmountChart));
        PieData data = new PieData(set);
        data.setValueTextColor(Color.TRANSPARENT);
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDrawEntryLabels(false);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setCenterTextSize(25);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHoleColor(ContextCompat.getColor(context, R.color.colorPrimary));
        pieChart.setHoleRadius(90);
        pieChart.getLegend().setEnabled(false);
        pieChart.invalidate(); // refresh

        // get transactions history
        walletPresenter.getTransactionsHistory(walletList.get(currentPos).getAddress());
        showLoading();
    }

    /**
     * Paginate card stack view for loop
     */
    private void paginateCardStackView() {
        cardStackView.setPaginationReserved();
        walletAdapter.addAll(walletList);
        walletAdapter.notifyDataSetChanged();
        onGetWalletSuccess(walletList, totalAmountChart);
    }

    private LineData getData(ArrayList<ChartRes.Result> dataList) {

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < dataList.size(); i++) {
            yVals.add(new Entry(i, Float.valueOf(String.valueOf(dataList.get(i).getPrice()))));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set1.setCubicIntensity(0.2f);
        set1.setLineWidth(1.5f);
//        set1.setCircleRadius(10f);
//        set1.setCircleHoleRadius(8f);
        set1.setDrawVerticalHighlightIndicator(false);
        set1.setColor(ContextCompat.getColor(context, R.color.color_content_chart));
        // set1.setCircleColorHole(ContextCompat.getColor(context, R.color.colorLightBlue));
        //  set1.setCircleColor(ContextCompat.getColor(context, R.color.colorWhite));
        set1.setHighLightColor(ContextCompat.getColor(context, R.color.colorLightBlue));
        //   set1.setDrawValues(true);

        set1.setDrawCircles(false);


        // create a data object with the datasets
        LineData data = new LineData(set1);
        data.setValueTextSize(12);
        return data;
    }

    @Override
    public void onGetWalletError(String message) {
        dismissAll();
        showAlert(message, context.getString(R.string.up_ok), null, null);
    }

    @Override
    public void onGetTransactionsSuccess(TransactionsHistoryRes.Result result) {
        dismissAll();
        rvTransactionHistory.setHasFixedSize(true);
        rvTransactionHistory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        rvTransactionHistory.setNestedScrollingEnabled(false);
        TransactionHistoryAdapter adapter = new TransactionHistoryAdapter(context, result.getDocs());
        rvTransactionHistory.setAdapter(adapter);
    }

    @Override
    public void onGetTransactionsError(String message) {
        dismissAll();
        showAlert(message, context.getString(R.string.up_ok), null, null);
    }

    @Override
    public void onConnectionError() {
        dismissAll();
        showAlert(context.getString(R.string.err_connection_fail),
                context.getString(R.string.up_ok), null, null);
    }

    @Override
    public void onGetDataChartSuccess(ArrayList<ChartRes.Result> listItem, String type) {
        dismissAll();

        ArrayList<ChartRes.Result> newItemList = new ArrayList<>(listItem);

        Collections.reverse(newItemList);

        LineData data = getData(newItemList);
        setupChart(lineChart, data, Color.rgb(255, 255, 255), newItemList, type);
    }


    @Override
    public void onGetDataChartFails() {
        dismissAll();
    }


    private void setDefaultTvTime() {
        tv1H.setTextColor(getResources().getColor(R.color.colorSecondaryText));
        tv24H.setTextColor(getResources().getColor(R.color.colorSecondaryText));
        tv1W.setTextColor(getResources().getColor(R.color.colorSecondaryText));
        tv1M.setTextColor(getResources().getColor(R.color.colorSecondaryText));
        tv1Y.setTextColor(getResources().getColor(R.color.colorSecondaryText));
    }

    @OnClick({R.id.tv1H, R.id.tv24H, R.id.tv1W, R.id.tv1M, R.id.tv1Y})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv1H:
                showLoading();
                walletPresenter.getChart(coinAsset, Const.TYPE_1H);
                setDefaultTvTime();
                tv1H.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.tv24H:
                showLoading();
                walletPresenter.getChart(coinAsset, Const.TYPE_24H);
                setDefaultTvTime();
                tv24H.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.tv1W:
                showLoading();
                walletPresenter.getChart(coinAsset, Const.TYPE_1W);
                setDefaultTvTime();
                tv1W.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.tv1M:
                showLoading();
                walletPresenter.getChart(coinAsset, Const.TYPE_1M);
                setDefaultTvTime();
                tv1M.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.tv1Y:
                showLoading();
                walletPresenter.getChart(coinAsset, Const.TYPE_1Y);
                setDefaultTvTime();
                tv1Y.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }
    }
}
