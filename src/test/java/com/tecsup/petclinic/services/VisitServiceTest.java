package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    void testCreateVisit() {
        VisitDTO visit = VisitDTO.builder()
                .petId(1)
                .vetId(1)
                .visitDate(LocalDate.of(2025, 1, 15))
                .description("annual checkup")
                .cost(new BigDecimal("65.00"))
                .build();

        VisitDTO created = visitService.create(visit);

        assertNotNull(created.getId());
        assertEquals(visit.getPetId(), created.getPetId());
        assertEquals(visit.getVetId(), created.getVetId());
        assertEquals(visit.getVisitDate(), created.getVisitDate());
        assertEquals(visit.getDescription(), created.getDescription());
        assertEquals(visit.getCost(), created.getCost());
    }

    @Test
    void testUpdateVisit() {
        VisitDTO visit = visitService.create(VisitDTO.builder()
                .petId(1)
                .vetId(1)
                .visitDate(LocalDate.of(2025, 2, 10))
                .description("initial checkup")
                .cost(new BigDecimal("40.00"))
                .build());

        visit.setVisitDate(LocalDate.of(2025, 2, 11));
        visit.setDescription("follow-up checkup");
        visit.setCost(new BigDecimal("55.00"));

        VisitDTO updated = visitService.update(visit);

        assertEquals(visit.getId(), updated.getId());
        assertEquals(visit.getVisitDate(), updated.getVisitDate());
        assertEquals(visit.getDescription(), updated.getDescription());
        assertEquals(visit.getCost(), updated.getCost());
    }

    @Test
    void testFindVisitById() throws VisitNotFoundException {
        VisitDTO visit = visitService.findById(5);

        assertEquals(5, visit.getId());
        assertEquals(1, visit.getPetId());
        assertEquals("general checkup", visit.getDescription());
        assertEquals(new BigDecimal("65.00"), visit.getCost());
    }

    @Test
    void testFindByPetId() {
        List<VisitDTO> visits = visitService.findByPetId(7);

        assertEquals(2, visits.size());
        assertEquals(7, visits.get(0).getPetId());
        assertEquals(7, visits.get(1).getPetId());
    }

    @Test
    void testFindByVetId() {
        List<VisitDTO> visits = visitService.findByVetId(2);

        assertEquals(2, visits.size());
        assertEquals(2, visits.get(0).getVetId());
        assertEquals(2, visits.get(1).getVetId());
    }

    @Test
    void testDeleteVisit() throws VisitNotFoundException {
        assertNotNull(visitService.findById(1));

        visitService.delete(1);

        assertThrows(VisitNotFoundException.class, () -> visitService.findById(1));
    }

    @Test
    void testCalculateTotalCostByPet() {
        assertEquals(new BigDecimal("320.00"), visitService.calculateTotalCostByPet(7));
    }

    @Test
    void testCalculateTotalCostByPet_NoVisits() {
        assertEquals(BigDecimal.ZERO, visitService.calculateTotalCostByPet(100));
    }

    @Test
    void testFindVisitById_NotFound() {
        assertThrows(VisitNotFoundException.class, () -> visitService.findById(999));
    }
}
