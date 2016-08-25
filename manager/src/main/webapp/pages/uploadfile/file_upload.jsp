<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<style>
table tr td {
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 4px;
	border-style: solid;
	border-color: #000000;
	height: 26px;
}

table tr td input {
	height: 15px;
}

.type-file-box {
	position: relative;
	width: 260px
}

input {
	vertical-align: middle;
	margin: 2;
	padding: 2;
}

.type-file-text {
	margin-left: 2px, height: 18px;
	border: 1px solid #cdcdcd;
	width: 180px;
}

.type-file-button {
	background-color: #FFF;
	border: 1px solid #CDCDCD;
	height: 20px;
	width: 70px;
}

.type-file-file {
	position: absolute;
	margin-left: -260px;
	height: 20px;
	filter: alpha(opacity : 0);
	opacity: 0;
	width: 260px
}

.type-add-button {
	height: 20px;
}
</style>
<script type="text/javascript"
	src="js/multiplefileupload/multiplefileupload.js"></script>

</head>
<body>
	<div style="margin: 5px;" id="continer">
	
		<div style="padding-left: 5px; padding-right: 5px">
			<form id="downBillchanform" method="post"
				action="pages/merchant/downChanBilDownloadAction.action"
				>
				<table width="100%" border="1">
					<tr>
						<td colspan="4" align="center">畅捷对账文件下载</td>
					</tr>
					<tr height="26"  id="fileadd1">
						<td align="center">对账日期</td>
						<td colspan="3"><input name="billDate" class=''  maxlength="12"   type="text"  id="billDate"/>
					    </td>
					</tr>
					<tr>
						<td align="center" colspan="4" id="uploadbutton"><a 
							class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:downBillchan()">下载</a>
					    </td>
					</tr>
				</table>
			</form>
		</div>
		<div style="padding-left: 5px; padding-right: 5px">
			<form id="fileuploadform" method="post"
				action="pages/merchant/uploadUploadAction.action"
				enctype="multipart/form-data">
				<table width="100%" border="1">
					<tr>
						<td colspan="4" align="center">对账文件上传</td>
					</tr>

					<tr height="26" id="fileadd1">
						<td align="center">对账文件</td>
						<td colspan="3"><input type='text' name='textfield'
							id='textfield' class='type-file-text' /> <input type='button'
							name='button' id='button' value='浏览...' class='type-file-button' />
							<input name="upload" type="file" class="type-file-file"
							id="upload" size="25" /> <!-- <input type="button" id="addFileSelectButton" class="type-add-button" value="更多文件"/>  -->
						</td>
					</tr>					
					<tr height="26" id="fileadd1">
						<td align="center">对账机构</td>
						<td colspan="3"><select id="instiid_ins"
							class="easyui-validatebox" name="instiid">
								<!--  <option value=''>请选择</option>
								<option value='96000001'>融宝快捷支付</option>
								<option value='98000001'>证联支付</option>
								<option value='97000001'>中信网银</option>
								<option value='93000003'>民生本行代扣</option>-->

						</select></td>
					</tr>
					<tr>
						<td align="center" colspan="4" id="uploadbutton"><a
							class="easyui-linkbutton" iconCls="icon-ok"
							href="javascript:fileUpload()">上传</a> <a
							class="easyui-linkbutton" iconCls="icon-back"
							href="javascript:back()">返回</a></td>
					</tr>
					<tr>
						<td align="center" colspan="4" id="uploadhint"><img
							alt="正在上传" src="pictuer/loading.gif">文件正在上传,请耐心等待...</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	 <div style="padding-left: 5px; padding-right: 5px">
			<table width="100%" border="1">
				<tr>
					<td colspan="4" align="center">微信对账</td>
				</tr>
				<tr height="26" id="fileadd1">
					<td align="center">对账日期</td>
<<<<<<< HEAD
					<td >
=======
					<td colspan="3">
