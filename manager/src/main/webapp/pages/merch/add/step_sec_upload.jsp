<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../../top.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</head>
<link href="<%=basePath%>js/uploadify/uploadify.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>js/uploadify/jquery.uploadify.min.js"></script>
<style type="text/css">
table {
	
}

table tr td {
	height: 25px;
	padding-left: 10px;
	border-width: 1px 1px 1px 1px;
	border-style: groove;
}

table tr td input {
	height: 15px
}

table tr td.head-title {
	background-color: #F0F8FF;
	font-weight: bold;
}

table tr td.head-guide {
	background-color: #F0FFFF;
	font-weight: bold;
}

table tr td font.current-step {
	color: #EE4000;
}
</style>
<script type="text/javascript"
	src="<%=basePath%>js/extendsValidator_1.0_20151215.js"></script>
</head>
<body>
	<div style="margin: 5px;" id="continer">
		<div id="title" name="title" class="easyui-panel" title="商户开通"
			style="background: #fafafa;" iconCls="icon-save" collapsible="false">
			<div style="padding-left: 5px; padding-right: 5px">
				<form id="merchDateForm"
					action="pages/merchant/commitMerchMerchantAction.action"
					method="post">
					<input type="hidden" id="merchApplyId" value="${merchApplyId}" />
					<input type="hidden" id="merchId" name="merchDeta.merchId"
						value="${merchDeta.merchId}" /> <input type="hidden"
						id="busilice_old" value="${merchDeta.member.licenceFile}" /> <input
						type="hidden" id="orgCert_old"
						value="${merchDeta.member.orgCodeFile}" /> <input type="hidden"
						id="corpfileFace_old" value="${merchDeta.member.corpFile}" /> <input
						type="hidden" id="corpfileOpp_old"
						value="${merchDeta.member.corpFileOpp}" /> <input type="hidden"
						id="signfileFace_old" value="${merchDeta.member.signCertFile}" />
					<input type="hidden" id="signfileOpp_old"
						value="${merchDeta.member.signCertFileOpp}" /> <input
						type="hidden" id="taxRegCert_old"
						value="${merchDeta.member.taxFile}" /> <input type="hidden"
						id="isde" value="${merchDeta.member.isDelegation}" />
					<table width="100%">
						<tr>
							<td colspan="4" class="head-guide">第一步:企业信息录入----><font
								class="current-step"> 第二步:上传证件照片</font></td>
						</tr>
						<tr>
							<td colspan="4" class="head-title"></td>
						</tr>
						<tr>
							<td align="center" width="20%">企业名称</td>
							<td width="30%">${merchDeta.member.enterpriseName}</td>
							<td align="center" width="20%">商户号</td>
							<td width="30%">${merchDeta.memberId}</td>
						</tr>
						<tr>
							<td align="center">企业法人营业执照</td>
							<td><input style="height: 30px" id="busilice_cert_img"
								type="file" name="headImage" class="easyui-validatebox">
								<div id="busilice_span" style="float: left;"></div> <!--  <a style="color:blue" id="licencefile_ins" href="javascript:showUpload('licenceno')">点击上传</a><font color="red">*</font>-->
							</td>
							<td align="center">组织机构代码证</td>
							<td><input style="height: 30px" id="orgCert_cert_img"
								type="file" name="headImage" class="easyui-validatebox">
								<span id="orgCert_span"></span> <!-- <a style="color:blue" id="orgcodefile_ins"  href="javascript:showUpload('orgcode')">点击上传</a> <font color="red">*</font>-->
							</td>
						</tr>
						<tr>
							<td align="center">法人身份证正面照</td>
							<td><input style="height: 30px" id="corpfileFace_cert_img"
								type="file" name="headImage" class="easyui-validatebox">
								<span id="corpfileFace_span"></span></td>
							<td align="center" id="psamORpass">法人身份证背面照</td>
							<td><input style="height: 30px" id="corpfileOpp_cert_img"
								type="file" name="headImage" class="easyui-validatebox">
								<span id="corpfileOpp_span"></span></td>
						</tr>
						<s:if test="merchDeta.member.isDelegation==1">
							<tr>
								<td align="center" id="psamORpass">委托人身份证正面照</td>
								<td><input style="height: 30px" id="signfileFace_cert_img"
									type="file" name="headImage" class="easyui-validatebox">
									<span id="signfileFace_span"></span></td>
								<td align="center" id="psamORpass">委托人身份证背面照</td>
								<td><input style="height: 30px" id="signfileOpp_cert_img"
									type="file" name="headImage" class="easyui-validatebox">
									<span id="signfileOpp_span"></span></td>
							</tr>
						</s:if>
						<tr>
							<td align="center">税务登记证文件目录</td>
							<td><input style="height: 30px" id="taxRegCert_cert_img"
								type="file" name="headImage" class="easyui-validatebox">
								<span id="taxRegCert_span"></span></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</form>

			</div>
			<div region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a href="javascript:toFirstStep();" class="easyui-linkbutton"
					iconCls="icon-back">上一步</a> <a class="easyui-linkbutton"
					iconCls="icon-ok" id="button_id" href="javascript:commitMerch()"
					onclick="">提交申请</a>
			</div>
		</div>
	</div>
