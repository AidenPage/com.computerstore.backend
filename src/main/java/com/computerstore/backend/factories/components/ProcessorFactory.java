package com.computerstore.backend.factories.components;


import com.computerstore.backend.domain.components.Processor;

/**
 * Created by Aiden on 2016/10/23.
 */
public class ProcessorFactory
{
    public static Processor getProcessor(String name, String price)
    {
        Processor processor = new Processor.Builder()
                .name(name)
                .price(price)
                .build();
        return processor;
    }
}
