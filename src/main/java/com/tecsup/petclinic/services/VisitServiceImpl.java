package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VisitServiceImpl implements VisitService {
	private final VisitRepository visitRepository;

	public VisitServiceImpl(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public List<VisitDTO> findAll() {
		return visitRepository.findAll().stream().map(this::toDto).toList();
	}

	@Override
	public VisitDTO findById(Long id) throws VisitNotFoundException {
		return visitRepository.findById(id)
				.map(this::toDto)
				.orElseThrow(() -> new VisitNotFoundException("Visit not found: " + id));
	}

	private VisitDTO toDto(Visit visit) {
		return VisitDTO.builder()
				.id(visit.getId())
				.petId(visit.getPet() == null ? null : visit.getPet().getId())
				.vetId(visit.getVet() == null ? null : visit.getVet().getId())
				.visitDate(visit.getVisitDate())
				.description(visit.getDescription())
				.cost(visit.getCost())
				.build();
	}
}
