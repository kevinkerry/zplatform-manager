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
						<td align="right" >退款订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="txnxRefund.refundorderno" id="refundorderno" maxlength="32" />
						</td>
						<td align="right" width="10%">会员号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="txnxRefund.memberid" id="memberid" maxlength="32" /></td>

					</tr>

					<tr>
						<td align="right" ><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a>
								<a href="javascript:resize()" class="easyui-linkbutton" iconCls="icon-redo">清空</a>
							</td>
							

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
				<div>
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="solid">
		<tr><td whdth="40px" align="center">&nbsp;退款编号:</td><td align="center" whdth="40px" id="REFUNDORDERNO"></td> 
			<td whdth="40px" align="center" >&nbsp;会员号:</td><td whdth="40px"  align="center"  id="MEMBERID"></td> </tr>
			<tr><td whdth="40px"  align="center">&nbsp;原订单金额:</td><td align="center" whdth="20%"  id="OLDAMOUNT"></td> 
			<td  whdth="20%" align="center" >&nbsp;退款金额:</td><td align="center" whdth="20%"  id="AMOUNT"></td> </tr>
			<tr><td whdth="20%"  align="center">&nbsp;退款原因:</td><td  align="center" whdth="20%"  id="REFUNDDESC"></td> 
			</tr>
		
					</table>
				</div>
				<br>
				<form id="firstTrial" method="post"
					action="pages/withdraw/queryTrialWithdraTriaAction.action">
					<input id="refundordernoA" type="hidden"
						name="txnxRefund.refundorderno"/> <input id="flag" type="hidden" name="txnxRefund.flag"
						/>
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="center">初审意见:</td>
							<td><textarea  id="stexaopt" rows="5" cols="80" name="txnxRefund.stexaopt">
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

function resize() {
	$(':input').val("");
}
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
							url : 'pages/refund/queryRefundRefundAuditAction.action',
							remoteSort : false,
							columns : [ [
									
									{
										field : 'REFUNDORDERNO',
										title : '退款编号',
										width : 120,
										align : 'center'
									},
									{
										field : 'OLDTXNSEQNO',
										title : '原交易序列号',
										width : 120,
										align : 'center'
									},
									
									{
										field : 'MEMBERID',
										title : '会员号',
										width : 120,
										align : 'center'
									},
									{
										field : 'OLDAMOUNT',
										title : '原订单金额',
										width : 120,
										align : 'center'
										
									},
									{
										field : 'AMOUNT',
										title : '退款金额',
										width : 120,
										align : 'center'
									},
									{
										field : 'REFUNDDESC',
										title : '退款原因',
										width : 120,
										align : 'center'
									},
									{
										field : 'STATUS',
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
											if (rec.STATUS == '01') {
												return '<a href="javascript:getWithdraw(\''
														+ rec.REFUNDORDERNO+","+rec.MEMBERID+","+
														+ rec.OLDAMOUNT+","+rec.AMOUNT+","+
														+ rec.REFUNDDESC
														+ '\')" style="color:blue;margin-left:10px">审核</a>';
											} else {  
												return '正在处理中';
											}
										}
									} ] ],
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							pagination : true,
							rownumbers : true,
							/**toolbar : [ {
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
							} ]*/
						});
	});

	function search() {

		var data = {
			"txnxRefund.refundorderno" : $('#refundorderno').val(),
			"txnxRefund.memberid" : $('#memberid').val(),
			//"twq.memberid" : $('#memberids').val()
		}
		$('#test').datagrid('load', data);
	}

	/**function showAdd() {

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
	}*/
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
					"pages/refund/examineRefundRefundAuditAction.action");
			$("#flag").val("true");
		} else {
			$("#firstTrial").attr("action",
					"pages/refund/examineRefundRefundAuditAction.action");
			$("#flag").val("false");
		}
		$('#firstTrial').form('submit', {
			onSubmit : function() {
				if ($('#firstTrial').form('validate')) {
					//$('#btn_submit').linkbutton('disable');
					//$("#icon-cancel").linkbutton('disable');
					$("#ws").hide();
					return true;
				}
				return false;
			},
			success : function(data) {
				var json = eval('(' + data + ')')
		    	$.messager.alert('提示',json.messg); 
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
	function getWithdraw(list){
		var ison=false;
		var c=list.split(",")
		 $("#REFUNDORDERNO").html(c[0]);
		 $("#MEMBERID").html(c[1]);
		 $("#OLDAMOUNT").html(c[2]);
		 $("#AMOUNT").html(c[3]);
		 $("#REFUNDDESC").html(c[4]);
		 $("#stexaopt").val("");
		 $("#refundordernoA").val(c[0]);
		/*$.ajax( { 
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
	           
	            }
	             }
		})*/
		isok=true;
		if(isok==true){
		showAdds();
		}
		}
	
</script>
</html>
