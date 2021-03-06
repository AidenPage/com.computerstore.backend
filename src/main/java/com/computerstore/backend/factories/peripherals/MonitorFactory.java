package com.computerstore.backend.factories.peripherals;


import com.computerstore.backend.domain.peripherals.Monitor;

/**
 * Created by Aiden on 2016/10/23.
 */
public class MonitorFactory
{
    public static Monitor getMonitor(String name, String price)
    {
        Monitor monitor = new Monitor.Builder()
                .name(name)
                .price(price)
                .build();
        return monitor;
    }
}
