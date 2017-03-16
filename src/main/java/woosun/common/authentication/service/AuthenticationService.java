package woosun.common.authentication.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import woosun.common.authentication.domain.AuthenticationSession;

public interface AuthenticationService {

	public <T> AuthenticationSession getSession(HttpServletRequest request, String name, 
			Class<T> originSessionType, String encryptStoreName);
	
	public void addSession(HttpServletResponse response, AuthenticationSession session,
			String encryptStoreName);
	
	public boolean equalSession(AuthenticationSession session1, AuthenticationSession session2);
	
	public void removeSession(AuthenticationSession session, HttpServletRequest request,
			HttpServletResponse response);
	
	public boolean isExpiredSession(AuthenticationSession session, Date now);
}
