package com.example.ontap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Khai báo biến view
    EditText edtSearch;
    RecyclerView rvData;
    ListView lvData;
    FloatingActionButton btnAdd;

    // Khai báo biến dữ liệu
    MyDb db;
    Adapter AdapterList;  // Adapter listview
    TaxiListAdapter mAdapter;   // Adapter RecyclerView
    ArrayList<Taxi> TaxiList;   // Chứa danh sách Taxi
    ArrayList<Taxi> TaxiListCopy;

    int SelectedID; // Chứa ID của phần tử được chọn


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        unitUI();

        db = new MyDb(this, "NameDB", null, 1);
        // Thiết lập dữ liệu mẫu
        db.addTaxi(new Taxi(1, "29V7 19091", 120, 10000,5));
        db.addTaxi(new Taxi(2, "29E1 25341", 50, 12000,5));
        db.addTaxi(new Taxi(3, "29C1 34344", 100, 13000, 5));
        db.addTaxi(new Taxi(4, "29B1 12355", 250, 15000,5));
        db.addTaxi(new Taxi(5, "29D1 23578", 251, 10000,5));
        db.addTaxi(new Taxi(6, "29G1 32567", 120, 11000,5));
        db.addTaxi(new Taxi(7, "29E1 24398", 120, 19000,5));
        db.addTaxi(new Taxi(8, "29V5 10248", 120, 15000,5));
        db.addTaxi(new Taxi(9, "29C1 98512", 120, 17000,5));

        TaxiList = new ArrayList<>();
        // Lấy tất cả taxi
        TaxiList = db.getAllTaxi();
        //showTaxiRecyclerView(TaxiList);
        showTaxiListView(TaxiList);

        // Gắn ContextMenu vào ListView
        registerForContextMenu(lvData);
    }

    private void showTaxiListView(ArrayList<Taxi> TaxiList) {
        AdapterList = new Adapter(TaxiList, this);
        lvData.setAdapter(AdapterList);
    }

    private void showTaxiRecyclerView(ArrayList<Taxi> lst) {
        mAdapter = new TaxiListAdapter(lst);
        rvData.setLayoutManager(new LinearLayoutManager(this));
        rvData.setAdapter(mAdapter);
    }

    private void unitUI() {
        edtSearch = findViewById(R.id.edtSearch);
        //rvData = findViewById(R.id.RVData);
        lvData = findViewById(R.id.lvData);
        btnAdd = findViewById(R.id.btnAdd);
    }

    // Tạo ContextMenu và gắn menu resource
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Select Option");
    }
    //xử lý sự kiện khi chọn một item trong ContextMenu

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()) {
            case R.id.edit:
                // Xử lý khi chọn "Sửa"

                // 1. Tạo intent để mở subActivity
                Intent intent = new Intent(this, MainActivity2.class);
                // 2. Truyền dữ liệu bằng bunlde
                Bundle b = new Bundle();
                Taxi taxi_selected = TaxiList.get(SelectedID);
                b.putInt("Id", taxi_selected.getCOLUMN_ID());
                b.putString("SoXe", taxi_selected.getCOLUMN_SO_XE());
                b.putFloat("QuangDuong", taxi_selected.getCOLUMN_QUANG_DUONG());
                b.putInt("DonGia", taxi_selected.getCOLUMN_DON_GIA());
                b.putInt("GiamGia", taxi_selected.getCOLUMN_PHAN_TRAM_KM());
                // Đưa bundle vào intent
                intent.putExtras(b);

                startActivity(intent);
                break;
            case R.id.delete:
                // Xử lý khi chọn "Xóa"
                thongBao("Bạn có chắc muốn xóa không ?");
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void thongBao(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage(s);
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(MainActivity.this,String.valueOf(i),Toast.LENGTH_SHORT).show();

                db.DeleteTaxi(SelectedID);
                // Lấy tất cả taxi
                TaxiList = db.getAllTaxi();
                showTaxiListView(TaxiList);
            }
        });
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        // Hiển thị messagebox
        builder.show();
    }
}