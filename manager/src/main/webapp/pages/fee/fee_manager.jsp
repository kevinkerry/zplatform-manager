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
		<div id="p" class="easyui-panel" title="扣率查询"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="dedurateForm" method="post">
				<table width="100%">
					<tr>
						<td align="right">扣率版本代码</td>
						<td align="left" style="padding-left: 5px"><input
							name="feeModel.feever" id="feever_qid" maxlength="8" /></td>
						<td align="right">扣率版本名称</td>
						<td align="left" style="padding-left: 5px"><input
							name="feeModel.feename" id="feename_qid" maxlength="128" /></td>
						<td align="right" colspan=2><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
							href="javascript:resize()" class="easyui-linkbutton"
							iconCls="icon-redo">重置</a></td>
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
					action="pages/fee/saveFeeFeeAction.action">
					<input name="feeModel.feeid" id="feeid" type="hidden" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="right" width="15%" height="50px">扣率版本代码</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="feeModel.feever" id="feeCode" validType="minLength[8,8]"
								maxlength="8" class="easyui-validatebox" /></td>
							<td align="right" width="15%">扣率版本名称</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="feeModel.feename" id="feeName" /></td>
						</tr>
						<tr></tr>
						<tr>
							<td align="right" width="15%">所属产品</td>
							<td align="left" style="padding-left: 5px" width="25%"><select
								id="prdtver" class="easyui-validatebox" missingMessage="请选择产品"
								required="true" name="feeModel.prdtver"
								class="easyui-validatebox">
									<option value="">--请选择产品--</option>
							</select></td>
							<td align="right" width="15%">备注</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="feeModel.notes" id="feeNotes" maxlength="32" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveFee()" id="btn_submit" onclick="">保存</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
		$.extend($.fn.validatebox.defaults.rules, {
			minLength: {
				validator: function(value, param) {
					var re = /^\d+$/;
					if (!re.test(value)) {
						return false;
					}
					return value.length >= param[0];
				},
				message: '请输入8位数字的扣率代码'
			}
		});
	
		var width = $("#continer").width();
	  	var gridHeight = 600;
		var panelWidth = 640;
		var panelHeight = 260;
		var panelHoriFloat = (width-panelWidth)/2;
		var panelVertFloat = 150;
	
		$(function() {
			$('#test').datagrid({
				title: '扣率版本列表',
				singleSelect: true,
				iconCls: 'icon-save',
				height: gridHeight,
				nowrap: false,
				striped: true,
				sortName: 'FEEVER',
				url: 'pages/fee/queryFeeFeeAction.action',
				remoteSort: false,
				columns: [[{
					field: 'FEEVER',
					title: '扣率版本代码',
					width: 150,
					align: 'center'
				},
				{
					field: 'FEENAME',
					title: '扣率版本名称',
					width: 220,
					align: 'center'
				},
				{
					field: 'STATUS',
					title: '状态',
					width: 100,
					align: 'center'
				},
				{
					field: 'FEEID',
					title: '操作',
					width: 150,
					align: 'center',
					formatter: function(value, rec) {
						return '<a href="javascript:showFee(' + value + ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:toMakeFEECase(' + '\'' + rec.FEEVER + '\'' + ')" style="color:blue;margin-left:10px">配置</a>';
					}
				}]],
				pagination: true,
				rownumbers: true,
				toolbar: [{
					id: 'btnadd',
					text: '新增扣率版本',
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
				'feeModel.feever': $('#feever_qid').val(),
				'feeModel.feename': $("#feename_qid").val()
			};
			$('#test').datagrid('load', data);
		}
	
		function showAdd() {
			$('#theForm').clearForm();
			$("#feeCode").removeAttr("readonly");
			$.ajax({
		  		type: "POST",
		  		url: "pages/fee/queryFeeverFeeAction.action",
		  		data:"",
		  		async: false,
		  		dataType: "json",
		  		success: function(json) {
		  			$("#feeCode").val(json.FEEVER);
                    $("#feeCode").attr("readonly","readonly");
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
			$("#theForm").attr("action", "pages/fee/saveFeeFeeAction.action");
			$('#btn_submit').linkbutton('enable');
			showProduct();
		}
	
		function resize() {
			$(':input').val("");
		}
	
		function saveFee() {
			$("#routver").removeAttr("disable");
			$('#theForm').form('submit', {
				onSubmit: function() {
					if ($('#theForm').form('validate')) {
						$('#btn_submit').linkbutton('disable');
						return true;
					}
					return false;
				},
				success: function(data) {
					if (data == '添加成功!' || data == '修改成功!') {
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
		
		function showProduct() {
			$.ajax({
				type: "POST",
				url: "pages/fee/queryProductAllFeeAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择产品--</option>";
					$.each(json,
					function(key, value) {
						html += '<option value="' + value.prdtver + '">' + value.prdtname + '</option>';
					});
					$("#prdtver").html(html);
				}
			});
		}
		
		function showFee(feeid) {
			$.ajax({
				type: "POST",
				url: "pages/fee/queryOneFeeFeeAction.action",
				data: "feever=" + feeid,
				dataType: "json",
				success: function(json) {
					$("#feeid").val(feeid);
					$("#feeCode").val(json.FEEVER);
					$("#feeCode").attr("readonly","readonly");
					$("#feeName").val(json.FEENAME);
					$("#feeNotes").val(json.NOTES);
					$.ajax({
						type: "POST",
						url: "pages/fee/queryProductAllFeeAction.action",
						dataType: "json",
						success: function(json) {
							var html = "<option value=''>--请选择产品--</option>";
							$.each(json,
							function(key, value) {
								html += '<option value="' + value.prdtver + '">' + value.prdtname + '</option>';
							});
							$("#prdtver").html(html);
						}
					});
	
					setTimeout(function() {
						$("#prdtver").val(json.PRDTVER);
					},
					500);
				}
	
			});
			$('#w').window({
				title: '修改扣率版本',
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
			$("#theForm").attr("action", "pages/fee/updateFeeFeeAction.action");
			$('#btn_submit').linkbutton('enable');
		}
		function toMakeFEECase(feever) {
			var form = document.forms['theForm'];
			form.action = "<%=basePath%>pages/fee/toMakeFeeFeeAction.action?feever=" + feever;
			form.submit();
		}
		function closeAdd() {
			$('#w').window('close');
		}
</script>
</html>

