package com.medistock.medicineservice;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import com.medistock.medicineservice.controller.MedicineController;
import com.medistock.medicineservice.dto.MedicineDto;
import com.medistock.medicineservice.entity.MedicineEntity;
import com.medistock.medicineservice.exception.MedicineServiceException;
import com.medistock.medicineservice.repository.MedicineRepository;
import com.medistock.medicineservice.service.MedicineService;
import com.medistock.medicineservice.service.MedicineServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicineTests {

private static final Logger LOGGER = LogManager.getLogger(MedicineServiceApplicationTests.class);
	
	@Mock
	MedicineRepository medicineRepository;

	@Mock
	ModelMapper modelMapper;

	@InjectMocks
	MedicineService medicineService = new MedicineServiceImpl();

	public static MedicineDto medicineDTO() {
		MedicineDto medicine = new MedicineDto();
		medicine.setMedicineId(1);
		medicine.setBatchCode(234);
		medicine.setExpiryDate(LocalDate.of(2023, 6, 13));
		medicine.setManufacturer("ABC");
		medicine.setMedicineName("Naproxen");
		medicine.setMedicineType("Ayurvedic");
		medicine.setPurchasePrice(890.3);
		medicine.setRack("Out of Stock");
		medicine.setSellingPrice(234.2);
		
		return medicine;
	}

	public static MedicineEntity medicines() {
		MedicineEntity medicine = new MedicineEntity();
		medicine.setMedicineId(1);
		medicine.setBatchCode(234);
		medicine.setExpiryDate(LocalDate.of(2023, 6, 13));
		medicine.setManufacturer("ABC");
		medicine.setMedicineName("Naproxen");
		medicine.setMedicineType("Ayurvedic");
		medicine.setPurchasePrice(890.3);
		medicine.setRack("Out of Stock");
		medicine.setSellingPrice(234.2);
		
		return medicine;
	}
	
    
	 MedicineEntity m1, m2;
	 MedicineDto m3;
	 
	 @BeforeAll
	    public  static void initBefore() {
	        LOGGER.info("Medicine module testing initialized.");
	    }

	    @BeforeEach
	    public void init() {
	        m1 = new MedicineEntity(11,123,"John", "Doe",LocalDate.now(),234.5,432.5,"Abcs","Out");
	        m2 = new MedicineEntity(12,133,"Kahn", "Doke",LocalDate.now(),24.5,42.5,"Kbcs","Avai");
	        
	        m3 = new MedicineDto(12,133,"Kahn", "Doke",LocalDate.now(),24.5,42.5,"Kbcs","Avai");
	    }

	@Test
	@DisplayName(value = "Check adding new medicine")
	void validMedicineAddition() throws MedicineServiceException {
		MedicineDto medicine = MedicineTests.medicineDTO();
		MedicineEntity med = MedicineTests.medicines();
		
		Mockito.when(modelMapper.map(medicineDTO(),MedicineEntity.class)).thenReturn(medicines());
		Mockito.when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.empty());
		Assertions.assertEquals("SAVED NEW MEDICINE",medicineService.addMedicine(medicine));
		 
		System.out.println(medicine.toString()+" "+med.toString());
        System.out.println(medicine.getMedicineName()+" "+medicine.getMedicineType());
        System.out.println(med.getManufacturer()+" "
                +med.getRack()+" "+
                med.getSellingPrice()+" "+med.getPurchasePrice()+
                med.getBatchCode()+" "+med.getExpiryDate()+" "+med.getMedicineId()+" "+med.getMedicineName()+" "+med.getMedicineType()
                );
	}
	
	@Test
	@DisplayName(value = "Medicine Name Already Taken")
	void testAddMedicineMedicineNameAlreadyTaken() throws MedicineServiceException {
	 
		MedicineDto medicine = MedicineTests.medicineDTO();
	    Optional<MedicineEntity> medicineNameOptional = Optional.of(new MedicineEntity());

	    when(medicineRepository.findByMedicineName("Naproxen")).thenReturn(medicineNameOptional);

	    Assertions.assertThrows(MedicineServiceException.class, () -> medicineService.addMedicine(medicine));
	}


	@Test
	@DisplayName(value = "Check viewing medicine for existing medicine")
	void viewForExistingMedicine() throws MedicineServiceException {
	    int medicineId = 1;
	    MedicineEntity medicineEntity = new MedicineEntity();
	    when(medicineRepository.findById(medicineId)).thenReturn(Optional.of(medicineEntity));
	    when(modelMapper.map(medicineEntity, MedicineDto.class)).thenReturn(new MedicineDto());
	    MedicineDto result = medicineService.getMedicineById(medicineId);
	    assertNotNull(result);
	}

	@Test
	@DisplayName(value = "check adding for adding a duplicate medicine")
	void invalidMedicineAddition() throws MedicineServiceException {
		MedicineDto medicine = MedicineTests.medicineDTO();
		Mockito.when(modelMapper.map(medicineDTO(), MedicineEntity.class)).thenReturn(medicines());
		Mockito.when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.of(medicines()));
		MedicineServiceException ex = Assertions.assertThrows(MedicineServiceException.class, () -> medicineService.addMedicine(medicine));
		Assertions.assertEquals("Medicine Already Present. Please provide new details.",ex.getMessage());
	}


	@Test
	@DisplayName(value = "Check Updating Exisisting medicine")
	void validMedicineUpdate() throws MedicineServiceException {
		MedicineDto medicine = MedicineTests.medicineDTO();

		Mockito.when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.of(medicines()));
		Assertions.assertEquals("Medicine details UPDATED",medicineService.updateMedicine(medicine));
	}

	@Test
	@DisplayName(value = "Check Updating non-Existing medicine")
	void invalidMedicineUpdate() throws MedicineServiceException {
		MedicineDto medicine = MedicineTests.medicineDTO();

		Mockito.when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.empty());
		MedicineServiceException ex = Assertions.assertThrows(MedicineServiceException.class, () -> medicineService.updateMedicine(medicine));
		Assertions.assertEquals("Medicine not found",ex.getMessage());
	}
	
   @Test
    @DisplayName(value = "Check viewing medicine for non-existing medicine")
    void viewForNonExistingMedicine() throws MedicineServiceException{
	  
	   MedicineDto medicine = MedicineTests.medicineDTO();
	   MedicineServiceException e = Assertions.assertThrows(MedicineServiceException.class, ()->medicineService.getMedicineById(medicine.getMedicineId()));
       Assertions.assertEquals("Medicine with given ID not present", e.getMessage());
	   
    }
   
   @Test
  	@DisplayName(value = "Check viewing All existing medicines")
    void viewForAllExistingMedicine() throws MedicineServiceException {
	   List<MedicineEntity> med = new ArrayList<>();
       med.add(m1);
       med.add(m2);
       when(medicineRepository.findAll()).thenReturn(med);
       assertEquals(med.size(), medicineService.getAllMedicines().size());
	}
   
   @Test
	@DisplayName(value = "Check viewing All Non-existing medicines")
	   void viewForAllNonExistingMedicine() throws MedicineServiceException{
	     
       List<MedicineDto> allMedicines = medicineService.getAllMedicines();
       Assertions.assertTrue(allMedicines.isEmpty());
	   }

   @Test
   @DisplayName(value = "Delete existing medicine")
	void validDeleteMedicine() throws MedicineServiceException{
		MedicineEntity medicine = MedicineTests.medicines();
		Mockito.when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.of(medicine));
		Assertions.assertDoesNotThrow(()-> medicineService.deleteMedicine(medicine.getMedicineId()));
	}
	 
        
	   @Test
	   @DisplayName(value = "check deleting non-existing Medicine")
       void deleteNonExistingMedicine() throws MedicineServiceException{
		   
		   MedicineServiceException e = Assertions.assertThrows(MedicineServiceException.class,
				 	() -> medicineService.deleteMedicine(2));
			Assertions.assertEquals("MEDICINE_NOT_FOUND", e.getMessage());
	   }
	   
		    @Mock
		    private MedicineService medicineService1;

		    @InjectMocks
		    private MedicineController medicineController;

		    @BeforeEach
		    public void setup() {
		        MockitoAnnotations.openMocks(this);
		    }

		    @Test
		    @DisplayName(value = "Add Medicine")
		    void testAddMedicine() throws MedicineServiceException {
		    	
		        MedicineDto medicineDto = new MedicineDto();
		        when(medicineService1.addMedicine(medicineDto)).thenReturn("SAVED NEW MEDICINE");

		        ResponseEntity<String> response = medicineController.addMedicine(medicineDto);

		        verify(medicineService1, times(1)).addMedicine(medicineDto);
		        assertEquals(HttpStatus.CREATED, response.getStatusCode());
		        assertEquals("SAVED NEW MEDICINE", response.getBody());
		    }

		    @Test
		    @DisplayName(value = "Update Medicine")
		    void testUpdateMedicine() throws MedicineServiceException {
		        
		        MedicineDto medicineDto = new MedicineDto();
		        when(medicineService1.updateMedicine(medicineDto)).thenReturn("Medicine details UPDATED");

		      	ResponseEntity<String> response = medicineController.updateMedicine(medicineDto);

		        verify(medicineService1, times(1)).updateMedicine(medicineDto);
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        assertEquals("Medicine details UPDATED", response.getBody());
		    }

		    @Test
		    @DisplayName(value = "Delete Medicine")
		    void testDeleteMedicine() throws MedicineServiceException {
		      
		        medicineController.deleteMedicine(1);
		        verify(medicineService1, times(1)).deleteMedicine(1);
		    }

		    @Test
		    @DisplayName(value = "Get By Medicine Id")
		    void testGetById() throws MedicineServiceException {
		       
		        MedicineDto medicineDto = new MedicineDto();
		        when(medicineService1.getMedicineById(1)).thenReturn(medicineDto);

		        ResponseEntity<MedicineDto> response = medicineController.getById(1);

		        verify(medicineService1, times(1)).getMedicineById(1);
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        assertEquals(medicineDto, response.getBody());
		    }

		    @Test
		    @DisplayName(value = "Get All Medicines")
		    void testGetAllMedicinesList() throws MedicineServiceException {
		        
		        List<MedicineDto> medicineDtoList = new ArrayList<>();
		        when(medicineService1.getAllMedicines()).thenReturn(medicineDtoList);

		        ResponseEntity<List<MedicineDto>> response = medicineController.getAllMedicinesList();

		        verify(medicineService1, times(1)).getAllMedicines();
		        assertEquals(HttpStatus.OK, response.getStatusCode());
		        assertEquals(medicineDtoList, response.getBody());
		    }
	   
	   
	   @Test
		@DisplayName(value = "Medicine Service Application")
		   void testApplicationStartsSuccessfully() {
		        assertDoesNotThrow(() -> MedicineserviceApplication.main(new String[]{}));
		    }
}
