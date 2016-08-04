<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <body>
  <style type="text/css">
  	table tr td{height:25px}
  	table tr td input{height:15px}
  	table tr td select{height:20px}
  </style>
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:140px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" action = "pages/txnslog/exportAllTxnsLogAction.action">
				<table width="100%">
					<tr>
					    <td align="right" width="10%">交易流水号</td>
						<td align="left" style="padding-left:5px" width="15%">
						    <input name="txnsLogModel.txnseqno" id="txnseqno" maxlength="32"/>					    
						</td>
						
						<td align="right" width="10%">银行卡号</td>
						<td align="left" style="padding-left:5px" width="15%">
						    <input name="txnsLogModel.pan" id="pan" maxlength="32"/>
						</td> 
						
						<td align="right" width="10%">商户订单号</td>
						<td align="left" style="padding-left:5px" width="15%">
						    <input name="txnsLogModel.accordno" id="accordno" maxlength="32"/>
						</td>
						
						<td align="right" width="10%">会员编号</td>
						<td align="left" style="padding-left:5px" width="15%">
						    <input name="txnsLogModel.accmemberid" id="accmemberid" maxlength="32"/>
						</td>
				   </tr>
						
				   <tr>
				        <td align="right" width="10%">受理二级商户号</td>
						<td align="left" style="padding-left:5px" width="15%">
						    <input name="txnsLogModel.accsecmerno" id="accsecmerno" maxlength="32"/>
						</td>
						
						<td align="right" width="10%">应答流水号</td>
						<td align="left" style="padding-left:5px" width="15%">
						    <input name="txnsLogModel.payrettsnseqno" id="payrettsnseqno" maxlength="32"/>
						</td>
						
						<td align="right" width="10%">中心应答码</td>
						<td colspan="1">
								<select name="txnsLogModel.retcode"  id="retcode">
								  <option value="">请选择</option>
						          <option value="00">成功</option>
						          <option value="01">失败</option>
					        	</select>
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
      $(function(){
    		  $('#test').datagrid({
  				title:'交易流水信息列表',
  				iconCls:'icon-save',
  				height:450,
  				singleSelect:true,
  				nowrap: false,
  				striped: true,
  				url:'pages/txnslog/getPersonTxnsLogsTxnsLogAction.action',
  				remoteSort: false,
  				idField:'ORGAN_ID',
  				columns:[
  				[
  					{field:'MEMBER_NAME',title:'商户名称',width:120,align:'center'},
  					{field:'ACCSECMERNO',title:'商户编号',width:120,align:'center'},
  					
  					{field:'PAN',title:'银行卡号',width:130,align:'center'}, 				
  					{field:'ACCORDNO',title:'商户订单号',width:130,align:'center'},
  					{field:'TXNSEQNO',title:'交易流水号',width:120,align:'center'},
  					{field:'ACCORDCOMMITIME',title:'交易时间',width:120,align:'center'},
  					{field:'AMOUNT',title:'交易金额(元)',width:120,align:'center'},
  					
  					{field:'TXNFEE',title:'手续费(元)',width:120,align:'center'},
  					{field:'TXNSEQNO_OG',title:'原交易流水号',width:120,align:'center'},
  					{field:'PAYRETTSNSEQNO',title:'应答流水号',width:120,align:'center'},
  					{field:'BUSINAME',title:'交易类型',width:120,align:'center'},
  					{field:'CHNLNAME',title:'交易渠道',width:120,align:'center'}					
  				]],
  				pagination:true,
  				rownumbers:true,
  		  		toolbar: [{
  		  			id: 'btnadd',
  		  			text: '导出',
  		  			iconCls: 'icon-add',
  		  			handler: function() {
  		  				exports();
  		  			}
  		  		}]
  			});  
    	  });
      
		function search(){
			var data={
			    "txnsLogModel.txnseqno":$('#txnseqno').val(),//交易流水号
			    "txnsLogModel.pan":$('#pan').val(),//银行卡号
			    "txnsLogModel.accordno":$('#accordno').val(),//商户订单号
			    "txnsLogModel.accmemberid":$('#accmemberid').val(),//（个人）会员号	
			    "txnsLogModel.accsecmerno":$('#accsecmerno').val(),//受理二级商户号
			    "txnsLogModel.payrettsnseqno":$('#payrettsnseqno').val(),//应答流水号
			    "txnsLogModel.retcode":$('#retcode').val()//中心应答码			    
		    }		
			$('#test').datagrid('load',data);
		}
		function exports(){
			$("#theForm").submit();
		}
	

  </script>
</html>
