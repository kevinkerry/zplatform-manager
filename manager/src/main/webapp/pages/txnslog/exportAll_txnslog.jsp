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
			<form id="theForm"   method="post" action = "pages/txnslog/exportAllSuccessTxnsLogAction.action">
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
		<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;text-align: center">
		<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="1">
<tr><td >交易序列号[证联金融所用：关联各子流水表]</td><td id="ttxnseqno"></td> 
<td >交易日期</td><td id="ttxndate"></td> </tr>
<tr><td >交易时间</td><td id="ttxntime"></td> 
<td >应用类型</td><td id="tapptype"></td> </tr>
<tr><td >业务类型</td><td id="tbusitype"></td> 
<td >业务代码</td><td id="tbusicode"></td> </tr>
<tr><td >交易金额[合计]</td><td id="tamount"></td> 
<td >交易佣金</td><td id="ttradcomm"></td> </tr>
<tr><td >交易手续费</td><td id="ttxnfee"></td> 
<td >分控版本[商户角色]</td><td id="triskver"></td> </tr>
<tr><td >分润版本[商户角色]</td><td id="tsplitver"></td> 
<td >扣率版本[商户角色]</td><td id="tfeever"></td> </tr>
<tr><td >产品版本[商户角色]</td><td id="tprdtver"></td> 
<td >收银台版本[商户角色]</td><td id="tcheckstandver"></td> </tr>
<tr><td >路由版本[客户角色]</td><td id="troutver"></td> 
<td >转出帐号或卡号</td><td id="tpan"></td> </tr>
<tr><td >转出帐号类型</td><td id="tcardtype"></td> 
<td >转出帐号或卡号所属机构</td><td id="tcardinstino"></td> </tr>
<tr><td >转入帐号或卡号</td><td id="tinpan"></td> 
<td >转入帐号或卡号类型</td><td id="tincardtype"></td> </tr>
<tr><td >转入帐号或卡号机构代码</td><td id="tincardinstino"></td> 
<td >受理订单号</td><td id="taccordno"></td> </tr>
<tr><td >受理订单所属机构</td><td id="taccordinst"></td> 
<td >受理二级商户号</td><td id="taccsecmerno"></td> </tr>
<tr><td >受理一级商户号</td><td id="taccfirmerno"></td> 
<td >受理清算日期</td><td id="taccsettledate"></td> </tr>
<tr><td >受理定提交时间</td><td id="taccordcommitime"></td> 
<td >受理定单完成时间</td><td id="taccordfintime"></td> </tr>
<tr><td >支付类型（01：快捷，02：网银，03：账户）</td><td id="tpaytype"></td> 
<td >支付定单号</td><td id="tpayordno"></td> </tr>
<tr><td >支付所属机构</td><td id="tpayinst"></td> 
<td >支付一级商户号</td><td id="tpayfirmerno"></td> </tr>
<tr><td >支付二级商户号</td><td id="tpaysecmerno"></td> 
<td >支付定单提交时间</td><td id="tpayordcomtime"></td> </tr>
<tr><td >支付定单完成时间</td><td id="tpayordfintime"></td> 
<td >支付方交易流水号</td><td id="tpayrettsnseqno"></td> </tr>
<tr><td >支付方应答码</td><td id="tpayretcode"></td> 
<td >支付方应答信息</td><td id="tpayretinfo"></td> </tr>
<tr><td >应用定单号</td><td id="tappordno"></td> 
<td >应用所属机构</td><td id="tappinst"></td> </tr>
<tr><td >应用定单提交时间</td><td id="tappordcommitime"></td> 
<td >应用定单完成时间</td><td id="tappordfintime"></td> </tr>
<tr><td >交易查询流水[证联金融返给客户端流水vs交易序列号]</td><td id="ttradeseltxn"></td> 
<td >中心应答码</td><td id="tretcode"></td> </tr>
<tr><td >中心应答信息</td><td id="tretinfo"></td> 
<td >交易状态标志位</td><td id="ttradestatflag"></td> </tr>
<tr><td >交易所涉流水表标志位</td><td id="ttradetxnflag"></td> 
<td >路由层次[当前交易号]</td><td id="ttxncode"></td> </tr>
<tr><td >收银代码</td><td id="tcashcode"></td> 
<td >涉及流水表标志</td><td id="trelate"></td> </tr>
<tr><td >中心应答时间</td><td id="tretdatetime"></td> 
<td >原交易序列号</td><td id="ttxnseqno_og"></td> </tr>
<tr><td >备注</td><td id="tnotes"></td> 
<td >备注</td><td id="tremarks"></td> </tr>
<tr><td >受理会员号</td><td id="taccmemberid"></td> 
<td >应用定单状态</td><td id="tapporderstatus"></td> </tr>
<tr><td >应用订单应答信息</td><td id="tapporderinfo"></td> </tr>


			
			</table>
				</div>
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
		
		var data={"tlb.accsecmerno":$('#accsecmerno').val(),
				"tlb.busicode":$('#busicode').val(),
				"tlb.accordcommitimes":$('#accordcommitimes').datebox('getValue'),
				"tlb.accordcommitimen":$('#accordcommitimen').datebox('getValue'), 
		}			
		var busicode = $('#busicode').val();
		if(busicode == ""){	
			$('#test').datagrid({
				title:'商户交易信息列表',
				iconCls:'icon-save',
				height:500,
				singleSelect:true,
				nowrap: false,
				striped: true,
				queryParams:"",
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
		//
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
			if($('#busicode').val() == 10000001){
				flag =1;					
			}else if($('#busicode').val() == 20000001){
				flag =2;	
			}else if($('#busicode').val() == 40000001){
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
  		  				exportAllCrr(busicode,data,flag);
  		  			}
  		  		}]
		
			});
		}	
		
		function exportAllSuccess(){
			$('#testForm').attr("action","pages/txnslog/exportAllSuccessTxnsLogAction.action");
			$('#testForm').submit();
		}
		function exportAllWithdrawals(busicode,data){
			$('#testForm').attr("action","pages/txnslog/exportAllWithdrawalsTxnsLogAction.action");
			$('#testForm').submit();
		}
		function exportAllInsteadPay(){
			$('#testForm').attr("action","pages/txnslog/exportAllInsteadPayTxnsLogAction.action");
			$('#testForm').submit();
		}
		function exportAllCrr(busicode,data,flag){
			$('#testForm').attr("action","pages/txnslog/exportAllCrrTxnsLogAction.action?flag="+flag);
			$('#testForm').submit();
		}

		
	</script>
</html>
