<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录证联金融后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"href="<%=basePath%>js/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"href="<%=basePath%>js/themes/icon.css" />
		<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
		<link rel="stylesheet" type="text/css"href="<%=basePath%>css/base.css" />
		<script type="text/javascript" src="<%=basePath%>js/function.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/json2.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.form.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>
		<link href="css/login/login-box.css" rel="stylesheet" type="text/css" />
     


</head>

<body id="loginbody">
<form id="theForm" action="<%=basePath%>validateUserLoginAction.action" method="post">
<div class="loginbody">
<span class="systemlogo"></span>
<div class="loginbox">
  <ul>
    <li>
      <input  type="text" class="loginuser" value="wyj" onclick="JavaScript:this.value=''"
      size="30" maxlength="128" name="user.loginName" id="loginname" class="easyui-validatebox" required="true" missingMessage="请输入用户名"/>
    </li>
    <li>
      <input   class="loginpwd" value="123456" onclick="JavaScript:this.value=''"
      type="password"  id="pwd" name="user.pwd"   size="30" maxlength="128" class="easyui-validatebox" required="true"  missingMessage="请输入密码"/>
    </li>
    <li>  
     <div>
	     <div><input  type="text" class="check" value="验证码"  onclick="JavaScript:this.value=''"
	     size="10"  id="randcode" name="randcode"   class="easyui-validatebox"  maxlength="4"/></div >
         <div><img src="validateCodeLoginAction.action" onclick="changeValidateCode(this)" style="vertical-align: middle;cursor:pointer;position: relative;top:2px" alt="刷新验证码" id="rand_image"/></div >
	 </div>
     </li> 
    <div><span  id="info" style="color:red">&nbsp;</span></div> 
    <li> 
    <input name="input" type="button" class="loginbtn" value="登录"  href="javascript:void()" id="login_btn" />
 <!--   <input type="submit" /> -->
    </li>
  </ul>
</div>
</div>
</form>
</body>
<script type="text/javascript">
	    $(function(){
	    	
	      $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		  $(window).resize(function(){  
	      $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	      }) 
	    })
		$(function(){
			
				if("?overtime" == location.search){
					window.parent.returnLogin();
					$("#info").html("操作超时,请重新登录");
				} 
				$('#rand_image').attr("src","validateCodeLoginAction.action?rand="+new Date().getTime());
				$("#pwd,#loginname,#randcode,#loginbody").keydown(function(event){
					if(event.keyCode==13){
						$('#theForm').form('submit', {  
						    onSubmit: function(){  
						        return $('#theForm').form('validate');   
						    },   
						    success:function(data){   
						    	var json = eval('(' + data + ')')
							    if(json.ret=='success'){
					    			window.location="<%=basePath%>"+"pages/querymenuAction.action";
								}else if(json.ret=='err_user'){
									$("#info").html(json.info);
									$('#rand_image').attr("src","validateCodeLoginAction.action?rand="+new Date().getTime());
									$.ajax({
										type: "GET",
									  	url: "loginFailedCookieLoginAction.action?rand="+new Date().getTime(),
									 	dataType: "text",
									 	success:function(text){
							    			
									 	}
									});
								}else{
									$("#info").html(json.info);
									$('#rand_image').attr("src","validateCodeLoginAction.action?rand="+new Date().getTime());
								}
						    }   
						});  
				    }
									
				});
			})
			function login(){
				if($('#processfile').validatebox("isValid")){
					$('#theForm').ajaxSubmit({
						success : function(json){
					     	search();
					     	closeAdd();		
						}
					});
				}
			}
			function changeValidateCode(obj) {   
		           //获取当前的时间作为参数，无具体意义   
		        var timenow = new Date().getTime();   
		           //每次请求需要一个不同的参数，否则可能会返回同样的验证码   
		        //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。   
		        obj.src="validateCodeLoginAction.action?d="+timenow;   
		    }   

			$("#reset_btn").click(function(){
				$("#theForm :input").val('');
			})

			$('#login_btn').click(function(){
				$('#theForm').form('submit', {  
				    onSubmit: function(){  
				    	
				        return $('#theForm').form('validate');   
				    },   
				    success:function(data){   
					    var json = eval('(' + data + ')');
					    if(json.ret=='success'){
			    			window.location="<%=basePath%>pages/querymenuAction.action";
						}else if(json.ret=='err_user'){
							$("#info").html(json.info);
							$('#rand_image').attr("src","validateCodeLoginAction.action?rand="+new Date().getTime());
							$.ajax({
								type: "GET",
							  	url: "loginFailedCookieLoginAction.action?rand="+new Date().getTime(),
							 	dataType: "text",
							 	success:function(text){
					    			
							 	}
							});
						}else{
							$("#info").html(json.info);
							$('#rand_image').attr("src","validateCodeLoginAction.action?rand="+new Date().getTime());
						}
				    }   
				});  
								
			})

</script>	
</html>
