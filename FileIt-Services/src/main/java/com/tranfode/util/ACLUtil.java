package com.tranfode.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ACLUtil {
 
	
	public JSONObject getAllRoles() throws FileItException, Exception {
		JSONObject rolesObject=null;
		String aclFilePath = "Security/ACLData.JSON";
		CloudFilesOperationUtil cloudOperationUtil = new CloudFilesOperationUtil();
		InputStream oInputStream1 = cloudOperationUtil.getFIleInputStream(aclFilePath);
		JSONParser parser = new JSONParser(); 
		JSONObject aclObject = (JSONObject) parser.parse(new InputStreamReader(oInputStream1));
		rolesObject= ((JSONObject)aclObject.get("Roles"));
		return rolesObject;
	}

	public JSONObject getAccessList(String role) throws FileItException, IOException, ParseException {
		JSONObject actionObject=new JSONObject();
		String aclFilePath = "Security/ACLData.JSON";
		CloudFilesOperationUtil cloudOperationUtil = new CloudFilesOperationUtil();
		InputStream oInputStream1 = cloudOperationUtil.getFIleInputStream(aclFilePath);
		JSONParser parser = new JSONParser(); 
		JSONObject aclObject = (JSONObject) parser.parse(new InputStreamReader(oInputStream1));
		JSONObject allActions= ((JSONObject)aclObject.get("Actions"));
		ArrayList<String> permittedActionList=new ArrayList<>();
		permittedActionList=(ArrayList<String>) ((JSONObject)aclObject.get("RoleActionMap")).get(role);
		for (String actionId : permittedActionList) {
			actionObject.put(actionId, allActions.get(actionId));
		}
		return actionObject;
	}

	public JSONObject getAllGroups() throws FileItException, IOException, ParseException {
		JSONObject groupObject=null;
		String aclFilePath = "Security/ACLData.JSON";
		CloudFilesOperationUtil cloudOperationUtil = new CloudFilesOperationUtil();
		InputStream oInputStream1 = cloudOperationUtil.getFIleInputStream(aclFilePath);
		JSONParser parser = new JSONParser(); 
		JSONObject aclObject = (JSONObject) parser.parse(new InputStreamReader(oInputStream1));
		groupObject= ((JSONObject)aclObject.get("Groups"));
		return groupObject;
	}
}
