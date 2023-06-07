package com.example.ontap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaxiListAdapter extends RecyclerView.Adapter<TaxiListAdapter.ViewHolder> {
    private List<Taxi> mTaxiList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvBienSo, tvQuangDuong, tvTongTien;
        public ViewHolder(View itemView) {
            super(itemView);
            tvBienSo = itemView.findViewById(R.id.tvBienSo);
            tvQuangDuong = itemView.findViewById(R.id.tvQuangDuong);
            tvTongTien = itemView.findViewById(R.id.tvTongTien);
        }
    }

    public TaxiListAdapter(List<Taxi> taxiList) {
        mTaxiList = taxiList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutadapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Taxi taxi = mTaxiList.get(position);

        // Set dữ liệu
        holder.tvBienSo.setText(taxi.getCOLUMN_SO_XE());

        float quangduong = taxi.getCOLUMN_QUANG_DUONG();
        // Chuyển float -> string
        holder.tvQuangDuong.setText(String.valueOf(quangduong));

        int DonGia = taxi.getCOLUMN_DON_GIA();
        int GiamGia = taxi.getCOLUMN_PHAN_TRAM_KM();
        float TongTien = DonGia * quangduong * (100-GiamGia)/100;
        holder.tvTongTien.setText(String.valueOf(TongTien));
    }

    @Override
    public int getItemCount() {
        return mTaxiList.size();
    }
}
