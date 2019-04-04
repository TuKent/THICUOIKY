package com.example.spendingmanagementapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhieuChiTieuAdapter extends ArrayAdapter<ChiTieuObject>
{
    private Context mcontext;
    private int mResource;
    private List<ChiTieuObject> mList;

    public PhieuChiTieuAdapter( Context context, int resource, List<ChiTieuObject> objects)
    {
        super(context, resource, objects);
        this.mcontext = context;
        this.mResource = resource;
        this.mList = objects;
    }
    public class ViewHolder
    {
        TextView Sotien;
        TextView Loaitien;
        TextView Diadiem;
        TextView Thoigian;
        TextView GhiChu;
        Button ThemChiTieu;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder  = null;
        if ( viewHolder == null)
        {
            convertView = LayoutInflater.from(mcontext).inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.Sotien = convertView.findViewById(R.id.lv_Sotien);
            viewHolder.Loaitien = convertView.findViewById(R.id.lv_Loaichitieu);
            viewHolder.Diadiem = convertView.findViewById(R.id.lv_Diadiem);
            viewHolder.Thoigian = convertView.findViewById(R.id.lv_Thoigian);
            viewHolder.GhiChu = convertView.findViewById(R.id.lv_Ghichu);
            viewHolder.ThemChiTieu = convertView.findViewById(R.id.btn_lv_themchitieu);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        ChiTieuObject chiTieuObject = mList.get(position);
        viewHolder.Sotien.setText(String.valueOf(chiTieuObject.getSoTienChiTieu()));
        viewHolder.Loaitien.setText(String.valueOf(chiTieuObject.getLoaiChiTieu()));
        viewHolder.Diadiem.setText(String.valueOf(chiTieuObject.getDiaDiemChiTieu()));
        viewHolder.Thoigian.setText(String.valueOf(chiTieuObject.getTimeChiTieu()));
        viewHolder.GhiChu.setText(String.valueOf(chiTieuObject.getGhiChuChiTieu()));
        return convertView;
    }
}
