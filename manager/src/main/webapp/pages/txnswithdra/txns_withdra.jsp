<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../top.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="css/passwordkeyboard.css"/>
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
						<td align="right" width="10%">提现订单号:</td>
						<td align="left" style="padding-left:5px" width="15%">
							<input name="twq.withdraworderno" id="withdrawordernos" maxlength="32"/>
						</td>
					
						<td align="right" width="10%">提现类型:</td>
						<td colspan="1">
								<select name="twq.withdrawtype" class="easyui-validatebox validatebox-text" id="withdrawtypes">
								  <option value="">请选择</option>
						          <option value="0">个人</option>
						          <option value="1">商户</option>
						      
					        	</select>
							</td>
						
						<td align="right" width="10%">会员号:</td> 
						<td align="left" style="padding-left:5px" width="15%">
							<input name="twq.memberid" id="memberids" maxlength="32"/>
						</td>
						
						
					</tr>
					
						<tr>
						<td align="right" rowspan="6">
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

	<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<div>
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"  border="solid">
<tr><td width="40%"   align="center" >提现订单号</td><td align="center" whdth="40px" id="twithdraworderno"></td> 
<td width="40%"   align="center" >批次号</td><td align="center" whdth="40px" id="tbatchno"></td> </tr>
<tr><td width="40%"   align="center" >会员号</td><td align="center" whdth="40px" id="tmemberid"></td> 
<td width="40%"   align="center" >提现类型</td><td align="center" whdth="40px" id="twithdrawtype"></td> </tr>
<tr><td width="40%"   align="center" >提现金额</td><td align="center" whdth="40px" id="tamount"></td> 
<td width="40%"   align="center" >银行账号</td><td align="center" whdth="40px" id="tacctno"></td> </tr>
<tr><td width="40%"   align="center" >银行账户名称</td><td align="center" whdth="40px" id="tacctname"></td> 
<td width="40%"   align="center" >银行代码</td><td align="center" whdth="40px" id="tbankcode"></td> </tr>
<tr><td width="40%"   align="center" >支行名称</td><td align="center" whdth="40px" id="tbankname"></td> 
<td width="40%"   align="center" >提现手续费</td><td align="center" whdth="40px" id="tfee"></td> </tr>
<tr><td width="40%"   align="center" >交易时间</td><td align="center" whdth="40px" id="ttxntime"></td> 
<td width="40%"   align="center" >完成时间</td><td align="center" whdth="40px" id="tfinishtime"></td> </tr>
<tr><td width="40%"   align="center" >状态</td><td align="center" whdth="40px" id="tstatus"></td> 
<td width="40%"   align="center" >提现渠道</td><td align="center" whdth="40px" id="twithdrawinstid"></td> </tr>
<tr><td width="40%"   align="center" >应答码</td><td align="center" whdth="40px" id="tretcode"></td> 
<td width="40%"   align="center" >应答信息</td><td align="center" whdth="40px" id="tretinfo"></td> </tr>
<tr><td width="40%"   align="center" >写入人</td><td align="center" whdth="40px" id="tinuser"></td> 
<td width="40%"   align="center" >写入时间</td><td align="center" whdth="40px" id="tintime"></td> </tr>
<tr><td width="40%"   align="center" >初审人</td><td align="center" whdth="40px" id="tstexauser"></td> 
<td width="40%"   align="center" >初审时间</td><td align="center" whdth="40px" id="tstexatime"></td> </tr>
<tr><td width="40%"   align="center" >初审意见</td><td align="center" whdth="40px" id="tstexaopt"></td> 
<td width="40%"   align="center" >复审人</td><td align="center" whdth="40px" id="tcvlexauser"></td> </tr>
<tr><td width="40%"   align="center" >复审时间</td><td align="center" whdth="40px" id="tcvlexatime"></td> 
<td width="40%"   align="center" >复审意见</td><td align="center" whdth="40px" id="tcvlexaopt"></td> </tr>
<tr><td width="40%"   align="center" >网关订单号</td><td align="center" whdth="40px" id="tgatewayorderno"></td> 
<td width="40%"   align="center" >银行主行行号</td><td align="center" whdth="40px" id="ttotalbankcode"></td> </tr>
<tr><td width="40%"   align="center" >交易序列号</td><td align="center" whdth="40px" id="ttxnseqno"></td> </tr>

		
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
	
	
	
	<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:800px;height:200px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;text-align: center">
			<div id="member" >
			<form action="" method="post"  id="organForm">
			<table width="100%" cellpadding="2" cellspacing="2" style="text-align: left" id="inputForm">
			<tr align="center"><td align="right" width="45%">

			会员号:
			</td>
			<td align="left">
				<input id="memberid"   name="qa.memberId" required="true"   type="text" class="easyui-validatebox" maxlength="20"  />
				</td>
				</tr>
				<tr align="center">
				<td align="right" width="45%">
			账户:
			</td>
			<td align="left">
			<select name="qa.usage" class="easyui-validatebox validatebox-text" id="usage">
						          <option value="101">资金账户</option>
						      
					        	</select>
				</td>
				</tr>
			</table>
			</form>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:saveTxnsW()" id="btn_submit" onclick="">下一步</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
			</div >
			<div id="merch1" style="display:none">
				<table width="100%" cellpadding="2" cellspacing="2" style="text-align: left" id="inputForm">
			<tr align="center"><td align="right" width="45%">
			<span id="bankname"></span>:
			</td>
			<td align="left">
				<span id="acctno"></span>
				</td>
				</tr>
				<tr align="center"><td align="right" width="45%">

			提现金额:
			</td>
			<td align="left">
				<input id="amount" placeholder="请输入提现金额"  name="qa.amount" required="true"   type="text"   maxlength="20"  onkeyup="money()" />元
				&nbsp;&nbsp;&nbsp;&nbsp;人民币:<span id="moneys"></span>
				</td>
				</tr>
				<tr align="center"><td align="right" width="45%">
				卡内余额:
				</td>
				<td align="left"><span id="Balance"></span>:元</td>
				</tr>
				<tr align="center"><td align="right" width="45%">
				提现手续费:
				</td>
				<td align="left"><span id="txnsfee"></span>元</td>
				</tr>
				
			</table>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:password('merch1','merch2')" id="btn_submits" onclick="">下一步</a>
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="last('merch1','member')">上一步</a>
			</div>
			
			</div>
			<div id="merch2" style="display:none">
			<div style="text-align: center;padding-top: 10px;p">
