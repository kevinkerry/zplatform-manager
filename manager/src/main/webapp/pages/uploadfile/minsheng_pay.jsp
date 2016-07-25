<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>

</head>
<body>
	  <style type="text/css">
  	table tr td{height:25px}
  	table tr td input{height:15px}
  	table tr td select{height:20px}
  </style>
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:100px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" action="">
				<table width="100%">
					<tr height="26">	
											
						<td align="right">交易日期</td>
						<td align="left" style="padding-left:5px" width="15%">
				        	<input id="paydate" type="text" class="easyui-datebox" name="crb.paydate"></input>
				        </td>
							
							
					    <td align="right">
								<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
														
					</tr>		
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">			
				<table id="test">
				</table>	
		</div>
			
		
	</div>

</body>

<script>

	$(function(){
		$('#test').datagrid({
			title:'民生代付信息列表',
			iconCls:'icon-save',
			height:500,
			singleSelect:true,
			nowrap: false,
			striped: true,
			url:'pages/merchant/queryMinshengUploadAction.action',
			remoteSort: false,
			idField:'TID',
			columns:[
			[
				{field:'banktrandataseqno',title:'银行转账流水号',width:120,align:'center'},
				{field:'banktranresno',title:'银行转账应答流水号',width:120,align:'center'},
				{field:'accno',title:'账号',width:120,align:'center'},
				{field:'accname',title:'账户名称',width:120,align:'center'},
				{field:'tranamt',title:'金额',width:120,align:'center'},
				{field:'restype',title:'应答类型',width:120,align:'center'},
				{field:'rescode',title:'应答码',width:120,align:'center'},
				{field:'resinfo',title:'应答信息',width:120,align:'center'},
				{field:'paydate',title:'交易日期',width:120,align:'center'},
				{field:'paydatetime',title:'交易时间',width:120,align:'center'},
				{field:'banktranbatchno',title:'转账批次号',width:120,align:'center'}			
			]],
			pagination:true,
			rownumbers:true
			
		});
	
	});

	function search(){
		var data={"crb.paydate":$('#paydate').datebox('getValue')}
		$('#test').datagrid('load',data);
	}
	

</script>
</html>