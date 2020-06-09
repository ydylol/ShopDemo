package com.rendiyu.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rendiyu.domain.ResultInfo;
import com.rendiyu.domain.User;
import com.rendiyu.service.UserService;
import com.rendiyu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Random;

/**
 * @author 任地狱
 */
public class UserController {

    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println("收到了注册账号的请求");
        String check = request.getParameter("check");
        String checkcode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo = new ResultInfo();
        if (check == null || !(check.equalsIgnoreCase(checkcode))) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            new ObjectMapper().writeValue(response.getWriter(), resultInfo);
            request.getSession().removeAttribute("check");
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.get("check");
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        UserService userServices = new UserServiceImpl();
        int regist = userServices.regist(user);
        resultInfo.setFlag(true);
        new ObjectMapper().writeValue(response.getWriter(), resultInfo);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        System.out.println("收到了登陆账号的请求");
        String check = request.getParameter("check");
        String checkcode= (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo = new ResultInfo();
        if(check==null||!(check.equalsIgnoreCase(checkcode))){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
            request.getSession().removeAttribute("check");
            return;

        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserServiceImpl();
        User user= userService.findByUsername(username);
        if(user==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名错误");
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
            return;
        }
        if(user.getPassword().equals(password)){
            resultInfo.setFlag(true);
            request.getSession().setAttribute("user",user);
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);

        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("密码错误");
            new ObjectMapper().writeValue(response.getWriter(),resultInfo);
            return;
        }
    }
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        User user = (User) request.getSession().getAttribute("user");
//        System.out.println(user);
        new ObjectMapper().writeValue(response.getWriter(),user);
    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        UserServiceImpl userService = new UserServiceImpl();
        int i = userService.active(activeCode);
        if(i==1){
            response.sendRedirect(request.getContextPath()+"/active_ok.html");
        }else {
            response.sendRedirect(request.getContextPath()+"/active_fail.html");
        }
    }
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/index.html");
    }
    public void checkCode(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        //服务器通知浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        //参数一：长
        //参数二：宽
        //参数三：颜色
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为灰色
        g.setColor(Color.GRAY);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        request.getSession().setAttribute("CHECKCODE_SERVER",checkCode);

        //设置画笔颜色为黄色
        g.setColor(Color.YELLOW);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }
    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGabcdefg";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }

}
