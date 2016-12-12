package com.zhoupu.zplogsbatch.service.observer;

import com.zhoupu.zplogsbatch.mongodb.SaasLogs;

/**
 * 观察者接口
 * 
 * @author tangdingyi
 *
 */
public interface LogsObserver {
	void update(SaasLogs logs);
}
