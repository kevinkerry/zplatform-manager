<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 120px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm1" method="post">
				<table width="100%">
					<tr>
					    <td align="center">账户组编号</td>
						<td colspan="1"><input name="query.groupCode" maxlength="40"
							type="text" id="groupCode1" /></td>
							
						<td align="center">主体客户名称</td>
						<td colspan="1"><input name="query.memberName" type="text" id="memberName1">
										
						<td align="center">创建日期</td>
						<td><input id="startDate" type="text"
							style="width: 120PX" class="easyui-datetimebox"
							data-options="showSeconds:true" name="query.startDate"></input>
							至<input id="endDate" type="text" style="width: 120PX"
							class="easyui-datetimebox" data-options="showSeconds:true"
							name="query.endDate"></input></td>
					</tr>
					<tr>
						<td align="center">账户组名称</td>
						<td colspan="1"><input name="query.groupName" maxlength="40"
							type="text" id="groupName1" /></td>
						
						<td align="center">合作机构名称</td>
						<td colspan="1"><input name="query.instiName" maxlength="40"
							type="text" id="instiName1" /></td>

						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
	</div>
	
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 150px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 2px solid #ccc; text-align: center">
				<form id="theForm" method="post" action="pages/industry/saveAccountGroupAction.action">
				    <input type="hidden"   id="id" />
					<input type="hidden"  id="groupCode"/>
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">		
						<tr>
						    <td align="right" width="15%">账户组名称<font color="red">*</font></td>
							<td align="left" style="padding-left: 5px;padding-bottom:10px;" width="25%">
							<input name="industryGroup.groupName" id="groupName" maxlength="64" class="easyui-validatebox"  missingMessage="请输入账户组名称" required="true"/>														
							</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
						
						<tr>							
							<td align="right" width="15%">主体客户(商户)<font color="red">*</font></td>
							<td align="left" style="padding-left: 5px;padding-bottom:10px;" width="25%">
							<input name="industryGroup.memberId" id="memberId" placeholder="请输入商户编号" maxlength="15" class="easyui-validatebox" onblur="queryMerchnameAndInstiname()" missingMessage="请输入主体客户" required="true"/>							
							 <label id="memberName1"></label> 
						</tr>
						<tr>
						    <td></td>
							<td></td>
						</tr>
						
						<tr>						
							<td align="right" width="15%">合作机构</td>
							<td align="left" style="padding-left: 2px;padding-bottom:2px;" width="25px">
							<input name="industryGroup.instiName" id="instiName" maxlength="15" class="easyui-validatebox" readonly="readonly"/>
							<input type="hidden" name="industryGroup.instiCode" id="instiCode"  class="easyui-validatebox" />
							</td>
						</tr>
						<tr>
						    <td></td>
							<td></td>
						</tr>
					
						<tr>	
							<td align="right" width="15%">资金是否可提到基本账户</td>
							<td align="left" style="padding-left: 5px;padding-bottom:5px;" width="25px" height="25px">						
							<input  id="drawable" type="checkbox" name="industryGroup.drawable" style="width:20px" onclick="isSelected()" />
							<label >是</label>
							</td>
						</tr>
						<tr>
						    <td></td>
							<td></td>
						</tr>
						
						<tr>	
							<td align="right" width="15%">备注</td>
							<td align="left" style="padding-left: 5px" width="25%">
							<input name="industryGroup.note" id="note" maxlength="128" class="easyui-validatebox" maxlength="128"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveAcountGroup()" id="btn_submit" onclick="">提交</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	

    	<div id="ww" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save"
		style="width: 1000px; height: 400px; padding: 5px;">
		<input type="hidden" id="personId"> <input type="hidden"
			id="type">
		<div id="pp" class="easyui-tabs" style="width: 1100px; height: 300px">
			
			<div title="">
				<table id="accountInfoList"></table>
			</div>

		</div>
	</div>
</body>

