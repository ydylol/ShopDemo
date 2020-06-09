package com.rendiyu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtilDruid {
    private static Properties pro;
    private static DataSource dataSource;
    static{
        pro=new Properties();
        try {
            pro.load(JDBCUtilDruid.class.getClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getdataSource() {

        return dataSource;
    }
    public static Connection getconnection()  {


        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void close(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (resultSet != null) {
                resultSet.close();
                resultSet=null;
            }
            if (statement != null) {
                statement.close();
                statement=null;
            }
            if (connection != null) {
                connection.close();
                connection=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void close( Statement statement,Connection connection){
        try {
            if (statement != null) {
                statement.close();
                statement=null;
            }
            if (connection != null) {
                connection.close();
                connection=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
