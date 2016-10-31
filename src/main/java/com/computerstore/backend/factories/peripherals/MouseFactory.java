package com.computerstore.backend.factories.peripherals;


import com.computerstore.backend.domain.peripherals.Mouse;

/**
 * Created by Aiden on 2016/10/23.
 */
public class MouseFactory
{
    public static Mouse getMouse(String name, String price)
    {
        Mouse mouse = new Mouse.Builder()
                .name(name)
                .price(price)
                .build();
        return mouse;
    }
}
