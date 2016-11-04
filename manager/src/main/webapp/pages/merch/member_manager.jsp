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


	<div id="w" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="saveForm" action="" method="post">
					<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo">
						<tr style="height: 25px">
							<td align="center" width="100px">冻结账号:</td>
							<td align="left"><span id="acccode"></span> <input
								id="accId" type="hidden" name="account.accId"></input></td>

						</tr>

						<tr style="height: 25px">
							<td align="center" width="74px">冻结额度:</td>
							<td align="left"><input type="text" id="moneys"
								onblur="check()" name="account.frozenBalance"
								class="easyui-validatebox" required="true" maxlength="32" /><span>可用额度为:<font
									id="money"></font>元
							</span></td>
						</tr>
						<tr style="height: 60px">
							<td>冻结开始时间:</td>
							<td align="left" id="intime"><input id="startTime"
								type="text" name="account.startTime" style="width: 120PX"
								class="easyui-datetimebox" data-options="showSeconds:false"></input>
								冻结结束时间 : <input id="endTime" type="text" name="account.endTime"
								style="width: 120PX" class="easyui-datetimebox"
								data-options="showSeconds:false"></input></td>

						</tr>


						<tr>
							<td>备注</td>
							<td align="left" colspan="3"><textarea id="group_notes_ins"
									rows="2" cols="75" name="account.notes" maxlength="64"></textarea>
							</td>
						</tr>



					</table>
				</form>
			</div>
			<div id="div_id" region="south" border="false"
				style="text-align: center; padding: 5px 0;">
				<a class="easyui-linkbutton" id="button_id" iconCls="icon-ok"
					href="javascript:saveParaDic()" onClick="">提交</a> <a
					class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)" onClick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
	<input type="hidden" id="corpfile_ins" />
	<input type="hidden" id="taxfile_ins" />
	<input type="hidden" id="licencefile_ins" />
	<input type="hidden" id="orgcodefile_ins" />
	<input type="hidden" id="merchid_ins" />
	<input type="hidden" id="memberid_ins" />
	<input type="hidden" id="merchname_ins" />
	<input type="hidden" id="flag_ins" />
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 100px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<td align="right" width="15%">会员编号</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							name="memberId" id="memberId" maxlength="15" /></td>
						<td align="right" width="15%">会员名称</td>
						<td align="left" style="padding-left: 5px" width="25%"><input
							name="memberName" id="memberName" /></td>
						<td align="right"><a href="javascript:search()"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>

				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>

	</div>

	<div id="ws" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 800px; height: 400px; padding: 5px;">
		<input type="hidden" id="mId"> <input type="hidden" id="type">
		<input type="hidden" id="memId">
		<div id="tt" class="easyui-tabs" style="width: 1100px; height: 300px">
			<div title="基本信息" id="jbxx" data-options="selected:true">

				<table style="width: 100%; height: 100%">
					<tr>
						<td align="center" width="20%">商户名称<font color="red">*</font></td>
						<td id="MERCHNAME"></td>
						<td align="center" width="20%">商户简称<font color="red">*</font></td>
						<td id="ALIAS"></td>
					</tr>
					<tr>
						<td align="center">商户英文名称</td>
						<td id="ENGNAME"></td>
						<td align="center">商户清算周期<font color="red">*</font></td>
						<td id="SETLNAME"></td>
					</tr>

					<tr>
						<td align="center">商户所属省<font color="red">*</font></td>
						<td id="PROVINCENAME"></td>
						<td align="center">商户清算类型<font color="red">*</font></td>
						<td id="SETLTYPENAME"></td>

					</tr>

					<tr>
						<td align="center">商户所属市<font color="red">*</font></td>
						<td id="CITYNAME"></td>
						<td align="center">营业执照号<font color="red">*</font></td>
						<td id="LICENCENO"></td>
					</tr>

					<tr>
						<td align="center">商户所属县<font color="red">*</font></td>
						<td id="STREETNAME"></td>
						<td align="center">税务登记号<font color="red">*</font></td>
						<td id="TAXNO"></td>

					</tr>
					<tr>
						<td align="center">所属行业</td>
						<td id="TRADENAME"></td>
						<td align="center">组织机构代码号<font color="red">*</font></td>
						<td id="ORGCODE"></td>
					</tr>

					<tr>
						<td align="center">商户网址</td>
						<td id="WEBSITE"></td>
						<td align="center">商户类型</td>
						<td id="MERCHTYPENAME"></td>
					</tr>


				</table>
			</div>
			<div title="联系人信息">
				<table style="width: 100%; height: 100%">
					<tr>
						<td align="center" id="psamORpass">法人姓名<font color="red">*</font></td>
						<td id="CORPORATION"></td>
						<td align="center">法人身份证号<font color="red">*</font></td>
						<td id="CORPNO"></td>
					</tr>
					<tr>
						<td align="center">联系人<font color="red">*</font></td>
						<td id="CONTACT"></td>
						<td align="center">联系人地址</td>
						<td id="CONTADDRESS"></td>
					</tr>
					<tr>
						<td align="center">联系人电话</td>
						<td id="CONTPHONE"></td>
						<td align="center">联系人职位</td>
						<td id="CONTTITLE"></td>
					</tr>
					<tr>
						<td align="center">联系人邮箱</td>
						<td id="CONTEMAIL"></td>
						<td align="center">客户来源</td>
						<td id="CUSTFROM"></td>
					</tr>
					<tr>
						<td align="center">客户经理</td>
						<td id="CUSTMGR"></td>
						<td align="center">客户经理部门</td>
						<td id="CUSTMGRDEPT"></td>
					</tr>
					<tr>
						<td align="center">签约人</td>
						<td id="SIGNATORY"></td>
						<td align="center">签约人电话</td>
						<td id="SIGNPHONE"></td>
					</tr>

				</table>
			</div>
			<div title="清算信息">
				<table style="width: 100%; height: 100%">
					<tr>
						<td align="center">开户行关键字检索</td>
						<td></td>
						<td align="center">开户行<font color="red">*</font></td>
						<td id="BANKNAME"></td>

					</tr>
					<tr>

						<td align="center">开户账号<font color="red">*</font></td>
						<td id="ACCNUM"></td>
						<td align="center">会员编号</td>
						<td id="MEMBERID"></td>
					</tr>
					<tr>

						<td align="center">开户名<font color="red">*</font></td>
						<td id="ACCNAME"></td>
						<td align="center">开户行所属省</td>
						<td id="BNKPROVINCENAME"></td>
					</tr>
					<tr>
						<td align="center">开户行所属市</td>
						<td id="BNKCITYNAME"></td>
						<td align="center">开户行所属县</td>
						<td id="BNKSTREETNAME"></td>
					</tr>
					<tr>
						<td align="center">保证金</td>
						<td id="DEPOSIT"><font color="red">元</font></td>
						<td align="center">服务费</td>
						<td id="CHARGE"><font color="red">元</font></td>
					</tr>
					<tr>
						<td align="center">合约开始日期</td>
						<td id="AGREEMT_START"></td>
						<td align="center">合约终止日期</td>
						<td id="AGREEMT_END"></td>

					</tr>

					<tr>
						<td align="center">邮编</td>
						<td id="POSTCODE"></td>
						<td align="center">邮箱</td>
						<td id="EMAIL"></td>
					</tr>
					<tr>
						<td align="center">身份证文件目录</td>
						<td>
							<div iconCls="" style="color: blue"
								onclick="showUpload('corpfile')">点击查看</div> <!--  <input  type="text" id="corpfile_ins" type="button" onclick="showUpload('corpfile')" > -->
						</td>
						<td align="center">税务登记证文件目录</td>
						<td>
							<div iconCls="" style="color: blue" onclick="showUpload('busi')">点击查看</div>
							<!--  <input  type="text" id="taxfile_ins" type="button" onclick="showUpload('busi')" >点击查看 -->
						</td>
					</tr>
					<tr>
						<td align="center">营业执照文件目录</td>
						<td>
							<div iconCls="" style="color: blue"
								onclick="showUpload('licenceno')">点击查看</div> <!--  <input  type="text" id="licencefile_ins" type="button" onclick="showUpload('licenceno')" >点击查看 -->
						</td>
						<td align="center">组织机构文件目录</td>
						<td>
							<div iconCls="" style="color: blue"
								onclick="showUpload('orgcode')">点击查看</div> <!-- <input  type="text" id="orgcodefile_ins" type="button" onclick="showUpload('orgcode')" >点击查看 -->
						</td>
					</tr>
					<tr>
						<td align="center">行政地区代码</td>
						<td id="ZONECODE"></td>
						<td align="center">产品<font color="red">*</font></td>
						<td id="PRDTNAME"></td>
					</tr>

					<tr>
						<td align="center">商户地址</td>
						<td id="ADDRESS"></td>
						<td align="center">商户密钥</td>
						<td>RSA</td>
					</tr>
					<tr>
						<%-- <td align="center">上级商户</td>
						<td>${merchMap.PARENT}</td> --%>
						<td align="center">收银台版本 <font color="red">*</font></td>
						<td id="CASHNAME"></td>
					</tr>
				</table>
			</div>

			<div title="扣率信息">
				<table width="100%" height="77" border="1" bgcolor="#D6D6D6">
					<tr>
						<td width="79" rowspan="3" align="center">交易类型</td>
						<td width="75" rowspan="3" align="center">扣率类型</td>
						<td width="75" rowspan="3" align="center">计费方式</td>
						<td width="64" rowspan="3" align="center">卡类型</td>
						<td width="68" rowspan="3" align="center">固定费用</td>
						<td colspan="9" align="center">扣率</td>
					</tr>
					<tr>


						<td colspan="3" align="center">阶段一</td>
						<td colspan="3" align="center">阶段二</td>
						<td colspan="3" align="center">阶段三</td>
					</tr>
					<tr>
						<td width="57" align="center">扣率(万分比)</td>
						<td width="59" align="center">最高</td>
						<td width="103" align="center">最低</td>
						<td width="72" align="center">扣率(万分比)</td>
						<td width="63" align="center">最高</td>
						<td width="82" align="center">最低</td>
						<td width="93" align="center">扣率(万分比)</td>
						<td width="95" align="center">最高</td>
						<td width="75" align="center">最低</td>
					</tr>
					<tbody id="fees">

					</tbody>
				</table>
			</div>

			<div title="风控信息">
				<table id="risk"></table>

			</div>
			<div title="路由信息">
				<table width="100%" height="20" border="1" bgcolor="#D6D6D6">
					<tr>
						<td width="100" align="center">路由版本</td>
						<td width="100" align="center">路由名称</td>
						<td width="100" align="center">收银代码</td>
						<td width="100" align="center">开始时间</td>
						<td width="100" align="center">结束时间</td>
						<td width="100" align="center">最小金额</td>
						<td width="100" align="center">最大金额</td>
						<td width="180" align="center">交易卡所属银行（发卡行）</td>
						<td width="100" align="center">卡类型</td>
						<td width="100" align="center">优先类型</td>
						<td width="100" align="center">优先级</td>
						<td width="100" align="center">是否默认</td>
					</tr>
					<tbody id="route">

					</tbody>



				</table>
			</div>
			<div title="账户信息">
				<table id="accounts"></table>
			</div>
		</div>
	</div>

	<div id="pres" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save"
		style="width: 1000px; height: 400px; padding: 5px;">
		<input type="hidden" id="personId"> <input type="hidden"
			id="type">
		<div id="pp" class="easyui-tabs" style="width: 1100px; height: 300px">
			<div title="基本信息" id="jbxx" data-options="selected:true">
				<table style="width: 100%; height: 100%">
					<tr>
						<td align="center" id="psamORpass">会员ID<font color="red">*</font></td>
						<td id="pMemberid"></td>
						<td align="center">性别<font color="red">*</font></td>
						<td id="sex"></td>
					</tr>
					<tr>
						<td align="center">QQ号<font color="red">*</font></td>
						<td id="qq"></td>
						<td align="center">详细地址</td>
						<td id="adress"></td>
					</tr>
					<tr>
						<td align="center">邮编</td>
						<td id="postcode"></td>
						<td align="center">证件类型</td>
						<td id="cardtype"></td>
					</tr>
					<tr>
						<td align="center">证件号码</td>
						<td id="cardno"></td>
						<td align="center">固定电话</td>
						<td id="telno"></td>
					</tr>
					<tr>
						<td align="center">传真号码</td>
						<td id="faxno"></td>
						<td align="center">问候</td>
						<td id="greeting"></td>
					</tr>
					<tr>
						<td align="center">状态</td>
						<td id="status"></td>
						<td align="center">绑定电话</td>
						<td id="bindPhone"></td>
					</tr>
					<tr>
						<td align="center">绑定邮箱</td>
						<td id="bindEmail"></td>
						<td align="center">写入人</td>
						<td id="pInuser"></td>
					</tr>
					<tr>
						<td align="center">写入时间</td>
						<td id="pIntime"></td>
						<td align="center">更新人</td>
						<td id="upuser"></td>
					</tr>
					<tr>
						<td align="center">更新时间</td>
						<td id="uptime"></td>
						<td align="center">是否vip</td>
						<td id="vipflag"></td>
					</tr>
				</table>

			</div>
			<div title="银行卡信息">
				<table id="bank"></table>

			</div>
			<div title="账户信息">
				<table id="account"></table>
			</div>

		</div>
	</div>
	<div id="w_view" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; text-align: center; vertical-align: middle">
				<br> <a id="filePath" href="merchant/1211131005124406.rar"
					target="view_window" style="font-size: 14px">查看</a>
			</div>
		</div>
	</div>
