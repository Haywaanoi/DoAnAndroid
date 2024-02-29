package model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

public class ThongTinXe implements Serializable {
    private int id;
    private String ten;
    private  byte[] image;
    private double gia;
    private int id_loai;
    private  String nhaSanXuat;
    private String mota;

    public ThongTinXe() {
    }

    public ThongTinXe(int id, String ten, byte[] image, double gia, int id_loai, String nhaSanXuat, String mota) {
        this.id = id;
        this.ten = ten;
        this.image = image;
        this.gia = gia;
        this.id_loai = id_loai;
        this.nhaSanXuat = nhaSanXuat;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getId_loai() {
        return id_loai;
    }

    public void setId_loai(int id_loai) {
        this.id_loai = id_loai;
    }

    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getFormattedPrice() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(this.gia);
    }
}
