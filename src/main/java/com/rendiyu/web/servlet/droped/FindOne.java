package com.rendiyu.web.servlet.droped;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findOne")
public class FindOne extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/json;charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);

            new ObjectMapper().writeValue(response.getWriter(),user);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
