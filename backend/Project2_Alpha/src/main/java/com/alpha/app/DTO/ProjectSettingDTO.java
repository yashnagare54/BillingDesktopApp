package com.alpha.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ProjectSettingDTO {

	private int settingId; // Optional, if you have a settingId
    private String businessName;
    private String businessMobile;
    private String businessEmail;
    private String businessAddress;
    private String businessGSTNumber;
    private String businessLogoImagePath; // Optional for image path
	
    
    public ProjectSettingDTO() {
		// TODO Auto-generated constructor stub
	}
    
    
    
    
    public ProjectSettingDTO(int settingId, String businessName, String businessMobile, String businessEmail,
			String businessAddress, String businessGSTNumber, String businessLogoImagePath) {
		super();
		this.settingId = settingId;
		this.businessName = businessName;
		this.businessMobile = businessMobile;
		this.businessEmail = businessEmail;
		this.businessAddress = businessAddress;
		this.businessGSTNumber = businessGSTNumber;
		this.businessLogoImagePath = businessLogoImagePath;
	}




	public int getSettingId() {
		return settingId;
	}
	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessMobile() {
		return businessMobile;
	}
	public void setBusinessMobile(String businessMobile) {
		this.businessMobile = businessMobile;
	}
	public String getBusinessEmail() {
		return businessEmail;
	}
	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getBusinessGSTNumber() {
		return businessGSTNumber;
	}
	public void setBusinessGSTNumber(String businessGSTNumber) {
		this.businessGSTNumber = businessGSTNumber;
	}
	public String getBusinessLogoImagePath() {
		return businessLogoImagePath;
	}
	public void setBusinessLogoImagePath(String businessLogoImagePath) {
		this.businessLogoImagePath = businessLogoImagePath;
	}
    
    
}
