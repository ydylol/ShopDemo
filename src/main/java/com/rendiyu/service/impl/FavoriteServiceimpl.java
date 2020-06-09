package com.rendiyu.service.impl;

import com.rendiyu.dao.FavoriteDao;
import com.rendiyu.dao.impl.FavoriteDaoimpl;
import com.rendiyu.service.FavoriteService;

import java.util.Date;

public class FavoriteServiceimpl implements FavoriteService {
    FavoriteDao favoriteDao=new FavoriteDaoimpl();

    @Override
    public int remove(int uid, int rid) {
        int remove = favoriteDao.remove(uid, rid);
        return remove;
    }

    @Override
    public boolean findOne(int uid, String rid) {
       return favoriteDao.findOne(uid,rid);
    }

    @Override
    public int add(int uid, int rid) {

        int add = favoriteDao.add(uid, new Date().toLocaleString(), rid);
        return add;

    }
}
