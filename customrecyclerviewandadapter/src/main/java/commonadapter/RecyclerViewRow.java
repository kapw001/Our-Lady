package commonadapter;

import android.view.View;

/**
 * Created by yasar on 28/3/18.
 */

public interface RecyclerViewRow<T> {


    View getForeGroundView();

    void showData(T item);

    void setOnItemClickListener(View.OnClickListener listener);

    void setOnItemLongClickListener(View.OnLongClickListener listener);
}
