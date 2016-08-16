package com.zlebank.zplatform.manager.service.iface;

import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.RouteModel;




public interface IRouteService extends IBaseService<RouteModel, Long>{

    Map<String, Object> findRouteEditionByPage(Map<String, Object> variables,int page,int rows);

    Map<String, Object> queryRoutver(Map<String, Object> variables);

    String AddRouteEdition(RouteModel routeModel);

    Map<String, Object> queryOneRoute(String routid);   

    String deleteRoute(int routid);

    String updateRoute(RouteModel routeModel);

    Map<String, Object> queryRouteConfig(Map<String, Object> variables,int page,int rows);

}
