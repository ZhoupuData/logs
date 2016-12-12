package com.zhoupu.zplogsbatch.service;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.zhoupu.zplogsbatch.domain.Urls;

/**
 * [简要描述]:<br/>
 * [详细描述]:<br/>
 * 
 * @author tangdingyi E-mail: 13913364179@163.com
 * @date 创建时间：2016年11月17日 下午3:15:49
 * @version 1.0 *
 * @since
 */
public interface UrlsService {

	void save(Urls u);
	
	Urls findOne(Long id);

	Map<String, Object> getInfoListMap(Map<String, Object> map);

	public Page<Urls> findAll(int page, int size);

	void delete(String ids);
}
