package com.rendiyu.web.controller;

import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.PageBean;
import com.rendiyu.domain.Route;
import com.rendiyu.service.RouteService;
import com.rendiyu.service.impl.RouteServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RouteController {
    public void findPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cid = request.getParameter("cid");
        String rname = request.getParameter("rname");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        rname=new String(rname.getBytes("ISO-8859-1"),"utf-8");
        System.out.println("cid"+cid);
        System.out.println("当前页"+currentPage);
        System.out.println("页面显示数量"+pageSize);
        System.out.println("查询名字"+rname);

        RouteService routeService=new RouteServiceImpl();
        PageBean<Route> pageBean=routeService.findPage(cid,rname,currentPage,pageSize);
        new ObjectMapper().writeValue(response.getWriter(),pageBean);

    }
    public void findPageByname(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        String rname = request.getParameter("rname");
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

    }
    public void findOne(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String rid = request.getParameter("rid");
        System.out.println("rid="+rid);
        Route route = new Route();
        RouteService routeService=new RouteServiceImpl();
        route=routeService.findOne(rid);
        System.out.println(route);
        new ObjectMapper().writeValue(response.getWriter(),route);
    }
}
