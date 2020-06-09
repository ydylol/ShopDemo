package com.rendiyu.dao.impl;

import com.rendiyu.dao.CategoryDao;
import com.rendiyu.domain.Category;
import com.rendiyu.utils.JDBCUtilDruid;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
   JdbcTemplate template=new JdbcTemplate(JDBCUtilDruid.getdataSource());
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        List<Category> categoryList = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
     //   System.out.println("找到了分类表"+categoryList);
        for (Category category : categoryList) {
            System.out.println(category);
        }
        return categoryList;

    }

    @Override
    public Category findOne(String cid) {
        String sql="select * from tab_category where cid = ?";
        Category category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class),Integer.parseInt(cid));
        return category;
    }
}
