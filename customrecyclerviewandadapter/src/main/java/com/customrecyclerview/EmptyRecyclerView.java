package com.customrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by yasar on 22/1/18.
 */

public class EmptyRecyclerView extends FrameLayout {

    private static final String TAG = "EmptyRecyclerView";

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private TextView msg;
    private ProgressBar progressBar;
    private Button retry;


    public EmptyRecyclerView(@NonNull Context context) {
        super(context);

        init(context);

    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);

    }

    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EmptyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);

    }


    private void init(Context context) {
        this.mContext = context;

        View view = inflate(context, R.layout.myrecycler, this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        msg = (TextView) view.findViewById(R.id.msg);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        retry = (Button) view.findViewById(R.id.retry);
        recyclerView.setHasFixedSize(true);


    }

    public void setItemTouchHelper(ItemTouchHelper.SimpleCallback touchHelper) {
        new ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView);
    }

    public RecyclerView getRecyclerView() {

        return recyclerView;
    }


    public boolean getNetworkstats() {

        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;

    }

    public void hideAll() {
        retry.setVisibility(GONE);
        recyclerView.setVisibility(GONE);
        msg.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    public void setLoading() {

        retry.setVisibility(GONE);
        recyclerView.setVisibility(GONE);
        msg.setVisibility(GONE);
        progressBar.setVisibility(VISIBLE);
    }

    public void setNoInternetErrorMsg() {

        SpannableString spannableString = new SpannableString("No Internet connection. Enable Wi-Fi or Cellular Mobile data, then try again.");
        ClickableSpan spanWiFi = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //to enable wifi
                try {
                    mContext.getApplicationContext().startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS).setFlags(FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ClickableSpan spanMobile = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //to enable mobile data
                try {
                    mContext.getApplicationContext().startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS).setFlags(FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        spannableString.setSpan(spanWiFi, 31, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 31, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(spanMobile, 40, 60, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 40, 60, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setErrorMsg(spannableString);

    }


    public void setRetryClickListener(OnClickListener retryClickListener) {
        retry.setOnClickListener(retryClickListener);
    }

    public void setErrorMsg(SpannableString errorMsg) {

        recyclerView.setVisibility(GONE);
        msg.setVisibility(VISIBLE);
        retry.setVisibility(VISIBLE);
        progressBar.setVisibility(GONE);
        msg.setText(errorMsg);
        msg.setMovementMethod(LinkMovementMethod.getInstance());
        msg.setHighlightColor(Color.TRANSPARENT);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setErrorMsg(String errorMsg) {

        recyclerView.setVisibility(GONE);
        msg.setVisibility(VISIBLE);
        retry.setVisibility(VISIBLE);

        if (errorMsg.toLowerCase().equalsIgnoreCase(getResources().getString(R.string.nodata))) {
            retry.setVisibility(GONE);
        }

        progressBar.setVisibility(GONE);
        msg.setText(errorMsg);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setSuccessfullyLoadedData() {
        retry.setVisibility(GONE);
        recyclerView.setVisibility(VISIBLE);
        msg.setVisibility(GONE);
        progressBar.setVisibility(GONE);
    }

    public void setDivider(int orientation) {
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, orientation));
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void setSwipeRefreshEnabled(boolean isEnabled) {
        swipeRefreshLayout.setEnabled(isEnabled);
    }

    public void setSwipeColorScheme(int color) {
        swipeRefreshLayout.setColorSchemeColors(color);
    }

    public void setSwipeRefreshing(boolean isRefreshing) {
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    public void setSwipeRefreshListener(SwipeRefreshLayout.OnRefreshListener swipeRefreshListener) {
        swipeRefreshLayout.setOnRefreshListener(swipeRefreshListener);
    }

    public void setLinearLayoutLayout(LinearLayoutManager layoutManager) {

        if (recyclerView != null)
            recyclerView.setLayoutManager(layoutManager);
        else Log.e(TAG, "setLinearLayoutLayout: recylerview not initialized ");

    }

    public void setLinearLayoutLayout(boolean isHorizontal) {

        if (isHorizontal) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }

    }

    private void setGridLayout(int columnCount) {

        recyclerView.setLayoutManager(new GridLayoutManager(mContext, columnCount));

    }

    private void setStaggeredGridLayoutManager(int columnCount, int orientation) {

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(columnCount, orientation));

    }

    public <T extends RecyclerView.Adapter> void setAdapter(T adapter) {

        recyclerView.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }

        emptyObserver.onChanged();
    }


    private RecyclerView.AdapterDataObserver emptyObserver = new RecyclerView.AdapterDataObserver() {


        @Override
        public void onChanged() {

            RecyclerView.Adapter<?> adapter = recyclerView.getAdapter();
            if (adapter != null) {
                if (adapter.getItemCount() == 0) {
                    setErrorMsg(getResources().getString(R.string.nodata));
                } else {
                    setSuccessfullyLoadedData();
                }
            }

        }
    };

}
