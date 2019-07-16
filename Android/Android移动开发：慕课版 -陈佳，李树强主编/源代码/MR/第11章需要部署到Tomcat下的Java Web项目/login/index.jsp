<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%
 String username="";
if(session.getAttribute("username")!=null){
	username=session.getAttribute("username").toString();	//获取保存在Session中的用户名
}
 if("mr".equals(username)){	//判断是否为合法用户
  	out.println("吉林省明日科技有限公司");
 	out.println("Tel：0431-84978981 84978982");
	out.println("E-mail：mingrisoft@mingrisoft.com");
	out.println("Address：长春市南关区明珠小区B16-4室");
 }else{	//没有成功登录时
 	out.println("您没有访问该页面的权限！");
 }
%>