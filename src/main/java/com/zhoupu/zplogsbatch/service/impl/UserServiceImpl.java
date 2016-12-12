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

import com.zhoupu.zplogsbatch.domain.User;
import com.zhoupu.zplogsbatch.repository.UserRepository;
import com.zhoupu.zplogsbatch.service.UserService;

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
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User u) {
		userRepository.save(u);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	@Override
	public User findOne(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findOne(id);
	}

	@Override
	public Map<String, Object> getInfoListMap(Map<String, Object> map) {
		int start = (Integer) map.get("start");
		int draw = (Integer) map.get("draw");
		int size = (Integer) map.get("length");
		int page = start / size;
		Page<User> pageContent = findAll(page, size);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("draw", draw);
		result.put("data", pageContent.getContent());
		result.put("recordsTotal", pageContent.getTotalElements());
		result.put("recordsFiltered", pageContent.getTotalElements());
		return result;
	}

	@Override
	public Page<User> findAll(int page, int size) {
		PageRequest pageable = new PageRequest(page, size);
		Page<User> pages = userRepository.findAll(pageable);
		return pages;
	}

	@Override
	public void delete(String ids) {
		String[] idArr = StringUtils.split(ids, ",");
		Long[] idLongArr = (Long[]) ConvertUtils.convert(idArr, Long.class);
		List<Long> listIds = Arrays.asList(idLongArr);
		listIds.forEach(id -> {
			userRepository.delete(id);
		});
		
	}
	
	
	
	

}
