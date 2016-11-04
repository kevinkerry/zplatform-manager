<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>

<!-- 收支明细查询 -->
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


</head>
<body>
	<div style="margin: 5px; border:" id="continer">
		<div id="p" class="easyui-panel" title="查询条件"
			style="height: 120px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
				<tr>
				    <td align="center">会员号</td>
					<td><input name="memberId"  type="text" id="memberId" maxlength="15" onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
					
					<td align="center">账户名</td>
					<td><input name="acctCodeName"  type="text" id="acctCodeName" maxlength="128"/></td>
					
					<td align="center">用途</td>
					<td colspan="1">
						<select name="usage" class="easyui-validatebox validatebox-text" id="usage" style="width:130px">
							<option value="">请选择</option>
							<option value="101">现金账户</option>
							<option value="102">银行存款</option>
							<option value="103">应收银行</option>
							<option value="104">证联收转存款</option>
							<option value="105">应付银行</option>
							<option value="106">通道手续费支出</option>
							<option value="107">待结算</option>
							<option value="108">应付待分润</option>
							<option value="109">保证金</option>
							<option value="110">手续费收入</option>
		                    <option value="111">授信</option>
						</select>
					</td>
						
					<td>
					</td>				
				</tr>
				<tr>
					<td align="center">科目号</td>
					<td><input name="acctCode"  type="text" id="acctCode" maxlength="22" onkeyup="value=value.replace(/[^\d]/g,'')"/></td>
					
					<td align="center">业务账户号</td>
					<td><input name="busiCode" maxlength="32" type="text"id="busiCode" maxlength="32"/></td>
					
					<td align="center">状态</td>
					<td colspan="1">
						<select name="accStatus" class="easyui-validatebox validatebox-text" id="accStatus" style="width:130px">
								<option value="">请选择</option>
								<option value="00">正常</option>
								<option value="11">冻结</option>
								<option value="10">止入</option>
								<option value="01">止出</option>
								<option value="99">注销</option>
						</select>
					</td>
					
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
	
	
	
		
	<div id="wt" class="easyui-window" closed="true" title="My Window"
		iconCls="icon-save" style="width: 500px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<table width="100%" cellpadding="2" cellspacing="2" id="groupinfo"
					border="1">
					<tr>						
						<td>会员号</td>
						<td id="memberid"></td>
					</tr>
					<tr>
						<td>科目号</td>
						<td id="acctcode"></td>
					</tr>
					<tr>		
						<td>账户名</td>
						<td id="acctcodename"></td>
					</tr>
					<tr>
						<td>用途</td>
						<td id="usage3"></td>
					</tr>
					<tr>
						<td>账户总金额</td>
						<td id="totalbalance"></td>
					</tr>
					<tr>
						<td>可用金额</td>
						<td id="balance"></td>
					</tr>
					<tr>
						<td>冻结金额</td>
						<td id="frozenbalance"></td>
					</tr>
					<tr>
						<td>账户状态</td>
						<td id="status"></td>
					</tr>
					<tr>
						<td>所在账户组</td>
						<td id="groupname"></td>
					</tr>
					
				</table>
			</div>
		</div>
	</div>

</body>

