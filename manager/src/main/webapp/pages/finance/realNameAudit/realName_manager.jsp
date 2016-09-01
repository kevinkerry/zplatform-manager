<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../../top.jsp"></jsp:include>
<body>
	<link href='js/checkboxbeautify/css.css' rel="stylesheet"
		type="text/css" />
	<script type="text/javascript" src="js/extendsValidator_1.0_20151215.js"></script>
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



	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right">企业会员号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							 id="memberId"  maxlength="32" />
						</td>
						<td align="right" width="10%">企业名称:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							 id="enterpriseName"  maxlength="32" /></td>
						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
							href="javascript:resizeAdd()" class="easyui-linkbutton"
							iconCls="icon-redo">清空</a></td>
					</tr>
				</table>
			</form>
		</div>

		<div style="margin-top: 5px">
			<table id="test">
			</table>
		</div>
	</div>

	
	
	
	<div id="edit" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save"    style="width:500px;height:200px;padding:5px;" >
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<div>
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="solid">
			<tr><td whdth="40px" align="center">&nbsp;企业会员号:</td><td align="center" whdth="40px" id="fin_memberId"></td> 
			<td whdth="40px" align="center" >&nbsp;企业名称</td><td whdth="40px"  align="center"  id="fin_enterpriseName"></td> </tr>
			<tr><td whdth="40px"  align="center">&nbsp;账户号:</td><td align="center" whdth="20%"  id="fin_accNo"></td> 
			<td  whdth="20%" align="center" >&nbsp;账户名:</td><td align="center" whdth="20%"  id="fin_accName"></td> </tr>
			<tr><td whdth="20%"  align="center">&nbsp;账户类型:</td><td  align="center" whdth="20%" >对公</td> 
			<td whdth="20%"  align="center">&nbsp;手机号:</td><td align="center" whdth="20%"  id="fin_mobil"></td> </tr>
		
					</table>
				</div>
				<br>
				<form id="firstTrial" method="post" action="">
					<input id="fin_tid" name="bean.tid" type="hidden"> 
					<input id="flag" type="hidden" name="bean.auditFlag">
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td align="center">打款金额:</td>
							<td><input name="bean.amount" id="amount" maxlength="20"  precision="2" class="easyui-numberbox" />元</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td align="center">初审意见:</td>
							<td><textarea rows="5" cols="80" name="bean.opinion" maxlength="255"  id="opinion">
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
	function resizeAdd() {
		$(':input').val("");
	}
	$(function() {
		$('#test').datagrid({
			title : '实名认证列表',
			iconCls : 'icon-save',
			height : 500,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/finance/queryFinRealNameAuditAction.action',
			remoteSort : false,
			idField : 'tid',
			collapsible : true,
			columns : [ [ {
				field : 'memberId',
				title : '企业会员号',
				width : 120,
				align : 'center'
			}, {
				field : 'enterpriseName',
				title : '企业名称',
				width : 120,
				align : 'center'
			},

			{
				field : 'accNum',
				title : '账户账号',
				width : 120,
				align : 'center'
			},
			{
				field : 'accName',
				title : '账户名',
				width : 120,
				align : 'center'
			},
			{
				field : 'type',
				title : '账户类型',
				width : 120,
				align : 'center',
				formatter : function(value, rec) {
					return '对公'
				}

			}, {
				field : 'contactsTelNo',
				title : '联系人手机号',
				width : 120,
				align : 'center'
				
			}, 
			{
				field : 'status',
				title : '状态',
				width : 120,
				align : 'center',
				formatter : function(value, rec) {

					if (value == '01') {
						return '待审核';
					} else if (value == '09') {
						return '审核未过';
					} else if (value == '00') {
						return '正常';
					}else {
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
					if (rec.status == '01') {
						return '<a href="javascript:toAudit('+rec.tid+')" style="color:blue;margin-left:10px">审核</a>';
					} else {
						return ;
					} 
				}
			} ] ],
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false,
			pagination : true,
			rownumbers : true,
			toolbar : []
		});

		$($('#test2').datagrid('getPanel')).panel('collapse', false);
		 });


	function search(){
		var data={'bean.memberId':$('#memberId').val(),'bean.enterpriseName':$("#enterpriseName").val()};
		$('#test').datagrid('load',data);
	}
	
	function toDetail(tid){
		
	}
	function toAudit(tid){	
		$("#flag").val(null);
		$("#amount").val(null);
		$("#opinion").val(null);
		
		$.ajax({
		   type: "POST",
		   url: "pages/finance/getByIdFinRealNameAuditAction.action",
		   data: "bean.tid="+tid,
		   dataType:"json",
		   success: function(json){
				$("#fin_memberId").html(json.memberId);
				$('#fin_enterpriseName').html(json.enterpriseName);
				$("#fin_accNo").html(json.accNum);					
				$("#fin_accName").html(json.accName);						
				$("#fin_mobile").html(json.notes);
				$("#fin_tid").val(json.tid);
				$("#fin_status").val(json.status);
		   }
	   });
		$('#edit').window({
			title: '审核',
			top:100,
			width: 600,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			modal: true,
			shadow: false,
			closed: false,
			height: 350
		});
		$('#btn_submit').linkbutton('enable');
		$('#icon-cancel').linkbutton('enable');
	}
	
	function firstTrial(falg) {
	    if (falg == true) {
	    	var amount=$("#amount").val();
	    	if(!amount){
	    		 $.messager.alert('提示', "金额不能为空");
	    		 return;
	    	}
	        $("#firstTrial").attr("action", "pages/finance/auditFinRealNameAuditAction.action");
	        $("#flag").val("true");
	    } else {
	    	var opinion=$("#opinion").val();
	    	if(!opinion){
	    		 $.messager.alert('提示', "审核意见不能为空");
	    		 return;
	    	}
	        $("#firstTrial").attr("action", "pages/finance/auditFinRealNameAuditAction.action");
	        $("#flag").val("false");
	        $("#amount").val("");
	    }
	    $('#firstTrial').form('submit', {
	        onSubmit: function() {
	            if ($('#firstTrial').form('validate')) {
	                $('#btn_submit').linkbutton('disable');
	                $("#icon-cancel").linkbutton('disable');
	                return true;
	            }
	            return false;
	        },
	        success: function(data) {
	            $.messager.alert('提示', "操作成功");
	            search();
	            closeAdd();
	        }
	    });
	}
	
	function closeAdd(){
		$('#edit').window('close');
		
	}
</script>

	

	
</script>
</html>
