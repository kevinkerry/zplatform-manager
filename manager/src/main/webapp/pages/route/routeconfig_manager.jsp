<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
		<script type="text/javascript" src="js/checkboxbeautify/jquery-hcheckbox.js"></script>
		<link href='js/checkboxbeautify/css.css' rel="stylesheet" type="text/css" />
		<style type="text/css">
			#groupinfo {height:25px;}
			#groupinfo tr td{height:25px;border-style:solid;border-width:0px 0px 0px 0px;border-color:#000000;padding:1px}
			#groupinfo tr td input{height:20px;margin-left:3px;}
			#groupinfo tr td span{height:20px;margin-left:3px;}
			
			.activeflag_label{width:90px}
		</style>
</head>

	<body>
		<div style="margin: 5px; border: " id="continer">
			<div id="p" class="easyui-panel" title="路由配置信息查询"			
				style="height: 100px; padding: 10px; background: #fafafa;"
				iconCls="icon-save" collapsible="true">
				<form id="dedurateForm" method="post">
					<table width="100%">
						<tr>												
							<td align="right" width="15%">路由版本代码</td>
							<td align="left" style="padding-left: 5px" width="25%">
								<input name="routeConfigModel.merchroutver" id="merchRoutver_qid" class="easyui-validatebox"/>
							</td>
							
							<td align="right" width="15%">交易渠道</td>					
						    <td align="left" style="padding-left:5px" width="25%">
								<select id="routver_qid"  name="routeConfigModel.routver" class="easyui-validatebox" >								    								    								  
								<option value="">--请选择交易渠道--</option>
								</select>
						     </td>
						</tr>
						<tr>					
							<td align="right" width="15%">状态</td>
							<td align="left" style="padding-left: 5px" width="25%">
							    <select id="status_qid" name="routeConfigModel.status" class="easyui-validatebox" >
								    <option value="">--请选择--</option>
								    <option value="00">在用</option>
								    <option value="01">停用</option>								    
								</select>					
							</td>				
							<td align="right" colspan=1>
								<a href="javascript:search()" class="easyui-linkbutton"
									iconCls="icon-search">查询</a>
								
							</td>
						
							

					</table>
				</form>
			</div>
			<div style="margin-top: 5px">
				<table id="test"></table>
			</div>
		</div>
		<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;text-align: center">
				<form id="theForm"   method="post" action="pages/route/saveRouteConfigRouteAction.action" >
				<input name="routeConfigModel.rid" id="rid" type="hidden"/>
				<input name="routeConfigModel.status" id="status1" type="hidden"/>
				<table width="100%" cellpadding="2" cellspacing="2" style="text-align: left" id="groupinfo">
					<tr style="height: 10px">
					    <td align="left" colspan="2">
					    <font color="red">提示：开始时间、结束时间请按照HHmmss格式输入</font></td>					    
					</tr>
					
					<tr>
					    <td align="right" width="15%" height="20px" >路由版本</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<select id="merchroutver"   name="routeConfigModel.merchroutver" required="true" missingMessage="请选择路由版本"   class="easyui-validatebox" />
						       <option value="">--请选择路由版本--</option>						    
						    </select>	
						    <font color="red">*</font>		
						</td>
						
						<td align="right" width="15%" height="20px" >交易渠道</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<select id="routver"   name="routeConfigModel.routver" required="true" missingMessage="请选择交易渠道"   class="easyui-validatebox" />
						       <option value="">--请选择交易渠道--</option>						    
						    </select>	
						    <font color="red">*</font></td>		
						</td>
					
					</tr>
					
					<tr>
					    <td align="right" width="15%" height="20px" >开始时间</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeConfigModel.stime" id="stime" required="true" missingMessage="请填写开始时间"  maxlength="6" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>
						
						<td align="right" width="15%" height="20px" >结束时间</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeConfigModel.etime" id="etime" required="true" missingMessage="请填写结束时间" maxlength="6" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>		
					</tr>
					
					
					
					<tr>
					    <td align="right" width="15%" height="20px" >最小金额</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeConfigModel.minamt" id="minamt" required="true" missingMessage="请填写最小金额" maxlength="12" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>
						
						<td align="right" width="15%" height="20px" >最大金额</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeConfigModel.maxamt" id="maxamt" required="true"  missingMessage="请填写最大金额" maxlength="12" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>
					</tr>
					
					<tr>
					    <td align="right" width="15%" height="20px" >优先类型</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeConfigModel.ordertype" id="ordertype" required="true" missingMessage="请填写优先类型" maxlength="12" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>
						
						<td align="right" width="15%" height="20px" >优先级</td>						
						<td align="left" style="padding-left:5px" width="25%">
							<input name="routeConfigModel.orders" id="orders" required="true"  missingMessage="请填写优先级" maxlength="12" class="easyui-validatebox" />
						    <font color="red">*</font></td>
						</td>
					</tr>
					
					<tr>
						<td align="right" width="15%">是否为默认路由</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<select name="routeConfigModel.isdef" id="isdef" />
								<option value="">--请选择是否为默认路由--</option>
								<option value="0">默认路由</option>
								<option value="1">非默认路由</option>
						    </select>
							<font color="red">*</font></td>
						</td>
							 					   					
						<td align="right" width="15%">备注</td>
						<td align="left" style="padding-left: 5px" width="25%">
							<input name="routeConfigModel.notes" id="notes" maxlength="64"/>
						</td>									
					</tr>
					
					
					<tr style="height: 60px">					
					    <td>选择发卡行</td>
						<td  align="left"  id="bankcode"  style="height: 60px" colspan="3"> 						     							
	                	</td>
					</tr>
					<tr>
					    <td>选择卡种类</td>
					    <td align="left"  id="cardtype" colspan="3">
					    
					    </td>
					</tr>		
					<tr style="height: 60px">					
					    <td>选择交易类型</td>
						<td  align="left"  id="busicode"  style="height: 60px" colspan="3"> 						     							
	                	</td>
					</tr>
							
				</table>
				</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:saveRouteConfig()" id="btn_submit" onclick="">保存</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	</body>

	<script>
		var width = $("#continer").width();
	  	var gridHeight = 540;
		var panelWidth = 640;
		var panelHeight = 260;
		var panelHoriFloat = (width-panelWidth)/2;
		var panelVertFloat = 150;
	
		$(function() {
			//交易渠道查询
			queryChnlcode(); 
		
			$('#test').datagrid({
				title: '路由版本配置列表',  
				singleSelect: true,
				iconCls: 'icon-save',
				height: gridHeight,
				nowrap: false,
				striped: true,
				sortName: 'ROUTVER',
				url: 'pages/route/queryRouteConfigRouteAction.action',
				remoteSort: false,
				columns: [[				    
					{field: 'MERCHROUTVER',title: '路由版本代码',width: 100,align: 'center'},
				    {field: 'STIME',title: '开始时间',width: 100,align: 'center'},
				    {field: 'ETIME',title: '结束时间',width: 100,align: 'center'},
				    {field: 'MINAMT',title: '最小金额',width: 100,align: 'center'},
				    {field: 'MAXAMT',title: '最大金额',width: 100,align: 'center'},
				    {field: 'BANKCODE',title: '发卡行',width: 150,align: 'center'},
				    {field: 'ROUTVER',title: '交易渠道',width: 150,align: 'center'},
				    {field: 'CARDTYPE',title: '卡类型',width: 100,align: 'center'},
				    {field: 'ISDEF',title: '是否为默认路由',width: 100,align: 'center',
				    	formatter: function(value, rec){
				    		if(value == 0){
				    			return "默认路由";
				    		}else if(value == 1){
				    			return "非默认路由";
				    		}
				    	}
				    }, 				    
				    {field: 'STATUS',title: '状态',width: 100,align: 'center',
				    	formatter: function(value, rec){
				    		if(value == 00){
				    			return "在用";
				    		}else if(value == 01){
				    			return "停用";
				    		}
				    	}
				    },				
				    {field: 'RID',title: '操作',width: 150,align: 'center', 
						formatter: function(value, rec) {
							if(rec.STATUS ==00){                                                                                                   
								return '<a href="javascript:showRouteConfig(' + value + ')" style="color:blue;margin-left:10px">修改</a>&nbsp;&nbsp;<a href="javascript:deleteRouteConfig('+ value + ')" style="color:blue;margin-left:10px">注销</a>';
							}else if(rec.STATUS ==01){
								return '<a href="javascript:startRouteConfig(' + value + ')" style="color:blue;margin-left:10px">启用</a>';
							}
							
					}
				}]],
				pagination: true,
				rownumbers: true,
				toolbar: [{
					id: 'btnadd',
					text: '新增路由配置', 
					iconCls: 'icon-add',
					handler: function() {
						showAdd();
					}
				}]
			});
			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh: function() {
	
		}
			});
		});
	
		function search() {
			var data = {
				'routeConfigModel.merchroutver': $("#merchRoutver_qid").val(),
				'routeConfigModel.status':$("#status_qid").val(),
				'routeConfigModel.routver':$("#routver_qid").val()
			};
			$('#test').datagrid('load', data);
		}
	
		//保存按钮 
		  function saveRouteConfig() {
			  
		      if ($('#theForm').form("validate")) {
		          $("#button_id").linkbutton('disable');
		          $('#theForm').form('submit', {
		              onSubmit: function() {
		                  return $('#theForm').form('validate');
		              },
		              success: function(data) {
		            	  if (data == '添加成功!' || data == '修改成功!') {
								closeAdd();
								search();
							}  
		            	  $.messager.alert('提示', data);
		            	  $('#button_id').linkbutton('enable');
		              }
		          });
		      }
		  }
	
		//发卡行 复选框 
		function loadBank() {
			var mark = 0;
		  	var html = '';
		  	$.ajax({
		  		type: "GET",
		  		url: "pages/route/queryBankCodeRouteAction.action",
		  		data: "",
		  		dataType: "json",
		  		success: function(json) {
		  			$.each(json,
		  			function(key, value) {
		  				html += '<input type="checkbox" id="bankcodeList" name="bankcodeList" style="align:left" value="' + value.bankcode + '" /><label class="activeflag_label">' + value.bankname + '</label>';		  			
		  				if (mark == 3 || mark == 7|| mark == 11 ) {
		  					html += '<br/>';
		  				}
		  				mark = mark + 1;
		  			});
		  			$("#bankcode").html(html);
		  			$("#bankcode").hcheckbox();
		  		}
		  	});
		  }
		//卡类型复选框 
		function loadCradtype(){
			//var mark1 = 0;
			var html = '';
			html += '<input type="checkbox" id="cradtypeList" name="cradtypeList" style="align:left" value="' + 1 + '" /><label class="activeflag_label"> 借记卡 </label>';
			html += '<input type="checkbox" id="cradtypeList" name="cradtypeList" style="align:left" value="' + 2 + '" /><label class="activeflag_label"> 贷记卡 </label>';
		    $('#cardtype').html(html);
		    $('#cardtype').hcheckbox();
		}
		//得到所有交易渠道 
		function queryChnlcode() {
			$.ajax({
				type: "POST",
				url: "pages/route/queryChnlcodeRouteAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择交易渠道--</option>";
					$.each(json,function(key, value) {
						html += '<option value="' + value.paraCode + '">' + value.paraName + '</option>';
					});
					$("#routver").html(html);
					$("#routver_qid").html(html);
				}
			});
		}
		
		//得到所有交易类型 
		function queryBusicode() {
			var mark2 = 0;
		  	var html = '';
		  	$.ajax({
		  		type: "GET",
		  		url: "pages/route/queryAllBusicodeRouteAction.action",
		  		data: "",
		  		dataType: "json",
		  		success: function(json) {
		  			$.each(json,
		  			function(key, value) {
		  				html += '<input type="checkbox" id="busicodeList" name="busicodeList" style="align:left" value="' + value.busiCode + '" /><label class="activeflag_label">' + value.busiName + '</label>';		  			
		  				if (mark2 == 3 || mark2 == 7|| mark2 == 11 ) {
		  					html += '<br/>';
		  				}
		  				mark2 = mark2 + 1;
		  			});
		  			$("#busicode").html(html);
		  			$("#busicode").hcheckbox();
		  		}
		  	});		
		}
		//交易渠道复选框的显示　
		function queryChannelcode() {			
			$.ajax({
				type: "POST",
				url: "pages/route/queryChnlcodeRouteAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择交易渠道--</option>";
					$.each(json,function(key, value) {
						html += '<option value="' + value.paraCode + '">' + value.paraName + '</option>';
					});
					$("#channelcode").html(html);
				}
			});			
		}
		
		//得到所有 路由版本代码
		function queryAllRoutver(){
			$.ajax({
				type: "POST",
				url: "pages/route/queryAllRoutverRouteAction.action",
				dataType: "json",
				success: function(json) {
					var html = "<option value=''>--请选择路由版本--</option>";
					$.each(json,function(key, value) {
						html += '<option value="' + value.routver + '">' + value.routname + '</option>';
					});
					$("#merchroutver").html(html);
				}
			});
		}
		//新增路由版本 
		function showAdd() {
			$('#theForm').clearForm();					
			loadBank();	
			loadCradtype();
			queryAllRoutver();
			queryBusicode();
			queryChannelcode();
			$('#w').window({
				title: '路由配置信息',
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false,
				height: 500
				
			});	
			$("#theForm").attr("action", "pages/route/addRouteConfigRouteAction.action");
			$("#btn_submit").linkbutton("enable");
		}
		
		//保存 
		function saveRoute(){			
			$('#theForm').form('submit', {
				onSubmit: function() {
					if ($('#theForm').form('validate')) {
						$('#btn_submit').linkbutton('disable');
						return true;
					}
					return false;
				},
				success: function(data) {
					if (data == '添加成功!' || data == '修改成功!' ||data == '注销成功!') {
						$.messager.alert('提示', data);
						closeAdd();
						$('#btn_submit').linkbutton('enable');
						search();
					} else {
						$.messager.alert('提示', data);
						$('#btn_submit').linkbutton('enable');
					}
				}
			});
		}

		//取消
		function closeAdd() {
			$('#w').window('close');
		}

		//修改
		function showRoute(routid){
			$.ajax({
				type: "POST",
				url: "pages/route/queryOneRouteRouteAction.action",
				data: "routid=" + routid,
				dataType: "json",
				success: function(json) {
					$("#routid").val(routid);
					$("#routver").val(json.ROUTVER);
					$("#routver").attr("readonly","readonly");
					$("#routname").val(json.ROUTNAME);
					$("#notes").val(json.NOTES);										
				}	
			});
			$('#w').window({
				title: '修改路由版本',
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false,
				
			});
			$("#theForm").attr("action", "pages/route/updateRouteRouteAction.action");
			$('#btn_submit').linkbutton('enable');
		}
	    //注销 
		function deleteRoute(routid){
			$.ajax({
				type: "POST",
				url: "pages/route/queryOneRouteRouteAction.action",
				data: "routid=" + routid,
				dataType: "json",
				success: function(json) {
					$("#routid").val(routid);
					$("#routver").val(json.ROUTVER);
					$("#routver").attr("readonly","readonly");
					$("#routname").val(json.ROUTNAME);
					$("#routname").attr("readonly","readonly");
					$("#notes").val(json.NOTES);										
				}	
			});
			$('#w').window({
				title: '注销路由版本', 
				top: panelVertFloat,
		  		left: panelHoriFloat,
		  		width: panelWidth,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false
			});
			$("#theForm").attr("action", "pages/route/deleteRouteRouteAction.action"); 
			$('#btn_submit').linkbutton('enable');
	    }
	    //注销 
		function deleteRouteConfig(routid){
			$.messager.confirm('提示', '您是否想要注销此路由配置?',
					function(r) {
						if (r) {
							$.ajax({
								type: "GET",
								url: "pages/route/deleteRouteConfigRouteAction.action",
								data: "routid=" + routid,
								dataType: "text",
								success: function(text) {
									$.messager.alert('提示', text);
									search();
								}
							});
			
						}
					});
		}
	    //启用 
	    function startRouteConfig(routid){
	    	$.messager.confirm('提示', '您是否想要启用此路由配置?', 
					function(r) {
						if (r) {
							$.ajax({
								type: "GET",
								url: "pages/route/startRouteConfigRouteAction.action",
								data: "routid=" + routid,
								dataType: "text",
								success: function(text) {
									$.messager.alert('提示', text);
									search();
								}
							});
			
						}
					});
	    }
	
	    //修改 
	    function showRouteConfig(rid){
			loadBank();	
			loadCradtype();
			queryAllRoutver();
			queryBusicode();
			queryChannelcode();
	    	$.ajax({
				type: "POST",
				url: "pages/route/queryOneRouteConfigRouteAction.action",
				data: "rid=" + rid,
				dataType: "json",
				success: function(json) {					
					
					
					$("#merchroutver").val(json.MERCHROUTVER);
					$("#stime").val(json.STIME);
					$("#etime").val(json.ETIME);
					$("#minamt").val(json.MINAMT);
					$("#maxamt").val(json.MAXAMT);
					$("#bankcode").val(json.BANKCODE);
					$("#cardtype").val(json.CARDTYPE);
					$("#busicode").val(json.BUSICODE);
					$("#routver").val(json.ROUTVER);
					$("#ordertype").val(json.ORDERTYPE);
					$("#orders").val(json.ORDERS);
					$("#isdef").val(json.ISDEF);
					$("#notes").val(json.NOTES);
					$("#status1").val(json.STATUS);
					setTimeout(function() {
						$("#rid").val(json.RID);
					},
					500);
					containBusicode(rid);
					containBank(rid);
					containCardtype(rid);
					
				}
	
			});
			$('#w').window({
				title: '修改路由配置信息',
				top: panelVertFloat, 
		  		width: panelWidth,
		  		left:panelHoriFloat,
		  		height: panelHeight,
				collapsible: false,
				minimizable: false,
				maximizable: false,
				modal: true,
				shadow: false,
				closed: false,
				height: 500
			});
			$("#theForm").attr("action", "pages/route/updateOneRouteConfigRouteAction.action");
			$('#btn_submit').linkbutton('enable');
	    }
	    
	    //包含的发卡行  
	    function containBank(rid) {
	        var mark4 = 0;
	        var html = '';
	        $.ajax({
	            type: "GET",
	            url: "pages/route/queryContainBankRouteAction.action?rid=" + rid,
	            dataType: "json",
	            success: function(json) {
	                $.each(json,
	                function(key, value) {
	                    if (value.FLAG == 'n') {
	                        html += '<input type="checkbox" id="bankcodeList" name="bankcodeList" style="width:40px" value="' + value.BANKCODE + '" /><label class="activeflag_label">' + value.BANKNAME + '</label>' ;
	                    } else {
	                        html += '<input type="checkbox" id="bankcodeList" name="bankcodeList" checked="checked"   style="width:40px" value="' + value.BANKCODE + '" /><label class="activeflag_label">' + value.BANKNAME + '</label>' ;
	                    }
	                    if (mark4 == 3 || mark4 == 7) {
	                        html += '</br>'
	                    }
	                    mark4 = mark4 + 1;
	                });
	                $("#bankcode").html(html);
	                $('#bankcode').hcheckbox();			
	                $('#bankcode').children('#bankcodeList').each(function() {
	                    if ($(this).attr("checked") == "checked") {	                       
	                    } else {
	                        $(this).removeAttr("disabled");
	                    }
	                });
	            }
	        });
	    }
	    
	    //包含的卡种类 
	    function containCardtype(rid) {
	        var mark5 = 0;
	        var html = '';
	        $.ajax({
	            type: "GET",
	            url: "pages/route/queryContainCardtypeRouteAction.action?rid=" + rid,
	            dataType: "json",
	            success: function(json) {
	                $.each(json,
	                function(key, value) {
	                    if (value.CONTAIN1 == 'NO') {
	                        html += '<input type="checkbox" id="cradtypeList" name="cradtypeList" style="width:40px" value="'+ 1 +'" /><label class="activeflag_label">借记卡</label>' ;
	                    } else if(value.CONTAIN1 == 'YES'){
	                        html += '<input type="checkbox" id="cradtypeList" name="cradtypeList" checked="checked"   style="width:40px" value="' + 1 + '" /><label class="activeflag_label"> 借记卡</label>' ;
	                    }
	                    if (value.CONTAIN2 == 'NO') {
	                        html += '<input type="checkbox" id="cradtypeList" name="cradtypeList" style="width:40px" value="' + 2 +'" /><label class="activeflag_label">贷记卡</label>' ;
	                    } else if(value.CONTAIN1 == 'YES'){
	                        html += '<input type="checkbox" id="cradtypeList" name="cradtypeList" checked="checked"   style="width:40px" value="'+ 2 +'" /><label class="activeflag_label">贷记卡</label>' ;
	                    }
	                    if (mark5 == 3 || mark5 == 7) {
	                        html += '</br>'
	                    }
	                    mark5 = mark5 + 1;
	                });
	                $("#cardtype").html(html);
	                $('#cardtype').hcheckbox();			
	                $('#cardtype').children('#cradtypeList').each(function() {
	                    if ($(this).attr("checked") == "checked") {	                       
	                    } else {
	                        $(this).removeAttr("disabled");
	                    }
	                });
	            }
	        });
	    }
	    
	    function containBusicode(rid){
	        var mark = 0;
	        var html = '';
	        $.ajax({
	            type: "GET",
	            url: "pages/route/queryContainBusicodeRouteAction.action?rid=" + rid,
	            dataType: "json",
	            success: function(json) {
	                $.each(json,
	                function(key, value) {
	                    if (value.FLAG == 'n') {
	                        html += '<input type="checkbox" id="busicodeList" name="busicodeList" style="width:40px" value="' + value.BUSICODE + '" /><label class="activeflag_label">' + value.BUSINAME + '</label>' ;
	                    } else {
	                        html += '<input type="checkbox" id="busicodeList" name="busicodeList" checked="checked"   style="width:40px" value="' + value.BUSICODE + '" /><label class="activeflag_label">' + value.BUSINAME + '</label>' ;
	                    }
	                    if (mark == 3 || mark== 7) {
	                        html += '</br>'
	                    }
	                    mark = mark + 1;
	                });
	                $("#busicode").html(html);
	                $('#busicode').hcheckbox();			
	                $('#busicode').children('#busicodeList').each(function() {
	                    if ($(this).attr("checked") == "checked") {	                       
	                    } else {
	                        $(this).removeAttr("disabled");
	                    }
	                });
	            }
	        });
	    }
</script>
</html>

