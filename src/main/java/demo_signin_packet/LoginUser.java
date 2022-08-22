package demo_signin_packet;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class LoginUser {
	
	
	
	private String userID;
	private String password;
	
	
	public LoginUser() {
		
	}

	public LoginUser(String userId, String password) {
		this.userID = userId;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
