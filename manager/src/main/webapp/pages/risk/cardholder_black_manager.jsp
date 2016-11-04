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
		<div id="p" class="easyui-panel" title="持卡人黑名单查询"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="dedurateForm" method="post">
				<table width="100%">
					<tr>
						<td align="right">身份证号</td>
						<td align="left" style="padding-left: 5px"><input id="idnum"
							maxlength="19" /></td>

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
				<form id="theForm" method="post"
					action="pages/risk/saveCardHolderBlackRiskAction.action">
					<input name="blackIdnumModel.tid" id="tid" type="hidden" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="right" width="15%" height="50px">身份证号</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="blackIdnumModel.idnum" id="idnum1" validType="cardNo[18]"
								maxlength="18" class="easyui-validatebox" /> <font color="red">*</font>
							</td>

							<td align="right" width="15%">风险等级</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="risklevel" class="easyui-validatebox"
								missingMessage="请选选择风险等级" required="true"
								name="blackIdnumModel.risklevel" class="easyui-validatebox">
									<option value="">--请选择风险等级--</option>
									<option value="5">拒绝</option>
							</select> <font color="red">*</font></td>

						</tr>

						<tr>
							<td align="right" width="15%">有效期开始日期</td>
							<td><input name="blackIdnumModel.sdate" maxlength="10"
								type="text" id="sdate" /> <font color="red">*</font></td>

							<td align="right" width="15%">有效期终止日期</td>
							<td><input name="blackIdnumModel.edate"
								class="easyui-validatebox" maxlength="10" id="edate" /> <font
								color="red">*</font></td>
						</tr>


						<tr>
							<td align="right" width="15%" height="50px">备注</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="blackIdnumModel.notes" id="notes" maxlength="42" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveBlackIdnum()" id="btn_submit" onclick="">保存</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
