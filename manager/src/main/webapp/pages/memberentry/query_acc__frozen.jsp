<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>

<head>

<!-- 收支明细查询 -->
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


</head>
<body>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 120px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
					    <td align="center">会员号</td>
						<td colspan="1">
							<input name="query.memberId"  type="text" id="memberId" maxlength="15" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
							
						<td align="center">科目号</td>
						<td colspan="1"><input name="query.acctCode" type="text" id="acctCode" maxlength="22" onkeyup="value=value.replace(/[^\d]/g,'')">
						
						
						<td align="center">用途</td>
						<td colspan="1"><select name="query.usage" 
							class="easyui-validatebox validatebox-text" id="usage" style="width:100px"/>
							<option value="">请选择</option>
							<option value="101">现金账户</option>
							<option value="102">银行存款</option>
							<option value="103">应收银行</option>
							<option value="104">证联收转存款</option>
							<option value="105">应付银行</option>
							<option value="106">通道手续费支出</option>
							<option value="107">待结算</option>
							<option value="108">应付待分润</option>
							<option value="109">保证金</option>
							<option value="110">手续费收入</option>							
						</select></td>
						
						<td align="center">冻结开始时间段从:</td>
						<td><input id="frozenSTimeFrom" type="text"
							style="width: 120PX" class="easyui-datetimebox"
							data-options="showSeconds:true" name="query.frozenSTimeFrom"></input>
							至<input id="frozenStimeTo" type="text" style="width: 120PX"
							class="easyui-datetimebox" data-options="showSeconds:true"
							name="query.frozenStimeTo"></input></td>
					</tr>
					<tr>
						<td align="center">交易流水号</td>
						<td colspan="1"><input name="query.txnseqno" type="text" id="txnseqno" maxlength="32" onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
						
						<td align="center">账户名</td>
						<td colspan="1"><input name="query.acctCodeName" maxlength="128"
							type="text" id="acctCodeName" /></td>

                        <td align="center">状态</td>
						<td colspan="1"><select name="query.status"
							class="easyui-validatebox validatebox-text" id="status" style="width:100px">
								<option value="">请选择</option>
								<option value="00">已解冻</option>
								<option value="01">冻结中</option>
						</select></td>

						<td align="center">冻结结束时间从:</td>
						<td><input id="frozenTimeFrom" type="text"
							style="width: 120PX" class="easyui-datetimebox"
							data-options="showSeconds:true" name="query.frozenTimeFrom"></input>
							至<input id="frozentimeTo" type="text" style="width: 120PX"
							class="easyui-datetimebox" data-options="showSeconds:true"
							name="query.frozentimeTo"></input>
						</td>

						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>

	</div>

</body>

<script>
	$(function(){
	  	  $('#frozenSTimeFrom,#frozenStimeTo,#frozenTimeFrom,#frozentimeTo').datebox({}); 
	}); 	
  	var width = $("#continer").width();
		$(function(){
			$('#test').datagrid({
				title:'冻结账户查询',
				iconCls:'icon-save',
				height:520,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/acc/freezeAmountFrozenAccountAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
                    {field:'memberId',title:'会员号',width:150,align:'center'},
                    {field:'acctCode',title:'科目号',width:180,align:'center'},
                    {field:'acctCodeName',title:'账户名',width:110,align:'center'},
                    {field:'usage',title:'用途',width:110,align:'center',
                    	formatter:function(value,rec){
        					if(value=="101"){
        						return "现金账户";
        					}else if(value=="102"){
        						return "银行存款";
        					}else if(value=="103"){
        						return "应收银行";
        					}else if(value=="104"){
        						return "证联收转存款";
        					}else if(value=="105"){
        						return "应付银行";
        					}else if(value=="106"){
        						return "通道手续费支出";
        					}else if(value=="107"){
        						return "待结算";
        					}else if(value=="108"){
        						return "应付待分润";
        					}else if(value=="109"){
        						return "保证金";
        					}else if(value=="110"){
        						return "手续费收入";
        					}else{
        						return "";  
        					}
        					}
                    },
					{field:'txnseqno',title:'交易流水号',width:110,align:'center'},
					 {field:'frozenBalance',title:'冻结金额(元)',width:100,align:'center'},
					{field:'frozenStime',title:'冻结开始时间',width:150,align:'center'},
					{field:'frozenTime',title:'冻结结束时间',width:150,align:'center'},
					{field:'status',title:'状态 ',width:150,align:'center',
						formatter:function(value,rec){
							if(value=="00"){
								return "已解冻";
							}else if(value=="01"){
								return "冻结中";
							}
						}
					
					},
				
					{field:'id',title:'操作',width:200,align:'center',
						formatter:function(value,rec){
							
							if(rec.status=="01"){
								return '<a href="javascript:unFreezeAmount(\''+rec.id+'\')" style="color:blue;margin-left:10px">解冻</a>';
							}else if (rec.status=="00"){
					          return '';
							}
						}
					}
				]],
				pagination:true,
				rownumbers:true
		
			});
	
		});
	
		function search(){
			var data={
					'query.memberId':$('#memberId').val(),
					'query.acctCode':$("#acctCode").val(),
					'query.usage':$("#usage").val(),
					'query.frozenSTimeFrom':$("#frozenSTimeFrom").datebox('getValue'),
					'query.frozenStimeTo':$("#frozenStimeTo").datebox('getValue'),
					
					'query.txnseqno':$("#txnseqno").val(),
					'query.acctCodeName':$("#acctCodeName").val(),
					'query.status':$("#status").val(),
					'query.frozenTimeFrom':$("#frozenTimeFrom").datebox('getValue'),
					'query.frozentimeTo':$("#frozentimeTo").datebox('getValue')
			};
			 $('#test').datagrid('load',data);
		}
		
		
		function unFreezeAmount(id){
	
				message='您是否想解冻资金?';
			
			$.messager.confirm('提示',message,function(r){   
			   if (r){  
				$.ajax({
				   type: "POST",
				   url: "pages/acc/unFreezeAmountFrozenAccountAction.action",
				   data: "id="+id,
				   dataType:"json",
				   success:function(json){
					
				    		$.messager.alert('提示',json.messg);   
				    		search();
				    	
						
				   
				 	}
				});
				    }   
				});  
		}	
					
	</script>
</html>
