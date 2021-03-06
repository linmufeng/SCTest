package com.kinlonho.framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kinlonho.bean.UserBean;
import com.kinlonho.framework.interceptor.Action;
import com.kinlonho.service.UserService;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月1日 上午12:20:39 
* 
*/
public class LoginAction implements Action{

	/**
	 * @see LoginAction#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @return 根据返回的uri对象即是查找结果集的name
	 */
	public Object execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("login方法被执行了。。。");
		
		Object uri = null;

		// 1. 获取请求数据，封装
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setName(name);
		user.setPassword(password);

		// 2. 调用Service
		System.out.println("网页传过来的用户名：" + name);
		UserService userService = new UserService();
		UserBean userInfo = userService.login(user);
		// 3. 跳转
		if (userInfo == null) {
			// 登陆失败
			uri = "fail";  
		} else {
			// 登陆成功
//			request.getSession().setAttribute("userInfo", userInfo);
            request.setAttribute("userName", userInfo.getName());
            request.setAttribute("age", userInfo.getAge());
			// 返回uri
			uri = "success"; 
		}
		return uri;
	}
}
