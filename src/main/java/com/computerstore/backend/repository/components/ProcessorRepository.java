package com.computerstore.backend.repository.components;


import com.computerstore.backend.domain.components.Processor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aiden on 2016/10/23.
 */
@Repository
public interface ProcessorRepository extends CrudRepository<Processor,Long> {
}
