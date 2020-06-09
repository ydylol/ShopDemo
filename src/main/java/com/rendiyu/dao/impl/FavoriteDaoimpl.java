package com.rendiyu.dao.impl;

import com.rendiyu.dao.FavoriteDao;
import com.rendiyu.domain.Favorite;
import com.rendiyu.utils.JDBCUtilDruid;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class FavoriteDaoimpl implements FavoriteDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtilDruid.getdataSource());
    String sql="";



    @Override
    public boolean findOne(int uid, String rid) {
        sql="select * from tab_favorite where uid= ? and rid=?";
        try {
            Favorite favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), uid, Integer.parseInt(rid));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 取消收藏功能
     * @param uid
     * @param rid
     * @return
     */
    @Override
    public int remove(int uid, int rid) {
        sql="delete from tab_favorite where uid=? and rid=?";
        String sql2="update tab_route set count=count-1 where rid=?";
        try {
            int update = template.update(sql, uid, rid);
            template.update(sql2,rid);
            return update;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }

    }

    @Override
    public int add(int uid, String date, int rid) {
        sql="insert into tab_favorite values (?,?,?)";
        String sql2="update tab_route set count=count+1 where rid=?";
        try {
            int update = template.update(sql, rid, date, uid);
            template.update(sql2,rid);
            return update;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
