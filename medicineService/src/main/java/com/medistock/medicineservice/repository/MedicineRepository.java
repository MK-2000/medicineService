package com.medistock.medicineservice.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.medistock.medicineservice.entity.MedicineEntity;

/**
 * The Interface MedicineRepository.
 */
public interface MedicineRepository extends 
		JpaRepository<MedicineEntity,Integer> {

	/**
	 * Find by medicine name.
	 *
	 * @param medicineName the medicineName
	 * @return the Optional
	 */
	Optional<MedicineEntity> findByMedicineName(String medicineName);
	/**
	 * Find by medicine ID.
	 *
	 * @param medicineId the medicineId
	 * @return the Optional
	 */
	Optional<MedicineEntity> findByMedicineId(Integer medicineId);
}