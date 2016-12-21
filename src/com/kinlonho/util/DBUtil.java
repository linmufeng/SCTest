package com.kinlonho.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


/**
 ***************************************************
 * CopyRight (c) www.bestlinmufeng.com
 ***************************************************
 * @author  mufeng E-mail: linmufeng@yeah.net
 * @date    created on 2016年12月20日 下午11:10:18
 * @info         
 * @version      
 ***************************************************
 */

public class DBUtil {
    
    private static String URL;          //数据库链接URL地址
    private static String USER_NAME;    //用户名
    private static String PASSWORD;     //密码
    private static String DRIVER;       //mysql驱动类
    private static ResourceBundle rb = 
            ResourceBundle.getBundle("com.kinlonho.util.jdbc");//读取配置文件
    
    private DBUtil(){}
    
    private static DispTest dt;
    
    //使用静态块加载驱动程序
    static{
        URL = rb.getString("jdbc.url");
        USER_NAME = rb.getString("jdbc.username");
        PASSWORD = rb.getString("jdbc.password");
        DRIVER = rb.getString("jdbc.driver");
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 负责打开 MySQL 数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            dt.print("获取连接失败");
        }
        return conn;
    }

    /**
     * 负责关闭 MySQL 数据库连接
     */
    public static boolean closeDBConnection(ResultSet rs,Statement stat,Connection conn) {
        try {
            if (rs != null){rs.close();}
            if (stat != null){stat.close();}
            if (conn != null){conn.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}