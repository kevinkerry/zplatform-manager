<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../top.jsp"%>
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
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<input type="hidden" id="flag_ins" value="${flag}" />
				<table width="100%">
					<tr>
						<td align="right" width="10%">订单号</td>
						<!-- <td align="left" style="padding-left:5px" width="15%">
							<input  id="merchId_ins" maxlength="15"/>
						</td> -->
						<td><input type="text" id="order" name="orderId"
							maxlength="15" /></td>
						<td align="right"><a href="javascript:search1()"
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
  	var ord;
  	var width = $("#continer").width();
		$(function(){
			flag=$('#flag_ins').val();
		//	if(flag=='1'){
				$('#test').datagrid({
					title:'募集款信息列表',
					iconCls:'icon-save',
					height:600,
					singleSelect:true,
					nowrap: false,
					striped: true,
					url:'pages/fund/raise_query.action',
					remoteSort: false,
					columns:[
					[
						{field:'orderid',title:'订单号',align:'center',width:180},
						{field : 'tid',hidden:true},
						{field:'memberid',title:'商户号',width:180,align:'center'},
						{field:'financingid',title:'融资人',width:180,align:'center'},
						{field:'productcode',title:'产品代码',width:180,align:'center'}, 
						{field:'status',title:'状态',width:180,align:'center',
							formatter:function(value,rec){
							if(value=="00"){
								return "审核通过";
							}if(value=="12"){
								return "注册待完善信息";
							}else if(value=="29"){
								return "注册审核终止";
							}else if(value=="49"){
								return "修改审核终止";
							}else if(value=="19"){
								return "商户注册终止";
							}else if(value=="69"){
								return "注销审核终止";
							}else if(value=="99"){
								return "商户归档";
							}else if(value=="41"){
								return "变更复审未过";
							}else if(value=="40"){
								return "变更待复审";
							}else if(value=="31"){
								return "变更待复审";
							}else if(value=="61"){
								return "注销复审未过";
							}else if(value=="21"){
								return "注册复审未过";
							}else if(value=="20"){
								return "注册待复审";
							}else if(value=="30"){
								return "变更待初审";
							}else if(value=="51"){
								return "注销初审未过";
							}else if(value=="50"){
								return "注销待初审";
							}else if(value=="11"){
								return "注册初审未过";
							}else if(value=="10"){
								return "注册待初审";
							}else if(value=="60"){
								return "注销待复审";
							}
						}
							},	
						{field:'DEPT_ID',title:'操作',width:150,align:'center',
							formatter:function(value,rec){
					//			if(rec.status=='11'||rec.status=='21'){
					//				return '<a href="javascript:toMerchChange('+rec.orderId+')" style="color:blue;margin-left:10px">修改</a>&nbsp;<a href="javascript:toMerchDetail('+rec.SELF_ID+')" style="color:blue;margin-left:10px">详情</a>';
					//			}else if(rec.status=='12'){
					//				return '<a href="javascript:toUpload('+rec.orderId+')" style="color:blue;margin-left:10px">上传证件照片</a>';
					//			}else{
					//				return '<a href="javascript:toMerchDetail('+rec.orderId+')" style="color:blue;margin-left:10px">详情</a>';
					//			}
							if(rec.status=="01"){
								return '<a href="javascript:shenHe(\''+rec.tid+'\')" style="color:blue;margin-left:10px">审核</a>';
							}
							}
						}
						
					]],
				
						pagination:true,
						rownumbers:true,
						/**
						toolbar:[{
							id:'btnadd',
							text:'新增商户',
							iconCls:'icon-add',
							handler:function(){
								window.location.href= "<%=basePath%>" +'/pages/merchant/showMerchAddMerchantAction.action';
								window.event.returnValue = false;
							}
					
						}]
				*/
				});
			//}else{
		//		$('#test').datagrid({
		//			title:'商户信息列表',
		//			iconCls:'icon-save',
		//			height:600,
		//			singleSelect:true,
		//			nowrap: false,
		//			striped: true,
		//			url:'pages/merchant/queryMerchMerchantAction.action?flag='+flag,
		//			remoteSort: false,
		//			columns:[
		//			[
		//				{field:'MEMBER_ID',title:' 订单号',align:'center',width:140},
		//				{field:'ENTERPRISE_NAME',title:'商户号',width:120,align:'center'},
		//				{field:'LICENCE_NO',title:'融资人',width:100,align:'center'},
		//				{field:'CORPORATION',title:'产品代码',width:100,align:'center'}, 
		//				{field:'STATUS',title:'状态',width:120,align:'center',
		//					formatter:function(value,rec){
		//						if(value=="00"){
		//							return "在用";
		//						}else if(value=="29"){
		//							return "注册审核终止";
        //						}else if(value=="49"){
		//							return "修改审核终止";
	     //						}else if(value=="19"){
		//							return "商户注册终止";
		//						}else if(value=="69"){
		//	                    	return "注销审核终止";
		//						}else if(value=="99"){
		//							return "商户归档";
		//						}else if(value=="41"){
		//							return "变更复审未过";
		//						}else if(value=="40"){
		//							return "变更待复审";
		//						}else if(value=="31"){
		//							return "变更待复审";
		//						}else if(value=="61"){
		//							return "注销复审未过";
		//						}else if(value=="21"){
		//							return "注册复审未过";
		//						}else if(value=="20"){
		//							return "注册待复审";
		//						}else if(value=="30"){
		//							return "变更待初审";
		//						}else if(value=="51"){
		//							return "注销初审未过";
		//						}else if(value=="50"){
		//							return "注销待初审";
		//						}else if(value=="11"){
		//							return "注册初审未过";
		//						}else if(value=="10"){
		//							return "注册待初审";
		//						}else if(value=="60"){
		//							return "注销待复审";
		//						}
		//				}
		//					},	
		//				{field:'DEPT_ID',title:'操作',width:100,align:'center',
		//					formatter:function(value,rec){
		//						if(flag=='1'){
		//							return '<a href="javascript:toMerchChange('+rec.SELF_ID+')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:toMerchDetail('+rec.SELF_ID+')" style="color:blue;margin-left:10px">详情</a>';
		//						}else if(flag=='2'){
		//							return '<a href="javascript:toMerchAudit('+rec.SELF_ID+')" style="color:blue;margin-left:10px">审核</a>';
		//						}else{
		//							return '<a href="javascript:toMerchAudit('+rec.SELF_ID+')" style="color:blue;margin-left:10px">复核</a>';
		//						}
		//					}
		//			  }
		//			]],
		//				pagination:true,
		//				rownumbers:true
		//		});
		//	}
				//	
			var p = $('#test').datagrid('getPager');
			$.extend($.fn.validatebox.defaults.rules, {   
			    minLength: {   
			        validator: function(value, param){ 
					var re =  /^\d+$/; 
		        		if(!re.test(value)){
							return false;
		        	}  
			            return value.length >= param[0];   
			        },   
		             message: '请输入4位数字的部门代码'  
			    }
			   
			});  
	    });
