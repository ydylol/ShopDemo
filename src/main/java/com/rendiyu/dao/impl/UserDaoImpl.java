package com.rendiyu.dao.impl;

import com.rendiyu.dao.UserDao;
import com.rendiyu.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.rendiyu.utils.JDBCUtilDruid;

/**
 * @author 任地狱
 */
public class UserDaoImpl implements UserDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtilDruid.getdataSource());




    @Override
    public int add(User user) {
        String sql="insert into tab_user values (null,?,?,?,?,?,?,?,0,?)";
        int update = template.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getCode());

        return  update;
    }

    @Override
    public void findUser(String activeCode) {
        String sql="update tab_user set status = 1 where username = ?";
        template.update(sql,activeCode);

    }

    @Override
    public User findByUsername(String username) {
        String sql="select * from tab_user where username = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }

    }
}
