package com.example.parkuy.Model;

public class Resi1 {
    private int id, totalHarga ;
    private String namaParkir, alamatParkir;
    private String waktu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String getNamaParkir() {
        return namaParkir;
    }

    public void setNamaParkir(String namaParkir) {
        this.namaParkir = namaParkir;
    }

    public String getAlamatParkir() {
        return alamatParkir;
    }

    public void setAlamatParkir(String alamatParkir) {
        this.alamatParkir = alamatParkir;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
