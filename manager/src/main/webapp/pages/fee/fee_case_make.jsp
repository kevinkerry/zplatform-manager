<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="../../top.jsp"></jsp:include>
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

#dedurateInfo {
	height: 25px;
}

#dedurateInfo  tr td {
	height: 25px;
	border-style: solid;
	border-width: 0px 1px 1px 0px;
	border-color: #3399FF
}

#dedurateInfo  tr td input {
	height: 15px;
	margin-left: 3px
}

#dedurateInfo  tr td textarea {
	height: 24px;
	margin: 2px
}

#dedurateInfo select {
	margin-left: 3px;
	height: 22px
}

.ui-select-input1 a {
	height: 20px;
	width: 40px;
	margin-right: 5px;
	margin-right: 5px;
	padding: 5px
}

#test  tr td {
	height: 45px;
}

#select_zone label {
	margin: 2px;
	width: 140px
}

#other_select_zone label {
	margin: 2px;
	width: 140px
}

#deduRateCaseInfo {
	height: 25px;
}

#deduRateCaseInfo tr td {
	height: 25px;
	border-style: solid;
	border-width: 0px 1px 1px 0px;
	border-color: #000000;
	padding: 1px
}

#deduRateCaseInfo tr td input {
	height: 20px;
	margin-left: 3px;
}

#deduRateCaseInfo tr td span {
	height: 20px;
	margin-left: 3px;
}

#deduRateCaseInfo tr td select {
	height: 20px;
	margin-left: 3px;
}

#deduRateCaseInfo tr td textarea {
	height: 35px;
	margin: 3px;
}
</style>
	<div style="margin-top: 5px">
		<table id="test"></table>
	</div>

	<input type="hidden" id="feever" value="${feever}" />

	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 900px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="theForm" action="pages/fee/updateFeeCaseFeeAction.action"
					method="post">
					<input type="hidden" id="caseid" name="feecaseModel.caseid">
					<table width="100%" cellpadding="2" cellspacing="2"
						id="deduRateCaseInfo" border="1">
						<tr>
							<td align="right" width="174px">扣率版本</td>
							<td align="left"><input id="busiPackCode_show"
								name="feecaseModel.feever"></input></td>
							<td align="right" colspan=2></td>
						</tr>
						<tr style="height: 25px">
							<td align="right">业务代码</td>
							<td align="left"><input id="busicode_show"
								name="feecaseModel.busicode"></input></td>
							<td align="right" width="174px">名称</td>
							<td align="left"><input type="text" id="businame"
								name="feecaseModel.businame" class="easyui-validatebox"
								required="true" maxlength="32" /></td>
						</tr>

						<tr>
							<td align="right">清算标志</td>
							<td align="left" colspan="4"><select
								name="feecaseModel.setlflg" id="setlflg"
								class="easyui-validatebox" required="true">
									<option value="" selected>请选择清算标志</option>
									<option value="0">不参加清算</option>
									<option value="1">参加清算，无原始交易</option>
									<option value="2">参加清算，有原始交易，但原始交易不清算</option>
									<option value="3">参加清算，有原始交易，原始交易参加清算，本交易清算不退还原始交易费用</option>
									<option value="4">
										参加清算，有原始交易，原始交易参加清算，本交易参加清算将退还原始交易手续费</option>
									<option value="5">
										参加清算，有原始交易，原始交易参加清算，本交易参加清算将差额退还原始交易手续费</option>
							</select></td>
						</tr>
						<tr>
							<td align="right">交易计费方式</td>
							<td align="left"><select id="selfFeeType"
								class="easyui-validatebox" name="feecaseModel.feetype"
								required="true"></select></td>
							<td align="right">手续费返还方式</td>
							<td align="left"><select id="feeRetflag"
								class="easyui-validatebox" name="feecaseModel.feeretflag"
								required="true"></select></td>
						</tr>

						<tr>
							<td align="right">商户对交易额记帐标志</td>
							<td align="left"><select id="merchAmtflag"
								class="easyui-validatebox" name="feecaseModel.merchamtflag">
									<option value="" selected>请选择商户对交易额记帐标志</option>
									<option value="N">无此项</option>
									<option value="C">贷记</option>
									<option value="D">借记</option>
							</select></td>
							<td align="right">商户手续费记帐标志</td>
							<td align="left"><select id="merchFeeflag"
								class="easyui-validatebox" name="feecaseModel.merchfeeflag">
									<option value="" selected>请选择商户手续费记帐标志</option>
									<option value="N">无此项</option>
									<option value="C">贷记</option>
									<option value="D">借记</option>
							</select></td>
						</tr>
						<tr>
							<td>备注</td>
							<td align="left" colspan="3"><textarea id="case_notes"
									rows="2" cols="115" name="feecaseModel.notes"></textarea></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" id="btn_submit" iconCls="icon-ok"
					onclick="updateFeeCase()">提交</a> <a class="easyui-linkbutton"
					iconCls="icon-cancel" href="javascript:void(0)"
					onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	<div region="south" border="false"
		style="text-align: center; padding: 5px 0;">
		<a href="javascript:history.back(-1);" class="easyui-linkbutton"
			iconCls="icon-back">返回</a>
	</div>
