package com.cnmc.shoppingbuddy;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

public class User {
	String user_id;	
	
	String setUserId(){
		final Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        // If the session is open, make an API call to get user data
	        // and define a new callback to handle the response
	        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	                // If the response is successful
	                if (session == Session.getActiveSession()) {
	                    if (user != null) {
	                        user_id = user.getId();//user id
	                    }   
	                }   
	            }   
	        }); 
	        Request.executeBatchAsync(request);
	    }
	    return user_id;
	}
}
