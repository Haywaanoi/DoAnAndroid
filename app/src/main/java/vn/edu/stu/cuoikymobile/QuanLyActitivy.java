package vn.edu.stu.cuoikymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class QuanLyActitivy extends AppCompatActivity {
    Button btnLoaiXe, btnXeDap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_actitivy);
        androidx.appcompat.widget.Toolbar tb = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        tb.setBackgroundColor(Color.rgb(103,79,163));
        setSupportActionBar(tb);
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
        btnLoaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        QuanLyActitivy.this,
                        DanhSachLoaiXeDap.class
                );
                startActivity(intent);
                finish();
            }
        });
        btnXeDap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        QuanLyActitivy.this,
                        DanhSachXe.class
                );
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControls() {
        btnLoaiXe = findViewById(R.id.btnLoaiXe);
        btnXeDap = findViewById(R.id.btnXeDap);
    }
}