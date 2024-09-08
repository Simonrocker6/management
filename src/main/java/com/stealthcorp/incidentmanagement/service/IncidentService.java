package com.stealthcorp.incidentmanagement.service;

import com.stealthcorp.incidentmanagement.model.Incident;
import com.stealthcorp.incidentmanagement.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.stealthcorp.incidentmanagement.model.Status.ACTIVE;

@Service
@Slf4j
public class IncidentService {

    private Map<Long, Incident> incidentMap = new HashMap<>();
    private long currentId = 1;

    public List<Incident> listIncidents() {
        return new ArrayList<>(incidentMap.values().stream().filter(x -> x.getStatus().equals(ACTIVE)).toList());
    }

    public Incident createIncident(Incident incident) {
        incident.setId(currentId++);
        incident.setCreatedTime(LocalDateTime.now());
        incident.setLastModifiedTime(LocalDateTime.now());
        incident.setStatus(ACTIVE);
        List<String> logs = new ArrayList<>();
        logs.add("Created at " + incident.getCreatedTime());
        incident.setChangeLog(logs);
        incidentMap.put(incident.getId(), incident);
        return incident;
    }

    public Incident modifyIncident(Long id, Incident incident) {
        if (!incidentMap.containsKey(id)) {
            log.error("Incident not found to modify.");
            return null;
        }
        incident.setId(id);
        incident.setStatus(ACTIVE);
        incident.setLastModifiedTime(LocalDateTime.now());
        List<String> logs = incident.getChangeLog();
        logs.add("Modified at " + incident.getLastModifiedTime());
        incident.setChangeLog(logs);
        incidentMap.put(id, incident);
        return incident;
    }

    public Incident getIncident(Long id) {
        return incidentMap.get(id);
    }

    public void deleteIncident(Long id)  {
        if (!incidentMap.containsKey(id)) {
            log.error("Incident not found to delete.");
            return;
        }
        Incident incident = incidentMap.get(id);
        incident.setStatus(Status.DELETED); // Mark as deleted instead of deletion
        List<String> logs = new ArrayList<>();
        logs.add("Deleted at " + incident.getLastModifiedTime());
        incident.setChangeLog(logs);
        incidentMap.put(id, incident);
    }
}