package com.example.spendingmanagementapplication;


import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhieuChiTieuFragment extends Fragment {

    ListView listView;
    static List<ChiTieuObject> ModeList = new ArrayList<>();
    static PhieuChiTieuAdapter adapter;
    static SQLiteChiTieu sqLiteChiTieu;
    Button ThemChiTieu;
    Button ThemSoDu;
    List<ChiTieuObject> ModeChiTieu = new ArrayList<>();
    public PhieuChiTieuFragment() {
        // Required empty public constructor
    }
    @Override
    public void onResume()
    {
        super.onResume();
        ModeChiTieu.clear();
        ModeList.clear();
        ModeList.addAll(sqLiteChiTieu.getAll());
        Date date = new Date();
        try
        {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().toString());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        for (int i = 0; i < ModeList.size();i++)
        {
            Date getDay = new Date();
            try
            {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(ModeList.get(i).getTimeChiTieu());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (getDay.getMonth() >= date.getMonth())
            {
                ModeChiTieu.add(ModeList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_phieu_chi_tieu, container, false);
        listView = view.findViewById(R.id.lv_phieuchitieu);
        sqLiteChiTieu = new SQLiteChiTieu(getContext());
        ModeList = sqLiteChiTieu.getAll();
        ThemChiTieu = view.findViewById(R.id.btn_lv_themchitieu);
        ThemSoDu = view.findViewById(R.id.btn_lv_sodu);
        ThemChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ThemChiTieuActivity.class);
                startActivity(i);
            }
        });
        ThemSoDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getContext(), SoDuActivity.class);
               startActivity(i);
            }
        });

        if(ModeList.isEmpty())
        {
            //Add();
            ModeList = sqLiteChiTieu.getAll();
        }
        adapter = new PhieuChiTieuAdapter(getContext(),R.layout.custom_listview_phieuchitieu, ModeChiTieu);
        listView.setAdapter(adapter);
        return view;
    }




    private void Add()
    {
        //sqLiteChiTieu.Insert(1,10000,"Fucking ","Xàm lồn đó ","Viet Nam","đéo nhớ hic ");
        //sqLiteChiTieu.Insert(2,1000000,"Xàm lồn đó ","Viet Nam","đéo nhớ hic ","No No No");

    }

}
