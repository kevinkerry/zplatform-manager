<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../top.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body class="easyui-layout" style="padding: 5px">
	
	<div region="west" title="用户栏" style="width: 250px; padding: 10px;" split="false">
		<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
			<li>
				<span>用户</span>
				<ul>
					<c:forEach var="organ" items="${organList}">
						<li state="closed">
							<span>${organ.organName}</span>
							<c:set var="organId" value="${organ.organId}"></c:set>
							<c:forEach var="dept" items="${deptList}">
									<c:if test="${dept.organId==organId}">
										<ul>
											<li state="closed">
												<span>${dept.deptName}</span>
												<c:set var="deptId" value="${dept.deptId}"></c:set>
												<ul>
													<c:forEach var="user" items="${userList}">
														<c:if test="${user.deptId==deptId}">
														 <li>
													      <span>
														      <a href="javascript:showUserFunction('${user.roleId }','${user.userId }')">
														      ${user.loginName }</a>
													      </span>
													     </li>
													     </c:if>
													</c:forEach>
												</ul>
											</li>
										</ul>
									</c:if>
									
								</c:forEach>
							
					    </li>
						
					</c:forEach>
				</ul>
			</li>
		</ul>
				
					
					
					
					
				
			</li>
			
		</ul>

	</div>
	<div region="center" title="权限栏" id="authMenu">
		<div style="margin-top:10px" id="menu_div">
			<ul id="tt2"></ul>
		
			<a href="javascript:submitUserAuth()" class="easyui-linkbutton" iconCls="icon-ok">提交</a>
			<a href="javascript:invokeFunction()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
		</div>
		
	</div>
	
	<div id="www" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:100px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
				<select id="munRole"></select>
				<input type="hidden" id="userId"/>
				<input type="hidden" id="roleId"/>
			</div>
			<div region="south" border="false" style="text-align:right;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="invokeFunction()">确定</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeView()">取消</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var w =$("#authMenu").width();
		$("#menu_div").css("margin-left",w/2.5+"px");
	})


	var roleFunction = null;
	function showUserFunction(roleId,userId){
		
		var flag = true;
		$('#userId').val(userId);
		$('#roleId').val(roleId);
		$('#tt2').tree({
			checkbox: true,
			animate:true,
			url: 'pages/system/queryFunctionUserAction.action?roleId='+roleId+'&userId='+userId,
			onCheck:function(node, checked){
				for(var i=0;i<roleFunction.length;i++){
					if(node.id==roleFunction[i].functId){
						if(checked==false){
							$('#tt2').tree('check',node.target);
						}else{
							
						}
					}
				}
				node.text = '<span style="color:blue">'+node.text+'</span>';
			},
			onBeforeExpand:function(node){
				var child = $('#tt2').tree('getChildren', node.target);
				if(child==""){
					return false;
				}
				
			},
			loadFilter: function(data){   
				if(flag){
					roleFunction = data.roleFunction;
					flag = false;
				}
		        return data.result;
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
		closeView();
		var w =$("#authMenu").width();
		$("#menu_div").css("margin-left",w/2.5+"px");
		//alert(w);
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
					if(p.indexOf(parent.id)<0){
						p+=parent.id+"|";
					}
					
					
				}
			}
		}
		var idArray = (p+s).split('|');
		var flag = false;
		var userFunction = "";
		for(var i=0;i<idArray.length;i++){
			for(var j=0;j<roleFunction.length;j++){
				if(idArray[i]==roleFunction[j].functId){
					flag = true;
				}
			}
			if(flag){//角色权限的Func_id
				flag = false;
			}else{//用户的权限
				if (userFunction != '') userFunction += ',';
				userFunction+=idArray[i];
			}
		}
		
		return userFunction;
	}
	
	
	function resize(){
		$('#www').window({
			title: '选择角色',
			top:200,
			width: 200,
			modal: true,
			shadow: false,
			minimizable:false,
			maximizable:false,
			closed: false,
			collapsible:false,
			height: 150
		});
	}

	function closeView(){
		$('#www').window('close')
	}

	function invokeFunction(){
		
		showUserFunction($("#roleId").val(),$('#userId').val())
	}

	function submitUserAuth(){
		var fid = getChecked();
		
		$.ajax({
			type: "POST",
		    url: "pages/system/saveAuthUserAction.action",
		    data: "userId="+$('#userId').val()+"&userFunc="+fid,
		    dataType: "text",
		    success: function(data){
				$.messager.alert('提示',data);  
		    }
		});
	}
	
</script>
</html>
