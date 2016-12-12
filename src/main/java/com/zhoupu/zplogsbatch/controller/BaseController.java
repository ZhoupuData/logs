
package com.zhoupu.zplogsbatch.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

	protected Map<String, Object> getCondition(MultiValueMap<String, Object> condition) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		String key = null;
		List<Object> value = null;
		Integer start = 1;
		Integer draw = 10;//d
		Integer length = 10;
		for (Map.Entry<String, List<Object>> entry : condition.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			if ("start".equals(key)) {
				start = NumberUtils.toInt(value.get(0).toString(), start);
			} else if ("draw".equals(key)) {
				draw = NumberUtils.toInt(value.get(0).toString(), draw);
			} else if ("length".equals(key)) {
				length = NumberUtils.toInt(value.get(0).toString(), length);
			} else {
				conditionMap.put(key, value.get(0).toString());
			}
		}
		conditionMap.put("start", start);
		conditionMap.put("draw", draw);
		conditionMap.put("length", length);
		return conditionMap;
	}
	
	
	@ExceptionHandler
	protected void handleException(Exception ex) {
		// 开发打印堆栈到控制台
		ex.printStackTrace();
	}

}
