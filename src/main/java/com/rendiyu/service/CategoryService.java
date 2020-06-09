package com.rendiyu.service;

import com.rendiyu.domain.Category;

public interface CategoryService {

    public String findAll();

    Category findOne(String cid);
}
