package com.computerstore.backend.restclient.peripherals;


import com.computerstore.backend.domain.peripherals.Speaker;
import com.computerstore.backend.services.peripherals.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Aiden on 2016/10/23.
 */
@RestController
public class SpeakerController
{
    @Autowired
    private SpeakerService service;

    /*
        CREATE
     */
    @RequestMapping(value = "/speaker/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Speaker> createSpeaker(@RequestBody Speaker speaker) {
        service.create(speaker);
        return new ResponseEntity<Speaker>(speaker, HttpStatus.CREATED);
    }

    /*
        READ SINGLE
    * */
    @RequestMapping(value = "/speaker/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Speaker> readSpeakerByID(@PathVariable("id") long id) {
        Speaker speaker = service.readById(id);
        if (speaker == null) {
            return new ResponseEntity<Speaker>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Speaker>(speaker, HttpStatus.OK);
    }

    /*
        READ ALL
      */
    @RequestMapping(value = "/speakers/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Speaker>> findAllSpeakers() {
        Iterable<Speaker> speakers = service.readAll();
        if (speakers == null) {
            return new ResponseEntity<Iterable<Speaker>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable<Speaker>>(speakers, HttpStatus.OK);
    }

    /*
        UPDATE
    * */
    @RequestMapping(value = "/speaker/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Speaker> updateSpeaker(@PathVariable("id") long id, @RequestBody Speaker speaker)
    {
        Speaker currentSpeaker = service.readById(id);
        if(currentSpeaker == null)
        {
            return new ResponseEntity<Speaker>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Speaker update = new Speaker.Builder()
                .copy(currentSpeaker)
                .name(speaker.getName())
                .price(speaker.getPrice())
                .build();
        service.update(update);
        return new ResponseEntity<Speaker>(update,HttpStatus.OK);
    }

    /*
        DELETE
    * */
    @RequestMapping(value = "/speaker/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Speaker> deleteColdDrinkSpeaker(@PathVariable("id") long id)
    {
        Speaker speaker = service.readById(id);
        if(speaker == null)
        {
            return new ResponseEntity<Speaker>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        service.delete(speaker);
        return new ResponseEntity<Speaker>(HttpStatus.NO_CONTENT);
    }
}