<!--
	$.extend($.fn.validatebox.defaults.rules, {
		minLength : {
			validator : function(value, param) {
				var re = /^\d+$/;
				if (!re.test(value)) {
					return false;
				}
				return value.length >= param[0];
			},
			message : '请输入16-19位数字的身份证号'
		}
	});

	-->
	function saveBlackIdnum() {
		$('#theForm').form('submit', {
			onSubmit : function() {
				if ($('#theForm').form('validate')) {
					$('#btn_submit').linkbutton('disable');
					return true;
				}
				return false;
			},
			success : function(data) {
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
	}
	
	
	var width = $("#continer").width();
	var gridHeight = 600;
	var panelWidth = 640;
	var panelHeight = 260;
	var panelHoriFloat = (width - panelWidth) / 2;
	var panelVertFloat = 150;

	$(function() {
		$('#sdate,#edate').datebox({});
		$('#test')
				.datagrid(
						{
							title : '持卡人黑名单列表',
							singleSelect : true,
							iconCls : 'icon-save',
							height : gridHeight,
							nowrap : false,
							striped : true,
							url : 'pages/risk/queryCardHolderBlackListRiskAction.action',
							remoteSort : false,
							columns : [ [
									{
										field : 'IDNUM',
										title : '持卡人身份证号',
										width : 150,
										align : 'center'
									},
									{
										field : 'RISKLEVELNAME',
										title : '银行卡风险等级',
										width : 220,
										align : 'center'
									},
									{
										field :'SDATE',
										title : '有效期开始时间',
										width : 220,
										align : 'center'										
									},
									{
										field :'EDATE',
										title : '有效期结束时间',
										width : 220,
										align : 'center'
									},
									{
										field : 'STATUS',
										title : '状态',
										width : 100,
										align : 'center',
										formatter : function(value, rec) {
											if (value == "00") {
												return "使用";
											} else {
												return "注销";
											}
										}
									},
									{
										field : 'T_ID',
										title : '操作',
										width : 150,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.STATUS == "00") {
												return '<a href="javascript:showBlackCardHolder('
														+ value
														+ ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteblack('
														+ value
														+ ')" style="color:blue;margin-left:10px">注销</a>';
											} else {
												return '<a href="javascript:startblack('
														+ value
														+ ')" style="color:blue;margin-left:10px">启用</a>';
											}
										}
									} ] ],
							pagination : true,
							rownumbers : true,
							toolbar : [ {
								id : 'btnadd',
								text : '新增持卡人黑名单',
								iconCls : 'icon-add',
								handler : function() {
									showAdd();
								}
							} ]
						});
		var p = $('#test').datagrid('getPager');
		$(p).pagination({
			onBeforeRefresh : function() {
			}
		});
	});

	function search() {
		var data = {
			'blackIdnumModel.idnum' : $('#idnum').val()
		};
		$('#test').datagrid('load', data);
	}

	function showAdd() {
		$('#theForm').clearForm();
		$('#w').window({
			title : '持卡人黑名单信息',
			top : panelVertFloat,
			width : panelWidth,
			height : panelHeight,
			left : panelHoriFloat,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false
		});
		$("#theForm").attr("action",
				"pages/risk/saveCardHolderBlackRiskAction.action");
		$('#btn_submit').linkbutton('enable');
	}

	function resize() {
		$(':input').val("");
	}

	function saveBlackPan() {
		$('#idnum').validatebox({
			required : true
		});
		$('#risklevel').validatebox({
			required : true
		});
		$('#sdate').validatebox({
			required : true
		});
		$('#edate').validatebox({
			required : true
		});
		$('#theForm').form('submit', {
			onSubmit : function() {
				if ($('#theForm').form('validate')) {
					$('#btn_submit').linkbutton('disable');
					return true;
				}
				return false;
			},
			success : function(data) {
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
	}

	function showRiskLevel() {
		$.ajax({
			type : "POST",
			url : "pages/risk/queryRiskLevelRiskAction.action",
			dataType : "json",
			success : function(json) {
				var html = "<option value=''>--请选择风险等级--</option>";
				$.each(json, function(key, value) {
					html += '<option value="' + value.PARA_CODE + '">'
							+ value.PARA_NAME + '</option>';
				});
				$("#risklevel").html(html);
			}
		});
	}

	function showBlackCardHolder(tid) {
		$.ajax({
			type : "POST",
			url : "pages/risk/queryOneBlackCardHolderRiskAction.action?tid="
					+ tid,
			dataType : "json",
			success : function(json) {				
				$("#idnum1").val(json.IDNUM);
				$("#risklevel").val(json.RISKLEVEL);
				$("#sdate").datebox('setValue',json.SDATE);
				$("#edate").datebox('setValue',json.EDATE);
				$("#notes").val(json.NOTES);				
				setTimeout(function() {
					$("#risklevel").val(json.RISKLEVEL);
				}, 500);
			}

		});
		
		$('#w').window({
			title : '持卡人黑名单',
			top : panelVertFloat,
			width : panelWidth,
			left : panelHoriFloat,
			height : panelHeight,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false
		});
		$("#theForm").attr("action",
				"pages/risk/updateBlackCardHolderRiskAction.action?tid="+tid);
		$('#btn_submit').linkbutton('enable');

	}

	function deleteblack(tid) {
		$.messager
				.confirm(
						'提示',
						'您是否想要注销此持卡人黑名单?',
						function(r) {
							if (r) {
								$
										.ajax({
											type : "GET",
											url : "pages/risk/deleteCardHolderBlackRiskAction.action?tid="
													+ tid,
											dataType : "text",
											success : function(text) {
												$.messager.alert('提示', text);
												search();
											}
										});

							}
						});
	}

	function startblack(tid) {
		$.messager.confirm('提示', '您是否想要启用此持卡人黑名单?', function(r) {
			if (r) {
				$.ajax({
					type : "GET",
					url : "pages/risk/startCardHolderBlackRiskAction.action",
					data : "tid=" + tid,
					dataType : "text",
					success : function(text) {
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
</script>
</html>

