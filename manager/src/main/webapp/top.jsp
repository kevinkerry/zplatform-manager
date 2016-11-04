<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />

<title>证联金融后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="description" content="综合支付系统" />
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/base.css" />
<link rel="stylesheet" type="text/css"
	href="css/jquery.autocomplete.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/function.js"></script>
<script type="text/javascript" src="js/jquery.PrintArea.js"></script>
<script type="text/javascript" src="js/jquery.maxlength.js"></script>
<script type="text/javascript" src="js/money.js"></script>
<script type="text/javascript">
	$().ready(function(){
	 	$.messager.defaults={ok:"确定",cancel:"取消"};
	 	$().maxlength();
	}); 
	
	$.ajaxSetup({     
	    contentType:"application/x-www-form-urlencoded;charset=utf-8",     
	    complete:function(XMLHttpRequest,textStatus){  
	        // 通过XMLHttpRequest取得响应头，sessionstatus，  
	        var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");
	        if(sessionstatus=="sessiontimeout"){     
	        	$.messager.confirm('提示','您的登录已超时,请重新登录',function(r){if(r){
	            	window.parent.location.replace("<%=basePath%>"+"pages/logoutAction.action?relogin=relogin");
	        		}
	        	});
	        }  
	    }  
	}); 
	
	</script>




</head>