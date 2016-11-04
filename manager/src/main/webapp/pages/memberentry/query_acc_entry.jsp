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
						<td align="center">凭证号</td>
								<td><input name="mq.voucherCode"  type="text" id="voucherCode" maxlength="32"  onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						
						<td align="center">科目号</td>
								<td><input name="mq.acctCode"  type="text" id="acctCode" maxlength="22" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						
						<td align="center">交易类型</td>
						<td colspan="1">
							<select name="mq.busiCode"
								class="easyui-validatebox validatebox-text" id="busiCode" style="width:130px">
									<option value="">请选择</option>
									<c:forEach items="${bus}" var="bus">
										<option value=${bus.busiCode} >${bus.busiName}</option>
									</c:forEach>
							</select>
						</td>
						
						<td align="center">分录时间</td>
						<td><input id="startTime" type="text" class="easyui-datebox"
							name="mq.startTime"></input> 至<input id="endTime" type="text"
							class="easyui-datebox" name="mq.endTime" ></input>
						</td>	
					</tr>
					<tr>
						<td align="center">交易流水号</td>
						<td><input name="mq.txnseqno" maxlength="32" type="text" id="txnseqno" maxlength="32" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
						
						<td align="center">订单号</td>
						<td><input name="mq.payordno" type="text" id="payordno" maxlength="32"/>
						</td>
						
						<td align="center">记账状态</td>
						<td colspan="1">
							<select name=mq.type
								class="easyui-validatebox validatebox-text" id="type" style="width:130px">
									<option value="">请选择</option>
									<option value="00">已记账</option>
									<option value="01">未记账</option>
									<option value="02">待记账</option>
							</select>
						</td>
						
						<td align="center">记账方向</td>
						<td colspan="1">
							<select name=mq.crdr class="easyui-validatebox validatebox-text" id="crdr" style="width:130px">
									<option value="">请选择</option>
									<option value="00">借</option>
									<option value="01">贷</option>									
							</select>
						</td>
						
                        <td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a>
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
	        columns: [[
	        {
	            field: 'voucherCode',
	            title: '凭证号',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'memberid',
	            title: '会员号',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'acctCode',
	            title: '科目号',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'acctCodeName',
	            title: '账户名',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'usage',
	            title: '用途',
	            width: 150,
	            align: 'center',
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
						return "企业待结算";
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
	        {
	            field: 'busiCode',
	            title: '交易类型',
	            width: 150,
	            align: 'center',
				formatter:function(value,rec){
					if(value=="10000001"){
						return "消费";
					}else if(value=="10000002"){
						return "消费-账户";
					}else if(value=="11000001"){
						return "消费-产品";
					}else if(value=="20000001"){
						return "充值";
					}else if(value=="20000002"){
						return "保证金充值";
					}else if(value=="30000001"){
						return "提现";
					}else if(value=="40000001"){
						return "退款";
					}else if(value=="50000001"){
						return "转账";
					}else if(value=="50000002"){
						return "保证金提取";
					}else if(value=="70000001"){
						return "代付";
					}else if(value=="80000001"){
						return "实名认证";
					}else if(value=="90000001"){
						return "手工充值";
					}else{
						return "";  
					}
					}
	        },
	        {
	            field: 'crdr',
	            title: '记账方向',
	            width: 150,
	            align: 'center',
	            formatter: function(value, rec) {
	                if (value == "C") {
	                    return "借";
	                } else if (value == "D") {
	                    return "贷";
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
	            title: '交易金额(元)',
	            width: 150,
	            align: 'center'
	        },
	        {
	            field: 'status',
	            title: '记账状态',
	            width: 150,
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
	    compareTime();
	    var data = {
	    	'mq.voucherCode': $('#voucherCode').val(),
	    	'mq.acctCode': $("#acctCode").val(),
	        'mq.busiCode': $('#busiCode').val(),	        
	        'mq.endTime': $("#endTime").datebox('getValue'),
	        'mq.startTime': $("#startTime").datebox('getValue'),	        
	        'mq.txnseqno': $("#txnseqno").val(),
	        'mq.payordno': $("#payordno").val(),
	        'mq.type': $("#type").val(),
	        'mq.crdr': $("#crdr").val()   
	    };			    
	    $('#test').datagrid('load', data);
	}	
	
	
/* 	$("#endTime").blur(function(){
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		if(startTime > endTime){
			alert("分录开始时间不能大于等于结束时间");
		}
	}); */
 	function compareTime(){
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		if(startTime >= endTime){
			alert("分录开始时间不能大于等于结束时间");
		}
		
	} 
	</script>
</html>
