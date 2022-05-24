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