<script>
  $(function() {
	    $('#endTime,#startTime').datebox({});
	});
	var width = $("#continer").width();
	$(function() {
	    $('#test').datagrid({
	        title: '账户信息',
	        iconCls: 'icon-save',
	        height: 520,
	        singleSelect: true,
	        nowrap: false,
	        striped: true,
	        url: 'pages/acc/queryMemberMemberAccountAction.action',
	        remoteSort: false,
	        idField: 'ORGAN_ID',
	        columns: [[	
            {
	            field: 'memberID',
	            title: '会员号',
	            width: 180,
	            align: 'center'
	        },
	        {
	            field: 'acctCode',
	            title: '科目号',
	            width: 220,
	            align: 'center'
	        },
	        {
	            field: 'busiAcctName',
	            title: '账户名',
	            width: 180,
	            align: 'center'
	        },
	        {
	            field: 'usage',
	            title: '用途',
	            width: 120,
	            align: 'center',formatter: function(value, rec) {
	            	switch (value) {
	            	case 'BASICPAY':
	            	    return '现金账户';
	            	case 'BANKDEPOSIT':
	            		return '银行存款';
	            	case 'RECEIVABLEBANK':
	            		return '应收银行';
	            	case 'ZLREVTRANDEPOSIT':
	            		return '证联收转存款';
	            	case 'BANKPAYABLE':
	            		return '应付银行';
	            	case 'CHANNELFEECOST':
	            		return '通道手续费支出';
	            	case 'WAITSETTLE':
	            		return '待结算';
	            	case 'PROFITSPAYABLE':
	            		return '应付待分润';
	            	case 'BAIL':
	            		return '保证金';
	            	case 'FEEINCOME':
	            		return '手续费收入';
	            	default :
	            		return '授信';
	            	}
	            }
	        },
	        {
	            field: 'totalBalance',
	            title: '账户总余额(元)',
	            width: 130,
	            align: 'center'
	        },
	        {
	            field: 'balance',
	            title: '可用余额(元)',
	            width: 130,
	            align: 'center'
	        },

	        {
	            field: 'fronzenBalance',
	            title: '冻结余额(元)',
	            width: 130,
	            align: 'center'
	        },
	        {
	            field: 'status',
	            title: '账户状态',
	            width: 100,
	            align: 'center',
	            formatter: function(value, rec) {
	                if (value == "00") {
	                    return "正常";
	                } else if (value == "11") {
	                    return "冻结";
	                } else if (value == "10") {
	                    return "止入";
	                } else if (value == "01") {
	                    return "止出";
	                } else if (value == "99") {
	                    return "注销";
	                }
	            }
	        },
	        {
	            field: 'acctId',
	            title: '操作',
	            width: 250,
	            align: 'center',
	            formatter: function(value, rec) {
	            	if(rec.usage == 'BAIL'){//保证金账户：只提供冻结金额操作 
	            		return '<a href="javascript:showUpdate(\'' + value + '\',\'' + rec.balance + '\',\'' + rec.acctCode + '\',\'' + rec.acctId + '\')" style="color:blue;margin-left:10px">冻结金额</a>'+'<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>';
	            	}else if(rec.usage == 'BASICPAY'){
		                if (rec.status == "00") {//正常
		                    return '<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',2)" style="color:blue;margin-left:10px">冻结</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',3)" style="color:blue;margin-left:10px">止入</a>' + '<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',5)" style="color:blue;margin-left:10px">止出</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',7)" style="color:blue;margin-left:10px">注销</a>' + '<a href="javascript:showUpdate(\'' + value + '\',\'' + rec.balance + '\',\'' + rec.acctCode + '\',\'' + rec.acctId + '\')" style="color:blue;margin-left:10px">冻结金额</a>'+'<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>';
		                } else if (rec.status == "11") {//冻结
		                    return '<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',1)" style="color:blue;margin-left:10px">解冻</a>' + '<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',4)" style="color:blue;margin-left:10px">解止入</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',6)" style="color:blue;margin-left:10px">解止出</a>'+'<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',7)" style="color:blue;margin-left:10px">注销</a>' +'<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>';
		                } else if (rec.status == "10") {//止入
		                    return '<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',4)" style="color:blue;margin-left:10px">解止入</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',2)" style="color:blue;margin-left:10px">冻结</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',7)" style="color:blue;margin-left:10px">注销</a>'+'<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>' ;
		                } else if (rec.status == "01") {//止出
		                    return '<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',6)" style="color:blue;margin-left:10px">解止出</a>&nbsp;&nbsp;<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',2)" style="color:blue;margin-left:10px">冻结</a>'+'<a href="javascript:deleteOrgan(\'' + value + '\',\'' + rec.status + '\',7)" style="color:blue;margin-left:10px">注销</a>'+'<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>'; 
		                } else {//注销状态
		                    return '<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>';
		                }
	            	}else{
	            		return '<a href="javascript:detail('+value+')" style="color:blue;margin-left:10px">详情</a>';
	            	}
	            	

	            }
	        }
	        ]],
	        pagination: true,
	        rownumbers: true
	    });
	});

	function search() {
	    var data = {
	    	'qa.memberId': $('#memberId').val(),
	    	'qa.acctCodeName':$("#acctCodeName").val(),
	        'qa.usage': $('#usage').val(),
	        'qa.acctCode': $("#acctCode").val(),
	        'qa.busiCode': $("#busiCode").val(),  
	        'qa.accStatus': $("#accStatus").val()	            
	    };
	    $('#test').datagrid('load', data);
	}

	function deleteOrgan(organId, status, type) {
	    if (type == 1) {
	        message = '您是否想解冻此用户?';
	    } else if (type == 2) {
	        message = '您是否想冻结此用户?';
	    } else if (type == 3) {
	        message = '您是否想止入此用户?';
	    } else if (type == 4) {
	        message = '您是否想解止入此用户?';
	    } else if (type == 5) {
	        message = '您是否想止 出此用户?';
	    } else if (type == 6) {
	        message = '您是否想解止 出此用户?';
	    } else if (type == 7) {
	        message = '您是否想注销此用户?';
	    }
	    $.messager.confirm('提示', message,
	    function(r) {
	        if (r) {
	            $.ajax({
	                type: "POST",
	                url: "pages/acc/operationMemberAccountAction.action",
	                data: "para.id=" + organId + "&para.status=" + status + "&type=" + type,
	                dataType: "json",
	                success: function(json) {
	                    $.messager.alert('提示', json.messg);
	                    search();
	                    closeAdd();
	                }
	            });
	        }
	    });
	}
	
	function check() {
	    var money = $("#money").html();
	    var moneys = $("#moneys").val();
	    if (moneys - money > 0) {
	        $("#moneys").val($("#money").html())
	    }
	}

	function showUpdate(pid, money, acccode, accId) {
	    $("#saveForm").clearForm();
	    $("#money").html(money);
	    $("#acccode").html(acccode);
	    $("#accId").val(accId);
	    $('#w').window({
	        title: '冻结金额',
	        top: 90,
	        left: 100,
	        width: 640,
	        modal: true,
	        minimizable: false,
	        collapsible: false,
	        maximizable: false,
	        shadow: false,
	        closed: false,
	        height: 260
	    });
	    $("#saveForm").attr("action", "pages/acc/freezeAmountMemberAccountAction.action");
	}

	function saveParaDic() {
	    var date = $("#startTime").datebox('getValue');
	    var date1 = $("#endTime").datebox('getValue');
	    if (date1 < date) {
	        return alert("结束日期不能小于开始日期");
	    }
	    if ($('#saveForm').form("validate")) {
	        $("#button_id").linkbutton('disable');
	        $('#saveForm').form('submit', {
	            onSubmit: function() {
	                return $('#saveForm').form('validate');
	            },
	            success: function(messg) {
	                $.messager.alert('提示', messg);
	                search();
	                closeAdd();
	                $("#button_id").linkbutton('enable');
	            }
	        });
	    }
	}

	function closeAdd() {
	    $('#w').window('close');
	}
	
	function detail(id){
			$('#wt').window({
				title: '详细信息',
				top:90,
				left:200,
				width:900,
				modal: true,
				minimizable:false,
				collapsible:false,
				maximizable:false,
				shadow: false,
				closed: false,
				height: 400
			});
		
       $.ajax({
            type: "POST",
            url: "pages/acc/detailMemberAccountAction.action?id="+id,
            dataType: "json",
            success: function(json) {
       		 $("#memberid").html(json.BUSINESS_ACTOR_ID);    		 
    		 $("#acctcode").html(json.ACCT_CODE);
    		 $("#acctcodename").html(json.ACCT_CODE_NAME);
    		 $("#totalbalance").html(json.TOTALBALANCE);
    		 $("#balance").html(json.BALANCE);
    		 $("#frozenbalance").html(json.FROZENBALANCE);
    		 var status =json.STATUS;
    		 if(status =="00" ){
    			 $("#status").html("正常");
    		 }else if(status =="11"){
    			 $("#status").html("冻结");
    		 }else if(status =="10"){
    			 $("#status").html("止入");
    		 }else if(status =="01"){
    			 $("#status").html("止出");
    		 }else if(status =="99"){
    			 $("#status").html("注销"); 
    		 }else{
    			 $("#status").html("未知"); 
    		 }
    		 var usage1 =json.USAGE;
    		 if(usage1=="101"){
    			 $("#usage3").html("现金账户");
    		 }else if(usage1=="102"){
    			 $("#usage3").html("银行存款");
    		 }else if(usage1=="103"){
    			 $("#usage3").html("应收银行");
    		 }else if(usage1=="104"){
    			 $("#usage3").html("证联收转存款");
    		 }else if(usage1=="105"){
    			 $("#usage3").html("应付银行");
    		 }else if(usage1=="106"){
    			 $("#usage3").html("通道手续费支出");
    		 }else if(usage1=="107"){
    			 $("#usage3").html("待结算"); 
    		 }else if(usage1=="108"){
    			 $("#usage3").html("应付待分润");
    		 }else if(usage1=="109"){
    			 $("#usage3").html("保证金");
    		 }else if(usage1=="110"){
    			 $("#usage3").html("手续费收入"); 
    		 }else if(usage1=="111"){
    			 $("#usage3").html("授信"); 
    		 }
    		 
    		 $("#groupname").html(json.GROUP_NAME);
            }
        }); 

	}
	
	
	</script>
</html>
