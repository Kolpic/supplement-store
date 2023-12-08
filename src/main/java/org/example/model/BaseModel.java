package org.example.model;

import lombok.Getter;

import javax.swing.*;

@Getter
public class BaseModel {

    private String name;
    private double price;
    private ImageIcon imageIcon;

    public BaseModel(String name, double price, ImageIcon imageIcon) {
        this.name = name;
        this.price = price;
        this.imageIcon = imageIcon;
    }
}
