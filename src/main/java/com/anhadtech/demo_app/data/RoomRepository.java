package com.anhadtech.demo_app.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
This is a templated value that takes the Entity(Room) and the class types of the Id
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
}
