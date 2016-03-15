<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../../top.jsp"></jsp:include>
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
			style="height: 140px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>

						<td align="right" width="10%">批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="transQuery.batchno" id="batchno" maxlength="32" />
						</td>
							<td align="right" width="10%">划拨流水号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="transQuery.tranId" id="tranId" maxlength="32" />
						</td>

					
						<td align="right" width="10%">关联订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="transQuery.relatedorderno" id="relatedorderno" maxlength="32" /></td>

					</tr>

					<tr>
					
					<td align="right" width="10%">业务类型:</td>
						<td colspan="1"><select name="transQuery.busicode"
							class="easyui-validatebox validatebox-text" id="busicode">
								<option value="">请选择</option>
								<option value="30000001">提现</option>
								<option value="70000001">代付</option>
								<option value="80000001">代扣</option>

						</select></td>
						<td align="right" width="10%">账户类型:</td>
							<td colspan="1"><select name="transQuery.acctType"
							class="easyui-validatebox validatebox-text" id="acctType">
								<option value="">请选择</option>
								<option value="01">对公</option>
								<option value="00">对私</option>
						</select></td>
						<td align="right" width="10%">状态:</td>
							<td colspan="1"><select name="transQuery.status"
							class="easyui-validatebox validatebox-text" id="status">
								<option value="">请选择</option>
								<option value="01">初始</option>
								<option value="02">划拨中</option>
								<option value="00">划拨成功</option>
								<option value="03">划拨失败</option>
								<option value="11">待初审</option>
								<option value="21">待复审</option>
								<option value="19">初审未过</option>
								<option value="29">复审未过</option>
						</select></td>
						<td align="right" rowspan="6"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>

					</tr>

				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>

	</div>


<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<div>
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="solid">
<tr><td width="25%"   align="center" >划拨流水号</td><td align="center" whdth="25%" id="ttranid"></td> 
<td width="25%"   align="center" >账户类型</td><td align="center" whdth="25%" id="tacctype"></td> </tr>
<tr><td width="25%"   align="center" >账号</td><td align="center" whdth="25%" id="taccno"></td> 
<td width="25%"   align="center" >户名</td><td align="center" whdth="25%" id="taccname"></td> </tr>
<tr><td width="25%"   align="center" >支付行号</td><td align="center" whdth="25%" id="tbanktype"></td> 
<td width="25%"   align="center" >开户行名称</td><td align="center" whdth="25%" id="tbankname"></td> </tr>
<tr><td width="25%"   align="center" >划拨金额</td><td align="center" whdth="25%" id="ttransamt"></td> 
<td width="25%"   align="center" >摘要</td><td align="center" whdth="25%" id="tremark"></td> </tr>
<tr><td width="25%"   align="center" >划拨备注</td><td align="center" whdth="25%" id="tresv"></td> 
<td width="25%"   align="center" >状态</td><td align="center" whdth="25%" id="tstatus"></td> </tr>
<tr><td width="25%"   align="center" >银行流水号</td><td align="center" whdth="25%" id="tbanktranid"></td> 
<td width="25%"   align="center" >付款结果</td><td align="center" whdth="25%" id="tresptype"></td> </tr>
<tr><td width="25%"   align="center" >应答码</td><td align="center" whdth="25%" id="trespcode"></td> 
<td width="25%"   align="center" >应答信息</td><td align="center" whdth="25%" id="trespmsg"></td> </tr>
<tr><td width="25%"   align="center" >付款日期</td><td align="center" whdth="25%" id="ttrandate"></td> 
<td width="25%"   align="center" >付款时间</td><td align="center" whdth="25%" id="ttrantime"></td> </tr>
<tr><td width="25%"   align="center" >关联订单号</td><td align="center" whdth="25%" id="trelatedorderno"></td> 
<td width="25%"   align="center" >交易序列号</td><td align="center" whdth="25%" id="ttxnseqno"></td> </tr>
<tr><td width="25%"   align="center" >拆分标记</td><td align="center" whdth="25%" id="tsplitflag"></td> 
<td width="25%"   align="center" >账务处理状态</td><td align="center" whdth="25%" id="taccstatus"></td> </tr>
<tr><td width="25%"   align="center" >账务处理信息</td><td align="center" whdth="25%" id="taccinfo"></td> 
<td width="25%"   align="center" >划拨类型</td><td align="center" whdth="25%" id="ttransfertype"></td> </tr>
<tr><td width="25%"   align="center" >创建时间</td><td align="center" whdth="25%" id="tcreatetime"></td> 
<td width="25%"   align="center" >会员标示</td><td align="center" whdth="25%" id="tmemberid"></td> </tr>
<tr><td width="25%"   align="center" >业务名称</td><td align="center" whdth="25%" id="tbusicode"></td>
<td width="25%"   align="center" >业务类型</td><td align="center" whdth="25%" id="tbusitype"></td> 
 </tr>
