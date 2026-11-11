package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Vet;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
import com.tecsup.petclinic.repositories.PetRepository;
import com.tecsup.petclinic.repositories.VetRepository;
import com.tecsup.petclinic.repositories.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class VisitServiceImpl implements VisitService {

	private final VisitRepository visitRepository;
	private final PetRepository petRepository;
	private final VetRepository vetRepository;

	public VisitServiceImpl(VisitRepository visitRepository, PetRepository petRepository,
			VetRepository vetRepository) {
		this.visitRepository = visitRepository;
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
	}

	@Override
	public List<VisitDTO> findAll() {
		return visitRepository.findAll().stream().map(this::toDto).toList();
	}

	@Override
	public VisitDTO findById(Integer id) throws VisitNotFoundException {
		return visitRepository.findById(Long.valueOf(id))
				.map(this::toDto)
				.orElseThrow(() -> new VisitNotFoundException("Visit with id " + id + " not found"));
	}

	@Override
	@Transactional
	public VisitDTO create(VisitDTO visitDTO) {
		Visit visit = new Visit();
		applyDto(visit, visitDTO);
		return toDto(visitRepository.save(visit));
	}

	@Override
	@Transactional
	public VisitDTO update(VisitDTO visitDTO) {
		if (visitDTO.getId() == null) {
			throw new EntityNotFoundException("Visit id is required for update");
		}
		Visit visit = visitRepository.findById(visitDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Visit with id " + visitDTO.getId() + " not found"));
		applyDto(visit, visitDTO);
		return toDto(visitRepository.save(visit));
	}

	@Override
	@Transactional
	public void delete(Integer id) throws VisitNotFoundException {
		Visit visit = visitRepository.findById(Long.valueOf(id))
				.orElseThrow(() -> new VisitNotFoundException("Visit with id " + id + " not found"));
		visitRepository.deleteById(visit.getId());
	}

	@Override
	public List<VisitDTO> findByPetId(Integer petId) {
		return visitRepository.findByPetId(petId).stream().map(this::toDto).toList();
	}

	@Override
	public List<VisitDTO> findByVetId(Integer vetId) {
		return visitRepository.findByVetId(vetId).stream().map(this::toDto).toList();
	}

	@Override
	public BigDecimal calculateTotalCostByPet(Integer petId) {
		return visitRepository.findByPetId(petId).stream()
				.map(Visit::getCost)
				.filter(cost -> cost != null)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private void applyDto(Visit visit, VisitDTO visitDTO) {
		visit.setVisitDate(visitDTO.getVisitDate());
		visit.setDescription(visitDTO.getDescription());
		visit.setCost(visitDTO.getCost());
		visit.setPet(resolvePet(visitDTO.getPetId()));
		visit.setVet(resolveVet(visitDTO.getVetId()));
	}

	private Pet resolvePet(Integer petId) {
		if (petId == null) {
			throw new EntityNotFoundException("Pet id is required");
		}
		return petRepository.findById(petId)
				.orElseThrow(() -> new EntityNotFoundException("Pet with id " + petId + " not found"));
	}

	private Vet resolveVet(Integer vetId) {
		if (vetId == null) {
			throw new EntityNotFoundException("Vet id is required");
		}
		return vetRepository.findById(vetId)
				.orElseThrow(() -> new EntityNotFoundException("Vet with id " + vetId + " not found"));
	}

	private VisitDTO toDto(Visit visit) {
		return VisitDTO.builder()
				.id(visit.getId())
				.petId(visit.getPet() != null ? visit.getPet().getId() : null)
				.vetId(visit.getVet() != null ? visit.getVet().getId() : null)
				.visitDate(visit.getVisitDate())
				.description(visit.getDescription())
				.cost(visit.getCost())
				.build();
	}
}
