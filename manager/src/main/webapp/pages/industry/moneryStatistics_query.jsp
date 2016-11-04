<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() 
			+ path + "/";
%>
<html>
<head>
</head>
<body>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询统计条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="moneyForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="20%">合作机构<font
								color="red">*</font></td>
						<td colspan="1"><select name="tlb.instiCode"
							class="easyui-validatebox validatebox-text" id="instiCode" style="width:200px">
								<c:forEach items="${coopInsti}" var="coop">																	
									<option  value=${coop.instiCode} >${coop.instiName}</option>							 							
								</c:forEach>   
						</select></td>	
									
						<td align="right">账户组</td>												
						<td colspan="1"><select name="tlb.groupCode"
							class="easyui-validatebox validatebox-text" id="groupCode" style="width:150px">
							<option value="">请选择</option>
							<c:forEach items="${list}" var="list">									
									<option  value=${list.GROUPCODE } >${list.GROUPNAME}</option>								
							</c:forEach> 
						</select></td>
						
								
						<td align="right">账户状态</td>
						<td colspan="1"><select name="tlb.accStatus"
							class="easyui-validatebox validatebox-text" id="accStatus" style="width:150px">
							<option value="">请选择</option>
							<option value="01">冻结中</option>
							<option value="00">已解冻</option>
						</select></td>
					</tr>
					<tr>
					   <td></td>
					   <td></td>
					   <td></td>
					   <td></td>	
					   <td></td>
					   <td></td>				
						<td align="right" ><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">统计</a></td>
							
						<td align="right" >
						<a id="btn" href="pages/industry/exportAllMoneyStatisticsAction.action" 
						class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a>						
					</tr>


				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
	</div>
	
</body>

<script>  
		$(function() {
			$('#test').datagrid({
				title: '行业应用专户资金统计', 
				singleSelect: true,
				iconCls: 'icon-save',
				height: 530,
				nowrap: false,
				striped: true,				
				url: 'pages/industry/queryInfoMoneyStatisticsAction.action',
				remoteSort: false,
				columns: [[				    
					{field: 'groupName',title: '账户组',width: 150,align: 'center',
						styler:function(value,rec){
							if (value =="小计:"){								
								return 'color:blue;';
							}else if(value == "合计:"){
								return 'color:red;';
							}else {
								return "color:black;";
							}
						} 
					},
				    {field: 'memberId',title: '会员号',width: 150,align: 'center'},
				    {field: 'acctCode',title: '科目号',width: 200,align: 'center'},
				    {field: 'acctName',title: '账户名',width: 150,align: 'center'},	
				    {field: 'acctStatus',title: '账户状态',width: 150,align: 'center',			    
				    	formatter: function(value,rec){
						    if(value =="正常"){
								return "已解冻";
							}else if(value =="冻结"){
								return "冻结中"; 
							}
						},
				    },
				    {field: 'totalBalance',title: '账户总金额(元)',width: 150,align: 'center'},	
				    {field: 'balance',title: '可用金额(元)',width: 150,align: 'center'},	
				    {field: 'frozenBalance',title: '冻结金额(元)',width: 150,align: 'center'}
				    
				]],
				pagination: false,
				rownumbers: true,				
			});		
		});
	
		function search() {
			var data = {
				'tlb.instiCode': $('#instiCode').val(),
				'tlb.groupCode': $("#groupCode").val(),
				'tlb.accStatus':$("#accStatus").val()
			};
			$('#test').datagrid('load', data);
		}
	


</script>
</html>

