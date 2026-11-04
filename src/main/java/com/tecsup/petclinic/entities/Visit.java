package com.tecsup.petclinic.entities;

<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
>>>>>>> Fernando

import java.math.BigDecimal;
import java.time.LocalDate;

<<<<<<< HEAD
@Entity(name = "visits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_id", nullable = false)
    private Integer petId;

    @Column(name = "vet_id")
    private Integer vetId;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    private String description;

    private BigDecimal cost;
=======
/**
 * 
 * @author jgomezm
 *
 */
@NoArgsConstructor
@Entity(name = "visits")
@Data
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "visit_date")
	private LocalDate visitDate;

	@Column(name = "description")
	private String description;

	@Column(name = "cost")
	private BigDecimal cost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pet_id")
	@ToString.Exclude
	private Pet pet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vet_id")
	@ToString.Exclude
	private Vet vet;
>>>>>>> Fernando
}
