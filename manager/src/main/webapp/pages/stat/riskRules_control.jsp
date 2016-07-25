<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<body>
    <div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:150px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post" action="">
				<table width="100%">
					<tr>
					    <td align="center" width="10%">商户/会员/银行卡号</td>
						<td align="left" style="padding-left:4px" width="15%">
							<input id="memberid" name="riskAnalyseLogQueryBean.memberid"/>
						</td>
						
						<td align="center" width="10%">规则类型</td>
						<td align="left" style="padding-left:4px" width="15%">
						   <select id="roletype" class="easyui-validatebox" name="riskAnalyseLogQueryBean.roletype" onchange="showRulecodeList()" missingMessage="请选择规则类型" required="true">
						       <option value="">--请选择规则类型--</option>
						       <option value="1">商户规则</option>
						       <option value="2">个人规则</option>
						       <option value="3">账户规则</option>						       
						   </select>						   
						</td>
						
						<td align="center" width="10%">规则编号</td>
						<td align="left" style="padding-left:4px" width="40%">
							<select id="rulecode"  class="easyui-validatebox" name="riskAnalyseLogQueryBean.rolecode" >
							     <option value="">--请选择规则描述--</option>
							</select>
						</td>										
					</tr>
					
	                <tr>    
						<td align="center" width="15%">开始时间</td>						
						<td align="left" style="padding-left:4px" width="25%">
						    <input id="startdate" name="riskAnalyseLogQueryBean.startdate" type="text"/>
						</td>
						
						<td align="center" width="15%">结束时间</td>
						<td align="left" style="padding-left:4px" width="25%">
						    <input id="enddate" name="riskAnalyseLogQueryBean.enddate" class="easyui-validatebox"/>
						</td>																				
						
						<td align="center" width="15%"></td>
						<td align="left" style="padding-left:4px" width="25%">
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<form id ="testform" method="post" action ="">		
				<table id="test"></table>
			</form>
		</div>
	</div>
</body>

