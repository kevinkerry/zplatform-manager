<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../top.jsp"></jsp:include>
<html>
<head>
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
<script type="text/javascript" src="js/extendsValidator_1.0_20151215.js"></script>
</head>
<body>

	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">

					<tr>
						<td align="right" width="15%">扣率版本</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="busiPack_ins" class="easyui-validatebox"
							onchange="showFeeCase_query()">
						</select></td>
						<!-- <td align="right" width="15%">业务版本号</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<input id="busiPackName" >
						</td> -->
						<td align="right"></td>
					</tr>
					<tr>
						<td align="right" width="15%">业务</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="busiCase_ins" class="easyui-validatebox">
								<option value="">--请选择业务--</option>
						</select></td>
						<td align="right" width="15%">会员号</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							id="memberid_ins"></td>
						<td align="right"><a href="javascript:search()"
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
				<form id="txnRateForm"
					action="pages/fee/saveMemberRateFeeAction.action" method="post">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left">
						<tr>
							<td width="15%">扣率版本</td>
							<td width="30%"><select id="busipack"
								class="easyui-validatebox" required="true"
								name="memberrateModel.feever" onchange="showFeeCase()">
							</select></td>
							<td width="15%">业务</td>
							<td><select id="busicase" class="easyui-validatebox"
								required="true" name="memberrateModel.busicode">
									<option value="">--请选择业务--</option>
							</select></td>
						</tr>
						<tr>
							<td width="15%">商户会员号</td>
							<td width="30%"><input id="memberid"
								name="memberrateModel.memberid" required="true" type="text"
								class="easyui-validatebox" /></td>
						</tr>
						<tr>
							<td>扣率类型</td>
							<td><select id="feeRateType" class="easyui-validatebox"
								required="true" name="memberrateModel.rateType"
								onchange="showRateDetail()">
									<option value="">--请选择扣率类型--</option>
									<option value="01">--单笔固定金额--</option>
									<option value="02">--单位比例+不封顶保底--</option>
									<option value="03">--单位比例+封顶保底--</option>
							</select></td>
							<td width="15%">扣率(百分比)</td>
							<td><input id="feeRate" name="memberrateModel.feeRateStr"
								validType="percent" required="true" type="text"
								class="easyui-validatebox" maxlength="5" /></td>
						</tr>
						<tr>
							<td>最低收费额(单位:元)</td>
							<td><input id="minFee" name="memberrateModel.minFeeStr"
								maxlength="12" validType="amount" required="true" type="text"
								class="easyui-validatebox" /></td>
							<td width="15%">最高收费额(单位:元)</td>
							<td><input id="maxFee" name="memberrateModel.maxFeeStr"
								required="true" maxlength="12" validType="amount" type="text"
								class="easyui-validatebox" /></td>
						</tr>
						<tr>
							<td>备注</td>
							<td colspan="3"><textarea id="notes" rows="3" cols="75"
									name="memberrateModel.notes" maxlength="32"></textarea></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" id="save_button" iconCls="icon-ok"
					href="javascript:saveTxnRate()" onclick="">保存</a> <a
					class="easyui-linkbutton" id="cancel_button" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
  	  var width = $("#continer").width();
  	  var gridHeight = 600;
  	  var panelWidth = 800;
  	  var panelHeight = 260;
  	  var panelHoriFloat = (width-panelWidth)/2;
  	  var panelVertFloat = 150;
	  $(function() {
			$.ajax({
				type: "POST",
				url: "pages/fee/queryFeeAllFeeAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择扣率版本--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.FEEVER + '">' + value.FEENAME + '</option>';
					});
					$("#busiPack_ins").html(html);
				}
			});
		});
		$(function() {
			$('#test').datagrid({
				title: '商户扣率列表',
				iconCls: 'icon-save',
				height: gridHeight,
				singleSelect: true,
				nowrap: false,
				striped: true,
				url: 'pages/fee/queryMemberRateFeeAction.action',
				remoteSort: false,
				columns: [[{
					field: 'FEEVER',
					title: '扣率版本',
					width: 150,
					align: 'center'
				},
				{
					field: 'BUSICODE',
					title: '业务代码',
					width: 120,
					align: 'center'
				},
	
				{
					field: 'MEMBERID',
					title: '会员号',
					width: 150,
					align: 'center'
				},
				{
					field: 'FEE_RATE',
					title: '扣率(百分比)',
					width: 100,
					align: 'center'
				},
				{
					field: 'MIN_FEE',
					title: '最低额(元)',
					width: 100,
					align: 'center'
				},
				{
					field: 'MAX_FEE',
					title: '最高额(元)',
					width: 100,
					align: 'center'
				},
				{
					field: 'BUSIID',
					title: '操作',
					width: 100,
					align: 'center',
					formatter: function(value, rec) {
						return '<a href="javascript:showTxnRate(\'' + rec.TID + '\')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp<a href="javascript:showTxnRateDetail(\'' + rec.TID + '\')" style="color:blue;margin-left:10px">详情</a>';
					}
				}
				]],
				pagination: true,
				rownumbers: true,
				toolbar: [{
					id: 'btnadd',
					text: '新增商户类型扣率',
					iconCls: 'icon-add',
					handler: function() {
						showAdd(false);
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
		});
		function showAdd(isToModify) {
			$("#save_button").show();
			$("#cancel_button").show();
			if(!isToModify){
				$("#busipack").removeAttr("disabled");
				$("#busicase").removeAttr("disabled");
			}else{
				$("#busipack").attr("disabled","disabled");
				$("#busicase").attr("disabled","disabled");
			}
			$("#save_button").linkbutton('enable');
			$('#txnRateForm').clearForm();
			$("#txnRateForm").attr("action", "pages/fee/saveMemberRateFeeAction.action");
			$('#w').window({
				title: '商户扣率信息', 
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: true,
				closed: false
			});
			showFee();
		}
		function saveTxnRate() {
			if ($('#txnRateForm').form("validate")) {
				$("#save_button").linkbutton('disable');
				$('#txnRateForm').form('submit', {
					onSubmit: function() {
						$("#busipack").removeAttr("disabled");
						$("#busicase").removeAttr("disabled");
						return $('#txnRateForm').form('validate');
					},
					success: function(data) {
						if (data == '执行成功') {
							closeAdd();
							search();
						} else if (data == '修改成功!') {
							closeAdd();
							search();
						} else {
							alert(data);
							$("#save_button").linkbutton('enable');
						}
					}
				});
			}
		}
		
		function showFee() {
			$.ajax({
				type: "POST",
				url: "pages/fee/queryFeeAllFeeAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择扣率版本--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.FEEVER + '">' + value.FEENAME + '</option>';
					});
					$("#busipack").html(html);
				}
			});
		}
		
		function showFeeCase() {
			feever = $("#busipack").val();
			$.ajax({
				type: "POST",
				url: "pages/fee/queryFeeCaseByFeeverFeeAction.action?feever=" + feever,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择业务--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.BUSICODE + '">' + value.BUSINAME + '</option>';
					});
					$("#busicase").html(html);
				}
			});
		}
		
		function showTxnRate(tid) {
			$("#save_button").show();
			$("#cancel_button").show();
			showAdd(true);
			showFee();
			$.ajax({
				type: "POST",
				url: "pages/fee/queryOneMemberRateFeeAction.action?caseid=" + tid,
				dataType: "json",
				success: function(json) {
					showFeeCase2(json.FEEVER);
					$("#feeRate").val(json.FEE_RATE);
					$("#memberid").val(json.MEMBERID);
					$("#feeRateType").val(json.RATE_TYPE);
					$("#minFee").val(json.MIN_FEE);
					$("#maxFee").val(json.MAX_FEE);
					$("#notes").val(json.NOTES);
					setTimeout(function() {
						//alert(json.FEEVER);
						$("#busipack").val(json.FEEVER);
						$("#busicase").val(json.BUSICODE);
					},500);
				}
			});
			$("#txnRateForm").attr("action", "pages/fee/updateMemberRateFeeAction.action");
	
		}
		function showFeeCase2(feever) {
	
			$.ajax({
				type: "POST",
				url: "pages/fee/queryFeeCaseByFeeverFeeAction.action?feever=" + feever,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择业务--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.BUSICODE + '">' + value.BUSINAME + '</option>';
					});
					$("#busicase").html(html);
				}
			});
			$("#busipack").val(feever);
		}
		
		function showFeeCase_query() {
			feever = $("#busiPack_ins").val();
			$.ajax({
				type: "POST",
				url: "pages/fee/queryFeeCaseByFeeverFeeAction.action?feever=" + feever,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择业务--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.BUSICODE + '">' + value.BUSINAME + '</option>';
					});
					$("#busiCase_ins").html(html);
	
				}
			});
		}
		
		function closeAdd() {
			$('#w').window('close');
	
		}
		
		function search() {
			var data = {
				'memberrateModel.feever': $('#busiPack_ins').val(),
				'memberrateModel.busicode': $("#busiCase_ins").val(),
				'memberrateModel.memberid': $("#memberid_ins").val()
			};
			$('#test').datagrid('load', data);
		}
		
		function showRateDetail() {
			var ratatype = $("#feeRateType").val();
			if (ratatype == '01') {
				$("#feeRate").attr("readonly", "readonly");
				$("#feeRate").val("0");
				$("#maxFee").attr("readonly", "readonly");
				$("#maxFee").val("0");
				$("#minFee").removeAttr("readonly");
				$("#minFee").val("");
			} else if (ratatype == '02') {
				$("#feeRate").removeAttr("readonly");
				$("#feeRate").val("");
				$("#maxFee").attr("readonly", "readonly");
				$("#maxFee").val("0");
				$("#minFee").attr("readonly", "readonly");
				$("#minFee").val("0");
			} else if (ratatype == '03') {
				$("#feeRate").removeAttr("readonly");
				$("#feeRate").val("");
				$("#maxFee").removeAttr("readonly");
				$("#maxFee").val("");
				$("#minFee").removeAttr("readonly");
				$("#minFee").val("");
			}
		}
		
		function showTxnRateDetail(tid) {
			showAdd();
			showFee();
			$.ajax({
				type: "POST",
				url: "pages/fee/queryOneMemberRateFeeAction.action?caseid=" + tid,
				dataType: "json",
				success: function(json) {
					showFeeCase2(json.FEEVER);
					$("#feeRate").val(json.FEE_RATE);
					$("#memberid").val(json.MEMBERID);
					$("#feeRateType").val(json.RATE_TYPE);
					$("#minFee").val(json.MIN_FEE);
					$("#maxFee").val(json.MAX_FEE);
					setTimeout(function() {
						//alert(json.FEEVER);
						$("#busipack").val(json.FEEVER);
						$("#busicase").val(json.BUSICODE);
					},500);
				}
			});
			$("#txnRateForm").attr("action", "pages/fee/updateMemberRateFeeAction.action");
			$("#save_button").hide();
			$("#cancel_button").hide();
		}
	</script>
</html>
