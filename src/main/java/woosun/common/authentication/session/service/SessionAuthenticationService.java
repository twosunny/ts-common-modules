package woosun.common.authentication.session.service;

import javax.servlet.http.HttpServletRequest;

public interface SessionAuthenticationService {
	
	public Object getAttribute(HttpServletRequest request, String attributeName);
	
	public void addAttribute(HttpServletRequest request, String attributeName, Object attributeValue);
	
	public void addAttribute(HttpServletRequest request, String attributeName, Object attributeValue, int maxInactiveIntervalInSecond);
}
