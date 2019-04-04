package com.example.spendingmanagementapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
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
public class LichSuFragment extends Fragment {
    ListView listView;
    //List<ChiTieuObject> ModeList = new ArrayList<>();
    static PhieuChiTieuAdapter adapter;
    static SQLiteChiTieu sqLiteChiTieu;
    Button ThemChiTieu;
    Button ThemSoDu;
    List<ChiTieuObject> ModeLichSu = new ArrayList<>();

    public LichSuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        ModeLichSu.clear();
        PhieuChiTieuFragment.ModeList.clear();
        PhieuChiTieuFragment.ModeList.addAll(sqLiteChiTieu.getAll());
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < PhieuChiTieuFragment.ModeList.size(); i++) {
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(PhieuChiTieuFragment.ModeList.get(i).getTimeChiTieu());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (getDay.getMonth() < date.getMonth()) {
                ModeLichSu.add(PhieuChiTieuFragment.ModeList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_chi_tieu, container, false);
        listView = view.findViewById(R.id.lv_phieuchitieu);
        sqLiteChiTieu = new SQLiteChiTieu(getContext());
        PhieuChiTieuFragment.ModeList = sqLiteChiTieu.getAll();
        ThemChiTieu = view.findViewById(R.id.btn_lv_themchitieu);
        ThemChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ThemChiTieuActivity.class);
                startActivity(i);
            }
        });

        if (PhieuChiTieuFragment.ModeList.isEmpty()) {
            //Add();
            PhieuChiTieuFragment.ModeList = sqLiteChiTieu.getAll();
        }
        adapter = new PhieuChiTieuAdapter(getContext(), R.layout.custom_listview_phieuchitieu, ModeLichSu);
        listView.setAdapter(adapter);
        return view;
    }

}
