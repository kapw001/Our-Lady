package com.pappayaed.ui.circular;

import android.graphics.Color;
import android.view.View;

import com.pappayaed.base.BasePresenter;
import com.pappayaed.common.NullCheckUtils;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.Circular;
import com.pappayaed.data.model.CircularAndHomeWork;
import com.pappayaed.data.model.HomeWork;
import com.pappayaed.data.model.ResultResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import calendar.android.com.customcalendar.CalendarUtils;
import calendar.android.com.customcalendar.EventObjects;

/**
 * Created by yasar on 27/3/18.
 */

public class CircularPresenterImpl<V extends ICircularView> extends BasePresenter<V> implements ICircularPresenter<V>, ICircularIntractor.OnFinishedListener {

    private ICircularIntractor iCircularIntractor;

    private ResultResponse resultResponse;

    private boolean isCircularEnabled = true;

    public CircularPresenterImpl(ICircularIntractor iCircularIntractor) {
        super(iCircularIntractor.getDataSource());

        this.iCircularIntractor = iCircularIntractor;
    }

    @Override
    public void getCircularAndHomeWork() {

        if (this.resultResponse == null) {
            getMvpView().showLoading();
            iCircularIntractor.getCircularAndHomework(this);
        } else {

            onSuccss(this.resultResponse);

        }

    }

    @Override
    public void onSuccss(ResultResponse resultResponse) {

        this.resultResponse = resultResponse;

        getMvpView().hideLoading();

        if (isCircularEnabled)
            loadCircularData();
        else loadHomeWOrkData();
    }


    public void loadCircularData() {


        if (!NullCheckUtils.isEmpty(resultResponse) && !NullCheckUtils.isEmpty(resultResponse.getResult())) {

            if (resultResponse != null && resultResponse.getResult().getCirculars() != null && resultResponse.getResult().getCirculars().size() > 0) {


                List<EventObjects> eventObjectsList = new ArrayList<>();

                List<Circular> list = resultResponse.getResult().getCirculars();

                for (Circular circular : list
                        ) {

                    EventObjects objects = new EventObjects(circular.getDescription(), CalendarUtils.convertStringToDate(circular.getDate()), Color.parseColor("#F06363"));
                    eventObjectsList.add(objects);

                }

                getMvpView().setEventList(eventObjectsList);

                getMvpView().setDataCircular(list);


            } else {
                getMvpView().setEventList(Collections.EMPTY_LIST);
                getMvpView().emptyData();

            }
        } else {
            getMvpView().setEventList(Collections.EMPTY_LIST);
            getMvpView().emptyData();

        }

    }

    public void loadHomeWOrkData() {

        if (!NullCheckUtils.isEmpty(resultResponse) && !NullCheckUtils.isEmpty(resultResponse.getResult())) {

            if (resultResponse != null && resultResponse.getResult().getHomeWorks() != null && resultResponse.getResult().getHomeWorks().size() > 0) {

                List<EventObjects> eventObjectsList = new ArrayList<>();

                List<HomeWork> list = resultResponse.getResult().getHomeWorks();

                for (HomeWork homeWork : list
                        ) {

                    EventObjects objects = new EventObjects(homeWork.getDescription(), CalendarUtils.convertStringToDate(homeWork.getDate()), Color.parseColor("#F06363"));
                    eventObjectsList.add(objects);

                }

                getMvpView().setEventList(eventObjectsList);

                getMvpView().setDataHome(list);


            } else {
                getMvpView().setEventList(Collections.EMPTY_LIST);
                getMvpView().emptyData();

            }
        } else {
            getMvpView().setEventList(Collections.EMPTY_LIST);
            getMvpView().emptyData();

        }

    }

    @Override
    public void setIsCircular(boolean isCircularEnabled) {
        this.isCircularEnabled = isCircularEnabled;
    }

    @Override
    public void onItemClick(View view, int position, Object o) {

        Date mDate = (Date) o;


        if (isCircularEnabled) {

            if (resultResponse != null && resultResponse.getResult().getCirculars() != null && resultResponse.getResult().getCirculars().size() > 0) {
                List<Circular> list = Utils.getParticularDateEventsForCircular(mDate, resultResponse.getResult().getCirculars());

                if (list.size() > 0)
                    getMvpView().setDataCircular(list);
                else {
//                    getMvpView().setEventList(Collections.EMPTY_LIST);
                    getMvpView().emptyData();

                }


            } else {
//                getMvpView().setEventList(Collections.EMPTY_LIST);
                getMvpView().emptyData();

            }


        } else {

            if (resultResponse != null && resultResponse.getResult().getHomeWorks() != null && resultResponse.getResult().getHomeWorks().size() > 0) {
                List<HomeWork> list = Utils.getParticularDateEventsForHomeWork(mDate, resultResponse.getResult().getHomeWorks());

                if (list.size() > 0)
                    getMvpView().setDataHome(list);
                else {
//                    getMvpView().setEventList(Collections.EMPTY_LIST);
                    getMvpView().emptyData();

                }


            } else {
//                getMvpView().setDataHome(Collections.EMPTY_LIST);
                getMvpView().emptyData();

            }

        }

    }


    @Override
    public void onFail(Throwable throwable) {
        getMvpView().hideLoading();
        getMvpView().onFail(throwable);
    }

    @Override
    public void onNetworkFailure() {

        getMvpView().hideLoading();
        getMvpView().onNetworkFailure();

    }
}
