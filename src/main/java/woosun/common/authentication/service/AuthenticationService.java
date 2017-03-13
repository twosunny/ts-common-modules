package woosun.common.authentication.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import woosun.common.authentication.domain.AuthenticationSession;

public interface AuthenticationService {

	public <T> AuthenticationSession getSession(HttpServletRequest request, String name, Class<T> originSessionType);
	
	public void addSession(HttpServletResponse response, AuthenticationSession session);
	
	public boolean equalsession(AuthenticationSession session1, AuthenticationSession session2);
	
	public void removeSession(AuthenticationSession session, HttpServletRequest request,
			HttpServletResponse response);
}
