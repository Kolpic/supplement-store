package org.example.model.protein;

import lombok.Getter;
import lombok.Setter;
import org.example.model.BaseModel;

import javax.swing.*;

@Getter
@Setter
public abstract class BaseProtein extends BaseModel {

    public BaseProtein(String name, double price, ImageIcon imageIcon) {
        super(name, price, imageIcon);
    }
}
