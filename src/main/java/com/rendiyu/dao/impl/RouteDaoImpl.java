package com.rendiyu.dao.impl;

import com.rendiyu.dao.RouteDao;
import com.rendiyu.domain.Category;
import com.rendiyu.domain.Route;
import com.rendiyu.domain.RouteImg;
import com.rendiyu.domain.Seller;
import com.rendiyu.utils.JDBCUtilDruid;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template=new JdbcTemplate(JDBCUtilDruid.getdataSource());
    @Override
    public int findCountBycid(int cid) {
        String sql="select count(rid) from tab_route where cid = ?";
        Integer integer = template.queryForObject(sql, Integer.class, cid);
        return integer;
    }

    @Override
    public List<Route> findList(int cid, int start,int pagesize) {
        String sql="select * from tab_route where cid= ? limit ? ,?";
        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, start, pagesize);

        return routeList;
    }

    @Override
    public int findCountBycidAndrname(int cid, String rname) {
        String sql="select count(rid) from tab_route where cid = ? and rname like ?";
        Integer integer = template.queryForObject(sql, Integer.class, cid,"%"+rname+"%");
        System.out.println("查到了"+integer+"条");
        System.out.println(rname);
        return integer;
    }

    @Override
    public List<RouteImg> findOneImg(int rid) {
        String sql="select * from tab_route_img where rid= ?";
        List<RouteImg> routeImgs = template.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        return routeImgs;
    }

    @Override
    public int findCount(String rid) {
        String sql="select count from tab_route where rid=?";
        Integer integer = template.queryForObject(sql, Integer.class,rid);
        return integer;
    }

    @Override
    public Category findOneCategory(int rid) {
        String sql="select * from tab_category join tab_route tr on tab_category.cid = tr.cid where rid= ?";
        Category category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), rid);
        return category;
    }

    @Override
    public Seller findOneSeller(int rid) {
        String sql="select * from tab_seller join tab_route tr on tab_seller.sid = tr.sid where rid=?";
        Seller seller = template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), rid);
        return seller;
    }

    /**
     * 查询商品详情，需要查询线路以及线路图片，以及销售商表，并且封装成route对象
     * @param rid
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql="select * from tab_route where rid=?";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        return route;
    }

    @Override
    public List<Route> findList(String rname, int start, int pagesize) {
        String sql="select * from tab_route where rname like  ? limit ? ,?";
        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), "%"+rname+"%", start, pagesize);

        return routeList;

    }

    @Override
    public int findCountBycidAndrname(String rname) {
        String sql="select count(rid) from tab_route where  rname like ?";
        Integer integer = template.queryForObject(sql, Integer.class,"%"+rname+"%");
        System.out.println("查到了"+integer+"条");
        System.out.println(rname);
        return integer;
    }

    @Override
    public List<Route> findList(int start, int pageSize) {
        String sql="select * from tab_route limit ? ,?";
        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class),start, pageSize);

        return routeList;
    }

    @Override
    public int findCountAll() {
        String sql="select count(rid) from tab_route";
        Integer integer = template.queryForObject(sql, Integer.class);
        System.out.println("查到了"+integer+"条");
        return integer;
    }

    @Override
    public List<Route> findList(int cid, String rname, int start, int pageSize) {
        String sql="select * from tab_route where cid= ? and rname like ? limit ? ,?";
        List<Route> routeList = template.query(sql, new BeanPropertyRowMapper<>(Route.class), cid, "%"+rname+"%",start, pageSize);

        return routeList;
    }
}
