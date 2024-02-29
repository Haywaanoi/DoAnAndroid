package vn.edu.stu.cuoikymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Dao.DBHelper;
import adapter.AdapterCustom;
import model.LoaiXeDap;
import model.ThongTinXe;
import util.DBConfigUtil;

public class DanhSachXe extends AppCompatActivity {
    DBHelper dbHelper;
    ListView lvXeDap;
    AdapterCustom adapter;
    Button btnThem, btnTrove;
    ArrayList<ThongTinXe> dsXeDap = new ArrayList<>();
    ArrayList<LoaiXeDap> dsLoaiXe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_xe);
        androidx.appcompat.widget.Toolbar tb = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        tb.setBackgroundColor(Color.rgb(103,79,163));
        setSupportActionBar(tb);
        DBConfigUtil.copyDatabaseFromAssets(DanhSachXe.this);
        addControls();
        dbHelper = new DBHelper(DanhSachXe.this);
        addEvents();
        hienthiDanhSachXe();
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_chon, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.mnuAbout){

        }
        else if (item.getItemId()==R.id.mnuExit){

        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachXe.this,
                        activity_FormXeDap.class);
                startActivity(intent);
                finish();
            }
        });

        btnTrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachXe.this,
                        QuanLyActitivy.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void hienthiDanhSachXe() {
        dsXeDap = (ArrayList<ThongTinXe>) dbHelper.getAllXeDap();
        dsLoaiXe = (ArrayList<LoaiXeDap>)dbHelper.getAllLoaiXeDap();
        adapter = new AdapterCustom(
                DanhSachXe.this,
                R.layout.list_xedap_item,
                dsXeDap, dbHelper, dsLoaiXe
        );
        lvXeDap.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void addControls() {
        lvXeDap = findViewById(R.id.lvXeDap);
        btnThem = findViewById(R.id.btnThem);
        btnTrove = findViewById(R.id.btnTroVe);

    }
}