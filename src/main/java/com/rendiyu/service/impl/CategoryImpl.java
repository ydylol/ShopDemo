package com.rendiyu.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.dao.CategoryDao;
import com.rendiyu.dao.impl.CategoryDaoImpl;
import com.rendiyu.domain.Category;
import com.rendiyu.service.CategoryService;
import com.rendiyu.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

public class CategoryImpl implements CategoryService {
    CategoryDao categoryDao=new CategoryDaoImpl();
    Jedis jedis = JedisUtils.getJedis();

    @Override
    public Category findOne(String cid) {

        return categoryDao.findOne(cid);
    }

    @Override
    public String findAll() {
        String category = jedis.get("category");
        if(category==null){
            List<Category> categoryLis=categoryDao.findAll();
            try {
                String s = new ObjectMapper().writeValueAsString(categoryLis);
                jedis.set("category",s);
                return s;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else {
            return category;
        }

      return null;
    }
}
