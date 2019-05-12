<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%
 String content=request.getParameter("content");//获取输入的微博信息
 String nickname=request.getParameter("nickname");//获取输入昵称
 if(content!=null && nickname!=null){
	nickname=new String(nickname.getBytes("iso-8859-1"),"utf-8");	//对昵称进行转码
	content=new String(content.getBytes("iso-8859-1"),"utf-8");	//对内容进行转码
	String date=new java.util.Date().toLocaleString();	//获取系统时间
%>
<%="[ "+nickname+" ]于 "+date+" 发表一条微博，内容如下："%>
<%=content%>
<% }%>


