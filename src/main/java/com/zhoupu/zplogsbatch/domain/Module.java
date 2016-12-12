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
@Table(name = "T_MODULE")
public class Module {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	//@OneToMany(mappedBy = "module", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//private List<ModuleAction> actions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    //public List<ModuleAction> getActions() {
    //    return actions;
    //}

    //public void setActions(List<ModuleAction> actions) {
    //    this.actions = actions;
    //}
}
