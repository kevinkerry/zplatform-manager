package com.zlebank.zplatform.manager.action.merch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.zlebank.zplatform.commons.utils.StringUtil;
import com.zlebank.zplatform.manager.action.base.BaseAction;
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
     * 查询路由版本代码
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

}
