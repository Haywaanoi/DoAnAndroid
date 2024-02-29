package Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import model.LoaiXeDap;
import model.ThongTinXe;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "XeDap.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME1 = "LoaiXeDap";
    private static final String COLUMN_idLoai = "id";
    private static final String COLUMN_tenLoai = "tenLoai";
    private static final String TABLE_NAME2 = "ThongTinXe";
    private static final String COLUMN_idXe = "id";
    private static final String COLUMN_tenXe = "ten";
    private static final String COLUMN_idLoaiXeDap = "id_loai";
    private static final String COLUMN_image = "image";
    private static final String COLUMN_gia = "gia";
    private static final String COLUMN_NhaSX = "nhaSanXuat";
    private static final String COLUMN_mota = "mota";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<ThongTinXe> getAllXeDap(){
        List<ThongTinXe> xeDap = new ArrayList<>();
        String[] projection = {
                DBHelper.COLUMN_idXe,
                DBHelper.COLUMN_tenXe,
                DBHelper.COLUMN_idLoaiXeDap,
                DBHelper.COLUMN_image,
                DBHelper.COLUMN_gia,
                DBHelper.COLUMN_NhaSX,
                DBHelper.COLUMN_mota
        };
        Cursor cursor = getReadableDatabase().query(
                DBHelper.TABLE_NAME2,projection,null,null,null,null,null
        );
        while (cursor.moveToNext()){
            ThongTinXe TTxe =new ThongTinXe(
                                cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_idXe)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_tenXe)),
                    cursor.getBlob(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_image)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_gia)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_idLoaiXeDap)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NhaSX)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_mota))
                        );
            xeDap.add(TTxe);
        }
        cursor.close();
        return xeDap;
    }

    public List<LoaiXeDap> getAllLoaiXeDap()
    {
        List<LoaiXeDap> loaiXe = new ArrayList<>();
        String[] projection = {
                DBHelper.COLUMN_idLoai,
                DBHelper.COLUMN_tenLoai
        };
        Cursor cursor = getReadableDatabase().query(
                DBHelper.TABLE_NAME1,projection,null,null,null,null,null
        );
        while (cursor.moveToNext()){

            LoaiXeDap loaixeDap=new LoaiXeDap(
                    cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_idLoai)),
                    cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_tenLoai))
            );
            loaiXe.add(loaixeDap);
        }
        cursor.close();
        return loaiXe;
    }

    public void addLoaiXe(LoaiXeDap loaiXeDap)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_tenLoai, loaiXeDap.getTenLoai());
        long them = getWritableDatabase().insert(DBHelper.TABLE_NAME1,null,values);
    }

    public void updateLoaiXe(LoaiXeDap loaiXeDap)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_tenLoai, loaiXeDap.getTenLoai());
        String selection = DBHelper.COLUMN_idLoai+ " = ?";
        String [] selectionArgs = {loaiXeDap.getId()+""};
        int updatedRows = getWritableDatabase().update(
                DBHelper.TABLE_NAME1,values,selection,selectionArgs
        );
    }

    public void deleteLoaiXe(int id)
    {
        String selection = DBHelper.COLUMN_idLoai+ " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int deletedRows = getWritableDatabase().delete(
                DBHelper.TABLE_NAME1,selection,selectionArgs
        );
    }

    public void addXeDap(ThongTinXe thongTinXe)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_tenXe, thongTinXe.getTen());
        values.put(DBHelper.COLUMN_idLoaiXeDap, thongTinXe.getId_loai());
        values.put(DBHelper.COLUMN_image, thongTinXe.getImage());
        values.put(DBHelper.COLUMN_gia, thongTinXe.getGia());
        values.put(DBHelper.COLUMN_NhaSX, thongTinXe.getNhaSanXuat());
        values.put(DBHelper.COLUMN_mota, thongTinXe.getMota());
        long them = getWritableDatabase().insert(DBHelper.TABLE_NAME2,null,values);
    }

    public void updateXeDap(ThongTinXe thongTinXe)
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_tenXe, thongTinXe.getTen());
        values.put(DBHelper.COLUMN_idLoaiXeDap, thongTinXe.getId_loai());
        values.put(DBHelper.COLUMN_image, thongTinXe.getImage());
        values.put(DBHelper.COLUMN_gia, thongTinXe.getGia());
        values.put(DBHelper.COLUMN_NhaSX, thongTinXe.getNhaSanXuat());
        values.put(DBHelper.COLUMN_mota, thongTinXe.getMota());
        String selection = DBHelper.COLUMN_idXe+" = ?";
        String[] selectionArgs = {thongTinXe.getId()+""};
        int updatedRows = getWritableDatabase().update(
                DBHelper.TABLE_NAME2,values,selection,selectionArgs
        );
    }

    public void deleteXeDap(int ma)
    {
        String selection = DBHelper.COLUMN_idXe+" = ?";
        String[] selectionArgs = {String.valueOf(ma)};
        int deletedRows = getWritableDatabase().delete(
                DBHelper.TABLE_NAME2,selection,selectionArgs
        );
    }

    public boolean hasXeDap(int idLoai)
    {
        String[] projection = {COLUMN_idXe};
        String selection = COLUMN_idLoai + " = ?";
        String[] selectionArgs = {String.valueOf(idLoai)};

        Cursor cursor = getReadableDatabase().query(
                TABLE_NAME2,projection,selection,selectionArgs,null,null,null
        );
        boolean hasXeDap = cursor.getCount()>0;
        cursor.close();
        System.out.println("Ton tai xe dap" + hasXeDap);
        return hasXeDap;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