<span id="set_text" style="font-size: large;">
<span>请输入</span>
<span style="color: red;">六位数字支付密码</span>
<span>，验证本次操作</span>
</span>
</div>
<div style="padding-top: 20px;text-align: center;">
        <form id="password" method="post" >
     <input  class="pass"  type="password"maxlength="1"value=""><input readonly class="pass" type="password"maxlength="1"value=""><input readonly class="pass" type="password"maxlength="1"value=""><input readonly class="pass" type="password"maxlength="1"value=""><input readonly class="pass" type="password"maxlength="1"value=""><input readonly class="pass pass_right" type="password"maxlength="1"value="">
     <input   type="hidden"  name="twb.memberid"  id="twbmemberid">  
      <input  type="hidden"  name="twb.withdrawtype" id="twbwithdrawtype">  
       <input  type="hidden" name="twb.amount" id="twbamount" >  
         <input  type="hidden"  name="twb.acctno" id="twbacctno">  
          <input  type="hidden"  name="twb.acctname" id="twbacctname">  
           <input  type="hidden"  name="twb.bankcode" id="twbbankcode">  
             <input  type="hidden"  name="twb.bankname" id="twbbankname"> 
            <input  type="hidden"  name="twb.passWord" id="twbpassword">  
             <input  type="hidden"  name="twb.merchId" id="twbmerchId">  
              <input  type="hidden"  name="twb.subMerchId" id="twbsubMerchId">  
               <input  type="hidden"  name="twb.cardType" id="twbcardType">  
                 <input  type="hidden"  name="twb.fee" id="fee">  
                 <input  type="hidden"  name="twb.totalBankCode" id="totalBankCode">  
   				 <input  type="hidden"  name="qa.memberId" id="twbmemberId"> 
  			  <input  type="hidden"  name="qa.usage" id="twbusage"> 
  			<input  type="hidden" name="twb.withdraworderno" id="withdraworderno"> 
  				<input  type="hidden" name="twb.txnseqNo" id="txnseqNo"> 
  			<input  type="hidden" id="feever"> 
  			<input  type="hidden" id="busicode"> 
  			<input  type="hidden" id="accfirmerno"> 
  		
  											
  													
  			  
        </form>
        <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:tixian()" id="btn_submit" onclick="">提现</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
    </div>
