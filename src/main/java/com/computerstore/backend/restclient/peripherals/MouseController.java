package com.computerstore.backend.restclient.peripherals;


import com.computerstore.backend.domain.peripherals.Mouse;
import com.computerstore.backend.services.peripherals.MouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aiden on 2016/10/23.
 */
@RestController
public class MouseController
{
    @Autowired
    private MouseService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/mouse/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mouse> creatMouse(@RequestBody Mouse mouse) {
        service.create(mouse);
        return new ResponseEntity<Mouse>(mouse, HttpStatus.CREATED);
    }

    /*
        READ SINGLE
    * */
    @RequestMapping(value = "/mouse/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Mouse> readMouseByID(@PathVariable("id") long id) {
        Mouse mouse = service.readById(id);
        if (mouse == null) {
            return new ResponseEntity<Mouse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Mouse>(mouse, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/mouses/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Mouse>> findAllMouses() {
        Iterable<Mouse> mouses = service.readAll();
        if (mouses == null) {
            return new ResponseEntity<Iterable<Mouse>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Mouse>>(mouses, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/mouse/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mouse> updateMouse(@PathVariable("id") long id, @RequestBody Mouse mouse)
    {
        Mouse currentMouse = service.readById(id);
        if(currentMouse == null)
        {
            return new ResponseEntity<Mouse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Mouse update = new Mouse.Builder()
                .copy(currentMouse)
                .name(mouse.getName())
                .price(mouse.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Mouse>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/mouse/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Mouse> deleteMouse(@PathVariable("id") long id)
    {
        Mouse mouse = service.readById(id);
        if(mouse == null)
        {
            return new ResponseEntity<Mouse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(mouse);
        return new ResponseEntity<Mouse>(HttpStatus.NO_CONTENT);
    }
}
