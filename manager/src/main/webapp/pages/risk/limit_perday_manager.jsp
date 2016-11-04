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
		<div id="p" class="easyui-panel" title="单卡单日限次查询"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="dedurateForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="15%" height="30px">风控版本</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="risk_qid" class="easyui-validatebox" missingMessage="请选择风控版本"
							required="true" class="easyui-validatebox"
							onchange="queryRiskCase_query()">
								<option value="">--请选择风控版本--</option>
						</select></td>
						<td align="right" width="15%">风控业务实例</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="riskcase_qid" class="easyui-validatebox"
							missingMessage="请选择风控版本实例" required="true"
							name="limitPerdayModel.caseid" class="easyui-validatebox">
								<option value="">--请选择风控版本实例--</option>
						</select></td>
						<td align="right" colspan=2><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
	</div>
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="theForm" method="post" action="">
					<input name="limitPerdayModel.TId" id="TId" type="hidden" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="right" width="15%" height="30px">风控版本</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="risk" class="easyui-validatebox" required="true"
								missingMessage="请选择风控版本" class="easyui-validatebox"
								onchange="queryRiskCase()">
									<option value="">--请选择风控版本--</option>
							</select></td>
							<td align="right" width="15%">风控业务实例</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="riskcase" class="easyui-validatebox"
								missingMessage="请选择风控业务实例" required="true"
								name="limitPerdayModel.caseid">
									<option value="">--请选择风控版本实例--</option>
							</select></td>
						</tr>
						<tr>
							<td align="right" width="15%">卡种类</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="cardtype" class="easyui-validatebox" required="true"
								missingMessage="请选择卡种类" name="limitPerdayModel.cardtype">
									<option value="" selected="selected">--请选择卡种类--</option>
									<option value="1">借记卡</option>
									<option value="2">信用卡</option>
									<option value="3">准贷记卡</option>
							</select></td>

							<td align="right" width="15%" height="30px">限制次数</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="limitPerdayModel.nums" id="nums_id" maxlength="10"
								onkeyup="this.value=this.value.replace(/\D/g,'')"
								class="easyui-validatebox" required="true"
								missingMessage="请填写限制次数"></td>
						</tr>

						<tr>
							</td>
							<td align="right" width="15%">风险等级</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="risklevel" class="easyui-validatebox"
								missingMessage="请选择风险等级" required="true"
								name="limitPerdayModel.risklevel">
							</select></td>

							<td align="right" width="15%" height="30px">备注</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="limitPerdayModel.notes" id="Notes" maxlength="32" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveLimitPerday()" id="btn_submit" onclick="">保存</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
	$(function(){
	 showRisk_query();queryRiskCase_query();
	});  
	var width = $("#continer").width();
	var gridHeight = 600;
	var panelWidth = 640;
	var panelHeight = 260;
	var panelHoriFloat = (width - panelWidth) / 2;
	var panelVertFloat = 150;
		$(function(){
			$('#test').datagrid({
				title:'单卡单日限次列表',
				singleSelect:true,
				iconCls:'icon-save',
				height:gridHeight,
				nowrap: false,
				striped: true,
				sortName:'FEEVER',
				url:'pages/risk/queryLimitPerdayByPageLimitAction.action',
				remoteSort: false,
				columns:[
 				[
                    {field:'RISKNAME',title:'风控版本',width:150,align:'center'},
					{field:'BUSINAME',title:'业务实例',width:150,align:'center'},
					{field:'CARDTYPE',title:'卡种类',width:150,align:'center',
						formatter:function(value,rec){
							if(value=="1"){
								return '<span style="color:black;">'+'借记卡'+'</span>';
							}else if(value=="2"){
								return '<span style="color:black;">'+'信用卡'+'</span>';
							}else if(value=="3"){
								return '<span style="color:black;">'+'准贷记卡'+'</span>';
							}
						}		
					},
					{field:'RISKLEVEL',title:'风险级别',width:120,align:'center',
						formatter:function(value,rec){
							if(value=="1"){
								return '<span style="color:green;">'+'提示'+'</span>';
							}else if(value=="2"){
								return '<span style="color:blue;">'+'关注'+'</span>';
							}else if(value=="3"){
								return '<span style="color:#B8860B;">'+'预警'+'</span>';
							}else if(value=="4"){
								return '<span style="color:#CD3700;">'+'警告'+'</span>';
							}else if(value=="5"){
								return '<span style="color:red;">'+'拒绝'+'</span>';
							}
						}	
				    },
					{field:'NUMS',title:'单卡单日最大交易次数',width:150,align:'center'},
					{field:'STATUS',title:'状态',width:100,align:'center',
						formatter:function(value,rec){
							if(value=="00"){
								return "使用";
							}else{
								return "注销";
							}
						}					
					},
					{field:'NOTES',title:'备注',width:150,align:'center'},
					{field:'T_ID',title:'操作',width:150,align:'center', 			
					  formatter:function(value,rec){	
						    if(rec.STATUS=="00"){
						    	return '<a href="javascript:showLimitPerday('+value+','+rec.RISKVER+')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteLimitPerday('+value+')" style="color:blue;margin-left:10px">注销</a>';
							}else{
								return '<a href="javascript:startWhite('+value+')" style="color:blue;margin-left:10px">启用</a>';
								
					 		}
					  }
				    }
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'新增单卡单日限次规则',
					iconCls:'icon-add',
					handler:function(){
						showAdd();
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh:function(){
					
				}
			});
		});
			
		function search(){
			var data={'riskCaseModel.riskver':$('#risk_qid').val(),'riskCaseModel.busicode':$("#riskcase_qid").val()};
			$('#test').datagrid('load',data);
		}
		
		function showAdd(){	
			$('#theForm').clearForm();
			$('#risk').removeAttr('disabled','disabled');
			$('#riskcase').removeAttr('disabled','disabled');
			$('#w').window({
				title: '单卡单日限次信息',
				top: panelVertFloat, 
		  		width: panelWidth,
		  		height: panelHeight,
		  		left:panelHoriFloat,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action","pages/risk/saveLimitPerdayLimitAction.action");
			$('#btn_submit').linkbutton('enable');	
			$("#risk").removeAttr("readonly");
			$("#riskcase").removeAttr("readonly");
			showRisk();
			showRiskLevel();
			
		}		
	function resize() {
		$(':input').val("");
	}	
	function saveLimitPerday(){
		$('#theForm').form('submit', {  
		    onSubmit: function(){  
		    if($('#theForm').form('validate')){
		    	$('#btn_submit').linkbutton('disable');
		    	return true;   
			}
		        return false;   
		    },   
		    success:function(data){  
			    	if(data.split("validateUserLoginAction").length>1){
			    		window.parent.location.replace("<%=basePath%>"+"pages/logoutAction.action?relogin=relogin");
			    	}
		    		if(data=='添加成功!'||data=='修改成功!'){
		    			$.messager.alert('提示', data);
		    			closeAdd();
		    			search();
			    	}else{ 
			    		$.messager.alert('提示', data);
			    		$('#btn_submit').linkbutton('enable');		
			    	}	        
		    }   
		}); 
		
	}
	function showRiskLevel(){	
		$.ajax({
		   type: "POST",
		   url: "pages/risk/queryRiskLevelRiskAction.action",
		   dataType:"json",
		   success: function(json){
		   		var html ="<option value=''>--请选择风险等级--</option>";
		   		$.each(json, function(key,value){
		   			html += '<option value="'+value.PARA_CODE+'">'+value.PARA_NAME+'</option>';
				})
				$("#risklevel").html(html);
		   }
		});
	}
	function showLimitPerday(tId,riskver){

		$.ajax({
		   type: "POST",
		   url: "pages/risk/queryOneLimitPerdayLimitAction.action",
		   data: "riskId="+tId,
		   dataType:"json",
		   success: function(json){	
			$('#risk').attr('disabled','true');
			$('#riskcase').attr('disabled','true');
			$("#nums_id").val(json.NUMS);
			$("#TId").val(json.T_ID);
			$("#Notes").val(json.NOTES);
			$('#cardtype').val(json.CARDTYPE);			
			showRiskLevel();
			showRisk();
		    setTimeout(function(){ 
			   $("#risk").val(riskver);
			   $("#risklevel").val(json.RISKLEVEL);			   
			   queryRiskCase();	
			},500);
		    setTimeout(function(){ 
				   $("#riskcase").val(json.CASEID);				  
		   },1000);
		   }
		});
		$('#w').window({
			title: '修改单卡单日限次',
			top: panelVertFloat, 
	  		width: panelWidth,
	  		height: panelHeight,
	  		left:panelHoriFloat,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			modal: true,
			shadow: false,
			closed: false,
			height: 220
		});
		$("#theForm").attr("action","pages/risk/updateLimitPerdayLimitAction.action");
		$('#btn_submit').linkbutton('enable');
		//$("#risk").attr("disabled","disabled");
		//$('#riskcase').attr('disabled','disabled');
		//$("#risk").attr("readonly","readonly");
		//$("#riskcase").attr("readonly","readonly");
	}
	function deleteLimitPerday(tid){
		$.messager.confirm('提示','您是否想要注销此单卡单日限次?',function(r){   
		    if (r){  
		    	$.ajax({
					type: "GET",
				  	url: "pages/risk/deleteLimitPerdayLimitAction.action",
				  	data: "rand="+new Date().getTime()+"&riskId="+tid,
				 	dataType: "text",
				 	success:function(text){
		    			$.messager.alert('提示',text);   
		    			search();
				 	}
				});
		    	
		    }   
		});  
	}
	function startWhite(tid){
		$.messager.confirm('提示','您是否想要启用单卡单日限次?',function(r){   
		    if (r){  
		    	$.ajax({
					type: "GET",
				  	url: "pages/risk/startLimitPerdayLimitAction.action",
				  	data: "rand="+new Date().getTime()+"&riskId="+tid,
				 	dataType: "text",
				 	success:function(text){
		    			$.messager.alert('提示',"启用成功！");   
		    			search();
				 	}
				});
		    }   
		});  
	}
	function closeAdd(){ 
		$('#w').window('close');			
	}
	function showRisk(){		
		$.ajax({
		   type: "POST",
		   url: "pages/risk/queryRiskAllLimitAction.action",
		   dataType:"json",
		   success: function(json){
		   		var html ="<option value=''>--请选择风控版本--</option>";
		   		$.each(json, function(key,value){
		   			html += '<option value="'+value.RISKVER+'">'+value.RISKNAME+'</option>';
				}) ;
				$("#risk").html(html);
		   }
		});
	}
	function queryRiskCase(){		
		var riskver=$("#risk").val();
		$.ajax({
		   type: "POST",
		   url: "pages/risk/queryRiskCaseRiskAction.action?riskver="+riskver,
		   dataType:"json",
		   success: function(json){
		   		var html ="<option value=''>--请选择风控版本实例--</option>";
		   		$.each(json, function(key,value){
		   			html += '<option value="'+value.CASEID+'">'+value.BUSINAME+'</option>';
				});
				$("#riskcase").html(html);
		   }
		});
	}
	function showRisk_query(){		
		$.ajax({
		   type: "POST",
		   url: "pages/risk/queryRiskAllLimitAction.action",
		   dataType:"json",
		   success: function(json){
		   		var html ="<option value=''>--请选择风控版本--</option>";
		   		$.each(json, function(key,value){
		   			html += '<option value="'+value.RISKVER+'">'+value.RISKNAME+'</option>';
				}); 
				$("#risk_qid").html(html);
		   }
		});
	}
	function queryRiskCase_query(){		
		var riskver=$("#risk_qid").val();
		$.ajax({
		   type: "POST",
		   url: "pages/risk/queryRiskCaseRiskAction.action?riskver="+riskver,
		   dataType:"json",
		   success: function(json){
		   		var html ="<option value=''>--请选择风控版本实例--</option>";
		   		$.each(json, function(key,value){
		   			html += '<option value="'+value.BUSICODE+'">'+value.BUSINAME+'</option>';
				});
				$("#riskcase_qid").html(html);
		   }
		});
	}
</script>
</html>

