package com.medistock.medicineservice.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.medistock.medicineservice.dto.MedicineDto;
import com.medistock.medicineservice.entity.MedicineEntity;
import com.medistock.medicineservice.exception.MedicineServiceException;
import com.medistock.medicineservice.repository.MedicineRepository;

/**
 * The Class MedicineServiceImpl.
 */
@Service
@Transactional
public class MedicineServiceImpl implements MedicineService{

	public static final Log LOGGER = LogFactory.getLog(MedicineServiceImpl.class);

	/** The medicine repository. */
	@Autowired
	private MedicineRepository medicineRepo;
	
	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;
	
	 /** 
     * @param medicine
     * @return MedicineDto
     * @throws MedicineServiceException
     */
	@Override
	public String addMedicine(MedicineDto medicine) throws MedicineServiceException {
		
		MedicineEntity medicineEntity = modelMapper.map(medicine, MedicineEntity.class); // maps the entity class to dto object recieved.
		Optional<MedicineEntity> fromRepo = medicineRepo.findById(medicine.getMedicineId());
		Optional<MedicineEntity> checkMedicineName = medicineRepo.findByMedicineName(medicine.getMedicineName());
		if(fromRepo.isPresent()) {
			throw new MedicineServiceException("Medicine Already Present. Please provide new details.");
		}
		if(checkMedicineName.isPresent()) {
			throw new MedicineServiceException("MEDICINE NAME ALREADY TAKEN.");
		}
		medicineRepo.save(medicineEntity);
		
		return "SAVED NEW MEDICINE";
	}

	 /** 
     * @param medicine
     * @return MedicineDto
     * @throws MedicineServiceException
     */
	@Override
	public String updateMedicine(MedicineDto medicine) throws MedicineServiceException {
		
		Optional<MedicineEntity> fromRepo = medicineRepo.findById(medicine.getMedicineId());
		MedicineEntity medicineEntity= fromRepo.orElseThrow(() -> new MedicineServiceException("Medicine not found"));
		
		medicineEntity.setBatchCode(medicine.getBatchCode());
		medicineEntity.setMedicineName(medicine.getMedicineName());
		medicineEntity.setMedicineType(medicine.getMedicineType());
		medicineEntity.setExpiryDate(medicine.getExpiryDate());
		medicineEntity.setPurchasePrice(medicine.getPurchasePrice());
		medicineEntity.setSellingPrice(medicine.getSellingPrice());
		medicineEntity.setManufacturer(medicine.getManufacturer());
		medicineEntity.setRack(medicine.getRack());
		
		return "Medicine details UPDATED";
	}

	/** 
     * @param medicineDto
     * @return MedicineDto
     * @throws MedicineServiceException
     */
	@Override
	public void deleteMedicine(int medicineId) throws MedicineServiceException {
		Optional<MedicineEntity> fromRepo = medicineRepo.findById(medicineId);
		MedicineEntity medicine = fromRepo.orElseThrow(() -> new MedicineServiceException("MEDICINE_NOT_FOUND"));
		medicineRepo.delete(medicine);
		
	}


	/** 
     * @param medicineDto
     * @return MedicineDto
     * @throws MedicineServiceException
     */
	@Override
	public MedicineDto getMedicineById(int medicineId) throws MedicineServiceException {
		Optional<MedicineEntity> fromRepo = medicineRepo.findById(medicineId);
		MedicineEntity medicine = fromRepo.orElseThrow(()-> new MedicineServiceException("Medicine with given ID not present"));
		return  modelMapper.map(medicine, MedicineDto.class);
		
	}

	/** 
     * @return List<MedicineDto>
     * @throws MedicineServiceException
     */
	@Override
	public List<MedicineDto> getAllMedicines() throws MedicineServiceException {
		List<MedicineEntity> fromRepo = medicineRepo.findAll();
		List<MedicineDto> medicineDto = new ArrayList<>();
		fromRepo.forEach(medicine -> medicineDto.add(modelMapper.map(medicine,MedicineDto.class )));
		return medicineDto;
	}

	
}