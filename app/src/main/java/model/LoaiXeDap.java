package model;

import java.io.Serializable;

public class LoaiXeDap implements Serializable {
    private int id;
    private String tenLoai;

    public LoaiXeDap() {
    }

    public LoaiXeDap(int id, String tenLoai) {
        this.id = id;
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return  id + " Ten Loai : " +tenLoai  ;
    }
}
