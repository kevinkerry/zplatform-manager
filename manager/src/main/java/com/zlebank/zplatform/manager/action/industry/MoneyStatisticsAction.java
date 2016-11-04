package com.zlebank.zplatform.manager.action.industry;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.bean.MoneyStatisticsQueryBean;
import com.zlebank.zplatform.manager.service.iface.IndustryAccountGroupService;
import com.zlebank.zplatform.member.bean.CoopInsti;
import com.zlebank.zplatform.member.bean.GroupMemberAccStatistic;
import com.zlebank.zplatform.member.service.CoopInstiService;
import com.zlebank.zplatform.member.service.IndustryGroupMemberService;

public class MoneyStatisticsAction extends BaseAction{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Autowired
    private CoopInstiService coopInstiService;
    @Autowired
    private IndustryGroupMemberService industryGroupMemberService;
    List<CoopInsti> coopInsti;
    List<Map<String, Object>> list;
    private IndustryAccountGroupService industryAccountGroupService;
    private MoneyStatisticsQueryBean tlb;
    /**
     * 查询所有的合作机构（格式为：合作机构名称(编号)）
     * @return
     */
    @SuppressWarnings("unused")
    public List<CoopInsti> getAllCoopInsti(){
        List<CoopInsti> coopInsti =  coopInstiService.getAllCoopInsti();
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
        StringBuffer instiNameAndCode =new StringBuffer();
        for(int i=0;i<coopInsti.size();i++){
            String instiName = coopInsti.get(i).getInstiName();
            String instiCode = coopInsti.get(i).getInstiCode();
            StringBuffer name =new StringBuffer();
            name.append(instiName).append("(").append(instiCode).append(")");            
            coopInsti.get(i).setInstiName(name.toString());            
        }         
        return coopInsti;
    }
    
    /**
     * 查询所有账户组的代码和名称
     * 
     */
    public List<Map<String, Object>> getAllGroupCodeAndName(){
        List<Map<String, Object>> groupNameAndCodeList =  industryAccountGroupService.getAllGroupCodeAndName();
        return groupNameAndCodeList;
    }
    
