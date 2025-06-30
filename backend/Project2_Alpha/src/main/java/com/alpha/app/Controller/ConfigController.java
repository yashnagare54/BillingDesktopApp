package com.alpha.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.app.Entity.ConfigProperties;
import com.alpha.app.Service.ConfigService;

// As per Captain requirement , this is API for dynamic properties will be saved as ".json" file which is named as "dbconfig.json" 
@RestController
@RequestMapping("/configapi")
public class ConfigController {

	@Autowired
	private ConfigService configService;
	
	
	@PostMapping("/configuration")
	ResponseEntity<String> receiveConfigFirstTime(@RequestBody ConfigProperties configProperties)
	{
		try {
			configService.saveConfigToFile(configProperties);
			return ResponseEntity.ok("Configuration saved and applied Successfully.");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving configuration: "+e.getMessage());
		}
	}
	
}
