package com.stealthcorp.incidentmanagement.service;

import com.stealthcorp.incidentmanagement.model.Incident;
import com.stealthcorp.incidentmanagement.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class IncidentServiceTest {

    private IncidentService incidentService;

    @BeforeEach
    void setUp() {
        incidentService = new IncidentService();
    }

    @Test
    void testCreateIncident() {
        Incident incident = new Incident();
        incident.setTitle("Test Incident");

        Incident createdIncident = incidentService.createIncident(incident);

        Assertions.assertNotNull(createdIncident);
        Assertions.assertEquals(1, createdIncident.getId());
        Assertions.assertEquals("Test Incident", createdIncident.getTitle());
        Assertions.assertEquals(Status.ACTIVE, createdIncident.getStatus());
        Assertions.assertNotNull(createdIncident.getCreatedTime());
        Assertions.assertEquals(1, incidentService.listIncidents().size());
    }

    @Test
    void testModifyIncident() {
        Incident incident = new Incident();
        incident.setTitle("Test Incident");
        Incident createdIncident = incidentService.createIncident(incident);

        Incident modifiedIncident = new Incident();
        modifiedIncident.setTitle("Modified Incident");

        Incident result = incidentService.modifyIncident(createdIncident.getId(), modifiedIncident);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(createdIncident.getId(), result.getId());
        Assertions.assertEquals("Modified Incident", result.getTitle());
        Assertions.assertTrue(result.getChangeLog().get(0).contains("Modified at"));
    }

    @Test
    void testListIncidents() {
        incidentService.createIncident(new Incident("Incident 1"));
        incidentService.createIncident(new Incident("Incident 2"));

        List<Incident> incidents = incidentService.listIncidents();
        Assertions.assertEquals(2, incidents.size());
    }

    @Test
    void testGetIncident() {
        Incident incident = new Incident();
        incident.setTitle("Test Incident");
        Incident createdIncident = incidentService.createIncident(incident);

        Incident retrievedIncident = incidentService.getIncident(createdIncident.getId());

        Assertions.assertNotNull(retrievedIncident);
        Assertions.assertEquals(createdIncident.getId(), retrievedIncident.getId());
    }

    @Test
    void testDeleteIncident() {
        Incident incident = new Incident();
        incident.setTitle("Test Incident");
        Incident createdIncident = incidentService.createIncident(incident);

        incidentService.deleteIncident(createdIncident.getId());

        Incident deletedIncident = incidentService.getIncident(createdIncident.getId());
        Assertions.assertNotNull(deletedIncident); // Should still exist
        Assertions.assertEquals(Status.DELETED, deletedIncident.getStatus());
    }

    @Test
    void testModifyIncidentNotFound() {
        Incident modifiedIncident = new Incident();
        modifiedIncident.setTitle("Modified Incident");

        Incident result = incidentService.modifyIncident(999L, modifiedIncident);
        Assertions.assertNull(result);
    }

    @Test
    void testDeleteIncidentNotFound() {
        incidentService.deleteIncident(999L);
        // Expect no exceptions and continue execution
    }
}
