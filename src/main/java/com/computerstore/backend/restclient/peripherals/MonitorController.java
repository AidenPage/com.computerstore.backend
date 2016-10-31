package com.computerstore.backend.restclient.peripherals;


import com.computerstore.backend.domain.peripherals.Monitor;
import com.computerstore.backend.services.peripherals.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aiden on 2016/10/23.
 */
@RestController
public class MonitorController
{
    @Autowired
    private MonitorService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/monitor/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Monitor> createMonitor(@RequestBody Monitor monitor) {
        service.create(monitor);
        return new ResponseEntity<Monitor>(monitor, HttpStatus.CREATED);
    }

    /*
        READ SINGLE
    * */
    @RequestMapping(value = "/monitor/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Monitor> readMonitorByID(@PathVariable("id") long id) {
        Monitor monitor = service.readById(id);
        if (monitor == null) {
            return new ResponseEntity<Monitor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Monitor>(monitor, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/monitors/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Monitor>> findAllMonitors() {
        Iterable<Monitor> monitors = service.readAll();
        if (monitors == null) {
            return new ResponseEntity<Iterable<Monitor>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Monitor>>(monitors, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/monitor/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Monitor> updateMonitor(@PathVariable("id") long id, @RequestBody Monitor monitor)
    {
        Monitor currentMonitor = service.readById(id);
        if(currentMonitor == null)
        {
            return new ResponseEntity<Monitor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Monitor update = new Monitor.Builder()
                .copy(currentMonitor)
                .name(monitor.getName())
                .price(monitor.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Monitor>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/monitor/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Monitor> deleteMonitor(@PathVariable("id") long id)
    {
        Monitor monitor = service.readById(id);
        if(monitor == null)
        {
            return new ResponseEntity<Monitor>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(monitor);
        return new ResponseEntity<Monitor>(HttpStatus.NO_CONTENT);
    }
}
