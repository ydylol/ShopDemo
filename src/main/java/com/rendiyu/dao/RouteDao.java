package com.rendiyu.dao;

import com.rendiyu.domain.Category;
import com.rendiyu.domain.Route;
import com.rendiyu.domain.RouteImg;
import com.rendiyu.domain.Seller;

import java.util.List;

public interface RouteDao {
    int findCountBycid(int cid);

    List<Route> findList(int cid, int start,int pagesize);
    List<Route> findList(String rname, int start,int pagesize);

    int findCountBycidAndrname(int cid, String rname);
    int findCountBycidAndrname( String rname);

    List<Route> findList(int cid, String rname, int start, int pageSize);
    List<Route> findList(int start, int pageSize);

    int findCountAll();

    Route findOne(int rid);

    List<RouteImg> findOneImg(int rid);

    Seller findOneSeller(int rid);

    Category findOneCategory(int rid);

    int findCount(String rid);
}
