package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.services.VisitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/visits")
    public ResponseEntity<VisitDTO> create(@RequestBody VisitDTO visitDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.create(visitDTO));
    }

    @PutMapping("/visits/{id}")
    public ResponseEntity<VisitDTO> update(@PathVariable Integer id, @RequestBody VisitDTO visitDTO) {
        try {
            VisitDTO existing = visitService.findById(id);
            existing.setPetId(visitDTO.getPetId());
            existing.setVetId(visitDTO.getVetId());
            existing.setVisitDate(visitDTO.getVisitDate());
            existing.setDescription(visitDTO.getDescription());
            existing.setCost(visitDTO.getCost());
            return ResponseEntity.ok(visitService.update(existing));
        } catch (VisitNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            visitService.delete(id);
            return ResponseEntity.ok().build();
        } catch (VisitNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
