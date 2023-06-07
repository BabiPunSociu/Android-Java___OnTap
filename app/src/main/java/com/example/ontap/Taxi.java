package com.example.ontap;

public class Taxi {
    private int COLUMN_ID;
    private  String COLUMN_SO_XE;
    private  float COLUMN_QUANG_DUONG;
    private  int COLUMN_DON_GIA;
    private  int COLUMN_PHAN_TRAM_KM;

    public Taxi() {}

    public Taxi(int COLUMN_ID, String COLUMN_SO_XE, float COLUMN_QUANG_DUONG, int COLUMN_DON_GIA, int COLUMN_PHAN_TRAM_KM) {
        this.COLUMN_ID = COLUMN_ID;
        this.COLUMN_SO_XE = COLUMN_SO_XE;
        this.COLUMN_QUANG_DUONG = COLUMN_QUANG_DUONG;
        this.COLUMN_DON_GIA = COLUMN_DON_GIA;
        this.COLUMN_PHAN_TRAM_KM = COLUMN_PHAN_TRAM_KM;
    }

    public int getCOLUMN_ID() {
        return COLUMN_ID;
    }

    public void setCOLUMN_ID(int COLUMN_ID) {
        this.COLUMN_ID = COLUMN_ID;
    }

    public String getCOLUMN_SO_XE() {
        return COLUMN_SO_XE;
    }

    public void setCOLUMN_SO_XE(String COLUMN_SO_XE) {
        this.COLUMN_SO_XE = COLUMN_SO_XE;
    }

    public float getCOLUMN_QUANG_DUONG() {
        return COLUMN_QUANG_DUONG;
    }

    public void setCOLUMN_QUANG_DUONG(float COLUMN_QUANG_DUONG) {
        this.COLUMN_QUANG_DUONG = COLUMN_QUANG_DUONG;
    }

    public int getCOLUMN_DON_GIA() {
        return COLUMN_DON_GIA;
    }

    public void setCOLUMN_DON_GIA(int COLUMN_DON_GIA) {
        this.COLUMN_DON_GIA = COLUMN_DON_GIA;
    }

    public int getCOLUMN_PHAN_TRAM_KM() {
        return COLUMN_PHAN_TRAM_KM;
    }

    public void setCOLUMN_PHAN_TRAM_KM(int COLUMN_PHAN_TRAM_KM) {
        this.COLUMN_PHAN_TRAM_KM = COLUMN_PHAN_TRAM_KM;
    }
}
