<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body>
	<style type="text/css">
table tr td {
	height: 25px
}

table tr td input {
	height: 15px
}

table tr td select {
	height: 20px
}
</style>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<input type="hidden" id="flag_ins" value="${flag}" />
				<table width="100%">
					<tr>
						<td align="right" width="10%">会员编号</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="enterpriseMemberId_ins" maxlength="15" /></td>
						<td align="right" width="10%">企业名称</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="enterpriseName_ins" maxlength="50" /></td>
						<td align="right" width="10%">企业状态</td>
						<td align="left" style="padding-left: 5px" width="15%"><select
							id="status_ins" class="easyui-validatebox">
								<option value='00'>在用</option>
								<option value='12'>注册待完善信息</option>
								<option value='10'>注册待初审</option>
								<option value='11'>注册初审未过</option>
								<option value='19'>注册初审终止</option>
								<option value='20'>注册待复审</option>
								<option value='21'>注册复审未过</option>
								<option value='29'>注册复审终止</option>
						</select></td>
						<td align="right" width="10%"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
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
  	var width = $("#continer").width();
		$(function(){
			flag=$('#flag_ins').val();
				$('#test').datagrid({
					title:'企业信息列表',
					iconCls:'icon-save',
					height:540,
					singleSelect:true,
					nowrap: false,
					striped: true,
					url:'pages/merchant/queryEnterpriseMerchantAction.action?flag='+flag,
					remoteSort: false,
					columns:[
					[
						{field:'MEMBER_ID',title:' 会员编号',align:'center',width:120},
						{field:'ENTERPRISE_NAME',title:'企业名称',width:120,align:'center'},
						{field:'LICENCE_NO',title:'营业执照号',width:120,align:'center'},
						{field:'CORPORATION',title:'法人名称',width:120,align:'center'}, 
						{field:'CONTACT',title:'联系人',width:120,align:'center'},
						{field:'STATUS_E',title:'状态',width:100,align:'center',
							formatter:function(value,rec){
								if(value=="00"){
									return "在用";
								}else if(value=="12"){
									return "注册待完善信息";
								}else if(value=="10"){
									return "注册待初审";
								}else if(value=="11"){
									return "注册初审未过";
								}else if(value=="19"){
									return "注册初审终止";
								}else if(value=="20"){
									return "注册待复审";
								}else if(value=="21"){
									return "注册复审未过";
								}else if(value=="29"){
									return "注册复审终止";
								}
						   }
					    },	
						{field:'DEPT_ID',title:'操作',width:150,align:'center',
						formatter:function(value,rec){
									return '<a href="javascript:toEnterpriseDetail('+rec.SELF_ID+')" style="color:blue;margin-left:10px">详情</a>';		
						}
					}			
					]],
						pagination:true,
						rownumbers:true
				});
			
			var p = $('#test').datagrid('getPager');
			$.extend($.fn.validatebox.defaults.rules, {   
			    minLength: {   
			        validator: function(value, param){ 
						var re =  /^\d+$/; 
		        		if(!re.test(value)){
							return false;
			        	}  
			            return value.length >= param[0];   
			        },   
			        message: '请输入4位数字的部门代码'  
			    }
			   
			});  
		});
		function search(){
			var data={
					'enterprise.enterpriseMemberId':$('#enterpriseMemberId_ins').val(),
					'enterprise.enterpriseName':$('#enterpriseName_ins').val(),
					'enterprise.enterpriseStatus':$('#status_ins').val()
					};
			$('#test').datagrid('load',data);
		}

		function toEnterpriseDetail(id){
			window.location.href= "<%=basePath%>" +'/pages/merchant/toEnterpriseDetailMerchantAction.action?enterpriseApplyId='+id; 
			window.event.returnValue = false;
		}

		
	</script>
</html>
