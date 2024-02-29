package vn.edu.stu.cuoikymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Dao.DBHelper;
import model.LoaiXeDap;
import model.ThongTinXe;
import util.DBConfigUtil;

public class DanhSachLoaiXeDap extends AppCompatActivity {

    DBHelper dbHelper;
    List<LoaiXeDap> loaiXeDap = new ArrayList<LoaiXeDap>();
    ArrayList<ThongTinXe> thongTinXe = new ArrayList<>();
    ListView dsLoaiXe;

    String DB_NAME = "XeDap.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    ArrayAdapter<LoaiXeDap> adapter;

    Button btnThem, btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_xe_dap);
        androidx.appcompat.widget.Toolbar tb = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        tb.setBackgroundColor(Color.rgb(103,79,163));
        setSupportActionBar(tb);
        DBConfigUtil.copyDatabaseFromAssets(DanhSachLoaiXeDap.this);
        dbHelper = new DBHelper(DanhSachLoaiXeDap.this);
        addControls();
        getDataLoaiXe();
        addEvents();
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
        dsLoaiXe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DanhSachLoaiXeDap.this, activity_FormLoaiXe.class);
                LoaiXeDap loai= loaiXeDap.get(position);
                intent.putExtra("loaixedap", loai);
                startActivity(intent);
            }
        });
        dsLoaiXe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                xuLyAlert(position);
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachLoaiXeDap.this,
                        activity_FormLoaiXe.class);
                startActivity(intent);
                finish();
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachLoaiXeDap.this, QuanLyActitivy.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void xulyXoa(int position) {
        LoaiXeDap loai = adapter.getItem(position);
        if (dbHelper.hasXeDap(loai.getId())) {
            showDeleteConfirmationDialog(position);
        } else {
            dbHelper.deleteLoaiXe(loai.getId());
            getDataLoaiXe();
        }

    }

    private void xuLyAlert(int index) {
        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setMessage("Ban co muon xoa");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                xulyXoa(index);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    private void showDeleteConfirmationDialog(int index) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setMessage("Không xóa được");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        android.app.AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void getDataLoaiXe() {
        loaiXeDap =(ArrayList<LoaiXeDap>)dbHelper.getAllLoaiXeDap();
        adapter=new ArrayAdapter<>(DanhSachLoaiXeDap.this,
                android.R.layout.simple_list_item_1,
                loaiXeDap);
        dsLoaiXe.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    private void addControls() {
        dsLoaiXe = findViewById(R.id.dsLoaiXe);
        btnThem = findViewById(R.id.btnThem);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getDataLoaiXe();
    }

}