package com.computerstore.backend.restclient.components;


import com.computerstore.backend.domain.components.Memory;
import com.computerstore.backend.services.components.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aiden on 2016/10/23.
 */
@RestController
@RequestMapping("/")
public class MemoryController
{
    @Autowired
    private MemoryService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/memory/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Memory> createMemory(@RequestBody Memory memory) {
        service.create(memory);
        return new ResponseEntity<Memory>(memory, HttpStatus.CREATED);
    }

    /*
        READ SINGLE
    * */
    @RequestMapping(value = "/memory/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Memory> readMemoryByID(@PathVariable("id") long id) {
        Memory drink = service.readById(id);
        if (drink == null) {
            return new ResponseEntity<Memory>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Memory>(drink, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/memories/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Memory>> findAllMemories() {
        Iterable<Memory> coldBeverages = service.readAll();
        if (coldBeverages == null) {
            return new ResponseEntity<Iterable<Memory>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Memory>>(coldBeverages, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/memory/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    public ResponseEntity<Memory> updateMemory(@PathVariable("id") long id, @RequestBody Memory memory)
    {
        Memory Memorys = service.readById(id);
        if(Memorys == null)
        {
            return new ResponseEntity<Memory>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Memory update = new Memory.Builder()
                .copy(Memorys)
                .name(memory.getName())
                .price(memory.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Memory>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/memory/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Memory> deleteMemory(@PathVariable("id") long id)
    {
        Memory memory = service.readById(id);
        if(memory == null)
        {
            return new ResponseEntity<Memory>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(memory);
        return new ResponseEntity<Memory>(HttpStatus.NO_CONTENT);
    }
}
