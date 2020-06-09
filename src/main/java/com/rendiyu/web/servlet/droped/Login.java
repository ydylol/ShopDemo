package com.rendiyu.web.servlet.droped;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.ResultInfo;
import com.rendiyu.domain.User;
import com.rendiyu.service.UserService;
import com.rendiyu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println("收到了登陆账号的请求");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user= userService.findByUsername(username);
        if(user==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名错误");
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
            return;
        }
        if(user.getPassword().equals(password)){
            resultInfo.setFlag(true);
            request.getSession().setAttribute("user",user);
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);

        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("密码错误");
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
