package com.stealthcorp.incidentmanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class IncidentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncidentManagementApplication.class, args);
          log.info("Incident Management Application Started");
    }

}