</body>

<script>
    $(document).ready(function () {//页面加载完成 初始化页面
    	//showProduct();
     });
  	var width = $("#continer").width();
  	$(function(){
  		   var feever=$('#feever').val();
			$('#test').datagrid({
				title:'配置扣率版本实例',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/fee/queryFeeCaseByFeeverFeeAction.action?feever='+feever,
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'FEEVER',title:'扣率代码',width:120,align:'center'},
					{field:'BUSICODE',title:'业务代码',width:100,align:'center'},
					{field:'BUSINAME',title:'业务名称',width:100,align:'center'},
					{field:'STATUS',title:'状态',width:100,align:'center',
						formatter:function(value,rec){
						if(value=="00"){
							return "使用";
						}else{
							return "失效";
						}
					}
						},	
					{field:'DEPT_ID',title:'操作',width:100,align:'center',
					formatter:function(value,rec){
						if(rec.STATUS=="00"){
							return '<a href="javascript:showFeeCase('+'\''+rec.FEEVER+'\''+',\''+rec.BUSICODE+'\','+'\''+rec.BUSINAME+'\''+','+rec.CASEID+')" style="color:blue;margin-left:10px">配置</a>';
						}else{
							return "";
						}
						
					}
									}
					
				]]
			});
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
		
		function showFeeCase(feever,busicode,businame,caseid){	
			$('#w').window({
				title: '扣率实例配置',
				top:100,
				width: 900,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 420
			});
			$("#busiPackCode_show").val(feever);
			$("#busicode_show").val(busicode);                                                                                                                
			$("#businame").val(businame);
			$("#caseid").val(caseid);
/* 			$('#dept_code').attr('class','easyui-validatebox');
			$("#deptForm").attr("action","pages/system/saveDeptAction.action"); */
			$('#btn_submit').linkbutton('enable');		
			showselfFeeType();
			showfeeRetflag();
			//查询实例的详细信息
			$.ajax({
				   type: "POST",
				   url: "pages/fee/queryOneFeeCaseFeeAction.action",
				   data: "caseid="+caseid,
				   dataType:"json",
				   success: function(json){
				       setTimeout(function(){ 
				    	    $("#setlflg").val(json.SETLFLG);
							$("#selfFeeType").val(json.FEETYPE);
							$("#feeRetflag").val(json.FEERETFLAG);
							$("#merchAmtflag").val(json.MERCHAMTFLAG);
							$("#merchFeeflag").val(json.MERCHFEEFLAG);
							$("#case_notes").val(json.NOTES);
					   },500);
				   }
			});
		}
		function closeAdd(){
			$('#w').window('close');
			
		}		

	    
		function search(){
			var data={deptName:$('#deptName').val(),"deptCode":$("#deptCode").val()};
			$('#test').datagrid('load',data);
		}
		
		

		function showProduct(){		
			$.ajax({
			   type: "POST",
			   url: "pages/fee/queryProductAllFeeAction.action",
			   dataType:"json",
			   success: function(json){
			   		var html ="<option value=''>--请选择产品--</option>";
			   		$.each(json, function(key,value){
			   			html += '<option value="'+value.prdtver+'">'+value.prdtname+'</option>';
					})
					$("#prdtver").html(html);
			   }
			});
		}

		

		function showselfFeeType(){		
			$.ajax({
			   type: "POST",
			   url: "pages/fee/querFeeFlagTypeFeeAction.action",
			   dataType:"json",
			   success: function(json){
			   		var html ="<option value=''>--请选择手续费方式--</option>";
			   		$.each(json, function(key,value){
					
						html += '<option value="'+value.PARA_CODE+'">'+value.PARA_NAME+'</option>';
					})
					$("#feeRetflag").html(html);
			   		
			   }
			});
		}
		function showfeeRetflag(){		
			$.ajax({
			   type: "POST",
			   url: "pages/fee/queryFeeSelfTypeFeeAction.action",
			   dataType:"json",
			   success: function(json){
			   		var html ="<option value=''>--请选择交易计费方式--</option>";
			   		$.each(json, function(key,value){
						html += '<option value="'+value.PARA_CODE+'">'+value.PARA_NAME+'</option>';
					})
					$("#selfFeeType").html(html);
			   }
			});
		}
	function updateFeeCase(){		
		$('#theForm').form('submit', {  
			  onSubmit: function(){  
			   if($('#theForm').form('validate')){
			    	$('#btn_submit').linkbutton('disable');
			    	return true;   
			   }
			        return false;   
			   },   
			  success:function(data){ 
				    var a= data.split("validateUserLoginAction");
					if(data.split("validateUserLoginAction").length>1){
						window.parent.location.replace("<%=basePath%>"+"pages/logoutAction.action?relogin=relogin");
						return ;
					}else if(data=='操作成功!'){
			    		alert(data);			    		
			    		$('#btn_submit').linkbutton('disable');
			    		closeAdd();
				    }
			    					        
			 }   
			});  
		}
					
	</script>
</html>
