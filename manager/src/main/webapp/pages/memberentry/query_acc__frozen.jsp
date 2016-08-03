<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:include page="../../top.jsp"></jsp:include>

	<head>

  <!-- 收支明细查询 -->
  <style type="text/css">
  	table tr td{height:25px}
  	table tr td input{height:15px}
  	table tr td select{height:20px}
  </style>
	
	
	</head>
	  <body>
  	<div style="margin: 5px;border:" id="continer">
	    <div id="p" class="easyui-panel" title="查询条件" style="height:120px;padding:10px;background:#fafafa;"   iconCls="icon-save" collapsible="true">
			<form id="theForm"   method="post" >
				<table width="100%" >
					<tr>
						<td align="center">
						账户号
							</td>
							<td colspan="1">
								<input name="aaq.accCode" maxlength="40"   type="text"  id="accCode"/>
							</td>
								<td align="center">
						交易流水
							</td>
							<td colspan="1">
									<input name="aaq.txnseqno" maxlength="40"   type="text"  id="txnseqno"/>
							</td>
								<td align="center">状态</td>	
								<td colspan="1">
								<select name="aaq.status" class="easyui-validatebox validatebox-text" id="status">
								  <option value="">请选择</option>
						          <option value="00">已解冻</option>
						          <option value="01">冻结中</option>
					        	</select>
							</td>
						</tr>
						<tr>
						
						<td align="center">冻结开始时间段从:</td>
						<td><input id="startFrozenSTime" type="text" style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="aaq.startFrozenSTime"></input>  
							至<input id="endFrozenSTime" type="text"  style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="aaq.endFrozenSTime"></input></td>
							
							<td align="center">冻结结束时间从:</td>
						<td><input id="startUnfrozenTime" type="text" style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="aaq.startUnfrozenTime"></input>  
							至<input id="endUnfrozenTime" type="text"  style="width: 120PX" class="easyui-datetimebox" data-options="showSeconds:false" name="aaq.endUnfrozenTime"></input></td>
						
						<td align="right" >
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>	
						</tr>
						<tr>
					
						
						</tr>
						
					<!-- 	<tr>
					
			             <td align="right" colspan="3">
							<a href="javascript:search()"  class="easyui-linkbutton" iconCls="icon-search">查询</a>
						</td>
			          
					    </tr> -->
		
		
				</table>
			</form>
		</div>
		<div style="margin-top: 5px">
			<table id="test"></table>
		</div>
		
	</div>
	
  </body>
  
  <script>
	$(function(){
	  	  $('#endFrozenSTime,#endUnfrozenTime,#startFrozenSTime,#startUnfrozenTime').datebox({   
	      }); 
	}); 	
  	var width = $("#continer").width();
		$(function(){
			$('#test').datagrid({
				title:'冻结账户查询',
				iconCls:'icon-save',
				height:400,
				singleSelect:true,
				nowrap: false,
				striped: true,
				url:'pages/acc/freezeAmountFrozenAccountAction.action',
				remoteSort: false,
				idField:'ORGAN_ID',
				columns:[
				[
					{field:'acctCode',title:'会计账户号',width:220,align:'center'},
					{field:'txnseqno',title:'交易流水号',width:180,align:'center'},
					 {field:'frozenBalance',title:'冻结金额(元)',width:100,align:'center'
					 },
					{field:'startTime',title:'冻结开始时间',width:150,align:'center'},
					{field:'endTime',title:'冻结结束时间',width:150,align:'center'},
					{field:'status',title:'状态 ',width:150,align:'center',
						formatter:function(value,rec){
							if(value=="00"){
								return "已解冻";
							}else if(value=="01"){
								return "冻结中";
							}
						}
					
					},
				
					{field:'id',title:'操作',width:200,align:'center',
						formatter:function(value,rec){
							
							if(rec.status=="01"){
								return '<a href="javascript:unFreezeAmount(\''+rec.id+'\')" style="color:blue;margin-left:10px">解冻</a>';
								
							
							}else if (rec.status=="00"){
					return "";


							}
						}
					}
				]],
				pagination:true,
				rownumbers:true
		
			});
	
		});
		

	    

		function search(){
	var data={'aaq.accCode':$('#accCode').val(),'aaq.txnseqno':$("#txnseqno").val(),'aaq.startUnfrozenTime':$("#startUnfrozenTime").datebox('getValue'),'aaq.endUnfrozenTime':$("#endUnfrozenTime").datebox('getValue'),'aaq.endFrozenSTime':$("#endFrozenSTime").datebox('getValue'),'aaq.startFrozenSTime':$("#startFrozenSTime").datebox('getValue'),'aaq.status':$("#status").val()};
	$('#test').datagrid('load',data);
		}

		
		
		function unFreezeAmount(id){
	
				message='您是否想解冻资金?';
			
			$.messager.confirm('提示',message,function(r){   
			   if (r){  
				$.ajax({
				   type: "POST",
				   url: "pages/acc/unFreezeAmountFrozenAccountAction.action",
				   data: "id="+id,
				   dataType:"json",
				   success:function(json){
					
				    		$.messager.alert('提示',json.messg);   
				    		search();
				    	
						
				   
				 	}
				});
				    }   
				});  
		}	
					
	</script>
</html>
