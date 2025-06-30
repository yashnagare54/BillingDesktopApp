package com.alpha.app.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.ProjectSettingDTO;
import com.alpha.app.Entity.ProjectSetting;
import com.alpha.app.Repositiory.ProjSettingRepositiory;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SettingServiceImpl implements ISettingService {

	
	@Autowired
	private ProjSettingRepositiory settingRepo;
	
	// For Image set
		@Value("${upload.businesslogo}")
		private String folderName;

		@PostConstruct
		public void myInit() {
			System.out.println("in Business Setting  " + folderName);
			// chk of folder exists --o.w create one!
			File path = new File(folderName);
			if (!path.exists()) {
				path.mkdirs();
			} else
				System.out.println("Logo image folder alrdy exists....");
		}


//	@Override
//	public ResponseEntity<?> upsertSetting(String businessName, String businessEmail, String businessAddress,
//			String businessGSTNumber,String businessMobile, MultipartFile imageName) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println("in sett service");
////		List<ProjectSetting> list=settingRepo.findAll();
////		if(list != null)
////		{
////			addSetting( businessName,  businessEmail,  businessAddress,
////					 businessGSTNumber, businessMobile,  imageName);
////			
////		}else
////		{
////			ProjectSetting updt=settingRepo.findById(1).get();
////			
////			if(!businessName != null)
////			{
////				updt.setBusinessName(businessName);
////			}
////			if(!businessEmail != null)
////			{
////				updt.setBusinessEmail(businessEmail);
////			}if(!businessAddress != null)
////			{
////				updt.setBusinessAddress(businessAddress);
////			}
////			if(!businessGSTNumber != null)
////			{
////				updt.setBusinessGSTNumber(businessGSTNumber);
////			}
////			if(!businessMobile != null)
////			{
////				updt.setBusinessMobile(businessMobile);
////			}
////			if(!imageName != null)
////			{
////				Path prodImgPath = Paths.get(folderName+File.separator+updt.getBusinessLogoImagePath());
////				Files.delete(prodImgPath);
////				
////				String targetPath =folderName+File.separator+imageName.getOriginalFilename();
////				Files.copy(imageName.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
////				updt.setBusinessLogoImagePath(targetPath);
////			}
////			settingRepo.save(updt);
////		}
//		
//		
//		
//		return new ResponseEntity<>("Setting Done", HttpStatus.CREATED);
//	}

	@Override
	public ProjectSetting getSoftwareSetting() {
		
		return settingRepo.findFirstByOrderBySettingIdAsc().orElse(null);
	}
	
	
	

	@Override
	public ResponseEntity<?> upsertSetting(String businessName, String businessEmail, String businessAddress,
			String businessGSTNumber, String businessMobile, MultipartFile imageName) throws IOException {
		// TODO Auto-generated method stub
		Optional<ProjectSetting> existingProjSetting = settingRepo.findFirstByOrderBySettingIdAsc();
		if(existingProjSetting.isPresent())
		{
			
			ProjectSetting updt = existingProjSetting.get();
			System.out.println("Inside if blk at first \n"+updt);
			if(businessName != null) updt.setBusinessName(businessName);
			if(businessEmail != null) updt.setBusinessEmail(businessEmail);
			if(businessAddress != null) updt.setBusinessAddress(businessAddress);
			if(businessGSTNumber != null) updt.setBusinessGSTNumber(businessGSTNumber);
			if(businessMobile != null) updt.setBusinessMobile(businessMobile);
			if(imageName !=null)
			{
				Path prodImgPath = Paths.get(folderName+File.separator+updt.getBusinessLogoImagePath());
			Files.delete(prodImgPath);
				
			String targetPath =folderName+File.separator+imageName.getOriginalFilename();
			Files.copy(imageName.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
			updt.setBusinessLogoImagePath(targetPath);
			}
			settingRepo.save(updt);
			
		}
		else
		{
			ProjectSetting proj = new ProjectSetting();
			
			proj.setBusinessName(businessName);
			proj.setBusinessEmail(businessEmail);
			proj.setBusinessAddress(businessAddress);
			proj.setBusinessGSTNumber(businessGSTNumber);
			proj.setBusinessMobile(businessMobile);
			String targetPath =folderName+File.separator+imageName.getOriginalFilename();
			Files.copy(imageName.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
			proj.setBusinessLogoImagePath(targetPath);
			System.out.println("Inside else blk at end \n"+proj);
			settingRepo.save(proj);
			
		}
		return new ResponseEntity<>("Setting Done", HttpStatus.CREATED);
	}


	@Override
	public ResponseEntity<?> upsertNewMthdSetting(ProjectSettingDTO projectSettingDTO, MultipartFile imageName) throws IOException {
		// TODO Auto-generated method stub
		Optional<ProjectSetting> existingProjSetting = settingRepo.findFirstByOrderBySettingIdAsc();
        if (existingProjSetting.isPresent()) {
            ProjectSetting updt = existingProjSetting.get();
            if (projectSettingDTO.getBusinessName() != null) updt.setBusinessName(projectSettingDTO.getBusinessName());
            if (projectSettingDTO.getBusinessEmail() != null) updt.setBusinessEmail(projectSettingDTO.getBusinessEmail());
            if (projectSettingDTO.getBusinessAddress() != null) updt.setBusinessAddress(projectSettingDTO.getBusinessAddress());
            if (projectSettingDTO.getBusinessGSTNumber() != null) updt.setBusinessGSTNumber(projectSettingDTO.getBusinessGSTNumber());
            if (projectSettingDTO.getBusinessMobile() != null) updt.setBusinessMobile(projectSettingDTO.getBusinessMobile());
            if (imageName != null && !imageName.isEmpty()) {
                Path prodImgPath = Paths.get(folderName + File.separator + updt.getBusinessLogoImagePath());
                if (Files.exists(prodImgPath)) {
                    Files.delete(prodImgPath);
                }

                String targetPath = folderName + File.separator + imageName.getOriginalFilename();
                Files.copy(imageName.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
//                updt.setBusinessLogoImagePath(imageName.getOriginalFilename());
                updt.upsertImageToProject(imageName.getOriginalFilename());
            }
            settingRepo.save(updt);
        } else {
            ProjectSetting proj = new ProjectSetting();
            proj.setBusinessName(projectSettingDTO.getBusinessName());
            proj.setBusinessEmail(projectSettingDTO.getBusinessEmail());
            proj.setBusinessAddress(projectSettingDTO.getBusinessAddress());
            proj.setBusinessGSTNumber(projectSettingDTO.getBusinessGSTNumber());
            proj.setBusinessMobile(projectSettingDTO.getBusinessMobile());
            if (imageName != null && !imageName.isEmpty()) {
                String targetPath = folderName + File.separator + imageName.getOriginalFilename();
                Files.copy(imageName.getInputStream(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
                proj.upsertImageToProject(imageName.getOriginalFilename());
            }
            settingRepo.save(proj);
        }
		return new ResponseEntity<>("Setting Done", HttpStatus.CREATED);
	}
}
