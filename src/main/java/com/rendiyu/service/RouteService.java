package com.rendiyu.service;

import com.rendiyu.domain.PageBean;
import com.rendiyu.domain.Route;

public interface RouteService {
    PageBean<Route> findPage(String cid,String rname, String currentPage, String pageSize);

    Route findOne(String rids);

    int findCount(String rid);
}
