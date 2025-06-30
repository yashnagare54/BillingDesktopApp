package com.alpha.app.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

//For Dynamic values , not static -- Database 
public class ConfigProperties {

	// For first time execution of program,
	//take DB Url, DB username, DB password dynamically
	private String dbUrl;
	private String username;
	private String password;
		
	//getters and Setters
	

	public String toJson() throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		
		objectNode.put("dbUrl", dbUrl);
		objectNode.put("username", username);
		objectNode.put("password", password);
		
		return mapper.writeValueAsString(objectNode);
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
