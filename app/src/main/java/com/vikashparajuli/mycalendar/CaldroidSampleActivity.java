package com.vikashparajuli.mycalendar;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vikashparajuli.mycalendar.caldroid.CaldroidFragment;
import com.vikashparajuli.mycalendar.caldroid.CaldroidListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class CaldroidSampleActivity extends AppCompatActivity {
    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    Date selectHalfMoon, selectEvent;
    public ArrayList<String> items;
    ArrayList<String> halfmoon_day, fullmoon_day;
    Calendar itemmonth;
    public Handler handler;
    int prevView;
/*
    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();

        // Min date is last 7 days
        cal.add(Calendar.DATE, -7);
        Date blueDate = cal.getTime();

        // Max date is next 7 days
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        Date greenDate = cal.getTime();

        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.blue));
            ColorDrawable green = new ColorDrawable(Color.GREEN);
            caldroidFragment.setBackgroundDrawableForDate(blue, blueDate);
            caldroidFragment.setBackgroundDrawableForDate(green, greenDate);
            caldroidFragment.setTextColorForDate(R.color.white, blueDate);
            caldroidFragment.setTextColorForDate(R.color.white, greenDate);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");


        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        //caldroidFragment = new CaldroidFragment();

        // //////////////////////////////////////////////////////////////////////
        // **** This is to show customized fragment. If you want customized
        // version, uncomment below line ****
        caldroidFragment = new CaldroidSampleCustomFragment();
        CalendarDetail fragobj = new CalendarDetail();
        // Calendar cal = Calendar.getInstance();


        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);


            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            Date selectFullMoon = cal.getTime();
            caldroidFragment.setSelectedFullMoonDates(selectFullMoon);

            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -4);
            selectHalfMoon = cal.getTime();
            caldroidFragment.setSelectedHalfMoonDates(selectHalfMoon);
            cal.add(Calendar.DATE, -2);
            selectHalfMoon = cal.getTime();
            caldroidFragment.setSelectedHalfMoonDates(selectHalfMoon);

            cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 3);
            selectEvent = cal.getTime();
            caldroidFragment.setSelectedEvents(selectEvent);

            cal.add(Calendar.DATE, 2);
            selectEvent = cal.getTime();
            caldroidFragment.setSelectedEvents(selectEvent);

            cal.add(Calendar.DATE, 2);
            selectHalfMoon = cal.getTime();
            caldroidFragment.setSelectedEvents(selectHalfMoon);
            cal.add(Calendar.DATE, -4);
            selectEvent = cal.getTime();
            caldroidFragment.setSelectedEvents(selectEvent);
            cal.add(Calendar.DATE, -2);
            selectEvent = cal.getTime();
            caldroidFragment.setSelectedEvents(selectEvent);


            //  caldroidFragment.setSixWeeksInCalendar(false);


            caldroidFragment.refreshView();

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
//            args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

            caldroidFragment.setArguments(args);
        }

        //setCustomResourceForDates();


        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {


            @Override
            public void onSelectDate(Date date, View view) {

                Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();

            /* if (date==null){
               //  view.setBackgroundResource(R.drawable.mark_selected);
                 caldroidFragment.setSelectedDate(date);
                 caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.mark_selected),date);
             }
                else
             {
                 caldroidFragment.clearSelectedDate(date);
                 caldroidFragment.setSelectedDate(date);
                 caldroidFragment.setBackgroundDrawableForDate(getResources().getDrawable(R.drawable.mark_selected),date);
             }*/
                String selectedGridDate = formatter.format(date);
                Log.e("grid val..", "grid val.." + selectedGridDate);
                String[] separatedTime = selectedGridDate.split("\\s+");

                CalendarDetail fragobj = new CalendarDetail();
                Bundle bundle = new Bundle();
                bundle.putString("edttext", separatedTime[0]);
                FragmentManager fragmentManager = getFragmentManager();
                fragobj.setArguments(bundle);
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // CalendarDetail hello = new CalendarDetail();
                fragmentTransaction.replace(R.id.container, fragobj);
                fragmentTransaction.commit();

            }


            @Override
            public void onChangeMonth(int month, int year) {
                /*String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                /*Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onCaldroidViewCreated() {
                /*if (caldroidFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "Caldroid view is created", Toast.LENGTH_SHORT)
                            .show();
                }*/
            }

        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

        // final TextView textView = (TextView) findViewById(R.id.textview);

        //  final Button customizeButton = (Button) findViewById(R.id.customize_button);


      /*  // Customize the calendar
        customizeButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (undo) {
                    customizeButton.setText(getString(R.string.customize));
                    textView.setText("");

                    // Reset calendar
                    caldroidFragment.clearDisableDates();
                    caldroidFragment.clearSelectedDates();
                    caldroidFragment.clearFullMoonDates();
                    caldroidFragment.clearHalfMoonDates();
                    caldroidFragment.clearEventDates();
                    caldroidFragment.setMinDate(null);
                    caldroidFragment.setMaxDate(null);
                    caldroidFragment.setShowNavigationArrows(true);
                    caldroidFragment.setEnableSwipe(true);
                    caldroidFragment.refreshView();
                    undo = false;
                    return;
                }

                // Else
                undo = true;
                customizeButton.setText(getString(R.string.undo));
                Calendar cal = Calendar.getInstance();

                // Min date is last 7 days
                cal.add(Calendar.DATE, -7);
                Date minDate = cal.getTime();

                // Max date is next 7 days
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 14);
                Date maxDate = cal.getTime();

                // Set selected dates
                // From Date
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 2);
                Date fromDate = cal.getTime();

                // To Date
                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 3);
                Date toDate = cal.getTime();

                // Set disabled dates
                ArrayList<Date> disabledDates = new ArrayList<Date>();
                for (int i = 5; i < 8; i++) {
                    cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, i);
                    disabledDates.add(cal.getTime());
                }

                // Customize
                caldroidFragment.setMinDate(minDate);
                caldroidFragment.setMaxDate(maxDate);
                caldroidFragment.setDisableDates(disabledDates);
                caldroidFragment.setSelectedDates(fromDate, toDate);
                caldroidFragment.setShowNavigationArrows(true);
                caldroidFragment.setEnableSwipe(true);

                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 1);
                Date selectFullMoon = cal.getTime();
                fullmoon_day=new ArrayList<String>();
                fullmoon_day.add("2016-12-27");
              //  caldroidFragment.setSelectedFullMoonDates(selectFullMoon);
                caldroidFragment.setFullmoon(fullmoon_day);

                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 2);
                Date selectHalfMoon = cal.getTime();
                caldroidFragment.setSelectedHalfMoonDates(selectHalfMoon);

                cal = Calendar.getInstance();
                cal.add(Calendar.DATE, 3);
                Date selectEvent = cal.getTime();
                caldroidFragment.setSelectedEvents(selectEvent);

                caldroidFragment.refreshView();

                // Move to date
                // cal = Calendar.getInstance();
                // cal.add(Calendar.MONTH, 12);
                // caldroidFragment.moveToDate(cal.getTime());

                String text = "Today: " + formatter.format(new Date()) + "\n";
                text += "Min Date: " + formatter.format(minDate) + "\n";
                text += "Max Date: " + formatter.format(maxDate) + "\n";
                text += "Select From Date: " + formatter.format(fromDate)
                        + "\n";
                text += "Select To Date: " + formatter.format(toDate) + "\n";
                for (Date date : disabledDates) {
                    text += "Disabled Date: " + formatter.format(date) + "\n";
                }

                textView.setText(text);
            }
        });*/
    }

    /**
     * Save current states of the Caldroid here
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (caldroidFragment != null) {
            caldroidFragment.saveStatesToKey(outState, "CALDROID_SAVED_STATE");
        }
    }


    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();
            fullmoon_day.clear();
            halfmoon_day.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            for (int i = 0; i < 7; i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items.add("2016-12-12");
                items.add("2016-12-07");
                items.add("2016-12-15");
                items.add("2016-12-20");
                items.add("2016-11-30");
                items.add("2016-11-28");
            }
            itemvalue = df.format(itemmonth.getTime());
            itemmonth.add(GregorianCalendar.DATE, 1);
            halfmoon_day.add("2016-12-12");
            fullmoon_day.add("2016-12-27");

        }
    };
}
