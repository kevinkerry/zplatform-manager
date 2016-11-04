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
<script type="text/javascript" src="js/extendsValidator_1.0_20151215.js"></script>
</head>
<body>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="分卡种单笔限额查询"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="dedurateForm" method="post">
				<table width="100%">
					<tr>
						<td align="right">风控版本</td>
						<td align="left" style="padding-left: 5px"><select
							id="busipack_qid" class="easyui-validatebox"
							onchange="showFeeCaseQuery()">
						</select></td>
						<td align="right">版本实例</td>
						<td align="left" style="padding-left: 5px"><select
							id="busicase_qid" class="easyui-validatebox">
								<option value="">--请选择业务--</option>
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
		iconCls="icon-save" style="width: 600px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="theForm" method="post" action="">
					<input name="limitCreditSingleModel.TId" id="TId" type="hidden" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="right" width="18%" height="30px">风控版本</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="busipack" class="easyui-validatebox" required="true"
								onchange="showFeeCase()">
							</select></td>
							<td align="right" width="18%" height="30px">版本实例</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="busicase" class="easyui-validatebox" required="true"
								name="limitCreditSingleModel.caseid">
									<option value="">--请选择业务--</option>
							</select></td>
						</tr>
						<tr>
							<td align="right" width="18%" height="30px">单笔最小限额（元）</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="limitCreditSingleModel.minAmount" id="minAmount"
								validType="amount" maxlength="11" class="easyui-validatebox" />
							</td>
							<td align="right" width="18%" height="30px">单笔最大限额（元）</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="limitCreditSingleModel.maxAmount" id="maxAmount"
								validType="amount" maxlength="11" class="easyui-validatebox" />
							</td>
						</tr>
						<tr>
							<td align="right" width="18%" height="30px">风险等级</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="risklevel" class="easyui-validatebox"
								missingMessage="请选选择风险等级" required="true"
								name="limitCreditSingleModel.risklevel"
								class="easyui-validatebox">
									<option value="">--请选择风险等级--</option>
							</select></td>
							<td align="right" width="15%">卡类别</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="cardtype" class="easyui-validatebox" required="true"
								missingMessage="请选择卡类型" name="limitCreditSingleModel.cardType">
									<option value="" selected="selected">--请选择卡类型--</option>
									<option value="1">--借记--</option>
									<option value="2">--贷记--</option>
									<option value="3">--准贷记--</option>
									<option value="4">--预付费--</option>
							</select></td>
						</tr>
						<tr>
							<td align="right" width="18%">备注</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="limitCreditSingleModel.notes" id="Notes" maxlength="32" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveWhitePan()" id="btn_submit" onclick="">保存</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
		$(function() {
			showFeeQuery();
		});
		 
		var width = $("#continer").width();
		var gridHeight = 600;
		var panelWidth = 640;
		var panelHeight = 260;
		var panelHoriFloat = (width - panelWidth) / 2;
		var panelVertFloat = 150;
	
		$(function() {
			$('#test').datagrid({
				title: '分卡种单笔限额列表',
				singleSelect: true,
				iconCls: 'icon-save',
				height: gridHeight,
				nowrap: false,
				striped: true,
				sortName: 'FEEVER',
				url: 'pages/risk/queryLimitSingleByPageRiskAction.action',
				remoteSort: false,
				columns: [[{
					field: 'BUSINAME',
					title: '业务名称',
					width: 150,
					align: 'center'
				},
				{
					field: 'MIN_AMOUNT',
					title: '最小限额(元)',
					width: 100,
					align: 'center'
				},
				{
					field: 'MAX_AMOUNT',
					title: '最大限额(元)',
					width: 150,
					align: 'center'
				},
				{
					field: 'CARD_TYPE',
					title: '卡类别',
					width: 100,
					align: 'center',
					formatter: function(value, rec) {
						if (value == "1") {
							return "借记";
						} else if (value == "2") {
							return "贷记";
						} else if (value == "3") {
							return "准贷记";
							0
						} else if (value == "4") {
							return "预付费";
						}
					}
				},
				{
					field: 'RISKLEVEL',
					title: '风险等级',
					width: 100,
					align: 'center',
					formatter: function(value, rec) {
						if (value == "1") {
							return '<span style="color:green;">' + '提示' + '</span>';
						} else if (value == "2") {
							return '<span style="color:blue;">' + '关注' + '</span>';
						} else if (value == "3") {
							return '<span style="color:#B8860B;">' + '预警' + '</span>';
						} else if (value == "4") {
							return '<span style="color:#CD3700;">' + '警告' + '</span>';
						} else if (value == "5") {
							return '<span style="color:red;">' + '拒绝' + '</span>';
						}
					}
				},
				{
					field: 'STATUS',
					title: '状态',
					width: 100,
					align: 'center',
					formatter: function(value, rec) {
						if (value == "00") {
							return "使用";
						} else {
							return "注销";
						}
					}
				},
				{
					field: 'T_ID',
					title: '操作',
					width: 150,
					align: 'center',
					formatter: function(value, rec) {
						if (rec.STATUS == "00") {
							return '<a href="javascript:showLimitMem(' + value + ',' + rec.RISKVER + ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteLimitMem(' + value + ')" style="color:blue;margin-left:10px">注销</a>';
						} else {
							return '<a href="javascript:startLimitMem(' + value + ')" style="color:blue;margin-left:10px">启用</a>';
	
						}
					}
				}]],
				pagination: true,
				rownumbers: true,
				toolbar: [{
					id: 'btnadd',
					text: '新增分卡种单笔限额',
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
				'limitCreditSingleModel.caseid': $('#busicase_qid').val(),
				'riskver': $('#busipack_qid').val()
			};
			$('#test').datagrid('load', data);
		}
	
		function showAdd() {
			$('#theForm').clearForm();
			$("#busipack").attr("disabled", false);
			$("#busicase").attr("disabled", false);
			$('#w').window({
				title: '分卡种单笔限额信息',
				top: panelVertFloat, 
		  		width: panelWidth,
		  		height: panelHeight,
		  		left:panelHoriFloat,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action", "pages/risk/saveLimitCreditSingleRiskAction.action");
			$('#btn_submit').linkbutton('enable');
			showRiskLevel();
			showFee();
		}
	
		function resize() {
			$(':input').val("");
		}
	
		function saveWhitePan() {
			var max = parseInt($("#maxAmount").val());
			var min = parseInt($("#minAmount").val());
			if (max >= min) {
				$('#theForm').form('submit', {
					onSubmit: function() {
						if ($('#theForm').form('validate')) {
							$('#btn_submit').linkbutton('disable');
							return true;
						}
						return false;
					},
					success: function(data) {
						var a= data.split("validateUserLoginAction");
						if(data.split("validateUserLoginAction").length>1){
							window.parent.location.replace("<%=basePath%>"+"pages/logoutAction.action?relogin=relogin");
							return ;
						}
						if (data == '添加成功!' || data == '修改成功!') {
							alert(data);
							closeAdd();
							search();
						} else {
							alert(data);
							$('#btn_submit').linkbutton('enable');
						}
					}
				});
			} else {
				alert("最大限额应当大于最小限额");
			}
	
		}
		function showRiskLevel() {
			$.ajax({
				type: "POST",
				url: "pages/risk/queryRiskLevelRiskAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择风险等级--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.PARA_CODE + '">' + value.PARA_NAME + '</option>';
					});
					$("#risklevel").html(html);
				}
			});
		}
		function showLimitMem(tid, riskver) {
			$.ajax({
				type: "POST",
				url: "pages/risk/queryLimitCreditSingleRiskAction.action",
				data: "riskId=" + tid,
				dataType: "json",
				success: function(json) {
					$("#TId").val(json.T_ID);
					$("#Notes").val(json.NOTES);
					$("#maxAmount").val(json.MAX_AMOUNT);
					$("#minAmount").val(json.MIN_AMOUNT);
					$("#busipack").attr("disabled", "disabled");
					$("#busicase").attr("disabled", "disabled");
					showRiskLevel();
					showFee();
					showFeeCaseValue(riskver);
					setTimeout(function() {
						$("#busicase").val(json.CASEID);
						$("#risklevel").val(json.RISKLEVEL);
						$("#busipack").val(riskver);
						$("#cardtype").val(json.CARD_TYPE);
					},500);
				}
	
			});
			$('#w').window({
				title: '修改分卡种单笔限额',
				top: panelVertFloat, 
		  		width: panelWidth,
		  		height: panelHeight,
		  		left:panelHoriFloat,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action", "pages/risk/updateLimitCreditSingleRiskAction.action");
			$('#btn_submit').linkbutton('enable');
		}
		
		function deleteLimitMem(tid) {
			$.messager.confirm('提示', '您是否想要注销此分卡种单笔限额?',
			function(r) {
				if (r) {
					$.ajax({
						type: "GET",
						url: "pages/risk/deleteLimitCreditSingleRiskAction.action",
						data: "rand=" + new Date().getTime() + "&riskId=" + tid,
						dataType: "text",
						success: function(text) {
							$.messager.alert('提示', text);
							search();
						}
					});
				}
			});
		}
		
		function startLimitMem(tid) {
			$.messager.confirm('提示', '您是否想要启用分卡种单笔限额?',
			function(r) {
				if (r) {
					$.ajax({
						type: "GET",
						url: "pages/risk/startLimitCreditSingleRiskAction.action",
						data: "rand=" + new Date().getTime() + "&riskId=" + tid,
						dataType: "text",
						success: function(text) {
							$.messager.alert('提示', text);
							search();
						}
					});
				}
			});
		}
		function closeAdd() {
			$('#w').window('close');
		}
		function showFee() {
			$.ajax({
				type: "POST",
				url: "pages/risk/queryRisklistRiskAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择风控版本--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.RISKVER + '">' + value.RISKNAME + '</option>';
					});
					$("#busipack").html(html);
				}
			});
		}
		function showFeeCase() {
			riskId = $("#busipack").val();
			$.ajax({
				type: "POST",
				url: "pages/risk/queryRisklistCaseRiskAction.action?riskId=" + riskId,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择风控实例--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.CASEID + '">' + value.BUSINAME + '</option>';
					});
					$("#busicase").html(html);
				}
			});
		}
		function showFeeCaseValue(riskId) {
			$.ajax({
				type: "POST",
				url: "pages/risk/queryRisklistCaseRiskAction.action?riskId=" + riskId,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择风控实例--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.CASEID + '">' + value.BUSINAME + '</option>';
					});
					$("#busicase").html(html);
				}
			});
		}
		function showFeeQuery() {
			$.ajax({
				type: "POST",
				url: "pages/risk/queryRisklistRiskAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择风控版本--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.RISKVER + '">' + value.RISKNAME + '</option>';
					});
					$("#busipack_qid").html(html);
				}
			});
		}
		function showFeeCaseQuery() {
			riskId = $("#busipack_qid").val();
			$.ajax({
				type: "POST",
				url: "pages/risk/queryRisklistCaseRiskAction.action?riskId=" + riskId,
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择风控实例--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.BUSICODE + '">' + value.BUSINAME + '</option>';
					});
					$("#busicase_qid").html(html);
				}
			});
		}
</script>
</html>

