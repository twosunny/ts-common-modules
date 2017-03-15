package woosun.common.cache.domain;

public class CacheSetProperties {
	
	private String cacheName;
	private int maxElementsInMemory;
	private int timeToLiveSeconds;
	private int timeToIdleSeconds;
	
	public CacheSetProperties(){}
	
	public CacheSetProperties(String cacheName, int maxElementsInMemory, int timeToLiveSeconds, int timeToIdleSeconds){
		this.cacheName = cacheName;
		this.maxElementsInMemory = maxElementsInMemory;
		this.timeToLiveSeconds = timeToLiveSeconds;
		this.timeToIdleSeconds = timeToIdleSeconds; 
	}
	
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public int getMaxElementsInMemory() {
		return maxElementsInMemory;
	}
	public void setMaxElementsInMemory(int maxElementsInMemory) {
		this.maxElementsInMemory = maxElementsInMemory;
	}
	public int getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}
	public void setTimeToLiveSeconds(int timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}
	public int getTimeToIdleSeconds() {
		return timeToIdleSeconds;
	}
	public void setTimeToIdleSeconds(int timeToIdleSeconds) {
		this.timeToIdleSeconds = timeToIdleSeconds;
	}
	
	
}
