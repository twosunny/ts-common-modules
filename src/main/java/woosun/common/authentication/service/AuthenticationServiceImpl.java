package woosun.common.authentication.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import woosun.common.authentication.configuration.AuthenticationProperties;
import woosun.common.authentication.domain.AuthenticationSession;
import woosun.common.convert.service.ObjectConvertService;
import woosun.common.encrypt.component.EncryptComponent;
import woosun.common.encrypt.util.EncryptUtils;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationProperties authenticationProperties;
	
	@Autowired
	private EncryptComponent encryptComponent;
	
	@Autowired
	private ObjectConvertService objectConvertService;
	
	@Override
	public <T> AuthenticationSession getSession(HttpServletRequest request, String name, 
			Class<T> originSessionType, String encryptStoreName) {
		
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
						T session = getSession(c.getValue(), originSessionType, encryptStoreName);
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
	public void addSession(HttpServletResponse response, AuthenticationSession session, String encryptStoreName) {
		
		String hash = authenticationProperties.getHash();
		String jsonStr = objectConvertService.objectToJson(session);
		String hashCode = null;
		
		String encSession = encryptComponent.textEncrypt(encryptStoreName, jsonStr);
		
		if(hash.equals("md5")){
			hashCode = EncryptUtils.getMd5Hash(encSession);
		}else{
			hashCode = EncryptUtils.getSha256Hash(encSession);
		}
		
		Cookie cookie = new Cookie(session.getSessionName(), hashCode + encSession);
		cookie.setPath(session.getSessionPath());
		cookie.setDomain(session.getSessionDomain());
		
		cookie.setMaxAge(session.getSessionMaxAge());
		
		response.addCookie(cookie);

	}
	
	@Override
	public boolean equalSession(AuthenticationSession session1, AuthenticationSession session2) {
		
		try{
			String sessionStr1 = objectConvertService.objectToJson(session1);
			String sessionStr2 = objectConvertService.objectToJson(session2);
			
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

	private <T> T getSession(String cookieValue, Class<T> returnType, String encryptStoreName){
		
		T session = null;
		String hash = authenticationProperties.getHash();
		
		String hashCode = null;
		String encSession = null;
		String originHashCode = null;
		
		if(hash.equals("md5")){
			hashCode = cookieValue.substring(0, 32);
			encSession = cookieValue.substring(32);
			originHashCode = EncryptUtils.getMd5Hash(encSession);
		}else{
			hashCode = cookieValue.substring(0, 64);
			encSession = cookieValue.substring(64);
			originHashCode = EncryptUtils.getSha256Hash(encSession);
		}
		
		if(!hashCode.equals(originHashCode)) return session;
		
		String decSession = encryptComponent.textDecrypt(encryptStoreName, encSession);
		session = objectConvertService.jsonToObject(decSession, returnType);
		
		return session;
	}
	
	@Override
	public boolean isExpiredSession(AuthenticationSession session, Date now){
		
		if(session == null) return true;
		
		int maxAge = session.getSessionMaxAge();
		Date createDt = session.getSessionCreateTime();
		
		if(maxAge == -1){
			return false;
		}
		
		LocalDateTime createTime = 
				LocalDateTime.ofInstant(Instant.ofEpochMilli(createDt.getTime()), ZoneId.systemDefault());
		LocalDateTime afterTime = createTime.plusSeconds(maxAge);
		
		LocalDateTime currentTime = 
				LocalDateTime.ofInstant(Instant.ofEpochMilli(now.getTime()), ZoneId.systemDefault());
		
		return currentTime.isAfter(afterTime);
	}
	
}
