package org.example.model.aminoasid;

import lombok.Getter;
import lombok.Setter;
import org.example.model.BaseModel;

import javax.swing.*;

@Getter
@Setter
public abstract class BaseAminoAcid extends BaseModel {
    private String name;
    private double price;
    private ImageIcon imageIcon;

    public BaseAminoAcid(String name, double price, ImageIcon imageIcon) {
        this.name = name;
        this.price = price;
        this.imageIcon = imageIcon;
    }
}
