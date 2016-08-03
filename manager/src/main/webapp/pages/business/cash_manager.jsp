<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<html>
<head>
		<script type="text/javascript" src="js/checkboxbeautify/jquery-hcheckbox.js"></script>
		<link href='js/checkboxbeautify/css.css' rel="stylesheet" type="text/css" />
		<style type="text/css">
			#groupinfo {height:25px;}
			#groupinfo tr td{height:25px;border-style:solid;border-width:0px 0px 0px 0px;border-color:#000000;padding:1px}
			#groupinfo tr td input{height:20px;margin-left:3px;}
			#groupinfo tr td span{height:20px;margin-left:3px;}
			
			.activeflag_label{width:90px}
		</style>
</head>
  <body>
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:100px;padding:10px;background:#fafafa;" iconCls="icon-save" collapsible="true">
			<form action="pages/paradic/queryGroupGrouAction.action" id="searchForm">
				<table width="100%">
					<tr>
						<td align="right">收银台代码</td>
						<td align="left" style="padding-left:5px"><input id="code_ins" maxlength="8" /></td>
						<td align="right" >收银台名称</td>
						<td align="left" style="padding-left: 5px" width="15%"><input id="name_ins"  maxlength="32"/></td>
					</tr>				
					<tr>
					</tr>	
					<tr>
					    <td align="right"></td>
						<td align="right"></td>
						<td align="right"></td>
						<td style="padding-left: 5px"></td>
						<td align="right" >
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
							<a href="javascript:resize()" class="easyui-linkbutton" iconCls="icon-redo">清空</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top:5px">
			<table id="paraList">
			</table>
		</div>
	</div>
	<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;text-align: center">
				<form id="saveForm" action="" method="post">
				<input type="hidden" id="t_id" name="cashModel.cashver"/>
					<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo">
						<tr style="height: 25px">
							<td align="center" width="74px">收银台名称</td>
							<td align="left"><input type="text" id="group_name_ins" name="cashModel.cashname" class="easyui-validatebox" required="true" maxlength="32"/></td>
						</tr>
						<tr style="height: 60px">
						    <td>选择交易渠道</td>
						    <td  align="left" id="yewu_ins">
     							
		                	</td>
						</tr>
						<tr >
							<td>备注</td>
							<td align="left" colspan="3"><textarea id="group_notes_ins" rows="2" cols="75" name="cashModel.notes" maxlength="64"></textarea>
							</td>
						</tr>						
					</table>
				</form>
			</div>
			<div id="div_id" region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" id="button_id" iconCls="icon-ok" href="javascript:saveParaDic()" onClick="">提交</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onClick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
  </body>
  <script>
  	  var width = $("#continer").width();
  	  var gridHeight = 600;
	  var panelWidth = 640;
	  var panelHoriFloat = (width-panelWidth)/2;
	  var panelVertFloat = 150;
	  $(function() {
	  	$('#paraList').datagrid({
	  		title: '收银台列表',
	  		iconCls: 'icon-save',
	  		height: gridHeight,
	  		nowrap: false,
	  		striped: true,
	  		singleSelect: true,
	  		url: 'pages/product/queryCashProductAction.action',
	  		remoteSort: false,
	  		columns: [[{
	  			field: 'CASHVER',
	  			title: '收银台代码',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'CASHNAME',
	  			title: '收银台名称',
	  			align: 'center',
	  			width: 150
	  		},
	  		{
	  			field: 'INTIME',
	  			title: '创建时间',
	  			width: 120,
	  			align: 'center'
	  		},
	  		{
	  			field: 'STATUS',
	  			title: '状态',
	  			width: 120,
	  			align: 'center'
	  		},
	  		{
	  			field: 'NOTES',
	  			title: '备注',
	  			width: 150,
	  			align: 'center'
	  		},
	  		{
	  			field: 'REMARKS',
	  			title: '操作',
	  			align: 'center',
	  			width: 150,
	  			rowspan: 2,
	  			formatter: function(value, rec) {
	  				if (rec.STATUS == "注销") {
	  					//   return  '<a href="javascript:REParaDic('+rec.CASHVER+')" style="color:blue;margin-left:10px">启用</a>';	;
	  				} else {
	  					return '<a href="javascript:showUpdate(\'' + rec.CASHVER + '\')" style="color:blue;margin-left:10px">修改</a>' + '<a href="javascript:DetailParaDic(\'' + rec.CASHVER + '\')" style="color:blue;margin-left:10px">详细</a>';
	  				}
	  			}
	  		}]],
	  		pagination: true,
	  		rownumbers: true,
	  		toolbar: [{
	  			id: 'btnadd',
	  			text: '新增收银台',
	  			iconCls: 'icon-add',
	  			handler: function() {
	  				showAdd(0);
	  			}
	  		}]
	  	});
	
	  });
	
	  function resize() {
	  	$('#searchForm :input').val('');
	  }
	
	  function showAdd(num) {
	  	document.getElementById("div_id").style.display = "";
	  	//$("#checkAll").attr("checked",false);
	  	$('#saveForm :input').val('');
	  	$("#saveForm").attr("action", "pages/product/AddCashProductAction.action");
	  	//  $("#busi_code_ins").attr("readonly",false);
	  	loadYWAll();
	  	$('#w').window({
	  		title: '收银台信息',
	  		top: panelVertFloat,
	  		left: panelHoriFloat,
	  		width: panelWidth,
	  		modal: true,
	  		minimizable: false,
	  		collapsible: false,
	  		maximizable: false,
	  		shadow: false,
	  		closed: false,
	  		height: 260
	  	});
	  }
	  
	  function closeAdd() {
	  	$('#w').window('close');
	  	$("#saveForm").attr("action", "pages/product/addProductProductAction.action");
	  }
	  
	  function search() {
	  	var data = {
	  		'cashModel.cashver': $('#code_ins').val(),
	  		'cashModel.cashname': $('#name_ins').val()
	  	};
	  	$('#paraList').datagrid('load', data);
	  }
	  function saveParaDic() {
	  	if ($('#saveForm').form("validate")) {
	  		$("#button_id").linkbutton('disable');
	  		$('#saveForm').form('submit', {
	  			onSubmit: function() {
	  				return $('#saveForm').form('validate');
	  			},
	  			success: function(data) {
	  				$.messager.alert('提示', data);
	  				search();
	  				closeAdd();
	  				$("#button_id").linkbutton('enable');
	  			}
	  		});
	  	}
	
	  }
	
	  function showUpdate(pid) {
	  	document.getElementById("div_id").style.display = "";
	  	$.ajax({
	  		type: "POST",
	  		url: "pages/product/queryOneCashProductAction.action",
	  		data: "pid=" + pid,
	  		async: false,
	  		dataType: "json",
	  		success: function(json) {
	  			$("#saveForm").attr("action", "pages/product/UpdateCashProductAction.action");
	  			$("#group_name_ins").val(json.CASHNAME);
	  			$("#t_id").val(json.CASHVER);
	  			$("#group_notes_ins").val(json.NOTES);
	  			loadYwMark(pid);
	  		}
	  	});
	
	  	//$("#busi_code_ins").attr("readonly",true);
	  	$('#w').window({
	  		title: '修改收银台信息',
	  		top: panelVertFloat,
	  		left: panelHoriFloat,
	  		width: panelWidth,
	  		modal: true,
	  		minimizable: false,
	  		collapsible: false,
	  		maximizable: false,
	  		shadow: false,
	  		closed: false,
	  		height: 260
	  	});
	  }
	
	  function DetailParaDic(pid) {
	  	document.getElementById("div_id").style.display = "none";
	  	//$("#div_id").attr("display","none");	
	  	$.ajax({
	  		type: "POST",
	  		url: "pages/product/queryOneCashProductAction.action",
	  		data: "pid=" + pid,
	  		async: false,
	  		dataType: "json",
	  		success: function(json) {
	  			$("#saveForm").attr("action", "pages/paradic/UpdateGroupGrouAction.action");
	  			$("#group_name_ins").val(json.CASHNAME);
	  			$("#t_id").val(json.CASHVER);
	  			$("#group_notes_ins").val(json.NOTES);
	  			loadYwMark(pid);
	  		}
	  	});
	  	//$("#busi_code_ins").attr("readonly",true);
	  	$('#w').window({
	  		title: '收银台信息',
	  		top: panelVertFloat,
	  		left: panelHoriFloat,
	  		width: panelWidth,
	  		modal: true,
	  		minimizable: false,
	  		collapsible: false,
	  		maximizable: false,
	  		shadow: false,
	  		closed: false,
	  		height: 260
	  	});
	  }
	
	  function deleteParaDic(pId) {
	  	$.messager.confirm('提示', '您确定注销此产品?',
	  	function(r) {
	  		if (r) {
	  			$.ajax({
	  				type: "GET",
	  				url: "pages/paradic/DelectGroupGrouAction.action",
	  				data: "rand=" + new Date().getTime() + "&pid=" + pId,
	  				dataType: "text",
	  				success: function(text) {
	  					$.messager.alert('提示', text);
	  					search();
	  				}
	  			});
	  		}
	  	});
	
	  }
	  function REParaDic(pId) {
	  	$.messager.confirm('提示', '您是否想要启用此业务版本?',
	  	function(r) {
	  		if (r) {
	  			$.ajax({
	  				type: "GET",
	  				url: "pages/paradic/ReUSERBusiPackBPackAction.action",
	  				data: "rand=" + new Date().getTime() + "&pid=" + pId,
	  				dataType: "text",
	  				success: function(text) {
	  					$.messager.alert('提示', text);
	  					search();
	  				}
	  			});
	  		}
	  	});
	
	  }
	  
	  function loadYWAll() {
	  	var mark = 0;
	  	var html = '';
	  	$.ajax({
	  		type: "GET",
	  		url: "pages/product/queryChnlTypeProductAction.action",
	  		data: "rand=" + new Date().getTime(),
	  		dataType: "json",
	  		success: function(json) {
	  			$.each(json,
	  			function(key, value) {
	  				html += '<input type="checkbox" id="checkboxList" name="checkboxList" style="align:left" value="' + value.CHNLCODE + '" /><label class="activeflag_label">' + value.CHNLNAME + '</label>';
	  				if (mark == 3 || mark == 7) {
	  					html += '<br/>';
	  				}
	  				mark = mark + 1;
	  			});
	  			$("#yewu_ins").html(html);
	  			$("#yewu_ins").hcheckbox();
	  		}
	  	});
	  }
	  
	  function loadYwMark(pid) {
	  	var mark = 0;
	  	var html = '';
	  	$.ajax({
	  		type: "GET",
	  		url: "pages/product/queryChnlMarkProductAction.action?pid=" + pid,
	  		data: "rand=" + new Date().getTime(),
	  		dataType: "json",
	  		success: function(json) {
	  			$.each(json,
	  			function(key, value) {
	  				if (value.CODEMARK == '空') {
	  					html += '<input type="checkbox" id="checkboxList" name="checkboxList" style="width:40px" value="' + value.CHNLCODE + '" /><label class="activeflag_label">' + value.CHNLNAME + '</label>';
	  				} else {
	  					html += '<input type="checkbox" id="checkboxList" name="checkboxList" checked="checked"   style="width:40px" value="' + value.CHNLCODE + '" /><label class="activeflag_label">' + value.CHNLNAME + '</label>';
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
