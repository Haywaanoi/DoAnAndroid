package vn.edu.stu.cuoikymobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button btnOK;
    EditText taikhoan, matkhau;
    androidx.appcompat.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
       taikhoan = findViewById(R.id.taikhoan);
      matkhau = findViewById(R.id.matkhau);
     btnOK = findViewById(R.id.dangnhap);
    }

    private void addEvents() {
   btnOK.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           xulydangnhap();
       }
   });

    }


    private void xulydangnhap() {
        String username = String.valueOf(taikhoan.getText());
        String pass = String.valueOf(matkhau.getText());

        // So sánh tên người dùng và mật khẩu bằng phương thức equals()
        if (username.equals("hoangan") && pass.equals("123456")) {
            Intent intent = new Intent(MainActivity.this, QuanLyActitivy.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        }
    }
}