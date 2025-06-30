package com.alpha.app.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="SettingMaster")
public class ProjectSetting {

//	settingid
//	business_name
//	business_mobile
//	business_email
//	business_address
//	business_gst_number
//	business_logo
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "setting_Id")
	private int settingId;
	
	@Column(name="business_name")
	private String businessName;
	


	@Column(name="business_mobile")
	private String businessMobile;
	

	@Column(name="business_email")
	private String businessEmail;
	
	@Column(name="business_address")
	private String businessAddress;
	
	@Column(name="business_gst_number")
	private String businessGSTNumber;
	
	@Column(name="business_logo")
	private String businessLogoImagePath;
	
	public ProjectSetting() {
		// TODO Auto-generated constructor stub
	}

	public ProjectSetting(int settingId, String businessName, String businessMobile, String businessEmail,
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

	@Override
	public String toString() {
		return "ProjectSetting [settingId=" + settingId + ", businessName=" + businessName + ", businessMobile="
				+ businessMobile + ", businessEmail=" + businessEmail + ", businessAddress=" + businessAddress
				+ ", businessGSTNumber=" + businessGSTNumber + ", businessLogoImagePath=" + businessLogoImagePath + "]";
	}
	
	// Helper method to set image path to project settings
	public String upsertImageToProject(String imagePath)
	{
		this.setBusinessLogoImagePath(imagePath);
		return "Logo set successfully";
	}
	
}
