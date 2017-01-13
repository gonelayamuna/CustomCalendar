package com.vikashparajuli.mycalendar.caldroid;

import java.util.Date;

import android.view.View;

import com.vikashparajuli.mycalendar.R;

/**
 * CaldroidListener inform when user clicks on a valid date (not within disabled
 * dates, and valid between min/max dates)
 * <p/>
 * The method onChangeMonth is optional, user can always override this to listen
 * to month change event
 *
 * @author thomasdao
 */
public abstract class CaldroidListener {
    /**
     * Inform client user has clicked on a date
     *
     * @param date
     * @param view
     */
    private View previousView;
    public abstract void onSelectDate(Date date, View view);


    /**
     * Inform client user has long clicked on a date
     *
     * @param date
     * @param view
     */

    public void onLongClickDate(Date date, View view) {

        // Do nothing
    }



    /**
     * Inform client that calendar has changed month
     *
     * @param month
     * @param year
     */
    public void onChangeMonth(int month, int year) {
        // Do nothing
    }

    ;
    public View setSelected(View view) {
        //previousView=null;
        if (previousView != null) {
            previousView.setBackgroundResource(R.drawable.mark_selected);
        }
        previousView = view;
        view.setBackgroundResource(R.drawable.current_date);
        return view;
    }

    /**
     * Inform client that CaldroidFragment view has been created and views are
     * no longer null. Useful for customization of button and text views
     */
    public void onCaldroidViewCreated() {
        // Do nothing
    }
}
