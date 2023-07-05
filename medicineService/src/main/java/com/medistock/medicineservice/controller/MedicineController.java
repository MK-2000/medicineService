package com.medistock.medicineservice.controller;

 
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medistock.medicineservice.dto.MedicineDto;
import com.medistock.medicineservice.exception.MedicineServiceException;
import com.medistock.medicineservice.service.MedicineService;
 
@RestController
@RequestMapping("/medicine")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
/**
 * The Class MedicineController.
 */
public class MedicineController {

	public static final Log LOGGER = LogFactory.getLog(MedicineController.class);	
	
	@Autowired
	/** The medicine service. */
	MedicineService medicineService;
	
	@Autowired
	/** The environment. */
	Environment environment;
	
	@PostMapping("/add-medicine")
	/** 
     * @param newMedicineDto
     * @return ResponseEntity<String>
     * @throws MedicineServiceException
     */
	public ResponseEntity<String> addMedicine(@RequestBody MedicineDto medicineDto)throws MedicineServiceException{
		
		String add = medicineService.addMedicine(medicineDto);
		return new ResponseEntity<>(add,HttpStatus.CREATED);
	}
	
	@PutMapping("/update-medicine")
	/** 
     * @param newMedicineDto
     * @return ResponseEntity<String>
     * @throws MedicineServiceException
     */
	public ResponseEntity<String> updateMedicine(@RequestBody MedicineDto medicineDto)throws MedicineServiceException{
		String ret = medicineService.updateMedicine(medicineDto);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMedicine/{medicineId}")
	/** 
     * @param medicineId
     * @throws MedicineServiceException
     */
	public void deleteMedicine(@PathVariable int medicineId) throws MedicineServiceException{
		medicineService.deleteMedicine(medicineId);
	}
	
	@GetMapping("getMedicine/{medicineId}")
	/** 
     * @param inputMedicineDto
     * @return ResponseEntity<MedicineDto>
     * @throws MedicineServiceException
     */
	public ResponseEntity<MedicineDto> getById(@PathVariable int medicineId) throws MedicineServiceException{
		MedicineDto res = medicineService.getMedicineById(medicineId);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("all-medicines")
	/** 
     * @return ResponseEntity<List<MedicineDto>>
     * @throws MedicineServiceException
     */
	public ResponseEntity<List<MedicineDto>> getAllMedicinesList()throws MedicineServiceException{
		List<MedicineDto> medicines = medicineService.getAllMedicines();
		return new ResponseEntity<>(medicines, HttpStatus.OK);
	}
}

