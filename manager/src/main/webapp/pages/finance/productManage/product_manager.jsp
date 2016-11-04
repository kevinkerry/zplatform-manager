<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../../top.jsp"></jsp:include>
<body>
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



	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right">产品代码:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="productCode" maxlength="10" /></td>
						<td align="right">产品名称:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="productName" maxlength="64" /></td>

						<td align="right">资管人:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="fundManager" maxlength="16" /></td>
						<td align="right">融资人:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="financier" maxlength="16" /></td>
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


</body>

<script>
	function resizeAdd() {
		$(':input').val("");
	}
	$(function() {
		$('#test').datagrid({
			title : '产品信息表',
			iconCls : 'icon-save',
			height : 500,
			singleSelect : true,
			nowrap : false,
			striped : true,
			url : 'pages/finance/queryFinProductAction.action',
			remoteSort : false,
			idField : 'pid',
			collapsible : true,
			columns : [ [ {
				field : 'productCode',
				title : '产品代码',
				width : '150',
				align : 'center'
			}, {
				field : 'productName',
				title : '产品名称',
				width : '150',
				align : 'center'
			},

			{
				field : 'fundManagerName',
				title : '资管人',
				width : '150',
				align : 'center'
			},
			{
				field : 'financierName',
				title : '融资人',
				width : '150',
				align : 'center'
			},
			{
				field : 'inTime',
				title : '录入时间',
				width : '150',
				align : 'center'
				

			},
			{
				field : 'notes',
				title : '备注',
				width : '250',
				align : 'center'
				

			} 
			
			] ],
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
		var data={'bean.productCode':$('#productCode').val(),'bean.productName':$("#productName").val(),'bean.fundManagerName':$("#fundManager").val(),'bean.financierName':$("#financier").val()};
		$('#test').datagrid('load',data);
	}
	

</script>




</script>
</html>
