package woosun.common.convert.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Service
public class ObjectConvertServiceImpl implements ObjectConvertService {
	
	private ObjectMapper jsonMapper = null;

	@PostConstruct
	private void init() {
		jsonMapper = new ObjectMapper();
		jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);// ex)user_no --> userNo
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	}

	@Override
	public <T> T jsonToObject(String jsonData, Class<T> returnType) {
		T data = null;

		try {
			data = jsonMapper.readValue(jsonData, returnType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return data;
	}

	@Override
	public String objectToJson(Object obj) {
		String jsonString = null;
		try {
			jsonString = jsonMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}

}
