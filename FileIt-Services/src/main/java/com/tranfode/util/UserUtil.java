package com.tranfode.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tranfode.Constants.CloudFileConstants;
import com.tranfode.domain.ActiveUser;
import com.tranfode.domain.ActiveUsers;
import com.tranfode.domain.User;
import com.tranfode.domain.Users;

public class UserUtil {

	public void updateUserLoginDetails(String userName, String status)  {
		String filePath=CloudFileConstants.ACTIVEUSERXML;
		ActiveUser activeUser;
		List<ActiveUser> userList;
		ActiveUsers activeUsers =new ActiveUsers();
		try {
			JAXBContext context = JAXBContext.newInstance(ActiveUsers.class);
			Unmarshaller un = context.createUnmarshaller();
			Marshaller m = context.createMarshaller();
			CloudFilesOperationUtil cloudFilesOperationUtil=new CloudFilesOperationUtil();
			try {
				InputStream oInputStream = cloudFilesOperationUtil.getFIleInputStream(filePath);
				if(null!=oInputStream ) {
					activeUsers = (ActiveUsers) un.unmarshal(new InputStreamReader(oInputStream));
					if(null!= activeUsers && null!=activeUsers.getActiveUserList() && !activeUsers.getActiveUserList().isEmpty()) {
						userList=activeUsers.getActiveUserList();
						//Iterator<ActiveUser> iterator=userList.iterator();
						boolean found=false;
						for (ActiveUser user : userList) {
							if(userName.equals(user.getUserName())) {
								if(CloudFileConstants.ACTIVE.equalsIgnoreCase(status)) {
									user.setLoginTime(new Date());
								}
								user.setStatus(status);
								found=true;
								break;
							}
						}
						/*while(iterator.hasNext()) {
							if(userName.equals(iterator.next().getUserName())) {
								iterator.setStatus(status);
								if(CloudFileConstants.ACTIVE.equalsIgnoreCase(status)) {
									iterator.next().setLoginTime(new Date());
								}
								found=true;
								break;
							}

						}*/
						if(!found) {
							String uniqueID = UUID.randomUUID().toString();
							activeUser=new ActiveUser(userName, uniqueID, status, new Date());
							userList.add(activeUser);
							activeUsers.setActiveUserList(userList);
						}
					}else {
						activeUsers =new ActiveUsers();
						userList=new ArrayList<ActiveUser>();
						String uniqueID = UUID.randomUUID().toString();
						activeUser=new ActiveUser(userName, uniqueID, status, new Date());
						userList.add(activeUser);
						activeUsers.setActiveUserList(userList);
					}
				}else {
					activeUsers =new ActiveUsers();
					userList=new ArrayList<ActiveUser>();
					String uniqueID = UUID.randomUUID().toString();
					activeUser=new ActiveUser(userName, uniqueID, status, new Date());
					userList.add(activeUser);
					activeUsers.setActiveUserList(userList);
				}
			} catch (FileItException e) {
				activeUsers =new ActiveUsers();
				userList=new ArrayList<ActiveUser>();
				String uniqueID = UUID.randomUUID().toString();
				activeUser=new ActiveUser(userName, uniqueID, status, new Date());
				userList.add(activeUser);
				activeUsers.setActiveUserList(userList);
			}
			
			
			StringWriter sw = new StringWriter();
			m.marshal(activeUsers, sw);
			String xmlString = sw.toString();
			InputStream is = new ByteArrayInputStream(xmlString.getBytes());
			CloudStorageConfig.getInstance().uploadFile(CloudPropertiesReader.getInstance().getString("bucket.name"),
					filePath, is, CloudFileConstants.XMLFILETYPE);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileItException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}



	public boolean isOperationAllowed(String userName, String resource) throws FileItException
	{
		boolean allowedFlag=false;
		String role=null;
		CloudFilesOperationUtil cloudOperationUtil = new CloudFilesOperationUtil();
		String userFilePath = "Security/userDetailsRepo.xml";
		Unmarshaller un;
		Users userList = null;
		try {
			User user=getUserDetails(userName);
			role=user.getRole();
			String aclFilePath = "Security/ACLData.JSON";
			InputStream oInputStream1 = cloudOperationUtil.getFIleInputStream(aclFilePath);
			
			JSONParser parser = new JSONParser(); 
			JSONObject aclObject = (JSONObject) parser.parse(new InputStreamReader(oInputStream1));
			ArrayList<String> actionList=new ArrayList<>();
			actionList=(ArrayList<String>) ((JSONObject)aclObject.get("RoleActionMap")).get(role);
			//Need to change the logic to get the resource Id
			String resourceId=null;
			JSONObject resourceObject=(JSONObject)aclObject.get("Resources");
			Set keySet=resourceObject.keySet();  
			for (Object key : keySet) {
				if(resource.endsWith(resourceObject.get(key).toString())) {
					resourceId=key.toString();
					break;
				}
			}
			String actionId=((JSONObject)aclObject.get("ResourceActionMap")).get(resourceId).toString();
			for (String  action : actionList) {
				if(action.equalsIgnoreCase(actionId)) {
					allowedFlag=true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allowedFlag;
	}
	
	
	public User getUserDetails(String userName)
	{
		User user=null;
		CloudFilesOperationUtil cloudOperationUtil = new CloudFilesOperationUtil();
		String userFilePath = "Security/userDetailsRepo.xml";
		Unmarshaller un;
		try {
			InputStream oInputStream = cloudOperationUtil.getFIleInputStream(userFilePath);
			JAXBContext context = JAXBContext.newInstance(Users.class);
			un = context.createUnmarshaller();
			Users userList = (Users) un.unmarshal(new InputStreamReader(oInputStream));

			for (User userTemp : userList.getUsers()) {
				if (userName.equals(userTemp.getUserName())) {
					user= userTemp;
				}
			}
		} catch (FileItException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
