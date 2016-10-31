package com.computerstore.backend.services.components.impl;


import com.computerstore.backend.domain.components.Motherboard;
import com.computerstore.backend.repository.components.MotherboardRepository;
import com.computerstore.backend.services.components.MotherboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aiden on 2016/10/23.
 */
@Service
public class MotherboardServiceImpl implements MotherboardService {

    @Autowired
    private MotherboardRepository repository;

    @Override
    public Motherboard create(Motherboard entity) {
        return repository.save(entity);
    }

    @Override
    public Motherboard readById(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Iterable<Motherboard> readAll() {
        Iterable<Motherboard> result = repository.findAll();
        return result;
    }

    @Override
    public Motherboard update(Motherboard entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Motherboard entity) {
        repository.delete(entity);
    }
}
