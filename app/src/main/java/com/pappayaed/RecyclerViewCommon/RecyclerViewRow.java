package com.pappayaed.RecyclerViewCommon;

import android.view.View;

/**
 * Created by yasar on 28/3/18.
 */

public interface RecyclerViewRow<T> {

    void showData(T item);

    void setOnItemClickListener(View.OnClickListener listener);

    void setOnItemLongClickListener(View.OnLongClickListener listener);
}
