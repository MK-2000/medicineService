package com.medistock.medicineservice.service;

import java.util.List;


import com.medistock.medicineservice.dto.MedicineDto;
import com.medistock.medicineservice.exception.MedicineServiceException;


/**
 * The Interface MedicineService.
 */
public interface MedicineService  {

	/**
     * @param medicineDto
     * @return
     * @throws MedicineServiceException
     */
	public String addMedicine(MedicineDto medicine) throws MedicineServiceException;
	
	 /**
     * @param medicineDto
     * @return
     * @throws MedicineServiceException
     */
	public String updateMedicine(MedicineDto medicine)throws MedicineServiceException;
	 
	/**
     * @param medicineDto
     * @return
     * @throws MedicineServiceException
     */
	public void deleteMedicine(int medicineId) throws MedicineServiceException;
	
	/**
	 * @param medicineDto
     * @return
     * @throws MedicineServiceException
     */
	public MedicineDto getMedicineById(int medicineId) throws MedicineServiceException;
	
	/**
     * @return
     * @throws MedicineServiceException
     */
	public List<MedicineDto> getAllMedicines() throws MedicineServiceException;
	
}