<tr>
<td width="25%"   align="center" >初审人</td><td align="center" whdth="25%" id="tstexauser"></td>
<td width="25%"   align="center" >初审时间</td><td align="center" whdth="25%" id="tstexatime"></td> 
 </tr>
<tr>
<td width="25%"   align="center" >初审意见</td><td align="center" whdth="25%" id="tstexaopt"></td>
<td width="25%"   align="center" >复审人</td><td align="center" whdth="25%" id="tcvlexauser"></td> 
 </tr>
<tr>
<td width="25%"   align="center" >复审时间</td><td align="center" whdth="25%" id="tcvlexatime"></td>
<td width="25%"   align="center" >复审意见</td><td align="center" whdth="25%" id="tcvlexaopt"></td> 
 </tr>
<tr><td width="25%"   align="center" >划拨手续费</td><td align="center" whdth="25%" id="ttxnfee"></td> </tr>
		
					</table>
				</div>
				<br>
				

	
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:closeAdd()" id="btn_submit" onclick="">返回</a>
			
			</div>
		</div>
	</div>


	
</body>

<script>
	var width = $("#continer").width();
	$(function() {
		$("#withdraworcheckbox").unbind();
		$('#test')
				.datagrid(
						{
							title : '划拨查询',
							iconCls : 'icon-save',
							height : 400,
							singleSelect : true,
							nowrap : false,
							striped : true,
							url : 'pages/transfer/queryTrinsferTransferAction.action',
							remoteSort : false,
							idField : 'ORGAN_ID',
							columns : [ [
							
									{
										field : 'tranid',
										title : '划拨流水号',
										width : 120,
										align : 'center'
									},
									
									{
										field : 'acctype',
										title : '账户类型',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {

											if (value == '01') {
												return '对公账户';
											} else if (value == '00') {
												return '对私账户';
											} 
										} 
										
										
										
									},
									{
										field : 'accno',
										title : '账号',
										width : 120,
										align : 'center'
									},
									{
										field : 'accname',
										title : '户名',
										width : 120,
										align : 'center'
									},
									{
										field : 'banktype',
										title : '支付行号',
										width : 120,
										align : 'center',
									},
								
									{
										field : 'bankname',
										title : '开户行名称',
										width : 120,
										align : 'center'
									},
							
								
									{
										field : 'relatedorderno',
										title : '关联订单号',
										width : 120,
										align : 'center'
									},
									{
										field : 'money',
										title : '划拨金额(元)',
										width : 120,
										align : 'center'
									},
									{
										field : 'busitype',
										title : '业务名称',
										width : 120,
										align : 'center',
										
											formatter : function(value, rec) {
												if (value == '7000') {
													return '代付';
												} else if (value == '3000') {
													return '提现';
												}else  {
													return '';
												}
												}
									},
									{
										field : 'createtime',
										title : '创建时间',
										width : 120,
										align : 'center'
									},
									{
										field : 'status',
										title : '状态',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {
											if (value == '01') {
												return '初始';
											} else if (value == '02') {
												return '划拨中';
											} else if (value == '00') {
												return '划拨成功';
											} else if (value == '03') {
												return '划拨失败';
											} else if (value == '11') {
												return '待初审';
											} else if (value == '21') {
												return '待复审';
											} else if (value == '19') {
												return '初审未过';
											} else if (value == '29') {
												return '复审未过';
											} else {
												return '';
											}
										} 
									}
									,
									{
										field : 'status-',
										title : '操作',
										width : 120,
										align : 'center',
										formatter : function(value, rec) {
											if (rec.tranid !=null) {
												return '<a href="javascript:getTransfer(\''
												+ rec.tranid
												+ '\')" style="color:blue;margin-left:10px">详情</a>';
									} else {  
										return '';
									}
									}
									}
									
									
									
									] ],
							singleSelect : false,
							selectOnCheck : true,
							checkOnSelect : false,
							pagination : true,
							rownumbers : true		,
					
						});
	});

	function search() {
		     

		var data = {
			"transQuery.batchno" : $('#batchno').val(),
			"transQuery.tranId" : $('#tranId').val(),
			"transQuery.relatedorderno" : $('#relatedorderno').val(),
			"transQuery.busicode" : $('#busicode').val(),
				"transQuery.acctType" : $('#acctType').val(),
					"transQuery.status" : $('#status').val()
		   }
		$('#test').datagrid('load', data);
	}

	function showAdd() {
	
		$('#ws').window({
			title : '详情',
			top : 100,
			width : 800,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			modal : true,
			shadow : false,
			closed : false,
			height :600
		});
	} 
	function closeAdd() {

		$('#w').window('close');
	}
	
