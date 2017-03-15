package woosun.common.cache.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration.Strategy;
import woosun.common.cache.component.CacheHelper;
import woosun.common.cache.domain.CacheSetProperties;

@Configuration
@EnableCaching
public class CachingConfiguration implements CachingConfigurer {
	
	@Autowired
	private CacheHelper cacheHelper;

	@Bean
	public net.sf.ehcache.CacheManager ehCacheManager() {
		
		if(cacheHelper == null){
			return null;
		}
		
		List<CacheSetProperties> cacheSetPropertiesList = cacheHelper.getCacheSetPropertiesList();
		
		if(CollectionUtils.isEmpty(cacheSetPropertiesList)){
			return null;
		}

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		
		for(CacheSetProperties cacheSetProperties : cacheSetPropertiesList){
			CacheConfiguration cacheConfiguration =  
					getCacheConfiguration(cacheSetProperties.getCacheName(), cacheSetProperties.getMaxElementsInMemory(), 
							cacheSetProperties.getTimeToLiveSeconds(), cacheSetProperties.getTimeToIdleSeconds());
			config.addCache(cacheConfiguration);
		}
		
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	private CacheConfiguration getCacheConfiguration(String name,
			int maxElementsInMemory, int timeToLiveSeconds, int timeToIdleSeconds) {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		//cache name
		cacheConfiguration.setName(name);
		//객체의 개수가 maxElementsInMemory에 도달했을 때,모메리에서 객체를 어떻게 제거할 지에 대한 정책을 지정한다.
		cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
		//메모리에 저장될 수 있는 객체의 최대 개수
		cacheConfiguration.setMaxEntriesLocalHeap(maxElementsInMemory);
		//이 값이 true이면 timeout 관련 설정은 무시되고, Element가 캐시에서 삭제되지 않는다.
		cacheConfiguration.setEternal(false);
		//데이터를 disk에 쓸지 안쓸지 결정
		cacheConfiguration.persistence(new PersistenceConfiguration()
				.strategy(Strategy.NONE));
		//Element가 존재하는 시간. 이 시간이 지나면 캐시에서 제거된다. 0이면 무한
		cacheConfiguration.setTimeToLiveSeconds(timeToLiveSeconds);
		//Element가 지정한 시간 동안 사용(조회)되지 않으면 캐시에서 제거된다.0이면 무한
		cacheConfiguration.setTimeToIdleSeconds(timeToIdleSeconds);

		return cacheConfiguration;
	}

	@Bean
	@Override
	public CacheManager cacheManager() {

		return new EhCacheCacheManager(ehCacheManager());
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
