# Demo Application with Springboot

## Notes

- Started with a spring application with only web.
- Add data and schema sql file
- Open the POM file and add
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
```
Then open the application.properties file

```
logging.level.org.springframework.jdbc.datasource.init.ScriptUtils=debug
spring.jpa.hibernate.ddl-auto=none # used to remove schema control to manual
```

Reload maven changes and then run the application. The console will show that 
the schema.sql file is executed and then the data.sql file is executed. 

Data is now loaded into H2.

---

We create a new package called data and add a data Class called Room.

```java
package com.anhadtech.demo_app.data;

import javax.persistence.*;

/*
        @Entity tells that the Class is a JPA entity
        @Table and speficy the name
        
        Add Column names, getters and setters
        
        override the toString Method.
 */
@Entity
@Table(name="ROOM")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROOM_ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    @Column(name = "BED_INFO")
    private String bedInfo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedInfo() {
        return bedInfo;
    }

    public void setBedInfo(String bedInfo) {
        this.bedInfo = bedInfo;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", bedInfo='" + bedInfo + '\'' +
                '}';
    }
}

```

Next, create an interface. 
```java
package com.anhadtech.demo_app.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
This is a templated value that takes the Entity(Room) and the class types of the Id
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{
}

```

Then, we create a new package(util) to see our data access working.
We add a class
```java
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
        Iterable<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
    }
}

```

This will findAll the rooms and print them to the console.