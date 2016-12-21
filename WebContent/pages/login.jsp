<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>登录</h1>
    <center>
        <div>
            <form action="/SCTest/login.scaction" method="post">
                <table>
                    <tr>
                        <td width="70" align="right"><font size="3">用户名：</font></td>
                        <td colspan="2"><input type="text" name="username"
                            value="${username}" style="width: 200, height:30;"></td>
                    </tr>
                    <tr>
                        <td width="70" align="right"><font size="3">密码：</font></td>
                        <td colspan="2"><input type="password" name="password"
                            value="${psw}" style="width: 200, height:30;"></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><input type="submit"
                            value="登录" style="width: 140, height:20"></td>
                        <td><font color="red"> ${msg} </font></td>
                    </tr>
                </table>
            </form>
        </div>
    </center>
</body>
</html>