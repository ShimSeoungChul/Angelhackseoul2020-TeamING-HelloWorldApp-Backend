package com.angelhack2020.ing.service;

import com.angelhack2020.ing.dao.EventRepository;
import com.angelhack2020.ing.domain.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceTest {

    @Autowired
    EventRepository eventRepository;

    @Test
    public void saveEvent(){

//        List<Event> eventList= new ArrayList<Event>();
//        for(int i =1500; 1600 > i ; i++){
//            System.out.println("test:"+i);
//            int weight;
//            String event;
//            if(i%4==0){
//                event = "buy";
//                weight = 5;
//            }else{
//                event = "click";
//                weight = 1;
//            }
//
//            Event eventObject = Event.builder()
//                    .id(i)
//                    .userId("tester10")
//                    .productId((int) (Math.random()*49)+1)
//                    .event(event)
//                    .weight(weight)
//                    .time(LocalDateTime.now())
//                    .build();
//            eventRepository.save(eventObject);
//        }
        //System.out.println("eventList"+eventList.size());

    }
}