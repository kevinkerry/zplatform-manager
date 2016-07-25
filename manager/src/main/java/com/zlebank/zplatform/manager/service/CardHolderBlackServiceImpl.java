package com.zlebank.zplatform.manager.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.BlackIdnumModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.ICardHolderBlackService;

/**
 * 持卡人黑名单service
 * Class Description
 *
 * @author jxr
 * @version
 * @date 2016年7月11日 上午10:28:06
 * @since
 */
public class CardHolderBlackServiceImpl extends BaseServiceImpl<BlackIdnumModel, Long> implements ICardHolderBlackService {

    @Autowired
    private DAOContainer daoContainer;
        
    public DAOContainer getDaoContainer() {
        return daoContainer;
    }

    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    @Override
    public IBaseDAO<BlackIdnumModel, Long> getDao() {
        
        return daoContainer.getCardHolderBlackDAO();
    }

    @Override
    public Map<String, Object> queryCardHolderBlackList(Map<String, Object> variables,int page,int rows) {
        String[] columns = new String[] { "v_idnum", "v_merchaname", "i_no","i_perno" };
        Object[] paramaters = new Object[4];
        paramaters[0] = variables.containsKey("idnum") ? variables
                .get("idnum") : null;
        paramaters[1] = variables.containsKey("merchname") ? variables
                .get("merchname") : null;
        paramaters[2] = page;
        paramaters[3] = rows;
        return getDao().executePageOracleProcedure("{CALL PCK_T_BLACKLIST_IDNUM.sel_t_blacklist_idnum(?,?,?,?,?,?)}",columns,
                paramaters, "cursor0","v_total");
        
    }

    @Override
    public String AddOneBlackCardHolder(BlackIdnumModel blackIdnumModel) {
        String[] columns = new String[] { "v_idnum", "v_risklevel", "v_notes","v_remarks","v_sdate","v_edate" };
        Object[] paramaters = new Object[]{blackIdnumModel.getIdnum(),blackIdnumModel.getRisklevel(),blackIdnumModel.getNotes(),
            blackIdnumModel.getRemarks(),blackIdnumModel.getSdate(),blackIdnumModel.getEdate()};             
        Object total= getDao().executeOracleProcedure("{CALL PCK_T_BLACKLIST_IDNUM.ins_t_blacklist_idnum(?,?,?,?,?,?,?)}",columns,
                paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }

    @Override
    public Map<String, Object> queryOneBlackCardHolder(String tid) {        
        String[] columns = new String[]{"v_tid"};

        Object[] paramaters = new Object[]{tid};
        Map<String, Object> map =  getDao().executeOracleProcedure(
                "{CALL PCK_T_BLACKLIST_IDNUM.sel_t_blacklist_idnum_date(?,?)}", columns, paramaters,
                "cursor0").get(0);
        return map;
        
    }

    @Override
    public String delteOneCardHolderBlack(String tid) {
        if (tid == null) {
            return "操作失败！";
        }
        Object[] paramaters = new Object[]{tid,null};
        String[] columns = new String[]{"v_t_id", "v_user"};
        Object total = getDao().executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_IDNUM.del_t_blacklist_idnum(?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
    }

    @Override
    public String startCardHolderBlack(String tid) {
        if(tid == null||tid == ""){
            return "操作失败";
        }
        Object[] paramaters = new  Object[]{tid,null};
        String[] columns = new String[]{"v_t_id","v_user"};
        Object total = getDao().executeOracleProcedure( "{CALL PCK_T_BLACKLIST_IDNUM.start_t_blacklist_idnum(?,?,?)}", 
                columns, paramaters, "cursor0").get(0).get("INFO");
        return (String)total;
    }

    @Override
    public String updateBlackCardHolder(BlackIdnumModel blackIdnumModel) {       
        if (blackIdnumModel == null) {
            return null;
        }
       
        Object[] paramaters = new Object[]{blackIdnumModel.getTid(), blackIdnumModel.getIdnum(),
                blackIdnumModel.getRisklevel(), blackIdnumModel.getNotes(), blackIdnumModel.getRemarks(),
                 blackIdnumModel.getSdate(),blackIdnumModel.getEdate()};
        String[] columns = new String[]{"v_t_id", "v_idnum", "v_risklevel",
                "v_notes", "v_remarks", "v_sdate", " v_edate"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL PCK_T_BLACKLIST_IDNUM.upt_t_blacklist_idnum(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;
      
    }





}
