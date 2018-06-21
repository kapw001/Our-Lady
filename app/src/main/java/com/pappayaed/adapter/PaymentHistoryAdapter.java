package com.pappayaed.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.pappayaed.R;
import com.pappayaed.common.Utils;
import com.pappayaed.data.model.FeesPaymentHistoryList;

import java.util.ArrayList;
import java.util.List;


public class PaymentHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FeesPaymentHistoryList> list;
    private static RecyclerAdapterPositionClicked recyclerAdapterPositionClicked;
    private static Context context;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private static final int UNSELECTED = -1;

    public PaymentHistoryAdapter() {
        // uncomment the line below if you want to open only one row at a time
        // viewBinderHelper.setOpenOnlyOne(true);
    }

    public PaymentHistoryAdapter(Fragment context, List<FeesPaymentHistoryList> list) {
        this.list = list;
        this.context = context.getContext();
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);

    }

    public PaymentHistoryAdapter(Context context, List<FeesPaymentHistoryList> list) {
        this.list = list;
        this.context = context;
        this.recyclerAdapterPositionClicked = (RecyclerAdapterPositionClicked) context;
        viewBinderHelper.setOpenOnlyOne(true);
    }

    public void updateList(List<FeesPaymentHistoryList> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paymenthistory_row, parent, false);
        return new RowViewHolder(view);

    }

    public void saveStates(Bundle outState) {
        viewBinderHelper.saveStates(outState);
    }

    public void restoreStates(Bundle inState) {
        viewBinderHelper.restoreStates(inState);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final FeesPaymentHistoryList object = list.get(position);


//
        ((RowViewHolder) holder).bill_no.setText(object.getBillNo());
        ((RowViewHolder) holder).status.setText(Utils.capitalizeFirstLetter(object.getStatus()));
//        ((RowViewHolder) holder).bank_name.setText(object.getBankName().equalsIgnoreCase("false") == true ? object.getBankName() : "");
        String d1 = Utils.convertDateToString(object.getPaidDate(), "yyyy-MM-dd", "dd/MM/yyyy");
        ((RowViewHolder) holder).paid_date.setText(d1);
        ((RowViewHolder) holder).paid_amount.setText(Utils.getDecimal(object.getPaidAmount()));
        ((RowViewHolder) holder).return_amount.setText(Utils.getDecimal(object.getReturnAmount()));

        String mode = object.getPaymentMode();

        if (mode.toLowerCase().equalsIgnoreCase("bank")) {
            mode = "Cheque/DD";
        }

        ((RowViewHolder) holder).payment_mode.setText(Utils.capitalizeFirstLetter(mode));
//        String d = object.getChequeDdDate().equalsIgnoreCase("false") == true ? object.getChequeDdDate() : Utils.convertDateGivenFormat(Utils.convertStringToDate(object.getChequeDdDate()));
//        ((RowViewHolder) holder).cheque_dd_date.setText(d);
//        ((RowViewHolder) holder).payment_ref.setText(object.getPaymentRef().equalsIgnoreCase("false") == true ? object.getPaymentRef() : "");


    }


    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }


    private static class RowViewHolder extends RecyclerView.ViewHolder {

        private TextView bill_no, bank_name, paid_date, paid_amount, return_amount, payment_mode, cheque_dd_date, status, payment_ref;

        public RowViewHolder(View itemView) {
            super(itemView);

            bill_no = (TextView) itemView.findViewById(R.id.bill_no);
            status = (TextView) itemView.findViewById(R.id.state);
            bank_name = (TextView) itemView.findViewById(R.id.bank_name);
            paid_date = (TextView) itemView.findViewById(R.id.paid_date);
            paid_amount = (TextView) itemView.findViewById(R.id.paid_amount);
            return_amount = (TextView) itemView.findViewById(R.id.return_amount);
            payment_mode = (TextView) itemView.findViewById(R.id.payment_mode);
            cheque_dd_date = (TextView) itemView.findViewById(R.id.cheque_dd_date);
            payment_ref = (TextView) itemView.findViewById(R.id.payment_ref);

        }


    }

    public interface RecyclerAdapterPositionClicked {
        void position(int pos, View view);

        void onRefresh();
    }

    private static void showToat(View view) {
        Toast.makeText(view.getContext(), "" + view.getId(), Toast.LENGTH_SHORT).show();
    }
}
