package com.rendiyu.web.servlet.droped;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.ResultInfo;
import com.rendiyu.domain.User;
import com.rendiyu.service.UserService;
import com.rendiyu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/regist")
public class Regist extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println("收到了注册账号的请求");
        String check = request.getParameter("check");
        String checkcode= (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo = new ResultInfo();
        if(check==null||!(check.equalsIgnoreCase(checkcode))){
               resultInfo.setFlag(false);
               resultInfo.setErrorMsg("验证码错误");
               new ObjectMapper().writeValue(response.getWriter(),resultInfo);
               request.getSession().removeAttribute("check");
               return;

        }


        Map<String, String[]> parameterMap = request.getParameterMap();

        parameterMap.get("check");

        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        UserService userServices = new UserServiceImpl();
        int regist = userServices.regist(user);
        resultInfo.setFlag(true);
        new ObjectMapper().writeValue(response.getWriter(),resultInfo);



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
