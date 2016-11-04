<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<style type="text/css">
table {
	
}

table tr td {
	height: 25px;
	padding-left: 10px;
	border-width: 1px 1px 1px 1px;
	border-style: groove;
}

table tr td input {
	height: 15px
}

table tr td.head-title {
	background-color: #F0F8FF;
	font-weight: bold;
}

table tr td.head-guide {
	background-color: #F0FFFF;
	font-weight: bold;
}

table tr td font.current-step {
	color: #EE4000;
}
</style>

<script type="text/javascript"
	src="<%=basePath%>/js/extendsValidator_1.0_20151215.js"></script>

<body>
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="saveForm" action="" method="post">
					<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo">
                        <tr>
							<td colspan="2" class="head-title"></td>
						</tr>
						<tr>
							<td align="center" width="20%">会员号<font color="red">*</font></td>
							<td width="30%">${accAccountMap.BUSINESS_ACTOR_ID}</td>
						</tr>
							<td align="center">科目号</td>
							<td>${accAccountMap.ACCT_CODE}</td>
						</tr>
						<tr>
							<td align="center">账户名<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.ACCT_CODE_NAME}</td>
						</tr>
						<tr>
							<td align="center">用途<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.USAGE}
						<tr>
							<td align="center">账户总金额<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.TOTAL_BALANCE}</td>
						</tr>
						<tr>
							<td align="center">可用金额<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.BALANCE }</td>
						</tr>
						<tr>
							<td align="center">冻结金额<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.FROZENBALANCE}</td>
						</tr>
						<tr>
							<td align="center">账户状态<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.STATUS}</td>
						</tr>
						<tr>
							<td align="center">所在账户组<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.PROVINCENAME }</td>
						</tr>
						<tr>
							<td align="center">账户组名称<font color="red">*</font></td>
							<td colspan="3">${accAccountMap.PROVINCENAME }</td>
						</tr>
						
					</table>
				</form>
			</div>
			
		</div>
	</div>
	
	
<script>
</script>
</body>


</html>
