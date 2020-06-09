package com.rendiyu.service.impl;

import com.rendiyu.dao.RouteDao;
import com.rendiyu.dao.impl.RouteDaoImpl;
import com.rendiyu.domain.*;
import com.rendiyu.service.RouteService;

import java.util.List;

/**
 * @author 任地狱
 */
public class RouteServiceImpl implements RouteService {
   RouteDao routeDao=new RouteDaoImpl();

    @Override
    public int findCount(String rid) {

        return routeDao.findCount(rid);
    }

    /**
     * 用于显示商品详情
     * @param rids
     * @return
     */
    @Override
    public Route findOne(String rids) {
        int rid=1;
        try {
            rid= Integer.parseInt(rids);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Route route = routeDao.findOne(rid);
        List<RouteImg> routeImg = routeDao.findOneImg(rid);
        Seller seller = routeDao.findOneSeller(rid);
        Category category=routeDao.findOneCategory(rid);
        route.setRouteImgList(routeImg);
        route.setSeller(seller);
        route.setCategory(category);

    /*    System.out.println(route);
        System.out.println(routeImg);
        System.out.println(seller);
        System.out.println(category);*/
        return route;
    }

    /**
     * 分页查询
     * @param cids  分类id
     * @param rname  路线名
     * @param currentPages  当前页
     * @param pageSizes     每页显示个数
     * @return
     */
    @Override
    public PageBean<Route> findPage(String cids,String rname,String currentPages, String pageSizes) {
        int cid=0;
        int currentPage=1;
        int pageSize=9;
        try {
          cid=  Integer.parseInt(cids);
          currentPage= Integer.parseInt(currentPages);
          pageSize= Integer.parseInt(pageSizes);
        }catch (Exception e){
           // e.printStackTrace();
        }
        currentPage= Integer.parseInt(currentPages);
        pageSize= Integer.parseInt(pageSizes);
        if(!cids.equals("null")&&!cids.equals("")){
            if("null".equals(rname)){
                int count= routeDao.findCountBycid(cid);
                PageBean<Route> pageBean = new PageBean<>();
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalCount(count);
                int totalPage=count%pageSize==0?count/pageSize:count/pageSize+1;
                pageBean.setPageSize(pageSize);
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalPage(totalPage);
                int start=(currentPage-1)*pageSize;
                List<Route> routeList=routeDao.findList(cid,start,pageSize);
                pageBean.setList(routeList);
                return pageBean;
            }else {
                int count= routeDao.findCountBycidAndrname(cid,rname);
                PageBean<Route> pageBean = new PageBean<>();
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalCount(count);
                int totalPage=count%pageSize==0?count/pageSize:count/pageSize+1;
                pageBean.setPageSize(pageSize);
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalPage(totalPage);
                int start=(currentPage-1)*pageSize;
                List<Route> routeList=routeDao.findList(cid,rname,start,pageSize);
                pageBean.setList(routeList);
                return pageBean;
            }

        }else {
            if("".equals(rname)){
                int count= routeDao.findCountAll();
                PageBean<Route> pageBean = new PageBean<>();
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalCount(count);
                int totalPage=count%pageSize==0?count/pageSize:count/pageSize+1;
                pageBean.setPageSize(pageSize);
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalPage(totalPage);
                int start=(currentPage-1)*pageSize;
                List<Route> routeList=routeDao.findList(start,pageSize);
                pageBean.setList(routeList);
                return pageBean;
            }else {
                System.out.println("点击的是首页");
                int count= routeDao.findCountBycidAndrname(rname);
                PageBean<Route> pageBean = new PageBean<>();
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalCount(count);
                int totalPage=count%pageSize==0?count/pageSize:count/pageSize+1;
                pageBean.setPageSize(pageSize);
                pageBean.setCurrentPage(currentPage);
                pageBean.setTotalPage(totalPage);
                int start=(currentPage-1)*pageSize;
                List<Route> routeList=routeDao.findList(rname,start,pageSize);
                pageBean.setList(routeList);
                return pageBean;

            }

        }

    }
}
