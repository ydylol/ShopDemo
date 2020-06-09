package com.rendiyu.dao;

import com.rendiyu.domain.User;

public interface UserDao {
    public int add(User user);

    void findUser(String activeCode);

    User findByUsername(String username);
}
