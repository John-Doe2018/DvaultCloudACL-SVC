package com.tranfode.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.tranfode.Constants.ErrorCodeConstants;
import com.tranfode.domain.AuthorizationDetails;
import com.tranfode.util.FileItException;
import com.tranfode.util.UserUtil;

@Provider
public class ACLFilter implements ContainerRequestFilter{

	@Context
	private HttpServletRequest request;
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		String userName=null;
		if(null!=requestContext.getHeaders().get("UserName") && !requestContext.getHeaders().get("UserName").isEmpty()) {
			
			userName=requestContext.getHeaders().get("UserName").get(0);/*getHeaderString("UserName");*/
		}
	
	//	AbstractRequest abstractRequest=(AbstractRequest) request;
		//String userName=abstractRequest.getCustomHeader().getUserName();
		String reqURL=request.getRequestURI();
		UserUtil userUtil=new UserUtil();
		
		try {
			if(!userUtil.isOperationAllowed(userName, reqURL)) {
				Response response=setResponseForUnauthorizedAccess(requestContext, userName);
				requestContext.abortWith(response);
			}
		} catch (FileItException e) {
			Response response=setResponseForUnauthorizedAccess(requestContext, userName);
			requestContext.abortWith(response);
		}
	}
	
    private Response setResponseForUnauthorizedAccess(ContainerRequestContext reqContext, String userName) {
    	AuthorizationDetails authorizationDetails=new AuthorizationDetails();
    	authorizationDetails.setCode(ErrorCodeConstants.ERR_CODE_0100);
		authorizationDetails.setMessage("Access denied./nUser is not athorized for this oeration.");
		
            Response response = Response.status(Response.Status.UNAUTHORIZED)
            		.header("User", userName)
            		.entity(authorizationDetails)
            		.type(MediaType.APPLICATION_JSON).build();
           
        return response;
    }

}
