package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.RouteModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IRouteService;

public class RouteServiceImpl extends BaseServiceImpl<RouteModel, Long> implements IRouteService {

    private DAOContainer daoContainer;

    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }
    @Override
    public IBaseDAO<RouteModel, Long> getDao() {
        
        return daoContainer.getRouteDAO();
    }
    
    @Override
    public Map<String, Object> findRouteEditionByPage(Map<String, Object> variables,
            int page,
            int rows) {
        String[] columns = new String[]{"v_routver", "v_routname", "v_status",
        "v_intime","v_notes","v_remarks","i_no","i_perno"};

            Object[] paramaters = new Object[]{
                variables.containsKey("routver")
                        ? variables.get("routver")
                        : null,
                variables.containsKey("routname")
                        ? variables.get("routname")
                        : null,
                variables.containsKey("status")
                        ? variables.get("status")
                        : null, 
                variables.containsKey("intime")
                        ? variables.get("intime")
                        : null, 
                variables.containsKey("notes")
                        ? variables.get("notes")
                        : null, 
                variables.containsKey("remarks")
                        ? variables.get("remarks")
                        : null, page, rows};
            return getDao().executePageOracleProcedure(
                    "{CALL PCK_T_ROUTE.SEL_T_ROUTE(?,?,?,?,?,?,?,?,?,?)}", columns, paramaters,
                    "cursor0", "v_total");
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryRoutver(Map<String, Object> variables) {
        List<Map<String, Object>> list =  (List<Map<String, Object>>) getDao().executeBySQL("select GET_BROK (?) as routver from dual ", 
                new Object[]{variables.get("table_name")});
        Map<String, Object> resultMap = list.get(0);
        return    resultMap;
    }

    @Override
    public String AddRouteEdition(RouteModel routeModel) {
        if (routeModel == null) {
            return "操作失败！";
        }

        Object[] paramaters = new Object[]{routeModel.getRoutname(),routeModel.getRoutver(),routeModel.getStatus(),
                routeModel.getInuser(),routeModel.getNote(),routeModel.getRemarks()};
        String[] columns = new String[]{"v_routname", "v_routver", " v_status",
                "v_inuser", "v_notes", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_ROUTE.INS_T_ROUTE(?,?,?,?,?,?,?)}", columns,
                        paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
        
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> queryOneRoute(String routid) {
        Object[] paramaters = new Object[]{routid};
        String[] columns = new String[]{"v_routid"};
        
        Map<String, Object> resultMap =    getDao().executeOracleProcedure("{CALL PCK_T_ROUTE.SEL_T_ROUTE_DETAIL(?,?)}", 
                columns, paramaters, "cursor0").get(0);
         return resultMap;
    }

    @Override
    public String updateRoute(RouteModel routeModel) {
        String[] columns = new String[]{"v_routid", "v_routname", "v_status",
        "v_upuser","v_notes","v_remarks"};
        Object[] paramaters = new Object[]{routeModel.getRoutid(), routeModel.getRoutname(),
        routeModel.getStatus(),routeModel.getUpuser(),routeModel.getNote(),routeModel.getRemarks()};
        Map<String, Object> map =  getDao().executeOracleProcedure(
                "{CALL PCK_T_ROUTE.UPT_T_ROUTE(?,?,?,?,?,?,?)}", columns, paramaters,
                "cursor0").get(0);
        String info = (String) map.get("RET");
        return info;
    }

    @Override
    public String deleteRoute(int routid) {
        Object[] paramaters = new Object[]{routid};
        String[] columns = new String[]{"v_routid"};
        
        Map<String, Object> resultMap =    getDao().executeOracleProcedure("{CALL PCK_T_ROUTE.DEL_T_ROUTE(?,?)}", 
                columns, paramaters, "cursor0").get(0);
        String info = (String) resultMap.get("RET");
         return info;
       
    }



   
}
