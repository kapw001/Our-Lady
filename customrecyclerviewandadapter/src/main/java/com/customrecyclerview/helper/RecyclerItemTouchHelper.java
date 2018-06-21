package com.customrecyclerview.helper;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import commonadapter.RecyclerViewAdapter;

/**
 * Created by yasar on 12/4/18.
 */

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private static final String TAG = "RecyclerItemTouchHelper";

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {


            if (getViewNotNull(viewHolder)) {
                final View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).mRow.getForeGroundView();

                getDefaultUIUtil().onSelected(foregroundView);
            } else {

                Log.e(TAG, "onSelectedChanged: There is no fore ground view ");
            }
        }
    }

    private boolean getViewNotNull(RecyclerView.ViewHolder viewHolder) {

        return ((RecyclerViewAdapter.ViewHolder) viewHolder).mRow.getForeGroundView() != null;
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        if (getViewNotNull(viewHolder)) {
            final View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).mRow.getForeGroundView();
            getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                    actionState, isCurrentlyActive);
        } else {

            Log.e(TAG, "onSelectedChanged: There is no fore ground view ");
        }

    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (getViewNotNull(viewHolder)) {
            final View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).mRow.getForeGroundView();
            getDefaultUIUtil().clearView(foregroundView);
        } else {

            Log.e(TAG, "onSelectedChanged: There is no fore ground view ");
        }


    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        if (getViewNotNull(viewHolder)) {

            final View foregroundView = ((RecyclerViewAdapter.ViewHolder) viewHolder).mRow.getForeGroundView();

            getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                    actionState, isCurrentlyActive);
        } else {

            Log.e(TAG, "onSelectedChanged: There is no fore ground view ");
        }


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
