<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<body>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post"
				action="pages/stat/tradeStatStatAction.action">
				<table width="100%">
					<tr>
						<td align="right" width="10%">起始时间</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="beginDate" name="tradeStatRequest.beginDate" /></td>
						<td align="right" width="10%">截止时间</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="endDate" name="tradeStatRequest.endDate" /></td>
					</tr>
					<tr>
						<td align="right" width="10%">一级商户会员号</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="firLevMemId" name="tradeStatRequest.firLevMemId" /></td>
						<td align="right" width="10%">二级商户会员号</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							id="secLevMemId" name="tradeStatRequest.secLevMemId" /></td>
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
	  $(function() {
	  	$('#beginDate,#endDate').datebox();
	  	var data = {
	  		'tradeStatRequest.beginDate': $('#beginDate').datebox('getValue'),
	  		'tradeStatRequest.endDate': $('#endDate').datebox('getValue'),
	  		'tradeStatRequest.firLevMemId': $('#firLevMemId').val(),
	  		'tradeStatRequest.secLevMemId': $('#secLevMemId').val()
	  	};
	  	$('#test').datagrid({
	  		title: '交易统计列表',
	  		iconCls: 'icon-save',
	  		height: 600,
	  		singleSelect: true,
	  		nowrap: false,
	  		striped: true,
	  		url: 'pages/stat/tradeStatStatAction.action',
	  		remoteSort: false,
	  		queryParams: data,
	  		columns: [[{
	  			field: 'chnlName',
	  			title: '渠道名称',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'busiName',
	  			title: '业务类型',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'succAmount',
	  			title: '成功交易金额',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'failAmount',
	  			title: '失败交易金额',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'succTxnFee',
	  			title: '成功交易手续费',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'failTxnFee',
	  			title: '失败交易手续费',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'succTradComm',
	  			title: '成功交易佣金',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'failTradComm',
	  			title: '失败交易佣金',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'succCount',
	  			title: '成功交易笔数',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'failCount',
	  			title: '失败交易笔数',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'totalCount',
	  			title: '总笔数',
	  			align: 'center',
	  			width: 120
	  		}]]
	  	});
	  });
	  function search() {
		  var data = {
			'tradeStatRequest.beginDate': $('#beginDate').datebox('getValue'),
			'tradeStatRequest.endDate': $('#endDate').datebox('getValue'),
			'tradeStatRequest.firLevMemId': $('#firLevMemId').val(),
			'tradeStatRequest.secLevMemId': $('#secLevMemId').val()
		  };	
	  	$('#test').datagrid('load',data);
	  }
	</script>
</html>