/* 	
	 function 	filel(){
		 $("#firstTrial").attr("action",
			"pages/instead/fileInsteadAction.action");
		 
		 $('#firstTrial').form('submit', {  
			    onSubmit: function(){  
				    if($('#firstTrial').form('validate')){
				    	$('#btn_submit').linkbutton('disable');		
				    	return 	true;
					}
			        return false;   
			    },   
			    success:function(data){  
			    	$.messager.alert('提示',data); 
	    			search();
		    		closeAdd();
			 
			        
			    }  
			});  
		 
	 } */
	/* function a(falg){
		if(falg==true){
			
			$("#firstTrial").attr("action",
					"pages/withdraw/queryTrialWithdraTriaAction.action");
			}else{
				$("#firstTrial").attr("action",
				"pages/withdraw/queryTrialWithdraTriaAction.action");
			}
		
	} 
	
	
	function firstTrial(falg){
	if(falg==true){
			$("#firstTrial").attr("action",
					"pages/withdraw/firstTrialTriaAction.action");
			$("#falg").val("true");
		}else{
				$("#firstTrial").attr("action",
				"pages/withdraw/firstTrialTriaAction.action");
				$("#falg").val("false");
	
		}
		$('#firstTrial').form('submit', {  
		    onSubmit: function(){  
			    if($('#firstTrial').form('validate')){
			    	$('#btn_submit').linkbutton('disable');		
			    	return 	true;
				}
		        return false;   
		    },   
		    success:function(data){  
		    	$.messager.alert('提示',data); 
    			search();
	    		closeAdd();
		 
		        
		    }  
		});   
	}
	*/
	
	
	function getTransfer(tranid){
		var ison=false;
		$.ajax( { 
				type: "POST",
	             url: "pages/transfer/queryTrinsferTransferAction.action",
	             data: {"transQuery.tranId":tranid},
	             dataType: "json",
	             async:false,
	             success: function(data){
	            var	json=data.rows[0];
	            if(json==null){
	            	$.messager.alert('提示',"数据不正确，请刷新后重试");	
	            }else{
	            	$("#ttranid").html(json.tranid);
	            	if(json.acctype=="00"){
	                	$("#tacctype").html("对私");
	            	}else if(json.acctype=="01"){
	            		$("#tacctype").html("对公");
	            	}else {
	            		$("#tacctype").html("");
	            	}
	        
	            	$("#taccno").html(json.accno);
	            	$("#taccname").html(json.accname);
	            	$("#tbanktype").html(json.banktype);
	            	$("#tbankname").html(json.bankname);
	            	$("#ttransamt").html(json.money+"元");
	            	$("#tremark").html(json.remark);
	            	$("#tresv").html(json.resv);
	            	
	            		if (json.status == '01') {
	            			$("#tstatus").html("初始");
						} else if (json.status == '02') {
							$("#tstatus").html("划拨中");
						} else if (json.status == '00') {
							$("#tstatus").html("划拨成功");
						} else if (json.status == '03') {
							$("#tstatus").html("划拨失败");
						} else if (json.status == '11') {
							$("#tstatus").html("待初审");
						} else if (json.status == '21') {
							$("#tstatus").html("待复审");
						} else if (json.status == '19') {
							$("#tstatus").html("初审未过");
						} else if (json.status == '29') {
							$("#tstatus").html("复审未过");
						} else {
							return '';
						}
	            	
	            	$("#tbanktranid").html(json.banktranid);
	            	$("#tresptype").html(json.resptype);
	            	$("#trespcode").html(json.respcode);
	            	$("#trespmsg").html(json.respmsg);
	            	$("#ttrandate").html(json.trandate);
	            	$("#ttrantime").html(json.trantime);
	            	$("#trelatedorderno").html(json.relatedorderno);
	            	if(json.busitype=="3000"){
	            		$("#tbusicode").html("提现");
	            	}else if(json.busitype=="7000"){
	            		$("#tbusicode").html("代付");
	            	}
	            	
	            	
	            	$("#tsplitflag").html(json.splitflag);
	            	$("#taccstatus").html(json.accstatus);
	            	$("#taccinfo").html(json.accinfo);
	            	$("#ttransfertype").html(json.transfertype);
	            	$("#tcreatetime").html(json.createtime);
	            	$("#tmemberid").html(json.memberid);
	            	$("#tbusitype").html(json.busitype);
	            	$("#tstexauser").html(json.stexauser);
	            	$("#tstexatime").html(json.stexatime);
	            	$("#tstexaopt").html(json.stexaopt);
	            	$("#tcvlexauser").html(json.cvlexauser);
	            	$("#tcvlexatime").html(json.cvlexatime);
	            	$("#tcvlexaopt").html(json.cvlexaopt);
	            	$("#ttxnseqno").html(json.txnseqno);
	            	$("#ttxnfee").html(json.fee);
	           isok=true;
	            }
	             }
		})
		if(isok==true){
		showAdd();
		}
		}
	
	function closeAdd() {
		$('#w').window('close');
		$('#ws').window('close');
	}
	
</script>
</html>
