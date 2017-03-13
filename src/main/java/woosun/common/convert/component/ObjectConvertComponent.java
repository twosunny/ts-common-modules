package woosun.common.convert.component;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

@Component
public class ObjectConvertComponent {
	private ObjectMapper jsonMapper = null;

	@PostConstruct
	private void init() {
		jsonMapper = new ObjectMapper();
		jsonMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);// ex)user_no --> userNo
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
	}

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
	
	public String objectToJson(Object obj)
	{
		String jsonString = null;
		try {
			jsonString = jsonMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
}
