<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../../top.jsp"></jsp:include>
<body class="easyui-layout" style="padding: 5px">
	
	<div region="west" title="角色栏" style="width: 250px; padding: 10px;" split="false">
		<ul id="tt1" class="easyui-tree" animate="true" dnd="true">
			<li>
				<span>角色</span>
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
													<c:forEach var="role" items="${roleList}">
														<c:if test="${role.deptId==deptId}">
														 <li>
													      <span>
														      <a href="javascript:roleQueryFunction('${role.roleId }')">
														      ${role.roleName }</a>
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

	</div>
<input type="hidden" id="roleId"/>
	<div region="center" title="权限栏" id="authMenu">
	
		<div style="margin: 5px" id="menu_div">
			<ul id="tt2"></ul>
			
		    <a href="javascript:submitUserAuth()" class="easyui-linkbutton" iconCls="icon-ok">提交</a>
		    <a href="javascript:invokeFunction()" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var w =$("#authMenu").width();
	$("#menu_div").css("margin-left",w/2.5+"px");
})
	var roleFunction = null;
	function roleQueryFunction(roleId){
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
  function invokeFunction(){
		var	roleId = $("#roleId").val()
		roleQueryFunction(roleId)
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