<script>

	$(function(){
		$('#startdate,#enddate').datebox({});

		$('#test').datagrid({
	  		title: '风控规则报表',
	  		iconCls: 'icon-save',
	  		height: 490,
	  		singleSelect: true,
	  		nowrap: false,
	  		striped: true,
	  		url: 'pages/stat/queryRiskRulesControlStatAction.action',
	  		remoteSort: false,	  		
	  		columns: [[
	  		{
	  			field: 'ROLECODE',
	  			title: '规则编号',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'ROLENAME',
	  			title: '触犯规则类',
	  			align: 'center',
	  			width: 250
	  		},
	  		{
	  			field: 'MEMBERID',
	  			title: '商户号\会员号\银行卡号',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'DATES',
	  			title: '日期',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'PARAMETER',
	  			title: '数值',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'INTIME',
	  			title: '写入时间',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'NOTES',
	  			title: '备注',
	  			align: 'center',
	  			width: 150
	  		}
	  		]],
		pagination: true,
		rownumbers: true,
	  	});
	})

	function showRulecodeList(){
		var roletype = $('#roletype').val();
		var html ="";
		$.ajax({
			type: "POST",
			url: "pages/stat/showRulecodeListStatAction.action?roletype=" + roletype,	
			dataType: "json",
			success: function(json) {
				var html = "<option value=''>--请选择规则描述--</option>";
				$.each(json,function(key, value) {	
					html += '<option value="'+ value.ROLECODE +'">'+value.ROLECODE+' —— '+value.ROLEDESC+'</option>';				
				}) ;
				$("#rulecode").html(html);
	
			}
		});
	}
	
	function search() {
		var data = {
			'riskAnalyseLogQueryBean.roletype':$('#roletype').val(),
			'riskAnalyseLogQueryBean.rolecode': $('#rulecode').val(),
			'riskAnalyseLogQueryBean.memberid': $('#memberid').val(),
			'riskAnalyseLogQueryBean.startdate':$('#startdate').datebox('getValue'),
			'riskAnalyseLogQueryBean.enddate':  $('#enddate').datebox('getValue')
		}
			
		var roletype = $('#roletype').val();
		if(roletype ==""){
			queryAll();			
		}else if(roletype ==1){
			queryMerch(roletype,data); 
		}else if(roletype ==2 ){
			queryPerson(roletype,data);			
		}else {
			queryAccount(roletype,data);			
		}
		
	}
	
	//查询所有的触犯规则的记录
	function queryAll(){
		$('#test').datagrid({
		  		title: '风控规则报表',
		  		iconCls: 'icon-save',
		  		height: 490,
		  		singleSelect: true,
		  		nowrap: false,
		  		striped: true,
		  		url: 'pages/stat/queryRiskRulesControlStatAction.action',
		  		remoteSort: false,	  		
		  		columns: [[
		  		{
		  			field: 'ROLECODE',
		  			title: '规则编号',
		  			align: 'center',
		  			width: 150
		  		},
		  		{
		  			field: 'ROLENAME',
		  			title: '触犯规则类',
		  			align: 'center',
		  			width: 250
		  		},
		  		{
		  			field: 'MEMBERID',
		  			title: '商户号\会员号\银行卡号',
		  			align: 'center',
		  			width: 150
		  		},
		  		{
		  			field: 'DATES',
		  			title: '日期',
		  			align: 'center',
		  			width: 150
		  		},
		  		{
		  			field: 'PARAMETER',
		  			title: '数值',
		  			align: 'center',
		  			width: 150
		  		},
		  		{
		  			field: 'INTIME',
		  			title: '写入时间',
		  			align: 'center',
		  			width: 150
		  		},
		  		{
		  			field: 'NOTES',
		  			title: '备注',
		  			align: 'center',
		  			width: 150
		  		}
		  		]],
			pagination: true,
			rownumbers: true,
		  	});
	}
	
	//商户 
	function queryMerch(roletype,data){
		
		$('#test').datagrid({
	  		title: '风控规则报表',
	  		iconCls: 'icon-save',
	  		height: 490,
	  		singleSelect: true,
	  		queryParams:data,
	  		nowrap: false,
	  		striped: true,
	  		url: 'pages/stat/queryRiskRulesControlStatAction.action?roletype=' + roletype,
	  		remoteSort: false,	  		
	  		columns: [[
	  		{
	  			field: 'ROLECODE',
	  			title: '触犯规则编号',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'MEMBERID',
	  			title: '商户ID',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'tradetype',
	  			title: '交易类型',
	  			align: 'center',
	  			formatter:function(){return '消费';},
	  			width: 150
	  		},
	  		{
	  			field: 'ROLENAME',
	  			title: '触犯规则类',
	  			align: 'center',
	  			width: 250
	  		},
	  		
	  		{
	  			field: 'DATES',
	  			title: '日期',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'PARAMETER',
	  			title: '数值',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'INTIME',
	  			title: '写入时间',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'NOTES',
	  			title: '备注',
	  			align: 'center',
	  			width: 150
	  		}

	  		]],
		pagination: true,
		rownumbers: true,
		toolbar: [{
			id: 'btnadd',
			text: '导出商户交易规则报表',
			iconCls: 'icon-add',
			handler: function() {
				exportMerchExcel(roletype);
			}
		}]
	  	});	
	}
	//个人 
	function queryPerson(roletype,data){
		$('#test').datagrid({
	  		title: '风控规则报表',
	  		iconCls: 'icon-save',
	  		height: 490,
	  		singleSelect: true,
	  		queryParams:data,
	  		nowrap: false,
	  		striped: true,
	  		url: 'pages/stat/queryRiskRulesControlStatAction.action?roletype=' + roletype,
	  		remoteSort: false,	  		
	  		columns: [[
	  		{
	  			field: 'MEMBERID',
	  			title: '会员编号',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'ROLECODE',
	  			title: '触犯规则编号',
	  			align: 'center',
	  			width: 150
	  		},
			{
				field: 'member_name',
				title: '姓名',
				align: 'center',
				width: 100
			},
			{
				field: 'idnum',
				title: '身份证号',
				align: 'center',
				width: 150
			},
			{
	  			field: 'phone',
	  			title: '注册手机号',
	  			align: 'center',
	  			width: 150
	  		},

	  		{
	  			field: 'ROLENAME',
	  			title: '触犯规则类',
	  			align: 'center',
	  			width: 250
	  		},

	  		{
	  			field: 'DATES',
	  			title: '日期',
	  			align: 'center',
	  			width: 100
	  		},
	  		{
	  			field: 'PARAMETER',
	  			title: '数值',
	  			align: 'center',
	  			width: 100
	  		},
	  		{
	  			field: 'INTIME',
	  			title: '写入时间',
	  			align: 'center',
	  			width: 100
	  		},
	  		{
	  			field: 'NOTES',
	  			title: '备注',
	  			align: 'center',
	  			width: 100
	  		}
	  		]],
		pagination: true,
		rownumbers: true,
		toolbar: [{
			id: 'btnadd',
			text: '导出个人钱包账户规则报表',
			iconCls: 'icon-add',
			handler: function() {
				exportPersonExcel(roletype);
			}
		}]
	  	});	
	}
	//账户 
	function queryAccount(roletype,data){
		$('#test').datagrid({
	  		title: '风控规则报表',
	  		iconCls: 'icon-save',
	  		height: 490,
	  		singleSelect: true,
	  		nowrap: false,
	  		striped: true,
	  		queryParams:data,
	  		url: 'pages/stat/queryRiskRulesControlStatAction.action?roletype=' + roletype,
	  		remoteSort: false,	  		
	  		columns: [[
	  		{
	  			field: 'ROLECODE',
	  			title: '触犯规则编号',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'MEMBERID',
	  			title: '支付卡号', 
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'ROLENAME',
	  			title: '触犯规则类',
	  			align: 'center',
	  			width: 250
	  		},
	  		{
	  			field: 'DATES',
	  			title: '日期',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'PARAMETER',
	  			title: '数值',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'INTIME',
	  			title: '写入时间',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'NOTES',
	  			title: '备注',
	  			align: 'center',
	  			width: 150
	  		}
	  		]],
		pagination: true,
		rownumbers: true,
		toolbar: [{
			id: 'btnadd',
			text: '导出账户安全规则报表',
			iconCls: 'icon-add',
			handler: function() {
				exportAccountExcel(roletype);
			}
		}]
	  	});		
	}
	
	//导出功能
	function exportMerchExcel(roletype,data){
		$('#theForm').attr("action","pages/stat/exportMerchExcelStatAction.action?roletype="+roletype);
		$('#theForm').submit();
	}
	function exportPersonExcel(roletype,data){
		$('#theForm').attr("action","pages/stat/exportPersonExcelStatAction.action?roletype="+roletype );
		$('#theForm').submit();
	}
	function exportAccountExcel(roletype,data){
		$('#theForm').attr("action","pages/stat/exportAccountExcelStatAction.action?roletype="+roletype);
		$('#theForm').submit();
	}

</script>
</html>