package com.zhoupu.zplogsbatch.commons;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbSearcher;

public class Utils {

	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	public static DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String getAreaName(DbSearcher searcher, String ip) {
		if (searcher == null || null == ip) {
			return "";
		} else {
			DataBlock sdata = null;
			try {
				sdata = searcher.btreeSearch(ip);
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
			return sdata.getRegion();
		}
	}
	
	
	public static String getVerCode() {
		int num = (int) ((Math.random() * 9 + 1) * 100000);
		return String.valueOf(num);
	}

	@Deprecated
	public static String getCname(Long cid) {
		String cname = DataCache.companyMap.get(cid);
		if (null == cname) {
			return "";
		}
		return cname;
	}

	public static String getNow() {
		String now = LocalDateTime.now().format(dateTimeFormatter);
		return now;
	}

	public static String getDate() {
		String now = LocalDate.now().format(date);
		return now;
	}

	public static String getUnameByUrl(String url) {
		String urlname = DataCache.urlsMap.get(url);
		if (null == urlname) {
			urlname = "";
		}
		return urlname;
	}

	public static String getUnameByAction(String action) {
		String actionname = DataCache.actionsMap.get(action);
		if (null == actionname) {
			actionname = "";
		}
		return actionname;
	}

	public String getMethodName(String method) {
		if (StringUtils.isEmpty(method)) {
			return "";
		}
		method = method.toLowerCase();
		String res = "";
		switch (method) {
		case "post":
			res = "新增";
			break;

		case "put":
			res = "修改";
			break;

		case "get":
			res = "查询";
			break;

		case "delete":
			res = "删除";
			break;

		default:
			break;
		}
		return res;
	}

}
