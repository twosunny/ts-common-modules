package woosun.common.authentication.session.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationServiceImpl implements SessionAuthenticationService {

	@Override
	public Object getAttribute(HttpServletRequest request, String attributeName) {
		return request.getSession().getAttribute(attributeName);
	}

	@Override
	public void addAttribute(HttpServletRequest request, String attributeName, Object attributeValue) {
		request.getSession().setAttribute(attributeName, attributeValue);
	}

	@Override
	public void addAttribute(HttpServletRequest request, String attributeName, Object attributeValue,
			int maxInactiveIntervalInSecond) {
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(maxInactiveIntervalInSecond);
		session.setAttribute(attributeName, attributeValue);
		
	}

	

}
