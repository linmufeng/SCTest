package com.kinlonho.service;

import com.kinlonho.bean.UserBean;
import com.kinlonho.dao.UserDao;
import com.kinlonho.dao.impl.UserDaoBaseImpl;
import com.kinlonho.util.DispTest;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月1日 上午12:28:24 
* 
*/
public class UserService {
	private UserDao ud = new UserDaoBaseImpl();

	// 模拟登陆
	public UserBean login(UserBean user){
	    System.out.println("进入UserService");
		return ud.login(user);
	}
	
	// 模拟注册
	public void register(UserBean user) {
		ud.register(user);
	}
}
