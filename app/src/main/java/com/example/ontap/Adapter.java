package com.example.ontap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    // Nguồn dữ liệu cho Adapter
    ArrayList<Taxi> data;
    // Ngữ cảnh của ứng dụng
    Activity context;
    // Đối tượng phân tích layout
    LayoutInflater inflater;

    // Hàm tạo
    public Adapter(){}
    public Adapter(ArrayList<Taxi> data, Activity context)
    {
        this.data = data;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getCOLUMN_ID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null)
            v = inflater.inflate(R.layout.layoutadapter, null);
        // Khai báo và ánh xạ view
        TextView tvBienSo, tvQuangDuong, tvTongTien;
        tvBienSo = v.findViewById(R.id.tvBienSo);
        tvQuangDuong = v.findViewById(R.id.tvQuangDuong);
        tvTongTien = v.findViewById(R.id.tvTongTien);
        // Set dữ liệu
        tvBienSo.setText(data.get(position).getCOLUMN_SO_XE());

        float quangduong = data.get(position).getCOLUMN_QUANG_DUONG();
        // Chuyển float -> string
        tvQuangDuong.setText("Quãng đường " + String.valueOf(quangduong) + " km");

        int DonGia = data.get(position).getCOLUMN_DON_GIA();
        int GiamGia = data.get(position).getCOLUMN_PHAN_TRAM_KM();
        float TongTien = DonGia * quangduong * (100-GiamGia)/100;
        tvTongTien.setText(String.valueOf(TongTien));

        return v;
    }
}
