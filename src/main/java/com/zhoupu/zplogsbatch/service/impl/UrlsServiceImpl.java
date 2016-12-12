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
import org.springframework.transaction.annotation.Transactional;

import com.zhoupu.zplogsbatch.commons.DataCache;
import com.zhoupu.zplogsbatch.domain.Urls;
import com.zhoupu.zplogsbatch.repository.UrlsRepository;
import com.zhoupu.zplogsbatch.service.UrlsService;

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
public class UrlsServiceImpl implements UrlsService {

	@Autowired
	private UrlsRepository urlsRepository;

	@Override
	public void save(Urls u) {
		urlsRepository.save(u);
		DataCache.urlsMap.put(u.getUrl(), u.getName());
	}

	@Override
	public Map<String, Object> getInfoListMap(Map<String, Object> map) {
		int start = (Integer) map.get("start");
		int draw = (Integer) map.get("draw");
		int size = (Integer) map.get("length");
		int page = start / size;
		Page<Urls> pageContent = findAll(page, size);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("draw", draw);
		result.put("data", pageContent.getContent());
		result.put("recordsTotal", pageContent.getTotalElements());
		result.put("recordsFiltered", pageContent.getTotalElements());
		return result;
	}

	@Override
	public Page<Urls> findAll(int page, int size) {
		PageRequest pageable = new PageRequest(page, size);
		Page<Urls> pages = urlsRepository.findAll(pageable);
		return pages;
	}

	@Override
	public Urls findOne(Long id) {
		return urlsRepository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(String ids) {
		String[] idArr = StringUtils.split(ids, ",");
		Long[] idLongArr = (Long[]) ConvertUtils.convert(idArr, Long.class);
		List<Long> listIds = Arrays.asList(idLongArr);
		listIds.forEach(id -> {
			urlsRepository.delete(id);
		});

	}

}
