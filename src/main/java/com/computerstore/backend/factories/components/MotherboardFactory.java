package com.computerstore.backend.factories.components;


import com.computerstore.backend.domain.components.Motherboard;

/**
 * Created by Aiden on 2016/10/23.
 */
public class MotherboardFactory {

    public static Motherboard getMotherboard(String name, String price)
    {
        Motherboard motherboard = new Motherboard.Builder()
                .name(name)
                .price(price)
                .build();
        return motherboard;
    }
}
