package woosun.common.cache.component;

import java.util.ArrayList;
import java.util.List;

import woosun.common.cache.domain.CacheSetProperties;

public class CacheHelper {

	private List<CacheSetProperties> cacheSetPropertiesList = new ArrayList<CacheSetProperties>();
	
	public void addProperties(CacheSetProperties cacheSetProperties){
		cacheSetPropertiesList.add(cacheSetProperties);
	}

	public List<CacheSetProperties> getCacheSetPropertiesList() {
		return cacheSetPropertiesList;
	}

	public void setCacheSetPropertiesList(List<CacheSetProperties> cacheSetPropertiesList) {
		this.cacheSetPropertiesList = cacheSetPropertiesList;
	}
	
}
