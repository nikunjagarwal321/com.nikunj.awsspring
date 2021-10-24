package com.nikunj.awsspring.redis;

import java.util.Map;

public interface UserRepo {
    Map<String, User> findAllUsers();

    void save(User user);

    void delete(String id);

    User findUser(String id);

}
