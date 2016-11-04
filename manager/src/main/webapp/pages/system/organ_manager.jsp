<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<style>
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
<body>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="15%">机构代码</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							name="organCode" id="organCode" /></td>
						<td align="right" width="15%">机构名称</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							name="organName" id="organName" /></td>
						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a> <a
							href="javascript:resize()" class="easyui-linkbutton"
							iconCls="icon-redo">清空</a></td>
					</tr>

				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>

	</div>
	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="organForm" action="pages/system/saveOrganAction.action"
					method="post">
					<input type="hidden" id="org_id" name="organ.organId" /> <input
						type="hidden" id="org_status" name="organ.status" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left" id="inputForm">
						<tr>
							<td width="15%">机构代码</td>
							<td width="30%"><input id="org_code" name="organ.organCode"
								required="true" missingMessage="请输入机构代码" type="text"
								class="easyui-validatebox" maxlength="4"
								validType="minLength[4,4]" /></td>
							<td width="15%">机构名称</td>
							<td><input id="org_name" name="organ.organName"
								required="true" missingMessage="请输入机构名称" type="text"
								class="easyui-validatebox" maxlength="20" /></td>
						</tr>
						<tr>
							<td>上级机构</td>
							<td><select id="org_super" class="easyui-validatebox"
								missingMessage="请选择上级机构" name="organ.superid"
								class="easyui-validatebox">
									<option value="">--请选择上级机构--</option>
							</select></td>
							<td>所属省</td>
							<td><select id="org_province" class="easyui-validatebox"
								missingMessage="请选择所属省" required="true" name="organ.province"
								class="easyui-validatebox" onchange="showCity()">
									<option value="">--请选择所属省--</option>
							</select></td>
						</tr>
						<tr>
							<td>所属市</td>
							<td><select id="org_city" class="easyui-validatebox"
								required="true" missingMessage="请选择所属市" name="organ.city"
								class="easyui-validatebox">
									<option value="">--请选择所属市--</option>
							</select></td>
							<td></td>
							<td></td>
						</tr>
						<tr>

							<td>备注</td>
							<td colspan="3"><textarea rows="3" cols="60" id="org_notes"
									name="organ.notes" maxlength="60" style="resize: none;"></textarea>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:saveRole()" id="btn_submit" onclick="">保存</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
  	var width = $("#continer").width();
		$(function(){
			showProvince();
			$('#test').datagrid({
				title:'组织机构列表',
				iconCls:'icon-save',
				height:600,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/system/queryOrganAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'ORGAN_CODE',title:'机构代码',width:120,align:'center'},
					{field:'ORGAN_NAME',title:'机构名称',width:150,align:'center'},
					{field:'SUPERID',title:'上级机构',width:100,align:'center'},
					{field:'PROVINCE',title:'所属省',width:100,align:'center'},
					{field:'CITY',title:'所属市',width:100,align:'center'},
					{field:'CREATOR',title:'创建者',width:100,align:'center'},
					{field:'STATUS',title:'状态',width:100,align:'center',
						formatter:function(value,rec){
						if(value=="00"){
							return "使用";
						}else{
							return "失效";
						}
					}
						},	
					{field:'ORGAN_ID',title:'操作',width:100,align:'center',
					formatter:function(value,rec){
							if(rec.STATUS=="00"){
								return '<a href="javascript:showOrgan('+value+')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+value+'\')" style="color:blue;margin-left:10px">注销</a>';
							}else{
								return "";
							}
						
					}
									}
					
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'btnadd',
					text:'新增机构',
					iconCls:'icon-add',
					handler:function(){
						showAdd();
						showSuperOrg();
						$("#inputForm :input").val('');
						$("#org_code").removeAttr('disabled');
					}
				}]
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
			        message: '请输入4位数字的组织机构代码'  
			    }
			   
			}); 
			
		});

		

		function resize(){
			$('#theForm :input').val('');
		}
		function showAdd(){	
			$("#organForm").attr("action","pages/system/saveOrganAction.action");
			$('#w').window({
				title: '新增机构',
				top:100,
				width: 500,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 240
			});
			$('#btn_submit').linkbutton('enable');	
		}
		function closeAdd(){
			$('#w').window('close');
		}		

	    
		function search(){
			var data={organCode:$('#organCode').val(),"organName":$("#organName").val()};
			$('#test').datagrid('load',data);
		}
		
		
		function saveRole(){
			
			$('#organForm').form('submit', {  
			    onSubmit: function(){  
				    if($('#organForm').form('validate')){
				    	$('#btn_submit').linkbutton('disable');		
						return true;
					}
			    
			        return false;   
			    },   
			    success:function(data){  
			    	var json = eval('(' + data + ')')
			    	$.each(json, function(key,value){
			    		$.messager.alert('提示',value.INFO); 
			    		if(value.RET='succ'){
			    			search();
				    		closeAdd();
				    	}  
			    		$('#btn_submit').linkbutton('enable');		
					}) 
			        
			    }   
			});  
			
		}
		function showProvince(){		
			$.ajax({
			   type: "POST",
			   url: "pages/system/queryProvinceOrganAction.action",
			   dataType:"json",
			   success: function(json){
			   		var html ="<option value=''>--请选择所属省--</option>";
			   		$.each(json, function(key,value){
						//alert(value.roleName);
						html += '<option value="'+value.pId+'">'+value.pName+'</option>';
					})
			   		$("#org_province").html(html);
			   }
			});
		}

		function showCity(){
			var pid = $("#org_province").val();
			$.ajax({
				   type: "POST",
				   url: "pages/system/queryCityOrganAction.action",
				   data: "pid="+pid,
				   dataType:"json",
				   success: function(json){
				   		var html ="<option value=''>--请选择所属市--</option>";
				   		$.each(json, function(key,value){
							//alert(value.roleName);
							html += '<option value="'+value.cId+'">'+value.cName+'</option>';
						})
				   		$("#org_city").html(html);
				   		
				   }
				});	
		}

		function showSuperOrg(){
			$.ajax({
				   type: "POST",
				   url: "pages/system/getSuperOrganAction.action",
				   dataType:"json",
				   success: function(json){
				   		var html ="<option value=''>--请选择上级机构--</option>";
				   		$.each(json, function(key,value){
							html += '<option value="'+value.organId+'">'+value.organName+'</option>';
						})
				   		$("#org_super").html(html);
				   }
				});	
		}
		function deleteOrgan(organId){
			$.messager.confirm('提示','您是否想要注销此机构?',function(r){   
			   if (r){  
				$.ajax({
				   type: "POST",
				   url: "pages/system/deleteOrganAction.action",
				   data: "organId="+organId,
				   dataType:"json",
				   success:function(json){
						$.each(json, function(key,value){
				    		$.messager.alert('提示',value.INFO);   
				    		search();
				    		closeAdd();
						}) 
				   
				 	}
				});
				    }   
				});  
		}
		function showOrgan(organId){	
			$("#organForm").attr("action","pages/system/updateOrganAction.action");	
			$("#org_code").attr('class','easyui-validatebox');
			$.ajax({
			   type: "POST",
			   url: "pages/system/getSingleByIdOrganAction.action",
			   data: "organId="+organId,
			   dataType:"json",
			   success: function(json){
				  
				var html ="<option value=''>--请选择所属市--</option>";
		   		$.each(json.cityList, function(key,value){
		   			html += '<option value="'+value.CId+'">'+value.CName+'</option>';
				})
		   		$("#org_city").html(html);
				$("#org_code").val(json.organModel.organCode);
				$("#org_code").attr('disabled','disabled');
				$("#org_name").val(json.organModel.organName);
				$("#org_super").val(json.organModel.superid);
				$("#org_province").val(json.organModel.province);
				$("#org_city").val(json.organModel.city);
				$("#org_notes").val(json.organModel.notes);
				$("#org_id").val(json.organModel.organId);
				$("#org_status").val(json.organModel.status);
				
			   }
			});
			$('#w').window({
				title: '修改机构',
				top:100,
				width: 500,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 240
			});
			$('#btn_submit').linkbutton('enable');	
			//alert($("#organForm").attr("action"));
		}
					
	</script>
</html>