<script>

	$(function(){
	  	  $('#intime1,#intime2').datebox({}); 
	  	  document.getElementById("drawable").checked=false;
	}); 	
  	var width = $("#continer").width();
		$(function(){
			$('#test').datagrid({
				title:'账户组列表',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/industry/queryGroupInfoAccountGroupAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
                    {field:'groupCode',title:'账户组编号',width:150,align:'center'},
                    {field:'groupName',title:'账户组名称',width:150,align:'center'},
                    {field:'memberName',title:'主体客户名称',width:150,align:'center'},
                    {field:'instiName',title:'合作机构名称',width:150,align:'center'},
					{field:'inTimeFormat',title:'创建日期',width:150,align:'center'},
					{field:'drawable',title:'资金是否可提',width:150,align:'center',
						 formatter:function(value,rec){
								if(value=="0"){
									return "是";
								}else if(value=="1"){
									return "否";
								}
							}	
					},
					{field:'note',title:'备注',width:150,align:'center'},
					{field:'id',title:'操作',width:150,align:'center',
						formatter:function(value,rec){														
								return '<a href="javascript:update(\''+rec.id+'\')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:accountList(\''+rec.groupCode+'\')" style="color:blue;margin-left:10px">账户列表</a>';						
						}
					}
				]],
				pagination:true,
				rownumbers:true,
				toolbar: [{
					id: 'btnadd', 
					text: '新增账户组', 
					iconCls: 'icon-add',
					handler: function() {
						showAdd();
					}
				}]		
			});
	
		});
	
		function search(){
			var data={
					'query.groupCode':$('#groupCode1').val(),
					'query.memberName':$("#memberName1").val(),
					'query.startDate':$("#startDate").datebox('getValue'),
					'query.endDate':$("#endDate").datebox('getValue'),
					'query.groupName':$("#groupName1").val(),
					'query.instiName':$("#instiName1").val()
			};
			 $('#test').datagrid('load',data);
		} 
		function closeAdd(){
			$('#w').window('close');
		}
		//新增账户组 				
		function showAdd() {
			$('#theForm').clearForm();
			
			$('#w').window({
				title: '账户组',
		  		width: 500,
		  		height: 300,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false,								
			});	
			isSelected();
			$("#btn_submit").linkbutton("enable");
		}
		//修改账户组
		function update(groupId){
			$('#theForm').clearForm();
 			$.ajax({
				type: "POST",
				url: "pages/industry/queryOneGroupAccountGroupAction.action",
				data: "groupId=" + groupId,
				async:false,
				dataType: "json",
				success: function(json) {
					$("#id").val(json.id);
					$("#groupCode").val(json.groupCode);
				    $("#groupName").val(json.groupName);
					$("#memberId").val(json.memberId);
					$("#instiCode").val(json.instiCode);
					$("#instiName").val(json.instiName);
					var ischecked = json.drawable;
					 if(ischecked == 1){
						$("#drawable").attr("checked","false");
					}else if(ischecked == 0){
						$("#drawable").attr("checked","true");
					} 
					$("#note").val(json.note); 
				}
			
			}); 
			$('#w').window({
				title: '账户组',
		  		width: 500,
		  		height: 300,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false,
				
			});	
			var id=$("#id").val();
			var groupCode = $("#groupCode").val();
			$("#theForm").attr("action", "pages/industry/updateAccountGroupAction.action?id="+ id +"&groupCode="+groupCode);
			$("#btn_submit").linkbutton("enable");


		}
		
		function accountList(groupCode){
			$('#ww').window({
				title: '账户列表',
		  		width: 1000,
		  		height: 600,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false,
				height: 500				
			});				
			$("#btn_submit").linkbutton("enable");
			account(groupCode);
		}
		function account(groupCode){
			$('#accountInfoList').datagrid({
				url:'pages/industry/queryAccountInfoListAccountGroupAction.action?groupCode='+groupCode,
				allowpage:false,
				columns:[
				[
				{field:'MEMBERID',title:'会员号',width:180,align:'center'},
				{field:'ACCTCODE',title:'科目号',width:180,align:'center'},
				{field:'ACCTCODENAME',title:'账户名',width:180,align:'center'},
				{field:'USAGE',title:'用途',width:180,align:'center'},	
				{field:'TOTALBALANCE',title:'账户总金额(元)',width:180,align:'center'}						
				]],		
			});
		}
		
		function saveAcountGroup(){
			$('#theForm').form('submit', {
				onSubmit: function() {
					if ($('#theForm').form('validate')) {
						$('#btn_submit').linkbutton('disable');
						return true;
					}
					return false;
				},
				success: function(data) {
					if (data == '添加成功!' || data == '修改成功!' ) {
						$.messager.alert('提示', data);
						closeAdd();
						$('#btn_submit').linkbutton('enable');
						search();
					} else {
						$.messager.alert('提示', data);
						$('#btn_submit').linkbutton('enable');
					}
				}
			});
		}
		
		function queryMerchnameAndInstiname(){
			var merchId = $("#memberId").val();
			$.ajax({
				type: "POST",
				url: "pages/industry/queryReverseInfoAccountGroupAction.action",
				data: "merchId=" + merchId,
				dataType: "json",
				success: function(nameMap) {					
					$("#instiName").val(nameMap.INSTINAME);
					$("#instiCode").val(nameMap.INSTICODE);
					
				}	
			});
		}
		
		function isSelected(){
			var chk = document.getElementById('drawable');
			if(chk.checked){
			    $("#drawable").val("0");
			}else{
				$("#drawable").val("1");
			}
			
		}
	</script>
</html>
