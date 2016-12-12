package com.zhoupu.zplogsbatch.service.observer;

import static com.zhoupu.zplogsbatch.commons.DataCache.userSocketSessionMap;

import com.zhoupu.zplogsbatch.mongodb.SaasLogs;
import com.zhoupu.zplogsbatch.websocket.saaslog.SocketSessionUtils;


/**
 * 具体观察者 实时日志输出
 * 
 * @author tangdingyi
 *
 */
public class OutputLogsObserver implements LogsObserver {

	@Override
	public void update(SaasLogs logs) {
		SocketSessionUtils.broadcast(logs.toString(),userSocketSessionMap);
	}

}
