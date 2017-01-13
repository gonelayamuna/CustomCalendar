package com.vikashparajuli.mycalendar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by EliteBook on 12/28/2016.
 */
public class CalendarDetail extends Fragment {
    ArrayList<ListviewContactItem> listContact;
    ListviewContactItem contact_item;
    String strtext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
      //  super.onViewCreated(view, savedInstanceState);
        TextView date_selected= (TextView) view.findViewById(R.id.date_txt);
        ListView calendar_list= (ListView) view.findViewById(R.id.cal_detail_list);

        Bundle bundle = this.getArguments();
        strtext = bundle.getString("edttext");
        date_selected.setText(strtext);

        contact_item = new ListviewContactItem();
        listContact=new ArrayList<>();
        contact_item.setDate_title("Thitti");
        contact_item.setDate_detai("Pournami upto 5am");
        listContact.add(contact_item);
        contact_item = new ListviewContactItem();
        contact_item.setDate_title("Star");
        contact_item.setDate_detai("Avittam");
        listContact.add(contact_item);
        contact_item = new ListviewContactItem();
        contact_item.setDate_title("Malyalam");
        contact_item.setDate_detai("Chingam 2nd");
        listContact.add(contact_item);

        contact_item.setDate_title("Thitti");
        contact_item.setDate_detai("Pournami upto 5am");
        listContact.add(contact_item);
        contact_item = new ListviewContactItem();
        contact_item.setDate_title("Star");
        contact_item.setDate_detai("Avittam");
        listContact.add(contact_item);
        contact_item = new ListviewContactItem();
        contact_item.setDate_title("Malyalam");
        contact_item.setDate_detai("Chingam 2nd");
        listContact.add(contact_item);

        calendar_list.setAdapter(new ListviewContactAdapter(getActivity(), listContact));
    }



}
