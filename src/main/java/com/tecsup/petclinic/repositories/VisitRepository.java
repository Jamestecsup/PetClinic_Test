package com.tecsup.petclinic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsup.petclinic.entities.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

	// Fetch visits by pet id (traviesa la relacion Visit.pet.id)
	List<Visit> findByPetId(Integer petId);

	// Fetch visits by vet id (traviesa la relacion Visit.vet.id)
	List<Visit> findByVetId(Integer vetId);
}
