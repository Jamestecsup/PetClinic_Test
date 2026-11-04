package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;
<<<<<<< HEAD
import com.tecsup.petclinic.mappers.VisitMapper;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Override
    public VisitDTO create(VisitDTO visitDTO) {
        Visit visit = visitRepository.save(visitMapper.mapToEntity(visitDTO));
        return visitMapper.mapToDto(visit);
    }

    @Override
    public VisitDTO update(VisitDTO visitDTO) {
        Visit visit = visitRepository.save(visitMapper.mapToEntity(visitDTO));
        return visitMapper.mapToDto(visit);
    }

    @Override
    public List<VisitDTO> findByPetId(Integer petId) {
        return visitRepository.findByPetId(petId).stream()
                .map(visitMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VisitDTO> findByVetId(Integer vetId) {
        return visitRepository.findByVetId(vetId).stream()
                .map(visitMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateTotalCostByPet(Integer petId) {
        return visitRepository.findByPetId(petId).stream()
                .map(Visit::getCost)
                .filter(cost -> cost != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void delete(Integer id) throws VisitNotFoundException {
        findById(id);
        visitRepository.deleteById(id);
    }

    @Override
    public VisitDTO findById(Integer id) throws VisitNotFoundException {
        return visitRepository.findById(id)
                .map(visitMapper::mapToDto)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found: " + id));
    }
=======
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
>>>>>>> Fernando
}
