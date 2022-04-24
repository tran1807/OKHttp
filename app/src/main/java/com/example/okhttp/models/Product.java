package com.example.okhttp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @Expose
    @SerializedName("id")
    public int id;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("image")
    public String img;

    @Expose
    @SerializedName("soluong")
    public String soluong;
}
