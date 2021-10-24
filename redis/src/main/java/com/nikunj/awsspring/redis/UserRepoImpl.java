package com.nikunj.awsspring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserRepoImpl implements UserRepo{

    HashOperations hashOperations;

    public static final String key = "USER";

    UserRepoImpl(RedisTemplate<String, User> redisTemplate){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, User> findAllUsers() {
        return hashOperations.entries(key);
    }

    @Override
    public void save(User user) {
        hashOperations.put(key, user.getId(), user);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(key, id);
    }

    @Override
    public User findUser(String id) {
        return (User)hashOperations.get(key, id);
    }
}
