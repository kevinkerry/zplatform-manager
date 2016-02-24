<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../top.jsp"></jsp:include>
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
			style="height: 140px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="10%">一级商户号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.merId" id="merId" maxlength="32" /></td>

						<td align="right" width="10%">商户订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.orderId" id="orderId" maxlength="32" /></td>



						<td align="right" width="10%">批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.batchFileNo" id="batchFileNo" maxlength="32" /></td>

					</tr>

					<tr>
						<td align="right" rowspan="6"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>

					</tr>

				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>

	</div>
	<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="secondTrial"   method="post" action="pages/withdraw/queryTrialWithdraTriaAction.action" >
				<input id="falgs" type="hidden" name="trial.falg">
				<table width="100%" cellpadding="2" cellspacing="2" style="text-align: left" id="inputForm">
				<tr>
				<td >商户号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.merId" id="memberId" maxlength="32" />
						</td>
				
				</tr>
			
					<td >批次号:</td>
					<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.batchFileNo" id="batch" maxlength="32" />
					</td>
			
						
						
				</tr>
				<tr>
				<td >复审意见:</td>
				<td>
				<textarea rows="5" cols="80" name="trial.stexaopt">
				</textarea>
				
				</td>
				</tr>
				</table>
			</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:secondTrial(true)" id="btn_submit" onclick="">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"  id="icon-cancel" onclick="secondTrial(false)">拒绝</a>
			</div>
		</div>
	</div>
	
	
	
	
	
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"
					border="solid">
					<tr>
						<td whdth="40px" align="center">商户代码:</td>
						<td whdth="40px" align="center" id="merIds"></td>
						<td whdth="40px" align="center">商户订单号:</td>
						<td whdth="40px" align="center" id="orderIds"></td>
					</tr>
					<tr>
						<td whdth="40px" align="center">币种:</td>
						<td whdth="40px" align="center" id="currencyCode"></td>
						<td whdth="40px" align="center">金额:</td>
						<td whdth="40px" align="center" id="amt"></td>
					</tr>
					<tr>
						<td whdth="40px" align="center">账号类型:</td>
						<td whdth="40px" align="center" id="accTypes"></td>
						<td whdth="40px" align="center">账号:</td>
						<td whdth="40px" align="center" id="accNo"></td>
					</tr>
					<tr>
						<td whdth="40px" align="center">户名:</td>
						<td whdth="40px" align="center" id="accName"></td>
						<td whdth="40px" align="center">手机号:</td>
						<td whdth="40px" align="center" id="phoneNo"></td>
					</tr>

					<tr>
						<td whdth="40px" align="center">写入时间:</td>
						<td whdth="40px" align="center" id="intime"></td>

					</tr>
				</table>

				<br>
				<form id="firstTrial" method="post">
					<input id="withdraworderno" type="hidden" name="trial.orderNo">
					<input id="falg" type="hidden" name="trial.falg">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="center">初审意见:</td>
							<td><textarea rows="5" cols="80" name="trial.stexaopt">
				</textarea></td>
						</tr>
					</table>
				</form>

			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:firstTrial(true)" id="btn_submit" onclick="">通过</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" id="icon-cancel"
					onclick="firstTrial(false)">拒绝</a>
			</div>
		</div>
	</div>

