package com.computerstore.backend.restclient.components;


import com.computerstore.backend.domain.components.Processor;
import com.computerstore.backend.services.components.ProcessorService;
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
public class ProcessorController
{
    @Autowired
    private ProcessorService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/processor/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Processor> createProcessor(@RequestBody Processor processor) {
        service.create(processor);
        return new ResponseEntity<Processor>(processor, HttpStatus.CREATED);
    }

    /*
        READ SINGLE Drink
    * */
    @RequestMapping(value = "/processor/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Processor> readProcessorByID(@PathVariable("id") long id) {
        Processor processor = service.readById(id);
        if (processor == null) {
            return new ResponseEntity<Processor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Processor>(processor, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/processors/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Processor>> findAllProcessors() {
        Iterable<Processor> processors = service.readAll();
        if (processors == null) {
            return new ResponseEntity<Iterable<Processor>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Processor>>(processors, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/processor/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    public ResponseEntity<Processor> updateProcessor(@PathVariable("id") long id, @RequestBody Processor processor)
    {
        Processor currentProcessor = service.readById(id);
        if(currentProcessor == null)
        {
            return new ResponseEntity<Processor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Processor update = new Processor.Builder()
                .copy(currentProcessor)
                .name(processor.getName())
                .price(processor.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Processor>(update,HttpStatus.OK);
    }

    /*
        DELETE HotDrink
    * */
    @RequestMapping(value = "/processor/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Processor> deleteProcessor(@PathVariable("id") long id)
    {
        Processor processor = service.readById(id);
        if(processor == null)
        {
            return new ResponseEntity<Processor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(processor);
        return new ResponseEntity<Processor>(HttpStatus.NO_CONTENT);
    }
}
