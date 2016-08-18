package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.zlebank.zplatform.manager.bean.ParaDic;
import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.RouteConfigModel;
import com.zlebank.zplatform.manager.dao.object.RouteModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IRouteService;

public class RouteServiceImpl extends BaseServiceImpl<RouteModel, Long> implements IRouteService {

    private ParaDic paraDic;
    
    
    public ParaDic getParaDic() {
        return paraDic;
    }

    public void setParaDic(ParaDic paraDic) {
        this.paraDic = paraDic;
    }

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

    @Override
    public Map<String, Object> queryRouteConfig(Map<String, Object> variables,
            int page,
            int rows) {
        Object[] paramaters = new Object[]{
                null,null,
                variables.containsKey("routver")?variables.get("routver"):null,
                variables.containsKey("status")?variables.get("status"):null,
                variables.containsKey("merchroutver")?variables.get("merchroutver"):null,
                page,rows
        };
        String[] columns = new String[]{"v_rid","v_routname","v_routver","v_status",
                "v_merchroutver","i_no","i_perno"};        
        return getDao().executePageOracleProcedure(
                "{CALL PCK_T_ROUTE_CONFIG.SEL_T_ROUTE_CONFIG(?,?,?,?,?,?,?,?,?)}",
                columns,paramaters,"cursor0","v_total");   
    }

    @Override
    public List<Map<String, Object>> queryChnlcode() {        
        @SuppressWarnings("unchecked")
        List<Map<String, Object>>  resultList = (List<Map<String, Object>>) getDao().executeBySQL("select * from t_para_dic t where t.para_type='CHNLCODE' ", null);

        return resultList ;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> getAllBank() {
        List<Map<String, Object>>  resultList = (List<Map<String, Object>>) getDao().executeBySQL("select distinct (t.bankcode),t.bankname from t_cash_bank t  ", null);
        return resultList;
    }

    @SuppressWarnings("rawtypes")
    @Override
    @Transactional
    public String addRouteConfig(RouteConfigModel routeConfigModel,
            List bankcodeList,
            List busicodeList,
            List cradtypeList) {
        
        String  bankcodeStr ="";
        String  busicodeStr =""; 
        String  cardtypeStr =""; 
        String  channelStr ="";
        if(routeConfigModel==null){
            return "产品不能为空";
        }
        if(bankcodeList!=null&&bankcodeList.size()>0){
             for(int i=0;i<bankcodeList.size();i++){
                 bankcodeStr = bankcodeStr+bankcodeList.get(i).toString()+";";
             }
        }
        if(busicodeList!=null&&busicodeList.size()>0){
            for(int i=0;i<busicodeList.size();i++){
                busicodeStr = busicodeStr+busicodeList.get(i).toString()+";";
            }
        }
        if(cradtypeList!=null&&cradtypeList.size()>0){
            for(int i=0;i<cradtypeList.size();i++){
                cardtypeStr = cardtypeStr+cradtypeList.get(i).toString()+";";
            }
        }
//        if(channelcodeList!=null&&channelcodeList.size()>0){
//            for(int i=0;i<channelcodeList.size();i++){
//                channelStr = channelStr+channelcodeList.get(i).toString()+";";
//            }
//        }
        Object[] paramaters = new Object[] {
                routeConfigModel.getCashcode(),
                routeConfigModel.getStime(),
                routeConfigModel.getEtime(),
                routeConfigModel.getMinamt(),
                routeConfigModel.getMaxamt(),
                bankcodeStr,
                busicodeStr,
                cardtypeStr,
                routeConfigModel.getRoutver(),
                "00",
                routeConfigModel.getInuser(),
                routeConfigModel.getOrdertype(),
                routeConfigModel.getOrders(),
                routeConfigModel.getIsdef(),
                routeConfigModel.getNotes(),
                routeConfigModel.getRemarks(),
                routeConfigModel.getMerchroutver()
        };
        String[] columns = new String[] {"v_cashcode","v_stime","v_etime","v_minamt","v_maxamt",
                "v_bankcode","v_busicode","v_cardtype","v_routver","v_status","v_inuser","v_ordertype","v_orders",
                "v_isdef","v_notes","v_remarks","v_merchroutver"};//总共17个参数+cursor0
         List<Map<String, Object>> executeOracleProcedure = getDao().executeOracleProcedure(
                "{CALL PCK_T_ROUTE_CONFIG.INS_T_ROUTE_CONFIG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                columns,paramaters, "cursor0");

        return executeOracleProcedure.get(0).get("RET").toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> queryAllRoutver() {
        
        List<Map<String, Object>>  resultList = (List<Map<String, Object>>) getDao().executeBySQL("select distinct (t.routver),t.routname from t_route t  ", null);
        return resultList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> queryAllBusicode() {
        List<Map<String, Object>>  resultList = (List<Map<String, Object>>) getDao().executeBySQL("select distinct (t.busicode),t.businame from t_business t  ", null);
        return resultList;
    }

    @Override
    public String deleteRouteConfig(String routid,Long upuserId) {
        if (routid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{routid,upuserId};
        String[] columns = new String[]{"v_rid", "v_upuser"};
        Object total =  getDao().executeOracleProcedure(
                        "{CALL PCK_T_ROUTE_CONFIG.DEL_T_ROUTE_CONFIG(?,?,?)}",columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total ;
    }

    @Override
    public String updateOneRouteConfig(RouteConfigModel routeConfigModel) {
        if (routeConfigModel == null) {
            return "操作失败！";
        }

        Object[] paramaters = new Object[]{routeConfigModel.getRid(), routeConfigModel.getCashcode(),
                routeConfigModel.getStime(), routeConfigModel.getEtime(), 
                routeConfigModel.getMinamt(),routeConfigModel.getMaxamt(), 
                routeConfigModel.getBankcode(),routeConfigModel.getBusicode(),
                routeConfigModel.getCardtype(),routeConfigModel.getRoutver(),
                routeConfigModel.getStatus(),routeConfigModel.getUpuser(),
                routeConfigModel.getOrdertype(),routeConfigModel.getOrders(),
                routeConfigModel.getIsdef(),routeConfigModel.getNotes(),
                routeConfigModel.getRemarks(),routeConfigModel.getMerchroutver()};
        String[] columns = new String[]{"v_rid", "v_cashcode", "v_stime",
                "v_etime", "v_minamt", "v_maxamt", " v_bankcode","v_busicode","v_cardtype","v_routver",
                "v_status","v_upuser","v_ordertype","v_orders","v_isdef","v_notes","v_remarks","v_merchroutver"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_ROUTE_CONFIG.UPT_T_ROUTE_CONFIG(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }


}
