package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
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

	@GetMapping("/visits")
	public ResponseEntity<List<VisitDTO>> findAllVisits() {
		return ResponseEntity.ok(visitService.findAll());
	}

	@GetMapping(value = "/visits", params = "petId")
	public ResponseEntity<List<VisitDTO>> findByPetId(@RequestParam Integer petId) {
		return ResponseEntity.ok(visitService.findByPetId(petId));
	}

	@GetMapping(value = "/visits", params = "vetId")
	public ResponseEntity<List<VisitDTO>> findByVetId(@RequestParam Integer vetId) {
		return ResponseEntity.ok(visitService.findByVetId(vetId));
	}

	@GetMapping("/visits/{id}")
	public ResponseEntity<VisitDTO> findById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(visitService.findById(id));
		} catch (VisitNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/visits/pet/{id}/total-cost")
	public ResponseEntity<BigDecimal> calculateTotalCostByPet(@PathVariable Integer id) {
		return ResponseEntity.ok(visitService.calculateTotalCostByPet(id));
	}
}
