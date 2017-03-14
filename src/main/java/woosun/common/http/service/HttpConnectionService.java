package woosun.common.http.service;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.SuccessCallback;

public interface HttpConnectionService {

	public <T> T executeGetHttp(String url, Class<T> responseType);
	
	public <T> T executePostHttp(String url,
			MultiValueMap<String, Object> params, Class<T> responseType);
	
	public <T> void asyncExecuteGetHttp(String url, Class<T> responseType);
	
	public <T> void asyncExecuteGetHttp(String url, Class<T> responseType, 
			SuccessCallback<ResponseEntity<T>> successCallback,
			FailureCallback failCallback);
	
	public <T> void asyncExecutePostHttp(String url,
			MultiValueMap<String, Object> params, Class<T> responseType);
	
	public <T> void asyncExecutePostHttp(String url,
			MultiValueMap<String, Object> params, Class<T> responseType,
			SuccessCallback<ResponseEntity<T>> successCallback,
			FailureCallback failCallback);
}
