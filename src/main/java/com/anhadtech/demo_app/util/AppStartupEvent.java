package com.anhadtech.demo_app.util;

import com.anhadtech.demo_app.data.Room;
import com.anhadtech.demo_app.data.RoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener <ApplicationReadyEvent> {
    /*
    The Application listner makes sure we wait for the Ready event before we run this code.
     */

    private final RoomRepository roomRepository;

    /*
    This is needed since the object is final.
     */
    public AppStartupEvent(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event){
        System.out.println("Finding and Printing all Rooms in the Database...");
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
    }
}
