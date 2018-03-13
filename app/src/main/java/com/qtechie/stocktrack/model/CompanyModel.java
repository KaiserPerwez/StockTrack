package com.qtechie.stocktrack.model;

/**
 * Created by Admin on 06-01-2018.
 */

public class CompanyModel {
    int id;
    String company_name,
            day_lowPrice, day_highPrice,
            week_lowPrice, week_highPrice,
            month_lowPrice, month_highPrice;
    public CompanyModel(String company_name, String day_lowPrice, String day_highPrice, String week_lowPrice, String week_highPrice, String month_lowPrice, String month_highPrice) {
        this.company_name = company_name;
        this.day_lowPrice = day_lowPrice;
        this.day_highPrice = day_highPrice;
        this.week_lowPrice = week_lowPrice;
        this.week_highPrice = week_highPrice;
        this.month_lowPrice = month_lowPrice;
        this.month_highPrice = month_highPrice;
    }
//getters


    public int getId() {
        return id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getDay_lowPrice() {
        return day_lowPrice;
    }

    public String getDay_highPrice() {
        return day_highPrice;
    }

    public String getWeek_lowPrice() {
        return week_lowPrice;
    }

    public String getWeek_highPrice() {
        return week_highPrice;
    }

    public String getMonth_lowPrice() {
        return month_lowPrice;
    }

    public String getMonth_highPrice() {
        return month_highPrice;
    }


    //setters


    public void setId(int id) {
        this.id = id;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setDay_lowPrice(String day_lowPrice) {
        this.day_lowPrice = day_lowPrice;
    }

    public void setDay_highPrice(String day_highPrice) {
        this.day_highPrice = day_highPrice;
    }

    public void setWeek_lowPrice(String week_lowPrice) {
        this.week_lowPrice = week_lowPrice;
    }

    public void setWeek_highPrice(String week_highPrice) {
        this.week_highPrice = week_highPrice;
    }

    public void setMonth_lowPrice(String month_lowPrice) {
        this.month_lowPrice = month_lowPrice;
    }

    public void setMonth_highPrice(String month_highPrice) {
        this.month_highPrice = month_highPrice;
    }
}
