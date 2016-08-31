<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<html>
<head>
<style type="text/css">
  	table tr td{height:25px}
  	table tr td input{height:15px}
  	table tr td select{height:20px}
  </style>
  <script type="text/javascript" src="js/extendsValidator_1.0_20151215.js" ></script>
</head>
  <body>
  
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:100px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" >
				<table width="100%">
				
				<tr>
						<td align="right" width="15%">扣率版本</td>
						<td align="left" style="padding-left:5px" width="25%">
							<select id="busiPack_ins" class="easyui-validatebox"   onchange="showFeeCase_query()">
							</select>
						</td>
						<!-- <td align="right" width="15%">业务版本号</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<input id="busiPackName" >
						</td> -->
						<td align="right">
							 
						</td>
					</tr>
					<tr>
						<td align="right" width="15%">业务</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<select id="busiCase_ins"  class="easyui-validatebox" >
							<option value="">--请选择业务--</option>
							</select>
						</td>
						<td align="right" width="15%">卡类别</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<select class="easyui-validatebox" id="cardtype_ins"  >
							<option value="">--请选择卡类别--</option>
							<option value="1">--借记--</option>
							<option value="2">--贷记--</option>
							<option value="3">--准贷记--</option>
							<option value="4">--月付费--</option>
							</select>
						</td>
						<td align="right">
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
					</tr>
					
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
		
	</div>
	<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;text-align: center">
				<form id="txnRateForm" action="pages/fee/saveMemberRateFeeAction.action" method="post" >
				<table width="100%" cellpadding="2" cellspacing="2" style="text-align: left">
					<tr>
						<td width="15%">扣率版本</td>
						<td width="30%">
						<select id="busipack" class="easyui-validatebox"  required="true" name="cardrateModel.feever" onchange="showFeeCase()">
							</select>
						</td>
						<td width="15%" >业务</td>
						<td>
							<select id="busicase" class="easyui-validatebox"  required="true" name="cardrateModel.busicode" >
							<option value="">--请选择业务--</option>
							</select>
						</td>
					</tr>
                    <tr>
						<td width="15%">卡类别</td>
						<td width="30%">
				            <select id="cardtype" class="easyui-validatebox"  required="true" name="cardrateModel.cardtype" >
							<option value="">--请选择卡类型--</option>
							<option value="1">--借记--</option>
							<option value="2">--贷记--</option>
							<option value="3">--准贷记--</option>
							<option value="4">--月付费--</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>扣率类型</td>
						<td>
							<select id="feeRateType" class="easyui-validatebox"  required="true"  name="cardrateModel.rateType" onchange="showRateDetail()">
							<option value="">--请选择扣率类型--</option>
							<option value="01">--单笔固定金额--</option>
							<option value="02">--单位比例+不封顶保底--</option>
							<option value="03">--单位比例+封顶保底--</option>
							</select>
						</td>
						<td width="15%" >扣率(百分比)</td>
						<td>
							<input id="feeRate" name="cardrateModel.feeRateStr" validType="percent"  type="text" class="easyui-validatebox" maxlength="5"/>
						</td>
					</tr>
					<tr>
						<td>最低收费额(单位:元)</td>
						<td>
							<input id="minFee" name="cardrateModel.minFeeStr" maxlength="12" validType="amount"  type="text"  class="easyui-validatebox"  />
						</td>
						<td width="15%">最高收费额(单位:元)</td>
						<td>
							<input id="maxFee" name="cardrateModel.maxFeeStr"  maxlength="12" validType="amount" type="text"  class="easyui-validatebox" />
						</td>
					</tr>
					<tr>						
						<td>备注</td>
						<td colspan="3">
							<textarea id="notes" rows="3" cols="75" name="cardrateModel.notes" maxlength="32"></textarea>
						</td>
					</tr>
				</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" id="save_button" iconCls="icon-ok" href="javascript:saveTxnRate()" onclick="">保存</a>
				<a class="easyui-linkbutton" id="cancel_button" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
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
				title: '卡扣率列表',
				iconCls: 'icon-save',
				height: gridHeight,
				singleSelect: true,
				nowrap: false,
				striped: true,
				url: 'pages/fee/queryCardRateFeeAction.action',
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
					field: 'CARDTYPE',
					title: '卡类型',
					width: 150,
					align: 'center',
					formatter: function(value, rec) {
						if (value == '1') {
							return '借记';
						} else if (value == '2') {
							return '贷记';
						} else if (value == '3') {
							return '准贷记';
						} else if (value == '4') {
							return '月付费';
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
					text: '新增卡类型扣率',
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
			$("#txnRateForm").attr("action", "pages/fee/saveCardRateFeeAction.action");
			$('#w').window({
				title: '卡扣率信息',
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
						if(data.split("validateUserLoginAction").length>1){
							window.parent.location.replace("<%=basePath%>"+"pages/logoutAction.action?relogin=relogin");
							return ;
						}
						if (data == '添加成功!') {
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
				url: "pages/fee/queryOneCardRateFeeAction.action?caseid=" + tid,
				dataType: "json",
				success: function(json) {

					showFeeCase2(json.FEEVER);
					$("#feeRate").val(json.FEE_RATE);
					$("#feeRateType").val(json.RATE_TYPE);
					$("#minFee").val(json.MIN_FEE);
					$("#maxFee").val(json.MAX_FEE);
					$("#notes").val(json.NOTES);
					setTimeout(function() {
						//alert(json.FEEVER);
						$("#busipack").val(json.FEEVER);
						$("#busicase").val(json.BUSICODE);
						$("#cardtype").val(json.CARDTYPE);
					},500);
				}
			});
			$("#txnRateForm").attr("action", "pages/fee/updateCardRateFeeAction.action");
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
				'cardrateModel.feever': $('#busiPack_ins').val(),
				'cardrateModel.busicode': $("#busiCase_ins").val(),
				'cardrateModel.cardtype': $("#cardtype_ins").val()
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
				url: "pages/fee/queryOneCardRateFeeAction.action?caseid=" + tid,
				dataType: "json",
				success: function(json) {
					showFeeCase2(json.FEEVER);
					$("#feeRate").val(json.FEE_RATE);
					$("#feeRateType").val(json.RATE_TYPE);
					$("#minFee").val(json.MIN_FEE);
					$("#maxFee").val(json.MAX_FEE);
					setTimeout(function() {
						//alert(json.FEEVER);
						$("#busipack").val(json.FEEVER);
						$("#busicase").val(json.BUSICODE);
						$("#cardtype").val(json.CARDTYPE);
					},500);
				}
			});
			$("#txnRateForm").attr("action", "pages/fee/updateCardRateFeeAction.action");
			$("#save_button").hide();
			$("#cancel_button").hide();
		}
	</script>
</html>
