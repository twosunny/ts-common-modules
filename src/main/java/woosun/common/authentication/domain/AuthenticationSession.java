package woosun.common.authentication.domain;

import java.util.Date;

public abstract class AuthenticationSession {

	private String sessionName;
	private String sessionDomain;
	private int sessionMaxAge = -1;
	private Date sessionCreateTime;
	private String sessionPath;
	
	public AuthenticationSession(String sessionName, 
			String sessionDomain, 
			String sessionPath, 
			Date sessionCreateTime){
		
		this.sessionName = sessionName;
		this.sessionDomain = sessionDomain;
		this.sessionPath = sessionPath;
		this.sessionCreateTime = sessionCreateTime;
	}
	
	public AuthenticationSession(String sessionName, 
			String sessionDomain, 
			String sessionPath, 
			Date sessionCreateTime,
			int sessionMaxAge){
		
		this.sessionName = sessionName;
		this.sessionDomain = sessionDomain;
		this.sessionPath = sessionPath;
		this.sessionCreateTime = sessionCreateTime;
		this.sessionMaxAge = sessionMaxAge;
		
	}
	
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSessionDomain() {
		return sessionDomain;
	}
	public void setSessionDomain(String sessionDomain) {
		this.sessionDomain = sessionDomain;
	}
	
	public int getSessionMaxAge() {
		return sessionMaxAge;
	}
	public void setSessionMaxAge(int sessionMaxAge) {
		this.sessionMaxAge = sessionMaxAge;
	}
	public Date getSessionCreateTime() {
		return sessionCreateTime;
	}
	public void setSessionCreateTime(Date sessionCreateTime) {
		this.sessionCreateTime = sessionCreateTime;
	}
	public String getSessionPath() {
		return sessionPath;
	}
	public void setSessionPath(String sessionPath) {
		this.sessionPath = sessionPath;
	}
	
	
	
	
}
