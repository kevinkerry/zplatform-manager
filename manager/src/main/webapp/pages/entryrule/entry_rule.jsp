<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<body>
	<style>
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
				<table width="100%">
					<tr>
						<td align="right" width="15%">交易类型</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="org_super"  name="sar.txnType"
							>
								<option value="">--请选择交易类型--</option>
						</select></td>
						<td align="right" width="15%">是否同步记账</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="qisSync"  name="sar.isSync"
							>
								<option value="">所有</option>
								<option value="0">异步</option>
								<option value="1">同步</option>
						</select></td>



					</tr>
					<tr>
						<td align="right" width="15%">状态</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="qactionStatus"  required="true"
							name="sar.status" >
								<option value="" selected="selected">所有</option>
								<option value="00">正常</option>

								<option value="01">停用</option>

						</select></td>
						<td align="right" width="15%">余额方向</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="qcrdr"  required="true"
							 name="sar.crdr"
							>
								<option value="">所有</option>
								<option value="C">贷记</option>
								<option value="D">借记</option>
						</select></td>
						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
							href="javascript:resize()" class="easyui-linkbutton"
							iconCls="icon-redo">清空</a></td>
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
				<form id="organForm" action="pages/system/saveOrganAction.action"
					method="post">
					<input type="hidden" id="ID" name="ID" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td width="15%">交易类型</td>
							<td width="30%"><select id="busi" class="easyui-validatebox"
								required="true" missingMessage="请选择交易类型" name="sr.txntype"
								class="easyui-validatebox">
									<option value="">--请选择交易类型--</option>
							</select></td>
							<td width="15%">科目</td>

							<td><select id="subjectCode" class="easyui-validatebox"
								required="true" name="sr.flag" class="easyui-validatebox"
								onchange="gradeChange()">
									<option value="">--请选择账户--</option>
									<option value="S101">收款方资金账户</option>
									<option value="F101">付款方资金账户</option>
									<option value="Y101">佣金方资金账户</option>
									<option value="S102">收款方保证金账户</option>
									<option value="F102">付款方保证金账户</option>
									<option value="Y102">佣金账户保证金账户</option>
									<option value="T001">通道手续费</option>
									<option value="T002">通道存款</option>
									<option value="T003">通道应收款</option>
									<option value="99">其他</option>
									<input id="subject" style="display: none;" type="text"
									name="sr.flags" missingMessage="请输入科目号" type="text"
									class="easyui-validatebox" maxlength="20"
									onchange="chectSubject()" /></td>
						</tr>
						<tr>
							<td>余额方向</td>
							<td><select id="crdr" class="easyui-validatebox"
								required="true" missingMessage="请选择余额方向" name="sr.crdrType"
								class="easyui-validatebox">
									<option value="C">贷记</option>
									<option value="D">借记</option>
							</select></td>
							<td>是否同步</td>
							<td><select id="isSync" class="easyui-validatebox"
								missingMessage="请选择是否同步" required="true" name="sr.syncFlag"
								class="easyui-validatebox" onchange="showCity()">
									<option value="0">异步</option>
									<option value="1">同步</option>
							</select></td>
						</tr>
						<tr>
							<td>计算公式</td>
							<td><input type="hidden" id="ordform"
								name="sr.entryAlgorithm"> <input id="ordforms"
								disabled="true " name="organ.organName" required="true"
								type="text" class="easyui-validatebox" maxlength="20" /><span
								style="color: red;">请点击按钮输入计算公式</span></td>
							<td colspan="2"><a href="javascript:void(0)"
								class="easyui-linkbutton" onclick="operation('A','本金')">本金</a> <a
								href="javascript:void(0)" class="easyui-linkbutton"
								onclick="operation('B','佣金')">佣金</a> <a
								href="javascript:void(0)" class="easyui-linkbutton"
								onclick="operation('T','通道手续费')">通道手续费</a> <a
								href="javascript:void(0)" class="easyui-linkbutton"
								onclick="operation('C','手续费')">手续费</a> <a
								href="javascript:void(0)" class="easyui-linkbutton"
								data-options="iconCls:'icon-add'" onclick="operation('add')">加上</a>
								<a href="javascript:void(0)" class="easyui-linkbutton"
								data-options="iconCls:'icon-remove'"
								onclick="operation('minus')">减去</a> <a href="javascript:void(0)"
								class="easyui-linkbutton" data-options="iconCls:'icon-back'"
								onclick="bank()" id="back">清空</a></td>

						</tr>
						<tr>

							<td>状态</td>
							<td><select id="actionStatus" class="easyui-validatebox"
								required="true" name="sr.actionStatus"
								class="easyui-validatebox">
									<option value="00" selected="selected">正常</option>
									<option value="01">停用</option>

							</select> <input type="hidden" id="id" name="sr.id" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveRole()" id="btn_submit" onclick="">保存</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>

	<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="balanceForm" action="pages/system/saveOrganAction.action"
					method="post">
					<input type="hidden" id="ID" name="ID" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td width="15%">交易类型</td>
							<td width="30%"><select id="busis"
								class="easyui-validatebox" required="true"
								missingMessage="请选择交易类型" name="sar.txnType"
								class="easyui-validatebox">
									<option value="">--请选择交易类型--</option>
							</select></td>

						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:balances()" id="btn_submit" onclick="">试算平衡</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>

