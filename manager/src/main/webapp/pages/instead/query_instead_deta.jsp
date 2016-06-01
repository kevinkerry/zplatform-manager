<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<jsp:include page="../../top.jsp"></jsp:include>
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
			style="height: 90px; padding: 10px; background: #fafafa;"
			iconCls="icon-save" collapsible="true">
			<form id="theForm" method="post">
				<table width="100%">
					<tr>
						<!--  <td align="right" width="10%">商户编号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.merId" id="merId" maxlength="32" /></td>

						<td align="right" width="10%">商户订单号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="instead.orderId" id="orderId" maxlength="32" /></td>-->
							<td align="right" width="10%">代付批次号:</td>
						<td align="left" style="padding-left: 5px" width="15%"><input
							name="insteadPayBatchQuery.batchNo" id="batchNo" maxlength="32" /></td>

						<td align="left" rowspan="6"><a href="javascript:search()"	
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
		iconCls="icon-save" style="width: 800px; height: 200px; padding: 5px;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc; text-align: center">
				<form id="firstTrial"   method="post" action="pages/withdraw/queryTrialWithdraTriaAction.action" enctype="multipart/form-data">
				导入文件:<input  id="file" type="file" name="file">
			</form>
			</div>
			<div region="south" border="false" style="text-align:center;padding:5px 0;">
				<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:filel()" id="btn_submit" onclick="">上传</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)"  id="icon-cancel" onclick="closeAdd()">取消</a>
			</div>
		</div>
	</div>
</body>

<script>
var width = $("#continer").width();
$(function() {
    $("#withdraworcheckbox").unbind();
    $('#test').datagrid({
        title: '代付导入列表',
        iconCls: 'icon-save',
        height: 400,
        singleSelect: true,
        nowrap: false,
        striped: true,
        url: 'pages/instead/queryInsteadBatchInsteadAction.action',
        remoteSort: false,
        idField: 'ORGAN_ID',
        columns: [[{
            field: 'merId',
            title: '商户编号',
            width: 120,
            align: 'center'
        },
        {
            field: 'batchNo',
            title: '商户批次号',
            width: 150,
            align: 'center'
        },
        {
            field: 'insteadPayBatchSeqNo',
            title: '代付批次号',
            width: 150,
            align: 'center'
        },
        {
            field: 'totalAmt',
            title: '总金额',
            width: 120,
            align: 'center',
            formatter : function(value, rec){
            	return fen2Yuan(value);
            }
        },
        {
            field: 'totalQty',
            title: '总笔数',
            width: 120,
            align: 'center'
        },
        {
            field: 'intime',
            title: '导入时间',
            width: 120,
            align: 'center',
            formatter : function(value, rec){
            	return formateDateTime(value);
            }
        } ,
		{field : 'status',title : '状态',width : 120,align : 'center',
			formatter : function(value, rec,index) {
				if (value == '01') {
					return '未审核';
				} else if (value == '02') {
					return '部分审核完毕';
				} else if (value == '03') {
					return '全部审核完毕';
				} else if (value == '00') {
					return '全部处理完毕';
				} else {
					return '';
				}
			} 
		} 
        ]],
        /* singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : false, */
        pagination: true,
        rownumbers: true,
        toolbar: [{
            id: 'btnadd',
            text: '导入代付文件',
            iconCls: 'icon-add',
            handler: function() {
                $("#btn_submit").linkbutton('enable');
                $("#icon-cancel").linkbutton('enable');
                $("#firstTrial")[0].reset();
                showAdd();
            }
        }]
    });
});

function search() {
    var data = {
        "insteadPayBatchQuery.batchNo": $('#batchNo').val(),
    }
    $('#test').datagrid('load', data);
}
function closeAdd() {
    $('#w').window('close');
}

function showAdd() {
    $('#w').window({
        title: '导入文件',
        top: 100,
        width: 400,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        modal: true,
        shadow: false,
        closed: false,
        height: 300
    });
}
function filel() {
    $("#firstTrial").attr("action", "pages/instead/fileInsteadAction.action");
    $('#firstTrial').form('submit', {
        onSubmit: function() {
            if ($('#firstTrial').form('validate')) {
                $('#btn_submit').linkbutton('disable');
                $("#icon-cancel").linkbutton('disable');
                return true;
            }
            return false;
        },
        success: function(data) {
            $.messager.alert('提示', data);
            search();
            closeAdd();
        }
    });
}
</script>
</html>
