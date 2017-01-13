package com.vikashparajuli.mycalendar;

import com.vikashparajuli.mycalendar.caldroid.CaldroidFragment;
import com.vikashparajuli.mycalendar.caldroid.CaldroidGridAdapter;

public class CaldroidSampleCustomFragment extends CaldroidFragment {

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub
        return new CaldroidSampleCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }

}