</body>

<script>
	var width = $("#continer").width();
	var gridHeight = 600;
	var panelWidth = 640;
	var panelHeight = 260;
	var panelHoriFloat = (width - panelWidth) / 2;
	var panelVertFloat = 150;
	$(function() {
		getBusiness();
		getBusiness1();
		$('#test').datagrid({
			title: '分录规则表',
			iconCls: 'icon-save',
			height: 400,
			singleSelect: true,
			nowrap: false,
			striped: true,
			url: 'pages/rule/queryRuleRuleAction.action',
			remoteSort: false,
			idField: 'ORGAN_ID',
			columns: [[{
				field: 'TXNTYPE',
				title: '交易类型码',
				width: 120,
				align: 'center'
			},
			{
				field: 'ACCT_CODE',
				title: '科目',
				width: 150,
				align: 'center'
			},
	
			{
				field: 'ACCT_CODE_NAME',
				title: '科目名称',
				width: 150,
				align: 'center',
				formatter: function(value, rec) {
					if (value == null) {
						if (rec.ACCT_CODE == "S101") {
							return "收款方资金账户";
						} else if (rec.ACCT_CODE == "S102") {
							return "收款方保证金账户";
						} else if (rec.ACCT_CODE == "F101") {
							return "付款方资金账户"
						} else if (rec.ACCT_CODE == "F102") {
							return "付款方保证金账户"
						} else if (rec.ACCT_CODE == "Y101") {
							return "佣金方资金账户"
						} else if (rec.ACCT_CODE == "Y102") {
							return "佣金方保证金账户"
						} else if (rec.ACCT_CODE == "T001") {
							return "通道手续费"
						} else if (rec.ACCT_CODE == "T002") {
							return "通道备付金账户"
						} else if (rec.ACCT_CODE == "T003") {
							return "通道应收款"
						}
					} else {
						return value;
	
					}
	
				}
			},
			{
				field: 'TCR',
				title: '余额方向',
				width: 100,
				align: 'center',
				formatter: function(value, rec) {
					if (value == "D") {
						return "借";
					} else if (value == "C") {
						return "贷";
					}
				}
			},
			{
				field: 'IS_SYNC',
				title: '是否同步记账',
				width: 100,
				align: 'center',
				formatter: function(value, rec) {
					if (value == "0") {
						return "异步";
					} else if (value == "1") {
						return "同步";
					}
				}
			},
			{
				field: 'ORDFORMS',
				title: '计算公式',
				width: 100,
				align: 'center'
			},
	
			{
				field: 'STATUS',
				title: '状态',
				width: 100,
				align: 'center',
				formatter: function(value, rec) {
					if (value == "00") {
						return "使用";
					} else if (value = "01") {
						return "停用";
					} else {
	
						return "注销";
					}
				}
			},
	
			{
				field: 'ORGAN_ID',
				title: '操作',
				width: 200,
				align: 'center',
				formatter: function(value, rec) {
					if (rec.STATUS == "00") {
						return '<a href="javascript:showOrgan(' + rec.TID + ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp' + '<a href="javascript:update(\'' + rec.TID + '\',2)" style="color:blue;margin-left:10px">停用</a>' + '&nbsp;&nbsp<a href="javascript:update(\'' + rec.TID + '\',3)" style="color:blue;margin-left:10px">注销</a>';
					} else if (rec.STATUS == "01") {
						return '<a href="javascript:showOrgan(' + rec.TID + ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp' + '<a href="javascript:update(\'' + rec.TID + '\',4)" style="color:blue;margin-left:10px">启用</a>' + '&nbsp;&nbsp<a href="javascript:update(\'' + rec.TID + '\',3)" style="color:blue;margin-left:10px">注销</a>';
	
					} else {
						return "";
					}
	
				}
			}]],
			pagination: true,
			rownumbers: true,
			toolbar: [{
				id: 'btnadd',
				text: '新增规则',
				iconCls: 'icon-add',
				handler: function() {
					showAdd();
					$("#inputForm :input").val('');
					gradeChange();
				}
			},
			{
				id: 'balance',
				text: '试算平衡',
				iconCls: 'icon-search',
				handler: function() {
					balance();
					$("#inputForm :input").val('');
					getBusiness1();
				}
			}]
		});
		/* 		var p = $('#test').datagrid('getPager');
			$.extend($.fn.validatebox.defaults.rules, {
				minLength : {
					validator : function(value, param) {
						var re = /^\d+$/;
						if (!re.test(value)) {
							return false;
						}
						return value.length >= param[0];
					},
					message : '请输入4位数字的组织机构代码'
				}
	
			}); */
	
	});
	
	function resize() {
		$('#theForm :input').val('');
	}
	
	function balance() {
		$("#balanceForm").attr("action", "pages/rule/balanceRuleAction.action");
		$('#ws').window({
			title: '试算平衡',
			top: 100,
			width: 400,
			collapsible: false,
			minimizable: false,
			maximizable: false,
			modal: true,
			shadow: false,
			closed: false,
			height: 240
		});
		$('#btn_submit').linkbutton('enable');
	}
	function showAdd() {
		$("#organForm").attr("action", "pages/rule/addRuelRuleAction.action");
		$('#w').window({
			title: '新增规则',
			top: 100,
			width: 800,
			collapsible: false,
			minimizable: false,
			maximizable: false,
			modal: true,
			shadow: false,
			closed: false,
			height: 240
		});
		$('#btn_submit').linkbutton('enable');
	
	}
	function closeAdd() {
		$('#w').window('close');
		$('#ws').window('close');
	}
	
	function search() {
		var data = {
			"sar.txnType": $('#org_super').val(),
			"sar.isSync": $("#qisSync").val(),
			"sar.status": $("#qactionStatus").val(),
			"sar.crdr": $("#qcrdr").val()
		}
		$('#test').datagrid('load', data);
	}
	
	function balances() {
		$('#balanceForm').form('submit', {
			onSubmit: function() {
				if ($('#balanceForm').form('validate')) {
					$('#btn_submit').linkbutton('disable');
					return true;
				}
				return false;
			},
			success: function(data) {
				$.messager.alert('提示', data);
				search();
				closeAdd();
	
			}
		});
	}
	
	function saveRole() {
		$('#organForm').form('submit', {
			onSubmit: function() {
				if ($('#organForm').form('validate') && chectSubject()) {
					ord = $("#ordform").val();
					if (!checkOrdform(ord)) {
						$.messager.alert('提示', "请输入正确的运算规则");
						return false;
					}
					if (!checkT()) {
						$.messager.alert('提示', "通道手续费不能包含运算符");
						return false;
					}
					$('#btn_submit').linkbutton('disable');
					return true;
				}
				return false;
			},
			success: function(data) {
				if (data == "true") {
					$.messager.alert('提示', "操作成功");
					search();
					closeAdd();
				} else {
					$.messager.alert('提示', data);
					$('#btn_submit').linkbutton({
						disabled: false
					});
	
				}
			}
		});
	}
	
	/**得到所有交易类型**/
	function getBusiness() {
		$.ajax({
			type: "POST",
			url: "pages/rule/getBusinessRuleAction.action",
			dataType: "json",
			success: function(json) {
				var html = "<option value=''>--请选择交易类型--</option>";
				$.each(json,
				function(key, value) {
					html += '<option value="' + value.busiCode + '">' + value.busiName + '</option>';
				});
				$("#org_super").html(html);
				$("#busi").html(html);
				$("#busis").html(html);
			}
		});
	}
	
	/**得到所有交易类型**/
	function getBusiness1() {
		$.ajax({
			type: "POST",
			url: "pages/rule/getBusinessRuleAction.action",
			dataType: "json",
			success: function(json) {
				var html = "<option value=''>--请选择交易类型--</option>";
				$.each(json,
				function(key, value) {
					html += '<option value="' + value.busiCode + '">' + value.busiName + '</option>';
				});
				$("#busi").html(html);
			}
		});
	}
	function update(id, type) {
		var messg = "";
		if (type == 3) {
			messg = "您是否想注销此规则";
		} else if (type == 2) {
			messg = "您是否想停用此规则";
		} else {
			messg = "您是否想启用此规则";
		}
	
		$.messager.confirm('提示', messg,
		function(r) {
			if (r) {
				$.ajax({
					type: "POST",
					url: "pages/rule/stopOrStarRuleRuleAction.action",
					data: "id=" + id + "&&falg=" + type,
					dataType: "text",
					success: function(json) {
	
						$.messager.alert('提示', json);
						search();
						closeAdd();
					}
				});
			}
		});
	}
	
	function showOrgan(organId) {
		$("#organForm").attr("action", "pages/rule/updatesRuleAction.action");
		$.ajax({
			type: "POST",
			url: "pages/rule/updateRuleAction.action",
			data: "sar.id=" + organId,
			dataType: "json",
			success: function(json) {
				getBusiness1();
				update = json.li.ACCT_CODE;
				crdr = json.li.TCR;
				isSync = json.li.IS_SYNC;
				ordform = json.li.ORDFORM;
				ordforms = json.li.ORDF;
				actionStatus = json.li.STATUS;
				subjectCode = json.li.ACCT_CODE;
				id = json.li.TID;
				if (subjectCode.match(/\D/) == null) {
					$("#subjectCode").val("99");
					$("#subject").val(subjectCode);
					gradeChange();
				} else {
					$("#subjectCode").val(subjectCode);
					gradeChange();
				};
				$("#id").val(id);
				$("#crdr").val(crdr);
				$("#isSync").val(isSync);
				$("#ordform").val(ordform);
				$("#ordforms").val(ordforms);
				$("#actionStatus").val(actionStatus);
				setTimeout(function() {
					$("#busi").val(json.li.TXNTYPE);
				},500);
	
			}
		});
		$('#w').window({
			title: '修改分录规则',
			top: 100,
			width: 800,
			collapsible: false,
			minimizable: false,
			maximizable: false,
			modal: true,
			shadow: false,
			closed: false,
			height: 240
		});
		$('#btn_submit').linkbutton('enable');
	
		//alert($("#organForm").attr("action"));
	}
	
	function chectSubject() {
		var accCode = $('#subject').val();
		if (accCode != "" && accCode != null && gradeChange()) {
			$.ajax({
				type: "POST",
				url: "pages/rule/getSubjectByCodeRuleAction.action",
				data: "accCode=" + accCode,
				dataType: "text",
				success: function(json) {
					if (json != "") {
						$.messager.alert('提示', json);
						return false;
					} else {
						return true;
					}
				}
			});
		} else {
			return true;
		}
	}
	
	function operation(a, b) {
		if (a == "add") {
			a = "\+";
			b = a;
		} else if (a == "minus") {
			a = "\-";
			b = a;
		} else {
			a = a;
		}
		ordforms = $("#ordforms").val();
		ordform = $("#ordform").val();
		$("#ordforms").val(ordforms + b);
		$("#ordform").val(ordform + a);
	}
	
	function gradeChange() {
		var code = $("#subjectCode").val();
	
		if (code == "99") {
			$("#subject").show();
			$("#subject").attr({
				required: true
			});
			return true;
		} else {
			$("#subject").hide();
			$("#subject").attr({
				required: false
			});
			return false;
		}
	}

	function bank() {
		$("#ordforms").val("");
		$("#ordform").val("");
	
	}
	
	function checkOrdform(value) {
		return /^([A-Z]{1}$)|([A-Z]{1}[+-]{1}[A-Z]{1}$)|([A-Z]{1}[+-]{1}[A-Z]{1}[+-]{1}[A-Z]{1})$/.test(value);
	
	}
	
	function checknum(value) {
		var Regx = /^[A-Za-z0-9]*$/;
		if (Regx.test(value)) {
			return true;
		} else {
			return false;
		}
	}
	
	function checkT() {
		ord = $("#ordform").val();
		var i = ord.indexOf("T");
		if (i == -1) {
			return true;
		} else {
			if (ord.length > 1) {
				return false;
			} else {
				return true;
			}
		}
	
	}
</script>
</html>
