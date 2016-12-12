package com.zhoupu.zplogsbatch.service.observer;

import java.util.ArrayList;
import java.util.List;

import com.zhoupu.zplogsbatch.mongodb.SaasLogs;


/**
 * 被观察者
 * 实时日志输出
 * @author tangdingyi
 *
 */
public class Logs {

	private List<LogsObserver> observers;

	public Logs() {
		observers = new ArrayList<>();
	}

	public void addObserver(LogsObserver obs) {
		observers.add(obs);
	}

	public void removeObserver(LogsObserver obs) {
		observers.remove(obs);
	}

	public void notifyObservers(SaasLogs logs) {
		for (LogsObserver obs : observers) {
			obs.update(logs);
		}
	}

}
