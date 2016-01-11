<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>ERROR</title>
<style type="text/css">
<!--
body{
	font-size: 12px;
	color: #333333;
}
a {
	text-decoration: none;
}
-->
</style>
<script language="javascript">
	window.attachEvent("onload", function() {
		var imageArray = document.getElementsByTagName("IMG");
		for (var i = 0; i < imageArray.length; i++) {
			var element = imageArray[i];
			if ("true" == element.getAttribute("mouseover")) {
				var src = element.src;
				var last_indexof = src.lastIndexOf(".");
				var mouseoversrc = src.substring(0, last_indexof) + "_mouseover" + src.substring(last_indexof)
				element.setAttribute("mouseoversrc", mouseoversrc);
				element.setAttribute("mouseoutsrc", src);							
				element["onmouseover"] = function() {this.src = this.getAttribute("mouseoversrc");}
				element["onmouseout"] = function() {this.src = this.getAttribute("mouseoutsrc");	}
				delete mouseoversrc;
				delete last_indexof;
				delete src;
			}
			delete element;
		}
		delete imageArray;
	});		
</script>
</head>
<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table width="80%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#6785A7">
  <tr>
    <td height="20" align="left" bgcolor="#20A2D3"><strong style="color:#FFFFFF">错误</strong></td>
  </tr>
  <tr>
    <td height="50" align="center" valign="middle" bgcolor="#F6F6F6">当前用户没有此功能的操作权限！</td>
  </tr>
  <tr>
    <td height="20" align="center" bgcolor="#CCE2E7"><a href="javascript:history.back();"><img src="<%=basePath %>pictuer/back.gif" alt="BACK" mouseover="true"  border="0" /></a></td>
  </tr>
</table>
</body>
</html>