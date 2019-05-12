﻿<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实现添加评论和删除评论的功能</title>
<link rel="stylesheet" href="CSS/style.css">
<script type="text/javascript">
	function addElement() {
	if(form1.content.value==""){
		alert("请输入评论内容！");
		return;

	}
	//创建TextNode节点
	var person = document.createTextNode(form1.person.value);
	var content = document.createTextNode(form1.content.value);	
	//创建td类型的Element节点
	var td_person = document.createElement("td"); 
	var td_content = document.createElement("td");	
	var tr = document.createElement("tr"); //创建一个tr类型的Element节点
	var tbody = document.createElement("tbody"); //创建一个tbody类型的Element节点
	//将TextNode节点加入到td类型的节点中
	td_person.appendChild(person);
	td_content.appendChild(content);
	td_person.style.bgColor="#FFFFFF";	
	//将td类型的节点添加到tr节点中
	tr.appendChild(td_person);
	tr.appendChild(td_content);	
	tbody.appendChild(tr); //将tr节点加入tbody中
	var tComment = document.getElementById("comment"); //获取table对象
	tComment.appendChild(tbody); //将节点tbody加入节点尾部
	form1.person.value="";	//清空评论人文本框
	form1.content.value="";		//清空评论内容文本框

}
//删除第一条评论
function deleteFirstE(){
	var tComment = document.getElementById("comment"); //获取table对象
	if(tComment.rows.length>1){
		tComment.deleteRow(1);		//删除表格的第二行，即第一条评论，
	}
}
//删除最后一条评论
function deleteLastE(){
	var tComment = document.getElementById("comment"); //获取table对象
	if(tComment.rows.length>1){
		tComment.deleteRow(tComment.rows.length-1);	//删除表格的最后一行，即最后一条评论
	}
}
	</script>
</script>
</head>
<body>
<table width="600" height="70" border="0" align="center" cellpadding="0" cellspacing="1" bordercolorlight="#FF9933" bordercolordark="#FFFFFF" bgcolor="#666666">
  <thead>
    <tr>
      <td width="14%" align="center" bgcolor="#FFFFFF"><img src="images/head.png" width="70" height="74"></td>
      <td width="86%" align="left" bgcolor="#FFFFFF">&nbsp;人生若真如一场大梦，这个梦倒也很有趣的。在这个大梦里，一定还有长长短短，深深浅浅，肥肥瘦瘦、甜甜苦苦，无数的小梦。有些已经随着日影飞去；有些还远着哩……</td>
    </tr>
  </thead>
</table>
<br>
<table width="600" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#666666" bordercolordark="#FFFFFF" id="comment">
  <tr>
	<td width="18%" height="27" align="center" bgcolor="#E5BB93">评论人</td>
	<td width="82%" align="center" bgcolor="#E5BB93">评论内容</td>
  </tr>
</table>
<form name="form1" method="post" action="">    
  <table width="600" height="122" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="119" height="14">&nbsp;</td>
      <td width="481">&nbsp;</td>
    </tr>
    <tr>
      <td height="27" align="center">评 论 人：</td>
      <td>
        <input name="person" type="text" id="person" size="40">

      </td>
    </tr>
    <tr>
      <td align="center">评论内容：</td>
      <td><textarea name="content" cols="60" rows="6" id="content"></textarea></td>
    </tr>
    <tr>
      <td height="40">&nbsp;</td>
      <td><input name="Button" type="button" class="btn_grey" value="发表" onClick="addElement()">
      &nbsp;
      <input name="Reset" type="reset" class="btn_grey" value="重置">
      &nbsp;
      <input name="Button" type="button" class="btn_grey" value="删除第一条评论" onClick="deleteFirstE()">
      &nbsp;
      <input name="Button" type="button" class="btn_grey" value="删除最后一条评论" onClick="deleteLastE()"></td>
    </tr>
  </table>
</form>
</body>
</html>