>>>>>>> branch 'develop' of root@192.168.101.11:zplatform-manager
						<input name="billdate" maxlength="12"   type="text"  id="startDate"/>
					</td>
					<td align="center">对账类型</td>
					<td >
						<select name="wechatType" id="wechatType">
							<option>--请选择对账类型--</option>
							<option value="APP">APP支付</option>
							<option value="QR">扫码支付</option>
						</select>
					</td>
				</tr>
				<tr height="26" id="fileadd1">
						<td align="center">对账机构</td>
						<td colspan="3"><select id="instiid_wechat"
							class="easyui-validatebox" >
							  <option value=''>请选择</option>
								<option value='app_wechat'>App支付</option>
								<option value='code_wechat'>扫码支付</option>
						</select></td>
					</tr>
				<tr>
					<td align="center" colspan="4" id="uploadbutton"><a
						class="easyui-linkbutton" iconCls="icon-ok"
						href="javascript:billFileUpload()">开始</a> <a
						class="easyui-linkbutton" iconCls="icon-back"
						href="javascript:back()">返回</a></td>
				</tr>
			</table>
	</div> 


</body>
<script type="text/javascript">
	function queryChannel() {

		// $('#billDate').datebox('getValue');
		// $('#startDate').datebox('getValue');
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
	$(document).ready(function() {
		queryChannel();
		$("#button").click(function() {
			$("#upload").trigger('click');
		});

		$("#upload").change(function() {
			$("#textfield").val($("#upload").val());
		});

		$("#uploadbutton").show();
		$("#uploadhint").hide();

		var fileInputList = new FileInputList();
		$("#addFileSelectButton").click(function() {
			fileInputList.add("upload", "addFileSelectButton")
		});
		 $('#billDate').datebox({  
		    required:true
	      }); 
	      
	      $('#startDate').datebox({   
	         }); 
		 
		
	});

	function fileUpload() {
		if ($("#instiid_ins").val() == "") {
			alert("请选择对账机构");
		} else {

			var filenullflag = false;
			$("input[name=upload]").each(function() {
				if (this.value == null || this.value == '') {
					filenullflag = true;
					return;
				}
			});
			if (filenullflag) {
				$.messager.alert('提示', '请选择上传文件');
				return;
			}

			$('#fileuploadform').form('submit', {
				onSubmit : function() {
					$("#uploadbutton").hide();
					$("#uploadhint").show();
					return $('#fileuploadform').form('validate');

				},
				success : function(json) {
					if(json!=null ||json!=""){
						json = eval('(' + json + ')');
						if (json.info == "对账文件上传成功！" || json.info == "") {
							alert("操作成功!");

							setTimeout(function() {
								back();

							}, 500);
						} else {
							$("#uploadbutton").show();
							$.messager.alert('提示', json.info);
							$("#uploadbutton").show();
							$("#uploadhint").hide();
						}
					}
					
				}
			});
		}

	}
	
	

	function back() {
		var form = document.forms['fileuploadform'];
		form.action = "pages/merchant/showUploadAction.action";
		form.submit();
	}
	
	function billFileUpload(){
		if($('#startDate').datebox('getValue')==""||$('#startDate').datebox('getValue')==null){
			$.messager.alert('提示', '请选择对账日期');
			return;
		}
		if ($("#instiid_wechat").val() == ""||$("#instiid_wechat").val() == null) {
			$.messager.alert('提示', '请选择对账机构');
			return;
		}
		if($("#wechatType").val()==""||$("#wechatType").val()==null){
			$.messager.alert('提示', '请选择对账类型');
		}
		$.ajax({
			type : "POST",
			url : "pages/merchant/downWeChatBillUploadAction.action",
			data : "billDate="+ $('#startDate').datebox('getValue')+"&instiid="+$("#instiid_wechat").val(),
			dataType : "json",
			success : function(json) {
				$.messager.alert('提示', json.info);
			}
		});
		
		
	}
	
	
	
	function downBillchan() {
		
		$('#downBillchanform').form('submit', {
			onSubmit : function() {
				if($('#downBillchanform').form('validate')){
					//校验日期，大于当前日期
					var billDate =new Date($('input[name="billDate"]').val().replace(/-/g,"/"));
					var myDate = new Date();
					var result=myDate.getFullYear()+'/'+(myDate.getMonth()+1)+'/'+myDate.getDate() ;
					var curDate=new Date(result);
					if(billDate-curDate>=0){
						alert("对账日期只能选择当前日期之前");
						return false;
					}
					
					$("#downBillchanButton").hide();
					return;
				}

			},
			success : function(json) {
				$("#downBillchanButton").show();
			},
			error: function(e) { 
				alert("下载失败！请联系管理员！"); 
			} 
		});
		

	}
</script>
</html>
