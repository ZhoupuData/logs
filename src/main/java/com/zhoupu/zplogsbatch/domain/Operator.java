package com.zhoupu.zplogsbatch.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 下午1:35:02
 * @version 1.0 *
 * @since
 */
@Entity
@Table(name = "T_OPERATOR")
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long cid;

	private Long uid;

	private String username;

	public Long getId() {
		return id;
	}

	public Long getCid() {
		return cid;
	}

	public Long getUid() {
		return uid;
	}

	public String getUsername() {
		return username;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
