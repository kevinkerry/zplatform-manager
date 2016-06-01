<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>

	<head>

  <!-- 收支明细查询 -->
  <style type="text/css">
  	table tr td{height:25px}
  	table tr td input{height:15px}
  	table tr td select{height:20px}
  </style>
	
	
	</head>
	  <body>

	  
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:120px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" >
				<table width="100%" >
					<tr>
						<td align="center">
							记账状态
							</td>
							<td colspan="1">
								<select name=mq.type class="easyui-validatebox validatebox-text" id="type">
								  <option value="">请选择</option>
						          <option value="00">已记账</option>
						          <option value="01">未记账</option>
						              <option value="02">待记账</option>
					        	</select>
							</td>
								<td align="center">
							交易类型
							</td>
							<td colspan="1">
								<select name="mq.busiCode" class="easyui-validatebox validatebox-text" id="busiCode">
								  <option value="">请选择</option>
								  <c:forEach items="${bus}" var="bus">
						          <option value=${bus.busiCode }>${bus.busiName}</option>
						          </c:forEach> 
					        	</select>
							</td>
								<td align="center">订单号</td>
						<td><input name="mq.payordno" maxlength="40"   type="text"  id="payordno"/></td>
						</tr>
						<tr>
						
						<td align="center">时间段从</td>
						<td><input id="startTime" type="text" class="easyui-datebox" name="mq.startTime"></input>  
							至<input id="endTime" type="text" class="easyui-datebox" name="mq.endTime"></input></td>
							<td align="center">账户号</td>
						<td><input name="mq.acctCode" maxlength="32"   type="text"  id="acctCode"/></td>
							<td align="center">交易流水号</td>
						<td><input name="mq.txnseqno" maxlength="32"   type="text"  id="txnseqno"/></td>
					
						<td align="right" >
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
						</tr>
					<!-- 	<tr>
					
			             <td align="right" colspan="3">
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
			          
					    </tr> -->
		
		
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
		
	</div>
	
  </body>
  
  <script>
  $(function() {
	    $('#endTime,#startTime').datebox({});
	});
	var width = $("#continer").width();
	$(function() {
	    $('#test').datagrid({
	        title: '收支明细',
	        iconCls: 'icon-save',
	        height: 400,
	        singleSelect: true,
	        nowrap: false,
	        striped: true,
	        url: 'pages/acc/queryTradeDetailEntryAction.action',
	        remoteSort: false,
	        idField: 'ORGAN_ID',
	        columns: [[{
	            field: 'voucherCode',
	            title: '凭证号',
	            width: 80,
	            align: 'center'
	        },
	        {
	            field: 'acctCode',
	            title: '账户号',
	            width: 220,
	            align: 'center'
	        },
	        {
	            field: 'crdr',
	            title: '余额方向',
	            width: 100,
	            align: 'center',
	            formatter: function(value, rec) {
	                if (value == "C") {
	                    return "贷";
	                } else if (value == "D") {
	                    return "借";
	                }
	            }
	        },
	        {
	            field: 'payordno',
	            title: '支付订单号',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'txnseqno',
	            title: '交易流水号',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'inTime',
	            title: '分录时间',
	            width: 150,
	            align: 'center'

	        },
	        {
	            field: 'amount',
	            title: '交易金额单位(元)',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'status',
	            title: '记账状态',
	            width: 100,
	            align: 'center',
	            formatter: function(value, rec) {
	                if (value == "00") {
	                    return "已记账";
	                } else if (value == "01") {
	                    return "未记账";
	                } else if (value == "02") {
	                    return "待记账";
	                }
	            }
	        }
	        ]],
	        pagination: true,
	        rownumbers: true

	    });
	});

	function search() {
	    var data = {
	        'mq.busiCode': $('#busiCode').val(),
	        'mq.acctCode': $("#acctCode").val(),
	        'mq.endTime': $("#endTime").datebox('getValue'),
	        'mq.startTime': $("#startTime").datebox('getValue'),
	        'mq.payordno': $("#payordno").val(),
	        'mq.type': $("#type").val(),
	        'mq.txnseqno': $("#txnseqno").val()
	    };
	    $('#test').datagrid('load', data);
	}	
	</script>
</html>
