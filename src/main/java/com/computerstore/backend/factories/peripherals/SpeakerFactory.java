package com.computerstore.backend.factories.peripherals;


import com.computerstore.backend.domain.peripherals.Speaker;

/**
 * Created by Aiden on 2016/10/23.
 */
public class SpeakerFactory
{
    public static Speaker getSpeaker(String name, String price)
    {
        Speaker speaker = new Speaker.Builder()
                .name(name)
                .price(price)
                .build();
        return speaker;
    }
}
