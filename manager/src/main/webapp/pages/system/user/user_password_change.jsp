<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <body>
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class= title="密码修改" style="height:200px;padding:10px;background:#fafafa;" iconCls="icon-save" collapsible="true">
			<form action="" id="searchForm" name="searchForm">
				<table width="100%">
					<tr>
						<td align="right">用户名称</td>
						<td align="left" style="padding-left:5px"><input id="username" name="userName" value="<s:property value="userModel.userName"/>" /></td>
					</tr>
					<tr height="4"></tr>
					<tr>
						<td align="right">原密码</td>
						<td align="left" style="padding-left: 5px"><input id="oldpassword" name="oldpassword" type="password"/></td>					   
					</tr>
					<tr height="4"></tr>
					<tr>
						<td align="right">新密码</td>
						<td align="left" style="padding-left: 5px"><input id="passwordone" name="passwordone" /></td>					   
					</tr>
					
					
					<tr height="4"></tr>
					<tr>
						<td align="right">确认密码</td>
						<td align="left" style="padding-left: 5px">
						<input id="passwordtwo" type="password"/>
					</tr>
					<tr height="4"></tr>
					<tr>
					    <td align="right"></td>
						<td align="left" style="padding-left: 5px">
						<a href="javascript:updatePassword()"  class="easyui-linkbutton" iconCls="icon-search">确定</a>
						</td>				
		
					</tr>
					<tr height="4"></tr>
					 <tr align="right">
					    <td align="right"></td>
						<td style="color:red" align="left"  id="tishi"  value="<s:property value="tishi"/>">
					         <s:property value="tishi"/>
					    </td>
					    <td align="left" style="padding-left: 5px">
					</tr>
				</table>
			</form>
		</div>

</form>
	 </div>
	 </div>
	 </div> 
  </body>
  </html>
  <script>
function updatePassword(){
	var password =document.getElementById("passwordone").value;
    var password2 =document.getElementById("passwordtwo").value;
    var password3 =document.getElementById("oldpassword").value;
if(password==""||password2==""||password3==""){
	document.getElementById("tishi").innerHTML = "密码不能为空";
	document.getElementById("password").value="";
	document.getElementById("password2").value="";
}else{
		if(password==password2){
			var form = document.forms['searchForm']; 
	        form.action="<%=basePath%>pages/system/updatePasswordUserSys.action";
	        form.submit();
		}else{
	        document.getElementById("tishi").innerHTML = "确认密码和输入密码不一致，请重新输入";
			document.getElementById("passwordone").value="";
			document.getElementById("passwordtwo").value="";
			document.getElementById("oldpassword").value="";
		}
		
	}	
 }	
		//CharMode函数  
		//测试某个字符是属于哪一类.  
		function CharMode(iN){  
			if (iN>=48 && iN <=57) //数字  
				return 1;  
			if (iN>=65 && iN <=90) //大写字母  
				return 2;  
			if (iN>=97 && iN <=122) //小写  
				return 4;  
			else  
				return 8; //特殊字符  
		}  
		
		//bitTotal函数  
		//计算出当前密码当中一共有多少种模式  
		function bitTotal(num){  
			modes=0;  
			for (i=0;i<4;i++){  
				if (num & 1) modes++;  
					num>>>=1;  
			}  
			return modes;  
		}  
		
		//checkStrong函数  
		//返回密码的强度级别  
		
		function checkStrong(sPW){  
			if (sPW.length<=4)  
				return 0; //密码太短  
			Modes=0;  
			for (i=0;i<sPW.length;i++){  
				//测试每一个字符的类别并统计一共有多少种模式.  
				Modes|=CharMode(sPW.charCodeAt(i));  
			}  
			
			return bitTotal(Modes);  
		
		}  
		
		//pwStrength函数  
		//当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色  
		
		function pwStrength(pwd){  
			O_color="#eeeeee";  
			L_color="#FF0000";  
			M_color="#FF9900";  
			H_color="#33CC00";  
			if (pwd==null||pwd==''){  
			Lcolor=Mcolor=Hcolor=O_color;  
			}  
			else{  
				S_level=checkStrong(pwd);  
				switch(S_level) {  
					case 0:  
						Lcolor=Mcolor=Hcolor=O_color;  
					case 1:  
						Lcolor=L_color;  
						Mcolor=Hcolor=O_color;  
						break;  
					case 2:  
						Lcolor=Mcolor=M_color;  
						Hcolor=O_color;  
						break;  
					default:  
						Lcolor=Mcolor=Hcolor=H_color;  
					}  
				}  
		
			document.getElementById("strength_L").style.background=Lcolor;  
			document.getElementById("strength_M").style.background=Mcolor;  
			document.getElementById("strength_H").style.background=Hcolor;  
			return;  
		}  
	
  </script>

