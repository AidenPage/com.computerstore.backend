package com.computerstore.backend.restclient.peripherals;


import com.computerstore.backend.domain.peripherals.Keyboard;
import com.computerstore.backend.services.peripherals.KeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aiden on 2016/10/23.
 */
@RestController
public class KeyboardController
{
    @Autowired
    private KeyboardService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/keyboard/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Keyboard> createKeyboard(@RequestBody Keyboard keyboard) {
        service.create(keyboard);
        return new ResponseEntity<Keyboard>(keyboard, HttpStatus.CREATED);
    }

    /*
        READ
    * */
    @RequestMapping(value = "/keyboard/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Keyboard> readKeyboardByID(@PathVariable("id") long id) {
        Keyboard keyboard = service.readById(id);
        if (keyboard == null) {
            return new ResponseEntity<Keyboard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Keyboard>(keyboard, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/keyboards/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Keyboard>> findAllKeyboards() {
        Iterable<Keyboard> keyboards = service.readAll();
        if (keyboards == null) {
            return new ResponseEntity<Iterable<Keyboard>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Keyboard>>(keyboards, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/keyboard/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Keyboard> updateKeyboard(@PathVariable("id") long id, @RequestBody Keyboard keyboard)
    {
        Keyboard currentKeyboard = service.readById(id);
        if(currentKeyboard == null)
        {
            return new ResponseEntity<Keyboard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Keyboard update = new Keyboard.Builder()
                .copy(currentKeyboard)
                .name(keyboard.getName())
                .price(keyboard.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Keyboard>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/keyboard/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Keyboard> deleteKeyboard(@PathVariable("id") long id)
    {
        Keyboard keyboard = service.readById(id);
        if(keyboard == null)
        {
            return new ResponseEntity<Keyboard>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(keyboard);
        return new ResponseEntity<Keyboard>(HttpStatus.NO_CONTENT);
    }
}
