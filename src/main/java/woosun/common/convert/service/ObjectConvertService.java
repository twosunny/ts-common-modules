package woosun.common.convert.service;

public interface ObjectConvertService {

	public <T> T jsonToObject(String jsonData, Class<T> returnType);
	
	public String objectToJson(Object obj);
}
