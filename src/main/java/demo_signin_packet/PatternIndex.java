package demo_signin_packet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;




@ManagedBean
@SessionScoped
@FacesValidator
public  class PatternIndex {

	
	private String name;
	private String mobile;
	private String email;
	private String msg;
	
	
	
	public PatternIndex() {
		
	}

	
	//Getter and Setter methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean save() {
		int result =0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodata", "root", "Malko08$@");
			PreparedStatement ps = conn.prepareStatement("insert into contact_form (name, mobile, email, message) values (?,?,?,?);");
			ps.setString(1, this.getName());
			ps.setString(2, this.getMobile());
			ps.setString(3, this.getEmail());
			ps.setString(4, this.getMsg());
			result = ps.executeUpdate();
			System.out.println("successfull");
		}catch(Exception e) {
			System.out.println(e);
		}
		
		if(result == 1) {
			return true;
		}else return false;
		
	}
	
	

	//ACtion method for validation
	
		public String loginAction() {
			
//			 String redirect = null;
			boolean n = false;
			
			 
			if((mobile.isEmpty() && email.isEmpty()) && (!name.isEmpty())) {
				 n = validateName(name);
				if(!msg.isEmpty()) {
					n=validateMsg(msg);
				}
				if(n==true && this.save()) {
					return "success-resul.xhtml";
					
				} else return "index.xhtml";
//				if(n==true) {
//					
//					return "success-result";
//				} else {
//					return "index";
//				}
//				 return "index";
			}
			else if((!email.isEmpty())) {
				n= validateEmail(email);
				if((!name.isEmpty())) {
					n= validateName(name);	
				}
				if(!mobile.isEmpty()) {
					n=validateMobile(mobile);
				}
				if(!msg.isEmpty()) {
					n=validateMsg(msg);
				}
				
				if(n==true && this.save()) {
					return "success-result";
				} else {
					return "index";
				}
			}
			
			else if((email.isEmpty() && (!mobile.isEmpty())) && (!name.isEmpty())) {	
				validateName(name);
			    n=validateMobile(mobile);
//			    validateEmail(email);
			     n=validateEmail2(email);
			    
				if(!msg.isEmpty()) {
					n=validateMsg(msg); 
				}
				if(n==true && this.save()) {
					return "success-result";
				} else {
					return "index";
				}
//				return "index";
			} 
//			else if((mobile.isEmpty() && (!email.isEmpty()) && (!name.isEmpty()))){
//                validateName(name);
//				validateEmail(email);
//				return "success-result";
//		    } 
			else if((!email.isEmpty() && (mobile.isEmpty()) && (!name.isEmpty())) ) {
				
				n=validateEmail(email);
//				validateMobile(mobile);
				validateName(name);
				if(!msg.isEmpty()) {
					n=validateMsg(msg); 
				}
				if(n==true && this.save()) {
					return "success-result";
				} else {
					return "index";
				}
//				return "success-result";
			} 
             else if((!email.isEmpty() && (!mobile.isEmpty()) && (!name.isEmpty())) ) {
				
				n=validateEmail(email);
				n=validateMobile(mobile);
				n=validateName(name);
				if(!msg.isEmpty()) {
					n=validateMsg(msg);
				}
				if(n==true && this.save()) {
					return "success-result";
				} else {
					return "index";
				}
//				return "index";
			} 
			
//			else if((!name.isEmpty()) && (!mobile.isEmpty()) && (!email.isEmpty()) ){
//				validateName(name);
//				validateMobile(mobile);
//				n=validateEmail(email);
////				validateMsg(msg);
//				if(!msg.isEmpty()) {
//					n=validateMsg(msg);
//				}
//				if(n==true) {
//					return "success-result";
//				} else {
//					return "index";
//				}
////				return "index";
//			}
			
			
			return "success-result";	
		}


		private boolean validateEmail(String email) {
			boolean b = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String emailRegex = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
			Pattern pt = Pattern.compile(emailRegex);
			if(pt.matcher(email).matches()) { 
				FacesContext.getCurrentInstance().addMessage("main-box:email", new FacesMessage(""));
				return true;
		    }
			else {
				facesContext.addMessage("main-box:email", new FacesMessage("Email should be in format"));
				return b;
			}	
		}
		
		private boolean validateEmail2(String email) {
			boolean b = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String emailRegex = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
			Pattern pt = Pattern.compile(emailRegex);
			if(pt.matcher(email).matches()) { 
				FacesContext.getCurrentInstance().addMessage("main-box:email", new FacesMessage(""));
				return true;
		    }
			else {
				facesContext.addMessage("main-box:email", new FacesMessage("Email is mandatory"));
				return b;
			}	
		}
		
		
		
		
		private boolean validateMsg(String msg) {
			boolean b = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String msgRegex = "[a-zA-Z]{4}[0-9]{3}[a-zA-Z0-9_!@#$%\\^\\*\\.\\/\\,\\>\\<\\&[\"]\\-\\+\\?`~()=;:']{5,10}";
			Pattern ptd = Pattern.compile(msgRegex);
			if(ptd.matcher(msg).matches()) {
				facesContext.addMessage("main-box:msg", new FacesMessage(""));
				return true;
				}
			else {
				facesContext.addMessage("main-box:msg", new FacesMessage("Pattern does not match"));
				return b;
			}
		}
		
		
		private boolean validateMobile(String mobile) {
			boolean b = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String mobRegex = "[6789][0-9]{9}";
			Pattern ptm = Pattern.compile(mobRegex);
			if(ptm.matcher(mobile).matches()) { 
				facesContext.addMessage("main-box:mobile", new FacesMessage(""));
				return true;
			}else {
				facesContext.addMessage("main-box:mobile", new FacesMessage("Mobile should be 10 digits "));
				return b;
			}	
		}
		
		private boolean validateName(String name) {   
			boolean b = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String nameRegex = "[a-zA-Z\\s]+";
			Pattern pt = Pattern.compile(nameRegex);
			if(pt.matcher(name).matches()) {
				facesContext.addMessage("main-box:name", new FacesMessage(""));	
				return true;
			}else {
				facesContext.addMessage("main-box:name", new FacesMessage("Name should be alphabet"));
				System.out.println("invalid");
				return b;
			}
			
		}


		


		

		
	
}
