package vn.edu.stu.cuoikymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Dao.DBHelper;
import model.LoaiXeDap;
import util.DBConfigUtil;

public class activity_FormLoaiXe extends AppCompatActivity {
    Button btnLuu;
    EditText etLoai;
    LoaiXeDap loaiXeDap;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_loai_xe);
        androidx.appcompat.widget.Toolbar tb = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        tb.setBackgroundColor(Color.rgb(103,79,163));
        setSupportActionBar(tb);
        Intent intent = getIntent();
        loaiXeDap = (LoaiXeDap) intent.getSerializableExtra("loaixedap");
        dbHelper = new DBHelper(activity_FormLoaiXe.this);
        DBConfigUtil.copyDatabaseFromAssets(activity_FormLoaiXe.this);
        addControls();
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
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaiXeDap != null){
                    loaiXeDap.setTenLoai(etLoai.getText().toString());
                    dbHelper.updateLoaiXe(loaiXeDap);
                }
                else {
                    loaiXeDap = new LoaiXeDap();
                    loaiXeDap.setTenLoai(etLoai.getText().toString());
                    dbHelper.addLoaiXe(loaiXeDap);
                }
                Intent intent = new Intent(activity_FormLoaiXe.this,
                        DanhSachLoaiXeDap.class);
                startActivity(intent);
                finish();
            }

        });
    }

    private void addControls() {
        btnLuu = findViewById(R.id.btnLuu);
        etLoai = findViewById(R.id.etTen);
        if(loaiXeDap != null)
        {
            etLoai.setText(loaiXeDap.getTenLoai());
        }
    }
}