package com.rendiyu.web.servlet.droped;

import com.rendiyu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeServlet")
public class ActiveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        UserServiceImpl userService = new UserServiceImpl();
        int i = userService.active(activeCode);
        if(i==1){
            response.sendRedirect(request.getContextPath()+"/active_ok.html");
        }else {
            response.sendRedirect(request.getContextPath()+"/active_fail.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