    /***
     * 页面跳转
     * @return
     */
    public String query(){       
        coopInsti = getAllCoopInsti();
        list = getAllGroupCodeAndName();
        return Action.SUCCESS;
    }
    /**
     * 查询功能
     * @throws UnsupportedEncodingException
     */
    public String queryInfo(){
        String instiCode = null,groupCode = null,accStatus= null;
        if(tlb==null){
            tlb = new MoneyStatisticsQueryBean();
        }else{
            instiCode = tlb.getInstiCode();
            groupCode = tlb.getGroupCode();
            accStatus = tlb.getAccStatus();
        }
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();  
        GroupMemberAccStatistic allGroup = industryGroupMemberService.queryGroupMemberAccStatistic( instiCode, groupCode, accStatus);       
        if(allGroup!=null){//判断查询结果列表是否为空
            if(allGroup.getGroupAccts()!=null){//判断群组的小计不为空  
                for(int i=0;i<allGroup.getGroupAccts().size();i++){
                   int memberSize = allGroup.getGroupAccts().get(i).getGroupMemberAccts().size();
                    for(int j=0;j<memberSize;j++){
                        Map<String, Object> map1= new HashMap<String, Object>();
                        map1.put("groupName",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getGroupName()) ;
                        map1.put("memberId",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getMemberId()) ;                        
                        map1.put("acctCode",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getAcctCode()) ;
                        map1.put("acctName",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getAcctName()) ;
                        map1.put("acctStatus",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getAcctStatus()) ;
                        map1.put("totalBalance",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getTotalBalance()) ;
                        map1.put("balance",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getBalance()) ;
                        map1.put("frozenBalance",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getFrozenBalance()) ; 
                        resultList.add(map1);
                    }
                    Map<String, Object> map2= new HashMap<String, Object>();
                    map2.put("groupName", "小计:");
                    map2.put("totalBalance", allGroup.getGroupAccts().get(i).getTotalBalance());
                    map2.put("balance", allGroup.getGroupAccts().get(i).getBalance());
                    map2.put("frozenBalance", allGroup.getGroupAccts().get(i).getFrozenBalance());
                    resultList.add(map2);
                }
              
            }
            Map<String, Object> map3= new HashMap<String, Object>();
            map3.put("groupName", "合计:");
            map3.put("totalBalance", allGroup.getTotalBalance());
            map3.put("balance", allGroup.getBalance());
            map3.put("frozenBalance", allGroup.getFrozenBalance());
            resultList.add(map3);
        }

        try {
            json_encode(resultList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
  


    }


    public void exportAll() throws UnsupportedEncodingException{
        String instiCode = null,groupCode = null,accStatus= null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();  
        GroupMemberAccStatistic allGroup = industryGroupMemberService.queryGroupMemberAccStatistic( instiCode, groupCode, accStatus);       
        if(allGroup!=null){//判断查询结果列表是否为空
            if(allGroup.getGroupAccts()!=null){//判断群组的小计不为空  
                for(int i=0;i<allGroup.getGroupAccts().size();i++){
                   int memberSize = allGroup.getGroupAccts().get(i).getGroupMemberAccts().size();
                    for(int j=0;j<memberSize;j++){
                        Map<String, Object> map1= new HashMap<String, Object>();
                        map1.put("groupName",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getGroupName()) ;
                        map1.put("memberId",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getMemberId()) ;                        
                        map1.put("acctCode",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getAcctCode()) ;
                        map1.put("acctName",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getAcctName()) ;
                        map1.put("acctStatus",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getAcctStatus()) ;
                        map1.put("totalBalance",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getTotalBalance()) ;
                        map1.put("balance",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getBalance()) ;
                        map1.put("frozenBalance",allGroup.getGroupAccts().get(i).getGroupMemberAccts().get(j).getFrozenBalance()) ; 
                        resultList.add(map1);
                    }
                    Map<String, Object> map2= new HashMap<String, Object>();
                    map2.put("groupName", "小计:");
                    map2.put("totalBalance", allGroup.getGroupAccts().get(i).getTotalBalance());
                    map2.put("balance", allGroup.getGroupAccts().get(i).getBalance());
                    map2.put("frozenBalance", allGroup.getGroupAccts().get(i).getFrozenBalance());
                    resultList.add(map2);
                }
              
            }
            Map<String, Object> map3= new HashMap<String, Object>();
            map3.put("groupName", "合计:");
            map3.put("totalBalance", allGroup.getTotalBalance());
            map3.put("balance", allGroup.getBalance());
            map3.put("frozenBalance", allGroup.getFrozenBalance());
            resultList.add(map3);
        }
        
        
        

        //创建excle,填充数据，并且导出
        HttpServletResponse response = ServletActionContext.getResponse();   
        response.setContentType("application/vnd.ms-excel;charset=utf-8");      
        response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode("行业资金统计.xls", "UTF-8")); 
        // 打开文件
        WritableWorkbook workbook = null;
        
        try {
            workbook = Workbook.createWorkbook(response.getOutputStream());
            // 生成名为"交易流水"的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("行业应用专户资金统计",0);
            sheet.setColumnView(  0,  20);
            sheet.setColumnView(  1,  20);
            sheet.setColumnView(  2,  25);
            sheet.setColumnView(  3,  20);
            sheet.setColumnView(  4,  20);
            sheet.setColumnView(  5,  20);
            sheet.setColumnView(  6,  20);
            sheet.setColumnView(  7,  20);
            //创建表头    列名分别为： 账户组、会员号、科目号、账户名、账户状态、账户总金额、可用金额、冻结金额
            Label label1 = new Label(0, 0,"账户组");
            Label label2 = new Label(1, 0,"会员号"); 
            Label label3 = new Label(2, 0,"科目号"); 
            Label label4 = new Label(3, 0,"账户名"); 
            Label label5 = new Label(4, 0,"账户状态"); 
            Label label6 = new Label(5, 0,"账户总金额(元)"); 
            Label label7 = new Label(6, 0,"可用金额(元)"); 
            Label label8 = new Label(7, 0,"冻结金额(元)");
            try {
                sheet.addCell(label1);
                sheet.addCell(label2);            
                sheet.addCell(label3);
                sheet.addCell(label4);
                sheet.addCell(label5);
                sheet.addCell(label6);           
                sheet.addCell(label7);
                sheet.addCell(label8);
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
            for(int i=0;i<resultList.size();i++){
                Label lableone = new Label(0, i+1, (String) resultList.get(i).get("groupName"));
                Label labletwo = new Label(1, i+1, (String) resultList.get(i).get("memberId"));
                Label lablethree = new Label(2, i+1, (String) resultList.get(i).get("acctCode"));                
                Label lablefour = new Label(3, i+1, (String) resultList.get(i).get("acctName"));
                Label lablefive = new Label(4, i+1, (String) resultList.get(i).get("acctStatus"));
                Label lablesix = new Label(5, i+1, (String) resultList.get(i).get("totalBalance"));
                Label lableseven = new Label(6, i+1, (String) resultList.get(i).get("balance"));
                Label lableight = new Label(7, i+1, (String) resultList.get(i).get("frozenBalance"));
                sheet.addCell(lableone);
                sheet.addCell(labletwo);
                sheet.addCell(lablethree);
                sheet.addCell(lablefour);
                sheet.addCell(lablefive);
                sheet.addCell(lablesix);
                sheet.addCell(lableseven);
                sheet.addCell(lableight);                
            }
            
            //写入数据并关闭文件 
            workbook.write(); 
            workbook.close(); 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
    
    
    
    //--------------------------------------------------------get  set方法-------------------------------------------------------
    
    public CoopInstiService getCoopInstiService() {
        return coopInstiService;
    }

    public void setCoopInstiService(CoopInstiService coopInstiService) {
        this.coopInstiService = coopInstiService;
    }

    public IndustryGroupMemberService getIndustryGroupMemberService() {
        return industryGroupMemberService;
    }

    public void setIndustryGroupMemberService(IndustryGroupMemberService industryGroupMemberService) {
        this.industryGroupMemberService = industryGroupMemberService;
    }

    public List<CoopInsti> getCoopInsti() {
        return coopInsti;
    }

    public void setCoopInsti(List<CoopInsti> coopInsti) {
        this.coopInsti = coopInsti;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public IndustryAccountGroupService getIndustryAccountGroupService() {
        return industryAccountGroupService;
    }

    public void setIndustryAccountGroupService(IndustryAccountGroupService industryAccountGroupService) {
        this.industryAccountGroupService = industryAccountGroupService;
    }

    public MoneyStatisticsQueryBean getTlb() {
        return tlb;
    }

    public void setTlb(MoneyStatisticsQueryBean tlb) {
        this.tlb = tlb;
    }
    
    
}
