<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../top.jsp"></jsp:include>
<body>
	<script type="text/javascript"
		src="js/checkboxbeautify/jquery-hcheckbox.js"></script>
	<link href='js/checkboxbeautify/css.css' rel="stylesheet"
		type="text/css" />
	<style type="text/css">
#groupinfo {
	height: 25px;
}

#groupinfo tr td {
	height: 25px;
	border-style: solid;
	border-width: 0px 0px 0px 0px;
	border-color: #000000;
	padding: 1px
}

#groupinfo tr td input {
	height: 20px;
	margin-left: 3px;
}

#groupinfo tr td span {
	height: 20px;
	margin-left: 3px;
}

.activeflag_label {
	width: 90px
}
</style>
	<div style="margin-top: 5px">
		<table id="test"></table>
	</div>

	<input type="hidden" id="riskver" value="${riskver}" />
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 900px; height: 900px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="theForm"
					action="pages/risk/updateRiskCaseRiskAction.action" method="post">
					<input type="hidden" id="caseid" name="riskCaseModel.caseid">
					<input type="hidden" id="riskver2" name="riskCaseModel.riskver">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="right" width="10%" height="50px">风控版本代码</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="riskCaseModel.busicode" id="busicode"
								validType="minLength[8,8]" maxlength="8"
								class="easyui-validatebox" /></td>
							<td align="right" width="10%">风控版本名称</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="riskCaseModel.businame" id="businame" /></td>
						</tr>
						<tr></tr>
						<tr>
							<td align="right" width="10%" height="50px">选择业务</td>
							<td align="left" id="yewu_ins" colspan="3"></td>
						</tr>
						<tr>

							<td align="right" width="10%" height="50px">备注</td>
							<td align="left" style="padding-left: 5px" width="25%"><input
								name="riskCaseModel.notes" id="Notes" maxlength="32" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" id="btn_submit" iconCls="icon-ok"
					onclick="updateRiskCase()">提交</a> <a class="easyui-linkbutton"
					iconCls="icon-cancel" href="javascript:void(0)"
					onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	<div region="south" border="false"
		style="text-align: center; padding: 5px 0;">
		<a href="javascript:history.back(-1);" class="easyui-linkbutton"
			iconCls="icon-back">返回</a>
	</div>
</body>

