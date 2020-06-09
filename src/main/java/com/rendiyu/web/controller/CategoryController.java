package com.rendiyu.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.Category;
import com.rendiyu.service.CategoryService;
import com.rendiyu.service.impl.CategoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 任地狱
 */
public class CategoryController {
        public void findAll(HttpServletRequest request, HttpServletResponse response){
            response.setContentType("application/json;charset=utf-8");
           // System.out.println("来请求了");
            CategoryService categoryService = new CategoryImpl();
            String categoryList=categoryService.findAll();
        //    System.out.println(categoryList);
            try {
                response.getWriter().println(categoryList);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String cid = request.getParameter("cid");
        CategoryService categoryService = new CategoryImpl();
        Category category = categoryService.findOne(cid);
        new ObjectMapper().writeValue(response.getWriter(),category);

    }
}
