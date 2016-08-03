package com.zlebank.zplatform.manager.service;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.container.DAOContainer;
import com.zlebank.zplatform.manager.dao.iface.IBaseDAO;
import com.zlebank.zplatform.manager.dao.object.NoticeModel;
import com.zlebank.zplatform.manager.service.base.BaseServiceImpl;
import com.zlebank.zplatform.manager.service.iface.INoticeService;

public class NoticeServiceImpl extends BaseServiceImpl<NoticeModel, Long> implements INoticeService{

	private DAOContainer daoContainer;
	@Override
	public IBaseDAO<NoticeModel, Long> getDao() {
		return daoContainer.getNoticeDAO();
	}
	public DAOContainer getDaoContainer() {
		return daoContainer;
	}
	public void setDaoContainer(DAOContainer daoContainer) {
		this.daoContainer = daoContainer;
	}
	
	public String deleteNotice(Long nid) {
		String errorInfo = "操作成功！";
		try {
			getDao().delete(getDao().get(nid));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorInfo="操作失败";
		}
		return errorInfo;
	}
	public List<?> findNoticeByPage(Map<String, Object> variables, int page,
			int rows) {
		String[] columns = new String[] { "v_title", "v_content","i_no", "i_perno" };
		Object[] paramaters = new Object[4];
		paramaters[0] = variables.containsKey("title") ? variables
				.get("title") : null;
		paramaters[1] = variables.containsKey("content") ? variables
				.get("content") : null;
		paramaters[2] = page;
		paramaters[3] = rows;
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.sel_t_notice(?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}
	public long findNoticeByPageCount(Map<String, Object> variables) {
		String[] columns = new String[] { "v_title", "v_content"};
		Object[] paramaters = new Object[2];
		paramaters[0] = variables.containsKey("title") ? variables
				.get("title") : null;
		paramaters[1] = variables.containsKey("content") ? variables
				.get("content") : null;
		Object total = getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.sel_t_notice_num(?,?,?)}", columns,
				paramaters, "cursor0").get(0).get("TOTAL");
		return Long.valueOf(total.toString());
	}
	public List<?> saveNotice(NoticeModel notice) {
		String[] columns = new String[] { 
				  "v_title",
			      "v_content",
			      "v_notice_type",
			      "v_notice_sort",
			      "v_notice_user_id",
			      "v_pub_time",
			      "v_pub_user",
			      "v_status",
			      "v_notes",
			      "v_remarks"
		};
		Object[] paramaters = new Object[] {
				"".equals(notice.getTitle()) ? null : notice.getTitle(),
				"".equals(notice.getContent()) ? null : notice.getContent(),
				"".equals(notice.getNoticeType()) ? null : notice.getNoticeType(),
				"".equals(notice.getNoticeSort()) ? null : notice.getNoticeSort(),
				"".equals(notice.getNoticeUserId()) ? null : notice.getNoticeUserId(),
				"".equals(notice.getPubTime()) ? null : notice.getPubTime(),
				"".equals(notice.getPubUser()) ? null : notice.getPubUser(),
				"".equals(notice.getStatus()) ? null : notice.getStatus(),
				"".equals(notice.getNotes()) ? null : notice.getNotes(),
				"".equals(notice.getRemarks()) ? null : notice.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.ins_t_notice(?,?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
	}
	public List<?> updateNotice(NoticeModel notice) {
		String[] columns = new String[] { 
				  "v_nid",
				  "v_title",
			      "v_content",
			      "v_notice_type",
			      "v_notice_sort",
			      "v_notice_user_id",
			      "v_pub_time",
			      "v_pub_user",
			      "v_status",
			      "v_notes",
			      "v_remarks"
		};
		Object[] paramaters = new Object[] {
				"".equals(notice.getNid()) ? null : notice.getNid(),
				"".equals(notice.getTitle()) ? null : notice.getTitle(),
				"".equals(notice.getContent()) ? null : notice.getContent(),
				"".equals(notice.getNoticeType()) ? null : notice.getNoticeType(),
				"".equals(notice.getNoticeSort()) ? null : notice.getNoticeSort(),
				"".equals(notice.getNoticeUserId()) ? null : notice.getNoticeUserId(),
				"".equals(notice.getPubTime()) ? null : notice.getPubTime(),
				"".equals(notice.getPubUser()) ? null : notice.getPubUser(),
				"".equals(notice.getStatus()) ? null : notice.getStatus(),
				"".equals(notice.getNotes()) ? null : notice.getNotes(),
				"".equals(notice.getRemarks()) ? null : notice.getRemarks() };
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.upt_t_notice(?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
				paramaters, "cursor0");
		
	}
	public List<?> findPublicNotice() {
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.sel_t_notice_public(?)}", new String[]{},
				null, "cursor0");
	}

	public List<?> findPersonNotice(Long userId) {
		String[] columns = new String[] { 
			      "v_notice_user_id"
		};
		Object[] paramaters = new Object[] { userId };
		return getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.sel_t_notice_person(?,?)}", columns,
				paramaters, "cursor0");
	}
	/**
	 * @param content 个人通知内容
	 * @param noticeUserId 被通知人ID
	 * @param pubUserId 发布通知人ID
	 * @return
	 */
	public List<?> savePersonNotice(String content,Long noticeUserId,Long pubUserId){
		//List<?> saveNotice(NoticeModel notice)
		NoticeModel notice = new NoticeModel();
		notice.setTitle(content);
		notice.setNoticeUserId(noticeUserId);
		notice.setPubUser(pubUserId);
		notice.setNoticeSort(1L);
		notice.setNoticeType(2L);
		return saveNotice(notice);
	}
	
	public Map<String, Object> getNotice(Long nid){
		String[] columns = new String[] { 
			      "v_nid"
		};
		Object[] paramaters = new Object[] { nid };
		List<Map<String, Object>> noticeList = getDao().executeOracleProcedure(
				"{CALL MODI_T_NOTICE.sel_t_notice_detail(?,?)}", columns,
				paramaters, "cursor0");
		if(noticeList.size()>0){
			return noticeList.get(0);
		}
		return null;
	}
}
