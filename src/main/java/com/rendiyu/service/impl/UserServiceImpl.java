package com.rendiyu.service.impl;

import com.rendiyu.dao.impl.UserDaoImpl;
import com.rendiyu.dao.UserDao;
import com.rendiyu.domain.User;
import com.rendiyu.service.UserService;
import com.rendiyu.utils.JedisUtils;
import com.rendiyu.utils.MailUtils;
import com.rendiyu.utils.UuidUtil;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * @author 任地狱
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public int regist(User user) {
        if("".equals(user.getBirthday())){
            user.setBirthday(new Date().toLocaleString());
        }
        int i;
      i= userDao.add(user);
        String uuid = UuidUtil.getUuid();
        Jedis jedis = JedisUtils.getJedis();
        jedis.setex(uuid,2*60 ,user.getUsername());
        String text= "<a href='http://localhost/user/active.do?activeCode="+uuid+"'>" +
                "点击激活用户</a>";
        MailUtils.sendMail(user.getEmail(),text,"邮件激活");
        return i;
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);

        return user;
    }

    @Override
    public int active(String activeCode) {
        Jedis jedis = JedisUtils.getJedis();
        String username = jedis.get(activeCode);
//        System.out.println(activeCode);
        if (username==null){
            return 0;
        }else{
             userDao.findUser(username);
             return 1;
        }


    }
}
