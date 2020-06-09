package com.rendiyu.service;

public interface FavoriteService {
    int add(int uid, int rid);

    boolean findOne(int uid, String rid);

    int remove(int uid, int rid);
}
