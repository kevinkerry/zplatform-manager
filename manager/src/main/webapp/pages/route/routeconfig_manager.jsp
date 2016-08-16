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
		<div style="margin: 5px; border: " id="continer">
			<div id="p" class="easyui-panel" title="路由配置信息查询"			
				style="height: 100px; padding: 10px; background: #fafafa;"
				iconCls="icon-save" collapsible="true">
				<form id="dedurateForm" method="post">
					<table width="100%">
						<tr>						
							<td align="right" width="15%">路由版本名称</td>
							<td align="left" style="padding-left: 5px" width="25%">
								<input name="routeModel.routname" id="routname_qid" class="easyui-validatebox"/>
							</td>
							<td align="right" width="15%">路由版本代码</td>
							<td align="left" style="padding-left: 5px" width="25%">
								<input name="routeConfigModel.merchRoutver" id="merchRoutver_qid" class="easyui-validatebox"/>
							</td>
						</tr>
						<tr>
						    <td align="right" width="15%">交易渠道</td>
						    <td align="left" style="padding-left:5px" width="25%">
								<select id="routver_qid"  name="routeConfigModel.routver" class="easyui-validatebox" >
								    <option value="">--请选择--</option>
								    <option value="10000001">消费</option>
								    <option value="20000001">充值</option>								    
								</select>
						   </td>
			
							<td align="right" width="15%">状态</td>
							<td align="left" style="padding-left: 5px" width="25%">
							    <select id="status_qid" name="routeConfigModel.status" class="easyui-validatebox" >
								    <option value="">--请选择--</option>
								    <option value="0">在用</option>
								    <option value="1">停用</option>								    
								</select>					
							</td>
						
						</tr>
						<tr>
							<td align="right" colspan=2>
								<a href="javascript:search()" class="easyui-linkbutton"
									iconCls="icon-search">查询</a>
								
							</td>
						</tr>
							

					</table>
				</form>
			</div>
			<div style="margin-top: 5px">
				<table id="test"></table>
			</div>
		</div>
		<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;text-align: center">
				<form id="theForm"   method="post" action="pages/route/saveRouteRouteAction.action" >
				<input name="routeModel.routid" id="routid" type="hidden"/>
				<table width="100%" cellpadding="2" cellspacing="2" style="text-align: left" id="inputForm">
					<tr>
						<td align="right" width="15%" height="50px" >路由版本代码</td>
						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeModel.routver" id="routver" required="true" validType="minLength[8,8]" maxlength="8" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>
						
					</tr>
					<tr></tr>
					<tr>						
						<td align="right" width="15%">备注</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<input name="routeModel.note" id="notes" maxlength="64"/>
						</td>
						
						<td align="right" width="15%">提示:请在备注处填写注销理由<font color="red">*</font></td>
						</td>
					</tr>
					
					
				</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:saveRoute()" id="btn_submit" onclick="">保存</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	</body>

	<script>
		var width = $("#continer").width();
	  	var gridHeight = 540;
		var panelWidth = 640;
		var panelHeight = 260;
		var panelHoriFloat = (width-panelWidth)/2;
		var panelVertFloat = 150;
	
		$(function() {
			$('#test').datagrid({
				title: '路由版本列表', 
				singleSelect: true,
				iconCls: 'icon-save',
				height: gridHeight,
				nowrap: false,
				striped: true,
				sortName: 'ROUTVER',
				url: 'pages/route/queryRouteConfigRouteAction.action',
				remoteSort: false,
				columns: [[				    
					{field: 'MERCHROUTVER',title: '路由版本代码',width: 100,align: 'center'},
				    {field: 'ROUTNAME',title: '路由版本名称',width: 100,align: 'center'},
				    {field: 'STIME',title: '开始时间',width: 100,align: 'center'},
				    {field: 'ETIME',title: '结束时间',width: 100,align: 'center'},
				    {field: 'MINAMT',title: '最小金额',width: 100,align: 'center'},
				    {field: 'MAXAMT',title: '最大金额',width: 100,align: 'center'},
				    {field: 'BANKCODE',title: '发卡行',width: 150,align: 'center'},
				    {field: 'BUSICODE',title: '交易类型',width: 150,align: 'center'},
				    {field: 'CARDTYPE',title: '卡类型',width: 100,align: 'center'},
				    {field: 'INTIME',title: '写入时间',width: 100,align: 'center'},
				    {field: 'UPTIME',title: '更新时间',width: 100,align: 'center'},
				    {field: 'ORDERS',title: '优先级',width: 100,align: 'center'},
				    {field: 'ISDEF',title: '是否为默认路由',width: 100,align: 'center',
				    	formatter: function(value, rec){
				    		if(value == 0){
				    			return "默认路由";
				    		}else if(value == 1){
				    			return "非默认路由";
				    		}
				    	}
				    }, 				    
				    {field: 'STATUS',title: '状态',width: 100,align: 'center',
				    	formatter: function(value, rec){
				    		if(value == 00){
				    			return "在用";
				    		}else if(value == 01){
				    			return "停用";
				    		}
				    	}
				    },
				    {field: 'NOTES',title: '备注',width: 100,align: 'center'},				
				    {field: 'ROUTID',title: '操作',width: 150,align: 'center', 
						formatter: function(value, rec) {
							if(rec.STATUS ==00){
								return '<a href="javascript:showRouteConfig(' + value + ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteRouteConfig('+ value + ')" style="color:blue;margin-left:10px">注销</a>';
							}else if(rec.STATUS ==01){
								return '<a href="javascript:startRouteConfig(' + value + ')" style="color:blue;margin-left:10px">启用</a>';
							}
							
					}
				}]],
				pagination: true,
				rownumbers: true,
				toolbar: [{
					id: 'btnadd',
					text: '新增路由版本',
					iconCls: 'icon-add',
					handler: function() {
						showAdd();
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh: function() {
	
		}
			});
		});
	
		function search() {
			var data = {
				'routeModel.routname': $("#routname_qid").val()
			};
			$('#test').datagrid('load', data);
		}
	

		
		//新增路由版本 
		function showAdd() {
			$('#theForm').clearForm();
		  	$.ajax({
		  		type: "POST",
		  		url: "pages/route/queryRoutverRouteAction.action",
		  		data:"",
		  		async: false,
		  		dataType: "json",
		  		success: function(json) {
		  			$("#routver").val(json.ROUTVER);
                    $("#routver").attr("disabled","disabled");
		  		}
		  	});	
			$('#w').window({
				title: '扣率版本信息',
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action", "pages/route/saveRouteRouteAction.action");
			$('#btn_submit').linkbutton('enable');
		}
		
		//保存 
		function saveRoute(){
			$("#routver").removeAttr("disabled");
			$('#theForm').form('submit', {
				onSubmit: function() {
					if ($('#theForm').form('validate')) {
						$('#btn_submit').linkbutton('disable');
						return true;
					}
					return false;
				},
				success: function(data) {
					if (data == '添加成功!' || data == '修改成功!' ||data == '注销成功!') {
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

		//取消
		function closeAdd() {
			$('#w').window('close');
		}

		//修改
		function showRoute(routid){
			$.ajax({
				type: "POST",
				url: "pages/route/queryOneRouteRouteAction.action",
				data: "routid=" + routid,
				dataType: "json",
				success: function(json) {
					$("#routid").val(routid);
					$("#routver").val(json.ROUTVER);
					$("#routver").attr("readonly","readonly");
					$("#routname").val(json.ROUTNAME);
					$("#notes").val(json.NOTES);										
				}	
			});
			$('#w').window({
				title: '修改路由版本',
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action", "pages/route/updateRouteRouteAction.action");
			$('#btn_submit').linkbutton('enable');
		}
	    //注销 
		function deleteRoute(routid){
			$.ajax({
				type: "POST",
				url: "pages/route/queryOneRouteRouteAction.action",
				data: "routid=" + routid,
				dataType: "json",
				success: function(json) {
					$("#routid").val(routid);
					$("#routver").val(json.ROUTVER);
					$("#routver").attr("readonly","readonly");
					$("#routname").val(json.ROUTNAME);
					$("#routname").attr("readonly","readonly");
					$("#notes").val(json.NOTES);										
				}	
			});
			$('#w').window({
				title: '注销路由版本', 
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action", "pages/route/deleteRouteRouteAction.action"); 
			$('#btn_submit').linkbutton('enable');
	    }
		
	

</script>
</html>

