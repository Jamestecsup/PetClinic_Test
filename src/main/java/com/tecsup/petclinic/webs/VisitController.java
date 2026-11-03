package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.services.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping(value = "/visits", params = "petId")
    public ResponseEntity<List<VisitDTO>> findByPetId(@RequestParam Integer petId) {
        List<VisitDTO> visits = visitService.findByPetId(petId);
        return ResponseEntity.ok(visits);
    }

    @GetMapping(value = "/visits", params = "vetId")
    public ResponseEntity<List<VisitDTO>> findByVetId(@RequestParam Integer vetId) {
        List<VisitDTO> visits = visitService.findByVetId(vetId);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/visits/pet/{id}/total-cost")
    public ResponseEntity<BigDecimal> calculateTotalCostByPet(@PathVariable Integer id) {
        BigDecimal totalCost = visitService.calculateTotalCostByPet(id);
        return ResponseEntity.ok(totalCost);
    }
}
