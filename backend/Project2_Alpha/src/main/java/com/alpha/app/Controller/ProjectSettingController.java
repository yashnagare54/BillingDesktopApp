package com.alpha.app.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.ProjectSettingDTO;
import com.alpha.app.Entity.ProjectSetting;
import com.alpha.app.Service.ISettingService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/settings")
public class ProjectSettingController {

	
	@Autowired
	private ISettingService settingService;
	
	
	
//	@PostMapping(value = "/upsert", consumes ={"multipart/form-data"})
//	ResponseEntity<?> addSoftwareSetting(@RequestParam(value = "businessName",required = false) String businessName, @RequestParam(value ="businessMobile",required = false)String businessMobile,
//			@RequestParam (value ="businessEmail",required = false)String businessEmail,@RequestParam(value ="businessAddress",required = false)String businessAddress,
//			@RequestParam(value ="businessGSTNumber",required = false)String businessGSTNumber,
//			@RequestParam(value ="businessLogoImagePath",required = false) MultipartFile imageName) throws IOException
//	{
//		System.out.println("in sett cont");
//		return settingService.upsertSetting(businessName,businessMobile,businessEmail,businessAddress,businessGSTNumber,imageName);
//	}
	
	@GetMapping
	ResponseEntity<?> getSoftwareSetting()
	{
		ProjectSetting proj = settingService.getSoftwareSetting();
		if(proj != null)
		{
			return new ResponseEntity<>(proj,HttpStatus.OK);
		}
		return new ResponseEntity<>("Please fill software information",HttpStatus.BAD_GATEWAY);
		
	}
	
	@PostMapping(value = "/upsert", consumes ={"multipart/form-data"})
	public ResponseEntity<?> upsertProject(@RequestPart("projectSetting") ProjectSettingDTO projectSettingDTO,
			@RequestParam(value ="businessLogoImagePath",required = false) MultipartFile imageName)throws IOException
	{
		return settingService.upsertNewMthdSetting(projectSettingDTO, imageName);
	}
}
