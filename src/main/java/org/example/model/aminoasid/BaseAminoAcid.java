package org.example.model.aminoasid;

import lombok.Getter;
import lombok.Setter;
import org.example.model.BaseModel;

import javax.swing.*;

@Getter
@Setter
public abstract class BaseAminoAcid extends BaseModel {

    public BaseAminoAcid(String name, double price, ImageIcon imageIcon) {
        super(name, price, imageIcon);
    }
}
