package woosun.common.cache.component;

import java.util.ArrayList;
import java.util.List;

import woosun.common.cache.domain.CacheSetProperties;

public class CacheHelper {

	private static List<CacheSetProperties> cacheSetPropertiesList = new ArrayList<CacheSetProperties>();
	
	public static void addProperties(CacheSetProperties cacheSetProperties){
		cacheSetPropertiesList.add(cacheSetProperties);
	}

	public static List<CacheSetProperties> getCacheSetPropertiesList() {
		return cacheSetPropertiesList;
	}

	public static void setCacheSetPropertiesList(List<CacheSetProperties> cacheSetPropertiesList) {
		CacheHelper.cacheSetPropertiesList = cacheSetPropertiesList;
	}
	
}