</div>
			</div>
			<div id="person1" style="display:none"></div>
			<div id="person2" style="display:none"></div>
			
			
			
			</div ></div >
  </body>
  
  <script>
  	var width = $("#continer").width();
	
	$(function (){
		
			$('#test').datagrid({
				title:'会员提现信息表',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/withdraw/queryTxnsWinhdrWithdraAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'withdraworderno',title:'提现订单号',width:120,align:'center'},
					{field:'memberid',title:'会员号',width:120,align:'center'},
					{field:'withdrawtype',title:'提现类型',width:120,align:'center',
						formatter:function(value,rec){
							if(value=='1'){
								return '商户';
							}else if(value=='0'){
								return '个人';
							}else{
								return '';
							}
						}
						},
					{field:'amount',title:'提现金额(元)',width:120,align:'center'},
					{field:'bankname',title:'支行名称',width:120,align:'center'},
					{field:'acctno',title:'银行账号',width:120,align:'center'},
					{field:'fee',title:'提现手续费(元)',width:120,align:'center'},
					{field:'status',title:'状态',width:120,align:'center',
						formatter:function(value,rec){
						
							if(value=='01'){
								return '待初审';
							}else if(value=='09'){
								return '初审未过';
							}else if(value=='11'){
								return '待复审';
							}else if(value=='19'){
								return '复审未过';
							}else if(value=='21'){
								return '等待批处理';
							}else if(value=='29'){
								return '批处理失败';
							}else if(value=='00'){
								return '提现成功';
							}else if(value=='39'){
								return '自行终止';
							}else{
								return '';
							}
						}
						},
					{field:'txnseqno-',title:'操作',width:100,align:'center',
					formatter:function(value,rec){
						if(rec.withdraworderno!=null){
							return '<a href="javascript:getWith(\''+rec.withdraworderno+'\')" style="color:blue;margin-left:10px">详细信息</a>';
						}else {
							return '';
						}
					}
				    } 
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'提现申请',
					iconCls:'icon-add',
					handler:function(){
						showAdd();
						$("#inputForm :input").val('');
						
					}
				}]
			});
		});
		

	    
		function search(){
			
		 	var data={"twq.withdraworderno":$('#withdrawordernos').val(),
			"twq.withdrawtype":$('#withdrawtypes').val(),
			"twq.memberid":$('#memberids').val()
		} 	
			$('#test').datagrid('load',data);
		}
		
