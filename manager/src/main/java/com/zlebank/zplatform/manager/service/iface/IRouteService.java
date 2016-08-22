package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

















import com.zlebank.zplatform.manager.dao.object.RouteConfigModel;
import com.zlebank.zplatform.manager.dao.object.RouteModel;




public interface IRouteService extends IBaseService<RouteModel, Long>{

    Map<String, Object> findRouteEditionByPage(Map<String, Object> variables,int page,int rows);

    Map<String, Object> queryRoutver(Map<String, Object> variables);

    String AddRouteEdition(RouteModel routeModel);

    Map<String, Object> queryOneRoute(String routid);   

    String deleteRoute(String string);

    String updateRoute(RouteModel routeModel);

    Map<String, Object> queryRouteConfig(Map<String, Object> variables,int page,int rows);

    List<Map<String, Object>> queryChnlcode();

    List<Map<String, Object>> getAllBank();

    @SuppressWarnings("rawtypes")
    String addRouteConfig(RouteConfigModel routeConfigModel,
            List checkboxList,
            List busicodeList,
            List cradtypeList);

    List<Map<String, Object>> queryAllRoutver();

    List<Map<String, Object>> queryAllBusicode();

    String deleteRouteConfig(String routid, Long upuserId);

    String updateOneRouteConfig(RouteConfigModel routeConfigModel, List bankcodeList, List busicodeList, List cradtypeList);

    String startRouteConfig(String routid, Long upuserId);

    List<?> queryOneRouteConfig(String rid);

    List<?> queryContainBank(String rid);

    List<Map<String, Object>> queryContainCardtype(String rid);

    List<Map<String, Object>> queryContainBusicode(String rid);

    String startRoute(String routid);

}
