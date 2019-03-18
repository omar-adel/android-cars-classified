package com.example.samuray.myapplication;

public class BrandModel {

    private Integer id;
    private Integer id_brand;
    private String name;


    public BrandModel(Integer id,
                    Integer id_brand,
                    String name
    ){
        this.id = id;
        this.id_brand = id_brand;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public int getIdbrand(){
        return id_brand;
    }
    public String getName(){
        return name;
    }

}

