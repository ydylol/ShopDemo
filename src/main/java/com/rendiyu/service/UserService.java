package com.rendiyu.service;

import com.rendiyu.domain.User;

public interface UserService {
    int regist(User user);

    int active(String activeCode);

    User findByUsername(String username);
}
