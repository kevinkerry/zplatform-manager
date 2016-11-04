<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<body>
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
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="15%">会员编号</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							name="memberId" id="memberId" maxlength="15" /></td>
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

</body>

<script>
  	var width = $("#continer").width();
		$(function(){
			$('#test').datagrid({
				title:'会员账户信息列表',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/merchant/queryACCByPageUploadAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'MEMBERID',title:'会员编号',width:120,align:'center'},
					{field:'BUSIACCT_CODE',title:'业务账户号',width:220,align:'center'},
					{field:'BALANCE',title:'可用余额(元)',width:150,align:'center'},
					{field:'FROZEN_BALANCE',title:'冻结余额(元)',width:150,align:'center'},
					{field:'TOTAL_BALANCE',title:'账户余额(元)',width:150,align:'center'},
					{field:'STATUS',title:'状态',width:100,align:'center',
						formatter:function(value,rec){
						if(value=="11"){
							return "冻结";
						}else if(value=="00"){
							return "正常";
						}else if(value=="10"){
							return "止入";
						}else if(value=="01"){
							return "止出";
						}else if(value=="99"){
							return "注销";
						}
						}
					}/* ,	
					{field:'DEPT_ID',title:'操作',width:100,align:'center',
					formatter:function(value,rec){
						if(rec.STATUS=="0"){
							return '<a href="javascript:showDept('+value+')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteDept('+value+')" style="color:blue;margin-left:10px">注销</a>';
						}else{
							return "";
						}
						
					}
				    } */
					
				]],
				pagination:true,
				rownumbers:true
		
			});
	
		});
		

	    
		function search(){
			var data={memberId:$('#memberId').val(),"acctCode":$("#acctCode").val()};
			$('#test').datagrid('load',data);
		}
		

					
	</script>
</html>
