package woosun.common.http.service;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpConnectionServiceImpl implements HttpConnectionService{
	
	private RestTemplate restTemplate;
	private AsyncRestTemplate asyncRestTemplate;
	
	@PostConstruct
	private void init(){
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		asyncRestTemplate = new AsyncRestTemplate(new HttpComponentsAsyncClientHttpRequestFactory());
	}

	@Override
	public <T> T executeGetHttp(String url, Class<T> responseType) {
		T response = null;
		try {
			response = restTemplate.getForObject(url, responseType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public <T> T executePostHttp(String url, MultiValueMap<String, Object> params, Class<T> responseType) {
		T response = null;
		try {
			response = restTemplate.postForObject(url, params, responseType);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
	
	@Override
	public <T> void asyncExecuteGetHttp(String url, Class<T> responseType) {
		
		try{
			asyncRestTemplate.getForEntity(url, responseType);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public <T> void asyncExecuteGetHttp(String url, Class<T> responseType,
			SuccessCallback<ResponseEntity<T>> successCallback, FailureCallback failCallback) {
		
		try{
			ListenableFuture<ResponseEntity<T>> entity = 
			asyncRestTemplate.getForEntity(url, responseType);
			
			entity.addCallback(successCallback, failCallback);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public <T> void asyncExecutePostHttp(String url, MultiValueMap<String, Object> params, Class<T> responseType) {
		
		try{
			HttpEntity<?> httpEntity = new HttpEntity<Object>(params,null);
			asyncRestTemplate.postForEntity(url, httpEntity, responseType);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public <T> void asyncExecutePostHttp(String url, MultiValueMap<String, Object> params, Class<T> responseType,
			SuccessCallback<ResponseEntity<T>> successCallback, FailureCallback failCallback) {

		try{
			
			HttpEntity<?> httpEntity = new HttpEntity<Object>(params,null);
			
			ListenableFuture<ResponseEntity<T>> entity =
			asyncRestTemplate.postForEntity(url, httpEntity, responseType);
			
			entity.addCallback(successCallback, failCallback);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	
	

}
