package com.zlebank.zplatform.manager.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class PaginationSupport<E> {
	public final static int PAGE_SIZE = 12;
	public static String PAGE_PARAMETER_NAME = "page";
	public final static int PAGE_ITEM_COUNT = 15; // super page foot 一锟斤拷锟斤拷锟斤拷锟绞撅拷母锟斤拷锟�	
	private int pageItemCount = PAGE_ITEM_COUNT;
	private int totalPage; // 锟剿达拷锟斤拷锟斤拷锟斤拷锟斤拷锟�	
	private int totalCount; // manager 锟斤拷锟斤拷锟剿达拷
	private int page; // action 锟斤拷锟斤拷 manager 然锟襟传碉拷锟剿达拷
	private int pageSize = PAGE_SIZE; // action 锟斤拷锟斤拷 manager 然锟襟传碉拷锟剿达拷
	private String pageName = ""; // 锟斤拷锟斤拷页锟斤拷牡锟街�: action 锟斤拷锟斤拷 manager 然锟襟传碉拷锟剿达拷
	private Map queryMap; // action 锟斤拷锟斤拷 manager 然锟襟传碉拷锟剿达拷 : 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷侄锟�	
	private List<?> items; // manager 锟斤拷锟斤拷锟剿达拷

	public PaginationSupport() {
	}

	public PaginationSupport(List<?> resultList, int totalCount, int page,
			int pageSize) {
		setItems(resultList);
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setTotalPage((int) Math.ceil((double) getTotalCount()
				/ (double) getPageSize()));
		if (page >= 1) {
			if (page >= getTotalPage()) {
				setPage(totalPage);
			} else {
				setPage(page);
			}
		} else {
			setPage(1);
		}
	}

	public PaginationSupport(List<E> items, int totalCount, int page,
			int pageSize, String pageName, Map queryMap) {
		this(items, totalCount, page, pageSize);
		setPageName(pageName);
		setQueryMap(queryMap);
	}

	public int getFirstPage() {
		return 1;
	}

	public int getPreviousPage() {
		return (getPage() - 1 >= 1 ? getPage() - 1 : 1);
	}

	public int getNextPage() {
		return (getTotalPage() - getPage() >= 1 ? (getPage() + 1)
				: getTotalPage());
	}

	public int getLastPage() {
		return getTotalPage();
	}

	public String getPageFootGeneral(String cssPaginationSupport,
			String cssPartPaginationSupport) {
		StringBuffer result = new StringBuffer();

		String colorHeadStringAll = "";
		String colorRearStringAll = "";
		String colorHeadStringPart = "";
		String colorRearStringPart = "";
		if (null != cssPaginationSupport) { // all
			colorHeadStringAll = "<span class=\"" + cssPaginationSupport
					+ "\">";
			colorRearStringAll = "</span>";
		}
		if (null != cssPartPaginationSupport) { // part
			colorHeadStringPart = "<span class=\"" + cssPartPaginationSupport
					+ "\">";
			colorRearStringPart = "</span>";
		}
		result.append(colorHeadStringAll);
		result.append("锟斤拷 " + colorHeadStringPart + "" + getTotalCount() + ""
				+ colorRearStringPart + " 锟斤拷锟斤拷录 锟斤拷前 " + colorHeadStringPart + ""
				+ getPage() + "" + colorRearStringPart + " / "
				+ colorHeadStringPart + "" + getTotalPage() + ""
				+ colorRearStringPart + " 页 每页锟斤拷示 " + colorHeadStringPart
				+ getPageSize() + colorRearStringPart + " 锟斤拷");
		result.append(colorRearStringAll);
		return result.toString();
	}

	// classical page foot
	public String getClassicalPageFoot(String cssClass) {
		StringBuffer result = new StringBuffer();
		String cssLinkString = "";
		String cssNoLinkStringHead = "";
		String cssNoLinkStringRail = "";
		if (null != cssClass && cssClass.length() > 0) {
			cssLinkString = " class=\"" + cssClass + "\"";
			cssNoLinkStringHead = "<span class=\"" + cssClass + "\">";
			cssNoLinkStringRail = "</span>";
		}
		if (getPage() > 1) {
			result.append(" [<a" + cssLinkString + " href=\"" + getPageName()
					+ "?" + PAGE_PARAMETER_NAME + "=" + getFirstPage()
					+ getQueryString() + "\">锟斤拷页</a>] [<a" + cssLinkString
					+ " href=\"" + getPageName() + "?" + PAGE_PARAMETER_NAME
					+ "=" + getPreviousPage() + getQueryString()
					+ "\">锟斤拷一页</a>] ");
		} else {
			result.append(" " + cssNoLinkStringHead + "[锟斤拷页]"
					+ cssNoLinkStringRail + " " + cssNoLinkStringHead + "[锟斤拷一页]"
					+ cssNoLinkStringRail + " ");
		}
		if (getPage() < getTotalPage()) {
			result.append(" [<a" + cssLinkString + " href=\"" + getPageName()
					+ "?" + PAGE_PARAMETER_NAME + "=" + getNextPage()
					+ getQueryString() + "\">锟斤拷一页</a>] [<a" + cssLinkString
					+ " href=\"" + getPageName() + "?" + PAGE_PARAMETER_NAME
					+ "=" + getTotalPage() + getQueryString() + "\">尾页</a>] ");
		} else {
			result.append(" " + cssNoLinkStringHead + "[锟斤拷一页]"
					+ cssNoLinkStringRail + " " + cssNoLinkStringHead + "[尾页]"
					+ cssNoLinkStringRail + " ");
		}

		// go No. page
		if (getTotalPage() > 1) {
			result
					.append(" "
							+ cssNoLinkStringHead
							+ "锟斤拷"
							+ cssNoLinkStringRail
							+ " <select style=\"width:75px;height:20px;\" onChange=\"location.href='"
							+ getPageName() + "?" + PAGE_PARAMETER_NAME
							+ "='+this.value+'" + getQueryString() + "'\">\n");
			for (int i = 1; i <= getTotalPage(); i++) {
				result.append("<option value=\"" + i + "\"");
				if (getPage() == i) {
					result.append(" selected");
				}
				result.append(">锟斤拷 " + i + " 页</option>");
			}
			result.append("</select>");
		}
		return result.toString();
	}

	public String getClassicalPageFoot() {
		return getClassicalPageFoot(null);
	}

	// super page foot
	public String getSuperPageFoot(String cssPaginationSupport,
			String cssPartPaginationSupport) {
		StringBuffer result = new StringBuffer();

		String cssLinkString = "";
		String cssNoLinkStringHead = "";
		String cssNoLinkStringRail = "";
		if (null != cssPaginationSupport) {
			cssLinkString = " class=\"" + cssPaginationSupport + "\"";
		}
		if (null != cssPartPaginationSupport) {
			cssNoLinkStringHead = "<span class=\"" + cssPartPaginationSupport
					+ "\">";
			cssNoLinkStringRail = "</span>";
		}

		result.append(" <a" + cssLinkString + " href=\"" + getPageName() + "?"
				+ PAGE_PARAMETER_NAME + "=" + getFirstPage() + getQueryString()
				+ "\">锟斤拷页</a> <a" + cssLinkString + " href=\"" + getPageName()
				+ "?" + PAGE_PARAMETER_NAME + "=" + getPreviousPage()
				+ getQueryString() + "\">锟斤拷一页</a> ");
		int startPage = 1;
		if (getPage() > (getPageItemCount() - 1) / 2 + 1) {
			startPage = getPage() - (getPageItemCount() - 1) / 2;
		}
		int endPage = startPage + getPageItemCount() - 1;
		if (endPage > getTotalPage()) {
			endPage = getTotalPage();
			if ((endPage - getPageItemCount() + 1) < startPage) {
				startPage = (endPage - getPageItemCount() + 1);
				if (startPage < 1) {
					startPage = 1;
				}
			}
		}
		for (int i = startPage; i <= endPage; i++) {
			if (getPage() == i) {
				result.append(" " + cssNoLinkStringHead + "" + i + ""
						+ cssNoLinkStringRail + " ");
			} else {
				result.append(" <a" + cssLinkString + " href=\""
						+ getPageName() + "?" + PAGE_PARAMETER_NAME + "=" + i
						+ getQueryString() + "\">" + i + "</a> ");
			}
		}
		result.append(" <a" + cssLinkString + " href=\"" + getPageName() + "?"
				+ PAGE_PARAMETER_NAME + "=" + getNextPage() + getQueryString()
				+ "\">锟斤拷一页</a> <a" + cssLinkString + " href=\"" + getPageName()
				+ "?" + PAGE_PARAMETER_NAME + "=" + getTotalPage()
				+ getQueryString() + "\">尾页</a> ");
		result.append(getPageFootGeneral(cssPaginationSupport,
				cssPartPaginationSupport));
		return result.toString();
	}

	public String getSuperPageFoot() {
		return getSuperPageFoot("cssPaginationSupport",
				"cssPartPaginationSupport");
	}

	private String getQueryString() { // query string by queryMap
		String queryString = "";
		if (null != queryMap && !queryMap.isEmpty()) { // exists query string

			for (Iterator iterator = queryMap.entrySet().iterator(); iterator
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iterator.next();
				String name = entry.getKey().toString();
				if (!PAGE_PARAMETER_NAME.equalsIgnoreCase(name)
						&& !"pagename".equalsIgnoreCase(name)) {
					String value = null;
					String[] arrayValue = null;
					try {
						arrayValue = (String[]) entry.getValue();
						for (int i = 0; i < arrayValue.length; i++) {
							queryString = "&" + name + "=" + arrayValue[i]
									+ queryString;
						}
					} catch (Exception e) {
						try {
							value = (String) entry.getValue();
							queryString = "&" + name + "=" + value
									+ queryString;
						} catch (Exception ex) {
						}
					}
				}
			}
		}
		return queryString;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}

	public Map getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map queryMap) {
		this.queryMap = queryMap;
	}

	public int getPageItemCount() {
		return pageItemCount;
	}

	public void setPageItemCount(int pageItemCount) {
		this.pageItemCount = pageItemCount;
	}
}