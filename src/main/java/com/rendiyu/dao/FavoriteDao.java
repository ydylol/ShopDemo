package com.rendiyu.dao;

public interface FavoriteDao {
    int add(int uid, String date, int rid);

    boolean findOne(int uid, String rid);

    int remove(int uid, int rid);
}
