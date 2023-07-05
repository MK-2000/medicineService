package com.medistock.medicineservice.dto;

import java.time.LocalDate;

/**
 * The Class MedicineDto.
 */
public class MedicineDto {

	/** The medicine id. */
	private Integer medicineId;
	/** The batch code. */
	private Integer batchCode;
	/** The medicine name. */
	private String medicineName;
	/** The medicine type. */
	private String medicineType;
	/** The expiry date. */
	private LocalDate expiryDate;
	/** The purchase price. */
	private Double purchasePrice;
	/** The selling price. */
	private Double sellingPrice;
	/** The manufacturer. */
	private String manufacturer;
	/** The rack. */
	private String rack;
	
	/**
	 * Instantiates a new MedicineDto.
	 */
	public MedicineDto() {
		super();
	}

	/**
	 * @param medicineId , @param batchCode , @param medicineName , @param medicineType ,@param expiryDate
	 * @param purchasePrice, @param sellingPrice, @param manufacturer, @param rack
	 */
	public MedicineDto(
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
		return "MedicineDto [medicineId=" + medicineId + ", batchCode=" + batchCode + ", medicineName=" + medicineName
				+ ", medicineType=" + medicineType + ", expiryDate=" + expiryDate + ", purchasePrice=" + purchasePrice
				+ ", sellingPrice=" + sellingPrice + ", manufacturer=" + manufacturer + ", rack=" + rack + "]";
	}

}

