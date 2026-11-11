package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface VisitService {

	List<VisitDTO> findAll();

	VisitDTO findById(Integer id) throws VisitNotFoundException;

	VisitDTO create(VisitDTO visitDTO);

	VisitDTO update(VisitDTO visitDTO);

	void delete(Integer id) throws VisitNotFoundException;

	List<VisitDTO> findByPetId(Integer petId);

	List<VisitDTO> findByVetId(Integer vetId);

	BigDecimal calculateTotalCostByPet(Integer petId);
}
