package com.rendiyu.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 任地狱
 * 项目约束
 * 用户模块
 * /user/login
 * /user/regist
 *
 * 分类模块
 * /category/findAll
 * /category/add
 *
 * 产品模块
 * /product/findAll
 * /product/addx
 *
 */
@WebServlet(value = "*.do",loadOnStartup = 1)
public class DispathServlet extends HttpServlet {
    Properties properties = new Properties();
    Map<Object,Object> map=new HashMap<>();
    @Override
    public void init() throws ServletException {
        try {
            properties.load(DispathServlet.class.getClassLoader().getResourceAsStream("url.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
               //遍历配置文件，将所有的控制类生成
                Object key = entry.getKey();
                String  className = (String) entry.getValue();
                Object value = Class.forName(className).newInstance();
                map.put(key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();

        int index=requestURI.lastIndexOf("/")+1;
        String s = requestURI.substring(index);
        String methodName = s.substring(0, s.lastIndexOf(".do"));
        int index1 = requestURI.indexOf("/") + 1;
        String modelName = requestURI.substring(index1,requestURI.lastIndexOf("/"));
        String className = properties.getProperty(modelName);
        Object modelController = map.get(modelName);
     /*   System.out.println(requestURI);
        System.out.println(className);
        System.out.println(modelName);
        System.out.println(methodName);*/
        try {
            Class.forName(className);
            Method method = modelController.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(modelController,request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        doPost(request, response);
    }
}
