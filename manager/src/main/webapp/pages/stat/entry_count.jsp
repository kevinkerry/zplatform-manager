<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <body> 
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:100px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post" action="pages/stat/entryCountStatAction.action">
				<table width="100%">
					<tr>
						<td align="right" width="10%">起始时间</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input id="beginDate" name="entryCountRequest.beginDate"/>
						</td>
						<td align="right" width="10%">截止时间</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input id="endDate" name="entryCountRequest.endDate"/>
						</td>
						</tr>
						<tr>
						<td align="right" width="10%">账户号</td>
						<td align="left" style="padding-left: 5px" width="15%" >
							<input id="acctCode" name="entryCountRequest.acctCode"/>
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
  </body>
  
  <script>
	  var width = $("#continer").width();
	  $(function() {
	  	$('#beginDate,#endDate').datebox();
	  	var data = {
				 'entryCountRequest.beginDate': $('#beginDate').datebox('getValue'),
				 'entryCountRequest.endDate': $('#endDate').datebox('getValue'),
				 'entryCountRequest.acctCode': $('#acctCode').val()
				};	
	  	$('#test').datagrid({ 
	  		title: '分录流水统计列表',
	  		iconCls: 'icon-save',
	  		height: 600,
	  		singleSelect: true,
	  		nowrap: false,
	  		striped: true,
	  		url: 'pages/stat/entryCountStatAction.action',
	  		remoteSort: false,
	  		queryParams: data,
	  		pagination:true,
	  		columns: [[{
	  			field: 'ACCT_CODE',
	  			title: '账户号',
	  			align: 'center',
	  			width: 140
	  		},
	  		{
	  			field: 'ACCT_CODE_NAME',
	  			title: '账户名',
	  			align: 'center',
	  			width: 160
	  		},
	  		{
	  			field: 'PARENT_ACCT_NAME',
	  			title: '父级账户',
	  			align: 'center',
	  			width: 160
	  		}, 
	  		{
	  			field: 'C_NUM',
	  			title: '贷记笔数',	
	  			align: 'center',
	  			width: 120,
	  			pagination:true,
	  			formatter:function(value,rec){
	  				if(rec.crdr=='C'){
	  					return '<font style="color:green">'+value+'</font>';
	  				}else if(rec.crdr=='D'){
	  					return '<font style="color:red">'+value+'</font>';
	  				}else{
	  					return value;
	  				}
	  			}
	  		},
	  		{
	  			field: 'C_AMOUNT',
	  			title: '贷记金额',
	  			align: 'center',
	  			width: 120,
	  			formatter:function(value,rec){
	  				if(rec.CRDR=='C'){
	  					return '<font style="color:green">+'+value+'</font>';
	  				}else if(rec.CRDR=='D'){
	  					return '<font style="color:red">-'+value+'</font>';
	  				}else{
	  					return value;
	  				}
	  			}
	  		},
	  		{
	  			field: 'D_NUM',
	  			title: '借记笔数',
	  			align: 'center',
	  			width: 120,
	  			formatter:function(value,rec){
	  				if(rec.CRDR=='D'){
	  					return '<font style="color:green">'+value+'</font>';
	  				}else if(rec.CRDR=='C'){
	  					return '<font style="color:red">'+value+'</font>';
	  				}else{
	  					return value;
	  				}
	  			}
	  		},
	  		{
	  			field: 'D_AMOUNT',
	  			title: '借记金额',
	  			align: 'center',
	  			width: 120,
	  			formatter:function(value,rec){
	  				if(rec.crdr=='D'){
	  					return '<font style="color:green">+'+value+'</font>';
	  				}else if(rec.crdr=='C'){
	  					return '<font style="color:red">-'+value+'</font>';
	  				}else{
	  					return value;
	  				}
	  			}
	  		},
	  		{
	  			field: 'FROZEN_NUM',
	  			title: '冻结笔数',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'FROZEN_AMOUNT',
	  			title: '冻结金额',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'UNFROZEN_NUM',
	  			title: '解冻笔数',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'UNFROZEN_AMOUNT',
	  			title: '解冻金额',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'BEFBALANCE',
	  			title: '期初余额',
	  			align: 'center',
	  			width: 120
	  		},
	  		{
	  			field: 'AFTBALANCE',
	  			title: '期末余额',
	  			align: 'center',
	  			width: 120
	  		}]]
	  	});  
	  });
	  function search() {
		  var data = {
			 'entryCountRequest.beginDate': $('#beginDate').datebox('getValue'),
			 'entryCountRequest.endDate': $('#endDate').datebox('getValue'),
			 'entryCountRequest.acctCode': $('#acctCode').val()
			};	
	  	$('#test').datagrid('load',data);
	  }
	</script>
</html>
