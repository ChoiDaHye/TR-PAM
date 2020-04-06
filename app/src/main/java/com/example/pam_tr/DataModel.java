package com.example.pam_tr;

public class DataModel {
    private String name, desc_lg, desc_sm, img, indeks;
    private Double latitude, longitude;

    public String getIndeks() {
        return indeks;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public DataModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc_lg() {
        return desc_lg;
    }

    public void setDesc_lg(String desc_lg) {
        this.desc_lg = desc_lg;
    }

    public String getDesc_sm() {
        return desc_sm;
    }

    public void setDesc_sm(String desc_sm) {
        this.desc_sm = desc_sm;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