<script>
	  	$(document).ready(function() { //页面加载完成 初始化页面
			//showProduct();
		});
	  	var width = $("#w").width();
		var gridHeight = 600;
		var panelWidth = 740;
		var panelHeight = 350;
		var panelHoriFloat = (width - panelWidth) / 2;
		var panelVertFloat = 150;
		$(function() {
			var riskver = $('#riskver').val();
			$('#test').datagrid({
				title: '配置扣率版本实例',
				iconCls: 'icon-save',
				height: gridHeight,
				singleSelect: true,
				nowrap: false,
				striped: true,
				url: 'pages/risk/queryRiskCaseRiskAction.action?riskver=' + riskver,
				remoteSort: false,
				idField: 'ORGAN_ID',
				columns: [[{
					field: 'BUSICODE',
					title: '业务代码',
					width: 120,
					align: 'center'
				},
				{
					field: 'BUSINAME',
					title: '业务名称',
					width: 150,
					align: 'center'
				},
				{
					field: 'STATUS',
					title: '状态',
					width: 120,
					align: 'center',
					formatter: function(value, rec) {
						if (value == "00") {
							return "使用";
						} else {
							return "失效";
						}
					}
				},
				{
					field: 'DEPT_ID',
					title: '操作',
					width: 100,
					align: 'center',
					formatter: function(value, rec) {
						if (rec.STATUS == "00") {
							return '<a href="javascript:showFeeCase(' + rec.RISKVER + ',' + '\'' + rec.BUSINAME + '\'' + ',' + rec.BUSICODE + ',' + rec.CASEID + ')" style="color:blue;margin-left:10px">配置</a>';
						} else {
							return "";
						}
					}
				}
				]]
			});
			
			var p = $('#test').datagrid('getPager');
			$.extend($.fn.validatebox.defaults.rules, {
				minLength: {
					validator: function(value, param) {
						var re = /^\d+$/;
						if (!re.test(value)) {
							return false;
						}
						return value.length >= param[0];
					},
					message: '请输入4位数字的部门代码'
				}
	
			});
		});
	
		function showFeeCase(riskver, businame, busicode, caseid) {
			$('#businame').val(businame);
			$('#busicode').val(busicode);
			$("#businame").attr("readonly", "readonly");
			$("#busicode").attr("readonly", "readonly");
			$('#caseid').val(caseid);
			$('#riskver2').val(riskver);
			$.ajax({
				type: "POST",
				url: "pages/risk/queryOneRiskCaseRiskAction.action",
				data: "riskId=" + caseid,
				dataType: "json",
				success: function(json) {
					setTimeout(function() {
						$("#Notes").val(json.NOTES);
					},500);
				}
			});
			$('#w').window({
				title: '配置风控策略信息',
				top: panelVertFloat, 
		  		width: panelWidth,
		  		height: panelHeight,
				modal: true,
				minimizable: false,
				collapsible: false,
				maximizable: false,
				shadow: false,
				closed: false,
			});
			loadYwMark(caseid);
			$('#btn_submit').linkbutton('enable');
		}
		
		function closeAdd() {
			$('#w').window('close');
		}
	
		function search() {
			var data = {
				deptName: $('#deptName').val(),
				"deptCode": $("#deptCode").val()
			};
			$('#test').datagrid('load', data);
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
	
		function updateRiskCase() {
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
					if (data == '操作成功!') {
						alert(data);
						$('#btn_submit').linkbutton('disable');
						closeAdd();
					}
				}
			});
		}
		
		function loadYWAll() {
			var mark = 0;
			var html = '';
			$.ajax({
				type: "GET",
				url: "pages/risk/queryRisklistCheckRiskAction.action",
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					$.each(json,
					function(key, value) {
						html += '<input type="checkbox" id="checkboxList" name="checkboxList" style="align:left" value="' + value.ORDERS + '" /><label class="activeflag_label">' + value.STRATEGY + '</label>';
						if (mark == 3 || mark == 7) {
							html += '<br/>';
						}
						mark = mark + 1;
					});
					$("#yewu_ins").html(html);
					$('#yewu_ins').hcheckbox();
				}
			});
		}
		
		function loadYwMark(caseId) {
			var mark = 0;
			var html = '';
			$.ajax({
				type: "GET",
				url: "pages/risk/queryRisklistCheckActiveRiskAction.action?riskId=" + caseId,
				data: "rand=" + new Date().getTime(),
				dataType: "json",
				success: function(json) {
					$.each(json,
					function(key, value) {
						if (value.CODEMARK == '0') {
							html += '<input type="checkbox" id="checkboxList" name="checkboxList" style="width:9px" value="' + value.ORDERS + '" /><label class="activeflag_label" style="width:110px" >' + value.STRATEGY + '</label>';
						} else {
							html += '<input type="checkbox" id="checkboxList" name="checkboxList" checked="checked"   style="width:9px" value="' + value.ORDERS + '" /><label class="activeflag_label" style="width:110px">' + value.STRATEGY + '</label>';
						}
						if (mark == 3 || mark == 7) {
							html += '</br>'
						}
						mark = mark + 1;
					});
					$("#yewu_ins").html(html);
					$('#yewu_ins').hcheckbox();
					//alert($('#yewu_ins').children('#checkboxList'));			
					$('#yewu_ins').children('#checkboxList').each(function() {
						if ($(this).attr("checked") == "checked") {
							//$(this).next('.activeflag_label').attr("disabled","disabled");
							//$(this).next('.activeflag_label').unbind('click');
							//$(this).attr("disabled","disabled");
						} else {
							$(this).removeAttr("disabled");
						}
					});
	
				}
			});
		}
	</script>
</html>
