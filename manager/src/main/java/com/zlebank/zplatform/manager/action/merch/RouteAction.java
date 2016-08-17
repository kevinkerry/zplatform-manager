package com.zlebank.zplatform.manager.action.merch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
import com.zlebank.zplatform.manager.dao.object.ParaDicModel;
import com.zlebank.zplatform.manager.dao.object.RouteConfigModel;
import com.zlebank.zplatform.manager.dao.object.RouteModel;
import com.zlebank.zplatform.manager.service.container.ServiceContainer;


/**
 * 
 * 路由管理
 *
 * @author jingxr
 * @version
 * @date 2016年8月10日 下午6:16:55
 * @since
 */
public class RouteAction extends BaseAction{
    private static final long serialVersionUID = 1L;
    private RouteModel routeModel;
    private ServiceContainer serviceContainer;
    private String routid;
    private RouteConfigModel routeConfigModel;
    private ParaDicModel paraDicModel;
    @SuppressWarnings("rawtypes")
    private List bankcodeList; 
    @SuppressWarnings("rawtypes")
    private List busicodeList;
    @SuppressWarnings("rawtypes")
    private List cradtypeList;

    @SuppressWarnings("rawtypes")
    public List getBankcodeList() {
        return bankcodeList;
    }
    @SuppressWarnings("rawtypes")
    public void setBankcodeList(List bankcodeList) {
        this.bankcodeList = bankcodeList;
    }
    @SuppressWarnings("rawtypes")
    public List getBusicodeList() {
        return busicodeList;
    }
    @SuppressWarnings("rawtypes")
    public void setBusicodeList(List busicodeList) {
        this.busicodeList = busicodeList;
    }
    @SuppressWarnings("rawtypes")
    public List getCradtypeList() {
        return cradtypeList;
    }
    @SuppressWarnings("rawtypes")
    public void setCradtypeList(List cradtypeList) {
        this.cradtypeList = cradtypeList;
    }
    public ParaDicModel getParaDicModel() {
        return paraDicModel;
    }
    public void setParaDicModel(ParaDicModel paraDicModel) {
        this.paraDicModel = paraDicModel;
    }
    public RouteConfigModel getRouteConfigModel() {
        return routeConfigModel;
    }
    public void setRouteConfigModel(RouteConfigModel routeConfigModel) {
        this.routeConfigModel = routeConfigModel;
    }
    public String getRoutid() {
        return routid;
    }
    public void setRoutid(String routid) {
        this.routid = routid;
    }
    public RouteModel getRouteModel() {
        return routeModel;
    }
    public void setRouteModel(RouteModel routeModel) {
        this.routeModel = routeModel;
    }
    
    public ServiceContainer getServiceContainer() {
        return serviceContainer;
    }
    public void setServiceContainer(ServiceContainer serviceContainer) {
        this.serviceContainer = serviceContainer;
    }
    /*
     * 路由版本界面
     */
    public String show(){
        return SUCCESS;
    }
    /**
     * 路由版本信息查询
     * @return
     */
    public String queryRouteEdition(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (routeModel == null) {
            routeModel = new RouteModel();
        }
        variables.put("routver", routeModel.getRoutver());
        variables.put("routname", routeModel.getRoutname());
        Map<String, Object> groupList = serviceContainer.getRouteService().findRouteEditionByPage(variables, getPage(), getRows());
        json_encode(groupList);
        return null;  
    }
    
    /**
     * 查询路由版本代码(路由版本代码要求按照规则生成，不能让用户自己填写、修改)
     * @throws IOException 
     */
    public String queryRoutver() throws IOException{
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("table_name", "T_ROUTE");
        Map<String,Object> map = (Map<String, Object>) serviceContainer.getRouteService().queryRoutver(variables);
        json_encode(map);
        return null;
        
    }
    
