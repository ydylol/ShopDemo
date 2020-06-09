package com.rendiyu.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.ResultInfo;
import com.rendiyu.domain.Route;
import com.rendiyu.domain.User;
import com.rendiyu.service.FavoriteService;
import com.rendiyu.service.RouteService;
import com.rendiyu.service.impl.FavoriteServiceimpl;
import com.rendiyu.service.impl.RouteServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FavoriteController {
            FavoriteService favoriteService=new FavoriteServiceimpl();
        public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
//            User user = (User) request.getSession().getAttribute("user");
//            System.out.println(user);
           response.setContentType("application/json;charset=utf-8");
            String rid = request.getParameter("rid");
            User user = (User) request.getSession().getAttribute("user");
            FavoriteService favoriteService=new FavoriteServiceimpl();
            RouteService routeService = new RouteServiceImpl();
            ResultInfo resultInfo=new ResultInfo();
            if (user==null){
                resultInfo.setFlag(false);
                resultInfo.setData(routeService.findCount(rid));
                new ObjectMapper().writeValue(response.getWriter(),resultInfo);
                return;
            }
            //System.out.println(rid);

            resultInfo.setData(routeService.findCount(rid));
           resultInfo.setFlag(favoriteService.findOne(user.getUid(),rid));
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
        }
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int add = favoriteService.add(user.getUid(), Integer.parseInt(rid));
        ResultInfo resultInfo = new ResultInfo();
        //System.out.println(add);
        if(add==1){
            resultInfo.setFlag(true);
            RouteService routeService = new RouteServiceImpl();
            int count = routeService.findCount(rid);
            System.out.println(count);
            resultInfo.setData(count);
            System.out.println(count);
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
        }else {
            resultInfo.setFlag(false);
        }

    }
    public  void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int remove = favoriteService.remove(user.getUid(), Integer.parseInt(rid));
        ResultInfo resultInfo = new ResultInfo();
        if(remove==1){
            resultInfo.setFlag(true);
            RouteService routeService = new RouteServiceImpl();
            int count = routeService.findCount(rid);
            System.out.println(count);
            resultInfo.setData(count);
            System.out.println(count);
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
        }else {
            resultInfo.setFlag(false);
        }
    }
}
