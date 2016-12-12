package com.zhoupu.zplogsbatch.dto;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.zhoupu.zplogsbatch.mongodb.SaasLogs;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月18日 下午3:25:37
 * @version 1.0 *
 * @since
 */
public class Log {

	private String timer;
	private Long uid;
	private Long cid;
	private String cname;
	
	private String ip;
	private String os;
	private String bname;

	private String url;
	private String action;
	private String params;
	private boolean success;
	private String result;

	public String getTimer() {
		return timer;
	}

	public Long getUid() {
		return uid;
	}

	public Long getCid() {
		return cid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public String getAction() {
		return action;
	}

	public String getParams() {
		return params;
	}

	public String getResult() {
		return result;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getOs() {
		return os;
	}

	public String getBname() {
		return bname;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
	
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Log(String[] msgs) {
		if (ArrayUtils.isEmpty(msgs) || 12 != msgs.length) {
			return;
		}
		int i = 0;
		this.timer = msgs[i++];
		this.uid = NumberUtils.toLong(msgs[i++]);
		this.cid = NumberUtils.toLong(msgs[i++]);
		this.cname =  msgs[i++];
		this.ip = msgs[i++];
		this.os = msgs[i++];
		this.bname = msgs[i++];
		this.url = msgs[i++];
		this.action = msgs[i++];
		this.params = msgs[i++];
		this.success = Boolean.valueOf(msgs[i++]);
		this.result = msgs[i++];
	}

	public SaasLogs getMongoLogs() {
		SaasLogs logs = new SaasLogs();
		logs.setTimer(timer);
		logs.setUid(uid);
		logs.setCid(cid);
		logs.setCname(cname);
		logs.setIp(ip);
		logs.setOs(os);
		logs.setBname(bname);
		logs.setUrl(url);
		logs.setAction(action);
		logs.setParams(params);
		logs.setSuccess(success);
		logs.setResult(result);

		return logs;
	}


}
