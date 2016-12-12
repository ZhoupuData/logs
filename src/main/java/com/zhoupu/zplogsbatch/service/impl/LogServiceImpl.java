package com.zhoupu.zplogsbatch.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.DBCollection;
import com.querydsl.core.types.Predicate;
import com.zhoupu.zplogsbatch.domain.ModuleAction;
import com.zhoupu.zplogsbatch.dto.ModuleReport;
import com.zhoupu.zplogsbatch.mongodb.QSaasLogs;
import com.zhoupu.zplogsbatch.mongodb.SaasLogs;
import com.zhoupu.zplogsbatch.mongodb.SaasLogsRepository;
import com.zhoupu.zplogsbatch.service.LogService;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 下午3:16:29
 * @version 1.0 *
 * @since
 */
@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private SaasLogsRepository saasLogsRepository;
	
	
	@Autowired
	private  MongoTemplate mongoTemplate;
	
	
	private Sort sort = new Sort(Sort.Direction.DESC, "id");


	@Override
	public Map<String, Object> getInfoListMap(Map<String, Object> map,SaasLogs saasLogs) {
		int start = (Integer)map.get("start"); 
		int draw  = (Integer)map.get("draw");
		int size = (Integer)map.get("length");
		int page = start/size;
        Page<SaasLogs> pageContent = findAll(saasLogs, page, size);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("draw", draw);
        result.put("data", pageContent.getContent());
        result.put("recordsTotal", pageContent.getTotalElements());
        result.put("recordsFiltered", pageContent.getTotalElements());
        return result;
	}




	/**
	 * 使用这样的方法，注意String的属性是否为"" 而不是null,如果为"",会匹配不到值
	 */
	@Override
	public Page<SaasLogs> findAll(SaasLogs saasLogs, int page, int size) {
		PageRequest pageable = new PageRequest(page, size,sort);
		ExampleMatcher matcher = ExampleMatcher.matching()
				    .withIgnorePaths("id","timer","ip","area","os","bname")
				    .withIgnoreNullValues()
			        .withMatcher("date", match -> match.exact())
			        .withMatcher("url", match -> match.contains())
			        .withMatcher("result", match -> match.contains())
			        .withMatcher("params", match -> match.contains()); 
		
		Example<SaasLogs> example = Example.of(saasLogs,matcher);
		Page<SaasLogs> pages = saasLogsRepository.findAll(example,pageable);
		
		return pages;
	}
	
	/**
	 * Example限制
	 * 只能是and连接。不支持or
	 * 支持starts/contains/ends/regex 匹配
	 *
	 */
	@Deprecated
	public Page<SaasLogs> findAll2(SaasLogs saasLogs, int page, int size) {
		Sort sort = new Sort(Sort.Direction.DESC, "id");
		PageRequest pageable = new PageRequest(page, size,sort);
		ExampleMatcher matcher = ExampleMatcher.matching()
				    .withIgnorePaths("id","ip","area","os","bname")
				    .withIgnoreNullValues()
			        .withMatcher("date", match -> match.exact())
			        .withMatcher("url", match -> match.contains())
			        /*
			                         改方法是对值转换
			         .withMatcher("url", match -> match.transform(source -> {
			                               这里的source 就是前台传过来的 url的值	
			        	return source;
			        }))
			        */
			        //.withMatcher("timer", match -> matches(""))
			        .withMatcher("result", match -> match.contains())
			        .withMatcher("params", match -> match.contains()); 
		
		Example<SaasLogs> example = Example.of(saasLogs,matcher);
		Page<SaasLogs> pages = saasLogsRepository.findAll(example,pageable);
		
		return pages;
	}


	
	@Override
	public Map<String, Object> getInfoListMap3(Map<String, Object> map,SaasLogs saasLogs) {
		int start = (Integer)map.get("start"); 
		int draw  = (Integer)map.get("draw");
		int size = (Integer)map.get("length");
		int page = start/size;
		String starttime = (String)map.get("starttime");
		String endtime = (String)map.get("endtime");
		Page<SaasLogs> pageContent = findAll3(saasLogs, page, size, starttime, endtime);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("draw", draw);
        result.put("data", pageContent.getContent());
        result.put("recordsTotal", pageContent.getTotalElements());
        result.put("recordsFiltered", pageContent.getTotalElements());
        return result;
	}


	@Override
	public Page<SaasLogs> findAll3(SaasLogs saasLogs, int page, int size,String starttime, String endtime) {
		PageRequest pageable = new PageRequest(page, size,sort);
		Page<SaasLogs> pages = saasLogsRepository.findByDateAndCidAndUidAndCnameLikeAndUrlLikeAndActionLikeAndParamsLikeAndSuccessAndResultLikeAndTimerGreaterThanEqualAndTimerLessThanEqual(
				saasLogs.getDate(), 
				saasLogs.getCid(), 
				saasLogs.getUid(), 
				saasLogs.getCname(), 
				saasLogs.getUname(), 
				saasLogs.getAction(), 
				saasLogs.getParams(),
				saasLogs.isSuccess(), 
				saasLogs.getResult(), 
				starttime, endtime, pageable);
		return pages;
	}




	@Override
	public Page<SaasLogs> findAll4(SaasLogs saasLogs, int page, int size, String starttime, String endtime) {
		PageRequest pageable = new PageRequest(page, size,sort);
		Page<SaasLogs> pages = saasLogsRepository.findAll(getBooleanExpression(saasLogs),pageable);
		return pages;
	}
	
	
	private  Predicate getBooleanExpression(SaasLogs saasLogs){
		
		QSaasLogs  qSaasLogs = QSaasLogs.saasLogs;
		
		Predicate predicate = qSaasLogs.id.endsWith("").and(qSaasLogs.success.isFalse());
		
		
		if(StringUtils.isNotEmpty(saasLogs.getDate())){
			//predicate.and(qSaasLogs.date.eq(saasLogs.getDate()));
			
		}
		return predicate;
	}




	@Override
	public Map<String, Object> getInfoListMap4(Map<String, Object> map, SaasLogs saasLogs) {
		int start = (Integer)map.get("start"); 
		int draw  = (Integer)map.get("draw");
		int size = (Integer)map.get("length");
		int page = start/size;
		String starttime = (String)map.get("starttime");
		String endtime = (String)map.get("endtime");
		Page<SaasLogs> pageContent = findAll4(saasLogs, page, size, starttime, endtime);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("draw", draw);
        result.put("data", pageContent.getContent());
        result.put("recordsTotal", pageContent.getTotalElements());
        result.put("recordsFiltered", pageContent.getTotalElements());
        return result;
	}




	@Override
	public Map<String, Object> getInfoListMap5(Map<String, Object> map, SaasLogs saasLogs) {
		int start = (Integer)map.get("start"); 
		int draw  = (Integer)map.get("draw");
		int size = (Integer)map.get("length");
		int page = start/size;
		String starttime = (String)map.get("starttime");
		String endtime = (String)map.get("endtime");
		Page<SaasLogs> pageContent = findAll5(saasLogs, page, size, starttime, endtime);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("draw", draw);
        result.put("data", pageContent.getContent());
        result.put("recordsTotal", pageContent.getTotalElements());
        result.put("recordsFiltered", pageContent.getTotalElements());
        return result;
	}




	
	@Override
	public Page<SaasLogs> findAll5(SaasLogs saasLogs, int page, int size, String starttime, String endtime) {
		Criteria criteria = new Criteria();
		
		PageRequest pageable = new PageRequest(page, size, sort);
		
		if(StringUtils.isNotEmpty(saasLogs.getDate())){
			criteria.and("date").is(saasLogs.getDate());
		}
		
		if(StringUtils.isNotEmpty(starttime) && StringUtils.isEmpty(endtime)){
			criteria.and("timer").gte(starttime);
		}
		if(StringUtils.isNotEmpty(endtime) && StringUtils.isEmpty(starttime)){
			criteria.and("timer").lte(endtime);
		}
		if(StringUtils.isNotEmpty(starttime) && StringUtils.isNotEmpty(endtime)){
			criteria.and("timer").gte(starttime).lte(endtime);
		}
		
		if(null != saasLogs.getCid()){
			criteria.and("cid").is(saasLogs.getCid());
		}
		if(null != saasLogs.getUid()){
			criteria.and("uid").is(saasLogs.getUid());
		}
		if(null != saasLogs.getCname()){
			criteria.and("cname").is(saasLogs.getCname());
		}
		if(null != saasLogs.getUrl()){
			criteria.and("url").regex(".*"+saasLogs.getUrl()+".*");
		}
		if(null != saasLogs.getAction()){
			criteria.and("action").regex(".*"+saasLogs.getAction()+".*");
		}
		if(null != saasLogs.getParams()){
			criteria.and("params").regex(".*"+saasLogs.getParams()+".*");
		}
		if(null != saasLogs.isSuccess()){
			criteria.and("success").is(saasLogs.isSuccess());
		}
		if(null != saasLogs.getResult()){
			criteria.and("result").regex(".*"+saasLogs.getResult()+".*");
		}
		
		Query query = new Query(criteria).with(pageable);
		List<SaasLogs> list = mongoTemplate.find(query, SaasLogs.class);
		Long count = mongoTemplate.count(query, SaasLogs.class);
		Page<SaasLogs>  pages = new PageImpl<SaasLogs>(list,pageable,count);
		return pages;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
    public Page<ModuleReport> findModuleReport(ModuleAction moduleAction, int page, int size, String startdate, String enddate) {
        Criteria criteria = new Criteria();
        
        if(StringUtils.isNotEmpty(startdate) && StringUtils.isEmpty(enddate)){
            criteria.and("date").gte(startdate);
        }
        if(StringUtils.isNotEmpty(enddate) && StringUtils.isEmpty(startdate)){
            criteria.and("date").lte(enddate);
        }
        if(StringUtils.isNotEmpty(startdate) && StringUtils.isNotEmpty(enddate)){
            criteria.and("date").gte(startdate).lte(enddate);
        }
        if(null != moduleAction.getUrl()){
            criteria.and("url").regex(".*"+moduleAction.getUrl()+".*");
        }
        if(null != moduleAction.getAction()){
            criteria.and("action").regex(".*"+moduleAction.getAction()+".*");
        }
        
        GroupBy groupBy = GroupBy.key("cid","cname").initialDocument("{ count: 0 }").reduceFunction("function(doc, prev) { prev.count += 1 }");
        GroupByResults<ModuleReport> results = mongoTemplate.group(criteria, "saasLogs", groupBy, ModuleReport.class);
        Iterator<ModuleReport> reportIt = results.iterator();
        List<ModuleReport> reportList = IteratorUtils.toList(reportIt);
        long count = reportList.size();
        PageRequest pageable = new PageRequest(page, size, sort);
        Page<ModuleReport> pages = new PageImpl<ModuleReport>(reportList,pageable,count);
        return pages;
    }
	
	@Override
    public int findTotalCount(String field) {
	    if (StringUtils.isBlank(field)) {
	        return 0;
	    }
	    DBCollection coll = mongoTemplate.getCollection("saasLogs");
        return coll.distinct(field).size();
    }
}