    /**
     * 保存路由版本信息
     */
    public String saveRoute(){
        String mark = "";
        if (routeModel == null){
            if(StringUtil.isEmpty(routeModel.getRoutname().trim())){
                mark="路由版本名称不能为空";
            }
        }
        routeModel.setInuser(getCurrentUser().getUserId());
        mark = serviceContainer.getRouteService().AddRouteEdition(routeModel);
        if(mark.equals("操作成功!")){
            mark ="添加成功!";
        }
        json_encode(mark);
        return null;
    }
    /**
     * 查询路由版本信息
     */
    public String queryOneRoute(){
        Map<String, Object> routeMap = serviceContainer.getRouteService()
                .queryOneRoute(routid);
        json_encode(routeMap);
        return null;   
    }
    /**
     * 修改路由版本信息
     */
    public String updateRoute(){
        String mark = "";
        if (routeModel  == null||StringUtil.isEmpty(routeModel.getRoutver().trim())||StringUtil.isEmpty(routeModel.getRoutname().trim())) {
            mark = "路由版本代码或者路由版本名称不能为空";
            json_encode(mark);
            return null;
        }
        routeModel.setUpuser(getCurrentUser().getUserId());
        mark = serviceContainer.getRouteService().updateRoute(routeModel);
        if(mark .equals("succ")){
            mark ="修改成功!";
        }
        json_encode(mark);
        return null;
        
    }
    /**
     * 注销路由版本信息
     */
    public String deleteRoute(){
        String mark = "";
        if (routeModel  == null||StringUtil.isEmpty(routeModel.getNote().trim())) {
            mark = "请在备注处填写注销理由";
            json_encode(mark);
            return null;
        }
        routeModel.setUpuser(getCurrentUser().getUserId());//记录修改人
        mark = serviceContainer.getRouteService().updateRoute(routeModel);
        if(mark.equals("succ")){//表示修改成功，下一步需要注销
            mark = serviceContainer.getRouteService().deleteRoute(routeModel.getRoutid());
            if(mark.equals("succ")){
                mark ="注销成功!";
            }
        }else{
            mark="注销失败!";
        }       
        json_encode(mark);
        return null;
        
    }
    
    //****************************************单条路由配置**********************************************
    public String showRouteConfig(){
        return "routeconfig";
    }

    @SuppressWarnings("unchecked")
    public String queryRouteConfig(){
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userId", getCurrentUser().getUserId());
        if (routeConfigModel == null) {
            routeConfigModel = new RouteConfigModel();
        }
        variables.put("routver", routeConfigModel.getRoutver());//交易渠道
        variables.put("status", routeConfigModel.getStatus());
        variables.put("merchroutver", routeConfigModel.getMerchroutver());
        Map<String, Object> groupList = serviceContainer.getRouteService().queryRouteConfig(variables, getPage(), getRows());
        json_encode(groupList);
        return null; 
    }
    /**
     * 查询交易渠道
     */
    @SuppressWarnings("unchecked")
    public String queryChnlcode(){
        
        List<Map<String, Object>> groupList = serviceContainer.getRouteService().queryChnlcode(); 
        List<ParaDicModel> list = new ArrayList<ParaDicModel>();
        for(int i=0;i<groupList.size();i++){
            String paracodeString = groupList.get(i).get("PARA_CODE").toString();
            String paranameString  = groupList.get(i).get("PARA_NAME").toString();
            ParaDicModel paraDicModel = new ParaDicModel();
            paraDicModel.setParaCode(paracodeString);
            paraDicModel.setParaName(paranameString);          
            list.add(paraDicModel);
        }   
       try {
          json_encode(list);
       } catch (IOException e) {
        
          e.printStackTrace();
       }
        
        return null;
    }
    
    /**
     * 查询发卡行对应的银行代码
     */
    @SuppressWarnings("unchecked")
    public String queryBankCode(){
        List<Map<String, Object>> resultList =(List<Map<String, Object>>) serviceContainer.getRouteService().getAllBank();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for(int i= 0; i<resultList.size();i++){
            String bankcodeString = resultList.get(i).get("BANKCODE").toString();
            String banknameString = resultList.get(i).get("BANKNAME").toString();
            Map<String, Object>  map = new HashMap<String, Object>();
            map.put("bankcode", bankcodeString);
            map.put("bankname", banknameString);
            list.add(map);           
        }
        try {
            json_encode(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 增加一条路由配置信息
     */
    public String addRouteConfig(){
        String result = "";
        if (routeConfigModel == null||StringUtil.isEmpty(routeConfigModel.getStime().trim())||
            StringUtil.isEmpty(routeConfigModel.getEtime().trim()) ) {
            result = "开始时间或结束时间不能为空";
            json_encode(result);
            return null;
        }
        routeConfigModel.setInuser(getCurrentUser().getUserId());
        result=serviceContainer.getRouteService().addRouteConfig(routeConfigModel, bankcodeList,busicodeList,cradtypeList);
        json_encode(result);
        return null;
    }
    /**
     *得到所有的路由版本
     */
    public String queryAllRoutver(){
        List<Map<String, Object>> resultList =(List<Map<String, Object>>) serviceContainer.getRouteService().queryAllRoutver();
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        for(int i= 0; i<resultList.size();i++){
            String routverString = resultList.get(i).get("ROUTVER").toString();
            String routnameString = resultList.get(i).get("ROUTNAME").toString();
            Map<String, Object>  map = new HashMap<String, Object>();
            map.put("routver", routverString);
            map.put("routname", routnameString);
            list.add(map);           
        }
        try {
            json_encode(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
