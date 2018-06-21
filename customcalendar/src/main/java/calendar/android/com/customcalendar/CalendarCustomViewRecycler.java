package calendar.android.com.customcalendar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yasar on 13/4/18.
 */

public class CalendarCustomViewRecycler extends LinearLayout implements onItemClick {

    private static final String TAG = CalendarCustomViewRecycler.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate;
    private RecyclerView calendarGridView;
    private static final int MAX_CALENDAR_COLUMN = 42;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private RecyclerAdapter mAdapter;

    private onItemClick onItemClick;

    private EventHighlight eventHighlight = EventHighlight.CIRCLE;

    private List<EventObjects> eventList = new ArrayList<>();

    public CalendarCustomViewRecycler(Context context) {
        super(context);
    }

    public CalendarCustomViewRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);

    }

    public void setEventHighlight(EventHighlight eventHighlight) {
        this.eventHighlight = eventHighlight;
        setUpCalendarAdapter();
    }

    public CalendarCustomViewRecycler(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layoutrecycler, this);
        previousButton = (ImageView) view.findViewById(R.id.previous_month);
        nextButton = (ImageView) view.findViewById(R.id.next_month);
        currentDate = (TextView) view.findViewById(R.id.display_current_date);
        calendarGridView = (RecyclerView) view.findViewById(R.id.calendar_grid);

        calendarGridView.setLayoutManager(new GridLayoutManager(getContext(), 7));
        calendarGridView.setHasFixedSize(true);
        calendarGridView.addItemDecoration(new ItemOffsetDecoration(1));


    }

    public void setOnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public void onItemClick(View view, int position, Object o) {

//        Toast.makeText(context, "" + ((Date) o).getDate(), Toast.LENGTH_SHORT).show();

        if (onItemClick != null) {
            onItemClick.onItemClick(view, position, o);
        } else {
            Log.e(TAG, "onItemClick: There is no onItemClick Listener ");
        }


    }

    public void setEvents(List<EventObjects> eventsList) {

        this.eventList.clear();
        this.eventList = eventsList;

        mAdapter.updateEvents(this.eventList);

        setUpCalendarAdapter();
    }

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
        private int offset;

        public ItemOffsetDecoration(int offset) {
            this.offset = offset;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = offset;
            outRect.right = offset;
            outRect.bottom = offset;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = offset;
            }

        }
    }

    private void setPreviousButtonClickEvent() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();

                startandendDate(cal);
            }
        });
    }

    private void setNextButtonClickEvent() {
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
                startandendDate(cal);
            }
        });
    }

    private void setGridCellClickEvents() {

    }


    private void startandendDate(Calendar cal) {


        Calendar c = cal;
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = 1;
        c.set(year, month, day);
        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("First Day of month: " + c.getTime());
        Log.e(TAG, "startandendDate: " + c.getTime());
        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
        System.out.println("Last Day of month: " + c.getTime());
        Log.e(TAG, "startandendDate: end date  " + c.getTime());


    }


    private void setUpCalendarAdapter() {
        List<Date> dayValueInCells = new ArrayList<Date>();

//        List<EventObjects> mEvents = new ArrayList<>();

//        EventObjects eventObjects = new EventObjects(3, "Test", CalendarUtils.convertStringToDate("14-04-2018"), Color.RED);
//
//        mEvents.add(eventObjects);
//
//        EventObjects eventObjects1 = new EventObjects(4, "sdfsdfsest", CalendarUtils.convertStringToDate("13-04-2018"), Color.BLUE);
//
//        mEvents.add(eventObjects1);

        Calendar mCal = (Calendar) cal.clone();
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while (dayValueInCells.size() < MAX_CALENDAR_COLUMN) {
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());
        currentDate.setText(sDate);

        mAdapter = new RecyclerAdapter(context, dayValueInCells, cal, this.eventList, eventHighlight, this);

        float scalefactor = getResources().getDisplayMetrics().density * 100;

        calendarGridView.setAdapter(mAdapter);
    }
}
