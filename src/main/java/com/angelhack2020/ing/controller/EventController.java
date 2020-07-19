package com.angelhack2020.ing.controller;


import com.angelhack2020.ing.domain.Event;
import com.angelhack2020.ing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("")
    private String create(@RequestParam(value = "event") String event,
                          @RequestParam(value = "productId") int productId,
                          @RequestParam(value = "userId") String userId){
        try{
            Event save = Event.builder()
                    .event(event)
                    .productId(productId)
                    .userId(userId)
                    .time(LocalDateTime.now())
                    .build();
            Event saved = eventService.saveEvent(save);

            if(saved==null){
                return "FAIL";
            }

            return "SUCC";
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
    }

}
