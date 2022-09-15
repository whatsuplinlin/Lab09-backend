package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Event;
import se331.rest.service.EventService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {


    @Autowired
    EventService eventService;
    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        List<Event> output = null;
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",String.valueOf(eventService.getEventSize()));

        try {
            output = eventService.getEvents(perPage,page);
            return new ResponseEntity<>(output,responseHeader,HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output,responseHeader,HttpStatus.OK);
        }

    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}



