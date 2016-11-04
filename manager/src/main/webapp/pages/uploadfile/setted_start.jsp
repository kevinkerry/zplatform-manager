<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
		<div id="p" class="easyui-panel" title="结算"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post"
				action="pages/merchant/saveProcessUploadAction.action">
				<table width="100%">
					<tr align="center">
						<td><a class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:setted()" id="btn_submit" onclick="">开始结算</a></td>
					</tr>
				</table>
			</form>
		</div>


	</div>
</body>

<script>
	
	function setted() {

		 $.ajax({
			   type: "POST",
			   url: "pages/merchant/startSettedUploadAction.action",
			   data: "rand="+ new Date().getTime(),
			   dataType:"text",
			   success:function(json){
				   $.messager.alert('提示',json); 
			 	}
			}); 


    	
	}

</script>
</html>
