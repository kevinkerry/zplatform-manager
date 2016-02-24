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
						<td align="right" width="10%">提现订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="twq.withdraworderno" id="withdrawordernos" maxlength="32" />
						</td>

						<td align="right" width="10%">提现类型:</td>
						<td colspan="1"><select name="twq.withdrawtype"
							class="easyui-validatebox validatebox-text" id="withdrawtypes">
								<option value="">请选择</option>
								<option value="0">个人</option>
								<option value="1">商户</option>

						</select></td>

						<td align="right" width="10%">会员号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="twq.memberid" id="memberids" maxlength="32" /></td>


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




	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="firstTrial" method="post"
					action="pages/withdraw/queryTrialWithdraTriaAction.action">
					<input id="withdraworderno" type="hidden"
						name="ftb.orderNo"> <input id="falg" type="hidden"
						name="ftb.falg">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="center">初审意见:</td>
							<td><textarea rows="5" cols="80" name="ftb.stexaopt">
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



	<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<div>
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="solid">
		<tr><td whdth="40px" align="center">&nbsp;会员号:</td><td align="center" whdth="40px" id="tmemberid"></td> 
			<td whdth="40px" align="center" >&nbsp;提现类型:</td><td whdth="40px"  align="center"  id="twithdrawtype"></td> </tr>
			<tr><td whdth="40px"  align="center">&nbsp;提现金额:</td><td align="center" whdth="20%"  id="tamount"></td> 
			<td  whdth="20%" align="center" >&nbsp;银行账号:</td><td align="center" whdth="20%"  id="tacctno"></td> </tr>
			<tr><td whdth="20%"  align="center">&nbsp;银行账户名称:</td><td  align="center" whdth="20%"  id="tacctname"></td> 
			<td whdth="20%"  align="center">&nbsp;银行代码:</td><td align="center" whdth="20%"  id="tbankcode"></td> </tr>
			<tr><td whdth="20%"  align="center" >&nbsp;支行名称:</td><td align="center" whdth="20%"  id="tbankname"></td> 
			<td whdth="20%"  align="center">&nbsp;写入时间:</td><td align="center" whdth="20%"  id="tintime"></td>
			</tr>
		
					</table>
				</div>
				<br>
				<form id="firstTrial" method="post"
					action="pages/withdraw/queryTrialWithdraTriaAction.action">
					<input id="withdraworderno" type="hidden"
						name="ftb.orderNo"> <input id="falg" type="hidden"
						name="ftb.falg">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="center">初审意见:</td>
							<td><textarea rows="5" cols="80" name="ftb.stexaopt">
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
							title : '会员提现信息表',
							iconCls : 'icon-save',
							height : 400,
							singleSelect : true,
							nowrap : false,
							striped : true,
							url : 'pages/withdraw/queryTrialWithdraTriaAction.action?falg=first',
							remoteSort : false,
							idField : 'ORGAN_ID',
							columns : [ [
									{
										field : 'ck',
										checkbox : true
									},
									{
										field : 'withdraworderno',
										title : '提现订单号',
										width : 120,
										align : 'center'
									},
									
									{
										field : 'memberid',
										title : '会员号',
										width : 120,
										align : 'center'
									},
									{
										field : 'withdrawtype',
										title : '提现类型',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {
											if (value == '1') {
												return '商户';
											} else if (value == '0') {
												return '个人';
											} else {
												return '';
											}
										}
									},
									{
										field : 'amount',
										title : '提现金额',
										width : 120,
										align : 'center'
									},
									{
										field : 'bankname',
										title : '支行名称',
										width : 120,
										align : 'center'
									},
									{
										field : 'acctno',
										title : '银行账号',
										width : 120,
										align : 'center'
									},
									{
										field : 'fee',
										title : '提现手续费',
										width : 120,
										align : 'center'
									},
									{
										field : 'status',
										title : '状态',
										width : 120,
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
												return '提现成功';
											} else if (value == '39') {
												return '自行终止';
											} else {
												return '';
											}
										}
									},
							
									{
										field : 'txnseqno-',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.withdraworderno != null) {
												return '<a href="javascript:getWithdraw(\''
														+ rec.withdraworderno
														+ '\')" style="color:blue;margin-left:10px">审核</a>';
											} else {  
												return '';
											}
										}
									} ] ],
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							pagination : true,
							rownumbers : true,
							toolbar : [ {
								id : 'btnadd',
								text : '批量审核',
								iconCls : 'icon-add',
								handler : function() {
									var check = $('#test').datagrid(
											'getChecked');
									if (check.length != 0) {
										var myArray = "";
										for (var i = 0; i < check.length; i++) {
											myArray += check[i].withdraworderno
													+ "|"
										}
										$("#firstTrial")[0].reset();
										$("#btn_submit").linkbutton('enable');
										$("#icon-cancel").linkbutton('enable');
										
										$("#withdraworderno").val(myArray);
										showAdd();
									} else {
										$.messager.alert('提示', "请选择数据");

									}
								}
							} ]
						});
	});

	function search() {

		var data = {
			"twq.withdraworderno" : $('#withdrawordernos').val(),
			"twq.withdrawtype" : $('#withdrawtypes').val(),
			"twq.memberid" : $('#memberids').val()
		}
		$('#test').datagrid('load', data);
	}

	function showAdd() {

		$('#w').window({
			title : '批量审核',
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
	function closeAdd() {
		$('#w').window('close');
		$('#ws').window('close');
	}

	/* function a(falg){
		if(falg==true){
			
			$("#firstTrial").attr("action",
					"pages/withdraw/queryTrialWithdraTriaAction.action");
			}else{
				$("#firstTrial").attr("action",
				"pages/withdraw/queryTrialWithdraTriaAction.action");
			}
		
	} */

	function firstTrial(falg) {
		if (falg == true) {
			$("#firstTrial").attr("action",
					"pages/withdraw/firstTrialTriaAction.action");
			$("#falg").val("true");
		} else {
			$("#firstTrial").attr("action",
					"pages/withdraw/firstTrialTriaAction.action");
			$("#falg").val("false");

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
				$.messager.alert('提示', data);
				search();
				closeAdd();

			}
		});
	}
	
	function showAdds() {

		$('#ws').window({
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
		
		
		
	}
	
	/*单笔审核**/
	function getWithdraw(withdraworderno){
		var ison=false;
		$.ajax( { 
				type: "POST",
	             url: "pages/withdraw/queryTrialWithdraTriaAction.action?falg=first",
	             data: {"twq.withdraworderno":withdraworderno},
	             dataType: "json",
	             success: function(data){
	            var	json=data.rows[0];
	            if(json==null){
	            	$.messager.alert('提示',"数据不正确，请刷新后重试");	
	            }else{
	            	 $("#tmemberid").html(json.memberid);
	            	 if(json.withdrawtype=='1'){
	            		 $("#twithdrawtype").html('商户')
	            	 }else if(json.withdrawtype=='0'){
	            		 $("#twithdrawtype").html('个人')
	            	 }
	            	
	            	 $("#tamount").html(json.amount+"(元)");
	            	 $("#tacctno").html(json.acctno);
	            	 $("#tacctname").html(json.acctname);
	            	 $("#tbankcode").html(json.bankcode);
	            	 $("#tbankname").html(json.bankname);
	            	 $("#ttxntime").html(json.txntime);
	            	 $("#withdraworderno").val(json.withdraworderno);
	            	 $("#tintime").html(json.intime);
	           isok=true;
	            }
	             }
		})
		if(isok==true){
		showAdds();
		}
		}
	
</script>
</html>
