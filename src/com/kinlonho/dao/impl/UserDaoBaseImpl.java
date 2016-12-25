package com.kinlonho.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kinlonho.bean.UserBean;
import com.kinlonho.dao.UserDao;
import com.kinlonho.util.DBUtil;
import com.kinlonho.util.DispTest;
import com.mysql.jdbc.PreparedStatement;

/**
 ***************************************************
 * CopyRight (c) www.bestlinmufeng.com
 ***************************************************
 * @author  MufengLin E-mail: linmufeng@yeah.net
 * @date    created on 2016年12月20日 下午11:18:30
 * @info         
 * @version      
 ***************************************************
 */

public class UserDaoBaseImpl implements UserDao {
    private DispTest test;

    /**
     * 登录
     */
    public UserBean login(UserBean user) {
        test = new DispTest();
        if (user == null){
            test.print("用户名为空");
            return null;
        }
        UserBean dbUser = queryUser(user.getName());
        test.print("传进来的用户名：" + user.getName());
        if (dbUser == null){
            test.print("查找不到该用户");
        }else{
            if (dbUser.getPassword().equals(user.getPassword())){
                //密码相同
                test.print("用户名和密码正确，开始登录...");
                
            }else{
                //密码不同
                test.print("出现错误，请检查密码");
                dbUser = null;
            }
        }
        return dbUser;
    }

    
    public void register(UserBean user) {
        test.print("注册成功：用户，" + user.getName());
    }


    /**
     * 负责根据参数查询对象表记录
     */
    public UserBean queryUser(String userName) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserBean user = null;
        String sql = "select name,password,age,sex from User where name = '" + userName + "'"; 
        test.print("sql语句:" + sql);
        try {
            test.print("queryUser方法正在打开数据库");
            conn = DBUtil.getConnection();//打开数据库
            if (conn == null){
                test.print("数据库连接失败");
                return null;
            }
            test.print("数据库连接成功");
            ps = (PreparedStatement) conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                test.print("数据库查询结果");
                user = new UserBean();
                user.setName(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setAge(rs.getInt(3));
                user.setSex(rs.getInt(4));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            test.print("根据用户名查询失败");
        } finally {
            DBUtil.closeDBConnection(rs, ps, conn);//关闭数据库
        }
        return user;
    }

    /**
     * 负责根据参数增加对象表记录
     */
    public boolean insertUser(UserBean u) {
        return false;
    }

    /**
     * 负责根据参数修改对象表记录
     */
    public boolean updateUser(UserBean u) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 负责根据参数删除对象表记录
     */
    public boolean deleteUser(UserBean u) {
        // TODO Auto-generated method stub
        return false;
    }
    

}
