<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>a view in MVC</title>
</head>
<body>
<form name="lagoutForm" action="logout.action" method="post">
<table>
<tr>
<td>Login Name:</td><td><% out.println(request.getAttribute("userName").toString());%></td>
</tr>
<tr>
<td>Age</td><td><% out.println(request.getAttribute("age").toString());%></td>
</tr>
</table>
<div>
<button><td>Logout</td></button>
</div>
</form>
</body>
</html>
