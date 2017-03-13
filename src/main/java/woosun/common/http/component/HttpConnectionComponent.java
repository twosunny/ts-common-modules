package woosun.common.http.component;

import javax.annotation.PostConstruct;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpConnectionComponent {
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	private void init(){
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	}

	public <T> T executeGetHttp(String url, Class<T> responseType) {
		T response = null;
		try {
			response = restTemplate.getForObject(url, responseType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public <T> T executePostHttp(String url,
			MultiValueMap<String, Object> params, Class<T> responseType) {
		T response = null;
		try {
			response = restTemplate.postForObject(url, params, responseType);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}
}
