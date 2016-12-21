package com.kinlonho.dao;

import com.kinlonho.bean.UserBean;
import com.kinlonho.util.DispTest;



public interface UserDao {

    // 登陆
    public UserBean login(UserBean user);

    // 模拟注册
    public void register(UserBean user);
    
    /**
     * 负责根据参数查询对象表记录
     * 
     * @param userName
     * @return
     */

    public UserBean queryUser(String userName);

    /**
     * 负责根据参数增加对象表记录
     * 
     * @param u
     * @return
     */
    public boolean insertUser(UserBean u);

    /**
     * 负责根据参数修改对象表记录
     * 
     * @param u
     * @return
     */
    public boolean updateUser(UserBean u);

    /**
     * 负责根据参数删除对象表记录
     * 
     * @param u
     * @return
     */
    public boolean deleteUser(UserBean u);

}