</body>

<script>
  $(function(){
	  $("input[id*='_cert_img']").each(function(){
		  var _this = $(this);
		  var id = _this.attr('id');
		  var certType = id.substring(0,id.indexOf('_cert_img'));
		  $(this).uploadify({
				'auto' : true,
				'swf' : '<%=basePath%>js/uploadify/uploadify.swf', 
				'uploader': '<%=basePath%>/pages/merchant/uploadMerchantAction.action',
				'fileObjName': 'headImage',
				'formData' : {'merchApplyId':$('#merchApplyId').val(),'certTypeCode':certType},
				 'method'   : 'post',
				//可选  
				'height': 20,
				//可选  
				'width': 120,
				//设置允许上传的文件格式  
				//'fileExt'   : '*.jpg;*.gif;*.png',  
				//设置允许上传的文件格式后，必须加上下面这行代码才能帮你过滤  
				//'fileDesc'    : 'Image Files',  
				//允许连续上传多个文件  
				'multi': 'false',
				//一次性最多允许上传多少个,不设置的话默认为999个  
				'queueSizeLimit': 1,
				//每个文件允许上传的大小(字节)  
				'sizeLimit'   : 1024*1024*10,  //1M=1024K=1024*1024Byte
				'buttonText'     : '选择文件' ,
				'onUploadError' : function(file, errorCode, errorMsg, errorString) {
					$.messager.alert('提示', '上传失败');
		        },
				 'onUploadSuccess' : function(file, data, response) {
			         	var isSucc = false;   
			         	var retInfo;
					 	if(!response||data.indexOf("status")==-1){
					 		isSucc = false; 
			            }else{
				            jsonRet = eval('(' + data + ')');
				            if(jsonRet.status=='OK'){
				            	isSucc = true;  
				            }
			            }
					 	if(isSucc){
					 		retInfo = "上传成功";
					 		updateFileUrl(certType);
					 	}else{retInfo = "上传失败";}
			            $.messager.alert('提示', retInfo);
			    }, 
		        'onFallback' : function() {
		        	$.messager.alert('提示', '检测到flash控件不可用,请确认当前浏览器支持flash控件');
		        }
			});
	});
	initCertUrl();
  });
	
	function toFirstStep(){
		window.location.href= "<%=basePath%>" +'/pages/merchant/toMerchChangeMerchantAction.action?merchApplyId='+$('#merchApplyId').val();
	}
	
	function commitMerch(){
			$.ajax({
				type: "POST",
				url: "pages/merchant/commitMerchMerchantAction.action",
				data: "rand=" + new Date().getTime()+"&merchApplyId="+$('#merchApplyId').val(),
				dataType: "json",
				success: function(json) {
					if (json.status == "OK") {
						$.messager.alert('提示','开通申请提交成功','info',function(){
							window.location.href = '<%=basePath%>pages/merchant/showMerchantAction.action';
						});
					} else {
						$.messager.alert('提示', '开通申请提交失败'); 
					}
				}
			});
	}
	
	function initCertUrl(){
		$("input[id*='_old']").each(function(){
			var _this = $(this);
			if(_this.val()==''){
				return;
			}
			var id = _this.attr('id');
			var certType = id.substring(0,id.indexOf('_old'));
			var certSpan = $('#'+certType+'_span');
			$.ajax({
				type: "POST",
				url: "pages/merchant/downloadImgUrlMerchantAction.action",
				data: "merchApplyId=" + $('#merchApplyId').val()+"&certTypeCode="+certType,
				dataType: "json",
				success: function(json) {
					 if(json.status=='OK'){
						 certSpan.html('<a href="<%=basePath%>'+json.url+'" target="view_window" style="font-size: 12px;color:blue">点击查看</a>');
					 }else if(json.status=='notExist'){
						 certSpan.html('暂无可查看文件');
					 } else{
						 certSpan.html('查询失败');
					 }
				}
			}); 
		});
	}
	
	function updateFileUrl(certType){
		var certSpan = $('#'+certType+'_span');
		$.ajax({
			type: "POST",
			url: "pages/merchant/downloadImgUrlMerchantAction.action",
			data: "merchApplyId=" + $('#merchApplyId').val()+"&certTypeCode="+certType+"&fouceDownload=fouce",
			dataType: "json",
			success: function(json) {
				 if(json.status=='OK'){
					 certSpan.html('<a href="<%=basePath%>'+json.url+'" target="view_window" style="font-size: 12px;color:blue">点击查看</a>');
				 }else if(json.status=='notExist'){
					 certSpan.html('暂无可查看文件');
				 } else{
					 certSpan.html('查询失败');
				 }
			}
		});  
	}
	</script>
</html>
