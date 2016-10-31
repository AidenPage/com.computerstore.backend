package com.computerstore.backend.factories.peripherals;


import com.computerstore.backend.domain.peripherals.Keyboard;

/**
 * Created by Aiden on 2016/10/23.
 */
public class KeyboardFactory
{
    public static Keyboard getKeyboard(String name, String price)
    {
        Keyboard keyboard = new Keyboard.Builder()
                .name(name)
                .price(price)
                .build();
        return keyboard;
    }
}
