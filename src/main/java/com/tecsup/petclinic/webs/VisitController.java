package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.services.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/visits/{id}")
	public ResponseEntity<VisitDTO> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(visitService.findById(id));
		} catch (VisitNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
