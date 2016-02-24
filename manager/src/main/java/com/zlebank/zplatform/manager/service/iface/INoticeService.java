package com.zlebank.zplatform.manager.service.iface;

import java.util.List;
import java.util.Map;

import com.zlebank.zplatform.manager.dao.object.NoticeModel;

public interface INoticeService extends IBaseService<NoticeModel, Long>{
	public List<?> saveNotice(NoticeModel notice);
	public List<?> updateNotice(NoticeModel notice);
	public String deleteNotice(Long nid);
	public List<?> findNoticeByPage(Map<String, Object> variables, int page,
			int rows);
	public long findNoticeByPageCount(Map<String, Object> variables);
	
	public List<?> findPublicNotice();
	public List<?> findPersonNotice(Long userId);
	public Map<String, Object> getNotice(Long nid);
	
	/**
	 * @param content 个人通知内容
	 * @param noticeUserId 被通知人ID
	 * @param pubUserId 发布通知人ID
	 * @return
	 */
	public List<?> savePersonNotice(String content,Long noticeUserId,Long pubUserId);
}
