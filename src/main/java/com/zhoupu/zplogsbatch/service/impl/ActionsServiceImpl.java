package com.zhoupu.zplogsbatch.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.zhoupu.zplogsbatch.commons.DataCache;
import com.zhoupu.zplogsbatch.domain.Actions;
import com.zhoupu.zplogsbatch.repository.ActionsRepository;
import com.zhoupu.zplogsbatch.service.ActionsService;

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
public class ActionsServiceImpl implements ActionsService {

	@Autowired
	private ActionsRepository actionsRepository;

	@Override
	public void save(Actions u) {
		actionsRepository.save(u);
		DataCache.actionsMap.put(u.getAction(), u.getName());
	}

	@Override
	public Map<String, Object> getInfoListMap(Map<String, Object> map) {
		int start = (Integer) map.get("start");
		int draw = (Integer) map.get("draw");
		int size = (Integer) map.get("length");
		int page = start / size;
		Page<Actions> pageContent = findAll(page, size);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("draw", draw);
		result.put("data", pageContent.getContent());
		result.put("recordsTotal", pageContent.getTotalElements());
		result.put("recordsFiltered", pageContent.getTotalElements());
		return result;
	}

	@Override
	public Page<Actions> findAll(int page, int size) {
		PageRequest pageable = new PageRequest(page, size);
		Page<Actions> pages = actionsRepository.findAll(pageable);
		return pages;
	}

	@Override
	public Actions findOne(Long id) {
		return actionsRepository.findOne(id);
	}

	@Override
	public void delete(String ids) {
		String[] idArr = StringUtils.split(ids, ",");
		Long[] idLongArr = (Long[]) ConvertUtils.convert(idArr, Long.class);
		List<Long> listIds = Arrays.asList(idLongArr);
		listIds.forEach(id -> {
			actionsRepository.delete(id);
		});

	}

}
