package com.example.spendingmanagementapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.List;

public class ChiTieuAdapter extends ArrayAdapter<ChiTieuObject> {
    private Context mcontext;
    private int mResource;
    private List<ChiTieuObject> mList;
    public ChiTieuAdapter(Context context, int resource, List<ChiTieuObject> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mResource = resource;
        this.mList = objects;
    }

    public class ViewHolder
    {
        EditText Sotienchitieu;
        EditText Loaichitieu;
        EditText Diadiemchitieu;
        EditText Thoigianchitieu;
        EditText Ghichuchitieu;
    }



    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (viewHolder == null )
        {
            convertView = LayoutInflater.from(mcontext).inflate(mResource, parent,false);
            viewHolder.Sotienchitieu = convertView.findViewById(R.id.lv_Sotien);
            viewHolder.Loaichitieu = convertView.findViewById(R.id.lv_Loaichitieu);
            viewHolder.Diadiemchitieu = convertView.findViewById(R.id.lv_Diadiem);
            viewHolder.Thoigianchitieu = convertView.findViewById(R.id.lv_Thoigian);
            viewHolder.Ghichuchitieu = convertView.findViewById(R.id.lv_Ghichu);
        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final ChiTieuObject Object = mList.get(position);
        viewHolder.Sotienchitieu.setText(String.valueOf(Object.getSoTienChiTieu()));
        viewHolder.Loaichitieu.setText(String.valueOf(Object.getLoaiChiTieu()));
        viewHolder.Diadiemchitieu.setText(String.valueOf(Object.getDiaDiemChiTieu()));
        viewHolder.Thoigianchitieu.setText(String.valueOf(Object.getTimeChiTieu()));
        viewHolder.Ghichuchitieu.setText(String.valueOf(Object.getGhiChuChiTieu()));
        return convertView;
    }
}
