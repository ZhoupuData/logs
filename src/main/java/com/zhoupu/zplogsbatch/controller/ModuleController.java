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
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhoupu.zplogsbatch.commons.Result;
import com.zhoupu.zplogsbatch.domain.Module;
import com.zhoupu.zplogsbatch.domain.ModuleAction;
import com.zhoupu.zplogsbatch.service.LogService;
import com.zhoupu.zplogsbatch.service.ModuleService;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController {

	@Autowired
	private ModuleService moduleService;
	
	@Autowired
    private LogService logService;

	@GetMapping("/getmodule")
	public String getmodule() {

		return "saas/module";
	}
	
	@GetMapping("/geteditmodule/{id}")
	public String getEditModule(ModelMap modelMap,@PathVariable("id") Long id) {
	    Module module = null;
        if(0==id){
            module = new Module();
        }else{
            module = moduleService.findOne(id);
        }
        modelMap.put("module", module);
		return "saas/editmodule";
	}
	
	
	@PostMapping(value = "/save")
	public @ResponseBody Result save(Module module) {
	    moduleService.save(module);
		return Result.ok();
	}
	
	@DeleteMapping(value = "/delete/{ids}")
	public @ResponseBody Result delete(@PathVariable("ids") String ids) {
	    moduleService.delete(ids);
		return Result.ok();
	}


	@GetMapping(value = "")
	public @ResponseBody Map<String, Object> getAll(@RequestParam MultiValueMap<String, Object> param) {
		Map<String, Object> map = getCondition(param);
		return moduleService.getInfoListMap(map);
	}

	@GetMapping(value = "/report")
    public String getReport(ModelMap modelMap) {
        return "saas/querymodulereport";
    }
	
	@PostMapping(value = "/reportdata")
    public @ResponseBody Map<String, Object> getReportData(ModuleAction moduleAction, @RequestParam MultiValueMap<String, Object> param) {
	    Map<String, Object> map = getCondition(param);
	    
	    //moduleAction = new ModuleAction();
        //moduleAction.setUrl("/erp/doc/route/getConsumers");
        //moduleAction.setAction("GET");
        map.put("moduleAction", moduleAction);
        //map.put("startdate", "2016-12-08");
        //map.put("enddate", "2016-12-08");
        return moduleService.getReportMap(map);
    }
}
