package com.zhoupu.zplogsbatch.commons;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

public class DataCache {

	public static Map<Long, String> companyMap = new HashMap<>();
	
	public static Map<String, String> urlsMap = new HashMap<>();
	
	public static Map<String, String> actionsMap = new HashMap<>();


	// ---------------
	public static final Map<String, WebSocketSession> userSocketSessionMap = new ConcurrentHashMap<>();
	
	public static final Map<String, WebSocketSession> countSocketSessionMap = new ConcurrentHashMap<>();

}
