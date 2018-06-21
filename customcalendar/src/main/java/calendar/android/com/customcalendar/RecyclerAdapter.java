package calendar.android.com.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yasar on 13/4/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolders> {

    private List<Date> monthlyDates;
    private Calendar currentDate;
    private List<EventObjects> allEvents;
    private Context context;
    private EventHighlight eventHighlight;
    private onItemClick mOnItemClick;

    public RecyclerAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, List<EventObjects> allEvents, EventHighlight eventHighlight, onItemClick onItemClick) {

        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        this.context = context;
        this.eventHighlight = eventHighlight;
        this.mOnItemClick = onItemClick;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cell_layout, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    public void updateEvents(List<EventObjects> allEvents) {
        this.allEvents.clear();
        this.allEvents = allEvents;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {


        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());

        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);

        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        holder.calendar_date.setText(String.valueOf(dayValue));

        if (dayValue == c.get(Calendar.DAY_OF_MONTH) && displayMonth == c.get(Calendar.MONTH) + 1 && displayYear == dateCal.get(Calendar.YEAR)) {
            holder.calendar_date.setTextColor(Color.BLUE);
            holder.calendar_date.setAlpha(1f);
        } else {
            holder.calendar_date.setTextColor(Color.BLACK);
            holder.calendar_date.setAlpha(.4f);
        }

        int day = dateCal.get(Calendar.DAY_OF_WEEK);

        if (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
            holder.itemView.setBackgroundColor(Color.parseColor("#d4d4d4"));
            holder.calendar_date.setAlpha(.2f);
        }

        if (displayMonth == currentMonth && displayYear == currentYear) {
//            view.setBackgroundColor(Color.parseColor("#FF5733"));
        } else {
//            view.setBackgroundColor(Color.parseColor("#cccccc"));
            holder.calendar_date.setText("");
        }

        Calendar eventCalendar = Calendar.getInstance();

//        Log.e(TAG, "getView: " + allEvents.size());

        holder.event.setVisibility(View.GONE);

        for (int i = 0; i < allEvents.size(); i++) {
            eventCalendar.setTime(allEvents.get(i).getDate());
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {
//                holder.event.setBackgroundColor(allEvents.get(i).getColor());


                switch (eventHighlight) {
                    case CIRCLE:
                        setCircleColor(holder.event, allEvents.get(i).getColor());
                        holder.event.setVisibility(View.VISIBLE);
                        break;
                    case RECTANGLE:

                        holder.itemView.setBackgroundColor(allEvents.get(i).getColor());
                        holder.calendar_date.setTextColor(Color.WHITE);
                        holder.calendar_date.setTypeface(Typeface.DEFAULT_BOLD);
                        holder.calendar_date.setAlpha(1f);
                        break;
                }

                setScaleAnimation(holder.calendar_date);


            }
        }

//        holder.event.setText(itemList.get(position).getPhoto());
    }


    private void setCircleColor(View view, @ColorInt int color) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(color);
        shape.setStroke(2, color);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(shape);
        } else {
            view.setBackgroundDrawable(shape);
        }

    }

    @Override
    public int getItemCount() {
        return this.monthlyDates.size();
    }


    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView calendar_date;
        public View event;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            calendar_date = (TextView) itemView.findViewById(R.id.calendar_date_id);
            event = (View) itemView.findViewById(R.id.event_id);
        }

        @Override
        public void onClick(View view) {

//            switch (eventHighlight) {
//                case CIRCLE:
//                    event.setVisibility(View.VISIBLE);
//                    break;
//                case RECTANGLE:
//                    itemView.setBackgroundColor(Color.BLUE);
//                    break;
//            }

            if (mOnItemClick != null) {
                mOnItemClick.onItemClick(view, getAdapterPosition(), monthlyDates.get(getAdapterPosition()));
            }

        }
    }

    private void setScaleAnimation(View view) {

        Animation zoomout = AnimationUtils.loadAnimation(view.getContext(), R.anim.scale);
        view.setAnimation(zoomout);

//        view.animate().scaleX(3).setDuration(3000).start(); view.animate().scaleY(3).setDuration(3000).start();

//        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(1000);
//        view.startAnimation(anim);

//        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        anim.setDuration(2000);
//        view.startAnimation(anim);
    }

}