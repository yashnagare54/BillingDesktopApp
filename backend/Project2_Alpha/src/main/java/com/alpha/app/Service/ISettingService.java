package com.alpha.app.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.app.DTO.ProjectSettingDTO;
import com.alpha.app.Entity.ProjectSetting;

public interface ISettingService {


	ResponseEntity<?> upsertSetting(String businessName, String businessEmail, String businessAddress,
			String businessGSTNumber, String businessMobile, MultipartFile imageName) throws IOException;

	ProjectSetting getSoftwareSetting();

	ResponseEntity<?> upsertNewMthdSetting(ProjectSettingDTO projectSettingDTO, MultipartFile imageName) throws IOException;

}
