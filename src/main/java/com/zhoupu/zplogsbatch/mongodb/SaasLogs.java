package com.zhoupu.zplogsbatch.mongodb;


import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.annotation.Id;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 上午11:31:49
 * @version 1.0 *
 * @since
 */
@Entity
public class SaasLogs {

	@Id
	private String id;

	private String timer;
	private Long uid;

	private Long cid;
	private String cname;
	
	private String ip;
	private String area;
	
	private String os;
	private String bname;

	private String url;
	private String action;
	//操作菜单名称
	private String uname;
	
	private String params;
	private Boolean success;
	private String result;
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimer() {
		return timer;
	}

	public void setTimer(String timer) {
		this.timer = timer;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[")
		   .append("id=").append(id).append(",")
			.append("timer=").append(timer).append(",")
			.append("uid=").append(uid).append(",")
			.append("cid=").append(cid).append(",")
			.append("cname=").append(cname).append(",")
			.append("ip=").append(ip).append(",")
			.append("area=").append(area).append(",")
			.append("os=").append(os).append(",")
			.append("bname=").append(bname).append(",")
			.append("url=").append(url).append(",")
			.append("action=").append(action).append(",")
			.append("uname=").append(uname).append(",")
		    .append("params=").append(params).append(",")
		    .append("success=").append(success).append(",")
		    .append("result=").append(result).append(",")
		    .append("date=").append(date);
		sb.append("]");
		
        return sb.toString();
	}

	
	

}