/* 		function queryTxnsLog(txnseqno){
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
				   success:function(json){
			
				 	}
				});
		} */
		
		function showAdd(){	

		$("#organForm").attr("action","pages/withdraw/queryMemberByMidWithdraAction.action"); 
		$("#password").attr("action","pages/withdraw/saveTxnsWinhdrWithdraAction.action"); 
		
			$('#w').window({
				title: '提现申请',
				top:100,
				width: 800,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 240
			});
			
		
		
			
			$('#organForm :input').val('');
			$('#password :input').val('');		
			$("#amount").val('');
			$("#moneys").html('')
			$('#btn_submit').linkbutton('enable');	
			$("#member").show();
			$("#merch1").hide();
			$("#merch2").hide();
			$("#person1").hide();
			$("#person2").hide();
			var input=	$("#password input[type=password]");
			
			var i=0;
			$.each(input,function(key,value){	
				if(i==0){
				var val=$(value);
				val.removeAttr("readonly");
			//this.removeAttr("readonly")
			}else{
				var val=$(value);
				val.attr("readonly","readonly");
			}
			i++
			});
			
		}
		
		
		
		function showAdds(){	


		
			$('#ws').window({
				title: '详细信息',
				top:100,
				width: 800,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 600
			})}
		function closeAdd(){
			$('#ws').window('close');
			$('#w').window('close');
		}	
	
		
		function saveTxnsW(){
			$("#organForm").form('submit', {  
			    onSubmit: function(){  
				    if($('#organForm').form('validate')){
				    	return 	true;
					}else{
			        return false;   
					} },   
			    
			    success:function(data){  
			    	var json = eval('(' + data + ')')
			 	if(json.messg==null){
			    	if(json.txns.withdrawtype==0){
									    	
			    	}else{
			
			    	$("#member").toggle();//隐藏  
			    	$("#merch1").toggle();//显示
			    	$("#Balance").text(json.balance);
			    	$("#bankname").text(json.txns.bankname);	
			    	$("#acctno").text(json.txns.acctno)	;
			    	//会员ID
			         $("#twbmemberid").val(json.txns.memberid);
			        //会员类型     
			    	$("#twbwithdrawtype").val(json.txns.withdrawtype);
			       
			        //银行账号    
			        $("#twbacctno").val(json.txns.acctno);
			        //账户名称
			        $("#twbacctname").val(json.txns.acctname);
			    
			        //银行代码     
			        $("#twbbankcode").val(json.txns.bankcode);
			        //支行名称
			        $("#twbbankname").val(json.txns.bankname);
			        
			        //一级商户号
			        $("#twbmerchId").val(json.txns.merchId);
			        //二级商户号
			        $("#twbsubMerchId").val(json.txns.subMerchId);
			        //卡类型 
			        $("#twbcardType").val(json.txns.cardType);
			        
			        //会员Id
			        $("#twbmemberId").val($("#memberid").val());
			        //类型
			        $("#twbusage").val($("#usage").val());
			        //主行行号
			        $("#totalBankCode").val(json.txns.totalBankCode);
		
			        
			       
			        $("#withdraworderno").val(json.txns.withdraworderno);
			        $("#txnseqNo").val(json.txns.txnseqNo);
			        $("#feever").val(json.txnsLog.feever);
			        $("#busicode").val(json.txnsLog.busicode);
			        $("#accfirmerno").val(json.txnsLog.accfirmerno);
			  
			    	}
			    	
			 	}else{
			 		$.messager.alert('提示',json.messg); 
			 		$('#btn_submit').linkbutton({disabled:false}); 	
			 		
			 	}
		 
			        
			    }  
			});   
		}
				
		function last(ida,idb){
			$("#"+ida).toggle();//隐藏  
	    	$("#"+idb).toggle();//显示
			
		}
		
			
		function password(ida,idb){
	//手续费
	var fee=	$("#txnsfee").text()*1;
	//余额
	var balance=$("#Balance").text()*1;
	 var amoun=$("#amount").val()*1;
			if(money()&&$("#amount").val().length!=0&&amoun+fee-balance<=0){
			//提现金额     
	        $("#twbamount").val($("#amount").val());
			$("#fee").val(fee);
			$("#"+ida).toggle();//隐藏  
	    	$("#"+idb).toggle();//显示
			}else{
				$.messager.alert("提示","请输入合法金额")
			}
		}
		function money(){
			var money=$("#amount").val();
			var balance=$("#Balance").text();
			var masg;
			var isok=true;
			if(money!=null&&isMoney(money)){
				if(money-balance>0){
					$("#amount").val(balance);
					money=balance;
				}
				masg=toChineseCash(money);
				 $("#moneys").css("color","black");		
			}else{
			masg="请输入合法的金额";
			 $("#moneys").css("color","red");
			 
				isok= false;}
				$("#moneys").html(masg);
			return isok;
			
		
		}
		
		

	$("#password input").keyup(function (event){
	//需要判断是否是第一个     需要判断是否按下的是删除键   如果不是第一个 且按下删除 回退一个
	var even= event.which;
	var my=$(this);
	var prev=my.prev();
	var next=my.next();
	
	//alert(next.attr("type")=="password");
	//alert(prev.tagName!="INPUT");
	my.focus();
	if(chack(even)){
	if(prev.size()==0&&even!=8){
		//下一个
		my.attr("readonly","readonly")
		next.removeAttr("readonly")
		next.focus();
		}else if(prev.size()==0&&even==8){
			my.focus();
		}else{
		if(even==8&&prev.size()!=0){
			//删除
			my.attr("readonly","readonly");
			prev.removeAttr("readonly");
			prev.focus();
		}else if(next.attr("type")=="password"&&prev.size()!=0){
			my.attr("readonly","readonly")
			next.removeAttr("readonly")
			next.focus();
		}else{
		var input=	$("#password input[type=password]");	
		var password='';
		input.each(function(){
			password+=$(this).val();
		    
		});
			$("#twbpassword").val(password);
		
		}
		
		}
		}else{
/* 		$.messager.alert("提示","请输入数字"
				
				 ,function (data){
			alert(data);
			if(data){
				alert("555")
				alert(my)
				my.focus();
			}
			
		}
		); */

		}
	
	
		
		
	});
	
	
	function chack(keyCode){
          if (keyCode == 46 || (keyCode >= 48 && keyCode <=57) || keyCode == 8||(keyCode >= 96 && keyCode <=105))//8是删除键  
              return true;  
          else  
              return false;  
      
	}
		function tixian(){
			$("#password").form('submit', {  
			    onSubmit: function(){  
				return true;  
		
			    },   
		
			    success:function(data){
			     	var json = eval('(' + data + ')')
			    	if(json.falg==true){
			    		$.messager.alert("提示",json.messg)	
			    	 	search();
			    		closeAdd();
			    	}else{
			    	$.messager.alert("提示",json.messg)
			   
			    	}
			    	
			    	
			    }
					
			
			});
			
			
		}
		
		$("#amount").blur(function() {
			
			$.ajax({
				   type: "POST",
				   url: "pages/withdraw/getTxnFeeWithdraAction.action",
				   data:"txns.txnseqno="+$("#withdraworderno").val()+"&&txns.feever="+$("#feever").val()+"&&txns.busicode="+$("#busicode").val()+"&&txns.amount="+$("#amount").val()+"&&txns.accfirmerno="+$("#accfirmerno").val()+"&&txns.cardtype="+$("#twbcardType").val(),
				   dataType:"json",
				   success:function(json){
					if(json.messg==null){
						$("#txnsfee").html(json.fee);
					
					}else{
						
						$.messager.alert("提示",json.messg)
					}   
					
				 	}
				});
			
			
		})
		
	
		

		function getWith(withdraworderno){
			var ison=false;
			$.ajax( { 
					type: "POST",
		             url: "pages/withdraw/queryTxnsWinhdrWithdraAction.action",
		             data: {"twq.withdraworderno":withdraworderno},
		             dataType: "json",
		             async:false,
		             success: function(data){
		            var	json=data.rows[0];
		            if(json==null){
		            	$.messager.alert('提示',"数据不正确，请刷新后重试");	
		            }else{
		            	$("#twithdraworderno").html(json.withdraworderno);
		            	$("#tbatchno").html(json.batchno);
		            	$("#tmemberid").html(json.memberid);
		            	
		            	if(json.withdrawtype=='1'){
		            		$("#twithdrawtype").html('商户');
		            	}else{
		            		
		            		$("#twithdrawtype").html('个人');
		            	}
		            	
		            	$("#tamount").html(json.amount+'元');
		            	$("#tacctno").html(json.acctno);
		            	$("#tacctname").html(json.acctname);
		            	$("#tbankcode").html(json.bankcode);
		            	$("#tbankname").html(json.bankname);
		            	$("#tfee").html(json.fee);
		            	$("#ttxntime").html(json.txntime);
		            	$("#tfinishtime").html(json.finishtime);
		        		if (json.status == '01') {
	            			$("#tstatus").html("待初审");
						} else if (json.status == '09') {
							$("#tstatus").html("初审未过");
						} else if (json.status == '11') {
							$("#tstatus").html("待复审");
						} else if (json.status == '19') {
							$("#tstatus").html("复审未过");
						} else if (json.status == '21') {
							$("#tstatus").html("等待批处理");
						} else if (json.status == '29') {
							$("#tstatus").html("批处理失败");
						} else if (json.status == '00') {
							$("#tstatus").html("提现成功");
						} else if(json.status == '39') {
							return '自行终止';
						}
		            	$("#twithdrawinstid").html(json.withdrawinstid);
		            	$("#tretcode").html(json.retcode);
		            	$("#tretinfo").html(json.retinfo);
		            	$("#tinuser").html(json.inuser);
		            	$("#tintime").html(json.intime);
		            	$("#tstexauser").html(json.stexauser);
		            	$("#tstexatime").html(json.stexatime);
		            	$("#tstexaopt").html(json.stexaopt);
		            	$("#tcvlexauser").html(json.cvlexauser);
		            	$("#tcvlexatime").html(json.cvlexatime);
		            	$("#tcvlexaopt").html(json.cvlexaopt);
		            	$("#tgatewayorderno").html(json.gatewayorderno);
		            	$("#ttotalbankcode").html(json.totalbankcode);
		            	$("#ttxnseqno").html(json.txnseqno);
		           isok=true;
		            }
		             }
			})
			if(isok==true){
			showAdds();
			}
			}
		

	</script>
</html>
