<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>

						<td align="right" width="15%">角色名称</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							name="role.roleName" id="roleName" /></td>
						<td align="right" width="15%">所属机构</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="roleOrganId" name="role.organId" onchange="showDept(1)">
								<option value=''>--请选择所属机构--</option>
						</select></td>
					</tr>
					<tr>

						<td align="right" width="15%">所属部门</td>
						<td align="left" style="padding-left: 5px" width="25%"><select
							id="roleDeptId" name="role.deptId">
								<option value=''>--请选择所属部门--</option>
						</select></td>
						<td></td>
						<td></td>
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
				<form id="roleForm" action="pages/system/saveRoleAction.action"
					method="post">
					<input id="role_roleId" name="role.roleId" type="hidden" />
					<table width="100%" cellpadding="2" cellspacing="2"
						style="text-align: left">
						<tr>
							<td>角色名称</td>
							<td><input id="role_name" name="role.roleName"
								missingMessage="请输入角色名称" required="true" type="text"
								class="easyui-validatebox" maxlength="20" /></td>
							<td>所属机构</td>
							<td><select id="role_organId" name="role.organId"
								required="true" missingMessage="请选择所属机构" type="text"
								class="easyui-validatebox" onchange="showDept()">
									<option value="">--请选择所属机构--</option>
							</select></td>
						</tr>
						<tr>
							<td>所属部门</td>
							<td><select id="role_deptId" class="easyui-validatebox"
								required="true" missingMessage="请选择所属部门" name="role.deptId"
								class="easyui-validatebox">
									<option value="">--请选择所属部门--</option>
							</select></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>备注</td>
							<td colspan="3"><textarea rows="3" cols="60" id="role_notes"
									maxlength="64" name="role.notes" style="resize: none;"
									onkeyup="value=value.replace(/<[^<]+>/g,'')"></textarea></td>
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
	<div id="w2" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<div region="center" title="权限栏" id="authMenu" closed="true">
					<input id="roleId" name="role.roleId" type="hidden" />
					<div style="margin: 5px" id="menu_div">
						<ul id="tt2"></ul>

						<a href="javascript:submitUserAuth()" class="easyui-linkbutton"
							iconCls="icon-ok">提交</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
  	var width = $("#continer").width();
		$(function(){
			showOrgan();
			/*function hello(){
				
				if(width!= $('#continer').width()){
					
					var pp = $('#test').datagrid('resize');
					$('#p').panel('resize');
					width= $('#continer').width();
				}
				//alert(width+"/"+$('#continer').width());
				window.setTimeout(hello,500);
			}
			window.setTimeout(hello,500);*/
			$('#test').datagrid({
				title:'角色列表',
				iconCls:'icon-save',
				height:600,
				nowrap: false,
				striped: true,
				url:'pages/system/queryRoleAction.action',
				singleSelect:true,
				remoteSort: false,
				idField:'ROLE_ID',
				columns:[
				[
					{field:'ROLE_NAME',title:'角色名称',width:120,align:'center'},
					{field:'ORGAN_ID',title:'所属机构',width:150,align:'center'},
					{field:'DEPT_ID',title:'所属部门',width:150,align:'center'},
					{field:'CREAT_DATE',title:'创建时间',width:150,align:'center'},
					{field:'CREATOR',title:'创建者',width:120,align:'center'},
					{field:'NOTES',title:'备注',width:120,align:'center'}, 
					{field:'STATUS',title:'状态',width:120,align:'center',
						formatter:function(value,rec){
							if(value=="00"){
								return "使用";
							}else{
								return "失效";
							}
						}
					},	
					{field:'ROLE_ID',title:'操作',width:150,align:'center',
						formatter:function(value,rec){
							if(rec.STATUS=="00"){
								return '<a href="javascript:ToshowRoleAuthority('+value+')" style="color:blue;margin-left:10px">赋权</a>&nbsp;<a href="javascript:showRole('+value+')" style="color:blue;margin-left:10px">修改</a>&nbsp;<a href="javascript:deleteRole('+value+')" style="color:blue;margin-left:10px">注销</a>';
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
					text:'新增角色',
					iconCls:'icon-add',
					handler:function(){
					showAdd();
					}
				}]
			});
			
		});
		function resize(){
			$('#theForm :input').val('');
		}
		function showAdd(){	
			$('#w').window({
				title: '新增角色',
				top:100,
				width: 500,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 210
			});
			$('#roleForm :input').val('');
			$("#roleForm").attr("action","pages/system/saveRoleAction.action");
			$('#btn_submit').linkbutton('enable');	
		}
		function closeAdd(){
			$('#w').window('close');
			
		}		

	    
		function search(){
			var data={'role.roleName':$('#roleName').val(),"role.deptId":$("#roleDeptId").val(),'role.organId':$("#roleOrganId").val()};
			$('#test').datagrid('load',data);
		}
		
		
		function saveRole(){
			$('#roleForm').form('submit', {  
			    onSubmit: function(){  
			    	if($('#roleForm').form('validate')){
			    		$('#btn_submit').linkbutton('disable');	
			    		return true;
			   		}
			        return false;   
			    },   
			    success:function(data){   
			    	 var json = eval('(' + data + ')')
			    	$.each(json, function(key,value){
			    		$.messager.alert('提示',value.INFO);   
			    		search();
			    		closeAdd();
			    		$('#btn_submit').linkbutton('enable');	
					}) 
			        	
				    
			    }   
			});  
			 
		}
		function showRole(roleId){		
			$.ajax({
			   type: "POST",
			   url: "pages/system/getSingleByIdRoleAction.action",
			   data: "roleId="+roleId,
			   dataType:"json",
			   success: function(json){
			    $("#role_roleId").val(json.roleId);
				$("#role_name").val(json.roleName);
				$("#role_organId").val(json.organId);
				$("#role_notes").val(json.notes);
				var html = '<option value="">--请选择所属部门--</option>';
				$.ajax({
					type: "GET",
				  	url: "pages/system/showDeptRoleAction.action",
				  	data: "rand="+new Date().getTime()+"&organId="+json.organId,
				 	dataType: "json",
				 	async: false,
				 	success:function(json){
						$.each(json, function(key,value){
							html += '<option value="'+value.deptId+'">'+value.deptName+'</option>';
						})
						$("#role_deptId").html(html);
				 	}
				});
				$("#role_deptId").val(json.deptId);
				
			   }
			});
			$('#w').window({
				title: '修改角色',
				top:100,
				width: 500,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 210
			});
			$("#roleForm").attr("action","pages/system/updateRoleAction.action");
			$('#btn_submit').linkbutton('enable');	
		}
		
		function deleteRole(roleId){
			$.ajax({
				   type: "POST",
				   url: "pages/system/getSingleByIdRoleAction.action",
				   data: "roleId="+roleId,
				   dataType:"json",
				   success: function(json){
				    $("#role_roleId").val(json.roleId);
					$("#role_name").val(json.roleName);
					$("#role_organId").val(json.organId);
					$("#role_notes").val(json.notes);
					var html = '<option value="">--请选择所属部门--</option>';
					$.ajax({
						type: "GET",
					  	url: "pages/system/showDeptRoleAction.action",
					  	data: "rand="+new Date().getTime()+"&organId="+json.organId,
					 	dataType: "json",
					 	async: false,
					 	success:function(json){
							$.each(json, function(key,value){
								html += '<option value="'+value.deptId+'">'+value.deptName+'</option>';
							})
							$("#role_deptId").html(html);
					 	}
					});
					$("#role_deptId").val(json.deptId);
					
				   }
				});
				$('#w').window({
					title: '注销角色',
					top:100,
					width: 500,
					collapsible:false,
					minimizable:false,
					maximizable:false,
					modal: true,
					shadow: false,
					closed: false,
					height: 210
				});
				$("#roleForm").attr("action","pages/system/deleteRoleAction.action");
				$('#btn_submit').linkbutton('enable');	
		}
		function showDept(flag){
			var organId;
			if(flag==1){
				organId=$('#roleOrganId').val();
			}else{
				organId=$('#role_organId').val();
			}
			var html = '<option value="">--请选择所属部门--</option>';
			$.ajax({
				type: "GET",
			  	url: "pages/system/showDeptRoleAction.action",
			  	data: "rand="+new Date().getTime()+"&organId="+organId,
			 	dataType: "json",
			 	success:function(json){
					$.each(json, function(key,value){
						html += '<option value="'+value.deptId+'">'+value.deptName+'</option>';
					})
					if(flag==1){
						$("#roleDeptId").html(html);
					}else{
						$("#role_deptId").html(html);
					}
					
			 	}
			});
		}

		function showOrgan(){
			var html = '<option value="">--请选择所属机构--</option>';
			$.ajax({
				type: "GET",
			  	url: "pages/system/showOrganRoleAction.action",
			  	data: "rand="+new Date().getTime(),
			 	dataType: "json",
			 	success:function(json){
					$.each(json, function(key,value){
						html += '<option value="'+value.organId+'">'+value.organName+'</option>';
					})
					$("#role_organId,#roleOrganId").html(html);
			 	}
			});
		}
					
		function ToshowRoleAuthority(roleId){
			$("#roleId").val(roleId);
			$('#w2').window({
				title: '角色赋权',
				top:10,
				width: 500,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 510
			});
			$("#roleId").val(roleId);
			var flag = true;
			$('#tt2').tree({
				checkbox: true,
				animate:true,
				url: 'pages/system/queryFunctionRoleAction.action?roleId='+roleId,
				loadFilter: function(data){   
					if(flag){
						roleFunction = data.roleFunction;
						flag = false;
					}
			        return data.result;
			    },   
			    onBeforeExpand:function(node){
					var child = $('#tt2').tree('getChildren', node.target);
					if(child==""){
						return false;
					}
				},
				onContextMenu: function(e, node){
					e.preventDefault();
					$('#tt2').tree('select', node.target);
					$('#mm').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			});
	   }
		function submitUserAuth(){
			var fid = getChecked();
			$.ajax({
				type: "POST",
			    url: "pages/system/saveFunctionRoleAction.action",
			    data: "roleId="+$('#roleId').val()+"&userFunc="+fid,
			    dataType: "text",
			    success: function(data){
					$.messager.alert('提示',data);  
					if(data=='保存成功'){
						$('#w2').window('close');
					}
			    }
			});
		}
		function getChecked(){		
			var nodes = $('#tt2').tree('getChecked');	
			var s = '';
			var p = '';
			
			for(var i=0; i<nodes.length; i++){
				if (s != '') s += '|';
				s += nodes[i].id;
				var parent = $('#tt2').tree('getParent',nodes[i].target);
				if(parent!=null){//有父节点的
					if(!parent.checked){//判断是否已被勾选
						var parentFlag=false;
						var idArray = (p+s).split('|');
						for(var j=0;j<idArray.length;j++){
							if(idArray[j]==parent.id){
								parentFlag=true;
								
							}
						}
						
						if(!parentFlag){
							p+=parent.id+"|";
						}
						
						/*if(p.indexOf(parent.id)<0){
							p+=parent.id+"|";
						}*/
					}
				}
			}

			
			var idArray = (p+s).split('|');
			//alert(p);
			//alert(s);
			var flag = false;
			var userFunction = "";
			
			for(var i=0;i<idArray.length;i++){
				if (userFunction != '') userFunction += ',';
				userFunction+=idArray[i];
			}
			
			return userFunction;
		}
	</script>
</html>
