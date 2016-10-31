package com.computerstore.backend.restclient.components;


import com.computerstore.backend.domain.components.Motherboard;
import com.computerstore.backend.services.components.MotherboardService;
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
public class MotherboardController {
    @Autowired
    private MotherboardService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/motherboard/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity<Motherboard> createMotherboard(@RequestBody Motherboard motherboard) {
        service.create(motherboard);
        return new ResponseEntity<Motherboard>(motherboard, HttpStatus.CREATED);
    }

    /*
        READ SINGLE Drink
    * */
    @RequestMapping(value = "/motherboard/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Motherboard> readMotherboardByID(@PathVariable("id") long id) {
        Motherboard motherboard = service.readById(id);
        if (motherboard == null) {
            return new ResponseEntity<Motherboard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Motherboard>(motherboard, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/motherboards/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Motherboard>> findAllMotherboards() {
        Iterable<Motherboard> Motherboards = service.readAll();
        if (Motherboards == null) {
            return new ResponseEntity<Iterable<Motherboard>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Motherboard>>(Motherboards, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/motherboard/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.PUT)
    public ResponseEntity<Motherboard> updateMotherboard(@PathVariable("id") long id, @RequestBody Motherboard motherboard)
    {
        Motherboard currentMotherboard = service.readById(id);
        if(currentMotherboard == null)
        {
            return new ResponseEntity<Motherboard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Motherboard update = new Motherboard.Builder()
                .copy(currentMotherboard)
                .name(motherboard.getName())
                .price(motherboard.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Motherboard>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/motherboard/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Motherboard> deleteMotherboard(@PathVariable("id") long id)
    {
        Motherboard motherboard = service.readById(id);
        if(motherboard == null)
        {
            return new ResponseEntity<Motherboard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(motherboard);
        return new ResponseEntity<Motherboard>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value ="/")
    public String defaultMapping()
    {
        return  "Developed By: Aiden Page;" + "\n" +
                "Group 3A;" + "\n"+
                "Student Number: 211121614;";
    }
}

