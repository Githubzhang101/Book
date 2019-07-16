<%@ page contentType="text/html; charset=utf-8" language="java" import="sun.misc.BASE64Decoder"%>
<%
 String content="";
 if(request.getParameter("content")!=null){
 	content=request.getParameter("content");//获取输入的微博信息
	content=content.replaceAll("%2B","+");	//替换content中的加号，这是由于在进行URL编码时，将+号转换为%2B了
 	BASE64Decoder decoder=new BASE64Decoder();
	content=new String(decoder.decodeBuffer(content),"utf-8");	//进行base64解码
 }
%>
<%="发表一条微博，内容如下："%>
<%=content%>


