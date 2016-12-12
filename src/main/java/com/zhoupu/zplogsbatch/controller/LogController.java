/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhoupu.zplogsbatch.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhoupu.zplogsbatch.mongodb.SaasLogs;
import com.zhoupu.zplogsbatch.service.LogService;

@Controller
@RequestMapping("/saas")
public class LogController extends BaseController {
	
	@Autowired
	private LogService logService;

	@GetMapping("/ouputlog")
	public String getSaasOuputlog() {

		return "saas/outputlog";
	}
	
	
	@GetMapping("/querylog")
	public String querylog() {

		return "saas/querylog";
	}
	
	
	@PostMapping(value = "")
	public @ResponseBody Map<String, Object> getAll(SaasLogs saasLogs,@RequestParam MultiValueMap<String, Object> param) {

		
		Map<String, Object> map = getCondition(param);


		return logService.getInfoListMap5(map, saasLogs);

	}
	
	
	

}
