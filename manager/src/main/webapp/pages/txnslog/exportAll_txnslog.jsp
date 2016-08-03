<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>
  <body>
  <style type="text/css">
  	table tr td{height:25px}
  	table tr td input{height:15px}
  	table tr td select{height:20px}
  </style>
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:140px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" action = "">
				<table width="100%">
					<tr>
					    <td align="right" width="10%">二级商户号</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.accsecmerno" id="accsecmerno" maxlength="32"/>
						</td>
						
					    <td align="right" width="10%">受理定单提交时间</td>
						<td  colspan="2"><input id="accordcommitimes" type="text" style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="tlb.accordcommitimes"></input>  
							至<input id="accordcommitimen" type="text"  style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="tlb.accordcommitimen"></input>
						</td>
						
						<td align="right" width="10%">交易类型</td>
						<td colspan="1">
								<select name="tlb.busicode" class="easyui-validatebox validatebox-text" id="busicode">
									  <option value="">--全部--</option>						
							          <option value="10000001">快捷消费</option>
							          <option value="20000001">充值</option>
							          <option value="30000001">提现</option>
							          <option value="70000001">代付</option>
							          <option value="40000001">退款</option>		
								</select>         
						       <!--    <c:forEach items="${bus}" var="bus">
								  		<c:if test="${bus.busiCode!='10000003'}">
						                      <option value=${bus.busiCode }>${bus.busiName}</option>
						                </c:if>
						          </c:forEach>
						          
						       -->  			          					        	
						</td>
				
					    <td align="right">
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>						
				   </tr>
					
				</table>
			</form>
		</div>
		
		
		<div style="margin-top: 5px">
			<form id="testForm" method = "post" action="">
				<table id="test"></table>
			</form>
			
		</div>
		
	
				
	</div>
  </body>
  
  <script>
  	var width = $("#continer").width();
		
		$(function(){
			$('#test').datagrid({
				title:'商户交易信息列表',
				iconCls:'icon-save',
				height:500,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/txnslog/queryAllSuccessTxnsLogAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'MEMBER_NAME',title:'商户名称',width:120,align:'center'},
					{field:'ACCSECMERNO',title:'商户编号',width:120,align:'center'},
					{field:'PAN',title:'银行卡号',width:120,align:'center'},
					{field:'ACCORDNO',title:'商户订单编号',width:150,align:'center'},
					{field:'TXNSEQNO',title:'交易流水号',width:120,align:'center'},
					{field:'ACCORDCOMMITIME',title:'交易时间',width:120,align:'center'},
					{field:'AMOUNT',title:'交易金额(元)',width:80,align:'center'},					
					{field:'TXNFEE',title:'手续费(元)',width:80,align:'center'},					
					{field:'PAYORDNO',title:'原交易流水号',width:120,align:'center'},
					{field:'PAYRETTSNSEQNO',title:'应答流水号',width:120,align:'center'},
					{field:'BUSINAME',title:'交易类型',width:80,align:'center'},
					{field:'CHNLNAME',title:'交易渠道',width:120,align:'center'},  						
				]],
				pagination:true,
				rownumbers:true	  		
		
			});
			
		})
	 function exports(){
			$("#theForm").submit();
	 }
		
	function search(){
		var busicode = $('#busicode').val();		
		var data={
				"tlb.accsecmerno":$('#accsecmerno').val(),
				"tlb.busicode":$('#busicode').val(),
				"tlb.accordcommitimes":$('#accordcommitimes').datebox('getValue'),
				"tlb.accordcommitimen":$('#accordcommitimen').datebox('getValue'), 
		}			
		if(busicode == ""){				
			$('#test').datagrid({
				title:'商户交易信息列表',
				iconCls:'icon-save',
				height:500,
				singleSelect:true,
				nowrap: false,
				striped: true,
				queryParams:data,
				url:'pages/txnslog/queryAllSuccessTxnsLogAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'MEMBER_NAME',title:'商户名称',width:120,align:'center'},
					{field:'ACCSECMERNO',title:'商户编号',width:120,align:'center'},
					{field:'PAN',title:'银行卡号',width:120,align:'center'},
					{field:'ACCORDNO',title:'商户订单编号',width:150,align:'center'},
					{field:'TXNSEQNO',title:'交易流水号',width:120,align:'center'},
					{field:'ACCORDCOMMITIME',title:'交易时间',width:120,align:'center'},
					{field:'AMOUNT',title:'交易金额(元)',width:80,align:'center'},
					{field:'TXNFEE',title:'手续费(元)',width:80,align:'center'},					
					{field:'PAYORDNO',title:'原交易流水号',width:120,align:'center'},
					{field:'PAYRETTSNSEQNO',title:'应答流水号',width:120,align:'center'},
					{field:'BUSINAME',title:'交易类型',width:80,align:'center'},
					{field:'CHNLNAME',title:'交易渠道',width:120,align:'center'},  						
				]],
				pagination:true,
				rownumbers:true,
  		  		toolbar: [{
  		  			id: 'btnadd',
  		  			text: '导出全部',
  		  			iconCls: 'icon-add',
  		  			handler: function() {
  		  				exportAllSuccess();
  		  			}
  		  		}]
		
			});
		}else if(busicode == 30000001){
			queryWithdrawals(busicode,data);
		}else if(busicode == 70000001){
			queryInsteadPay(busicode,data);
		}else{
			queryAllSuccess(busicode,data);
		}
		}
		
		function queryWithdrawals(busicode,data){			
			$('#test').datagrid({
				title:'商户交易信息列表',
				iconCls:'icon-save',
				height:500,
				singleSelect:true,
				nowrap: false,
				striped: true,
				queryParams:data,
				url:'pages/txnslog/queryWithdrawalsTxnsLogAction.action',
				remoteSort: false,					
				columns:[
				[
					{field:'MEMBER_NAME',title:'商户名称',width:120,align:'center'},
					{field:'GATEWAYORDERNO',title:'商户订单编号',width:120,align:'center'},
					{field:'TXNSEQNO',title:'交易流水号',width:120,align:'center'},
					{field:'WITHDRAWORDERNO',title:'提现订单号',width:150,align:'center'},
					{field:'TRAN_DATA_SEQ_NO',title:'划拨流水号',width:120,align:'center'},
					{field:'TRAN_BATCH_NO',title:'划拨批次号',width:120,align:'center'},
					{field:'BANK_TRAN_DATA_SEQ_NO',title:'转账流水号',width:80,align:'center'},
					{field:'BANK_TRAN_BATCH_NO',title:'转账批次号',width:80,align:'center'},	
					{field:'PAYORDCOMTIME',title:'交易时间',width:120,align:'center'},
					{field:'AMOUNT',title:'交易金额(元)',width:120,align:'center'},
					{field:'TXNFEE',title:'手续费(元)',width:120,align:'center'},  									
					{field:'CHNLNAME',title:'交易渠道',width:100,align:'center'},
			
				]],
				pagination:true,
				rownumbers:true,
  		  		toolbar: [{
  		  			id: 'btnadd',
  		  			text: '导出',
  		  			iconCls: 'icon-add',
  		  			handler: function() {
  		  				exportAllWithdrawals(busicode,data);
  		  			}
  		  		}]
		
			});
		}
		//代付
		function queryInsteadPay(busicode,data){
			$('#test').datagrid({
				title:'商户交易信息列表',
				iconCls:'icon-save',
				height:500,
				singleSelect:true,
				nowrap: false,
				striped: true,
				queryParams:data,
				url:'pages/txnslog/queryInsteadPayTxnsLogAction.action',
				remoteSort: false,					
				columns:[
				[
					{field:'MEMBER_NAME',title:'商户名称',width:120,align:'center'},
					{field:'ORDER_ID',title:'商户订单编号',width:120,align:'center'},
					{field:'TXNSEQNO',title:'交易流水号',width:120,align:'center'},
					{field:'INSTEAD_PAY_DATA_SEQ_NO',title:'代付流水号',width:150,align:'center'},
					{field:'INSTEAD_PAY_BATCH_SEQ_NO',title:'代付批次号',width:120,align:'center'},
					{field:'TRAN_DATA_SEQ_NO',title:'划拨流水号',width:120,align:'center'},
					{field:'TRAN_BATCH_NO',title:'划拨批次号',width:80,align:'center'},
					{field:'BANK_TRAN_DATA_SEQ_NO',title:'转账流水号',width:80,align:'center'},
					{field:'BANK_TRAN_BATCH_NO',title:'转账批次号',width:80,align:'center'},
					{field:'PAYORDCOMTIME',title:'交易时间',width:120,align:'center'},
					{field:'AMOUNT',title:'交易金额(元)',width:120,align:'center'},
					{field:'TXNFEE',title:'手续费(元)',width:120,align:'center'},  									
					{field:'CHNLNAME',title:'交易渠道',width:100,align:'center'},				
				]],
				pagination:true,
				rownumbers:true,
  		  		toolbar: [{
  		  			id: 'btnadd',
  		  			text: '导出',
  		  			iconCls: 'icon-add',
  		  			handler: function() {
  		  				exportAllInsteadPay(busicode,data);
  		  			}
  		  		}]
		
			});
		}
				
		function queryAllSuccess(busicode,data){
			var flag ="";
			if(busicode == 10000001){
				flag =1;					
			}else if(busicode == 20000001){
				flag =2;	
			}else if(busicode == 40000001){
				flag =4;
			}
			$('#test').datagrid({
				title:'商户交易信息列表',
				iconCls:'icon-save',
				height:500,
				singleSelect:true,
				nowrap: false,
				striped: true,
				queryParams:data,
				url:'pages/txnslog/queryAllSuccessTxnsLogAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'MEMBER_NAME',title:'商户名称',width:120,align:'center'},
					{field:'ACCSECMERNO',title:'商户编号',width:120,align:'center'},
					{field:'PAN',title:'银行卡号',width:120,align:'center'},
					{field:'ACCORDNO',title:'商户订单编号',width:150,align:'center'},
					{field:'TXNSEQNO',title:'交易流水号',width:120,align:'center'},
					{field:'ACCORDCOMMITIME',title:'交易时间',width:120,align:'center'},
					{field:'AMOUNT',title:'交易金额(元)',width:80,align:'center'},
					{field:'TXNFEE',title:'手续费(元)',width:80,align:'center'},
					{field:'BUSINAME',title:'交易类型',width:80,align:'center'},
					{field:'PAYORDNO',title:'支付订单号',width:120,align:'center'},
					{field:'PAYRETTSNSEQNO',title:'应答流水号',width:120,align:'center'},
					{field:'CHNLNAME',title:'交易渠道',width:120,align:'center'},  						
				]],
				pagination:true,
				rownumbers:true,
  		  		toolbar: [{
  		  			id: 'btnadd',
  		  			text: '导出',
  		  			iconCls: 'icon-add',
  		  			handler: function() {
  		  				exportAllCrr(busicode,flag);
  		  			}
  		  		}]
		
			});
		}	
		
		function exportAllSuccess(){
			$('#theForm').attr("action","pages/txnslog/exportAllSuccessTxnsLogAction.action");
			$('#theForm').submit();
		}
		function exportAllWithdrawals(busicode,data){
			$('#theForm').attr("action","pages/txnslog/exportAllWithdrawalsTxnsLogAction.action");
			$('#theForm').submit();
		}
		function exportAllInsteadPay(){
			$('#theForm').attr("action","pages/txnslog/exportAllInsteadPayTxnsLogAction.action");
			$('#theForm').submit();
		}
		function exportAllCrr(busicode,flag){
			$('#theForm').attr("action",'pages/txnslog/exportAllCrrTxnsLogAction.action?flag='+ flag +'&busicode='+busicode);
			$('#theForm').submit();
		}

		
	</script>
</html>
