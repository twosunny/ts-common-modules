package woosun.common.http.service;

import org.springframework.util.MultiValueMap;

public interface HttpConnectionService {

	public <T> T executeGetHttp(String url, Class<T> responseType);
	
	public <T> T executePostHttp(String url,
			MultiValueMap<String, Object> params, Class<T> responseType);
}
