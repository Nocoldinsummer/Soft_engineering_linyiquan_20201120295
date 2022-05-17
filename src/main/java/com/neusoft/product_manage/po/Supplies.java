package com.neusoft.product_manage.po;

public class Supplies {
    private Integer Code;
    private String Name;
    private String Production_date;
    private String Production_quality;
    private String Manufacturer;
    private String Sourse;

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProduction_date() {
        return Production_date;
    }

    public void setProduction_date(String production_date) {
        Production_date = production_date;
    }

    public String getProduction_quality() {
        return Production_quality;
    }

    public void setProduction_quality(String production_quality) {
        Production_quality = production_quality;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getSourse() {
        return Sourse;
    }

    public void setSourse(String sourse) {
        Sourse = sourse;
    }
}
