package com.medistock.medicineservice.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * The Class MedicineEntity.
 */
@Entity
@Table(name = "medicines")
public class MedicineEntity {

	/** The medicine id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="medicine_id")
	private Integer medicineId;
	
	/** The batch code. */
	 @NotNull(message = "Enter a valid batch code.")
	 @Min(value = 1, message = "Batch Code cannot be negative or 0.")
	private Integer batchCode;
	
	/** The medicine name. */
	@Column(name="medicine_name")
	@JsonProperty("medicine_name")
	@Pattern(regexp = "^[a-z A-Z]+$", message = "Only alphabetic characters are allowed")
	private String medicineName;
	
	/** The medicine type. */
	@Pattern(regexp = "^[a-z A-Z]+$", message = "Only alphabetic characters are allowed")
	private String medicineType;
	
	/** The expiry date. */
	@Future
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate expiryDate;
	
	/** The purchase price. */
	 @NotNull(message = "Purchase Price cannot be empty.")
	 @Min(value = 0, message = "Purchase Price cannot be less than 0.")
	private Double purchasePrice;
	
	/** The selling price. */
	@NotNull(message = "Selling Price cannot be empty.")
	@Min(value = 0, message = "Selling Price cannot be less than 0.")
	private Double sellingPrice;
	
	/** The manufacturer. */
	@Pattern(regexp = "^[a-z A-Z]+$", message = "Only alphabetic characters are allowed")
	private String manufacturer;
	
	/** The rack. */
	@Pattern(regexp = "^[a-z A-Z]+$", message = "Only alphabetic characters are allowed")
	private String rack;
	
	/**
	 * Instantiates a new MedicineDto.
	 */
	public MedicineEntity() {
		super();
	}

	/**
	 * @param medicineId
	 * @param batchCode
	 * @param medicineName
	 * @param medicineType
	 * @param expiryDate
	 * @param purchasePrice
	 * @param sellingPrice
	 * @param manufacturer
	 * @param rack
	 */
	public MedicineEntity(
			Integer medicineId, 
			Integer batchCode, 
			String medicineName, 
			String medicineType,
			LocalDate expiryDate, 
			Double purchasePrice, 
			Double sellingPrice, 
			String manufacturer, 
			String rack) {
		super();
		this.medicineId = medicineId;
		this.batchCode = batchCode;
		this.medicineName = medicineName;
		this.medicineType = medicineType;
		this.expiryDate = expiryDate;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.manufacturer = manufacturer;
		this.rack = rack;
	}
	
	/** 
	 * @return String
	 */
	public Integer getMedicineId() {
		return medicineId;
	}

	/** 
	 * @param medicineId
	 */
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	/** 
	 * @return Integer
	 */
	public Integer getBatchCode() {
		return batchCode;
	}

	/** 
	 * @param medicineId
	 */
	public void setBatchCode(Integer batchCode) {
		this.batchCode = batchCode;
	}

	/** 
	 * @return String
	 */
	public String getMedicineName() {
		return medicineName;
	}

	/** 
	 * @param medicineName
	 */
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	/** 
	 * @return String
	 */
	public String getMedicineType() {
		return medicineType;
	}

	/** 
	 * @param medicineType
	 */
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}

	/** 
	 * @return LocalDate
	 */
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	/** 
	 * @param expiryDate
	 */
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	/** 
	 * @return Double
	 */
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	/** 
	 * @param purchasePrice
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	/** 
	 * @return Double
	 */
	public Double getSellingPrice() {
		return sellingPrice;
	}

	/** 
	 * @param sellingPrice
	 */
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	/** 
	 * @return String
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/** 
	 * @param manufacturer
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/** 
	 * @return String
	 */
	public String getRack() {
		return rack;
	}

	/** 
	 * @param rack
	 */
	public void setRack(String rack) {
		this.rack = rack;
	}

	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return "medicine [medicineId=" + medicineId + ", batchCode=" + batchCode + ", medicineName=" + medicineName
				+ ", medicineType=" + medicineType + ", expiryDate=" + expiryDate + ", purchasePrice=" + purchasePrice
				+ ", sellingPrice=" + sellingPrice + ", manufacturer=" + manufacturer + ", rack=" + rack + "]";
	}
	
	
	
}