//======================================查询操作！做修改   ajaxfrom=================================== 
// 修改：调用该方法以后发送请求，查询出按照要求的所有的符合要求的数据。	
		function search(){
			//var url="pages/merchant/queryMerchMerchantAction.action?flag="+$("#flag").val();
			var data={
					'merchDeta.member.memberId':$('#merchId_ins').val(),
					'merchDeta.member.memberName':$('#memberName_ins').val()
					};
			$('#test').datagrid('load',data);
		}
		
		
		function search1(){
//	window.location.href= "<%=basePath%>" +'pages/fund/raise_search.action?orderId='+$("#order").val();
			var data={
					'orderId':$('#order').val()
					};
			$('#test').datagrid('load',data);
		}
//===================================================================================================
		function toMerchChange(merchApplyId){
			window.location.href= "<%=basePath%>" +'/pages/merchant/toMerchChangeMerchantAction.action?merchApplyId='+merchApplyId;
			//window.event.retjurnValue = false;
		}

		function toMerchDetail(merchApplyId){
			window.location.href= "<%=basePath%>" +'/pages/merchant/toMerchDetailMerchantAction.action?merchApplyId='+merchApplyId;
			//window.event.returnValue = false;
		}
		function toMerchAudit(merchApplyId){
			flag=$('#flag_ins').val();
			window.location.href= "<%=basePath%>" +'/pages/merchant/toMerchDetailMerchantAction.action?merchApplyId='+merchApplyId+'&flag='+flag;
			//window.event.returnValue = false;
		}
		function toUpload(merchApplyId){
			window.location.href= "<%=basePath%>" +'/pages/merchant/.action?='+merchApplyId;
		}
		//审核
		function shenHe(tid){
			//window.location.href= "<%=basePath%>" +'/pages/fund/raise_aduit.action?tid='+tid;
			if(confirm("确定审核通过？")){
			$.ajax({
				"type":"post",
				"url" : "<%=basePath%>" +'/pages/fund/raise_aduit.action',
				"data" :{"tid":tid},
				dataType: "json",
				success:function(data){
					if(data.success==1){
						
						window.location.reload(true);
					}else{
						alert("审核失败");
						window.location.reload(true);
					}
				}
			});
			}else{
				window.location.reload(true);
			}
		}
	</script>
</html>
