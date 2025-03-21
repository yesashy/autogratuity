package com.autogratuity.data.repository.config;

import com.autogratuity.data.model.AppConfig;
import com.autogratuity.data.repository.core.DataRepository;

import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Repository interface for application configuration and device management.
 * Extends the DataRepository interface to provide specialized methods for
 * config operations while inheriting the broader repository contract.
 */
public interface ConfigRepository extends DataRepository {
    
    /**
     * Get the application configuration.
     * 
     * @return Single that emits the app configuration
     */
    @Override
    Single<AppConfig> getAppConfig();
    
    /**
     * Observe changes to the app configuration in real-time.
     * 
     * @return Observable that emits app configuration updates
     */
    @Override
    Observable<AppConfig> observeAppConfig();
    
    /**
     * Register the current device with the system.
     * 
     * @param deviceInfo Map containing device information
     * @return Completable that completes when registration is finished
     */
    @Override
    Completable registerDevice(Map<String, Object> deviceInfo);
    
    /**
     * Update the last active timestamp for the current device.
     * 
     * @return Completable that completes when update is finished
     */
    @Override
    Completable updateDeviceLastActive();
    
    /**
     * Clear all in-memory caches.
     * 
     * @return Completable that completes when caches are cleared
     */
    @Override
    Completable clearCaches();
    
    /**
     * Prefetch critical data for improved performance.
     * 
     * @return Completable that completes when prefetching is finished
     */
    @Override
    Completable prefetchCriticalData();
    
    /**
     * Get a configuration value as a string.
     * @param key The configuration key
     * @param defaultValue Default value if not found
     * @return The configuration value
     */
    String getConfigValue(String key, String defaultValue);
    
    /**
     * Get a configuration value as a boolean.
     * @param key The configuration key
     * @param defaultValue Default value if not found
     * @return The configuration value
     */
    boolean getConfigBoolean(String key, boolean defaultValue);
    
    /**
     * Increment a counter value.
     * @param counterKey The counter key to increment
     * @return Completable that completes when counter is incremented
     */
    Completable incrementCounter(String counterKey);
    
    /**
     * Return a no-op completable that just completes immediately.
     * @return Completable that completes immediately
     */
    Completable noOpCompletable();
}