package com.stealthcorp.incidentmanagement.controller;

import com.stealthcorp.incidentmanagement.model.Incident;
import com.stealthcorp.incidentmanagement.service.IncidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //Allow request from local for dev.
@RequestMapping("/api/incidents")
@Slf4j
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @GetMapping
    public List<Incident> listIncidents() {
        return incidentService.listIncidents();
    }

    @PostMapping
    public synchronized Incident createIncident(@RequestBody Incident incident) {
        return incidentService.createIncident(incident);
    }

    @GetMapping("/{id}")
    public Incident getIncident(@PathVariable Long id) {
        return incidentService.getIncident(id);
    }

    @PutMapping("/{id}")
    public synchronized Incident modifyIncident(@PathVariable Long id, @RequestBody Incident incident) {
        return incidentService.modifyIncident(id, incident);
    }

    @DeleteMapping("/{id}")
    public synchronized void deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
    }
}