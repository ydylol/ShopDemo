package com.rendiyu.dao;

import com.rendiyu.domain.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> findAll();

    Category findOne(String cid);
}
