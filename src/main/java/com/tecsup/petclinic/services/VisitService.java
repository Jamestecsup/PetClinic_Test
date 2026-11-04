package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import com.tecsup.petclinic.exceptions.VisitNotFoundException;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.util.List;

public interface VisitService {

    VisitDTO create(VisitDTO visitDTO);

    VisitDTO update(VisitDTO visitDTO);

    List<VisitDTO> findByPetId(Integer petId);

    List<VisitDTO> findByVetId(Integer vetId);

    BigDecimal calculateTotalCostByPet(Integer petId);

    void delete(Integer id) throws VisitNotFoundException;

    VisitDTO findById(Integer id) throws VisitNotFoundException;
=======
import java.util.List;

public interface VisitService {
	List<VisitDTO> findAll();
	VisitDTO findById(Long id) throws VisitNotFoundException;
>>>>>>> Fernando
}
