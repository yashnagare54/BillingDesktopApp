package com.alpha.app.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.alpha.app.Entity.ConfigProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

// In Entity --> ConfigProperties
@Service
public class ConfigService {

	private static final String CONFIG_FILE_PATH = "dbconfig.json";
	
	// Add first time configuration
	public void saveConfigToFile(ConfigProperties configProperties) throws JsonProcessingException, IOException
	{
		try(FileWriter fileWriter = new FileWriter(CONFIG_FILE_PATH))
		{
			fileWriter.write(configProperties.toJson());
		}
	}

	// Update file if required
	
	
	
	
}
