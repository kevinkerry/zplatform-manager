package com.zlebank.zplatform.manager.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.ParaDicModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.IParaDicService;
@Service
public class ParaDicServiceImpl extends BaseServiceImpl<ParaDicModel, Long>
        implements
            IParaDicService {
    private DAOContainer daoContainer;
    @Override
    public IBaseDAO<ParaDicModel, Long> getDao() {
        return daoContainer.getParadicDAO();
    }
    public DAOContainer getDaoContainer() {
        return daoContainer;
    }
    public void setDaoContainer(DAOContainer daoContainer) {
        this.daoContainer = daoContainer;
    }

    public List<?> getAllParaListByParaType(String paraType) {
        return getAllParaListByParaType(paraType, "");
    }

    public List<?> getAllParaListByParaType(String paraType, String status) {
        String queryString = "";
        String[] paramaters = null;
        if (status == null || status.equals("")) {
            queryString = "select * from t_para_dic t where t.para_type=?";
            paramaters = new String[]{paraType};
        } else {
            queryString = "select * from t_para_dic t where t.para_type=? and t.status=?";
            paramaters = new String[]{paraType, status};
        }
        return this.getDao().queryBySQL(queryString, paramaters);

    }
    // 新增一条参数信息
    public String AddOneParaDic(ParaDicModel paradic) {
        Object[] paramaters = new Object[]{paradic.getParaCode(),
                paradic.getParentId(), paradic.getParaName(),
                paradic.getHasSub(), paradic.getStatus(), paradic.getRemarks()};
        String[] columns = new String[]{"v_para_code", "v_parent_id",
                "v_para_name", "v_has_sub", "v_status", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL MODI_T_PARA_DIC.ins_t_para_dic(?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;

    }
    // 分页查询参数
    public List<?> queryParaDicList(String parentid,
            String paraname,
            String status,
            int page,
            int rows) {
        Object[] paramaters = new Object[]{parentid, paraname, "1", page, rows};
        String[] columns = new String[]{"v_parent_id", "v_para_name",
                "v_status", "i_no", "i_perno"};
        return getDao().executeOracleProcedure(
                "{CALL MODI_T_PARA_DIC.sel_t_para_dic(?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");
    }
    // 分页查询参数信息总条数
    public Long queryParaDicCount(String parentid,
            String paraname,
            String status) {
        Object[] paramaters = new Object[]{parentid, paraname, "1"};
        String[] columns = new String[]{"v_parent_id", "v_para_name",
                "v_status"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL MODI_T_PARA_DIC.sel_t_para_dic_num(?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("TOTAL");
        return Long.valueOf(total.toString());
    }
    // 修改参数信息
    public String UpdatePara(ParaDicModel paradic) {
        Object[] paramaters = new Object[]{paradic.getTid(),
                paradic.getParaCode(), paradic.getParentId(),
                paradic.getParaName(), paradic.getHasSub(), "1",
                paradic.getRemarks()};
        String[] columns = new String[]{"v_tid", "v_para_code", "v_parent_id",
                "v_para_name", "v_has_sub", "v_status", "v_remarks"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL MODI_T_PARA_DIC.upt_t_para_dic(?,?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("INFO");
        return (String) total;

    }
    // -----------------------------------------------------统计报表--------------------------------------------------------------------

    // 按月汇总
    public List<?> count_report(String startDate, String endDate, String type) {
        // 加判断 然后再get(0)
        Object[] paramaters = new Object[]{startDate, endDate, type};
        String[] columns = new String[]{"v_startDate", "v_endDate", "v_type"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.stat_endDately(?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 分页查询商户交易、机构交易统计
    public List<?> merchant_report(String startDate,
            String endDate,
            String type,
            String merchNo,
            String merchName,
            String totalType,
            int page,
            int rows) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, totalType, startDate, endDate,
                merchNo, merchName, page, rows};
        String[] columns = new String[]{"v_type", "v_total_type",
                "v_startDate", "v_endDate", "v_merch_no", "v_merch_name",
                "i_no", "i_perno"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.merchant_monthly(?,?,?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0");

    }
    public Long merchant_report_NUM(String startDate,
            String endDate,
            String type,
            String merchNo,
            String merchName,
            String totalType) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, totalType, startDate, endDate,
                merchNo, merchName};
        String[] columns = new String[]{"v_type", "v_total_type",
                "v_startDate", "v_endDate", "v_merch_no", "v_merch_name"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL STAT_MONTHLY.merchant_monthly_num(?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("TOTAL");
        return Long.valueOf(total.toString());

    }
    // 商户 机构一条详细信息
    @SuppressWarnings("unchecked")
    public HashMap<String, Object> merchant_report_detail(String type,
            String merchNo,
            String merDate) {

        Object[] paramaters = new Object[]{type, merchNo, merDate};
        String[] columns = new String[]{"v_type", "v_merch_no", "v_RPTDate"};
        List<?> list = getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.merchant_monthly_detail(?,?,?,?)}",
                columns, paramaters, "cursor0");
        if (list.size() > 0) {
            return (HashMap<String, Object>) list.get(0);
        } else {
            return null;
        }

    }
    // 查询所有商户交易、机构交易 导出数据
    public List<?> merchant_report_all(String startDate,
            String endDate,
            String type,
            String merchNo,
            String merchName,
            String totalType) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, totalType, startDate, endDate,
                merchNo, merchName};
        String[] columns = new String[]{"v_type", "v_total_type",
                "v_startDate", "v_endDate", "v_merch_no", "v_merch_name"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.merchant_monthly_all(?,?,?,?,?,?,?)}",
                columns, paramaters, "cursor0");

    }
    // 分页查询 交易明细
    public List<?> trade_detail_query(Long userId,
            String startDate,
            String endDate,
            String merchNo,
            String merchOutNo,
            String posNo,
            int page,
            int rows) {
        Object[] paramaters = new Object[]{userId, merchNo, merchOutNo, posNo,
                startDate, endDate, page, rows};
        String[] columns = new String[]{"v_user", "v_merch_no",
                "v_merch_out_no", "v_pos_no", "v_startDate", "v_endDate",
                "i_no", "i_perno"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.trade_query(?,?,?,?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 交易总条数
    public Long trade_detail_query_num(Long userId,
            String startDate,
            String endDate,
            String merchNo,
            String merchOutNo,
            String posNo) {
        Object[] paramaters = new Object[]{userId, merchNo, merchOutNo, posNo,
                startDate, endDate};
        String[] columns = new String[]{"v_user", "v_merch_no",
                "v_merch_out_no", "v_pos_no", "v_startDate", "v_endDate"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL STAT_MONTHLY.trade_query_num(?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("TOTAL");
        return Long.valueOf(total.toString());
    }
    // 查询一条交易明细的详细信息
    public HashMap<String, Object> trade_one_detail(String tradeId) {
        Object[] paramaters = new Object[]{tradeId};
        String[] columns = new String[]{"v_trade_id"};
        return (HashMap<String, Object>) getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.trade_query_detail(?,?)}", columns,
                paramaters, "cursor0").get(0);

    }
    // 查询服务器时间
    public String query_sysdate() {
        Object[] paramaters = new Object[]{};
        String[] columns = new String[]{};
        Object total = getDao()
                .executeOracleProcedure("{CALL STAT_MONTHLY.query_sysdate(?)}",
                        columns, paramaters, "cursor0").get(0).get("TODAYTIME");
        return String.valueOf(total.toString());

    }
    // 查询交易明细 导出数据
    public List<?> trade_detail_all(Long userId,
            String startDate,
            String endDate,
            String merchNo,
            String merchOutNo,
            String posNo) {
        Object[] paramaters = new Object[]{userId, merchNo, merchOutNo, posNo,
                "'20130130'", endDate};
        String[] columns = new String[]{"v_user", "v_merch_no",
                "v_merch_out_no", "v_pos_no", "v_startDate", "v_endDate"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.trade_query_all(?,?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 分页查询终端交易统计
    public List<?> pos_trade_sel(String startDate,
            String endDate,
            String type,
            String merchNo,
            String posNo,
            int page,
            int rows) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, startDate, endDate, merchNo,
                posNo, page, rows};
        String[] columns = new String[]{"v_type", "v_startDate", "v_endDate",
                "v_merch_no", "v_pos_no", "i_no", "i_perno"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.sel_pos_trade(?,?,?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 终端交易统计数量
    public Long pos_trade_sel_num(String startDate,
            String endDate,
            String type,
            String merchNo,
            String posNo) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, startDate, endDate, merchNo,
                posNo};
        String[] columns = new String[]{"v_type", "v_startDate", "v_endDate",
                "v_merch_no", "v_pos_no"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL STAT_MONTHLY.sel_pos_trade_num(?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("TOTAL");
        return Long.valueOf(total.toString());
    }
    // 查询一条交易明细的详细信息
    public HashMap<String, Object> pos_trade_one_detail(String type,
            String posNo,
            String posDate) {
        Object[] paramaters = new Object[]{type, posDate, posNo};
        String[] columns = new String[]{"v_type", "v_RPTDate", "v_pos_no"};
        return (HashMap<String, Object>) getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.sel_pos_trade_detail(?,?,?,?)}", columns,
                paramaters, "cursor0").get(0);

    }
    // 查询终端交易 导出数据
    public List<?> pos_trade_sel_export(String startDate,
            String endDate,
            String type,
            String merchNo,
            String posNo) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, startDate, endDate, merchNo,
                posNo};
        String[] columns = new String[]{"v_type", "v_startDate", "v_endDate",
                "v_merch_no", "v_pos_no"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.sel_pos_trade_all(?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 分页查询 商户和终端业绩
    public List<?> out_yeji_sel(String startDate,
            String endDate,
            String type,
            String totalType,
            String deminNo,
            String deminName,
            int page,
            int rows) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, totalType, deminNo, deminName,
                startDate, endDate, page, rows};
        String[] columns = new String[]{"v_type", "v_total_type",
                "v_deimen_no", "v_deimen_name", "v_startDate", "v_endDate",
                "i_no", "i_perno"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.sel_mer_out(?,?,?,?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 导出查询 商户和终端业绩
    public List<?> out_yeji_sel_export(String startDate,
            String endDate,
            String type,
            String totalType,
            String deminNo,
            String deminName) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, totalType, deminNo, deminName,
                startDate, endDate};
        String[] columns = new String[]{"v_type", "v_total_type",
                "v_deimen_no", "v_deimen_name", "v_startDate", "v_endDate"};
        return getDao().executeOracleProcedure(
                "{CALL STAT_MONTHLY.sel_mer_out_all(?,?,?,?,?,?,?)}", columns,
                paramaters, "cursor0");

    }
    // 商户和终端业绩总条数
    public Long out_yeji_sel_num(String startDate,
            String endDate,
            String type,
            String totalType,
            String deminNo,
            String deminName) {
        if (type == null || type.equals("")) {
            type = "2"; // 默认按日统计当天
        } else {
            if (type.equals("1")) {// 按月统计，截取日期到月
                if (startDate != null && !startDate.equals("")) {
                    startDate = startDate.substring(0, 6);
                }
                if (endDate != null && !endDate.equals("")) {
                    endDate = endDate.substring(0, 6);
                }
            }
        }
        Object[] paramaters = new Object[]{type, totalType, deminNo, deminName,
                startDate, endDate};
        String[] columns = new String[]{"v_type", "v_total_type",
                "v_deimen_no", "v_deimen_name", "v_startDate", "v_endDate"};
        Object total = getDao()
                .executeOracleProcedure(
                        "{CALL STAT_MONTHLY.sel_mer_out_num(?,?,?,?,?,?,?)}",
                        columns, paramaters, "cursor0").get(0).get("TOTAL");
        return Long.valueOf(total.toString());

    }
}
