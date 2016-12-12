package com.zhoupu.zplogsbatch.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.zhoupu.zplogsbatch.domain.ModuleAction;
import com.zhoupu.zplogsbatch.dto.ModuleReport;
import com.zhoupu.zplogsbatch.mongodb.SaasLogs;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 下午3:15:49
 * @version 1.0 *
 * @since
 */
public interface LogService {

	Map<String, Object> getInfoListMap(Map<String, Object> map, SaasLogs t);
	public Page<SaasLogs> findAll(SaasLogs saasLogs,int page,int size);
	
	
	
	Map<String, Object> getInfoListMap3(Map<String, Object> map, SaasLogs t);
	public Page<SaasLogs> findAll3(SaasLogs saasLogs,int page,int size,String starttime, String endtime);
	
	
	
	Map<String, Object> getInfoListMap4(Map<String, Object> map, SaasLogs t);
	public Page<SaasLogs> findAll4(SaasLogs saasLogs,int page,int size,String starttime, String endtime);
	
	
	Map<String, Object> getInfoListMap5(Map<String, Object> map, SaasLogs t);
	public Page<SaasLogs> findAll5(SaasLogs saasLogs,int page,int size,String starttime, String endtime);
	
	public Page<ModuleReport> findModuleReport(ModuleAction moduleAction, int page, int size, String startdate, String enddate);
	public int findTotalCount(String field);

}
