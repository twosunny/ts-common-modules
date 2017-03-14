package woosun.common.http.service;

import javax.annotation.PostConstruct;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpConnectionServiceImpl implements HttpConnectionService{
	
private RestTemplate restTemplate;
	
	@PostConstruct
	private void init(){
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
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

}
