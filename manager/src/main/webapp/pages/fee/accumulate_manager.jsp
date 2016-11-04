<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
							id="feever_ins" class="easyui-validatebox"
							onchange="showFeeCase_query()">
						</select></td>
						<td align="right"></td>
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
				<form id="accumulateRateForm"
					action="pages/fee/saveAccumulateRateFeeAction.action" method="post">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left">
						<tr>
							<td width="15%">扣率版本</td>
							<td width="30%"><select id="busipack"
								class="easyui-validatebox" required="true"
								name="accumulateRateModel.feever" onchange="showFeeCase()"
								missingMessage="请选择扣率版本">
							</select> <font color="red">*</font></td>
							</td>
							<td width="15%">业务</td>
							<td><select id="busicase" class="easyui-validatebox"
								required="true" name="accumulateRateModel.busicode"
								missingMessage="请选择业务">
									<option value="">--请选择业务--</option>
							</select> <font color="red">*</font></td>
							</td>
						</tr>
						<tr>
							<td>收费类型</td>
							<td><select id="ratetype" class="easyui-validatebox"
								required="true" name="accumulateRateModel.ratetype"
								missingMessage="请选择收费类型" />
								<option value="04">--分段计费--</option> </select> <font color="red">*</font></td>
							</td>
							<td width="15%">扣率(百分比)</td>
							<td><input id="feerate"
								name="accumulateRateModel.feerateStr" class="easyui-validatebox"
								required="true" validType="percent" type="text" maxlength="4"
								missingMessage="请填写扣率" /> <font color="red">*</font></td>
							</td>

						</tr>

						<tr>
							<td>累计方式</td>
							<td><select id="accmode" class="easyui-validatebox"
								required="true" name="accumulateRateModel.accmode" />
								<option value="">--请选择累计方式--</option>
								<option value="0">日</option>
								<option value="1">月</option>
								<option value="2">年</option> </select></td>
						</tr>
						<tr>
							<td>最低收费额(单位:元)</td>
							<td><input id="minfee" name="accumulateRateModel.minfeeStr"
								validType="amount" maxlength="12" type="text"
								class="easyui-validatebox" /></td>
							<td width="15%">最高收费额(单位:元)</td>
							<td><input id="maxfee" name="accumulateRateModel.maxfeeStr"
								validType="amount" maxlength="12" type="text"
								class="easyui-validatebox" /></td>
						</tr>

						<tr>
							<td>阶梯1(单位：元)</td>
							<td><input id="limit1" name="accumulateRateModel.limit1Str"
								validType="amount" type="text" class="easyui-validatebox"
								maxlength="12" /></td>
							<td width="15%">扣率(百分比)</td>
							<td><input id="feerate2"
								name="accumulateRateModel.feerate2Str" validType="percent"
								type="text" class="easyui-validatebox" maxlength="4" /></td>
						</tr>
						<tr>
							<td>最低收费额(单位:元)</td>
							<td><input id="minfee2"
								name="accumulateRateModel.minfee2Str" maxlength="12"
								validType="amount" type="text" class="easyui-validatebox" /></td>
							<td width="15%">最高收费额(单位:元)</td>
							<td><input id="maxfee2"
								name="accumulateRateModel.maxfee2Str" maxlength="12"
								validType="amount" type="text" class="easyui-validatebox" /></td>
						</tr>

						<tr>
							<td>阶梯2(单位：元)</td>
							<td><input id="limit2" name="accumulateRateModel.limit2Str"
								validType="amount" type="text" class="easyui-validatebox"
								maxlength="12" /></td>
							<td width="15%">扣率(百分比)</td>
							<td><input id="feerate3"
								name="accumulateRateModel.feerate3Str" validType="percent"
								type="text" class="easyui-validatebox" maxlength="4" /></td>
						</tr>
						<tr>
							<td>最低收费额(单位:元)</td>
							<td><input id="minfee3"
								name="accumulateRateModel.minfee3Str" maxlength="12"
								validType="amount" type="text" class="easyui-validatebox" /></td>
							<td width="15%">最高收费额(单位:元)</td>
							<td><input id="maxfee3"
								name="accumulateRateModel.maxfee3Str" maxlength="12"
								validType="amount" type="text" class="easyui-validatebox" /></td>
						</tr>
						<tr>
							<td>备注</td>
							<td colspan="3"><textarea id="notes" rows="3" cols="75"
									name="accumulateRateModel.notes" maxlength="32"></textarea></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" id="save_button" iconCls="icon-ok"
					href="javascript:saveAccumulateRate()" onclick="">保存</a> <a
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
	  var panelHeight = 360;
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
					$("#feever_ins").html(html);
				}
			});
		});
		var width = $("#continer").width();
		$(function() {
			$('#test').datagrid({
				title: '累计扣率列表',
				iconCls: 'icon-save',
				height: gridHeight,
				singleSelect: true,
				nowrap: false,
				striped: true,
				url: 'pages/fee/queryAccumulateRateFeeAction.action',
	
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
					field: 'RATE_TYPE',
					title: '收费类型',
					width: 150,
					align: 'center',
					formatter:function(value, rec){
						return '分段计费';
					}
				},
				{
					field: 'ACCMODE',title: '累计方式', width: 100,align: 'center',
						formatter : function(value, rec) {
							if (value == 0) {
								return '日';
							} else if (value == 1) {
								return '月';
							} else if(value == 2){
								return '年';
							}
						}
				},
				{
					field: 'FEE_RATE',
					title: '扣率(百分比)',
					width: 100,
					align: 'center'
				},
				{
					field: 'MIN_FEE',
					title: '最低收费额',
					width: 100,
					align: 'center'
				},
				{
					field: 'MAX_FEE',
					title: '最高收费额',
					width: 100,
					align: 'center'
				},				
				{
					field: 'TID',
					title: '操作',
					width: 100,
					align: 'center',
					formatter: function(value, rec) {
						return '<a href="javascript:showAccumulateRate(\'' + rec.TID + '\')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp<a href="javascript:showAccumulateRateDetail(\'' + rec.TID + '\')" style="color:blue;margin-left:10px">详情</a>';
					}
				}
	
				]],
				pagination: true,
				rownumbers: true,
				toolbar: [{
					id: 'btnadd',
					text: '累计类型扣率',
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
			$("#cancel_button").linkbutton('enable');
			$('#accumulateRateForm').clearForm();
			$("#accumulateRateForm").attr("action", "pages/fee/saveAccumulateRateFeeAction.action");
			$('#w').window({
				title: '累计扣率信息',
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: true,
				closed: false,
			});
			showFee();
		}
		//保存 
		function saveAccumulateRate() {
		       var minfee =$("#minfee").val();
		       var maxfee = $("#maxfee").val();
		       var minfee2 = $("#minfee2").val();
		       var maxfee2 = $("#maxfee2").val();
		       var minfee3 = $("#minfee3").val();
		       var maxfee3 = $("#maxfee3").val();
		       var minfee_min = parseFloat(minfee);
		       var maxfee_max = parseFloat(maxfee);
		       var minfee2_min = parseFloat(minfee2);
		       var maxfee2_max = parseFloat(maxfee2);
		       var minfee3_min = parseFloat(minfee3);
		       var maxfee3_max = parseFloat(maxfee3);
		       if((minfee_min >maxfee_max) || (minfee2_min >maxfee2_max) || (minfee3_min>maxfee3_max)){
		    	   alert("最低收费额不能大于最高收费额");
		       }else if($("#ratetype").val() == null || $("#ratetype").val() == ""){
		    	   alert("请选择收费类型");
		       } else{
					if ($('#accumulateRateForm').form("validate")) {
						$("#save_button").linkbutton('disable');
						$('#accumulateRateForm').form('submit', {
							onSubmit: function() {
								$("#busipack").removeAttr("disabled");
								$("#busicase").removeAttr("disabled");
								return $('#accumulateRateForm').form('validate');
							},
							success: function(data) {
								if (data == '添加成功!') {
									alert(data);
									closeAdd();
									search();
								} else if (data == '修改成功!') {
									alert(data);
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
		       

		}
		function showFee() {
			$.ajax({
				type: "POST",
				url: "pages/fee/queryFeeAllFeeAction.action",
				dataType: "json",
				async:false,
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
		function showAccumulateRate(tid) {
			showAdd(true);
			showFee();
			$("#save_button").show();
			$("#cancel_button").show();
			$.ajax({
				type: "POST",
				url: "pages/fee/queryOneAccumulateRateFeeAction.action?caseid=" + tid,
				async:false,
				dataType: "json",
				success: function(json) {	
					showFeeCase2(json.FEEVER);
					$("#ratetype").val(json.RATE_TYPE);
					$("#servicefee").val(json.SERVICEFEE);
					$("#feerate").val(json.FEE_RATE);
					$("#accmode").val(json.ACCMODE);
					$("#minfee").val(json.MIN_FEE);
					$("#maxfee").val(json.MAX_FEE);
					$("#limit1").val(json.LIMIT1);
					$("#feerate2").val(json.FEE_RATE2);
					$("#minfee2").val(json.MIN_FEE2);
					$("#maxfee2").val(json.MAX_FEE2);
					
					$("#limit2").val(json.LIMIT2);
					$("#feerate3").val(json.FEE_RATE3);
					$("#minfee3").val(json.MIN_FEE3);
					$("#maxfee3").val(json.MAX_FEE3);
					
					$("#notes").val(json.NOTES);
					setTimeout(function() {
						//alert(json.FEEVER);
						$("#busipack").val(json.FEEVER);
						$("#busicase").val(json.BUSICODE);
						$("#cardtype").val(json.CARDTYPE);
						$("#accmode").val(json.ACCMODE);
					},
					500);
	
				}
			});
			$("#accumulateRateForm").attr("action", "pages/fee/updateAccumulateRateFeeAction.action");
	
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
				'accumulateRateModel.feever': $('#feever_ins').val()
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
		function showAccumulateRateDetail(tid) {
			showAdd(true);
			showFee();
			$.ajax({
				type: "POST",
				url: "pages/fee/queryOneAccumulateRateFeeAction.action?caseid=" + tid,
				dataType: "json",
				success: function(json) {	
					showFeeCase2(json.FEEVER);						
					$("#ratetype").val(json.RATE_TYPE);
					$("#servicefee").val(json.SERVICEFEE);
					$("#accmode").val(json.ACCMODE);
					$("#feerate").val(json.FEE_RATE);	
					
					$("#minfee").val(json.MIN_FEE);
					$("#maxfee").val(json.MAX_FEE);
					
					$("#limit1").val(json.LIMIT1);
					$("#feerate2").val(json.FEE_RATE2);
					$("#minfee2").val(json.MIN_FEE2);
					$("#maxfee2").val(json.MAX_FEE2);
					
					$("#limit2").val(json.LIMIT2);
					$("#feerate3").val(json.FEE_RATE3);
					$("#minfee3").val(json.MIN_FEE3);
					$("#maxfee3").val(json.MAX_FEE3);					
					$("#notes").val(json.NOTES);

					setTimeout(function() {
						//alert(json.FEEVER);
						$("#busipack").val(json.FEEVER);
						$("#busicase").val(json.BUSICODE);
						$("#cardtype").val(json.CARDTYPE);
						$("#accmode").val(json.ACCMODE);
					},
					500);
	
				}
			});
			$("#save_button").hide();
			$("#cancel_button").hide();
		}
	</script>
</html>
