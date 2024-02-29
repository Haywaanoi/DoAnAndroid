package adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Dao.DBHelper;
import model.LoaiXeDap;
import model.ThongTinXe;
import vn.edu.stu.cuoikymobile.R;
import vn.edu.stu.cuoikymobile.activity_FormXeDap;

public class AdapterCustom extends ArrayAdapter<ThongTinXe> {
    Activity context;
    int resource;
    List<ThongTinXe> xeDap;
    DBHelper dbHelper;

    ArrayList<LoaiXeDap> dsLoaiXe;
    public AdapterCustom(Activity context, int resource, List<ThongTinXe> laptops, DBHelper dbHelper, ArrayList<LoaiXeDap> dsNhaCungCap) {
        super(context, resource, laptops);
        this.context = context;
        this.resource =  resource;
        this.xeDap = laptops;
        this.dbHelper = dbHelper;
        this.dsLoaiXe = dsNhaCungCap;
    }

    public String getTenLoaiXe(int id, ArrayList<LoaiXeDap> loai){
        for(LoaiXeDap dsLoai : loai)
        {
            if(dsLoai.getId()== id)
            {
                return dsLoai.getTenLoai();
            }
        }
        return null;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        ImageView imageXe=(ImageView)item.findViewById(R.id.imageXe);
        TextView tvIdXe = item.findViewById(R.id.tvIdXe);
        TextView tvTenXe = item.findViewById(R.id.tvTenXe);
        TextView tvLoaiXe = item.findViewById(R.id.tvLoaiXe);
        TextView tvGia = item.findViewById(R.id.tvGia);
        final Button btnSua = item.findViewById(R.id.btnSua);
        final Button btnXoa = item.findViewById(R.id.btnXoa);
        final ThongTinXe n_xeDap = xeDap.get(position);
        tvIdXe.setText(n_xeDap.getId()+"");
        tvTenXe.setText(n_xeDap.getTen());
        tvLoaiXe.setText(getTenLoaiXe(n_xeDap.getId_loai(), dsLoaiXe));
        tvGia.setText(n_xeDap.getFormattedPrice());
        Bitmap bmImageXe= BitmapFactory.decodeByteArray(n_xeDap.getImage(),0,n_xeDap.getImage().length);
        imageXe.setImageBitmap(bmImageXe);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(context, activity_FormXeDap.class);
                ThongTinXe xe= xeDap.get(position);
                Intent.putExtra("dsLoai",dsLoaiXe);
                Intent.putExtra("xeDap", xe);
                context.startActivityForResult(Intent,789);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc muốn xóa ?");

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteXeDap(xeDap.get(position).getId());
                        xeDap.remove(position);
                        notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return item;
    }
}
