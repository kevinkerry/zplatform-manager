<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
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
	    <div id="p" class="easyui-panel" title="查询条件" style="height:100px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" action="pages/merchant/saveProcessUploadAction.action">
				<table width="100%">
					<tr height="26" id="fileadd1">
							<td align="center">
								对账机构
							</td>
							<td colspan="1">
								<select id="instiid_ins" class="easyui-validatebox"  name="instiid" >
								  <!--  <option value=''>请选择</option>
						          <option value='96000001'>融宝快捷支付</option>
						          <option value='98000001'>证联支付</option>
						          <option value='97000001'>中信网银</option>-->
					        	</select>
							</td>
							<td align="center" colspan="2" id="uploadbutton">
								<a class="easyui-linkbutton" iconCls="icon-ok" 
									href="javascript:saveProcess()">生成任务</a>
							</td>
						</tr>
						<tr>
						
						<td align="center">合约开始日期</td>
						<td><input name="agreemtStart" maxlength="12"   type="text"  id="startDate"/></td>
						<td align="center">合约终止日期</td>
						<td><input  class="easyui-validatebox" maxlength="12"  name="agreemtEnd"  id="endDate"/></td>
						<td>
						 <div region="south" border="false" style="text-align:center;padding:5px 0;">
						 <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:search()" id="btn_submit" onclick="">查询</a>
			             </div>
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
	$(function(){
	  	  $('#startDate,#endDate').datebox({   
	      }); 
	  	queryChannel();
	}); 	  
	
	
	function queryChannel() {
		$.ajax({
			type : "POST",
			url : "pages/merchant/queryChannelUploadAction.action",
			data : "",
			dataType : "json",
			success : function(json) {
				var dataArray = eval(json);
				var html = "<option value=''>请选择</option>"
				for ( var i in dataArray) {
					//TODO
					for (j = 0; j < dataArray[i].length; j++) {
						html += "<option value='"+dataArray[i][j].chnlCode+"'>"
								+ dataArray[i][j].chnlName + "</option>"
						$("#instiid_ins").html(html)
					}
				}

			}
		});
	}
	
	$(function(){
		$('#test').datagrid({
			title:'任务列表',
			iconCls:'icon-save',
			height:400,
			singleSelect:true,
			nowrap: false,
			striped: true,
			url:'pages/merchant/queryProcessUploadAction.action',
			remoteSort: false,
			idField:'TID',
			columns:[
			[
				{field:'TID',title:'任务代码',width:120,align:'center'},
				{field:'STARTTIME',title:'任务开始时间',width:100,align:'center'},
				{field:'STATUS',title:'状态',width:100,align:'center',
					formatter:function(value,rec){
					if(value=="00"){
						return "初始";
					}else if(value=="01"){
						return "进行中";
					}else if(value=="02"){
						return "失败终止";
					}else if(value=="99"){
						return "成功结束";
					}
				}
					},	
				{field:'TI',title:'操作',width:100,align:'center',
				formatter:function(value,rec){
					if(rec.STATUS=="00"){
						return '<a href="javascript:startProcess('+rec.TID+')" style="color:blue;margin-left:10px">开始任务</a>';
					}else{
						return "";
					}
					
				}
				}
				
			]],
			pagination:true,
			rownumbers:true
			
		});

	});
	
	
	function saveProcess() {
		if($("#instiid_ins").val()==""){
			alert("请选择对账机构");
		}else{
			$('#theForm').form('submit', {
				onSubmit : function() {
					return $('#theForm').form('validate');
				},
				success : function(json) { 
					json =eval('('+json+')');
					if (json.INFO == "执行成功!") {				
						$.messager.alert('提示', json.INFO);
						search();
					} else {
					$("#uploadbutton").show();
						$.messager.alert('提示', json.INFO);
						$("#uploadbutton").show();
						$("#uploadhint").hide();
					}
				}
			});
		}		
		

	}
	function search(){
		
		var data={"startDate":$('#startDate').datebox('getValue'),"endDate":$("#endDate").datebox('getValue')};
		$('#test').datagrid('load',data);
	}
	function startProcess(tId) {

		 $.ajax({
			   type: "POST",
			   url: "pages/merchant/StartCheckFileUploadAction.action",
			   data: "filestartid="+tId,
			   dataType:"json",
			   success:function(json){
				$.each(json, function(key,value){
		    		$.messager.alert('提示',value.INFO);   
		    		search();
				}) 
			 	}
			}); 


    	
	}

</script>
</html>