</body>

<script>
  	var width = $("#continer").width();
		$(function(){
			$('#test').datagrid({
				title:'会员信息列表',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/merchant/queryMerchByPageUploadAction.action?falg=merch',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'MEMBERID',title:'会员编号',width:120,align:'center'},
					{field:'MEMBERNAME',title:'商户姓名',width:150,align:'center'},
					{field:'MEMBERTYPE',title:'会员类型',width:100,align:'center',
						formatter:function(value,rec){
							if(value=="01"){
								return "个人";
							}else if(value=="02"){
								return "商户";
							}else if(value=="03"){
								return "企业";
							}
						}
					},

					{field:'MEMBERSTAT',title:'状态',width:100,align:'center',
						formatter:function(value,rec){
						if(value=="01"){
							return "未激活";
						}else if(value=="00"){
							return "正常";
						}else if(value=="02"){
							return "系统锁定";
						}
						}
					} ,	
					{field:'DEPT_ID',title:'操作',width:350,align:'center',
					formatter:function(value,rec){
						if(rec.MEMBERTYPE!="01"){
							return 	'<a href="javascript:memberOperation(1,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">冻结</a>&nbsp;&nbsp;<a href="javascript:memberOperation(2,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">止入</a>'
							+'<a href="javascript:memberOperation(3,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">止出</a>&nbsp;&nbsp;<a href="javascript:memberOperation(4,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">解止出</a>'
							+'<a href="javascript:memberOperation(5,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">解冻</a>&nbsp;&nbsp;<a href="javascript:memberOperation(6,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">解止入</a>'
							+'<a href="javascript:queryMerch(\''+rec.MEMID+'\',\''+rec.MEMBERSTAT+'\',\''+rec.MEMBERTYPE+'\',\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">详细信息</a>';
						}else{
							return 	'<a href="javascript:memberOperation(1,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">冻结</a>&nbsp;&nbsp;<a href="javascript:memberOperation(2,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">止入</a>'
							+'<a href="javascript:memberOperation(3,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">止出</a>&nbsp;&nbsp;<a href="javascript:memberOperation(4,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">解止出</a>'
							+'<a href="javascript:memberOperation(5,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">解冻</a>&nbsp;&nbsp;<a href="javascript:memberOperation(6,\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">解止入</a>'
							+'<a href="javascript:queryPerson(\''+rec.MEMID+'\',\''+rec.MEMBERSTAT+'\',\''+rec.MEMBERTYPE+'\',\''+rec.MEMBERID+'\')" style="color:blue;margin-left:10px">详细信息</a>';						
						}
						
					}
				   } 
					
				]],
				pagination:true,
				rownumbers:true
		
			});
	
		});
		

	    

		function search(){
			var data={'memberId':$('#memberId').val(),'memberName':$("#memberName").val()};
			$('#test').datagrid('load',data);
		}
		/*商户window*/
		function queryMerch(merchId,status,type,memberId){
		
			$('#ws').window({
				title: '商户会员信息',
				top:100,
				width: 1300,
				left:100,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 400
				
			});
			
			$("#mId").val(merchId);
			$("#type").val(type);
			$("#memId").val(memberId);
			baseMessage();
			risk(memberId);
			fee(memberId);	
			account(memberId);
			 route(memberId);	
		}
		
		/*个人window*/
		function queryPerson(personId,status,type,memberId){
			
			$('#pres').window({
				title: '个人会员信息',
				top:100,
				width: 1300,
				left:100,
				collapsible:false,
				minimizable:false,
				maximizable:false,
				modal: true,
				shadow: false,
				closed: false,
				height: 400
				
			});
			
			$("#personId").val(memberId);
			$("#type").val(type);
			basePerson();
			account(memberId);
			bank(memberId);
			
		}
		
		
		
		
		function baseMessage(){
			  var mid=$("#mId").val();
			    var type=$("#type").val();
			    var memid=$("#memId").val();
				$.ajax({
					   type: "POST",
					   url: "pages/acc/memberBaseMessageMemberMessageAction.action",
					   data: "mid="+mid,
					   dataType:"json",
					   success:function(json){
						   $("#MERCHNAME").html(json.MERCHNAME);
						   $("#ALIAS").html(json.ALIAS);
						   $("#ENGNAME").html(json.ENGNAME);
						   $("#SETLNAME").html(json.SETLNAME);
						   $("#PROVINCENAME").html(json.PROVINCENAME);
						   $("#SETLTYPENAME").html(json.SETLTYPENAME);
						   $("#CITYNAME").html(json.CITYNAME);
						   $("#LICENCENO").html(json.LICENCENO);
						   $("#STREETNAME").html(json.STREETNAME);
						   $("#TAXNO").html(json.TAXNO);
						   $("#TRADENAME").html(json.TRADENAME); 
						   $("#ORGCODE").html(json.ORGCODE);
						   $("#WEBSITE").html(json.WEBSITE);
						   $("#MERCHTYPENAME").html(json.MERCHTYPENAME);
					/*联系人信息*/
						   $("#CORPORATION").html(json.CORPORATION);
						   $("#CORPNO").html(json.CORPNO);
						   $("#CONTACT").html(json.CONTACT);
						   
						   $("#CONTADDRESS").html(json.CONTADDRESS);
						   
						   $("#CONTPHONE").html(json.PROVINCENAME);
						   $("#CONTTITLE").html(json.CONTTITLE);
						   
						   $("#CONTEMAIL").html(json.CONTEMAIL);
						   
						   $("#CUSTFROM").html(json.CUSTFROM);
						   
						   $("#CUSTMGR").html(json.CUSTMGR);
						   
						   $("#CUSTMGRDEPT").html(json.CUSTMGRDEPT);
						   
						   $("#TRADENAME").html(json.TRADENAME); 
						   $("#SIGNATORY").html(json.SIGNATORY);
						   $("#SIGNPHONE").html(json.SIGNPHONE);
							/*清算信息*/
						   $("#BANKNAME").html(json.BANKNAME);
						   $("#ACCNUM").html(json.ACCNUM);
						   $("#MEMBERID").html(json.MEMBERID);
						   $("#ACCNAME").html(json.ACCNAME);
						   
						   
						   $("#BNKPROVINCENAME").html(json.BNKPROVINCENAME);
						   
						   $("#BNKCITYNAME").html(json.BNKCITYNAME);
						   
						   $("#BNKSTREETNAME").html(json.BNKSTREETNAME);
						   
						   $("#DEPOSIT").html(json.DEPOSIT);
						   
						   $("#CHARGE").html(json.CHARGE);
						   
						   $("#AGREEMT_START").html(json.AGREEMT_START);
						   
						   $("#AGREEMT_END").html(json.AGREEMT_END); 
						   $("#POSTCODE").html(json.POSTCODE);
						   
						   $("#EMAIL").html(json.EMAIL);
						   
						   $("#ZONECODE").html(json.ZONECODE);
						     $("#PRDTNAME").html(json.PRDTNAME);
							   $("#ADDRESS").html(json.ADDRESS);
							     $("#CASHNAME").html(json.CASHNAME);
								 
					 	
							     
							     $("#corpfile_ins").val(json.CORPFILE);
								   $("#taxfile_ins").val(json.TAXFILE);
								     $("#licencefile_ins").val(json.LICENCEFILE);
									   $("#orgcodefile_ins").val(json.ORGCODEFILE); 
									   $("#merchid_ins").val(json.MERCHID);  
									   $("#memberid_ins").val(json.MEMBERID);
									     $("#merchname_ins").val(json.MERCHNAME);
					   
					   
					   
					   }
					});
		}
		
	 	$('#tt').tabs({
		    border:false,
		    onSelect:function(title){
		/*商户merchId */
		   	var mid=$("#mId").val();
		/*会员状态*/   
		var type=$("#type").val();
		/*memberId*/   
		var memid=$("#memId").val();
		    if(type==02){
		    if(title=="基本信息"){
		    	baseMessage();
			}
			else if (title=="扣率信息"){
			
				fee(memid);
				
			}
			
		else if(title=="风控信息"){
				risk(memid);
				
			}else if(title=="路由信息"){
				 route(memid);
				
			}
		    else if(title=="账户信息"){
		  
				account(memid);
		    	
		    }
			
				
		    }
		    }});
	 	
	 	function basePerson(){
	 	    var personid=$("#personId").val();
		    var type=$("#type").val();
	    	$.ajax({
				   type: "POST",
				   url: "pages/acc/personMessageMemberMessageAction.action",
				   data: "memberId="+personid,
				   dataType:"json",
				   success:function(json){
						$("#pMemberid").html(json.pMemberid);
							if(json.sex==0){
								$("#sex").html("女");
							}else if(json.sex==1){
								$("#sex").html("男");
							}else{
								$("#sex").html("");
							}
							if(json.status==00){
								$("#status").html("正常");
							}else if(json.status==99){
								$("#status").html("停用");
							} else {
								$("#status").html("");
							}
							
							$("#bindPhone").html(json.bindPhone);
							$("#uptime").html(json.uptime);
							$("#vipflag").html(json.vipflag);
							$("#pIntime").html(json.pIntime);
							$("#upuser").html(json.upuser);
							$("#bindEmail").html(json.bindEmail);
							$("#pInuser").html(json.pInuser);
						$("#faxno").html(json.faxno);
							$("#greeting").html(json.greeting);
						$("#cardno").html(json.cardno);
							$("#telno").html(json.telno);
						$("#postcode").html(json.postcode);
							$("#cardtype").html(json.cardtype);
						$("#qq").html(json.qq);
							$("#adress").html(json.adress);
					   
				   }
	    	
	    	});
	 	}
	 	
	 	$('#pp').tabs({
		    border:false,
		    onSelect:function(title){
		    	/*商户personId */
			   	var mid=$("#mId").val();
			/*会员状态*/   
			var type=$("#type").val();
			/*memberId*/   
			var memid=$("#personId").val();
		    if(type==01){
		    if(title=="基本信息"){
		    	basePerson();
			}
			
			else if (title=="银行卡信息"){
				 bank(memid);
				
			}else if(title=="账户信息"){
				account(memid);
			}
		    }else{
		    	
		    	
		    }
			
				
		    }
		});
	 	
		function showUpload(type){
			var url;
			$("#btn_upload").linkbutton('enable');  
			var uploadTitle;
			if(type=='busi'){
				uploadTitle='上传税务登记照';
				url=$("#taxfile_ins").val();
			}else if(type=='licenceno'){
				uploadTitle='上传营业执照';
				url=$("#licencefile_ins").val();
			}else if(type=='orgcode'){
				uploadTitle='上传组织代码证';
				url=$("#orgcodefile_ins").val();
			}else if(type=='corpfile'){
				url=$("#corpfile_ins").val();
				uploadTitle='上传身份证照';
			}else if(type=='other'){
				uploadTitle='上传全部附件';
			}
			if(url==null||url==""){
				alert("没有上传文件");
			}else{
				var imagePath = url.substr(1).split("/");
				$.ajax({
					   type: "POST",
					   url: "pages/merchant/downLoadMerchantAction.action",
					   data: "imageURL="+url,
					   dataType:"json",
					   success: function(json){
							var basePaht="<%=basePath%>";
							$("#filePath").attr("href",basePaht+'download/'+imagePath[4]);
							$('#w_view').window({
								title: uploadTitle,
								width: 400,
								modal: true,
								collapsible:false,
								minimizable:false,
								maximizable:false,
								shadow: false,
								closed: false,
								height: 95
							});
					   }
					});	
			}
			
		}
		
		function fee(memid){
		
			$.ajax({
				   type: "POST",
				   url: "pages/acc/memeberFeeMemberMessageAction.action",
				   data: "memberId="+memid,
				   dataType:"json",
				   success:function(json){
					  var html='';
					   $.each(json, function(key,value){
						 html+='<tr>';
					 	 	html+='<td>'+(value.BUSINAME==null?"":value.BUSINAME)+'</td>';
							html+='<td>'+(value.FEETYPENAME==null?"":value.FEETYPENAME)+'</td>';
							html += '<td>'+(value.RATETYPENAME==null?"":value.RATETYPENAME)+'</td>';
							html+='<td>'+(value.CARDNAME==null?"":value.CARDNAME)+'</td>';
							html+='<td>'+(value.SERVICEFEE==null?"":value.SERVICEFEE) +'</td>';
							html+='<td>'+(value.FEE_RATE==null?"":value.FEE_RATE)+'</td>';
							html+='<td>'+(value.MIN_FEE==null?"":value.MIN_FEE)+'</td>';
							html+='<td>'+(value.MAX_FEE==null?"":value.MAX_FEE)+'</td>';
							html+='<td>'+(value.FEE_RATE2==null?"":value.FEE_RATE2)+'</td>';
							html+='<td>'+(value.MAX_FEE2==null?"":value.MAX_FEE2)+'</td>';
							html+='<td>'+(value.MIN_FEE2==null?"":value.MIN_FEE2)+'</td>';
							html+='<td>'+(value.FEE_RATE3==null?"":value.FEE_RATE3)+'</td>';
							html+='<td>'+(value.MAX_FEE3==null?"":value.MAX_FEE3)+'</td>';
							html+='<td>'+(value.MIN_FEE3==null?"":value.MIN_FEE3)+'</td>';
						html+='</tr>';
				
						})		
	
						$("#fees").html(html);	
					   
					   
				   }
				});	
			
			
		}
		
		function risk(memberId){
	
			$('#risk').datagrid({
				allowpage:false,
				url : "pages/acc/queryRickMemberMessageAction.action?memberId=" + memberId,
				columns:[
				[
					{field:'RISK',title:'风控规则',width:180,align:'center'},
					{field:'RISKCOUNT',title:'风控描述',width:80,align:'center'},
					{field:'RISKAMOUNT',title:'风控描述',width:100,align:'center'},
					{field:'RISKDESC',title:'风控描述',width:80,align:'center'} 
					
				]],
				pagination:false,
				rownumbers:true
			
			
	

	})
/* 			

			
			$.ajax({
			 type: "POST",
			   url: "pages/acc/queryRickMemberMessageAction.action",
			   data: "memberId="+memberId,
			   dataType:"json",
			   success:function(json){
				   var html='';
				   $.each(json, function(key,value){
					   html+='<tr>';
				 	 	html+='<td>'+value.RISK+'</td>';
				 	 	html+='<td>'+value.RISKCOUNT+'</td>';
				 	 	html+='<td>'+value.RISKAMOUNT+'</td>';
				 	 	html+='<td>'+value.RISKDESC+'</td>';
						html+='</tr>';
					   
				   })
					$("#risk").html(html);
			   }
			
			
		})
		 */
			
			
		}
		
		
		
		function route(memberId) {
		
			$.ajax({
				type : "POST",
				url : "pages/acc/queryRouteMemberMessageAction.action",
				data : "memberId=" + memberId,
				dataType : "json",
				success : function(json) {
					var html = '';
					$.each(json, function(key, value) {
						   html+='<tr>';
					 	 	html+='<td>'+(value.ROUTVER==null?"":value.ROUTVER)+'</td>';
					 	 	html+='<td>'+(value.chnlname==null?"":value.chnlname)+'</td>';
					 	 	html+='<td>'+(value.CASHCODE==null?"":value.CASHCODE)+'</td>';
					 	 	html+='<td>'+(value.STIME==null?"":value.STIME)+'</td>';
					 		html+='<td>'+(value.ETIME==null?"":value.ETIME)+'</td>';
					 		html+='<td>'+(value.MINAMT==null?"":value.MINAMT)+'</td>';
					 		html+='<td>'+(value.MAXAMT==null?"":value.MAXAMT)+'</td>';
					 		html+='<td>'+(value.BANKCODE==null?"":value.BANKCODE)+'</td>';
					 		html+='<td>'+(value.CARDTYPE==null?"":value.CARDTYPE)+'</td>';
					 		html+='<td>'+(value.ORDERTYPE==null?"":value.ORDERTYPE)+'</td>';
					 		html+='<td>'+(value.ORDERS==null?"":value.ORDERS)+'</td>';
					 		html+='<td>'+(value.ISDEF==null?"":value.ISDEF)+'</td>';
							html+='</tr>';
					})
					$("#route").html(html);
				}

			})

		}
		
		
		function bank(memberId){
				
					$('#bank').datagrid({
						allowpage:false,
						url : "pages/acc/memberBankInfoMemberMessageAction.action?memberId=" + memberId,
						columns:[
						[
							{field:'cardno',title:'银行卡号',width:180,align:'center'},
							{field:'cardtype',title:'卡类型',width:80,align:'center'},
							{field:'phone',title:'手机号',width:100,align:'center'},
							{field:'accname',title:'账户名称',width:80,align:'center'},
							{field:'bankname',title:'银行名称',width:80,align:'center'},
							{field:'status',title:'状态',width:50,align:'center',
								formatter:function(value,rec){
								if(value=="00"){
									return "已绑定";
								}else if(value=="01"){
									return "待绑定";
								}
								}
							} ,	
							{field:'DEPT_ID',title:'操作',width:100,align:'center',
							formatter:function(value,rec){
								if(rec.status=="00"){
								return 	'<a href="javascript:unbundling(\''+rec.id+'\',\''+rec.status+'\',\''+memberId+'\')" style="color:blue;margin-left:10px">银行卡解绑</a>';
												}else{
													return 	'';
								}
								
							}
						   } 
							
						]],
						pagination:false,
						rownumbers:true
					
					
			

			})
		}
		
		
		function unbundling(id,status,memberId){
			alert(id);
			$.ajax({
				type : "POST",
				url : "pages/acc/unlockBankMemberMessageAction.action",
				data : "qid="+id+"&&status="+status,
				dataType : "text",
				success : function(text) {
					$.messager.alert('提示',text); 
					bank(memberId);
				
				}
				
			})
			
			
			
		}
		
		function account(memberId){
			$('#account,#accounts').datagrid({
				url:'pages/acc/queryMemberMemberAccountAction.action?qa.memberId='+memberId,
				allowpage:false,
				columns:[
				[
				{field:'acctCode',title:'科目号',width:180,align:'center'},
				{field:'busiAcctCode',title:'业务账户号',width:180,align:'center'},
				{field:'balance',title:'可用余额(元)',width:80,align:'center'},
				{field:'totalBalance',title:'账户总余额(元)',width:80,align:'center'},
				{field:'fronzenBalance',title:'冻结余额(元)',width:80,align:'center'},
				{field:'busiAcctName',title:'账户名',width:150,align:'center'},
					 {field:'status',title:'账户状态',width:80,align:'center',
							formatter:function(value,rec){
								
								if(value=="00"){
									return "正常";
								}else if(value=="11"){
									return "冻结";
								}
								else if(value=="10"){
									return "止入";
								}
								else if(value=="11"){
									return "止出";
								}
								else if(value=="99"){
									return "注销";
								}
								
							}
					 } ,
				
					{field:'acctId',title:'操作',width:220,align:'center',
						formatter:function(value,rec){
							
							if(rec.status=="00"){
								return '<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',2)" style="color:blue;margin-left:10px">冻结</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',3)" style="color:blue;margin-left:10px">止入</a>'
								+'<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',5)" style="color:blue;margin-left:10px">止出</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',7)" style="color:blue;margin-left:10px">注销</a>'
								+'<a href="javascript:showUpdate(\''+memberId+'\',\''+value+'\',\''+rec.balance+'\',\''+rec.acctCode+'\',\''+rec.acctId+'\')" style="color:blue;margin-left:10px">冻结金额</a>';
							
							}else if (rec.status=="11"){
						return  '<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',7)" style="color:blue;margin-left:10px">注销</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',1)" style="color:blue;margin-left:10px">解冻</a>'	
						+'<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',4)" style="color:blue;margin-left:10px">解止入</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',6)" style="color:blue;margin-left:10px">解止出</a>'
						+'<a href="javascript:showUpdate(\''+memberId+'\',\''+value+'\',\''+rec.balance+'\',\''+rec.acctCode+'\',\''+rec.acctId+'\')" style="color:blue;margin-left:10px">冻结金额</a>';
						;
				


							}else if(rec.status=="10"){
						return  '<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',4)" style="color:blue;margin-left:10px">解止入</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',5)" style="color:blue;margin-left:10px">止出</a>'
						+'<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',2)" style="color:blue;margin-left:10px">冻结</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',7)" style="color:blue;margin-left:10px">注销</a>'
						+'<a href="javascript:showUpdate(\''+memberId+'\',\''+value+'\',\''+rec.balance+'\',\''+rec.acctCode+'\',\''+rec.acctId+'\')" style="color:blue;margin-left:10px">冻结金额</a>';
				
						

								
							}else if(rec.status=="11"){
								return  '<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',6)" style="color:blue;margin-left:10px">解止出</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',2)" style="color:blue;margin-left:10px">冻结</a>'
								+'<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',5)" style="color:blue;margin-left:10px">止出</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\''+memberId+'\',\''+value+'\',\''+rec.status+'\',7)" style="color:blue;margin-left:10px">注销</a>';
								+'<a href="javascript:showUpdate(\''+memberId+'\',\''+value+'\',\''+rec.balance+'\',\''+rec.acctCode+'\',\''+rec.acctId+'\')" style="color:blue;margin-left:10px">冻结金额</a>';
								;

							}else{
								return  '';

								
							}  
							
							
							
						}	 
					
					} 
				
					
				]],
		
			});
	
		
			
			
		}
		 // 1	冻结  2 止入 3 止出 4 解止出 5 解冻 6 解止入
		function memberOperation(type,memberId){
			if(type==1){
				message='您是否想解冻此用户?';
			} else if(type==2){
				message='您是否想止 出此用户?';
			} else if(type==3){
				message='您是否想止 出此用户?';
			} else if(type==4){
				message='您是否想解止入此用户?';
			}else if(type==5){
				message='您是否想解冻此用户?';
			}else if(type==6){
				message='您是否想解止入此用户?';
				
			}

			$.messager.confirm('提示',message,function(r){   
			   if (r){  
				$.ajax({
				   type: "POST",
				   url:   "pages/acc/memberOperationMemberMessageAction.action",
				   data: "memberId="+memberId+"&falg="+type,
				   dataType:"text",
				   success:function(json){
					
				    		$.messager.alert('提示',json);   
				    		search();
				    		closeAdd();
						
				   
				 	}
				});
				    }   
				});  
		}
		
		function deleteOrgan(memberId,organId,status, type){
// 			alert(type);
			
	
			if (type==1) {
				message='您是否想解冻此用户?';
			}
			else if(type==2){
				message='您是否想冻结此用户?';
				
			}
			else if(type==3){
				message='您是否想止入此用户?';
			}
			else if(type==4){
				message='您是否想解止入此用户?';
			}else if(type==5){
				message='您是否想止 出此用户?';
				
			}
		else if(type==6){
			message='您是否想解止 出此用户?';
			
		
	}else if(type==7){
		message='您是否想注销此用户?';
		
	}
			$.messager.confirm('提示',message,function(r){   
			   if (r){  
				$.ajax({
				   type: "POST",
				   url: "pages/acc/OperationMemberAccountAction.action",
				   data: "para.id="+organId+"&para.status="+status+"&type="+type,
				   dataType:"json",
				   success:function(json){
					
				    		$.messager.alert('提示',json.messg);   
				    		account(memberId);
				    		closeAdd();
						
				   
				 	}
				});
				    }   
				});  
		}	
		
		function check(){
			if($("#money").html()<$("#moneys").val()){
				$("#moneys").val($("#money").html())
				
			}
			
	
		
		
		}
		
		function showUpdate(pid,money,acccode,accId){
			$("#saveForm").clearForm();
		$("#money").html(money);
		  $("#acccode").html(acccode);
		  $("#accId").val(accId);
		  
			/* $.ajax({
			   type: "POST",
			   url: "pages/product/queryOneCashProductAction.action",
			   data: "pid="+pid,
			   async: false,
			   dataType:"json",
			   success: function(json){
					    $("#saveForm").attr("action","pages/product/UpdateCashProductAction.action");						   			
						$("#group_name_ins").val(json.CASHNAME);
						$("#t_id").val(json.CASHVER);
						$("#group_notes_ins").val(json.NOTES);	
					    loadYwMark(pid);  
			   }
			}); */
		     

			//$("#busi_code_ins").attr("readonly",true);
			$('#w').window({
				title: '冻结金额',
				top:90,
				left:100,
				width:640,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 260
			});
			$("#saveForm").attr("action","pages/acc/freezeAmountMemberAccountAction.action");
		}
		function saveParaDic(){
			var date = $("#startTime").datebox('getValue');
			var date1 = $("#endTime").datebox('getValue');
			if(date1<date){
				return alert("结束日期不能小于开始日期");
			}
			 if($('#saveForm').form("validate")){
			    $("#button_id").linkbutton('disable');
				$('#saveForm').form('submit', {  
				    onSubmit: function(){  
				        return $('#saveForm').form('validate');   
				    },   
				    success:function(messg){    
				    	var a= messg.split("validateUserLoginAction");
						if(messg.split("validateUserLoginAction").length>1){
							window.parent.location.replace("<%=basePath%>"+"pages/logoutAction.action?relogin=relogin");
							return ;
						}else{
				    		$.messager.alert('提示',messg);  
				    		search();
				    		closeAdd();
				    		$("#button_id").linkbutton('enable');
						}
				    }   
				});  
			 }
				 
			}
		
		function closeAdd(){
			$('#w').window('close');
			
		}	
	</script>
</html>
