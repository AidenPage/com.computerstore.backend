package com.computerstore.backend.restclient.peripherals;


import com.computerstore.backend.domain.peripherals.Printer;
import com.computerstore.backend.services.peripherals.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aiden on 2016/10/23.
 */
@RestController
public class PrinterController
{
    @Autowired
    private PrinterService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/printer/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Printer> createPrinter(@RequestBody Printer printer) {
        service.create(printer);
        return new ResponseEntity<Printer>(printer, HttpStatus.CREATED);
    }

    /*
        READ SINGLE
    * */
    @RequestMapping(value = "/printer/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Printer> readPrinterByID(@PathVariable("id") long id) {
        Printer printer = service.readById(id);
        if (printer == null) {
            return new ResponseEntity<Printer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Printer>(printer, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/printers/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Printer>> findAllPrinters() {
        Iterable<Printer> printers = service.readAll();
        if (printers == null) {
            return new ResponseEntity<Iterable<Printer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Printer>>(printers, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/printer/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Printer> updatePrinter(@PathVariable("id") long id, @RequestBody Printer printer)
    {
        Printer currentPrinter = service.readById(id);
        if(currentPrinter == null)
        {
            return new ResponseEntity<Printer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Printer update = new Printer.Builder()
                .copy(currentPrinter)
                .name(printer.getName())
                .price(printer.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Printer>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/printer/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Printer> deletePrinter(@PathVariable("id") long id)
    {
        Printer burger = service.readById(id);
        if(burger == null)
        {
            return new ResponseEntity<Printer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(burger);
        return new ResponseEntity<Printer>(HttpStatus.NO_CONTENT);
    }
}
