<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<script type="text/javascript">
			$(function() {
				$.extend($.fn.validatebox.defaults.rules, {
					equalTo: {
						validator: function(value, param) {
							return value == $(param[0]).val();
						},
						message: '两次输入的字符不一至'
					}
			
				});
				//检测当前用户密码有效期
				checkPwdTime();
				$('#tt').tabs('add', {
					title: "首页",
					content: '<iframe  scrolling="auto" frameborder="0"  style="width:100%;height:100%"></iframe>',
					closable: true,
					fit: true
				});
			
				$('#tt').tabs({
					onClose: function(title) {
						if ($('#tt').tabs('tabs') == "" || $('#tt').tabs('tabs') == null) {
							$('#tt').tabs('add', {
								title: "首页",
								content: '<iframe  scrolling="auto" frameborder="0"  style="width:100%;height:100%"></iframe>',
								closable: true,
								fit: true
							});
						}
					},
					onBeforeClose: function(title) {
			
						if ($('#tt').tabs('tabs').length == 1) {
							if (title == "首页") {
								return false;
							}
						}
			
						return true;
					}
				});
			
				pwdexpirDay();
			})
			
			function pwdexpirDay() {
				var day = $('#pwdDay').val();
				if (day < 6) {
					//$('#expiration').html('用户密码有效期还有'+day+"天到期，请及时更新您的密码！");
					$('#expiration').html('距离您的登录密码到期还有' + day + "天，请您及时更新密码！");
				} else {
					$('#expiration').html("");
				}
			}
			
			function checkPwdTime() {
				var pwdFlag = $('#pwdFlag').val();
				if (pwdFlag > 0) { //密码有效期过期
					showPassword(); //显示密码修改页面
					$('#w').window({
						onBeforeClose: function() {
							$.messager.alert('提示', '为了您账户的安全性，请及时修改登录密码，否则无法进入本系统！', '',
							function() {
								returnLogin();
							});
						}
					});
			
				}
			}
			
			function addTab(id) {
				var pp = $('#tt').tabs('getTab', '首页');
				if (pp != null && pp != '') {
					var tab = pp.panel('options').tab;
				}
				if ($('#tt').tabs('exists', $('#' + id).text())) {
					$('#tt').tabs('select', $('#' + id).text());
				} else {
					$('#tt').tabs('add', {
						title: $('#' + id).text(),
						content: '<iframe  scrolling="auto" frameborder="0" src="' + $('#' + id).attr("src") + '" style="width:100%;height:100%"></iframe>',
						closable: true,
						fit: true
					});
				}
			
			}
			
			function addTabPOS(titlename, urlname) {
				alert("AccEntry=" + AccEntry);
				alert("urlname=" + urlname);
				$('#tt').tabs('add', {
					title: titlename,
					content: '<iframe  scrolling="auto" frameborder="0" src="' + urlname + '" style="width:100%;height:100%"></iframe>',
					closable: true,
					fit: true
				});
			
			}
			
			function ConfirmLogout() {
				$.messager.confirm('操作', '确认退出?',
				function(r) {
					if (r) {
						window.parent.location.href = "<%=basePath%>" + "/pages/logoutAction.action";
					}
				});
			}
			
			function returnLogin() {
				window.parent.location.href = "<%=basePath%>" + "/pages/logoutAction.action";
			}
			
			function showPassword() {
				$('#theForm :input').val('');
				$('#w').window({
					title: "修改密码",
					width: 400,
					modal: true,
					collapsible: false,
					minimizable: false,
					maximizable: false,
					shadow: false,
					closed: false,
					height: 200
				});
			}
			
			function closePassword() {
				$('#w').window('close');
			}
			
			function changePassword() {
				//alert(0);
				$("#oldPwd").validatebox("validate");
				$("#newPwd").validatebox("validate");
				$("#conPwd").validatebox("validate");
				//$('#theForm').form("validate")
				//alert('01');
				if ($("#oldPwd").validatebox("isValid") && $("#newPwd").validatebox("isValid") && $("#conPwd").validatebox("isValid")) {
			
					$('#theForm').ajaxSubmit({
						success: function(json) {
							json = eval('(' + json + ')');
							if (json.retcode == "succ") {
								$.messager.alert('提示', json.retinfo);
								if ($('#pwdFlag').val() > 0) {
									$('#w').window({
										onBeforeClose: function() {}
									});
									$('#expiration').html("")
								}
								closePassword();
							} else {
								$.messager.alert('提示', json.retinfo);
							}
						}
					});
				}
			}
			
			//CharMode函数  
			//测试某个字符是属于哪一类.  
			function CharMode(iN) {
				if (iN >= 48 && iN <= 57) //数字  
				return 1;
				if (iN >= 65 && iN <= 90) //大写字母  
				return 2;
				if (iN >= 97 && iN <= 122) //小写  
				return 4;
				else return 8; //特殊字符  
			}
			
			//bitTotal函数  
			//计算出当前密码当中一共有多少种模式  
			function bitTotal(num) {
				modes = 0;
				for (i = 0; i < 4; i++) {
					if (num & 1) modes++;
					num >>>= 1;
				}
				return modes;
			}
			
			//checkStrong函数  
			//返回密码的强度级别  
			function checkStrong(sPW) {
				if (sPW.length <= 8) return 0; //密码太短  
				Modes = 0;
				for (i = 0; i < sPW.length; i++) {
					//测试每一个字符的类别并统计一共有多少种模式.  
					Modes |= CharMode(sPW.charCodeAt(i));
				}
			
				return bitTotal(Modes);
			
			}
			
			//pwStrength函数  
			//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色  
			function pwStrength(pwd) {
				O_color = "#eeeeee";
				L_color = "#FF0000";
				M_color = "#FF9900";
				H_color = "#33CC00";
				if (pwd == null || pwd == '') {
					Lcolor = Mcolor = Hcolor = O_color;
				} else {
					S_level = checkStrong(pwd);
					switch (S_level) {
					case 0:
						Lcolor = Mcolor;
						Mcolor = Hcolor;
						Hcolor = O_color;
					case 1:
						Lcolor = L_color;
						Mcolor = Hcolor = O_color;
						break;
					case 2:
						Lcolor = Mcolor = M_color;
						Hcolor = O_color;
						break;
					default:
						Lcolor = Mcolor = Hcolor = H_color;
					}
				}
			
				document.getElementById("strength_L").style.background = Lcolor;
				document.getElementById("strength_M").style.background = Mcolor;
				document.getElementById("strength_H").style.background = Hcolor;
				return;
			}
		
		</script>
	</head>
	<body class="easyui-layout" >
		<div region="north" border="false"	style="height:71px;background-image: url('pictuer/bg.jpg') ">
				<input type="hidden" id="pwdFlag" value="${pwdFlag}"/>
				<input type="hidden" id="pwdDay" value="${pwdDay}"/>
				<table width="100%" height="100%" border="0">
					
					<tr>
						<td align="right"  valign="bottom" width="">
							
						</td>
						<td align="left"  valign="bottom">
							${loginName}用户,欢迎您登陆到本系统!&nbsp;&nbsp;&nbsp;&nbsp;<span id="expiration" style="color:red"></span>
						</td>
						<td align="right"  valign="bottom">
							<a id="passwordchange" style="margin-right: 10px" href="javascript:showPassword()" href="javascript:addTab('passwordchange')">修改密码</a>
		        			<a href="javascript:ConfirmLogout();"  style="margin-right: 10px">退出</a>
						</td>
					</tr>
				</table>
			
		</div>
		
		<div region="west" split="true" title="操作栏" style="width: 180px;">
			<div class="easyui-accordion" fit="true" border="false">
			
				<c:forEach var="function" items="${funlist}">
				
					<c:if test="${function.LEAFNODE==0}">
						<c:set var="myValue" value="${function.FUNCT_ID}"></c:set>
						
				        <div title="${function.FUNCT_NAME }" >
							<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
								<c:forEach var="zilist" items="${funlist}" varStatus="status">
				           			<c:if test="${zilist.PARENT_ID==myValue}">
				           			
				                  		<li>  
										  <a id=tabname${status.index}
										    src=${zilist.URL}
										    href="javascript:addTab('tabname${status.index }')">
										  	${zilist.FUNCT_NAME}</a>
								      </li>
					          		</c:if>
						       
						       	</c:forEach>
							</ul>
						</div>	
					</c:if>
					
				</c:forEach>
				 
			</div>
		</div>
		
		<div region="south" border="false"
			style="height: 25px; padding: 5px; background: #E0ECFF; text-align: center">
			 证联资本管理有限责任公司
		</div>
		
		<div region="center">
			<div class="easyui-tabs"  fit="true" border="false" id="tt" tools="#tab-tools">
			</div>
		</div>
		
		<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" style="background:#fff;border:1px solid #ccc;text-align: center">
					<form id="theForm" action="pages/system/changePasswordUserAction.action" method="post">
						
						<table width="100%" style="margin-top: 5px">
							<tr>
								<td align="right" width="40%" height="25px">
									原密码
								</td>
								<td align="left" style="padding-left: 5px">
									<input id="oldPwd" name="oldPwd" type="password" missingMessage="请输入原密码" class="easyui-validatebox"   maxlength="128"  required="true"/>
								</td>					   
							</tr>
							<tr>
								<td align="right" height="25px">
									新密码
								</td>
								<td align="left" style="padding-left: 5px">
									<input id="newPwd" name="newPwd" type="password" missingMessage="请输入新密码" validType="length[8,128]" class="easyui-validatebox"   maxlength="128"  required="true" type="password" onKeyUp=pwStrength(this.value) onBlur=pwStrength(this.value) />
								</td>					   
							</tr>
							<tr>
								<td align="right" height="25px">
									密码强度: 
								</td>
								<td align="left" style="padding-left: 5px">
									<table width="140px" border="1" cellspacing="0" cellpadding="1" bordercolor="#cccccc" height="25" style='display:inline'>  
										<tr align="center" bgcolor="#eeeeee"> 
											<td width="33%" id="strength_L">弱</td>  
											
											<td width="33%" id="strength_M">中</td>  
											
											<td width="33%" id="strength_H">强</td>  
										</tr>  
									</table>
								</td>					   
							</tr>
							<tr>
								<td align="right" height="25px">
									确认密码
								</td>
								<td align="left" style="padding-left: 5px">
									<input id="conPwd" name="conPwd" type="password" missingMessage="请输入确认密码" validType="equalTo['#newPwd']" class="easyui-validatebox"   maxlength="128"  required="true"/>
								</td>
							</tr>
						
						</table>
					</form>
				</div>
				<div region="south" border="false" style="text-align:center;padding:5px 0;">
					<a class="easyui-linkbutton" iconCls="icon-save" href="javascript:changePassword()" >提交</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:closePassword()" >取消</a>
				</div>
			</div>
		</div>
	</body>
</html>