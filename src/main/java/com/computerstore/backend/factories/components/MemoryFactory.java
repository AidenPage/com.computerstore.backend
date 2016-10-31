package com.computerstore.backend.factories.components;
import com.computerstore.backend.domain.components.Memory;

/**
 * Created by Aiden on 2016/10/23.
 */

public class MemoryFactory
{
    public static Memory getMemory(String name, String price)
    {
        Memory memory = new Memory.Builder()
                .name(name)
                .price(price)
                .build();
        return memory;
    }
}
