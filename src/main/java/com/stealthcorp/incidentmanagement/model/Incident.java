package com.stealthcorp.incidentmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class Incident {

    private Long id;
    private String title;
    private String description;
    private Severity severity;
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;
    private String owner;
    private List<String> changeLog = new ArrayList<>();
    private Status status;

    public Incident(String title) {
        this.title = title;
    }
}