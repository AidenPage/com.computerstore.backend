package com.computerstore.backend.repository.peripherals;


import com.computerstore.backend.domain.peripherals.Mouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aiden on 2016/10/23.
 */
@Repository
public interface MouseRepository extends CrudRepository<Mouse,Long> {
}
