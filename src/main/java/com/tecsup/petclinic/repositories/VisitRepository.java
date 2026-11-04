package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    List<Visit> findByPetId(Integer petId);

    List<Visit> findByVetId(Integer vetId);
=======
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
>>>>>>> Fernando
}
