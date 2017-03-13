package woosun.common.authentication.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import woosun.common.authentication.configuration.AuthenticationProperties;
import woosun.common.authentication.domain.AuthenticationSession;
import woosun.common.convert.component.ObjectConvertComponent;
import woosun.common.encrypt.component.EncryptComponent;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationProperties authenticationProperties;
	
	@Autowired
	private EncryptComponent encryptComponent;
	
	@Autowired
	private ObjectConvertComponent objectConvertComponent;
	
	@Override
	public <T> AuthenticationSession getSession(HttpServletRequest request, String name, Class<T> originSessionType) {
		
		AuthenticationSession authenticationSession = null;
		
		if(request == null || StringUtils.isEmpty(name)){
			return authenticationSession;
		}
		
		if(!AuthenticationSession.class.isAssignableFrom(originSessionType)){
			return authenticationSession;
		}
		
		try{
			
			Cookie cookie[] = request.getCookies();
			if(cookie != null){
				for(Cookie c : cookie){
					if(name.equals(c.getName())){
						T session = getSession(c.getValue(), originSessionType);
						authenticationSession = (AuthenticationSession)session;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return authenticationSession;
	}

	@Override
	public void addSession(HttpServletResponse response, AuthenticationSession session) {
		
		String hash = authenticationProperties.getHash();
		String jsonStr = objectConvertComponent.objectToJson(session);
		String hashCode = null;
		
		if(hash.equals("md5")){
			hashCode = encryptComponent.getMd5Hash(jsonStr);
		}else{
			hashCode = encryptComponent.getSha256Hash(jsonStr);
		}
		
		String encSession = encryptComponent.textEncrypt(hashCode + jsonStr);
		Cookie cookie = new Cookie(session.getSessionName(), encSession);
		cookie.setPath(session.getSessionPath());
		cookie.setDomain(session.getSessionDomain());
		
		if(session.getSessionMaxAge() > -1){
			cookie.setMaxAge(session.getSessionMaxAge());
		}
		
		response.addCookie(cookie);

	}
	
	@Override
	public boolean equalSession(AuthenticationSession session1, AuthenticationSession session2) {
		
		try{
			String sessionStr1 = objectConvertComponent.objectToJson(session1);
			String sessionStr2 = objectConvertComponent.objectToJson(session2);
			
			if(sessionStr1.equals(sessionStr2)){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		
		return false;
	}
	
	@Override
	public void removeSession(AuthenticationSession session, HttpServletRequest request,
			HttpServletResponse response) {

		request.removeAttribute(session.getSessionName());
		
		Cookie cookie = new Cookie(session.getSessionName(), "");
		cookie.setPath("/");
		cookie.setDomain(session.getSessionDomain());
		cookie.setMaxAge(0);
		
		response.addCookie(cookie);
	}

	private <T> T getSession(String cookieValue, Class<T> returnType){
		
		T session = null;
		String hash = authenticationProperties.getHash();
		String decSession = encryptComponent.textDecrypt(cookieValue);
		
		String hashCode = null;
		String jsonStr = null;
		String originHashCode = null;
		
		if(hash.equals("md5")){
			hashCode = decSession.substring(0, 32);
			jsonStr = decSession.substring(32);
			originHashCode = encryptComponent.getMd5Hash(jsonStr);
		}else{
			hashCode = decSession.substring(0, 64);
			jsonStr = decSession.substring(64);
			originHashCode = encryptComponent.getSha256Hash(jsonStr);
		}
		
		if(hashCode.equals(originHashCode)){
			session = objectConvertComponent.jsonToObject(jsonStr, returnType);
		}
		
		return session;
	}
	
}
