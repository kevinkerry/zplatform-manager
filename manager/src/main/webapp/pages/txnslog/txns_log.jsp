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
			<form id="theForm"   method="post" >
				<table width="100%">
					<tr>
						<td align="right" width="10%">交易流水号</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.txnseqno" id="txnseqno" maxlength="32"/>
						</td>
					
						<td align="right" width="10%">交易类型</td>
						<td colspan="1">
								<select name="tlb.busicode" class="easyui-validatebox validatebox-text" id="busicode">
								  <option value="">请选择</option>
						
								  <c:forEach items="${bus}" var="bus">
								  		<c:if test="${bus.busiCode!='10000003'}">
						          <option value=${bus.busiCode }>${bus.busiName}</option>
						          </c:if>
						          </c:forEach> 
					        	</select>
						</td>
						
						<td align="right" width="10%">转出帐号或卡号</td> 
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.pan" id="pan" maxlength="32"/>
						</td>
						
							<td align="right" width="10%">商户订单号</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.accordno" id="accordno" maxlength="32"/>
						</td>
						
						
					</tr>
					<tr>
					<td align="right" width="10%">一级商户号</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.accfirmerno" id="accfirmerno" maxlength="32"/>
						</td>
					<td align="right" width="10%">二级商户号</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.accsecmerno" id="accsecmerno" maxlength="32"/>
						</td>
					<td align="right" width="10%">支付流水号</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.payrettsnseqno" id="payrettsnseqno" maxlength="32"/>
						</td>
						
						
					<td align="right" width="10%">中心应答码</td>
					<td colspan="1">
								<select name="tlb.retcode"  id="retcode">
								  <option value="">请选择</option>
						          <option value="00">成功</option>
						          <option value="01">失败</option>
					        	</select>
					</td>
					</tr>
						<tr>
							<td align="right" width="10%">受理清算日期</td>
							<td align="left" style="padding-left:5px" width="15%">
							<input id="accsettledate" type="text" class="easyui-datebox" name="tlb.accsettledate"></input>  
								
							</td>
						

							<td align="right" width="10%">支付类型</td>
								<td colspan="1">
								<select name="tlb.payType"  id="paytype">
								  <option value="">请选择</option>
						          <option value="01">快捷</option>
						          <option value="02">网银</option>
						          <option value="03">账户</option>
						          <option value="04">代付</option>
					        	</select>							
							</td>
							
							<td align="right" width="10%">支付订单号</td>
							<td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.payordno" id="payordno" maxlength="32"/>
						    </td>
						    
						     <td align="right" width="10%">交易渠道
						    <td colspan="1">
								<select name="tlb.payinst"  id="payinst">
								  <option value="">请选择</option>					
								  <c:forEach items="${channel}" var="channel">								  		
						              <option value=${channel.chnlcode }>${channel.chnlname}</option>						         
						          </c:forEach> 			          						           
					        	</select>
					        </td>
					    <tr>
						   
					        <td align="right" width="10%">会员号</td>
					        <td align="left" style="padding-left:5px" width="15%">
							<input name="tlb.accmemberid" id="accmemberid" maxlength="32"/>
						    </td>
						    
						    <td align="right" width="10%">受理定单提交时间</td>
							<td  colspan="2"><input id="accordcommitimes" type="text" style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="tlb.accordcommitimes"></input>  
								至<input id="accordcommitimen" type="text"  style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="tlb.accordcommitimen"></input></td>
						
						    <td align="right">
								<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						    
													
					   </tr>
					
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
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
<tr><td >交易渠道</td><td id="tpayinst"></td> 
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
				title:'会员账户信息列表',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/txnslog/getTxnsLoByTxnsBeanTxnsLogAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'txnseqno',title:'交易流水号',width:120,align:'center'},
					{field:'txndate',title:'交易日期',width:120,align:'center'},
					{field:'txntime',title:'交易时间',width:120,align:'center'},
					/* {field:'busicode',title:'业务类型',width:120,align:'center'}, */
					{field:'amount',title:'交易金额(元)',width:120,align:'center'},
					{field:'tradcomm',title:'交易佣金(元)',width:120,align:'center'},
					{field:'txnfee',title:'交易手续费(元)',width:120,align:'center'},
					{field:'paytype',title:'支付类型',width:120,align:'center',
						formatter:function(value,rec){
							if(value=="01"){
								return "快捷支付";
							}else if(value=="02"){
								return "网银支付";
							}else if(value=="03"){
								return "账户支付";
							}else if(value=="04"){
								return "代付";
							}else{
								return "未知";
							}
							}
					},  
					
					
					{field:'retinfo',title:'中心应答信息',width:120,align:'center'},
					{field:'pan',title:'转出帐号或卡号',width:120,align:'center'},
					{field:'inpan',title:'转入帐号或卡号',width:120,align:'center'},			
					{field:'accfirmerno',title:'一级商户号',width:120,align:'center'},
					{field:'accsecmerno',title:'二级商户号',width:120,align:'center'},
					{field:'accordfintime',title:'受理定单完成时间',width:120,align:'center'},
					{field:'txnseqno-',title:'操作',width:100,align:'center',
					formatter:function(value,rec){
						if(rec.txnseqno!=null){
							return '<a href="javascript:queryTxnsLog(\''+rec.txnseqno+'\')" style="color:blue;margin-left:10px">详细信息</a>';
						}else {
							return '';
						}
						
					}
				    } 
					
				]],
				pagination:true,
				rownumbers:true
		
			});
	
		});
		
		function search(){
			var data={"tlb.txnseqno":$('#txnseqno').val(),
			"tlb.busicode":$('#busicode').val(),
			"tlb.pan":$('#pan').val(),
			"tlb.accordno":$('#accordno').val(),
			"tlb.accsecmerno":$('#accsecmerno').val(),
			"tlb.accfirmerno":$('#accfirmerno').val(),
 			"tlb.accsettledate":$('#accsettledate').datebox('getValue'),
			"tlb.accordcommitimes":$('#accordcommitimes').datebox('getValue'),
			"tlb.accordcommitimen":$('#accordcommitimen').datebox('getValue'), 
			"tlb.payType":$('#paytype').val(),
			"tlb.payrettsnseqno":$('#payrettsnseqno').val(),
			"tlb.retcode":$('#retcode').val(),
			"tlb.payordno":$('#payordno').val(),
			"tlb.payinst":$('#payinst').val(),
			"tlb.accmemberid":$('#accmemberid').val()
		}
			$('#test').datagrid('load',data);
		}
		
		function queryTxnsLog(txnseqno){
		 $("#ttxnseqno").html("");
			   $("#ttxndate").html("");
			   $("#ttxntime").html("");
			   $("#tapptype").html("");
			   $("#tbusitype").html("");
			   $("#tbusicode").html("");
			   $("#tamount").html("");
			   $("#ttradcomm").html("");
			   $("#ttxnfee").html("");
			   $("#triskver").html("");
			   $("#tsplitver").html("");
			   $("#tfeever").html("");
			   $("#tprdtver").html("");
			   $("#tcheckstandver").html("");
			   $("#troutver").html("");
			   $("#tpan").html("");
			   $("#tcardtype").html("");
			   $("#tcardinstino").html("");
			   $("#tinpan").html("");
			   $("#tincardtype").html("");
			   $("#tincardinstino").html("");
			   $("#taccordno").html("");
			   $("#taccordinst").html("");
			   $("#taccsecmerno").html("");
			   $("#taccfirmerno").html("");
			   $("#taccsettledate").html("");
			   $("#taccordcommitime").html("");
			   $("#taccordfintime").html("");
			   $("#tpaytype").html("");
			   $("#tpayordno").html("");
			   $("#tpayinst").html("");
			   $("#tpayfirmerno").html("");
			   $("#tpaysecmerno").html("");
			   $("#tpayordcomtime").html("");
			   $("#tpayordfintime").html("");
			   $("#tpayrettsnseqno").html("");
			   $("#tpayretcode").html("");
			   $("#tpayretinfo").html("");
			   $("#tappordno").html("");
			   $("#tappinst").html("");
			   $("#tappordcommitime").html("");
			   $("#tappordfintime").html("");
			   $("#ttradeseltxn").html("");
			   $("#tretcode").html("");
			   $("#tretinfo").html("");
			   $("#ttradestatflag").html("");
			   $("#ttradetxnflag").html("");
			   $("#ttxncode").html("");
			   $("#tcashcode").html("");
			   $("#trelate").html("");
			   $("#tretdatetime").html("");
			   $("#ttxnseqno_og").html("");
			   $("#tnotes").html("");
			   $("#tremarks").html("");
			   $("#taccmemberid").html("");
			   $("#tapporderstatus").html("");
			   $("#tapporderinfo").html("");
			   
			$('#w').window({
				title: '详细信息',
				top:90,
				left:100,
				width:900,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 800
			});
		
	
			$.ajax({
				   type: "POST",
				   url: "pages/txnslog/getTxnsLogByTxnseqnoTxnsLogAction.action",
				   data: "tlb.txnseqno="+txnseqno,
				   dataType:"json",
				   success:function(date){
					   
					   if(date.messg!=null){
						   
					   }else{
						   json=date.json;		   
					   $("#ttxnseqno").html(json.TXNSEQNO);
					   $("#ttxndate").html(json.TXNDATE);
					   $("#ttxntime").html(json.TXNTIME);
					   $("#tapptype").html(json.APPTYPE);
					   $("#tbusitype").html(json.BUSITYPE);
					   $("#tbusicode").html(json.BUSINAME);
					   $("#tamount").html(fen2Yuan(json.AMOUNT));
					   $("#ttradcomm").html(fen2Yuan(json.TRADCOMM));
					   $("#ttxnfee").html(fen2Yuan(json.TXNFEE));
					   $("#triskver").html(json.RISKNAME);
					   $("#tsplitver").html(json.SPLITVER);
					   $("#tfeever").html(json.FEENAME);
					   $("#tprdtver").html(json.PRDTNAME);
					   $("#tcheckstandver").html(json.CASHNAME);
					   $("#troutver").html(json.ROUTNAME);
					   $("#tpan").html(json.PAN);
					   $("#tcardtype").html(json.CARDTYPE);
					   $("#tcardinstino").html(json.CARDINSTINO);
					   $("#tinpan").html(json.INPAN);
					   $("#tincardtype").html(json.INCARDTYPE);
					   $("#tincardinstino").html(json.INCARDINSTINO);
					   $("#taccordno").html(json.ACCORDNO);
					   $("#taccordinst").html(json.ACCORDINST);
					   $("#taccsecmerno").html(json.ACCSECMERNO);
					   $("#taccfirmerno").html(json.ACCFIRMERNO);
					   $("#taccsettledate").html(json.ACCSETTLEDATE);
					   $("#taccordcommitime").html(json.ACCORDCOMMITIME);
					   $("#taccordfintime").html(json.ACCORDFINTIME);
					   $("#tpaytype").html(json.PAYTYPE);
					   $("#tpayordno").html(json.PAYORDNO);
                       $("#tpayinst").html(json.CHNLNAME);
					   $("#tpayfirmerno").html(json.PAYFIRMERNO);
					   $("#tpaysecmerno").html(json.PAYSECMERNO);
					   $("#tpayordcomtime").html(json.PAYORDCOMTIME);
					   $("#tpayordfintime").html(json.PAYORDFINTIME);
					   $("#tpayrettsnseqno").html(json.PAYRETTSNSEQNO);
					   $("#tpayretcode").html(json.PAYRETCODE);
					   $("#tpayretinfo").html(json.PAYRETINFO);
					   $("#tappordno").html(json.APPORDNO);
					   $("#tappinst").html(json.APPINST);
					   $("#tappordcommitime").html(json.APPORDCOMMITIME);
					   $("#tappordfintime").html(json.APPORDFINTIME);
					   $("#ttradeseltxn").html(json.TRADESELTXN);
					   $("#tretcode").html(json.RETCODE);
					   $("#tretinfo").html(json.RETINFO);
					   $("#ttradestatflag").html(json.TRADESTATFLAG);
					   $("#ttradetxnflag").html(json.TRADETXNFLAG);
					   $("#ttxncode").html(json.TXNCODE);
					   $("#tcashcode").html(json.CASHCODE);
					   $("#trelate").html(json.RELATE);
					   $("#tretdatetime").html(json.RETDATETIME);
					   $("#ttxnseqno_og").html(json.TXNSEQNO_OG);
					   $("#tnotes").html(json.NOTES);
					   $("#tremarks").html(json.REMARKS);
					   $("#taccmemberid").html(json.ACCMEMBERID);
					   $("#tapporderstatus").html(json.APPORDERSTATUS);
					   $("#tapporderinfo").html(json.APPORDERINFO);
					   }
				 	}
				});
		}
	</script>
</html>
