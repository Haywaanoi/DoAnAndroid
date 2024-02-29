package vn.edu.stu.cuoikymobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import Dao.DBHelper;
import model.LoaiXeDap;
import model.ThongTinXe;
import util.DBConfigUtil;

public class activity_FormXeDap extends AppCompatActivity {
    static final int REQUEST_CHOOSE_PHOTO=321;
    ImageView imageXe;
    Spinner spinner;
    EditText etTen,etGia,etNSX,etMota;
    DBHelper dbHelper;
    ArrayList<LoaiXeDap> dsLoai = new ArrayList<>();
    ArrayAdapter<String> spinnerLoai;
    Button btnLuu, btnTroVe, btnChon;
    ThongTinXe thongTinXe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_xedap);
        androidx.appcompat.widget.Toolbar tb = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        tb.setBackgroundColor(Color.rgb(103,79,163));
        setSupportActionBar(tb);
        Intent intent = getIntent();
        thongTinXe = (ThongTinXe) intent.getSerializableExtra("xeDap");
        dbHelper = new DBHelper(activity_FormXeDap.this);
        DBConfigUtil.copyDatabaseFromAssets(activity_FormXeDap.this);
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

    private void addControls() {
        this.spinner =(Spinner)findViewById(R.id.spinner);
        etTen = findViewById(R.id.etTen);
        etGia = findViewById(R.id.etGia);
        etNSX = findViewById(R.id.etNSX);
        etMota = findViewById(R.id.etMota);
        btnChon= findViewById(R.id.btnChon);
        btnLuu = findViewById(R.id.btnLuu);
        btnTroVe = findViewById(R.id.btnTroVe);
        imageXe =(ImageView)findViewById(R.id.imageXe);
        if(thongTinXe != null)
        {
            etTen.setText(thongTinXe.getTen());
            etGia.setText(thongTinXe.getGia()+"");
            etNSX.setText(thongTinXe.getNhaSanXuat());
            etMota.setText(thongTinXe.getMota());
            Bitmap bmImageXe = BitmapFactory.decodeByteArray(thongTinXe.getImage(),0,thongTinXe.getImage().length);
            imageXe.setImageBitmap(bmImageXe);
            selectedAdapterLoai();

        }else
        {

            hienthiAdapterLoai();
        }
    }
    private void selectedAdapterLoai(){
        dsLoai=(ArrayList<LoaiXeDap>) dbHelper.getAllLoaiXeDap();
        ArrayList<String> dsTenLoai = new ArrayList<>();
        int pos = -1;
        for(int i=0;i<dsLoai.size();i++)
        {
            dsTenLoai.add(dsLoai.get(i).getTenLoai());
            if(dsLoai.get(i).getId()==thongTinXe.getId_loai())
            {
                pos = i;
            }
        }
        spinnerLoai = new ArrayAdapter(activity_FormXeDap.this, android.R.layout.simple_spinner_dropdown_item, dsTenLoai);
        spinnerLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerLoai);
        spinner.setSelection(pos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                thongTinXe.setId_loai(dsLoai.get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void hienthiAdapterLoai(){
        dsLoai=(ArrayList<LoaiXeDap>) dbHelper.getAllLoaiXeDap();
        ArrayList<String> ds = new ArrayList<>();
        for(int i=0;i<dsLoai.size();i++)
            ds.add(dsLoai.get(i).getTenLoai());
        spinnerLoai = new ArrayAdapter(activity_FormXeDap.this, android.R.layout.simple_spinner_dropdown_item, ds);
        spinnerLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerLoai);
    }
    private void choosePhoto(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQUEST_CHOOSE_PHOTO);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if (requestCode==REQUEST_CHOOSE_PHOTO){
                try {
                    Uri imageUri=data.getData();
                    InputStream inputStream=getContentResolver().openInputStream(imageUri);
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    imageXe.setImageBitmap(bitmap);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private byte[] getBytesFromImageView(ImageView imageLaptop) {
        BitmapDrawable drawable=(BitmapDrawable) imageLaptop.getDrawable();
        Bitmap bitmap=drawable.getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }
    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_FormXeDap.this,
                        DanhSachXe.class);
                startActivity(intent);
                finish();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thongTinXe != null)
                {
                    thongTinXe.setTen(etTen.getText().toString());
                    thongTinXe.setGia(Double.parseDouble(etGia.getText().toString()));
                    thongTinXe.setNhaSanXuat(etNSX.getText().toString());
                    thongTinXe.setMota(etMota.getText().toString());
                    thongTinXe.setImage(getBytesFromImageView(imageXe));
                    dbHelper.updateXeDap(thongTinXe);
                }else
                {
                    thongTinXe = new ThongTinXe();
                    thongTinXe.setTen(etTen.getText().toString());
                    thongTinXe.setGia(Double.parseDouble(etGia.getText().toString()));
                    thongTinXe.setNhaSanXuat(etNSX.getText().toString());
                    thongTinXe.setMota(etMota.getText().toString());
                    thongTinXe.setImage(getBytesFromImageView(imageXe));
                    dbHelper.addXeDap(thongTinXe);
                }
                Intent intent = new Intent(activity_FormXeDap.this,DanhSachXe.class);
                startActivity(intent);
                finish();
            }
        });
    }
}