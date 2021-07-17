package org.tianly.redis.services.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.tianly.redis.pojo.Animal;
import org.tianly.redis.services.CacheServices;

@Service
public class CacheServicesImpl implements CacheServices {
    @Override
    @Cacheable(value = "put",key = "#animal.name")
    public Animal put(Animal animal) {
        return animal;
    }
}
