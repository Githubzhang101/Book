<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%
 String username=request.getParameter("username");	//获取用户名
 String pwd=request.getParameter("pwd");	//获取密码
 if("mr".equals(username)){	//判断用户名是否正确
 if("mrsoft".equals(pwd)){	//判断密码是否正确
 	  session.setAttribute("username" , username);	//保存用户名到session中
   }
 }
response.sendRedirect("index.jsp");	//重定向页面到index.jsp页面
%>