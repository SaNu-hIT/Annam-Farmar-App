package com.sft.annam.Model;

/**
 * Created by SFT on 15/2/2017.
 */
public class KarshakaItem_Model {
    private String karshakasena_id;
    private String ownerid;
    private String title;
    private String location;
    private String desc;
    private String numbFppl;
    private String radious;

    public KarshakaItem_Model(String karshakasena_id, String ownerid, String title, String location, String desc, String numbFppl, String radious) {
        this.karshakasena_id = karshakasena_id;
        this.ownerid = ownerid;
        this.location = location;
        this.numbFppl = numbFppl;
        this.title = title;
        this.desc = desc;
        this.radious = radious;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumbFppl() {
        return numbFppl;
    }

    public void setNumbFppl(String numbFppl) {
        this.numbFppl = numbFppl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(String ownerid) {
        this.ownerid = ownerid;
    }

    public String getRadious() {
        return radious;
    }

    public void setRadious(String radious) {
        this.radious = radious;
    }

    public String getKarshakasena_id() {
        return karshakasena_id;
    }

    public void setKarshakasena_id(String karshakasena_id) {
        this.karshakasena_id = karshakasena_id;
    }
}
