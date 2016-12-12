package com.zhoupu.zplogsbatch.commons;

import java.util.Map;

/**
 * 
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年3月2日 下午1:37:02
 * @version 1.0 *
 * @since
 */
public class Result {

	private boolean result;

	private String eMessage;

	private String info;

	private Map<String, Object> map;

	private Object obj;

	public String geteMessage() {
		return eMessage;
	}

	public void seteMessage(String eMessage) {
		this.eMessage = eMessage;
	}

	public boolean isResult() {
		return result;
	}

	public Result(boolean result, String eMessage, String info, Map<String, Object> map) {
		super();
		this.result = result;
		this.eMessage = eMessage;
		this.info = info;
		this.map = map;
	}

	public Result(boolean result, String eMessage, String info, Object obj) {
		super();
		this.result = result;
		this.eMessage = eMessage;
		this.info = info;
		this.obj = obj;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public static Result ok() {
		return new Result(true, "", "success", null);
	}

	public static Result ok(Map<String, Object> map) {
		return new Result(true, "", "success", map);
	}

	public static <T> Result ok(T t) {
		return new Result(true, "", "success", t);
	}

	public static Result ok(String info) {
		return new Result(true, "", info, null);
	}

	public static Result error(String info) {
		return new Result(false, "", info, null);
	}

	public static Result error(String eMessage, String info) {
		return new Result(false, eMessage, info, null);
	}

	public static Result error(String info, Map<String, Object> map) {
		return new Result(false, "", info, map);
	}

	public static Result eMessage(String eMessage) {
		return new Result(false, eMessage, "", null);
	}

	public static Result info(String info) {
		return new Result(false, "", info, null);
	}

	public static Result info(String info, Map<String, Object> map) {
		return new Result(false, "", info, map);
	}

}