</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$("#withdraworcheckbox").unbind();
		$('#test')
				.datagrid(
						{
							title : '代付信息',
							iconCls : 'icon-save',
							height : 400,
							singleSelect : true,
							nowrap : false,
							striped : true,
							url : 'pages/instead/queryInsteadInsteadAction.action?falg=first',
							remoteSort : false,
							idField : 'ORGAN_ID',
							columns : [ [
									{
										field : 'merId',
										title : '商户编号',
										width : 120,
										align : 'center'
									},
									{
										field : 'orderId',
										title : '商户订单号',
										width : 120,
										align : 'center'
									},
									{
										field : 'amt',
										title : '单笔金额',
										width : 120,
										align : 'center'
									},
									{
										field : 'accType',
										title : '账号类型',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {
											if (value == '02') {
												return '商户';
											} else if (value == '01') {
												return '个人';
											} else {
												return '';
											}
										}
									},
									{
										field : 'accNo',
										title : '账号',
										width : 120,
										align : 'center'
									},

									{
										field : 'accName',
										title : '户名',
										width : 120,
										align : 'center'
									},
									{
										field : 'bankCode',
										title : '开户行代码',
										width : 180,
										align : 'center'
									},
									{
										field : 'status',
										title : '状态',
										width : 180,
										align : 'center',
										formatter : function(value, rec) {

											if (value == '01') {
												return '待初审';
											} else if (value == '09') {
												return '初审未过';
											} else if (value == '11') {
												return '待复审';
											} else if (value == '19') {
												return '复审未过';
											} else if (value == '21') {
												return '等待批处理';
											} else if (value == '29') {
												return '批处理失败';
											} else if (value == '00') {
												return '划拨成功';
											} else if (value == '39') {
												return '自行终止';
											} else {
												return '';
											}
										}
									},
									{
										field : 'batchId',
										title : '批次号',
										width : 180,
										align : 'center'
									},
									{
										field : 'orderId-',
										title : '操作',
										width : 180,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.orderId != null) {
												return '<a href="javascript:getCharge(\''
														+ rec.txnseqno
														+ '\')" style="color:blue;margin-left:10px">审核</a>';
											} else {
												return '';
											}
										}

									}

							] ],
							/* singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false, */
							pagination : true,
							rownumbers : true,
						
							toolbar : [ {
								id : 'btnadds',
								text : '按条件审核',
								iconCls : 'icon-add',
								handler : function() {
									$("#btn_submit").linkbutton('enable');
									$("#icon-cancel").linkbutton('enable');
									$("#secondTrial")[0].reset();
									showAdd();
								}
							}
								 ] 
						});
	});

	function search() {
		var data = {
			"instead.merId" : $('#merId').val(),
			"instead.orderId" : $('#orderId').val(),
			"instead.batchFileNo" : $('#batchFileNo').val(),
		}
		$('#test').datagrid('load', data);
	}
	function closeAdd() {
		$('#ws').window('close');
		$('#w').window('close');
	}

	function filel() {
		$("#firstTrial").attr("action",
				"pages/instead/firstInsteadInsteadAction.action");
		$('#firstTrial').form('submit', {
			onSubmit : function() {
				if ($('#firstTrial').form('validate')) {
					$('#btn_submit').linkbutton('disable');
					$("#icon-cancel").linkbutton('disable');
					return true;
				}
				return false;
			},
			success : function(data) {
				$.messager.alert('提示', data);
				search();
				closeAdd();
			}
		});
	}

	function showAdd() {
	
		$('#ws').window({
			title : '按条件审核',
			top : 100,
			width : 800,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 240
		});
	}

	function showAdds() {

		$('#w').window({
			title : '单笔审核',
			top : 100,
			width : 800,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height : 500
		});

		$("#firstTrial")[0].reset();
		$("#btn_submit").linkbutton('enable');
		$("#icon-cancel").linkbutton('enable');
	}

	function firstTrial(falg) {
		$("#firstTrial").attr("action",
				"pages/instead/firstInsteadInsteadAction.action");
		if (falg == true) {
			$("#falg").val(true);
		} else {
			$("#falg").val(false);
		}

		$('#firstTrial').form('submit', {
			onSubmit : function() {
				if ($('#firstTrial').form('validate')) {
					$('#btn_submit').linkbutton('disable');
					$("#icon-cancel").linkbutton('disable');
					return true;
				}
				return false;
			},
			success : function(data) {
				var json = eval('(' + data + ')');
				if (json.falg == true) {
					$.messager.alert('提示', json.messg);
					search();
					closeAdd();
				} else {
					$.messager.alert('提示', json.messg);
					$("#btn_submit").linkbutton('enable');
					$("#icon-cancel").linkbutton('enable');
				}

			}
		});
	}

	function getCharge(chargeno) {

		var isok = false;
		$.ajax({
			type : "POST",
			url : "pages/instead/getDetailByTxnseqnoInsteadAction.action",
			data : {
				"txnserno" : chargeno
			},
			dataType : "json",
			async : false,
			success : function(json) {

				if (json == '' || json == undefined || json == null) {

					$.messager.alert('提示', "数据不正确，请刷新后重试");
				} else {

					$("#merIds").html(json.merId);
					$("#orderIds").html(json.orderId);
					if (json.currencyCode == '156') {
						$("#currencyCode").html('人民币');
					}

					$("#amt").html(json.amt + "元");
					if (json.accType == '01') {
						$("#accTypes").html('银行卡')
					} else if (json.accType == '02') {
						$("#accTypes").html('存折')
					}
					$("#accNo").html(json.accNo);
					$("#accName").html(json.accName);
					$("#phoneNo").html(json.phoneNo);
					$("#intime").html(json.intime);
					$("#withdraworderno").val(chargeno);

					isok = true;

				}
			}
		})
		if (isok == true) {
			showAdds();
		} else {
			$.messager.alert('提示', "数据不存在或者内部错误");
		}

	}
	
	
	
	function secondTrial(falg){
		
		if(falg==true){

				$("#secondTrial").attr("action",
						"pages/instead/batchFirstInsteadAction.action?falg=first");
				$("#falgs").val("true");
				$("#falg").val("true");
				$("#falgss").val("true");
			}else{

					$("#secondTrial").attr("action",
							"pages/instead/batchFirstInsteadAction.action?falg=first");
					$("#falgs").val("false");
					$("#falg").val("false");
					$("#falgss").val("false");
		
			}
			$('#secondTrial').form('submit', {  
			    onSubmit: function(){  
				    if($('#secondTrial').form('validate')){
				    	$('#btn_submit').linkbutton('disable');
						$("#icon-cancel").linkbutton('disable');
				    	return 	true;
					}
			        return false;   
			    },   
			    success:function(data){  
			    	$.messager.alert('提示',data); 
	    			search();
		    		closeAdd();
			 
			        
			    }  
			});   
		}
</script>
</html>
