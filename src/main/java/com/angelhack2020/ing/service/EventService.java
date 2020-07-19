package com.angelhack2020.ing.service;


import com.angelhack2020.ing.dao.EventRepository;
import com.angelhack2020.ing.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public Event saveEvent(Event event){
        if(event.getEvent().equals("click")){
            event.setWeight(1);
        }else if(event.getEvent().equals("buy")){
            event.setWeight(5);
        }else{
            return null;
        }

        Event saved = eventRepository.save(event);
        return saved;
    }
}
