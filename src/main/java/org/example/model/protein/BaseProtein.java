package org.example.model.protein;

import lombok.Getter;
import lombok.Setter;
import org.example.model.BaseModel;

import javax.swing.*;

@Getter
@Setter
public abstract class BaseProtein extends BaseModel {
    private String name;
    private double price;
    private ImageIcon imageIcon;

    public BaseProtein(String name, double price, ImageIcon imageIcon) {
        this.name = name;
        this.price = price;
        this.imageIcon = imageIcon;
    }
}
